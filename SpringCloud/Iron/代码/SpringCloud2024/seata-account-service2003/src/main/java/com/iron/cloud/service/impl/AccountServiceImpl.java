package com.iron.cloud.service.impl;

import com.iron.cloud.mapper.AccountMapper;
import com.iron.cloud.pojo.Account;
import com.iron.cloud.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService
{
    @Resource
    AccountMapper accountMapper;

    /**
     * 扣减账户余额
     */
    @Override
    public void decrease(Long userId, Long money) {
        log.info("------->account-service中扣减账户余额开始");

        // 通过 userId 查询到 account
        final Example example = new Example(Account.class);
        final Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);

        // 更新
        final Account account = accountMapper.selectOneByExample(example);
        account.setResidue(account.getResidue()-money);
        account.setUsed(account.getUsed() + money);

        myTimeOut();
        //int age = 10/0;
        accountMapper.updateByExampleSelective(account, example);

        log.info("------->account-service中扣减账户余额结束");
    }

    /**
     * 模拟超时异常，全局事务回滚
     */
    private static void myTimeOut()
    {
        try { TimeUnit.SECONDS.sleep(65); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}