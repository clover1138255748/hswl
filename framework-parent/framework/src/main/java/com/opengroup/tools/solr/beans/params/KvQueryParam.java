package com.opengroup.tools.solr.beans.params;

import org.springframework.util.StringUtils;

/**
 * �򵥵�field-value��ʽ�Ĳ�ѯ����
 * 
 * @author ijavoracle
 * @version $Id: KvQueryParam.java, v 0.1 2016��1��18�� ����3:20:29 ijavoracle Exp $
 */
public class KvQueryParam extends QueryParam {

    /**
     * ����ֵ
     */
    private String fieldValue;

    public KvQueryParam() {
    }

    public KvQueryParam(String fieldName, String fieldValue) {
        this(fieldName, fieldValue, BooleanOperator.OR);
    }

    public KvQueryParam(String fieldName, String fieldValue, BooleanOperator paramOperator) {
        this(fieldName, fieldValue, paramOperator, null);
    }

    public KvQueryParam(String fieldName, String fieldValue, Class<?> typeClazz) {
        this(fieldName, fieldValue, BooleanOperator.OR, typeClazz);
    }

    public KvQueryParam(String fieldName, String fieldValue, BooleanOperator paramOperator,
                        Class<?> typeClazz) {
        super(fieldName, paramOperator, typeClazz);
        this.fieldValue = fieldValue;
    }

    @Override
    public String getFieldValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(fieldValue);
        if (!StringUtils.isEmpty(this.getMagicType())) {
            sb.append(" +_type_:").append(this.getMagicType());
        }
        return sb.toString();
    }

    /**
     * ��ԭʼ����ֵ��Ϊ��ѯ�����������κ���������
     * 
     * @param fieldValue
     */
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

}
