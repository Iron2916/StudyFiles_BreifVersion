package com.iron.GuvaBloomFilterService;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.stereotype.Service;

@Service
public class GuavaBloomFIlterService {
    public void guavaBloomFilter() {

        System.out.println("进入了Service");
        // 插入一百万数据
        int size = 1000000;

        // false positive probability：出错概率，越小占用 二进制数组占用内存越大
        double fpp = 0.03;

        final BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);


        for (int i=0; i<size; i++) {

            filter.put(i);
        }

        for (int i=size+1; i< size+100000; i++) {

            if (filter.mightContain(i)) {
                // 误判
                System.out.println("---------------------误判了！---------------------------");
            }
        }
    }
}
