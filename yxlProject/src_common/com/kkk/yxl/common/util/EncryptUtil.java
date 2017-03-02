package com.kkk.yxl.common.util;

import java.security.MessageDigest;
import java.util.Arrays;
/*
 * 加密算法工具类
 * */
public class EncryptUtil
{
	/**
	 * md5算法
	 * @param s
	 * @return
	 */
	public static String MD5(String s)
	{
		//16进制转化
		char hexDigits[] =
		{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try
		{
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				//对每个字节都进行移位
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 密码加密,生成存放到数据库中的密文都不一样
	 * 加密明文A:$MD5+$随机数+$密码明文
	 * 加密后密文：$MD5+$随机数+A加密后的串($MD5+$随机数+$密码明文)
	 * 
	 * */
	public static String encodePwd(String sourcePwd)
	{
		//密码前缀$MD5
		String prefix="$MD5";
		//32位随机数
		String rand="$"+RandomCodeUtil.randomStr(32);
		//拼装成前缀$MD5+$随机数
		prefix+=prefix+rand;
		System.out.println("----"+prefix);
		//拼装成$MD5+$随机数+$密码明文
		String preEncodeStr=prefix+"$"+sourcePwd;
		//进行加密
		String afterEncodeStr=EncryptUtil.MD5(preEncodeStr);
		
		//最后拼装成存放到数据库中的密码串
		String dbStr=prefix+"$"+afterEncodeStr;
		return dbStr;
	}
	
	/**
	 * 验证两个密码串是否一致
	 * 将加密之前的字符串与加密后的字符串进行对比
	 * @param sourcePwd 明文(待验证的密码)
	 * @param encodePwd 加密后的密码:如 $MD5$wIdRcNJe1bnCZqX8nGJsLNiiXlq2ePTS$0D1C0A387FA001B1C9E719E539810096
	 * @return
	 * */
	public static boolean checkPwdStr(String sourcePwd,String encodePwd)
	{
		//将密码串分别拆分出来
		String[] encodeArr=encodePwd.split("\\$");
		//System.out.println("-encodeArr-"+Arrays.toString(encodeArr));
		
		//取得前缀
		String prefix="$"+encodeArr[1]+"$"+encodeArr[2];
		//拼装成$MD5+$随机数+$密码明文
		String preEncodeStr=prefix+"$"+sourcePwd;
		//进行加密
		String afterEncodeStr=EncryptUtil.MD5(preEncodeStr);
		
		String dbStr=prefix+"$"+afterEncodeStr;
		
		return dbStr.equals(encodePwd);
	}
	
	//测试方法
	public static void main(String args[])
	{
		String sourcePwd="111111";
		String dbStr=EncryptUtil.encodePwd(sourcePwd);
		System.out.println("-dbStr-"+dbStr);
		
		String encodePwd="$MD5$9NK7D28dUltgzfTTjRmqoQzEPMTtDvAz$D246EDFCE76348393E8D23B68684CE67";
		boolean flag=EncryptUtil.checkPwdStr(sourcePwd, encodePwd);
		System.out.println("--flag--"+flag);
	}
	
}
