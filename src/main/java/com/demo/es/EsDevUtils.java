package com.demo.es;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.index.AliasAction;
import org.springframework.data.elasticsearch.core.index.AliasActionParameters;
import org.springframework.data.elasticsearch.core.index.AliasActions;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
            map.put("refresh_interval", "30s");  //设置自动refresh时间30s
            indexOperations.putMapping(TestPo.class);
            boolean result = indexOperations.create(map);
            logger.info("job putMapping currentDateIndex newIndex is {}.", newIndexName);

            AliasActions aliasActions = new AliasActions();
            aliasActions.add(new AliasAction.Add(AliasActionParameters.builder()
                    .withAliases(indexAliasesName)
                    .withIndices(indexName)
//                    .withIsHidden()
//                    .withIsWriteIndex()
//                    .withRouting()
                    .build()));
            boolean aliasResult = indexOperations.alias(aliasActions);
//            indexOperations.refresh();
            logger.info("job create currentDateIndex newIndex is {} , create result  is {}  aliasResult is {} ", newIndexName, result, aliasResult);
        }
    }

    public void bulk(ElasticsearchRestTemplate elasticsearchTemplate, List<TestPo> datas) {
        elasticsearchTemplate.save(datas);
    }

}
