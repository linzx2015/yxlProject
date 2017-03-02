package com.kkk.yxl.outer.util;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kkk.yxl.common.util.ConstantFinalUtil;

/**
 * 专门处理yxl网站的信息
 * 
 * */
public class YxlHtmlParser
{
	
	/**
	 * 抓取测试项的问题和答案
	 * @return JSONObject {"code":"0","info":"","data":
	 *  	{"questionList":[{"name":"","answerList":[{"name":""}]}]}
	 * */
	public JSONObject question(String outId)
	{
		JSONObject resultJson=new JSONObject();
		String url=ConstantFinalUtil.YXL_URL+outId+"/start";
		
		try
		{
			JSONObject dataJson=new JSONObject();
			//通过HttpSpiderUtil工具类,获取Dom对象
			Document document=HttpSpiderUtil.getDocObj(url);
			//取测试列表的元素节点
			Elements questionList=document.getElementsByClass("test_contents");
			JSONArray questionArr=new JSONArray();
			int i=0;
			for (Iterator iterator = questionList.iterator(); iterator.hasNext();)
			{
				Element questionEle = (Element) iterator.next();
				
				Element questionTestEle=questionEle.getElementsByClass("descs").get(0);
				//取得测试的题目内容
				String questionStr=questionTestEle.text();
				questionStr=questionStr.substring(questionStr.indexOf(i+"")+3);
			
				//存储问题的标题项
				JSONObject questionTitleJson=new JSONObject();
				questionTitleJson.put("orderNum", i+"");
				questionTitleJson.put("name", questionStr);
				
				JSONArray answerJsonArr=new JSONArray();
				//取得测试的选择项,注意是多个选择的项用Elements
				Elements questionAnswerEles=questionEle.getElementsByClass("i_mid");
				
				int j=1;
				for (Iterator iterator2 = questionAnswerEles.iterator(); iterator2.hasNext();)
				{
					//获取当前选择项元素
					Element optionEle = (Element) iterator2.next();
					//获取单选按钮的元素
					Element radionEle=optionEle.getElementsByTag("input").get(0);
					//选择项的内容
					String optionText=optionEle.text();
					
					//将得到元素存储到json中
					JSONObject answerJson=new JSONObject();
					answerJson.put("orderNum", j+"");
					answerJson.put("name", optionText);
					answerJson.put("id",radionEle.attr("value"));
					answerJsonArr.add(answerJson);
					j++;
				}
				//将选择项存入到questionTitleJson中
				questionTitleJson.put("answerList", answerJsonArr);
				questionArr.add(questionTitleJson);
				i++;
			}
			dataJson.put("questionList", questionArr);
			dataJson.put("questionId", outId);
			resultJson.put("data", dataJson);
			resultJson.put("code", "0");
			resultJson.put("info", "获取成功");
		} catch (IOException e)
		{
			resultJson.put("code", "1");
			resultJson.put("info", "获取失败");
			ConstantFinalUtil.LoggerMsg.error("--获取问题和答案失败--",e);
		}
		return resultJson;
	}
	
