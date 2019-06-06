package com.csq.study.springboot.util;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class XmlBuilder {
	//将xml转为指定的POJO
		public static Object xmlStrToObject(Class<?> clazz,String xmlStr) throws Exception{

			Object xmlObject=null;
			Reader reader=null;
			JAXBContext context=JAXBContext.newInstance(clazz);

			//xml转为对象的接口
			Unmarshaller unmarshaller=context.createUnmarshaller();

			reader=new StringReader(xmlStr);
			xmlObject=unmarshaller.unmarshal(reader);

			if(reader!=null){
				reader.close();
			}

			return xmlObject;
		}

}
