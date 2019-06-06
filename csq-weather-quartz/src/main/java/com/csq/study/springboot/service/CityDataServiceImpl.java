package com.csq.study.springboot.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.csq.study.springboot.util.XmlBuilder;
import com.csq.study.springboot.vo.City;
import com.csq.study.springboot.vo.CityList;
@Service
public class CityDataServiceImpl implements CityDataService{

	@Override
	public List<City> listCity() throws Exception {
		//读取xml文件
				Resource resource=new ClassPathResource("cityList.xml");
				BufferedReader br=new BufferedReader(new InputStreamReader(resource.getInputStream(),"utf-8"));
				StringBuffer buffer=new StringBuffer();
				String line="";

				while((line=br.readLine())!=null){
					buffer.append(line);
				}

				br.close();

				//xml转为Java对象
				CityList cityList= (CityList) XmlBuilder.xmlStrToObject(CityList.class, buffer.toString());

				return cityList.getCityList();
			

	}

}
