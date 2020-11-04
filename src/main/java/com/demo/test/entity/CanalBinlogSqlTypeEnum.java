package com.demo.test.entity;

public enum CanalBinlogSqlTypeEnum {

    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private String typeName;

    CanalBinlogSqlTypeEnum(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
