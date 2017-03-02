package com.kkk.yxl.question.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AItems
{
	private int id;
	private String name;
	private String content;
	private byte status;
	private Date createTime;
	private Date updateTime;
	private Date pubTime;
	private int columnsId;
	private int testedNum;
	private int favNum;
	private int suggNum;
	private int questionNum;
	private int outId;
	private String imgPath;
	//关联栏目
	private AColumns columns;
	//关联问题
	private List<AQuestion> questionList=new ArrayList<AQuestion>();
	
	public AColumns getColumns()
	{
		return columns;
	}
	public List<AQuestion> getQuestionList()
	{
		return questionList;
	}
	public void setQuestionList(List<AQuestion> questionList)
	{
		this.questionList = questionList;
	}
	public void setColumns(AColumns columns)
	{
		this.columns = columns;
	}
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
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
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
	public int getColumnsId()
	{
		return columnsId;
	}
	public void setColumnsId(int columnsId)
	{
		this.columnsId = columnsId;
	}
	public int getTestedNum()
	{
		return testedNum;
	}
	public void setTestedNum(int testedNum)
	{
		this.testedNum = testedNum;
	}
	public int getFavNum()
	{
		return favNum;
	}
	public void setFavNum(int favNum)
	{
		this.favNum = favNum;
	}
	public int getSuggNum()
	{
		return suggNum;
	}
	public void setSuggNum(int suggNum)
	{
		this.suggNum = suggNum;
	}
	public int getQuestionNum()
	{
		return questionNum;
	}
	public void setQuestionNum(int questionNum)
	{
		this.questionNum = questionNum;
	}
	public int getOutId()
	{
		return outId;
	}
	public void setOutId(int outId)
	{
		this.outId = outId;
	}
	public String getImgPath()
	{
		return imgPath;
	}
	public void setImgPath(String imgPath)
	{
		this.imgPath = imgPath;
	}
}
