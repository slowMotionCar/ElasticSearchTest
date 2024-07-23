package com.example.elasticsearchtest.demos.web.controller;

import com.example.elasticsearchtest.demos.web.pojo.DoctorMedicine;
import com.example.elasticsearchtest.demos.web.service.DoctorMedicineServie;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName: DoctorMedicineController
 * @Description: TODO
 * @Author: zzl
 * @Date: 2024/7/22 19:56
 * @Version: 1.0
 */
@RestController
public class DoctorMedicineController {

    @Resource
    DoctorMedicineServie doctorMedicineServie;

    @GetMapping("findAllDoctorMedicine")
    public void findAll() {
        List<DoctorMedicine> all =  doctorMedicineServie.getAll();
        System.out.println(all);
        return;
    }

    @GetMapping("findOneDoctorMedicine")
    public DoctorMedicine findOne(@RequestParam Integer id) {
        DoctorMedicine one =  doctorMedicineServie.findOne(id);
        System.out.println(one);
        return one;
    }


}
