package com.iron.service;

import com.iron.pojo.User;

import java.util.List;

public interface TestService
{
    List<User> getUser();

    User selectById(int id);
}
