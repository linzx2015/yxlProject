package com.kkk.yxl.user.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Arole implements Serializable
{
	//角色主键id
	private int id;
	//角色名称
	private String name;
	//介绍
	private String content;
	//状态
	private int status;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//发布时间
	private Date pubTime;
	
	private List<AAdmins> adminList;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Date getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public Date getUpdateTime()
	{
		return updateTime;
	}
	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}
	public Date getPubTime()
	{
		return pubTime;
	}
	public void setPubTime(Date pubTime)
	{
		this.pubTime = pubTime;
	}
	public List<AAdmins> getAdminList()
	{
		return adminList;
	}
	public void setAdminList(List<AAdmins> adminList)
	{
		this.adminList = adminList;
	}
}
