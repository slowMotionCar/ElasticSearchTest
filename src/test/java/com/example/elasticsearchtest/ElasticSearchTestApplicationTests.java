package com.example.elasticsearchtest;

import com.example.elasticsearchtest.demos.web.pojo.HotelConstants;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/***
* @Description //简单增删改查
* @Param
* @return
**/
@SpringBootTest
class ElasticSearchTestApplicationTests {


    private RestHighLevelClient client;

    @Test
    void contextLoads() {
        System.out.println("hello");
    }

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

    //添加索引
    @Test
    public void createIndex() throws IOException {
        //创建一个CreateIndexRequest对象
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("hotel");
        //用json格式写入CreateIndexRequest
        createIndexRequest.source(HotelConstants.MAPPING_TEMPLATE, XContentType.JSON);
        //通过RestClient发送
        client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }


    //删除索引
    @Test
    public void deleteIndex() throws IOException {

        // 1.创建Request对象
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("hotel");
        // 2.发送请求
        client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);

    }

    // 查询索引存在吗
    @Test
    public void availableIndex() throws IOException {
        // 1.创建Request对象
        GetIndexRequest request = new GetIndexRequest("hotel");
        GetIndexRequest request2 = new GetIndexRequest("book");
        // 2.发送请求
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        boolean exists2 = client.indices().exists(request2, RequestOptions.DEFAULT);
        // 3.输出
        System.out.println(exists ? "该索引库已存在" : "该索引库不存在!");
        System.out.println(exists2 ? "该索引库已存在" : "该索引库不存在!");
    }

}
