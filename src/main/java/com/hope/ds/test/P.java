package com.hope.ds.test;

import tech.spiro.addrparser.common.RegionInfo;
import tech.spiro.addrparser.io.RegionDataInput;
import tech.spiro.addrparser.io.file.JSONFileRegionDataInput;
import tech.spiro.addrparser.parser.Location;
import tech.spiro.addrparser.parser.LocationParserEngine;
import tech.spiro.addrparser.parser.ParserEngineException;

/**
 * @author dongchao
 * @description: TODO
 * @date 2021/8/14 10:42 下午
 */
public class P {

    public static void main(String[] args) throws ParserEngineException {
//        int i = 1500;
//        int t = 3000;
//
//        double r = i/(double)t;
//        System.out.printf("result : "+r);

        // china-region.json文件作为基础数据
        RegionDataInput regionDataInput = new JSONFileRegionDataInput("/Users/dongchao/Downloads/china-region.json");

        // 创建并初始化位置解析引擎，一般配置为全局单例
        LocationParserEngine engine = new LocationParserEngine(regionDataInput);
        // 初始化，加载数据，比较耗时
        engine.init();

        // 执行解析操作
//        Location location = engine.parse(118.750934,32.038634);

        Location location = engine.parse(120.146191,30.27395);

        // 获取省市区信息
        RegionInfo provInfo = location.getProv();
        RegionInfo cityInfo = location.getCity();
        RegionInfo districtInfo = location.getDistrict();

        System.out.printf("");
    }
}
