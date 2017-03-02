package com.kkk.yxl.outer.service;

import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.question.pojo.AColumns;
import com.kkk.yxl.question.pojo.AItems;

public interface IOuterService
{
	/**
	 * 将从html中获取到的JSONObject数据存入得到数据库中
	 * 抓取壹心理网站的封装的json;@see YxlHtmlParser.columnList
	 * 并返回对应的JSONObject数据
	 * */
	JSONObject insertBatchItemsService(JSONObject reqJson);
	
	/**
	 * 按栏目将获得的数据保存到数据库中
	 * */
	JSONObject insertAllItemsService(String columId);
	
	/**
	 * 根据栏目将数据拉取得到
	 * 
	 * */
	void spiderDataByColumn(AColumns columns); 
	
	/**
	 * 保存问题和答案
	 * 
	 * */
	void insertBatchItemsService();
	
	/**
	 * 保存单个问题和答案选择项
	 * 
	 * */
	void insertOneItemsService(AItems item);
}
