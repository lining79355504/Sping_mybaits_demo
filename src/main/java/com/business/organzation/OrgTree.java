package com.business.organzation;

import java.io.Serializable;
import java.util.List;

/**
 * Author:  lining17
 * Date :  2020-05-27
 */
public class OrgTree implements Serializable {


    private static final long serialVersionUID = 796774075409184389L;

    private String desc;

    private String name;

    private List<String> admin;

    private String type;

    private List<OrgTree> childrenOrganizations;

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

    public List<String> getAdmin() {
        return admin;
    }

    public void setAdmin(List<String> admin) {
        this.admin = admin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrgTree> getChildrenOrganizations() {
        return childrenOrganizations;
    }

    public void setChildrenOrganizations(List<OrgTree> childrenOrganizations) {
        this.childrenOrganizations = childrenOrganizations;
    }
}
