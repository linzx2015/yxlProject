package com.kkk.yxl.user.pojo;

import java.io.Serializable;
import java.util.Date;

//对应数据库的管理员表
public class AAdmins implements Serializable
{
	// 管理员id
	private int id;
	private int roleId;
	// 管理员邮箱
	private String email;
	// 管理员密码
	private String password;
	// 管理员手机
	private String phone;
	// qq
	private String qq;
	// 真实姓名
	private String trueName;
	//图片路径
	private String photoPath ; 
	// 当前状态 0:禁用;1:启用
	private int status;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;
	// 上次登录时间
	private Date lastLoginTime;

	//持有role
	private Arole role;

	public Date getLastLoginTime()
	{
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime)
	{
		this.lastLoginTime = lastLoginTime;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getQq()
	{
		return qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}

	public String getTrueName()
	{
		return trueName;
	}

	public void setTrueName(String trueName)
	{
		this.trueName = trueName;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
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

	public String getPhotoPath()
	{
		return photoPath;
	}

	public void setPhotoPath(String photoPath)
	{
		this.photoPath = photoPath;
	}

	public int getRoleId()
	{
		return roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	public Arole getRole()
	{
		return role;
	}

	public void setRole(Arole role)
	{
		this.role = role;
	}
}
