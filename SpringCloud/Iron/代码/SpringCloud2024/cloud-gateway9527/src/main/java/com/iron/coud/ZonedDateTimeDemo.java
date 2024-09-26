package com.iron.coud;

import java.time.ZonedDateTime;

/**
 * 创建 ZondateTime 格式代码
 */
public class ZonedDateTimeDemo
{
    public static void main(String[] args)
    {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
              System.out.println(zbj);
    }
}