package com.business.organzation;

import java.io.Serializable;
import java.util.List;

/**
 * Author:  lining17
 * Date :  2020-05-27
 */
public class OrgInfo implements Serializable {


    private static final long serialVersionUID = -1038934403148932222L;

    private String desc;

    private String name;

    private String type;

    private List<String> admins;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getAdmins() {
        return admins;
    }

    public void setAdmins(List<String> admins) {
        this.admins = admins;
    }

    @Override
    public String toString() {
        return "MrnOrganizationInfo{" +
                "desc='" + desc + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", admins=" + admins +
                '}';
    }
}