	/**
	 * 抓取详情页内容
	 * @return JSONObject {"code":"0","info":"成功","data":
	 * 	{"detail":[{"id":"","aa":""},{},{}],"pageinfo":{}}
	 * */
	public JSONObject detail(String outId)
	{
		JSONObject resultJson=new JSONObject();
		String url=ConstantFinalUtil.YXL_URL+outId;
		
		//适当进行休眠,避免被目标服务器压力过大,导致本机ip被封
		try
		 {
			Thread.sleep(500);
			JSONObject dataJson=new JSONObject();
			//通过HttpSpiderUtil工具类,获取Dom对象
			Document document=HttpSpiderUtil.getDocObj(url);
			
			//tshow_index 需要获取的内容 :收藏数 详情内容 tdesc 图片地址 不用取
			Elements detailEles=document.getElementsByClass("tshow_index");
			//相当于只取第一个元素
			if(detailEles.size()==1)
			{
				Element detailsFixedEles = detailEles.get(0).getElementsByClass("tdesc").get(0);
				Element linfoAEle = detailsFixedEles.getElementsByTag("p").get(0);
				String title = linfoAEle.text();
				
				// 取得收藏次数
				String favNum = detailEles.toString();
				favNum = favNum.substring(favNum.indexOf("次数\"></span>") + 11, favNum.indexOf("次收"));

				/* 其实不用爬以下注释内容 问题表和评论表做出来之后 联表查询就可以了 */
				// 取得 总题目数

				Element totalQuestionstr = detailEles.get(0).getElementsByClass("total").get(0)
						.getElementsByTag("span").get(0);
				String totalQuest = totalQuestionstr.text();
				totalQuest = totalQuest.substring(totalQuest.indexOf("共 ") + 2, totalQuest.indexOf(" \u00A0"));

				// 取得 取得评论条数
				Element linfoAElefav = detailEles.get(0).getElementsByTag("a").get(0);
				String commentNum = linfoAElefav.text();
				commentNum = commentNum.substring(0, commentNum.indexOf("条"));

				JSONObject detailsJSON = new JSONObject();
				detailsJSON.put("detail", title);
				detailsJSON.put("favNum", favNum);
				detailsJSON.put("commentNum", commentNum);
				detailsJSON.put("totalQuest", totalQuest);

				//将拼装好的json,放到容器中
				dataJson.put("detail", detailsJSON);
			}
			//放数据到data
			resultJson.put("data", dataJson);
			resultJson.put("code", "0");
			resultJson.put("info", "获取成功");
		 } catch (Exception e)
		 {
			resultJson.put("code", "1");
			resultJson.put("info", "获取失败");
			ConstantFinalUtil.LoggerMsg.error("-从壹心理获取详情页信息失败-",e);
		 }
		return resultJson;
	}
	
