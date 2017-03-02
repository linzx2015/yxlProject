package com.kkk.yxl.user.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.common.util.PageInfoUtil;
import com.kkk.yxl.user.dao.IAdminDao;
import com.kkk.yxl.user.pojo.AAdmins;
import com.kkk.yxl.user.service.IUserPackService;

@Service("adminService")
public class AAdminServiceImpl implements IUserPackService<AAdmins>
{
	@Resource
	private IAdminDao adminDao;

	@Override
	public JSONObject insertObjService(AAdmins admin)
	{
		resultJson.clear();
		int res=this.adminDao.insert(admin);
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "添加成功");
			dataJson.clear();
			dataJson.put("id", admin.getId());
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
	public JSONObject updateObjService(AAdmins admin)
	{
		int res=this.adminDao.update(admin);
		resultJson.clear();
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "更新成功");
			dataJson.clear();
			dataJson.put("id", admin.getId());
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
	public JSONObject deleteObjService(AAdmins admin)
	{
		resultJson.clear();
		int res=this.adminDao.delete(admin);
		if(res>0)
		{
			resultJson.put("code", "0");
			resultJson.put("info", "删除成功");
			dataJson.clear();
			dataJson.put("id", admin.getId());
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
	public AAdmins queryObjService(Map<String, Object> paramMap)
	{
		return this.adminDao.queryOneObject(paramMap);
	}

	@Override
	public List<AAdmins> queryMultiObjService(PageInfoUtil pageInfoUtil,Map<String, Object> paramMap)
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
			List<AAdmins> adminList=this.adminDao.queryMultiObject(paramMap);
			//查出记录存放在id
			if(adminList.size()==1)
			{
				pageInfoUtil.setTotalRecord(adminList.get(0).getId());
			}
			
			//取出分页的结果
			paramMap.put("pageCond", "false");
			paramMap.put("currenRecord", pageInfoUtil.getCurrentRecord());
			paramMap.put("pageSize", pageInfoUtil.getPageSize());
			return this.adminDao.queryMultiObject(paramMap);
		}else
		{
			return this.adminDao.queryMultiObject(paramMap);
		}
	}
	
}
