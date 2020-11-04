package com.demo.test.entity;


import java.io.Serializable;
import java.util.List;

public class CanalBinlogEntity<V> implements Serializable {

    private static final long serialVersionUID = -4507273519960819614L;

    private List<V> data;

    private String database;

    private String table;

    private CanalBinlogSqlTypeEnum type;

    private long es;

    private long ts;

    private long id;

    private boolean isDdl;

    private List<V> old;

    private List<String> pkNames;

    private String sql;

    public List<V> getData() {
        return data;
    }

    public void setData(List<V> data) {
        this.data = data;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public CanalBinlogSqlTypeEnum getType() {
        return type;
    }

    public void setType(CanalBinlogSqlTypeEnum type) {
        this.type = type;
    }

    public long getEs() {
        return es;
    }

    public void setEs(long es) {
        this.es = es;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDdl() {
        return isDdl;
    }

    public void setDdl(boolean ddl) {
        isDdl = ddl;
    }

    public List<V> getOld() {
        return old;
    }

    public void setOld(List<V> old) {
        this.old = old;
    }

    public List<String> getPkNames() {
        return pkNames;
    }

    public void setPkNames(List<String> pkNames) {
        this.pkNames = pkNames;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "CanalBinlogEntity{" +
                "data=" + data +
                ", database='" + database + '\'' +
                ", table='" + table + '\'' +
                ", type=" + type +
                ", es=" + es +
                ", ts=" + ts +
                ", id=" + id +
                ", isDdl=" + isDdl +
                ", old=" + old +
                ", pkNames=" + pkNames +
                ", sql='" + sql + '\'' +
                '}';
    }
}
