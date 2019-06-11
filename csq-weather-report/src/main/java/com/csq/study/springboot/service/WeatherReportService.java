package com.csq.study.springboot.service;

import com.csq.study.springboot.vo.Weather;

public interface  WeatherReportService {

	//根据城市id查询天气信息
	Weather getDataByCityId(String cityId);
	
}
