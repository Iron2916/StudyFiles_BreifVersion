package com.iron.cloud.service.Impl;

import com.iron.cloud.mapper.PayMapper;
import com.iron.cloud.pojo.Pay;
import com.iron.cloud.service.PayService;
import jakarta.annotation.Resource;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayserviceImpl implements PayService {

    @Resource
    PayMapper payMapper;

    @Override
    public int add(Pay pay) {

        final val result = payMapper.insert(pay);
        return result;
    }

    @Override
    public int delete(Integer id) {

        final int result = payMapper.deleteByPrimaryKey(id);
        return result;
    }

    @Override
    public int update(Pay pay) {

        final int result = payMapper.updateByPrimaryKey(pay);
        return result;
    }

    @Override
    public Pay getById(Integer id) {

        final Pay result = payMapper.selectByPrimaryKey(id);
        return result;
    }

    @Override
    public List<Pay> getAll() {

        final List<Pay> payList = payMapper.selectAll();
        return payList;
    }
}
