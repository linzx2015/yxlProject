package com.kkk.yxl.outer.test;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.common.util.ConstantFinalUtil;
import com.kkk.yxl.outer.util.HttpSpiderUtil;
import com.kkk.yxl.outer.util.YxlHtmlParser;

public class HttpSpiderTest
{
	
	@Test
	public void testHtml()
	{
		HttpSpiderUtil httpUtil = new HttpSpiderUtil();
		
		String urlStr = "http://www.xinli001.com" ;
		/* 请求头 */
		Map<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		
		/* 参数 */
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("id", "10");
		paramsMap.put("name", "test");
		String response = httpUtil.infoFromUrlByGet(urlStr,headerMap,paramsMap);
		System.out.println(response);
	}
	
	//测试获取栏目信息
	@Test
	public void testYxlComlun()
	{
		YxlHtmlParser yhp=new YxlHtmlParser();
		JSONObject resultJson=yhp.columnList("amor",1);
		ConstantFinalUtil.LoggerMsg.info("--{}-",resultJson);
	}
	
}
