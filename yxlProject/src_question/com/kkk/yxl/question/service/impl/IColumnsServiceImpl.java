package com.kkk.yxl.question.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.common.util.PageInfoUtil;
import com.kkk.yxl.question.dao.IColumnsDao;
import com.kkk.yxl.question.pojo.AColumns;
import com.kkk.yxl.question.service.IQuestionPackService;

@Service("columnsService")
public class IColumnsServiceImpl implements IQuestionPackService<AColumns>
{
	@Resource
	private IColumnsDao columnDao;

	@Override
	public JSONObject insertObjService(AColumns column)
	{
		resultJson.clear();
		int res=this.columnDao.insert(column);
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "添加成功");
			dataJson.clear();
			dataJson.put("id", column.getId());
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
	public JSONObject updateObjService(AColumns column)
	{
		int res=this.columnDao.update(column);
		resultJson.clear();
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "更新成功");
			dataJson.clear();
			dataJson.put("id", column.getId());
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
	public JSONObject deleteObjService(AColumns column)
	{
		resultJson.clear();
		int res=this.columnDao.delete(column);
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "删除成功");
			dataJson.clear();
			dataJson.put("id", column.getId());
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
	public AColumns queryObjService(Map<String, Object> paramMap)
	{
		return this.columnDao.queryOneObject(paramMap);
	}

	@Override
	public List<AColumns> queryMultiObjService(PageInfoUtil pageInfoUtil,Map<String, Object> paramMap)
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
			List<AColumns> columnList=this.columnDao.queryMultiObject(paramMap);
			//查出记录存放在id
			if(columnList.size()==1)
			{
				pageInfoUtil.setTotalRecord(columnList.get(0).getId());
			}
			
			//取出分页的结果
			paramMap.put("pageCond", "false");
			paramMap.put("currenRecord", pageInfoUtil.getCurrentRecord());
			paramMap.put("pageSize", pageInfoUtil.getPageSize());
			return this.columnDao.queryMultiObject(paramMap);
		}else
		{
			return this.columnDao.queryMultiObject(paramMap);
		}
	}
}
