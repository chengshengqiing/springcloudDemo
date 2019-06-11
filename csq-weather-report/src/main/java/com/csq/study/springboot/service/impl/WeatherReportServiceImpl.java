package com.csq.study.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csq.study.springboot.service.WeatherDataService;
import com.csq.study.springboot.service.WeatherReportService;
import com.csq.study.springboot.vo.Weather;
import com.csq.study.springboot.vo.WeatherResponse;

@Service
public class WeatherReportServiceImpl implements WeatherReportService{

	@Autowired
	private WeatherDataService weatherDataService;
	@Override
	public Weather getDataByCityId(String cityId) {
		WeatherResponse response = weatherDataService.getDataByCityId(cityId);
				return response.getData();

	}

}
