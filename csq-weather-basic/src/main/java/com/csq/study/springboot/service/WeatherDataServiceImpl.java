package com.csq.study.springboot.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.csq.study.springboot.vo.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

	
	private final static Logger logger=LoggerFactory.getLogger(WeatherDataServiceImpl.class);
	
	private static final String WEATHER_API="http://wthrcdn.etouch.cn/weather_mini?";
	
	//private final Long TIME_OUT=1800L;
	
	

	@Autowired
	private RestTemplate  restTemplate;
	
	public WeatherResponse getDataByCityId(String cityId) {
		String uri=WEATHER_API + "citykey=" + cityId;
		
		return this.doGetWeather(uri);
	}

	
	public WeatherResponse getDataByCityName(String cityName) {
		String uri=WEATHER_API + "city=" + cityName;
		
		return this.doGetWeather(uri);
	}
	private WeatherResponse doGetWeather(String uri) {
  
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);		
		    String strBody=null;
		    ObjectMapper mapper=new ObjectMapper();
			WeatherResponse weather=null;
			
			if(response.getStatusCodeValue()==200) {
				strBody=response.getBody();
			}
			
		//用json反序列化成我们想要的数据
		try {
			/*
			 * strBody：要解析的参数内容，从respString获取
			 * WeatherResponse.class：要转成的对象类型
			 */
			weather=mapper.readValue(strBody,WeatherResponse.class);
		}catch(IOException e) {
			logger.error("Error!",e);
		}
		
		return weather;
	}

}
