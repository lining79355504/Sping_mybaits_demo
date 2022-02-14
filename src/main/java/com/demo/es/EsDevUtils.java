package com.demo.es;

import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.index.AliasAction;
import org.springframework.data.elasticsearch.core.index.AliasActionParameters;
import org.springframework.data.elasticsearch.core.index.AliasActions;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author mort
 * @Description
 * @date 2022/2/14
 *
 *
 * <dependency>
 * <groupId>org.springframework.data</groupId>
 * <artifactId>spring-data-elasticsearch</artifactId>
 * <version>4.3.1</version>
 * </dependency>
 **/
public class EsDevUtils {

    private static final Logger logger = LoggerFactory.getLogger(EsDevUtils.class);

    public void createIndexByMonth(Timestamp date, String indexName, String indexAliasesName,
                                   int day, int shards, int replicas, ElasticsearchRestTemplate elasticsearchTemplate) {

        Timestamp someDayAfterToday = new Timestamp(date.getTime() + day * 24 * 60 * 60 * 1000L);
        String newIndexName = indexName + "_" + new SimpleDateFormat("yyyy_MM").format(new Date(someDayAfterToday.getTime()));
        IndexOperations indexOperations = elasticsearchTemplate.indexOps(IndexCoordinates.of(indexName));
        if (!indexOperations.exists()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("number_of_shards", shards);
            map.put("number_of_replicas", replicas);
            indexOperations.create(map);
            indexOperations.putMapping(TestPo.class);
            logger.info("job putMapping currentDateIndex newIndex is {}.", newIndexName);

            AliasActions aliasActions = new AliasActions();
            aliasActions.add(new AliasAction.Add(AliasActionParameters.builder()
                    .withAliases(indexAliasesName)
                    .withIndices(indexName)
//                    .withIsHidden()
//                    .withIsWriteIndex()
//                    .withRouting()
                    .build()));
            indexOperations.alias(aliasActions);
            boolean result = indexOperations.create();
            logger.info("job create currentDateIndex newIndex is {} , alias result  is {} ", newIndexName, result);
        }
    }

}
