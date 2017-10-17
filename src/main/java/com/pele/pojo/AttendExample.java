package com.pele.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AttendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AttendExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
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

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAttendDateIsNull() {
            addCriterion("attend_date is null");
            return (Criteria) this;
        }

        public Criteria andAttendDateIsNotNull() {
            addCriterion("attend_date is not null");
            return (Criteria) this;
        }

        public Criteria andAttendDateEqualTo(Date value) {
            addCriterionForJDBCDate("attend_date =", value, "attendDate");
            return (Criteria) this;
        }

        public Criteria andAttendDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("attend_date <>", value, "attendDate");
            return (Criteria) this;
        }

        public Criteria andAttendDateGreaterThan(Date value) {
            addCriterionForJDBCDate("attend_date >", value, "attendDate");
            return (Criteria) this;
        }

        public Criteria andAttendDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("attend_date >=", value, "attendDate");
            return (Criteria) this;
        }

        public Criteria andAttendDateLessThan(Date value) {
            addCriterionForJDBCDate("attend_date <", value, "attendDate");
            return (Criteria) this;
        }

        public Criteria andAttendDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("attend_date <=", value, "attendDate");
            return (Criteria) this;
        }

        public Criteria andAttendDateIn(List<Date> values) {
            addCriterionForJDBCDate("attend_date in", values, "attendDate");
            return (Criteria) this;
        }

        public Criteria andAttendDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("attend_date not in", values, "attendDate");
            return (Criteria) this;
        }

        public Criteria andAttendDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("attend_date between", value1, value2, "attendDate");
            return (Criteria) this;
        }

        public Criteria andAttendDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("attend_date not between", value1, value2, "attendDate");
            return (Criteria) this;
        }

        public Criteria andAttendWeekIsNull() {
            addCriterion("attend_week is null");
            return (Criteria) this;
        }

        public Criteria andAttendWeekIsNotNull() {
            addCriterion("attend_week is not null");
            return (Criteria) this;
        }

        public Criteria andAttendWeekEqualTo(Byte value) {
            addCriterion("attend_week =", value, "attendWeek");
            return (Criteria) this;
        }

        public Criteria andAttendWeekNotEqualTo(Byte value) {
            addCriterion("attend_week <>", value, "attendWeek");
            return (Criteria) this;
        }

        public Criteria andAttendWeekGreaterThan(Byte value) {
            addCriterion("attend_week >", value, "attendWeek");
            return (Criteria) this;
        }

        public Criteria andAttendWeekGreaterThanOrEqualTo(Byte value) {
            addCriterion("attend_week >=", value, "attendWeek");
            return (Criteria) this;
        }

        public Criteria andAttendWeekLessThan(Byte value) {
            addCriterion("attend_week <", value, "attendWeek");
            return (Criteria) this;
        }

        public Criteria andAttendWeekLessThanOrEqualTo(Byte value) {
            addCriterion("attend_week <=", value, "attendWeek");
            return (Criteria) this;
        }

        public Criteria andAttendWeekIn(List<Byte> values) {
            addCriterion("attend_week in", values, "attendWeek");
            return (Criteria) this;
        }

        public Criteria andAttendWeekNotIn(List<Byte> values) {
            addCriterion("attend_week not in", values, "attendWeek");
            return (Criteria) this;
        }

        public Criteria andAttendWeekBetween(Byte value1, Byte value2) {
            addCriterion("attend_week between", value1, value2, "attendWeek");
            return (Criteria) this;
        }

        public Criteria andAttendWeekNotBetween(Byte value1, Byte value2) {
            addCriterion("attend_week not between", value1, value2, "attendWeek");
            return (Criteria) this;
        }

        public Criteria andAttendMorningIsNull() {
            addCriterion("attend_morning is null");
            return (Criteria) this;
        }

        public Criteria andAttendMorningIsNotNull() {
            addCriterion("attend_morning is not null");
            return (Criteria) this;
        }

        public Criteria andAttendMorningEqualTo(Date value) {
            addCriterionForJDBCTime("attend_morning =", value, "attendMorning");
            return (Criteria) this;
        }

        public Criteria andAttendMorningNotEqualTo(Date value) {
            addCriterionForJDBCTime("attend_morning <>", value, "attendMorning");
            return (Criteria) this;
        }

        public Criteria andAttendMorningGreaterThan(Date value) {
            addCriterionForJDBCTime("attend_morning >", value, "attendMorning");
            return (Criteria) this;
        }

        public Criteria andAttendMorningGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("attend_morning >=", value, "attendMorning");
            return (Criteria) this;
        }

        public Criteria andAttendMorningLessThan(Date value) {
            addCriterionForJDBCTime("attend_morning <", value, "attendMorning");
            return (Criteria) this;
        }

        public Criteria andAttendMorningLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("attend_morning <=", value, "attendMorning");
            return (Criteria) this;
        }

        public Criteria andAttendMorningIn(List<Date> values) {
            addCriterionForJDBCTime("attend_morning in", values, "attendMorning");
            return (Criteria) this;
        }

        public Criteria andAttendMorningNotIn(List<Date> values) {
            addCriterionForJDBCTime("attend_morning not in", values, "attendMorning");
            return (Criteria) this;
        }

        public Criteria andAttendMorningBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("attend_morning between", value1, value2, "attendMorning");
            return (Criteria) this;
        }

        public Criteria andAttendMorningNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("attend_morning not between", value1, value2, "attendMorning");
            return (Criteria) this;
        }

        public Criteria andAttendEveningIsNull() {
            addCriterion("attend_evening is null");
            return (Criteria) this;
        }

        public Criteria andAttendEveningIsNotNull() {
            addCriterion("attend_evening is not null");
            return (Criteria) this;
        }

        public Criteria andAttendEveningEqualTo(Date value) {
            addCriterionForJDBCTime("attend_evening =", value, "attendEvening");
            return (Criteria) this;
        }

        public Criteria andAttendEveningNotEqualTo(Date value) {
            addCriterionForJDBCTime("attend_evening <>", value, "attendEvening");
            return (Criteria) this;
        }

        public Criteria andAttendEveningGreaterThan(Date value) {
            addCriterionForJDBCTime("attend_evening >", value, "attendEvening");
            return (Criteria) this;
        }

        public Criteria andAttendEveningGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("attend_evening >=", value, "attendEvening");
            return (Criteria) this;
        }

        public Criteria andAttendEveningLessThan(Date value) {
            addCriterionForJDBCTime("attend_evening <", value, "attendEvening");
            return (Criteria) this;
        }

        public Criteria andAttendEveningLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("attend_evening <=", value, "attendEvening");
            return (Criteria) this;
        }

        public Criteria andAttendEveningIn(List<Date> values) {
            addCriterionForJDBCTime("attend_evening in", values, "attendEvening");
            return (Criteria) this;
        }

        public Criteria andAttendEveningNotIn(List<Date> values) {
            addCriterionForJDBCTime("attend_evening not in", values, "attendEvening");
            return (Criteria) this;
        }

        public Criteria andAttendEveningBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("attend_evening between", value1, value2, "attendEvening");
            return (Criteria) this;
        }

        public Criteria andAttendEveningNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("attend_evening not between", value1, value2, "attendEvening");
            return (Criteria) this;
        }

        public Criteria andAbsenceIsNull() {
            addCriterion("absence is null");
            return (Criteria) this;
        }

        public Criteria andAbsenceIsNotNull() {
            addCriterion("absence is not null");
            return (Criteria) this;
        }

        public Criteria andAbsenceEqualTo(Integer value) {
            addCriterion("absence =", value, "absence");
            return (Criteria) this;
        }

        public Criteria andAbsenceNotEqualTo(Integer value) {
            addCriterion("absence <>", value, "absence");
            return (Criteria) this;
        }

        public Criteria andAbsenceGreaterThan(Integer value) {
            addCriterion("absence >", value, "absence");
            return (Criteria) this;
        }

        public Criteria andAbsenceGreaterThanOrEqualTo(Integer value) {
            addCriterion("absence >=", value, "absence");
            return (Criteria) this;
        }

        public Criteria andAbsenceLessThan(Integer value) {
            addCriterion("absence <", value, "absence");
            return (Criteria) this;
        }

        public Criteria andAbsenceLessThanOrEqualTo(Integer value) {
            addCriterion("absence <=", value, "absence");
            return (Criteria) this;
        }

        public Criteria andAbsenceIn(List<Integer> values) {
            addCriterion("absence in", values, "absence");
            return (Criteria) this;
        }

        public Criteria andAbsenceNotIn(List<Integer> values) {
            addCriterion("absence not in", values, "absence");
            return (Criteria) this;
        }

        public Criteria andAbsenceBetween(Integer value1, Integer value2) {
            addCriterion("absence between", value1, value2, "absence");
            return (Criteria) this;
        }

        public Criteria andAbsenceNotBetween(Integer value1, Integer value2) {
            addCriterion("absence not between", value1, value2, "absence");
            return (Criteria) this;
        }

        public Criteria andAttendStatusIsNull() {
            addCriterion("attend_status is null");
            return (Criteria) this;
        }

        public Criteria andAttendStatusIsNotNull() {
            addCriterion("attend_status is not null");
            return (Criteria) this;
        }

        public Criteria andAttendStatusEqualTo(Byte value) {
            addCriterion("attend_status =", value, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusNotEqualTo(Byte value) {
            addCriterion("attend_status <>", value, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusGreaterThan(Byte value) {
            addCriterion("attend_status >", value, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("attend_status >=", value, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusLessThan(Byte value) {
            addCriterion("attend_status <", value, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusLessThanOrEqualTo(Byte value) {
            addCriterion("attend_status <=", value, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusIn(List<Byte> values) {
            addCriterion("attend_status in", values, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusNotIn(List<Byte> values) {
            addCriterion("attend_status not in", values, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusBetween(Byte value1, Byte value2) {
            addCriterion("attend_status between", value1, value2, "attendStatus");
            return (Criteria) this;
        }

        public Criteria andAttendStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("attend_status not between", value1, value2, "attendStatus");
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