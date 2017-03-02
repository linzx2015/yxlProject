package com.kkk.yxl.question.pojo;

import java.util.Date;

public class AAnswer
{
	private int id;
	private int questionId;
	private int orderNum;
	private int outId;
	private String name;
	private byte status;
	private Date createTime;
	private Date updateTime;
	private Date pubTime;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getQuestionId()
	{
		return questionId;
	}
	public void setQuestionId(int questionId)
	{
		this.questionId = questionId;
	}
	public int getOrderNum()
	{
		return orderNum;
	}
	public void setOrderNum(int orderNum)
	{
		this.orderNum = orderNum;
	}
	public int getOutId()
	{
		return outId;
	}
	public void setOutId(int outId)
	{
		this.outId = outId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public byte getStatus()
	{
		return status;
	}
	public void setStatus(byte status)
	{
		this.status = status;
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
}
