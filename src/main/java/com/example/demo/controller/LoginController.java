package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.pojo.model.LoginArgs;
import com.example.demo.service.StaffService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Args;
import com.example.demo.utils.JSONResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author
 * @date 2020/9/26
 **/
@Slf4j
@Controller
@Api("LoginController")
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private StaffService staffService;

    @ApiOperation("/登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public JSONResultVo submitLogin(HttpServletRequest request, @RequestBody LoginArgs args) {
        HttpSession session = request.getSession();
        User user = userService.getByUserName(args.getUsername());
        JSONResultVo<Object> jsonResultVo = new JSONResultVo<>();
        if ("用户不存在".equals(userService.submitLogin(user))) {
            jsonResultVo.setInfo("用户不存在");
            return jsonResultVo;
        } else if (!args.getPassword().equals(user.getPassword())) {
            jsonResultVo.setInfo("密码错误");
            return jsonResultVo;
        }
        session.setAttribute("user", user);
        jsonResultVo.setInfo("登录成功");
        return jsonResultVo;
    }

   /* @ApiOperation("/员工登录")
    @PostMapping(value = "/stafflogin")
    @ResponseBody
    public JSONResultVo staffLogin(HttpServletRequest request, @RequestBody LoginArgs args) {
        HttpSession session = request.getSession();
        Staff staff = staffService.getByStaffName(args.getStaffName());
        JSONResultVo<Object> jsonResultVo = new JSONResultVo<>();
        if (staff == null||!args.getPassword().equals(staff.getStaffPassword())) {
            jsonResultVo.setInfo("信息错误");
            return jsonResultVo;
        }
        session.setAttribute("staff", staff);
        jsonResultVo.setInfo("登录成功");
        return jsonResultVo;
    }*/

    @ApiOperation("注册添加用户")
    @PostMapping(value = "/submit")
    @ResponseBody
    public JSONResultVo regist(@RequestBody Args registArgs) {
        userService.registUser(registArgs);
        JSONResultVo jsonResultVo = new JSONResultVo();
        jsonResultVo.setMsg("注册成功");
        return jsonResultVo;
    }

}
