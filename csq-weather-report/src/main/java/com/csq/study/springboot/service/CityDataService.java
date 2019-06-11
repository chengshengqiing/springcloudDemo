package com.csq.study.springboot.service;

import java.util.List;

import com.csq.study.springboot.vo.City;

public interface CityDataService {
	
	/**
	 * 
	 * @Title: listCity 
	 * @Description: 获取城市列表 
	 * @return
	 * @throws Exception
	 * @author chengshengqing-YinHe  2019年6月5日 下午5:23:39
	 */
	List<City> listCity() throws Exception;
}
