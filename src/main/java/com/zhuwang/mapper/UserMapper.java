package com.zhuwang.mapper;

import com.zhuwang.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    public int addUser(User user);

    public List<User> query();

    public List<Map> queryBj();

    public List<Map> queryKc();

    public Map queryXs(Integer id);

    void BatchAdd(@Param("user") User user);

    void del(Integer id);

    void dels(Integer id);

    List<User> querys();
}
