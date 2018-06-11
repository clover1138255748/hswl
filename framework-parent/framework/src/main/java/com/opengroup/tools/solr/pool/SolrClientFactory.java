package com.opengroup.tools.solr.pool;

import java.util.Date;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import com.opengroup.exception.BaseFrameworkException;
import com.opengroup.tools.solr.beans.client.SolrClientWrapper;

/**
 * A simple factory to construct new SolrClient instance
 * 
 * @author ijavoracle
 * @version $Id: SolrClientFactory.java, v 0.1 2016��1��15�� ����2:59:04 ijavoracle Exp $
 */
public class SolrClientFactory {

    private SolrClientPoolConfig config;

    public SolrClientFactory(SolrClientPoolConfig config) {
        this.config = config;
    }

    /**
     * ����SolrClientʵ��
     * 
     * @param type
     * @return
     */
    public SolrClientWrapper newSolrClient() {
        SolrServerTypeEnum type = (SolrServerTypeEnum) config.get(SolrPoolConfigItem.SERVER_TYPE);
        SolrClient client;
        if (type == SolrServerTypeEnum.SINGLE_NODE) {
            client = new HttpSolrClient((String) config.get(SolrPoolConfigItem.SERVER_URL));
        } else if (type == SolrServerTypeEnum.SOLR_CLOUD) {
            //TODO 
            throw new BaseFrameworkException("SolrCloud������...TODO... ");
        } else {
            throw new BaseFrameworkException("��Ч��Solr��������");
        }

        return new SolrClientWrapper(client, new Date());
    }
}
