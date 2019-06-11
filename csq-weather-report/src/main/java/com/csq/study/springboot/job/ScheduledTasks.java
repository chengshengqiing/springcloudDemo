package com.csq.study.springboot.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.csq.study.springboot.service.CityDataService;
import com.csq.study.springboot.service.WeatherDataService;
import com.csq.study.springboot.vo.City;

@Component
public class ScheduledTasks {

	  private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	  private static final SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 
	  @Autowired
	  private CityDataService cityDataService;
	  @Autowired
	  private WeatherDataService watherDataService;
	    @Scheduled(fixedRate = 30000)
	    public void scheduledDemo(){
	        logger.info("天气数据同步任务 开始  every 5 seconds:{}", formate.format(new Date()) );
	        
	        List<City> cityList=null;
	   
	        try {
				
	        	cityList=cityDataService.listCity();
	        	
			} catch (Exception e) {
				logger.info("获取异常",e);
			}
	        
	       for (City city : cityList) {
	    	   String cityId = city.getCityId();
	    	   logger.info("天气任务同步中,cityId:"+cityId);
	    	   //根据城市id获取天气
	    	   watherDataService.syncDataByCityId(cityId);
		}
	       logger.info("天气任务同步End");
	    }
}
