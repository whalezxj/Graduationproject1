package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.pojo.Leave;
import com.example.demo.pojo.Salary;
import com.example.demo.pojo.model.OperationArgs;
import com.example.demo.service.LeaveService;
import com.example.demo.service.SalaryService;
import com.example.demo.utils.JSONResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2020/10/9
 **/

@RestController
@Slf4j
@Api("我的")
@RequestMapping("/my")
public class MyController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private SalaryService salaryService;

    @ApiOperation("薪资")
    @PostMapping(value="/salary" )
    public JSONResultVo getSalary(@RequestParam  String staffId){
        Salary salary = salaryService.getSalary(staffId);
        JSONResultVo jsonResultVo = new JSONResultVo();
        jsonResultVo.setInfo(salary);
        return jsonResultVo;
    }

    @ApiOperation("薪资管理")
    @PostMapping(value="/setsalary" )
    public JSONResultVo setSalary(@RequestBody Salary salary){
        salaryService.setSalary(salary);
        JSONResultVo jsonResultVo = new JSONResultVo();
        jsonResultVo.setInfo(salary);
        return jsonResultVo;
    }

    @ApiOperation("薪资列表")
    @PostMapping(value="/salarylist")
    public JSONResultVo<IPage<Salary>> querySalary(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<Salary> lists = salaryService.querySalary();
        PageInfo page = new PageInfo(lists, 5);
        Map<String, Object> map = new HashMap<>();
        map.put("pageInfo", page);
        JSONResultVo<Object> jsonResultVo = new JSONResultVo<>();
        return jsonResultVo.setData(map);
    }

    @ApiOperation("请假")
    @PostMapping(value="/addleave")
    public JSONResultVo addLeave(@RequestBody  Leave leave){
        String result = leaveService.addLeave(leave);
        return JSONResultVo.setData(result);
    }

    @ApiOperation("获取当前用户请假列表")
    @PostMapping(value="/getleave")
    public JSONResultVo<IPage<Leave>> getLeave(@RequestParam(value = "pn", defaultValue = "1") Integer pn , @RequestParam(value = "staffId") String staffId) {
        PageHelper.startPage(pn, 10);
        List<Leave> lists = leaveService.getLeave(staffId);
        PageInfo page = new PageInfo(lists, 5);
        Map<String, Object> map = new HashMap<>();
        map.put("pageInfo", page);
        JSONResultVo<Object> jsonResultVo = new JSONResultVo<>();
        return jsonResultVo.setData(map);
    }

    @ApiOperation("请假列表")
    @PostMapping(value="/leavelist")
    public JSONResultVo<IPage<Leave>> queryLeave(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<Leave> lists = leaveService.queryLeave();
        PageInfo page = new PageInfo(lists, 5);
        Map<String, Object> map = new HashMap<>();
        map.put("pageInfo", page);
        JSONResultVo<Object> jsonResultVo = new JSONResultVo<>();
        return jsonResultVo.setData(map);
    }

    @ApiOperation("审核请假")
    @PostMapping(value="/operationleave")
    public String operationLeave(@RequestBody OperationArgs args){
        return leaveService.operationLeave(args);
    }

}
