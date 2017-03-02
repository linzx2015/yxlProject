package com.kkk.yxl.common.util;

import java.util.concurrent.Callable;

import com.kkk.yxl.outer.service.IOuterService;
import com.kkk.yxl.question.pojo.AColumns;

/**
 * 多线程工具类：java1.6之后使用concurrent 
 * 优化原本Thread和Runnable的缺点
 * Callable==Runnable接口; Callable中的泛型必须和实现的Call方法返回值一致
 * 如何为多线程的属性赋值:set方法;在当前的多线程类中和spring没有关系
 * 
 * */

public class MultiThreadUtil implements Callable<Integer>
{
	private IOuterService outerService;
	private AColumns columns;
	
	@Override
	public Integer call() throws Exception
	{
		ConstantFinalUtil.LoggerMsg.info("--执行的线程栏目为{}-call--",columns.getName());
		//拉取所有对应的栏目,并一一打印
		this.outerService.spiderDataByColumn(columns);
		return null;
	}
	
	public void setOuterService(IOuterService outerService)
	{
		this.outerService = outerService;
	}
	public void setColumns(AColumns columns)
	{
		this.columns = columns;
	}
	
}
