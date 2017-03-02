package com.kkk.yxl.common.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kkk.yxl.common.util.ConstantFinalUtil;

public class BaseTest
{
	protected ApplicationContext ac;
	protected Map<String, Object> paramMap=new HashMap<String,Object>();
	
	protected Map<String,Object> getParamMap()
	{
		paramMap.clear();
		return paramMap;
	}
	@Before
	public void init()
	{
		ConstantFinalUtil.LoggerMsg.info("----init----");
		this.ac=new ClassPathXmlApplicationContext("spring/*.xml");
	}
	
	@Test
	public void test()
	{
		ConstantFinalUtil.LoggerMsg.info("----test----");
	}
	
	@After
	public void close()
	{
		ConstantFinalUtil.LoggerMsg.info("----close----");
	}
}
