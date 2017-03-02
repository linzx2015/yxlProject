package com.kkk.yxl.question.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.common.util.PageInfoUtil;
import com.kkk.yxl.question.dao.IAnswerDao;
import com.kkk.yxl.question.pojo.AAnswer;
import com.kkk.yxl.question.service.IQuestionPackService;

@Service("answerService")
public class IAnswerServiceImpl implements IQuestionPackService<AAnswer>
{
	@Resource
	private IAnswerDao answerDao;

	@Override
	public JSONObject insertObjService(AAnswer answer)
	{
		resultJson.clear();
		int res=this.answerDao.insert(answer);
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "添加成功");
			dataJson.clear();
			dataJson.put("id", answer.getId());
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
	public JSONObject updateObjService(AAnswer answer)
	{
		int res=this.answerDao.update(answer);
		resultJson.clear();
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "更新成功");
			dataJson.clear();
			dataJson.put("id", answer.getId());
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
	public JSONObject deleteObjService(AAnswer answer)
	{
		resultJson.clear();
		int res=this.answerDao.delete(answer);
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "删除成功");
			dataJson.clear();
			dataJson.put("id", answer.getId());
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
	public AAnswer queryObjService(Map<String, Object> paramMap)
	{
		return this.answerDao.queryOneObject(paramMap);
	}

	@Override
	public List<AAnswer> queryMultiObjService(PageInfoUtil pageInfoUtil,Map<String, Object> paramMap)
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
			List<AAnswer> answerList=this.answerDao.queryMultiObject(paramMap);
			//查出记录存放在id
			if(answerList.size()==1)
			{
				pageInfoUtil.setTotalRecord(answerList.get(0).getId());
			}
			
			//取出分页的结果
			paramMap.put("pageCond", "false");
			paramMap.put("currenRecord", pageInfoUtil.getCurrentRecord());
			paramMap.put("pageSize", pageInfoUtil.getPageSize());
			return this.answerDao.queryMultiObject(paramMap);
		}else
		{
			return this.answerDao.queryMultiObject(paramMap);
		}
	}
}
