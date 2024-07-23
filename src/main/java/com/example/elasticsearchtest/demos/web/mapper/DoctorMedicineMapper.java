package com.example.elasticsearchtest.demos.web.mapper;

import com.example.elasticsearchtest.demos.web.pojo.DoctorMedicine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName: DoctorMedicineMapper
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/22 20:00
 * @Version: 1.0
 */
@Mapper
public interface DoctorMedicineMapper {

    @Select("SELECT * FROM `t_doctor_medicine`")
    List<DoctorMedicine> getAll();

    @Select("SELECT * FROM `t_doctor_medicine` where  id = #{id}")
    DoctorMedicine getOne(Integer id);
}
