package com.demo.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import groovy.transform.builder.Builder;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 
 */
//@Data
@Builder
public class AccAccountWalletLogPoEntity implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 账号ID
     */
    private Integer accountId;

    /**
     * 操作类型(0消费， 1充值, 2人工返货, 3系统返货, 4反作弊返款, 5特批返货, 6-专项返货, 11资金转入, 12资金转出, 13-退款)
     */
    private Integer operationType;

    /**
     * 现金
     */
    private Long cash;

    /**
     * 红包
     */
    private Long redPacket;

    /**
     * 专项返货
     */
    private Long specialRedPacket;

    /**
     * 备注
     */
    private String remark;

    /**
     * 软删除，0是有效，1是删除
     */
    private Integer isDeleted;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp ctime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp mtime;

    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp date;

    /**
     * 历史代理商id
     */
    private Integer oldAgentId;

    /**
     * 售卖类型
     */
    private Integer salesType;

    /**
     * 新代理商id
     */
    private Integer agentId;

    private static final long serialVersionUID = 1L;
}