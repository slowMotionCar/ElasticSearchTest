package com.example.elasticsearchtest.demos.web.utils;

import com.alibaba.fastjson.JSON;
import com.example.elasticsearchtest.demos.web.pojo.DoctorMedicineES;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

/**
 * @ClassName: SerializationUtil
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/23 11:41
 * @Version: 1.0
 */
public class SerializationUtil {

    //提取自动解析, 作为公共解析方法!
    public static void autoParse(SearchResponse response) {
        SearchHits hits = response.getHits();
        TotalHits totalHits = hits.getTotalHits();
        System.out.println("总共命中了!"+totalHits);
        SearchHit[] hitsList = hits.getHits();
        for (SearchHit hitItem : hitsList) {
            String sourceAsString = hitItem.getSourceAsString();
            DoctorMedicineES doctorMedicineES = JSON.parseObject(sourceAsString, DoctorMedicineES.class);
            System.out.println(doctorMedicineES.toString());
        }
    }
}
