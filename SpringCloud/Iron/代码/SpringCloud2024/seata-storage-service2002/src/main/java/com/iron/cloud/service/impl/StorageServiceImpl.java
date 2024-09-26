package com.iron.cloud.service.impl;

import com.iron.cloud.mapper.StorageMapper;
import com.iron.cloud.pojo.Storage;
import com.iron.cloud.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService
{

    @Resource
    private StorageMapper storageMapper;

    /**
     * 扣减库存
     */
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------->storage-service中扣减库存开始");

        System.out.println("productId = " + productId + ", count = " + count);
        // 根据productId进行查询库存货物
        final Example example = new Example(Storage.class);
        final Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);

        final Storage storage = storageMapper.selectOneByExample(example);
        System.out.println(storage);

        //  更新库存
        storage.setResidue(storage.getResidue() - count);
        storage.setUsed(storage.getUsed() + count);
        System.out.println(storage);

        // 更新数据库
        storageMapper.updateByPrimaryKey(storage);
        log.info("------->storage-service中扣减库存结束");
    }
}