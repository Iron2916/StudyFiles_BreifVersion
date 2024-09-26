package com.iron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisCommands;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisCommand;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GEOService {

    @Autowired
    RedisTemplate redisTemplate;

    // 添加
    public String geoAdd() {

        Map<String, Point> map = new HashMap<>();
        map.put("天安门",new Point(116.403963,39.915119));
        map.put("故宫",new Point(116.403414 ,39.924091));
        map.put("长城" ,new Point(116.024067,40.362639));
        redisTemplate.opsForGeo().add("city", map);

        return map.toString();
    }

    // 获得某个点的经纬度
    public Point getPosition(String member) {

        final List<Point> list = redisTemplate.opsForGeo().position("city", member);
        System.out.println(list.get(0));
        return list.get(0);
    }

    // 获得base32的编码值
    public String hash(String member) {
        //geohash算法生成的base32编码值
        List<String> list= this.redisTemplate.opsForGeo().hash("city",member);
        System.out.println(list.get(0));
        return list.get(0);
    }

    // 获取两个city之间的距离
    public Distance getDistance(String p1, String p2) {

        final Distance distance = redisTemplate.opsForGeo().distance("city", p1, p2, RedisGeoCommands.DistanceUnit.KILOMETERS);
        return distance;
    }

    // 通过经纬度查找附近距离的地点
    public GeoResults radiusByXY() {

        Circle circle = new Circle(116.418017, 39.914402, 10000);        // 十公里范围内
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending()
                .limit(50);
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = this.redisTemplate.opsForGeo()
                .radius("city", circle, args);
        return geoResults;
    }

    public GeoResults radiusByName() {

        String name = "天安门";
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending()
                .limit(50);
        final Distance distance = new Distance(10, Metrics.KILOMETERS);
        final GeoResults geoResults = redisTemplate.opsForGeo().radius("city", name, distance, args);

        return geoResults;
    }
}
