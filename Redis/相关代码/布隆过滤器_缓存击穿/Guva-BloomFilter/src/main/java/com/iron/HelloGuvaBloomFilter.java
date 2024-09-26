package com.iron;


import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class HelloGuvaBloomFilter {


    public static void main(String[] args) {

        // 创建 Guva BloomFilter 底层使用的是 二进制数组 + 多个Hash函数
        final BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 100);

        // 判断是否存在
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));

        // 加入元素
        filter.put(1);
        System.out.println(filter.mightContain(1));
    }
}
