package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.enums.staffStatus;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.mapper.StaffMapper;
import com.example.demo.pojo.BaseModel;
import com.example.demo.pojo.Department;
import com.example.demo.pojo.Staff;
import com.example.demo.pojo.model.StaffArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class StaffService extends ServiceImpl<DepartmentMapper, Department> {

    @Autowired
    private StaffMapper staffMapper;

    public List<BaseModel> queryStaff() {
        List<BaseModel> list = staffMapper.queryBase();
        list.forEach(item -> {
            item.setStaffStatus(staffStatus.getByValue(item.getStaffStatus()).getName());
        });
        return list;
    }


    public List<BaseModel> selectByStaffName(String staffName) {
        List<BaseModel> list = staffMapper.selectByname(staffName);
        list.forEach(item -> {
            item.setStaffStatus(staffStatus.getByValue(item.getStaffStatus()).getName());
        });
        return list;
    }

    @Transactional
    public Integer addStaff(StaffArgs args) {
        args.setStaffId(String.valueOf(new Random().nextInt(99999)));
        Integer insert = staffMapper.addStaff(args);
        return insert;
    }

    @Transactional
    public Integer deleteStaff(String id) {
        Integer integer = staffMapper.deleteStaffById(id);
        return integer;
    }

    @Transactional
    public Integer activiteStaff(String id) {
        Integer integer = staffMapper.activiteStaffById(id);
        return integer;
    }


    public Staff getByStaffId(String staffId) {
        Staff staff = staffMapper.getByStaffId(staffId);
        return staff;
    }
}
