package com.iron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/geo")
public class GeoController {

    @Autowired
    private GEOService geoService;

    // 添加地理位置
    @GetMapping ("/add")
    public String addGeoLocations() {
        return geoService.geoAdd();
    }

    // 根据名称获取地理位置的经纬度
    @GetMapping("/position/{city}")
    public Point getPosition(@PathVariable String city) {
        return geoService.getPosition(city);
    }

    // 获取指定成员的geohash
    @GetMapping("/hash/{member}")
    public String getGeoHash(@PathVariable String member) {
        return geoService.hash(member);
    }

    // 获取两个地点之间的距离
    @GetMapping("/distance")
    public double getDistance(@RequestParam String p1, @RequestParam String p2) {
        return geoService.getDistance(p1, p2).getValue();
    }

    // 根据经纬度查找附近的地点
    @GetMapping("/radiusByXY")
    public GeoResults<RedisGeoCommands.GeoLocation<String>> findNearbyByXY() {
        return geoService.radiusByXY();
    }

    // 根据名称查找附近的地点
    @GetMapping("/radiusByName")
    public GeoResults<RedisGeoCommands.GeoLocation<String>> findNearbyByName() {
        return geoService.radiusByName();
    }
}