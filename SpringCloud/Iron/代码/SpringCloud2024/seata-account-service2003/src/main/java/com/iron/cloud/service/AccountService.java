package com.iron.cloud.service;

import org.apache.ibatis.annotations.Param;

public interface AccountService {

    /**
     * @param userId
     * @param money 本次消费金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}
