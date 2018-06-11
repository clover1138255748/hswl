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
 * ֱ������solr��������ߵ�solr��ѯ�����࣬�ṩһЩsolr��ѯ�ķ���
 * 
 * @author ijavoracle
 * @version $Id: SolrQueryEngine.java, v 0.1 2016��1��19�� ����10:52:49 ijavoracle Exp $
 */
public class SolrQueryEngine {

    /**
     * �������ֶ�:_type_�����ڱ��������Դ���ĸ���
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
        //���������ҵ��ʵ�����solr DIHʵ���ӳ���ϵ����ֻ��ѯָ��entity������
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
            //��Ϊdoc.getFieldValueMap()���ص��ǡ��򻯡��Ĳ�֧��map.entrySet()�ȷ���map���޷�toJson()�����ת����HashMap��
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
