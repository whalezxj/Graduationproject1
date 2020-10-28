package com.example.demo.service;

import com.example.demo.enums.leaveStatus;
import com.example.demo.mapper.LeaveMapper;
import com.example.demo.pojo.Leave;
import com.example.demo.pojo.model.OperationArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author
 * @date 2020/10/9
 **/
@Service
public class LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;

    public String addLeave(Leave leave) {
        leave.setLeaveId(String.valueOf(new Random().nextInt(999999)));
        leave.setCreatetime(new Timestamp(new Date().getTime()));
        leaveMapper.addLeave(leave);
        return "提交成功";
    }

    public List<Leave> queryLeave() {
        List<Leave> list = leaveMapper.queryLeave();
        list.forEach(item->{
            item.setLeaveStatus(leaveStatus.getByValue(item.getLeaveStatus()).getName());
        });
        return list;
    }

    public String operationLeave(OperationArgs args) {
        leaveMapper.operationLeave(args);
        return "操作成功";
    }

    public List<Leave> getLeave(String staffId) {
        List<Leave> list = leaveMapper.getLeave(staffId);
        return list;
    }
}
