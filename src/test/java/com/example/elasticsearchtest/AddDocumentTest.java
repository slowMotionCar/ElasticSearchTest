package com.example.elasticsearchtest;

import com.alibaba.fastjson.JSON;
import com.example.elasticsearchtest.demos.web.pojo.DoctorMedicine;
import com.example.elasticsearchtest.demos.web.pojo.DoctorMedicineES;
import com.example.elasticsearchtest.demos.web.pojo.HotelConstants;
import com.example.elasticsearchtest.demos.web.service.DoctorMedicineServie;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @ClassName: AddDocument
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/22 19:43
 * @Version: 1.0
 */
@SpringBootTest
public class AddDocumentTest {
    private static final Logger logger = Logger.getLogger(AddDocumentTest.class.getName());

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

    //添加索引
    @Test
    public void createIndex() throws IOException {
        //创建一个CreateIndexRequest对象
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("doctormedicine");
        //用json格式写入CreateIndexRequest
        createIndexRequest.source(HotelConstants.MAPPING_TEMPLATE, XContentType.JSON);
        //通过RestClient发送
        client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    @Test
    public void addDoc() throws IOException {


        //从数据库读取
        DoctorMedicine one = doctorMedicineServie.findOne(1);

        //准备为一个ES数据
        DoctorMedicineES doctorMedicineES = new DoctorMedicineES();
        BeanUtils.copyProperties(one,doctorMedicineES);

        //封装JSON
        String stringMedicine = JSON.toJSONString(doctorMedicineES);
        logger.info(one.toString());
        logger.warning(stringMedicine.toString());

        //创建Request对象
        IndexRequest doctormedicineRequest = new IndexRequest("doctormedicine").id(doctorMedicineES.getId().toString());
        doctormedicineRequest.source(stringMedicine,XContentType.JSON);
        client.index(doctormedicineRequest, RequestOptions.DEFAULT);
    }

    //查询文档
    @Test
    public void findDoc() throws IOException {

        //查询的index和id
        GetRequest getRequest = new GetRequest("doctormedicine", "1");

        //查询
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);

        //解析
        String json = response.getSourceAsString();
        DoctorMedicineES doctorMedicineES = JSON.parseObject(json, DoctorMedicineES.class);
        logger.info(doctorMedicineES.toString());

    }



}
