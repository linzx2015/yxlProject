package com.kkk.yxl.outer.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.kkk.yxl.common.util.ConstantFinalUtil;

import java.util.HashMap;
import java.util.Iterator;

public class HttpSpiderUtil
{
	/**
	 * 用于获取Document对象,方便解析html标签
	 * @param url
	 * @return Document
	 * */
	public static Document getDocObj(String url) throws IOException
	{
		Connection conn=Jsoup.connect(url);
		conn.timeout(ConstantFinalUtil.CONNECT_TIMEOUT);
		Document document=conn.get();
		return document;
	}
	
	/**
	 * urlStr:需要抓取新的链接
	 * headerMap:请求头信息
	 * paramMap:请求的参数信息  ==发信的内容
	 * @return 从抓取的链接中得到的响应结果
	 * */
	public String infoFromUrlByGet(String urlStr,Map<String,String>headerMap,Map<String,String> paramMap)
	{
		StringBuffer sb=new StringBuffer();
		try
		{
			//get方式获取网页
			if(!urlStr.endsWith("?"))
			{
				urlStr+="?";
			}
			//将要请求的参数拼装一起
			for(Iterator it=paramMap.entrySet().iterator();it.hasNext();)
			{
				Entry<String, String> en=(Entry<String, String>) it.next();
				urlStr+=en.getKey()+"="+en.getValue()+"&";
			}
			System.out.println("--urlStr--"+urlStr);
			URL url=new URL(urlStr);
			//获取HttpURLConnection,其为URLConnection的子类
			HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
			//将请求头放到参数中
			for(Iterator ith=headerMap.entrySet().iterator();ith.hasNext();)
			{
				Entry en2=(Entry) ith.next();
				httpURLConnection.setRequestProperty(en2.getKey()+"", en2.getValue()+"");
			}
			
			String temp="";
			//获取输入流
			BufferedReader br=new BufferedReader(new 
					InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
			while((temp=br.readLine())!=null)
			{
				sb.append(temp+"\n");
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * post请求参数向指定网页提交
	 * 
	 * */
	public String infoFromUrlByPost(String urlStr,Map<String,String> headerMap,Map<String,String> paramMap)
	{
		StringBuffer sb=new StringBuffer();
		try
		{
			URL url=new URL(urlStr);
			HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			
			for(Iterator it=headerMap.keySet().iterator();it.hasNext();)
			{
				Entry en=(Entry) it.next();
				httpURLConnection.setRequestProperty(en.getKey()+"",en.getValue()+"");
			}
			//向服务器发送post请求
			BufferedWriter bw=new BufferedWriter(new 
					OutputStreamWriter(httpURLConnection.getOutputStream(),"UTF-8"));
			/* 如何将服务器写数据呢?
			 * 通过firebug发现post请求,发送的是以下字符串
			 * returnUrl=&email=22%4022.com&password=111111ddd&code=dddd
			 *  */
			StringBuffer paramSb=new StringBuffer();
			for(Iterator it=paramMap.keySet().iterator();it.hasNext();)
			{
				String key=(String) it.next();
				paramSb.append(key+"="+paramMap.get(key)+"&");
			}
			bw.write(paramSb.toString());
			bw.flush();
			bw.close();
			
			BufferedReader br=new BufferedReader(new 
					InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
			String line="";
			while((line=br.readLine())!=null)
			{
				sb.append(line);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}
}
