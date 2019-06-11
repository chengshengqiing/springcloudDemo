package com.csq.study.springboot.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.csq.study.springboot.vo.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class WeatherDataServiceImpl implements WeatherDataService {

	
	private final static Logger logger=LoggerFactory.getLogger(WeatherDataServiceImpl.class);
	
	private static final String WEATHER_API="http://wthrcdn.etouch.cn/weather_mini?";
	
	private final Long TIME_OUT=30L;

	@Autowired
	private RestTemplate  restTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	
	public WeatherResponse getDataByCityId(String cityId) {
		String uri=WEATHER_API + "citykey=" + cityId;
		
		return this.doGetWeather(uri);
	}

	
	public WeatherResponse getDataByCityName(String cityName) {
		String uri=WEATHER_API + "city=" + cityName;
		
		return this.doGetWeather(uri);
	}
	private WeatherResponse doGetWeather(String uri) {
  
		ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
		    String key=uri;//将调用的URI作为key
		    String strBody=null;
		    ObjectMapper mapper=new ObjectMapper();
			WeatherResponse weather=null;
			//先查缓存，缓存没有在查服务
			if(!this.stringRedisTemplate.hasKey(key)) {
				logger.info("没有找到key");
				ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);	
			
			if(response.getStatusCodeValue()==200) {
				strBody=response.getBody();
			}
			ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
			}else {
				logger.info("找到key"+key+",value="+ops.get(key));
				strBody=ops.get(key);
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
