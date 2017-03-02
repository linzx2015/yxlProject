package com.kkk.yxl.question.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.common.util.PageInfoUtil;
import com.kkk.yxl.question.dao.IAQuestionDao;
import com.kkk.yxl.question.pojo.AQuestion;
import com.kkk.yxl.question.service.IQuestionPackService;

@Service("questionService")
public class IAQuestionServiceImpl implements IQuestionPackService<AQuestion>
{
	@Resource
	private IAQuestionDao questionDao;

	@Override
	public JSONObject insertObjService(AQuestion question)
	{
		resultJson.clear();
		int res=this.questionDao.insert(question);
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "添加成功");
			dataJson.clear();
			dataJson.put("id", question.getId());
			dataJson.put("effectRows", res);
			resultJson.put("data",dataJson);
		}else
		{
			resultJson.put("code", "1");
			resultJson.put("info", "添加失败");
		}
		return resultJson;
	}

	@Override
	public JSONObject updateObjService(AQuestion question)
	{
		int res=this.questionDao.update(question);
		resultJson.clear();
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "更新成功");
			dataJson.clear();
			dataJson.put("id", question.getId());
			dataJson.put("effectRows", res);
			resultJson.put("data",dataJson);
		}else
		{
			resultJson.put("code", "1");
			resultJson.put("info", "更新失败");
		}
		return resultJson;
	}

	@Override
	public JSONObject deleteObjService(AQuestion question)
	{
		resultJson.clear();
		int res=this.questionDao.delete(question);
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "删除成功");
			dataJson.clear();
			dataJson.put("id", question.getId());
			dataJson.put("effectRows", res);
			resultJson.put("data",dataJson);
		}else
		{
			resultJson.put("code", "1");
			resultJson.put("info", "删除失败");
		}
		return resultJson;
	}

	@Override
	public AQuestion queryObjService(Map<String, Object> paramMap)
	{
		return this.questionDao.queryOneObject(paramMap);
	}

	@Override
	public List<AQuestion> queryMultiObjService(PageInfoUtil pageInfoUtil,Map<String, Object> paramMap)
	{
		if(paramMap.get("keyword")!=null)
		{
			paramMap.put("keyword", "%"+paramMap.get("keyword")+"%");
		}
		if(pageInfoUtil!=null)
		{
			paramMap.put("pageCond","true");
			//currenRecord和pageSize可以不加
			paramMap.put("currenRecord", "0");
			paramMap.put("pageSize", pageInfoUtil.getPageSize());
			List<AQuestion> questionList=this.questionDao.queryMultiObject(paramMap);
			//查出记录存放在id
			if(questionList.size()==1)
			{
				pageInfoUtil.setTotalRecord(questionList.get(0).getId());
			}
			
			//取出分页的结果
			paramMap.put("pageCond", "false");
			paramMap.put("currenRecord", pageInfoUtil.getCurrentRecord());
			paramMap.put("pageSize", pageInfoUtil.getPageSize());
			return this.questionDao.queryMultiObject(paramMap);
		}else
		{
			return this.questionDao.queryMultiObject(paramMap);
		}
	}
}
