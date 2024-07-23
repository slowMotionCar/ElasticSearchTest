package com.example.elasticsearchtest.demos.web.service.impl;

import com.example.elasticsearchtest.demos.web.mapper.DoctorMedicineMapper;
import com.example.elasticsearchtest.demos.web.pojo.DoctorMedicine;
import com.example.elasticsearchtest.demos.web.service.DoctorMedicineServie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: DoctorMedicineServiceImpl
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/22 19:55
 * @Version: 1.0
 */
@Service
public class DoctorMedicineServiceImpl implements DoctorMedicineServie {

    @Resource
    DoctorMedicineMapper doctorMedicineMapper;

    @Override
    public List<DoctorMedicine> getAll() {

        List<DoctorMedicine> all = doctorMedicineMapper.getAll();
        return all;

    }

    @Override
    public DoctorMedicine findOne(Integer id) {

       DoctorMedicine one = doctorMedicineMapper.getOne(id);
        return one;

    }


}
