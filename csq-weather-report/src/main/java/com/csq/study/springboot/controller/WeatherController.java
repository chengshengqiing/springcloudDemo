package com.csq.study.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.csq.study.springboot.service.CityDataService;
import com.csq.study.springboot.service.WeatherReportService;

@RestController
@RequestMapping("/report")
public class WeatherController {

	@Autowired
	private WeatherReportService WeatherReportService;
	@Autowired
	private CityDataService  cityDataService;
	
	@GetMapping("/cityId/{cityId}")
	public ModelAndView getReportByCityId(@PathVariable("cityId") String  cityId,Model model)throws Exception {
		
		model.addAttribute("title", "天气预报测试");
		model.addAttribute("cityId", cityId);
		model.addAttribute("cityList", cityDataService.listCity());
		model.addAttribute("report", WeatherReportService.getDataByCityId(cityId));
		
		return new ModelAndView("report","reportModel",model);
	}
	
	}
	