package com.kkk.yxl.question.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AColumns implements Serializable
{

	private int id;
	private String name;
	private String url;
	private String content;
	private Date createTime;
	private Date updateTime;
	private Date pubTime;
	private byte flag;
	private byte status;
	//一个栏目对应多个项
	private List<AItems> itemList=new ArrayList<AItems>();
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
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
	public byte getFlag()
	{
		return flag;
	}
	public void setFlag(byte flag)
	{
		this.flag = flag;
	}
	public byte getStatus()
	{
		return status;
	}
	public void setStatus(byte status)
	{
		this.status = status;
	}
}
