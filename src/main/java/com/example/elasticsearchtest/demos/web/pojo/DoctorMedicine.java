package com.example.elasticsearchtest.demos.web.pojo;

/**
 * @ClassName: DoctorMedicine
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/22 19:50
 * @Version: 1.0
 */

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

/**
 * 医生的药方表(DoctorMedicine)实体类
 *
 * @author sola
 * @date 2020-12-14 15:37:05
 */

@Table(name = "t_doctor_medicine")
@Data
@NameStyle(Style.normal)
public class DoctorMedicine {

    @Id
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
     * 类型，1：处方，2：膏方
     */
    private Integer type;

    /**
     * 膏方类型，0 : 默认，普通；1：素膏；2：清膏
     */
    private Integer medicinalgelType;

    /**
     * 药方名称
     */
    private String name;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 拼音简码
     */
    private String pinyinCode;

    /**
     * 医生id
     */
    private Long doctorId;

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
     * 状态，-1：废弃，0：可用
     */
    private Integer status;

    /**
     * sn排序值
     */
    private Integer sn;

    /**
     * 处方费
     */
    private BigDecimal prescriptionFee;

    /**
     * 制作基础费
     */
    private BigDecimal makeBaseFee;

    /**
     * 制作增长费
     */
    private BigDecimal makeIncreaseFee;

    /**
     * 膏方药材转化比率
     */
    private Integer ratio;

    /**
     * 味数
     */
    private Integer medicinalNum;

    /**
     * 出处
     */
    private String sourceBook;

    /**
     * 原文
     */
    private String sourceText;

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

    /**
     * 方解
     */
    private String analysis;

    private List<Object> details;

    //煎法
    private String decoctingMethod;

    private String avoid;

    private String processKey;

    /*介绍*/
    private String bookContent;

    private String governance;

    private String modernResearch;

    private String medicalHistory;

    private String mistreatment;

    private String metamorphosis;

    private String pathogenesis;

    private String syndrome;

    private String selectedNotes;

    private String intendedFor1;

    private String intendedFor2;

    private String pathogeny1;

    private String pathogeny2;

    private String pathogeny3;

    private String pathogeny4;

    private String addAndSub;

    private String compatibility;

    private String king;

    private String minister;

    private String zuo;

    private String dynasty;

    private String pic;

    //名称name 出处bookSource:sourceBook 原文originalText:sourceText 煎法decoctingMethod:decoctingMethod 用法(服法)usa:usageText 禁忌avoid:avoid
    //主治attending:treatment 方解(加减)fangXie:analysis
}

