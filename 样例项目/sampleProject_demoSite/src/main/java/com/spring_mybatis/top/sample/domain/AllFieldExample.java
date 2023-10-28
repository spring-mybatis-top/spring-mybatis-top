//本代码由 http://spring-mybatis.top 生成
package com.spring_mybatis.top.sample.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AllFieldExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AllFieldExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameIntegerIsNull() {
            addCriterion("NAME_INTEGER is null");
            return (Criteria) this;
        }

        public Criteria andNameIntegerIsNotNull() {
            addCriterion("NAME_INTEGER is not null");
            return (Criteria) this;
        }

        public Criteria andNameIntegerEqualTo(Integer value) {
            addCriterion("NAME_INTEGER =", value, "nameInteger");
            return (Criteria) this;
        }

        public Criteria andNameIntegerNotEqualTo(Integer value) {
            addCriterion("NAME_INTEGER <>", value, "nameInteger");
            return (Criteria) this;
        }

        public Criteria andNameIntegerGreaterThan(Integer value) {
            addCriterion("NAME_INTEGER >", value, "nameInteger");
            return (Criteria) this;
        }

        public Criteria andNameIntegerGreaterThanOrEqualTo(Integer value) {
            addCriterion("NAME_INTEGER >=", value, "nameInteger");
            return (Criteria) this;
        }

        public Criteria andNameIntegerLessThan(Integer value) {
            addCriterion("NAME_INTEGER <", value, "nameInteger");
            return (Criteria) this;
        }

        public Criteria andNameIntegerLessThanOrEqualTo(Integer value) {
            addCriterion("NAME_INTEGER <=", value, "nameInteger");
            return (Criteria) this;
        }

        public Criteria andNameIntegerIn(List<Integer> values) {
            addCriterion("NAME_INTEGER in", values, "nameInteger");
            return (Criteria) this;
        }

        public Criteria andNameIntegerNotIn(List<Integer> values) {
            addCriterion("NAME_INTEGER not in", values, "nameInteger");
            return (Criteria) this;
        }

        public Criteria andNameIntegerBetween(Integer value1, Integer value2) {
            addCriterion("NAME_INTEGER between", value1, value2, "nameInteger");
            return (Criteria) this;
        }

        public Criteria andNameIntegerNotBetween(Integer value1, Integer value2) {
            addCriterion("NAME_INTEGER not between", value1, value2, "nameInteger");
            return (Criteria) this;
        }

        public Criteria andNameLongIsNull() {
            addCriterion("NAME_LONG is null");
            return (Criteria) this;
        }

        public Criteria andNameLongIsNotNull() {
            addCriterion("NAME_LONG is not null");
            return (Criteria) this;
        }

        public Criteria andNameLongEqualTo(Long value) {
            addCriterion("NAME_LONG =", value, "nameLong");
            return (Criteria) this;
        }

        public Criteria andNameLongNotEqualTo(Long value) {
            addCriterion("NAME_LONG <>", value, "nameLong");
            return (Criteria) this;
        }

        public Criteria andNameLongGreaterThan(Long value) {
            addCriterion("NAME_LONG >", value, "nameLong");
            return (Criteria) this;
        }

        public Criteria andNameLongGreaterThanOrEqualTo(Long value) {
            addCriterion("NAME_LONG >=", value, "nameLong");
            return (Criteria) this;
        }

        public Criteria andNameLongLessThan(Long value) {
            addCriterion("NAME_LONG <", value, "nameLong");
            return (Criteria) this;
        }

        public Criteria andNameLongLessThanOrEqualTo(Long value) {
            addCriterion("NAME_LONG <=", value, "nameLong");
            return (Criteria) this;
        }

        public Criteria andNameLongIn(List<Long> values) {
            addCriterion("NAME_LONG in", values, "nameLong");
            return (Criteria) this;
        }

        public Criteria andNameLongNotIn(List<Long> values) {
            addCriterion("NAME_LONG not in", values, "nameLong");
            return (Criteria) this;
        }

        public Criteria andNameLongBetween(Long value1, Long value2) {
            addCriterion("NAME_LONG between", value1, value2, "nameLong");
            return (Criteria) this;
        }

        public Criteria andNameLongNotBetween(Long value1, Long value2) {
            addCriterion("NAME_LONG not between", value1, value2, "nameLong");
            return (Criteria) this;
        }

        public Criteria andNameBigDecimalIsNull() {
            addCriterion("NAME_BIG_DECIMAL is null");
            return (Criteria) this;
        }

        public Criteria andNameBigDecimalIsNotNull() {
            addCriterion("NAME_BIG_DECIMAL is not null");
            return (Criteria) this;
        }

        public Criteria andNameBigDecimalEqualTo(BigDecimal value) {
            addCriterion("NAME_BIG_DECIMAL =", value, "nameBigDecimal");
            return (Criteria) this;
        }

        public Criteria andNameBigDecimalNotEqualTo(BigDecimal value) {
            addCriterion("NAME_BIG_DECIMAL <>", value, "nameBigDecimal");
            return (Criteria) this;
        }

        public Criteria andNameBigDecimalGreaterThan(BigDecimal value) {
            addCriterion("NAME_BIG_DECIMAL >", value, "nameBigDecimal");
            return (Criteria) this;
        }

        public Criteria andNameBigDecimalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("NAME_BIG_DECIMAL >=", value, "nameBigDecimal");
            return (Criteria) this;
        }

        public Criteria andNameBigDecimalLessThan(BigDecimal value) {
            addCriterion("NAME_BIG_DECIMAL <", value, "nameBigDecimal");
            return (Criteria) this;
        }

        public Criteria andNameBigDecimalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("NAME_BIG_DECIMAL <=", value, "nameBigDecimal");
            return (Criteria) this;
        }

        public Criteria andNameBigDecimalIn(List<BigDecimal> values) {
            addCriterion("NAME_BIG_DECIMAL in", values, "nameBigDecimal");
            return (Criteria) this;
        }

        public Criteria andNameBigDecimalNotIn(List<BigDecimal> values) {
            addCriterion("NAME_BIG_DECIMAL not in", values, "nameBigDecimal");
            return (Criteria) this;
        }

        public Criteria andNameBigDecimalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NAME_BIG_DECIMAL between", value1, value2, "nameBigDecimal");
            return (Criteria) this;
        }

        public Criteria andNameBigDecimalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NAME_BIG_DECIMAL not between", value1, value2, "nameBigDecimal");
            return (Criteria) this;
        }

        public Criteria andNameFloatIsNull() {
            addCriterion("NAME_FLOAT is null");
            return (Criteria) this;
        }

        public Criteria andNameFloatIsNotNull() {
            addCriterion("NAME_FLOAT is not null");
            return (Criteria) this;
        }

        public Criteria andNameFloatEqualTo(Float value) {
            addCriterion("NAME_FLOAT =", value, "nameFloat");
            return (Criteria) this;
        }

        public Criteria andNameFloatNotEqualTo(Float value) {
            addCriterion("NAME_FLOAT <>", value, "nameFloat");
            return (Criteria) this;
        }

        public Criteria andNameFloatGreaterThan(Float value) {
            addCriterion("NAME_FLOAT >", value, "nameFloat");
            return (Criteria) this;
        }

        public Criteria andNameFloatGreaterThanOrEqualTo(Float value) {
            addCriterion("NAME_FLOAT >=", value, "nameFloat");
            return (Criteria) this;
        }

        public Criteria andNameFloatLessThan(Float value) {
            addCriterion("NAME_FLOAT <", value, "nameFloat");
            return (Criteria) this;
        }

        public Criteria andNameFloatLessThanOrEqualTo(Float value) {
            addCriterion("NAME_FLOAT <=", value, "nameFloat");
            return (Criteria) this;
        }

        public Criteria andNameFloatIn(List<Float> values) {
            addCriterion("NAME_FLOAT in", values, "nameFloat");
            return (Criteria) this;
        }

        public Criteria andNameFloatNotIn(List<Float> values) {
            addCriterion("NAME_FLOAT not in", values, "nameFloat");
            return (Criteria) this;
        }

        public Criteria andNameFloatBetween(Float value1, Float value2) {
            addCriterion("NAME_FLOAT between", value1, value2, "nameFloat");
            return (Criteria) this;
        }

        public Criteria andNameFloatNotBetween(Float value1, Float value2) {
            addCriterion("NAME_FLOAT not between", value1, value2, "nameFloat");
            return (Criteria) this;
        }

        public Criteria andNameDoubleIsNull() {
            addCriterion("NAME_DOUBLE is null");
            return (Criteria) this;
        }

        public Criteria andNameDoubleIsNotNull() {
            addCriterion("NAME_DOUBLE is not null");
            return (Criteria) this;
        }

        public Criteria andNameDoubleEqualTo(Double value) {
            addCriterion("NAME_DOUBLE =", value, "nameDouble");
            return (Criteria) this;
        }

        public Criteria andNameDoubleNotEqualTo(Double value) {
            addCriterion("NAME_DOUBLE <>", value, "nameDouble");
            return (Criteria) this;
        }

        public Criteria andNameDoubleGreaterThan(Double value) {
            addCriterion("NAME_DOUBLE >", value, "nameDouble");
            return (Criteria) this;
        }

        public Criteria andNameDoubleGreaterThanOrEqualTo(Double value) {
            addCriterion("NAME_DOUBLE >=", value, "nameDouble");
            return (Criteria) this;
        }

        public Criteria andNameDoubleLessThan(Double value) {
            addCriterion("NAME_DOUBLE <", value, "nameDouble");
            return (Criteria) this;
        }

        public Criteria andNameDoubleLessThanOrEqualTo(Double value) {
            addCriterion("NAME_DOUBLE <=", value, "nameDouble");
            return (Criteria) this;
        }

        public Criteria andNameDoubleIn(List<Double> values) {
            addCriterion("NAME_DOUBLE in", values, "nameDouble");
            return (Criteria) this;
        }

        public Criteria andNameDoubleNotIn(List<Double> values) {
            addCriterion("NAME_DOUBLE not in", values, "nameDouble");
            return (Criteria) this;
        }

        public Criteria andNameDoubleBetween(Double value1, Double value2) {
            addCriterion("NAME_DOUBLE between", value1, value2, "nameDouble");
            return (Criteria) this;
        }

        public Criteria andNameDoubleNotBetween(Double value1, Double value2) {
            addCriterion("NAME_DOUBLE not between", value1, value2, "nameDouble");
            return (Criteria) this;
        }

        public Criteria andNameBooleanIsNull() {
            addCriterion("NAME_BOOLEAN is null");
            return (Criteria) this;
        }

        public Criteria andNameBooleanIsNotNull() {
            addCriterion("NAME_BOOLEAN is not null");
            return (Criteria) this;
        }

        public Criteria andNameBooleanEqualTo(Boolean value) {
            addCriterion("NAME_BOOLEAN =", value, "nameBoolean");
            return (Criteria) this;
        }

        public Criteria andNameBooleanNotEqualTo(Boolean value) {
            addCriterion("NAME_BOOLEAN <>", value, "nameBoolean");
            return (Criteria) this;
        }

        public Criteria andNameBooleanGreaterThan(Boolean value) {
            addCriterion("NAME_BOOLEAN >", value, "nameBoolean");
            return (Criteria) this;
        }

        public Criteria andNameBooleanGreaterThanOrEqualTo(Boolean value) {
            addCriterion("NAME_BOOLEAN >=", value, "nameBoolean");
            return (Criteria) this;
        }

        public Criteria andNameBooleanLessThan(Boolean value) {
            addCriterion("NAME_BOOLEAN <", value, "nameBoolean");
            return (Criteria) this;
        }

        public Criteria andNameBooleanLessThanOrEqualTo(Boolean value) {
            addCriterion("NAME_BOOLEAN <=", value, "nameBoolean");
            return (Criteria) this;
        }

        public Criteria andNameBooleanIn(List<Boolean> values) {
            addCriterion("NAME_BOOLEAN in", values, "nameBoolean");
            return (Criteria) this;
        }

        public Criteria andNameBooleanNotIn(List<Boolean> values) {
            addCriterion("NAME_BOOLEAN not in", values, "nameBoolean");
            return (Criteria) this;
        }

        public Criteria andNameBooleanBetween(Boolean value1, Boolean value2) {
            addCriterion("NAME_BOOLEAN between", value1, value2, "nameBoolean");
            return (Criteria) this;
        }

        public Criteria andNameBooleanNotBetween(Boolean value1, Boolean value2) {
            addCriterion("NAME_BOOLEAN not between", value1, value2, "nameBoolean");
            return (Criteria) this;
        }

        public Criteria andNameLocalDateIsNull() {
            addCriterion("NAME_LOCAL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andNameLocalDateIsNotNull() {
            addCriterion("NAME_LOCAL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andNameLocalDateEqualTo(Date value) {
            addCriterionForJDBCDate("NAME_LOCAL_DATE =", value, "nameLocalDate");
            return (Criteria) this;
        }

        public Criteria andNameLocalDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("NAME_LOCAL_DATE <>", value, "nameLocalDate");
            return (Criteria) this;
        }

        public Criteria andNameLocalDateGreaterThan(Date value) {
            addCriterionForJDBCDate("NAME_LOCAL_DATE >", value, "nameLocalDate");
            return (Criteria) this;
        }

        public Criteria andNameLocalDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("NAME_LOCAL_DATE >=", value, "nameLocalDate");
            return (Criteria) this;
        }

        public Criteria andNameLocalDateLessThan(Date value) {
            addCriterionForJDBCDate("NAME_LOCAL_DATE <", value, "nameLocalDate");
            return (Criteria) this;
        }

        public Criteria andNameLocalDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("NAME_LOCAL_DATE <=", value, "nameLocalDate");
            return (Criteria) this;
        }

        public Criteria andNameLocalDateIn(List<Date> values) {
            addCriterionForJDBCDate("NAME_LOCAL_DATE in", values, "nameLocalDate");
            return (Criteria) this;
        }

        public Criteria andNameLocalDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("NAME_LOCAL_DATE not in", values, "nameLocalDate");
            return (Criteria) this;
        }

        public Criteria andNameLocalDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("NAME_LOCAL_DATE between", value1, value2, "nameLocalDate");
            return (Criteria) this;
        }

        public Criteria andNameLocalDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("NAME_LOCAL_DATE not between", value1, value2, "nameLocalDate");
            return (Criteria) this;
        }

        public Criteria andNameZonedDateTimeIsNull() {
            addCriterion("NAME_ZONED_DATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andNameZonedDateTimeIsNotNull() {
            addCriterion("NAME_ZONED_DATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andNameZonedDateTimeEqualTo(Date value) {
            addCriterion("NAME_ZONED_DATE_TIME =", value, "nameZonedDateTime");
            return (Criteria) this;
        }

        public Criteria andNameZonedDateTimeNotEqualTo(Date value) {
            addCriterion("NAME_ZONED_DATE_TIME <>", value, "nameZonedDateTime");
            return (Criteria) this;
        }

        public Criteria andNameZonedDateTimeGreaterThan(Date value) {
            addCriterion("NAME_ZONED_DATE_TIME >", value, "nameZonedDateTime");
            return (Criteria) this;
        }

        public Criteria andNameZonedDateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("NAME_ZONED_DATE_TIME >=", value, "nameZonedDateTime");
            return (Criteria) this;
        }

        public Criteria andNameZonedDateTimeLessThan(Date value) {
            addCriterion("NAME_ZONED_DATE_TIME <", value, "nameZonedDateTime");
            return (Criteria) this;
        }

        public Criteria andNameZonedDateTimeLessThanOrEqualTo(Date value) {
            addCriterion("NAME_ZONED_DATE_TIME <=", value, "nameZonedDateTime");
            return (Criteria) this;
        }

        public Criteria andNameZonedDateTimeIn(List<Date> values) {
            addCriterion("NAME_ZONED_DATE_TIME in", values, "nameZonedDateTime");
            return (Criteria) this;
        }

        public Criteria andNameZonedDateTimeNotIn(List<Date> values) {
            addCriterion("NAME_ZONED_DATE_TIME not in", values, "nameZonedDateTime");
            return (Criteria) this;
        }

        public Criteria andNameZonedDateTimeBetween(Date value1, Date value2) {
            addCriterion("NAME_ZONED_DATE_TIME between", value1, value2, "nameZonedDateTime");
            return (Criteria) this;
        }

        public Criteria andNameZonedDateTimeNotBetween(Date value1, Date value2) {
            addCriterion("NAME_ZONED_DATE_TIME not between", value1, value2, "nameZonedDateTime");
            return (Criteria) this;
        }

        public Criteria andNameInstantIsNull() {
            addCriterion("NAME_INSTANT is null");
            return (Criteria) this;
        }

        public Criteria andNameInstantIsNotNull() {
            addCriterion("NAME_INSTANT is not null");
            return (Criteria) this;
        }

        public Criteria andNameInstantEqualTo(Date value) {
            addCriterion("NAME_INSTANT =", value, "nameInstant");
            return (Criteria) this;
        }

        public Criteria andNameInstantNotEqualTo(Date value) {
            addCriterion("NAME_INSTANT <>", value, "nameInstant");
            return (Criteria) this;
        }

        public Criteria andNameInstantGreaterThan(Date value) {
            addCriterion("NAME_INSTANT >", value, "nameInstant");
            return (Criteria) this;
        }

        public Criteria andNameInstantGreaterThanOrEqualTo(Date value) {
            addCriterion("NAME_INSTANT >=", value, "nameInstant");
            return (Criteria) this;
        }

        public Criteria andNameInstantLessThan(Date value) {
            addCriterion("NAME_INSTANT <", value, "nameInstant");
            return (Criteria) this;
        }

        public Criteria andNameInstantLessThanOrEqualTo(Date value) {
            addCriterion("NAME_INSTANT <=", value, "nameInstant");
            return (Criteria) this;
        }

        public Criteria andNameInstantIn(List<Date> values) {
            addCriterion("NAME_INSTANT in", values, "nameInstant");
            return (Criteria) this;
        }

        public Criteria andNameInstantNotIn(List<Date> values) {
            addCriterion("NAME_INSTANT not in", values, "nameInstant");
            return (Criteria) this;
        }

        public Criteria andNameInstantBetween(Date value1, Date value2) {
            addCriterion("NAME_INSTANT between", value1, value2, "nameInstant");
            return (Criteria) this;
        }

        public Criteria andNameInstantNotBetween(Date value1, Date value2) {
            addCriterion("NAME_INSTANT not between", value1, value2, "nameInstant");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeIsNull() {
            addCriterion("NAME_BLOB_CONTENT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeIsNotNull() {
            addCriterion("NAME_BLOB_CONTENT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeEqualTo(String value) {
            addCriterion("NAME_BLOB_CONTENT_TYPE =", value, "nameBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeNotEqualTo(String value) {
            addCriterion("NAME_BLOB_CONTENT_TYPE <>", value, "nameBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeGreaterThan(String value) {
            addCriterion("NAME_BLOB_CONTENT_TYPE >", value, "nameBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("NAME_BLOB_CONTENT_TYPE >=", value, "nameBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeLessThan(String value) {
            addCriterion("NAME_BLOB_CONTENT_TYPE <", value, "nameBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeLessThanOrEqualTo(String value) {
            addCriterion("NAME_BLOB_CONTENT_TYPE <=", value, "nameBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeLike(String value) {
            addCriterion("NAME_BLOB_CONTENT_TYPE like", value, "nameBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeNotLike(String value) {
            addCriterion("NAME_BLOB_CONTENT_TYPE not like", value, "nameBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeIn(List<String> values) {
            addCriterion("NAME_BLOB_CONTENT_TYPE in", values, "nameBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeNotIn(List<String> values) {
            addCriterion("NAME_BLOB_CONTENT_TYPE not in", values, "nameBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeBetween(String value1, String value2) {
            addCriterion("NAME_BLOB_CONTENT_TYPE between", value1, value2, "nameBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameBlobContentTypeNotBetween(String value1, String value2) {
            addCriterion("NAME_BLOB_CONTENT_TYPE not between", value1, value2, "nameBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeIsNull() {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeIsNotNull() {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeEqualTo(String value) {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE =", value, "nameAnyBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeNotEqualTo(String value) {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE <>", value, "nameAnyBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeGreaterThan(String value) {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE >", value, "nameAnyBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE >=", value, "nameAnyBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeLessThan(String value) {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE <", value, "nameAnyBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeLessThanOrEqualTo(String value) {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE <=", value, "nameAnyBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeLike(String value) {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE like", value, "nameAnyBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeNotLike(String value) {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE not like", value, "nameAnyBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeIn(List<String> values) {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE in", values, "nameAnyBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeNotIn(List<String> values) {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE not in", values, "nameAnyBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeBetween(String value1, String value2) {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE between", value1, value2, "nameAnyBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameAnyBlobContentTypeNotBetween(String value1, String value2) {
            addCriterion("NAME_ANY_BLOB_CONTENT_TYPE not between", value1, value2, "nameAnyBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeIsNull() {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeIsNotNull() {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeEqualTo(String value) {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE =", value, "nameImageBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeNotEqualTo(String value) {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE <>", value, "nameImageBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeGreaterThan(String value) {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE >", value, "nameImageBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE >=", value, "nameImageBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeLessThan(String value) {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE <", value, "nameImageBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeLessThanOrEqualTo(String value) {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE <=", value, "nameImageBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeLike(String value) {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE like", value, "nameImageBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeNotLike(String value) {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE not like", value, "nameImageBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeIn(List<String> values) {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE in", values, "nameImageBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeNotIn(List<String> values) {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE not in", values, "nameImageBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeBetween(String value1, String value2) {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE between", value1, value2, "nameImageBlobContentType");
            return (Criteria) this;
        }

        public Criteria andNameImageBlobContentTypeNotBetween(String value1, String value2) {
            addCriterion("NAME_IMAGE_BLOB_CONTENT_TYPE not between", value1, value2, "nameImageBlobContentType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}