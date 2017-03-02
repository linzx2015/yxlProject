package com.kkk.yxl.common.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.common.util.PageInfoUtil;

public interface IUserService<T>
{
	//所有实现类都需要用到
    JSONObject resultJson=new JSONObject();
	JSONObject dataJson=new JSONObject();
	/**
	 * 增加一条记录的服务
	 */
	JSONObject insertObjService(T t);

	/**
	 * 更新一条记录的服务
	 * @author kkk
	 */
	JSONObject updateObjService(T t);

	/**
	 * 删除一条记录的服务
	 * @author kkk
	 */
	JSONObject deleteObjService(T t);

	/**
	 * 查询一条记录的服务
	 * @author kkk
	 */
	T queryObjService(Map<String, Object> paramMap);

	/**
	 * 查询多条记录的服务
	 * @author kkk
	 */
	List<T> queryMultiObjService(PageInfoUtil pageInfoUtil,Map<String, Object> paramMap);
}
