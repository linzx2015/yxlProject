package com.kkk.yxl.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 常用的静态常量:方便后续维护
 * 
 */
public class ConstantFinalUtil
{
	// log4j日志常量
	public static final Logger LoggerMsg = LogManager.getLogger(ConstantFinalUtil.class);

	public static JSONObject getJson()
	{
		JSONObject resultJson = new JSONObject();
		return resultJson;
	}

	// http连接超时时间
	public static final int CONNECT_TIMEOUT = 5000;
	// http读取信息超时时间
	public static final int READ_TIMEOUT = 5000;
	// 壹心理网站url
	public static final String YXL_URL = "http://www.xinli001.com/ceshi/";
}
