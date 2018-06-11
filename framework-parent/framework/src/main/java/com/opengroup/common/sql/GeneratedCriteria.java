package com.opengroup.common.sql;

import com.opengroup.tools.string.StringUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.Condition;

/**
 * Created by walker on 17-10-16.
 */
public abstract class GeneratedCriteria {
    protected List<Criterion> criteria;

    protected GeneratedCriteria() {
        super();
        criteria = new ArrayList<Criterion>();
    }

    public boolean isValid() {
        return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
        return criteria;
    }

    public List<Criterion> getCriteria() {
        return criteria;
    }

    protected void addCriterion(String condition, ConditionSymbolEnum symbol) {
        if (condition == null) {
            throw new RuntimeException("Value for condition cannot be null");
        }

        criteria.add(new Criterion(condition + symbol.getSymbol()));
    }

    protected void addCriterion(String condition, ConditionSymbolEnum symbol, Object value,
                                String property) {
        if (condition == null) {
            throw new RuntimeException("Value for condition cannot be null");
        }

        if (value == null) {
            return;
        }
        if (value instanceof String){
            if(StringUtil.isBlank((String)value)){
                return;
            }
        }
        criteria.add(new Criterion(condition + symbol.getSymbol(), value));
    }

    protected void addCriterion(String condition, ConditionSymbolEnum symbol, Object value1,
                                Object value2, String property) {


        if (condition == null) {
            throw new RuntimeException("Value for condition cannot be null");
        }

        if (value1 == null && value2 == null) {
            return;
        }

        if (value1 == null || isEmptyString(value1)){
            addCriterion(condition,ConditionSymbolEnum.LESS_THAN_OR_EQUAL,value2,property);
            return;
        }

        if (value2 == null
                || isEmptyString(value2)){
            addCriterion(condition,ConditionSymbolEnum.GREATER_THAN_OR_EQUAL,value1,property);
            return;
        }

        criteria.add(new Criterion(condition + symbol.getSymbol(), value1, value2));
    }

    public Criteria andFieldIsNull(GenerateField generateField) {
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.IS_NULL);
        return (Criteria) this;
    }

    public Criteria andFieldIsNotNull(GenerateField generateField) {
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.IS_NOT_NULL);
        return (Criteria) this;
    }

    public Criteria andFieldEqualTo(GenerateField generateField, Object value) {
        isTypeMatch(generateField, value);
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.EQUAL, value,
            generateField.getPropertyName());
        return (Criteria) this;
    }

    public Criteria andFieldNotEqualTo(GenerateField generateField, Object value) {
        isTypeMatch(generateField, value);
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.NOT_EQUAL, value,
            generateField.getPropertyName());
        return (Criteria) this;
    }

    public Criteria andFieldGreaterThan(GenerateField generateField, Object value) {
        isTypeMatch(generateField, value);
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.GREATER_THAN, value,
            generateField.getColumnName());
        return (Criteria) this;
    }

    public Criteria andFieldGreaterThanOrEqualTo(GenerateField generateField, Object value) {
        isTypeMatch(generateField, value);
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.GREATER_THAN_OR_EQUAL,
            value, generateField.getColumnName());
        return (Criteria) this;
    }

    public Criteria andFieldLessThan(GenerateField generateField, Object value) {
        isTypeMatch(generateField, value);
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.LESS_THAN, value,
            generateField.getColumnName());
        return (Criteria) this;
    }

    public Criteria andFieldLessThanOrEqualTo(GenerateField generateField, Object value) {
        isTypeMatch(generateField, value);
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.LESS_THAN_OR_EQUAL, value,
            generateField.getColumnName());
        return (Criteria) this;
    }

    public Criteria andFieldLike(GenerateField generateField, Object value) {
        isTypeMatch(generateField, value);
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.LIKE, value,
            generateField.getColumnName());
        return (Criteria) this;
    }

    public Criteria andFieldNotLike(GenerateField generateField, Object value) {
        isTypeMatch(generateField, value);
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.NOT_LIKE, value,
            generateField.getColumnName());
        return (Criteria) this;
    }

    public Criteria andFieldIn(GenerateField generateField, Collection values) {
        if (CollectionUtils.isEmpty(values)){
            return (Criteria) this;
        }
        isTypeMatch(generateField, values);
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.IN, values,
            generateField.getColumnName());
        return (Criteria) this;
    }

    public Criteria andFieldNotIn(GenerateField generateField, Collection values) {
        if (CollectionUtils.isEmpty(values)){
            return (Criteria) this;
        }
        isTypeMatch(generateField, values);
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.NOT_IN, values,
            generateField.getColumnName());
        return (Criteria) this;
    }

    public Criteria andFieldBetween(GenerateField generateField, Object value1, Object value2) {

        isTypeMatch(generateField, value1, value2);
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.BETWEEN, value1, value2,
            generateField.getColumnName());
        return (Criteria) this;
    }

    public Criteria andFieldNotBetween(GenerateField generateField, Object value1, Object value2) {
        isTypeMatch(generateField, value1, value2);
        addCriterion(generateField.getColumnName(), ConditionSymbolEnum.NOT_BETWEEN, value1, value2,
            generateField.getColumnName());
        return (Criteria) this;
    }
    public Criteria mergeCriteria(Criteria criteria){
        if (criteria == null){
            return (Criteria) this;
        }
        for (Criterion criterion : criteria.criteria){
            this.criteria.add(criterion);
        }
         return (Criteria) this;
    }
    private void isTypeMatch(GenerateField generateField, Collection collection) {
        if (collection == null){
            return;
        }
        for (Object value : collection){
            isTypeMatch(generateField,value);
        }
    }

    private void isTypeMatch(GenerateField generateField, Object... values) {

        boolean isNumber = false;
        if (generateField.getType().getSuperclass().equals(Number.class)){
            isNumber = true;
        }
        for (Object value : values){
            if (value == null){
                continue;
            }
            Class valueClazz = value.getClass();
            if (valueClazz.equals(generateField.getType())){
                //类型相同，可以互相转
                continue;
            } else if (isNumber && valueClazz.getSuperclass().equals(Number.class)){
                //两边都是数字,可以互相转
                continue;
            } else {
                throw new RuntimeException("参数类型和数据表中数据类型不匹配");
            }
        }
    }

    private boolean isEmptyString(Object value){
        return (value instanceof String) && (StringUtil.isBlank((String) value));
    }
}