package com.kkk.yxl.common.util;

/**
 * 枚举工具类:用于status进行设置值时,限制用户只能输入两种值
 * 
 * */
public enum ADataEnumUtil
{
	STATUS_ENABLE(Byte.valueOf("1"),"启用"),
	STATUS_DISABLE(Byte.valueOf("0"),"禁用");
	//增加枚举
	private byte status;
	private String info;
	private ADataEnumUtil(byte status, String info)
	{
		this.status = status;
		this.info = info;
	}
	public byte getStatus()
	{
		return status;
	}
	public void setStatus(byte status)
	{
		this.status = status;
	}
	public String getInfo()
	{
		return info;
	}
	public void setInfo(String info)
	{
		this.info = info;
	} 
}
