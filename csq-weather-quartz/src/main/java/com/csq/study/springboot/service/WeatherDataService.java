package com.csq.study.springboot.service;

import com.csq.study.springboot.vo.WeatherResponse;

public interface WeatherDataService {
	    //根据城市ID查询天气数据
		WeatherResponse getDataByCityId(String cityId);
		
		//根据城市名称查询天气数据
		WeatherResponse getDataByCityName(String cityName);
		
		//根据城市ID同步天气数据
		void syncDataByCityId(String cityId);
}
