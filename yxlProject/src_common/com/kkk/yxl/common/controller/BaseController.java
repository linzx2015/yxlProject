package com.kkk.yxl.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kkk.yxl.common.util.PageInfoUtil;

public class BaseController
{
	private Map<String,Object> paramMap=new HashMap<String,Object>();
	protected PageInfoUtil pageInfoUtil;
	protected Map<String,Object> getParamMap()
	{
		paramMap.clear();
		return paramMap;
	}
	
	protected PageInfoUtil getPageInfoUtil(HttpServletRequest req)
	{
		this.pageInfoUtil=new PageInfoUtil();
		//实现分页效果
		String pageStr=req.getParameter("page");
		try
		{
			this.pageInfoUtil.setCurrentPage(Integer.valueOf(pageStr));
		} catch (NumberFormatException e)
		{
			//避免前台乱提交数据,不做转换类型的处理,正常提交当前页才能进行查询
		}
		return this.pageInfoUtil;
	}
}	
