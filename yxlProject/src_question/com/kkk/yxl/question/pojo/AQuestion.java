package com.kkk.yxl.question.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AQuestion
{
	private int id;
	private int itemsId;
	private String name;
	private int orderNum;
	private byte status;
	private Date createTime;
	private Date updateTime;
	private Date pubTime;
	
	//一个问题,有多个答案
	private List<AAnswer> answerList=new ArrayList<AAnswer>();
	
	public List<AAnswer> getAnswerList()
	{
		return answerList;
	}
	public void setAnswerList(List<AAnswer> answerList)
	{
		this.answerList = answerList;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getItemsId()
	{
		return itemsId;
	}
	public void setItemsId(int itemsId)
	{
		this.itemsId = itemsId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getOrderNum()
	{
		return orderNum;
	}
	public void setOrderNum(int orderNum)
	{
		this.orderNum = orderNum;
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
