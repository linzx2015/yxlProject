package com.kkk.yxl.outer.test;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.common.test.BaseTest;
import com.kkk.yxl.common.util.ConstantFinalUtil;
import com.kkk.yxl.outer.service.IOuterService;
import com.kkk.yxl.outer.util.YxlHtmlParser;
import com.kkk.yxl.question.pojo.AAnswer;
import com.kkk.yxl.question.pojo.AColumns;
import com.kkk.yxl.question.pojo.AItems;
import com.kkk.yxl.question.pojo.AQuestion;
import com.kkk.yxl.question.service.IQuestionPackService;

public class OutServiceTest extends BaseTest
{
	private IOuterService outerService;
	private IQuestionPackService<AItems> itemsService;
	private IQuestionPackService<AColumns> columnsService;
	private IQuestionPackService<AQuestion> questionService;
	private IQuestionPackService<AAnswer> answerService;
	
	@Before
	public void init()
	{
		super.init();
		this.outerService=(IOuterService) this.ac.getBean("outerService");
		this.itemsService=(IQuestionPackService<AItems>) this.ac.getBean("itemsService");
	}
	
	//拉取栏目和栏目下的项,保存到数据库中
	@Test
	public void insertBatchTest()
	{
		/////
		YxlHtmlParser yhp=new YxlHtmlParser();
		JSONObject reqJson=yhp.columnList("amor",1);
		this.outerService.insertBatchItemsService(reqJson);
		ConstantFinalUtil.LoggerMsg.info("--------------------");
	}
	
	//拉取所有栏目,并保存到数据库中
	//要先有栏目,因为关联了item表,必须有栏目id才能一一对应保存item表的数据
	@Test
	public void insertAllItemsServiceTest()
	{
		String columnId="";
		this.outerService.insertAllItemsService(columnId);
		ConstantFinalUtil.LoggerMsg.info("-----insertAllItemsServiceTest-----");
	}
	
	@Test
	public void insertOneItemServiceTest()
	{
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("id", 2);
		AItems item=this.itemsService.queryObjService(paramMap);
		//保存一个测试项
		this.outerService.insertOneItemsService(item);
	}
	
	@Test
	public void spiderYxlTest()
	{
		//?????存在answer和question表数据保存不了的问题,但是log上有sql已经发出
		//需要数据全部拉取完成了才能完成提交,因为spring将拉取的整个过程看做一个事务
		this.outerService.insertBatchItemsService();
		ConstantFinalUtil.LoggerMsg.info("----------");
	}
}
