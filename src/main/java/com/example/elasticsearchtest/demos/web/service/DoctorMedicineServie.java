package com.example.elasticsearchtest.demos.web.service;

import com.example.elasticsearchtest.demos.web.pojo.DoctorMedicine;

import java.util.List;

/**
 * @ClassName: DoctorMedicineServie
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/22 19:55
 * @Version: 1.0
 */
public interface DoctorMedicineServie {
    List<DoctorMedicine> getAll();

    DoctorMedicine findOne(Integer id);
}
