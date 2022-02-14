package com.demo.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author mort
 * @Description
 * @date 2022/2/14
 **/
@Document(indexName = "test_index_name")
public class TestPo {

    @Id
    private Long id;

    /**
     * 账号ID
     */
    private Integer accountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
