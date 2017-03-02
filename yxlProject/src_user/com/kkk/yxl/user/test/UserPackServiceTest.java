package com.kkk.yxl.user.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.common.service.IUserService;
import com.kkk.yxl.common.test.BaseTest;
import com.kkk.yxl.common.util.ConstantFinalUtil;
import com.kkk.yxl.common.util.PageInfoUtil;
import com.kkk.yxl.user.pojo.AAdmins;
import com.kkk.yxl.user.pojo.Arole;

public class UserPackServiceTest extends BaseTest
{
	private IUserService<AAdmins> adminService;
	private IUserService<Arole> roleService;
	
	@Before
	public void init()
	{
		super.init();
		this.adminService=(IUserService<AAdmins>) this.ac.getBean("adminService");
		this.roleService=(IUserService<Arole>) this.ac.getBean("roleService");
	}
	
	@Test
	public void queryOneObjtest()
	{
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("id", 2);
		AAdmins admin=this.adminService.queryObjService(paramMap);
		ConstantFinalUtil.LoggerMsg.info("--queryOneObjtest-{}-",admin.getPassword());
	}
	
	@Test
	public void insertOneTest()
	{
		//注意postgresql对数据类型要求比较高
		AAdmins admins=new AAdmins();
		admins.setEmail("22");
		admins.setRoleId(1);
		admins.setPassword("22");
		admins.setPhone("11");
		admins.setQq("11");
		admins.setTrueName("11");
		admins.setStatus(1);
		admins.setPhotoPath("1");
		admins.setCreateTime(new Date());
		admins.setUpdateTime(new Date());
		admins.setLastLoginTime(new Date());
		JSONObject resultJson=this.adminService.insertObjService(admins);
		ConstantFinalUtil.LoggerMsg.info("--insertOneTest-{}-",resultJson);
	}
	
	@Test
	public void updateOneTest()
	{
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("id", 2);
		AAdmins admin=this.adminService.queryObjService(paramMap);
		admin.setEmail("kkkkkkkkkkk");
		JSONObject resultJson=this.adminService.updateObjService(admin);
		ConstantFinalUtil.LoggerMsg.info("--{}--",resultJson);
	}
	
	@Test
	public void queryMutliAdmin()
	{
		/* 查询条件 */
		Map<String, Object> paramMap = this.getParamMap();
		paramMap.put("keyword", "2");
		/* 分页 */
		PageInfoUtil pageInfoUtil = new PageInfoUtil();
		/*List<Aadmins> adminsList = this.userService.queryMultiObjService(pageInfoUtil, paramMap);*/
		List<AAdmins> adminsList = this.adminService.queryMultiObjService(null, paramMap);
		for (Iterator iterator = adminsList.iterator(); iterator.hasNext();)
		{
			AAdmins admins = (AAdmins) iterator.next();
			ConstantFinalUtil.LoggerMsg.info("id:{},email:{}",admins.getId() ,admins.getEmail());
		}
		
		ConstantFinalUtil.LoggerMsg.info("分页对象:{}",pageInfoUtil);
	}
	
	/////////////////////////////////////////////////////////
	//测试角色
	@Test
	public void queryOneRoleTest()
	{
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("id", 1);
		Arole role=this.roleService.queryObjService(paramMap);
		ConstantFinalUtil.LoggerMsg.info("--{}-",role.getName());
	}
	
	@Test
	public void queryMultiPartTest()
	{
		/* 查询条件 */
		Map<String, Object> paramMap = this.getParamMap();
		//paramMap.put("keyword", "2");
		/* 分页 */
		PageInfoUtil pageInfoUtil = new PageInfoUtil();
		/*List<Arole> roleList = this.roleService.queryMultiObjService(pageInfoUtil, paramMap);*/
		List<Arole> roleList = this.roleService.queryMultiObjService(null, paramMap);
		for (Iterator iterator = roleList.iterator(); iterator.hasNext();)
		{
			Arole role = (Arole) iterator.next();
			ConstantFinalUtil.LoggerMsg.info("id:{},email:{}",role.getId() ,role.getContent());
		}
		
		ConstantFinalUtil.LoggerMsg.info("分页对象:{}",pageInfoUtil);
	}
	
	
}
