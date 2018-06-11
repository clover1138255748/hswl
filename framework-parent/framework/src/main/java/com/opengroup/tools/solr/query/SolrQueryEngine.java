package com.opengroup.tools.solr.query;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.opengroup.common.anno.SolrEntityTag;
import com.opengroup.exception.BaseFrameworkException;
import com.opengroup.tools.solr.beans.client.SolrClientWrapper;
import com.opengroup.tools.solr.beans.params.SolrQueryCondition;
import com.opengroup.tools.solr.beans.response.QueryResult;
import com.opengroup.tools.solr.pool.SolrClientPool;

/**
 * 直接面向solr组件调用者的solr查询核心类，提供一些solr查询的方法
 * 
 * @author ijavoracle
 * @version $Id: SolrQueryEngine.java, v 0.1 2016年1月19日 上午10:52:49 ijavoracle Exp $
 */
public class SolrQueryEngine {

    /**
     * 保留的字段:_type_，用于标记数据来源与哪个表
     */
    private String         RESERVED_FIELD_TYPE = "_type_";

    private SolrClientPool solrClientPool;

    public SolrQueryEngine(SolrClientPool solrClientPool) {
        this.solrClientPool = solrClientPool;
    }

    public QueryResult<Map<String, Object>> query(SolrQueryCondition condition) {
        SolrClientWrapper scw = solrClientPool.getSolrClient();
        try {
            SolrQuery sq = condition.getRequestParams();
            QueryResponse resp = scw.getSolrClient().query(sq);
            SolrDocumentList docs = resp.getResults();
            return iterateDocs(docs);
        } catch (SolrServerException e) {
            throw new BaseFrameworkException(e);
        } catch (IOException e) {
            throw new BaseFrameworkException(e);
        } finally {
            solrClientPool.releaseSolrClient(scw);
        }
    }

    public <T> QueryResult<T> query(SolrQueryCondition condition, Class<T> clazz) {
        String _type_ = getEntityType(clazz);
        //如果配置了业务实体类和solr DIH实体的映射关系，则只查询指定entity的数据
        if (!StringUtils.isEmpty(_type_)) {
            condition.addFilterQuery(RESERVED_FIELD_TYPE, _type_.trim());
        }

        SolrClientWrapper scw = solrClientPool.getSolrClient();
        try {
            SolrQuery sq = condition.getRequestParams();
            QueryResponse resp = scw.getSolrClient().query(sq);
            SolrDocumentList docs = resp.getResults();
            QueryResult<Map<String, Object>> mapTmpResult = iterateDocs(docs);

            List<T> items = new ArrayList<T>();
            List<Map<String, Object>> mapItems = mapTmpResult.getItems();
            for (Map<String, Object> mapItem : mapItems) {
                items.add(JSON.parseObject(JSON.toJSONString(mapItem), clazz));
            }
            QueryResult<T> result = new QueryResult<T>();
            result.setNumFound(mapTmpResult.getNumFound());
            result.setItems(items);
            return result;
        } catch (SolrServerException e) {
            throw new BaseFrameworkException(e);
        } catch (IOException e) {
            throw new BaseFrameworkException(e);
        } finally {
            solrClientPool.releaseSolrClient(scw);
        }
    }

    private QueryResult<Map<String, Object>> iterateDocs(SolrDocumentList docs) {
        QueryResult<Map<String, Object>> result = new QueryResult<Map<String, Object>>();
        result.setNumFound(docs.getNumFound());

        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
        Map<String, Object> fieldValueMap;
        Map<String, Object> copyMap;
        for (SolrDocument doc : docs) {
            fieldValueMap = doc.getFieldValueMap();
            //因为doc.getFieldValueMap()返回的是“简化”的不支持map.entrySet()等方法map，无法toJson()，因此转换成HashMap。
            copyMap = new HashMap<String, Object>();
            for (String key : fieldValueMap.keySet()) {
                copyMap.put(key, fieldValueMap.get(key));
            }
            items.add(copyMap);
        }
        result.setItems(items);
        return result;
    }

    private String getEntityType(Class<?> clazz) {
        Annotation[] annos = clazz.getDeclaredAnnotations();
        if (annos != null) {
            for (Annotation anno : annos) {
                if (anno instanceof SolrEntityTag) {
                    SolrEntityTag tanno = (SolrEntityTag) anno;
                    return tanno == null ? null : tanno.value();
                }
            }
        }
        return null;
    }
}
