package by.gsu.epamlab.model.enums;

import by.gsu.epamlab.model.constants.QueryConstants;

public enum TaskEnum {
    TODAY("actual", "and date <= current_date()"),
    TOMORROW("actual", "and date = current_date() + interval 1 day"),
    OTHER("actual", "and date > current_date() + interval 1 day"),
    FIXED("fixed", ""),
    DELETED("deleted", "");
    private String status;
    private String sqlDateQuery;

    TaskEnum(String status, String sqlDateQuery) {
        this.status = status;
        this.sqlDateQuery = sqlDateQuery;
    }

    public String getStatus() {
        return status;
    }

    public String getSqlDateQuery() {
        return sqlDateQuery;
    }

    public String getQuery(){
        return QueryConstants.GET_TASK_QUERY_START + getSqlDateQuery() + QueryConstants.GET_TASK_QUERY_END;
    }
}
