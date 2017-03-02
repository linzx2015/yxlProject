package com.kkk.yxl.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * http的工具类
 * 
 * HTTP协议:
 * 把HTTP协议理解为写信的过程;
 * 通过firebug,可以查看每一个链接向服务器发出的详细信息;
 * 请求头======向服务器写信的信封
 * 响应头======服务器的回信的信封
 * 
 * 参数=====向服务器写信的内容(信封里面的内容)
 * 响应=====服务器的回信的信封里面的内容
 * 
 * @author kkk 
 *
 */
public class HttpUtil
{
	/**
	 * httpget方式请求
	 * 参数==发信
	 * @param urlStr 请求地址
	 * @param headerMap 信封的内容
	 * @param paramsMap 信的内容
	 * @return 返回url对应的网页内容;回信的内容
	 */
	public String methodGet(String urlStr,Map<String, String> headerMap,Map<String, String> paramsMap)
	{
		StringBuffer sb = new StringBuffer() ; 
		/* get提交 */
		try
		{
			if(!urlStr.endsWith("?"))
			{
				urlStr += "?" ; 
			}
			
			/* get方式提交,如果将信的内容,传递给服务器 */
			for (Iterator iterator = paramsMap.entrySet().iterator(); iterator.hasNext();)
			{
				Entry me = (Entry) iterator.next();
				urlStr += me.getKey() + "=" + me.getValue() + "&"; 
			}
			System.out.println(urlStr + "=====");
			
			/* 根据指定url字符串创建一个url对象 */
			URL url = new URL(urlStr);
			/* 查看一个urlConnection具体是什么
			 * 强转HttpURLConnection; 因为urlConnection本身就是HttpURLConnection;通过debug看到的
			 *  */
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection() ; 
			/*System.out.println("----" + urlConnection);*/
			
			/* 设置超时时间 */
			urlConnection.setConnectTimeout(ConstantFinalUtil.CONNECT_TIMEOUT);
			urlConnection.setReadTimeout(ConstantFinalUtil.READ_TIMEOUT);
			
			/* 如何将信封的内容设置到请求中 */
			for (Iterator iterator = headerMap.entrySet().iterator(); iterator.hasNext();)
			{
				Entry me = (Entry) iterator.next();
				urlConnection.setRequestProperty(me.getKey() + "", me.getValue() + "");
			}
			
			/* 获取输入流 */
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			String line = "" ; 
			while((line = br.readLine()) != null )
			{
				//System.out.println(line);
				//response += line ;
				sb.append(line);
			}
		} catch (Exception e)
		{
			ConstantFinalUtil.LoggerMsg.error("发送get请求失败了;URL:{},参数:{},返回:{}"
					,urlStr,paramsMap,sb , e);
		}
		return sb.toString() ; 
	}
	
	/**
	 * http Post请求
	 * @param url
	 * @param headerMap
	 * @param paramsMap
	 * @return
	 */
	public String methodPost(String urlStr,Map<String, String> headerMap,Map<String, String> paramsMap)
	{
		StringBuffer sb = new StringBuffer();
		try
		{
			URL url = new URL(urlStr);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection() ;
			/* 设置输入和输出的开关 */
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			
			/* 设置超时时间 */
			urlConnection.setConnectTimeout(ConstantFinalUtil.CONNECT_TIMEOUT);
			urlConnection.setReadTimeout(ConstantFinalUtil.READ_TIMEOUT);
			
			/* 如何将信封的内容设置到请求中 */
			for (Iterator iterator = headerMap.entrySet().iterator(); iterator.hasNext();)
			{
				Entry me = (Entry) iterator.next();
				urlConnection.setRequestProperty(me.getKey() + "", me.getValue() + "");
			}
			
			/* 向服务器端传数据 */
			OutputStream os = urlConnection.getOutputStream() ;
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
			
			/* 如何将服务器写数据呢?
			 *https就使用HttpsURLConnection
			 * 通过firebug发现post请求,发送的是以下字符串
			 * returnUrl=&email=22%4022.com&password=111111ddd&code=dddd
			 *  */
			StringBuffer paramSb = new StringBuffer();
			for (Iterator iterator = paramsMap.keySet().iterator(); iterator.hasNext();)
			{
				String key = (String) iterator.next();
				/* 获取的是一个一个的key */
				paramSb.append(key + "=" + paramsMap.get(key) + "&");
			}
			//System.out.println("====>" + paramSb);
			
			/* 将信息发送给服务器端 */
			bw.write(paramSb.toString());
			
			bw.flush();
			bw.close();
			
			InputStream is = urlConnection.getInputStream() ;
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = "" ; 
			while((line = br.readLine()) != null)
			{
				sb.append(line);
			}
		} catch (Exception e)
		{
			/* 将异常记录日志文件中 */
			ConstantFinalUtil.LoggerMsg.error("发送post请求失败了;URL:{},参数:{},返回:{}"
					,urlStr,paramsMap,sb , e);
		}
		return sb.toString() ; 
	}
}
