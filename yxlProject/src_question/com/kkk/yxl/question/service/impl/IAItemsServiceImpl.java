package com.kkk.yxl.question.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.common.util.PageInfoUtil;
import com.kkk.yxl.question.dao.IAItemsDao;
import com.kkk.yxl.question.pojo.AItems;
import com.kkk.yxl.question.service.IQuestionPackService;

@Service("itemsService")
public class IAItemsServiceImpl implements IQuestionPackService<AItems>
{

	@Resource
	private IAItemsDao itemsDao;

	@Override
	public JSONObject insertObjService(AItems items)
	{
		resultJson.clear();
		int res=this.itemsDao.insert(items);
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "添加成功");
			dataJson.clear();
			dataJson.put("id", items.getId());
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
	public JSONObject updateObjService(AItems items)
	{
		int res=this.itemsDao.update(items);
		resultJson.clear();
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "更新成功");
			dataJson.clear();
			dataJson.put("id", items.getId());
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
	public JSONObject deleteObjService(AItems items)
	{
		resultJson.clear();
		int res=this.itemsDao.delete(items);
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "删除成功");
			dataJson.clear();
			dataJson.put("id", items.getId());
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
	public AItems queryObjService(Map<String, Object> paramMap)
	{
		return this.itemsDao.queryOneObject(paramMap);
	}

	@Override
	public List<AItems> queryMultiObjService(PageInfoUtil pageInfoUtil,Map<String, Object> paramMap)
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
			List<AItems> itemsList=this.itemsDao.queryMultiObject(paramMap);
			//查出记录存放在id
			if(itemsList.size()==1)
			{
				pageInfoUtil.setTotalRecord(itemsList.get(0).getId());
			}
			
			//取出分页的结果
			paramMap.put("pageCond", "false");
			paramMap.put("currenRecord", pageInfoUtil.getCurrentRecord());
			paramMap.put("pageSize", pageInfoUtil.getPageSize());
			return this.itemsDao.queryMultiObject(paramMap);
		}else
		{
			return this.itemsDao.queryMultiObject(paramMap);
		}
	}
}
