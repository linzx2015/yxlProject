package com.kkk.yxl.outer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.common.util.ADataEnumUtil;
import com.kkk.yxl.common.util.ConstantFinalUtil;
import com.kkk.yxl.common.util.MultiThreadUtil;
import com.kkk.yxl.common.util.PageInfoUtil;
import com.kkk.yxl.outer.service.IOuterService;
import com.kkk.yxl.outer.util.YxlHtmlParser;
import com.kkk.yxl.question.pojo.AAnswer;
import com.kkk.yxl.question.pojo.AColumns;
import com.kkk.yxl.question.pojo.AItems;
import com.kkk.yxl.question.pojo.AQuestion;
import com.kkk.yxl.question.service.IQuestionPackService;

@Service("outerService")
public class OuterServiceImpl implements IOuterService
{
	//由于IAItemsServiceImpl在question包下,所以需要引入该service
	@Resource
	private IQuestionPackService<AItems> itemsService;
	@Resource
	private IQuestionPackService<AColumns> columnsService;
	@Resource 
	private IQuestionPackService<AQuestion> questionService;
	@Resource 
	private IQuestionPackService<AAnswer> answerService;
	
	//reqJson从html中解析得到,保存栏目和栏目下显示的项
	@Override
	public JSONObject insertBatchItemsService(JSONObject reqJson)
	{
		JSONObject resultJson=new JSONObject();
		Map<String,Object> paramMap=new HashMap<String,Object>();
		if("0".equalsIgnoreCase(reqJson.get("code")+""))
		{
			//从data获取itemList
			//{"code":"0","info":"成功","data":
			//{"itemsList":[{"id":"","aa":""},{},{}],"pageinfo":{}}
			JSONObject dataJson=(JSONObject) reqJson.get("data");
			
			String column=dataJson.get("columnId")+"";
			paramMap.clear();
			paramMap.put("url",column);
			//根据栏目id,查询栏目
			AColumns columns=this.columnsService.queryObjService(paramMap);
			
			JSONArray itemsArr=(JSONArray) dataJson.get("itemsList");
			for (Iterator iterator = itemsArr.iterator(); iterator.hasNext();)
			{
				//JSONArray存的都是JSONObject
				JSONObject tempJson = (JSONObject) iterator.next();
				//先根据OutId对数据库中的数据进行查询
				paramMap.clear();
				//postgreSql中严格要求数据类型
				paramMap.put("outId",Integer.valueOf(tempJson.get("outId")+""));
				AItems item=this.itemsService.queryObjService(paramMap);
				
				//设置标志位,没有则新增,有则更新
				boolean flag=true;
				if(item==null)
				{
					item=new AItems();
					item.setCreateTime(new Date());
					item.setPubTime(new Date());
					flag=false;
				}
				//设置栏目与item之间的外键
				item.setColumnsId(columns.getId());
				item.setName(tempJson.get("name") + "");
				item.setImgPath(tempJson.get("imgPath") + "");
				//Integer.valueOf(tempJson.get("testedNum")+"")
				//tempJson.getIntValue("testedNum")
				item.setTestedNum(Integer.valueOf(tempJson.get("testedNum")+""));
				item.setOutId(Integer.valueOf(tempJson.get("outId") + ""));
				
				//容易抛异常,导致事务回滚,故捕捉异常,但不处理
				try
				{
					item.setContent(tempJson.get("content")+"");
					item.setSuggNum(Integer.valueOf(tempJson.get("suggNum")+""));
					item.setFavNum(Integer.valueOf(tempJson.get("favNum")+""));
					item.setQuestionNum(Integer.valueOf(tempJson.get("questionNum")+""));
				}catch (Exception e) {
					//不做处理
				}
				item.setUpdateTime(new Date());
				
				/* 存在:调用更新的方法,
				 * 不存在:调用插入的方法 */
				if(flag)
				{
					this.itemsService.updateObjService(item);
				}else
				{
					this.itemsService.insertObjService(item);
				}
			}
		}
		//返回json
		return null;
	}