	/**
	 * 从yxl网页中获取抓取栏目列表页面html信息
	 * 并转化成json信息
	 * @param column:栏目id
	 * @return 返回的数据:{"code":"0","info":"成功","data":
	 * 	{"itemsList":[{"id":"","aa":""},{},{}],"pageinfo":{}}
	 * */
	public JSONObject columnList(String columnId,int currentPage)
	{
		JSONObject resultJson=new JSONObject();
		//ConstantFinalUtil.YXL_URL壹心理网站连接
		String url=ConstantFinalUtil.YXL_URL+columnId+"?page="+currentPage;	
		
		try
		{	
			//dataJson存放itemsList信息
			JSONObject dataJson=new JSONObject();
			//通过HttpSpiderUtil工具类,获取Dom对象
			Document document=HttpSpiderUtil.getDocObj(url);
			//System.out.println("=document=="+document);
			
			//先获取总节点
			Elements listShowEles=document.getElementsByClass("list_show");
			if(listShowEles.size()==1)
			{
				 Elements listShowElesChildren=listShowEles.get(0).getElementsByClass("fixed");
				 //存放信息 "itemsList":[{"id":"","aa":""},{},{}],"pageinfo":{}
				 JSONArray itemsArr=new JSONArray();
				 int count=0;
				 for (Iterator iterator = listShowElesChildren.iterator(); iterator.hasNext();)
				 {
					Element element = (Element) iterator.next();
					//取第一个元素 <p class="linfo fl"> <a href="http://www.xinli001.com/ceshi/99897649">
					//测测今年的桃花运：什么时候遇到对的人</a><br>	
					//<p class="linfo fl">有两个class只能选取其中一个进行获取
					Element linfoEle=element.getElementsByClass("linfo").get(0);
					//按照标签获取a标签
					Element linfoAhrefEle=linfoEle.getElementsByTag("a").get(0);
					//取得开始和结束标签的内容-->测测今年的桃花运：什么时候遇到对的人
					String title=linfoAhrefEle.text();
					//获取href属性的值
					String hrefValue=linfoAhrefEle.attr("href");
					
					//取测试人数-->7472人测试过
					String testNumStr=element.getElementsByClass("total").get(0).text();
					testNumStr=testNumStr.substring(0,testNumStr.indexOf("人"));
					
					//get(0)从第一个元素中获取图片地址
					Element imgEle=element.getElementsByClass("pbox").get(0);
					//也可以通过class=lazyload获取img元素,在获取属性
					String imgPath=imgEle.getElementsByTag("img").get(0).attr("src");
					
					JSONObject itemJson=new JSONObject();
					//拼装成要存入数据库中的json
					itemJson.put("name", title);
					itemJson.put("testedNum", testNumStr);
					itemJson.put("imgPath", imgPath);
					//http://www.xinli001.com/ceshi/99897649 截取columid
					itemJson.put("outId", hrefValue.substring(hrefValue.lastIndexOf("/")+1, hrefValue.length()));
					
					//查询对应的详情页
					JSONObject detailJson=this.detail(itemJson.get("outId")+"");
					if("0".equalsIgnoreCase(detailJson.get("code")+""))
					{
						JSONObject dataDetailJson=detailJson.getJSONObject("data");
						dataDetailJson=dataDetailJson.getJSONObject("detail");
						itemJson.put("content", dataDetailJson.get("detail"));
						itemJson.put("suggNum", dataDetailJson.get("commentNum"));
						itemJson.put("questionNum", dataDetailJson.get("totalQuest"));
						itemJson.put("favNum", dataDetailJson.get("favNum"));
					}
					//将itemJson存入到itemsArr中,转成json数组形式的数据
					itemsArr.add(itemJson);
					count++;
				} 
			 dataJson.put("itemsList", itemsArr);
		 }
		 //存储分页信息
		 Element pageEle=document.getElementById("pages");
		 //存储对应所有的超链接信息
		 Elements pageListEle=pageEle.getElementsByTag("a");
		
		 String totalPage="0";
		 //从最后一条开始遍历,先对集合进行逆序操作
		 Collections.reverse(pageListEle);
		 for (Iterator iterator = pageListEle.iterator(); iterator.hasNext();)
		 {
			Element element = (Element) iterator.next();
			//依次得到分页的字符串
			String pageStr=element.text();
			//判断最后一个是否数字,若是则马上退出
			if(pageStr.matches("\\d+"))
			{
				totalPage=pageStr;
				break;
			}
		 }
	 	 
		 dataJson.put("totalPage", totalPage);
		 //存储分页栏目
		 dataJson.put("columnId", columnId);
		 resultJson.put("data", dataJson);
		 resultJson.put("code", "0");
		 resultJson.put("info", "获取成功");
		} catch (IOException e)
		{
			resultJson.put("code","1");
			resultJson.put("info", "获取失败;"+e);
			ConstantFinalUtil.LoggerMsg.error("-从壹心理获取栏目信息失败-",e);
		}
		return resultJson;
	}
	
