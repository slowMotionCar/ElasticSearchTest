package com.example.elasticsearchtest.demos.web.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName: DoctorMedicineES
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/22 20:21
 * @Version: 1.0
 */

@Data
public class DoctorMedicineES {
    private Long id;

    //对应知识库方剂code
    private String prescriptionCode;

    /**
     * 对应字典表的科目类别code
     */
    private String subjectsType;

    /**
     * 是否为制式，0 : 默认，否；1：是
     */
    private Integer isStandard;

    /**
     * 药方名称
     */
    private String name;

    /**
     * 治疗
     */
    private String treatment;

    /**
     * 来源
     */
    private String source;

    /**
     * 用法
     */
    private String useRemark;

    /**
     * 出处
     */
    private String sourceBook;


    /**
     * 组成
     */
    private String component;

    /**
     * 用法
     */
    private String usageText;

    /**
     * 功用
     */
    private String effect;

    /**
     * 适用症状
     */
    private String indication;


    private List<Object> details;

    //煎法
    private String decoctingMethod;


    private String dynasty;

}