	//不传栏目id时则代表查询全部,开启多线程进行spiderDataByColumn网页信息抓取
	@Override
	public JSONObject insertAllItemsService(String columId)
	{
		//使用多线程执行查询任务
		List<MultiThreadUtil> multiThreadUtilList=new ArrayList<MultiThreadUtil>();
		//设置查询的参数条件
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.clear();
		paramMap.put("status", ADataEnumUtil.STATUS_ENABLE.getStatus());
		//url即为columId
		paramMap.put("url", columId);
		List<AColumns> columnList=this.columnsService.queryMultiObjService(null, paramMap);
		for (Iterator iterator = columnList.iterator(); iterator.hasNext();)
		{
			AColumns aColumns = (AColumns) iterator.next();
			ConstantFinalUtil.LoggerMsg.info("-url-{}--",aColumns.getUrl());
			
			//多线程调用spiderDataByColumn方法,分别进行栏目的获取
			MultiThreadUtil multiThreadUtil=new MultiThreadUtil();
			multiThreadUtil.setOuterService(this);
			multiThreadUtil.setColumns(aColumns);
			//将当前线程对象加入到多线程队列中
			multiThreadUtilList.add(multiThreadUtil);
		}
		
		//启动一个multiThreadUtilList.size()大小的线程池
		try
		{
			ExecutorService executorService=Executors.newFixedThreadPool(multiThreadUtilList.size());
			executorService.invokeAll(multiThreadUtilList);
			//当所有线程执行完之后,才能停下来
			executorService.shutdown();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	//拉取所有的栏目和项,并一一调用insertBatchItemsService进行保存
	@Override
	public void spiderDataByColumn(AColumns columns)
	{
		//分页,先把指定栏目的记录数全部拉取
		YxlHtmlParser yxlHtmlParser=new YxlHtmlParser();
		//先取第一个栏目
		JSONObject resultJson=yxlHtmlParser.columnList(columns.getUrl(),1);
		JSONObject dataJson=(JSONObject) resultJson.get("data");
		int totalPage=Integer.valueOf(dataJson.get("totalPage")+"");
		
		//进行遍历
		for (int i = 0; i < totalPage; i++)
		{
			ConstantFinalUtil.LoggerMsg.info("--当前栏目{}-栏目编号{}-",columns.getName(),i);
			resultJson=yxlHtmlParser.columnList(columns.getUrl(),i);
			//调用本类方法,将数据保存到数据库
			this.insertBatchItemsService(resultJson);
		}
		
	}

	@Override
	public void insertBatchItemsService()
	{
		PageInfoUtil pageInfoUtil=new PageInfoUtil();
		pageInfoUtil.setPageSize(100);
		Map<String, Object> paramMap=new HashMap<String,Object>();
		List<AItems> itemList=this.itemsService.queryMultiObjService(pageInfoUtil, paramMap);
		for(int i=1;i<pageInfoUtil.getTotalPage();i++)
		{
			pageInfoUtil.setCurrentPage(i);
			ConstantFinalUtil.LoggerMsg.info("-答案列表-总页数{}-当前页{}-",pageInfoUtil.getTotalPage(),pageInfoUtil.getCurrentPage());
			itemList=this.itemsService.queryMultiObjService(pageInfoUtil, paramMap);
			for (Iterator iterator = itemList.iterator(); iterator.hasNext();)
			{
				AItems aItems = (AItems) iterator.next();
				//根据item查询对应的数据
				this.insertOneItemsService(aItems);
			}
		}
	}

	@Override
	public void insertOneItemsService(AItems item)
	{
		ConstantFinalUtil.LoggerMsg.info("--抓取问题和答案,测试项id:{}-outId{}-name{}-",
				item.getId(),item.getOutId(),item.getName());
		//必须有outId
		if("".equalsIgnoreCase(item.getOutId()+""))
		{
			ConstantFinalUtil.LoggerMsg.info("---抓取问题和答案-为空,请确认,测试项id:{}-outId{}-name{}-",
				item.getId(),item.getOutId(),item.getName());
			return;
		}
		
		try
		{
			YxlHtmlParser yxlHtmlParser=new YxlHtmlParser();
			//根据测试页面对应outId进行查询
			JSONObject resultJson=yxlHtmlParser.question(item.getOutId()+"");
			//抓取成功
			if("0".equalsIgnoreCase(resultJson.getString("code")))
			{
				JSONObject dataJson=(JSONObject) resultJson.get("data");
				//获取问题列表
				JSONArray questionArr=(JSONArray) dataJson.get("questionList");
				for (Iterator iterator = questionArr.iterator(); iterator.hasNext();)
				{
					JSONObject questionJson=(JSONObject) iterator.next();
					
					AQuestion question=new AQuestion();
					question.setItemsId(item.getId());
					//从问题标题项的json中获取
					question.setName(questionJson.getString("name"));
					question.setOrderNum(Integer.valueOf(questionJson.get("orderNum") + ""));
					question.setCreateTime(new Date());
					question.setUpdateTime(new Date());
					question.setPubTime(new Date());
					
					//存储question
					JSONObject resDbJson=this.questionService.insertObjService(question);
					JSONArray answerArr=(JSONArray) questionJson.get("answerList");
					for (Iterator iterator2 = answerArr.iterator(); iterator2.hasNext();)
					{
						JSONObject answerJson = (JSONObject) iterator2.next();
						AAnswer answer=new AAnswer();
						answer.setName(answerJson.get("name") + "");
						answer.setOrderNum(Integer.valueOf(answerJson.get("orderNum") + ""));
						answer.setOutId(Integer.valueOf(answerJson.get("id") + ""));
						answer.setQuestionId(Integer.valueOf(dataJson.get("questionId")+""));
						answer.setCreateTime(new Date());
						answer.setUpdateTime(new Date());
						answer.setPubTime(new Date());
						this.answerService.insertObjService(answer);
					}
				}
				
			}
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
			//不处理数字异常
		}
	}
}
