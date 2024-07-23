package com.example.elasticsearchtest;

import com.alibaba.fastjson.JSON;
import com.example.elasticsearchtest.demos.web.pojo.DoctorMedicine;
import com.example.elasticsearchtest.demos.web.service.DoctorMedicineServie;
import com.example.elasticsearchtest.demos.web.utils.SerializationUtil;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName: SearchTest
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/23 11:37
 * @Version: 1.0
 */

@SpringBootTest
public class SearchTest {

    @Resource
    DoctorMedicineServie doctorMedicineServie;

    private RestHighLevelClient client;

    //初始化RestClient客户端
    @BeforeEach
    void setUp() {
        this.client=new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://localhost:9200")
        ));
    }

    //关闭RestHighLevelClient
    @AfterEach
    void afterAll() throws IOException {
        this.client.close();
    }

    // 查询所有数据
    @Test
    public void searchAll() throws IOException {

        //执行查询
        //准备一个请求
        SearchRequest searchRequest = new SearchRequest("doctormedicine");

        //准备DSL
        searchRequest.source().query(QueryBuilders.matchAllQuery());

        //发送请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        //解析数据
        SerializationUtil.autoParse(searchResponse);

    }


    // 查询特定数据
    @Test
    public void findOneBook() throws IOException {

        //执行查询
        //准备一个请求
        SearchRequest searchRequest = new SearchRequest("doctormedicine");

        //准备DSL
        searchRequest.source().query(QueryBuilders.matchQuery("sourceBook","伤寒论"));

        //发送请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        //解析数据
        SerializationUtil.autoParse(searchResponse);
    }

    // 查询特定数据全等
    @Test
    public void findOneTerm() throws IOException {

        //执行查询
        //准备一个请求
        SearchRequest searchRequest = new SearchRequest("doctormedicine");

        //准备DSL
        searchRequest.source().query(QueryBuilders.termQuery("source","伤寒论"));

        //发送请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        //解析数据
        SerializationUtil.autoParse(searchResponse);
    }


    // 查询range数据
    @Test
    public void findOneTermInRange() throws IOException {

        //执行查询
        //准备一个请求
        SearchRequest searchRequest = new SearchRequest("doctormedicine");

        //准备DSL
        searchRequest.source().query(QueryBuilders.rangeQuery("prescriptionCode").gt(4400).lt(4500));

        //发送请求
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        //解析数据
        SerializationUtil.autoParse(searchResponse);
    }


    //复合查询
    @Test
    public void BoolSearch() throws IOException {
        // 1.准备Request
        SearchRequest request = new SearchRequest("doctormedicine");
        // 2.准备DSL
        // 2.1.准备BooleanQuery
        request.source().query(QueryBuilders
                // 2.2.添加查询条件
                //- must：必须匹配每个子查询，类似“与”
                // - should：选择性匹配子查询，类似“或”
                // - must_not：必须不匹配，**不参与算分**，类似“非”
                // - filter：必须匹配，**不参与算分**
                .boolQuery().must(QueryBuilders.matchQuery("sourceBook","普济本事方"))
                        .must(QueryBuilders.matchQuery("source","普济"))
                        .must(QueryBuilders.matchQuery("indication","膨胀")));
        // 3.发送请求
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 4.解析响应
        SerializationUtil.autoParse(response);
    }


}
