package com.zhuwang.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zhuwang.dao.User;
import com.zhuwang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class TestController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("test/{id}")
    public String test(@PathVariable("id")Integer id){
        Map xs = userMapper.queryXs(id);
        List<Map> kc = userMapper.queryKc();
        List<Map> bj = userMapper.queryBj();
        JSONObject object = new JSONObject();
        object.put("name",xs.get("name"));
        object.put("opinion",xs.get("num"));
        object.put("gender",bj);
        object.put("skills",kc);
        return object.toJSONString();
    }

    @RequestMapping("user/add")
    public String add(@RequestBody User user){
        userMapper.addUser(user);
        userMapper.BatchAdd(user);
        return "1";
    }

    @RequestMapping("test/user/query")
    public List <User> query(User user){
        return userMapper.query();
    }

    @RequestMapping("test/user/del")
    public String dels(Integer id){
        userMapper.del(id);
        System.out.println(id);
        userMapper.dels(id);
        return "1";
    }

    @RequestMapping("/uploadFile")
    public String uploadFile(MultipartFile file,User user){
        System.out.println(user);
        String fileName = file.getOriginalFilename();
        String newFileName = System.currentTimeMillis()+ fileName.substring(fileName.lastIndexOf("."));
        String dirPath = "E:/file/";
        File filePath = new File(dirPath);
        if (!filePath.exists()){
            filePath.mkdirs();
        }
        try {
            file.transferTo(new File(dirPath+newFileName));
            user.setFileName(fileName);
            user.setNfileName(newFileName);
            user.setFileUrl("/statics/"+newFileName);
            userMapper.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "1";
    }

    @RequestMapping("query")
    public ModelAndView query(Model model){
        List<User> list = userMapper.querys();
        model.addAttribute("userList",list);
        return new ModelAndView("user/userList");
    }



    /*@Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("testRedis")
    public String testRedis(){
        String name = redisTemplate.opsForValue().get("name");
        return name;
    }*/

    /*@GetMapping("toLoginPage")
    public String toLoginPage(Model model){
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        return "login";
    }*/


}
