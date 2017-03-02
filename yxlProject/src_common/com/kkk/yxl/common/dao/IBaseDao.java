package com.kkk.yxl.common.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T>
{
	/**
	 * 保存一条记录
	 * 
	 * @author kkk
	 */
	int insert(T t);

	/**
	 * 更新一条记录
	 * 
	 * @author kkk
	 */
	int update(T t);

	/**
	 * 删除一条记录
	 * 
	 * @author kkk
	 */
	int delete(T t);

	/**
	 * 查询一条记录
	 * 
	 * @author kkk
	 */
	T queryOneObject(Map<String, Object> paramMap);

	/**
	 * 查询多条记录
	 * 
	 * @author kkk
	 */
	List<T> queryMultiObject(Map<String, Object> paramMap);
}