	/**
	 * 从yxl网页中获取只抓取栏目列表页面html信息
	 * 并转化成json信息
	 * @param column:栏目id
	 * @return 返回的数据:{"code":"0","info":"成功","data":
	 * 	{"itemsList":[{"id":"","aa":""},{},{}],"pageinfo":{}}
	 * */
	public JSONObject columnOnly(String columnId,int currentPage)
	{
		JSONObject resultJson=new JSONObject();
		//ConstantFinalUtil.YXL_URL壹心理网站连接
		String url=ConstantFinalUtil.YXL_URL+columnId+"?page="+currentPage;	
		
		try
		{	
			//dataJson存放itemsList信息
			JSONObject dataJson=new JSONObject();
			//通过HttpSpiderUtil工具类,获取Dom对象
			Document document=HttpSpiderUtil.getDocObj(url);
			//System.out.println("=document=="+document);
			
			//先获取总节点
			Elements listShowEles=document.getElementsByClass("list_show");
			if(listShowEles.size()==1)
			{
				Elements listShowElesChildren=listShowEles.get(0).getElementsByClass("fixed");
				//存放信息 "itemsList":[{"id":"","aa":""},{},{}],"pageinfo":{}
				JSONArray itemsArr=new JSONArray();
				int count=0;
				for (Iterator iterator = listShowElesChildren.iterator(); iterator.hasNext();)
				{
					Element element = (Element) iterator.next();
					//取第一个元素 <p class="linfo fl"> <a href="http://www.xinli001.com/ceshi/99897649">
					//测测今年的桃花运：什么时候遇到对的人</a><br>	
					//<p class="linfo fl">有两个class只能选取其中一个进行获取
					Element linfoEle=element.getElementsByClass("linfo").get(0);
					//按照标签获取a标签
					Element linfoAhrefEle=linfoEle.getElementsByTag("a").get(0);
					//取得开始和结束标签的内容-->测测今年的桃花运：什么时候遇到对的人
					String title=linfoAhrefEle.text();
					//获取href属性的值
					String hrefValue=linfoAhrefEle.attr("href");
					
					//取测试人数-->7472人测试过
					String testNumStr=element.getElementsByClass("total").get(0).text();
					testNumStr=testNumStr.substring(0,testNumStr.indexOf("人"));
					
					//get(0)从第一个元素中获取图片地址
					Element imgEle=element.getElementsByClass("pbox").get(0);
					//也可以通过class=lazyload获取img元素,在获取属性
					String imgPath=imgEle.getElementsByTag("img").get(0).attr("src");
					
					JSONObject itemJson=new JSONObject();
					//拼装成要存入数据库中的json
					itemJson.put("name", title);
					itemJson.put("testedNum", testNumStr);
					itemJson.put("imgPath", imgPath);
					//http://www.xinli001.com/ceshi/99897649 截取columid
					itemJson.put("outId", hrefValue.substring(hrefValue.lastIndexOf("/")+1, hrefValue.length()));
					
					//查询对应的详情页
					JSONObject detailJson=this.detail(itemJson.get("outId")+"");
					if("0".equalsIgnoreCase(detailJson.get("code")+""))
					{
						JSONObject dataDetailJson=detailJson.getJSONObject("data");
						dataDetailJson=dataDetailJson.getJSONObject("detail");
						itemJson.put("content", dataDetailJson.get("detail"));
						itemJson.put("suggNum", dataDetailJson.get("commentNum"));
						itemJson.put("questionNum", dataDetailJson.get("totalQuest"));
						itemJson.put("favNum", dataDetailJson.get("favNum"));
					}
					//将itemJson存入到itemsArr中,转成json数组形式的数据
					itemsArr.add(itemJson);
					count++;
				} 
				dataJson.put("itemsList", itemsArr);
			}
			//存储分页信息
			Element pageEle=document.getElementById("pages");
			//存储对应所有的超链接信息
			Elements pageListEle=pageEle.getElementsByTag("a");
			
			String totalPage="0";
			//从最后一条开始遍历,先对集合进行逆序操作
			Collections.reverse(pageListEle);
			for (Iterator iterator = pageListEle.iterator(); iterator.hasNext();)
			{
				Element element = (Element) iterator.next();
				//依次得到分页的字符串
				String pageStr=element.text();
				//判断最后一个是否数字,若是则马上退出
				if(pageStr.matches("\\d+"))
				{
					totalPage=pageStr;
					break;
				}
			}
			
			dataJson.put("totalPage", totalPage);
			//存储分页栏目
			dataJson.put("columnId", columnId);
			resultJson.put("data", dataJson);
			resultJson.put("code", "0");
			resultJson.put("info", "获取成功");
		} catch (IOException e)
		{
			resultJson.put("code","1");
			resultJson.put("info", "获取失败;"+e);
			ConstantFinalUtil.LoggerMsg.error("-从壹心理获取栏目信息失败-",e);
		}
		return resultJson;
	}
	
}
