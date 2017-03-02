package com.kkk.yxl.user.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.common.util.PageInfoUtil;
import com.kkk.yxl.user.dao.IRoleDao;
import com.kkk.yxl.user.pojo.Arole;
import com.kkk.yxl.user.service.IUserPackService;

@Service("roleService")
public class ARoleServiceImpl implements IUserPackService<Arole>
{
	@Resource
	private IRoleDao roleDao;
	
	@Override
	public JSONObject insertObjService(Arole role)
	{
		resultJson.clear();
		int res=this.roleDao.insert(role);
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "添加成功");
			dataJson.clear();
			dataJson.put("id", role.getId());
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
	public JSONObject updateObjService(Arole role)
	{
		int res=this.roleDao.update(role);
		resultJson.clear();
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "更新成功");
			dataJson.clear();
			dataJson.put("id", role.getId());
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
	public JSONObject deleteObjService(Arole role)
	{
		resultJson.clear();
		int res=this.roleDao.delete(role);
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "删除成功");
			dataJson.clear();
			dataJson.put("id", role.getId());
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
	public Arole queryObjService(Map<String, Object> paramMap)
	{
		return this.roleDao.queryOneObject(paramMap);
	}

	@Override
	public List<Arole> queryMultiObjService(PageInfoUtil pageInfoUtil,Map<String, Object> paramMap)
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
			List<Arole> roleList=this.roleDao.queryMultiObject(paramMap);
			//查出记录存放在id
			if(roleList.size()==1)
			{
				pageInfoUtil.setTotalRecord(roleList.get(0).getId());
			}
			
			//取出分页的结果
			paramMap.put("pageCond", "false");
			paramMap.put("currenRecord", pageInfoUtil.getCurrentRecord());
			paramMap.put("pageSize", pageInfoUtil.getPageSize());
			return this.roleDao.queryMultiObject(paramMap);
		}else
		{
			return this.roleDao.queryMultiObject(paramMap);
		}
	}
}
