package com.iron.mapper;

import com.iron.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    List<User> getUser();

    User selectById(int id);
}
