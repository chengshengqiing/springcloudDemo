package com.csq.study.springboot.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

//声明为xml的根元素
@XmlRootElement(name = "d")
//声明xml的访问类型为FIELD（字段）
@XmlAccessorType(XmlAccessType.FIELD)
public class City{	
	//声明为xml的属性
		@XmlAttribute(name = "d1")
	    private String cityId;
		//声明为xml的属性
		@XmlAttribute(name = "d2")
	    private String cityName;
		//声明为xml的属性
		@XmlAttribute(name = "d3")
	    private String cityCode;
		//声明为xml的属性
		@XmlAttribute(name = "d4")
	    private String province;
	
		public String getCityId() {
			return cityId;
		}


		public void setCityId(String cityId) {
			this.cityId = cityId;
		}


		public String getCityName() {
			return cityName;
		}


		public void setCityName(String cityName) {
			this.cityName = cityName;
		}


		public String getCityCode() {
			return cityCode;
		}


		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}


		public String getProvince() {
			return province;
		}


		public void setProvince(String province) {
			this.province = province;
		}


		@Override
		public String toString() {
			return "City [cityId=" + cityId + ", cityName=" + cityName + ", cityCode=" + cityCode + ", province="
					+ province + "]";
		}
	
	    
}
