package com.kkk.yxl.common.util;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.kkk.yxl.outer.service.IOuterService;

/**
 * 由spring来进行任务调度
 * 定时 执行任务
 * */
@Component("myJobUtil")
public class MyJobUtil
{
	@Resource
	private IOuterService outerService;
	
	public void testOne()
	{
		System.out.println("====testOne========" + new Date().toLocaleString());
		/*try
		{
			Thread.sleep(6000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}*/
	}
	
	public void testTwo()
	{
		System.out.println("====testTwo========" + new Date().toLocaleString());
	}
	
	//具体的执行任务
	public void spiderYxlJob()
	{
		ConstantFinalUtil.LoggerMsg.info("-----抓取壹心理网站内容--开始-----");
		//不传参数代表抓取全部内容
		this.outerService.insertAllItemsService("");
		ConstantFinalUtil.LoggerMsg.info("-----抓取壹心理网站内容--结束-----");
	}
}
