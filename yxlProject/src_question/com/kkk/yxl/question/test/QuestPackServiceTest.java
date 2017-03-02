package com.kkk.yxl.question.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.common.service.IUserService;
import com.kkk.yxl.common.test.BaseTest;
import com.kkk.yxl.common.util.ConstantFinalUtil;
import com.kkk.yxl.common.util.PageInfoUtil;
import com.kkk.yxl.question.pojo.AAnswer;
import com.kkk.yxl.question.pojo.AColumns;

public class QuestPackServiceTest extends BaseTest
{
	//不能使用IUserPackService？？？,因为有多个子集成？
	private IUserService<AColumns> columnsService;
	private IUserService<AAnswer> answerService;
	
	@Before
	public void init()
	{
		super.init();
		this.columnsService=(IUserService<AColumns>) this.ac.getBean("columnsService");
		this.answerService=(IUserService<AAnswer>) this.ac.getBean("answerService");
	}
	
	@Test
	public void queryOneObjtest()
	{
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("id", 3);
		AColumns columns=this.columnsService.queryObjService(paramMap);
		ConstantFinalUtil.LoggerMsg.info("--queryOneObjtest-{}-",columns.getName());
	}
	
	@Test
	public void insertOneTest()
	{
		//注意postgresql对数据类型要求比较高
		AColumns columns=new AColumns();
		columns.setName("22");
		columns.setStatus(Byte.valueOf("1"));
		columns.setUrl("kkkk");
		columns.setContent("qqqq");
		columns.setFlag(Byte.valueOf("1"));
		columns.setCreateTime(new Date());
		columns.setUpdateTime(new Date());
		columns.setPubTime(new Date());
		JSONObject resultJson=this.columnsService.insertObjService(columns);
		ConstantFinalUtil.LoggerMsg.info("--insertOneTest-{}-",resultJson);
	}
	
	@Test
	public void updateOneTest()
	{
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("id", 9);
		AColumns columns=this.columnsService.queryObjService(paramMap);
		columns.setName("kkkkkkkkkkk");
		JSONObject resultJson=this.columnsService.updateObjService(columns);
		ConstantFinalUtil.LoggerMsg.info("--{}--",resultJson);
	}
	
	@Test
	public void queryMutliAdminTest()
	{
		/* 查询条件 */
		Map<String, Object> paramMap = this.getParamMap();
		paramMap.put("keyword", "测");
		/* 分页 */
		PageInfoUtil pageInfoUtil = new PageInfoUtil();
		/*List<Acolumns> columnsList = this.userService.queryMultiObjService(pageInfoUtil, paramMap);*/
		List<AColumns> columnsList = this.columnsService.queryMultiObjService(null, paramMap);
		for (Iterator iterator = columnsList.iterator(); iterator.hasNext();)
		{
			AColumns columns = (AColumns) iterator.next();
			ConstantFinalUtil.LoggerMsg.info("id:{},email:{}",columns.getId() ,columns.getName());
		}
		
		ConstantFinalUtil.LoggerMsg.info("分页对象:{}",pageInfoUtil);
	}
	@Test
	public void query()
	{
		Map<String, Object> paramMap = this.getParamMap();
		paramMap.put("id", 1);
		answerService.queryObjService(paramMap);
	}
	@Test
	public void insert()
	{
		/*AAnswer answer=new AAnswer();
		answer.setName("hui");
		answer.setOrderNum(Integer.valueOf("1"));
		answer.setOutId(Integer.valueOf("1"));
		
		answer.setCreateTime(new Date());
		answer.setUpdateTime(new Date());
		answer.setPubTime(new Date());
		this.answerService.insertObjService(answer);*/
	}
}
