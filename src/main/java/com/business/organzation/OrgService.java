package com.business.organzation;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.demo.utils.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author:  lining17
 * Date :  2020-05-27
 */
public class OrgService {

    private static final Logger log = LoggerFactory.getLogger(OrgService.class);

    private static OrgTree orgInfo = new OrgTree();

    static {

        String json = "{\n" +
                "  \"name\": \"1\",\n" +
                "  \"type\": \"1\",\n" +
                "  \"desc\": \"1\",\n" +
                "  \"admin\": [\n" +
                "    \"12\",\n" +
                "    \"12\"\n" +
                "  ],\n" +
                "  \"childrenOrganizations\": [\n" +
                "    {\n" +
                "      \"name\": \"2\",\n" +
                "      \"type\": \"2\",\n" +
                "      \"desc\": \"2\",\n" +
                "      \"admin\": [\n" +
                "        \"12\",\n" +
                "        \"12\"\n" +
                "      ],\n" +
                "      \"childrenOrganizations\": [\n" +
                "        {\n" +
                "          \"name\": \"3\",\n" +
                "          \"type\": \"3\",\n" +
                "          \"desc\": \"3\",\n" +
                "          \"admin\": [\n" +
                "            \"12\",\n" +
                "            \"12\"\n" +
                "          ],\n" +
                "          \"childrenOrganizations\": []\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"12\",\n" +
                "      \"type\": \"2\",\n" +
                "      \"desc\": \"12\",\n" +
                "      \"admin\": [\n" +
                "        \"12\",\n" +
                "        \"12\"\n" +
                "      ],\n" +
                "      \"childrenOrganizations\": []\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        orgInfo = GsonUtils.fromJson(json, OrgTree.class);
    }


    public List<OrgInfo> organization(String type, String value) {
        List<OrgInfo> ret = new ArrayList<>();
        OrgTree searchOrgInfo = getParentInfo(orgInfo, type, value);
        if (null != searchOrgInfo) {
            List<OrgTree> childrenOrganizations = searchOrgInfo.getChildrenOrganizations();

            ret = childrenOrganizations.stream().map(orgTmp -> {
                OrgInfo tmp = new OrgInfo();
                tmp.setAdmins(orgTmp.getAdmin());
                tmp.setName(orgTmp.getName());
                tmp.setType(orgTmp.getType());
                return tmp;
            }).collect(Collectors.toList());
        }

        return ret;
    }


    public List<Map<String, NameDesc>>  orgSearch(String name) {
        List<Map<String, NameDesc>>  ret = new ArrayList<>();
        search(orgInfo, name, ret, new HashMap<>());
        return ret;
    }

    private void search(OrgTree orgInfo, String name, List<Map<String, NameDesc>> list,
                        Map<String, NameDesc> tmp) {
        tmp.put(orgInfo.getType(), new NameDesc(orgInfo.getName(), orgInfo.getDesc()));
        List<OrgTree> organizations = orgInfo.getChildrenOrganizations();
        if (CollectionUtils.isEmpty(organizations)) {
            tmp.remove(orgInfo.getType());
            return;
        }

        for (OrgTree organization : organizations) {
            if (organization.getName().contains(name)) {
                tmp.put(organization.getType(), new NameDesc(organization.getName(), organization.getDesc()));
                list.add(new HashMap<>(tmp));
            }
            search(organization, name, list, tmp);
        }
    }


    public OrgTree getParentInfo(OrgTree organization, String type, String value) {

        List<OrgTree> childrenOrganizations = organization.getChildrenOrganizations();
        if (CollectionUtils.isEmpty(childrenOrganizations)) {
            return null;
        }

        for (OrgTree childrenOrganization : childrenOrganizations) {
            if (childrenOrganization.getType().equals(type) && childrenOrganization.getName().equals(value)) {
                return organization;
            }

            OrgTree tmp = getParentInfo(childrenOrganization, type, value);
            if (null != tmp) {
                return tmp;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        OrgService orgService = new OrgService();
        List<Map<String, NameDesc>> maps = orgService.orgSearch("12");
        log.info("{}", maps);
        List<OrgInfo> organization = orgService.organization("2", "2");
        log.info("{}", organization);

    }

}
