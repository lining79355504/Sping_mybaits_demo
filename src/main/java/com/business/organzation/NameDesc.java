package com.business.organzation;

/**
 * Author:  lining17
 * Date :  2020-05-27
 */
public class NameDesc {

    private String name;
    private String desc;
    private String type;

    public NameDesc(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public NameDesc(String name, String desc, String type) {
        this.name = name;
        this.desc = desc;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NameDesc{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
