package com.zhuwang.controller;

import com.zhuwang.dao.User;
import com.zhuwang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("toLoginPage")
    public String toLoginPage(Model model){
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        return "login";
    }

    @GetMapping("/index")
    public String userList(Map<String, List> result) {
        List<User> Users=userMapper.querys();
        result.put("users",Users);
        return "index";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, HttpServletResponse servletResponse) throws IOException {
        userMapper.del(id);
        return "redirect:/index";
    }

    //新增数据
    @GetMapping("/add")
    public String save() {
        return "add";
    }

    @PostMapping("/add")
    public String addone(User user) {
        userMapper.addUser(user);
        return "redirect:/index";
    }




}
