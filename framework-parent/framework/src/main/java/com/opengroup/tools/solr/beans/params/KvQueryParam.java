package com.opengroup.tools.solr.beans.params;

import org.springframework.util.StringUtils;

/**
 * 简单的field-value形式的查询参数
 * 
 * @author ijavoracle
 * @version $Id: KvQueryParam.java, v 0.1 2016年1月18日 下午3:20:29 ijavoracle Exp $
 */
public class KvQueryParam extends QueryParam {

    /**
     * 参数值
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
     * 把原始输入值作为查询参数，不做任何其它处理
     * 
     * @param fieldValue
     */
    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

}
