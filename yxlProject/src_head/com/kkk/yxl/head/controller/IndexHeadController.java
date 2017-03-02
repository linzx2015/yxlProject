package com.kkk.yxl.head.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kkk.yxl.common.controller.BaseController;
import com.kkk.yxl.common.service.IUserService;
import com.kkk.yxl.common.util.ADataEnumUtil;
import com.kkk.yxl.common.util.ConstantFinalUtil;
import com.kkk.yxl.common.util.PageInfoUtil;
import com.kkk.yxl.question.pojo.AColumns;
import com.kkk.yxl.question.pojo.AItems;

/**
 * 首页控制模块
 * 
 * */
@Controller
public class IndexHeadController extends BaseController
{
	@Resource
	private IUserService<AColumns> columnsService;
	@Resource
	private IUserService<AItems> itemsService;
	
	@RequestMapping("/index")
	public String index()
	{
		ConstantFinalUtil.LoggerMsg.info("--index--");
		return "/head/index";
	}
	
	/**
	 * 栏目页面
	 * @PathVariable REST方式获取url中的参数
	 * */
	@RequestMapping("/{url}")
	public String column(@PathVariable String url,HttpServletRequest req)
	{
		ConstantFinalUtil.LoggerMsg.info("--column--");
		Map<String,Object> paramMap=this.getParamMap();
		//查询对应的栏目
		paramMap.put("url", url);
		AColumns column=this.columnsService.queryObjService(paramMap);
		req.setAttribute("column", column);
		//此封装不好,不通用,后续再修改
		PageInfoUtil pageInfoUtil=this.getPageInfoUtil(req);
		
		//只能查询已经启用的栏目
		paramMap=this.getParamMap();
		paramMap.put("status", ADataEnumUtil.STATUS_ENABLE.getStatus());
		//根据id查询关联的对象
		paramMap.put("columnsId", column.getId());
		List<AItems> itemList=this.itemsService.queryMultiObjService(pageInfoUtil, paramMap);
		req.setAttribute("pageInfoUtil", pageInfoUtil);
		req.setAttribute("itemList", itemList);
		
		//查询人气推荐
		paramMap=this.getParamMap();
		paramMap.put("status", ADataEnumUtil.STATUS_ENABLE.getStatus());
		paramMap.put("columnsId", column.getId());
		paramMap.put("orderby", "testedNumDesc");
		PageInfoUtil hotPageInfoUtil=new PageInfoUtil();
		//分页工具中已经默认设置当前页为1
		hotPageInfoUtil.setPageSize(3);
		List<AItems> hotItemList=this.itemsService.queryMultiObjService(hotPageInfoUtil, paramMap);
		req.setAttribute("hotItemList", hotItemList);
		return "/head/columns";
	}
	
	@RequestMapping("/columns/{itemId}")
	public String columnInfo(@PathVariable String itemId,HttpServletRequest req)
	{
		ConstantFinalUtil.LoggerMsg.info("--columnInfo-itemId{}-",itemId);	
		Map<String,Object> paramMap=this.getParamMap();
		//根据栏目下的详情项id进行查询,传id的话,如果id连续可能会被猜到id为连续,然后直接全部被拉取信息
		paramMap.put("id", Integer.valueOf(itemId));
		AItems items=this.itemsService.queryObjService(paramMap);
		req.setAttribute("items", items);
		
		/**
		 * 取上一篇
		 * ~先按发布时间倒着排序:时间距离现在越近,排名越靠前
		 * 不能想通过拿List最后一条来,因为是分页查询,有可能查出来的数据非常多
		 * */
		PageInfoUtil pageInfoUtil=new PageInfoUtil();
		pageInfoUtil.setPageSize(1);
		
		paramMap=this.getParamMap();
		paramMap.put("status", ADataEnumUtil.STATUS_ENABLE.getStatus());
		paramMap.put("columnsId",items.getColumnsId());
		paramMap.put("pubTimeDa",items.getPubTime());
		paramMap.put("orderby","pubTimeAsc");
		List<AItems> preItemList=this.itemsService.queryMultiObjService(pageInfoUtil, paramMap);
		AItems preItem=null;
		if(preItemList.size()>0)
		{
			//直接取第一个正序的项
			preItem=preItemList.get(0);
		}
		req.setAttribute("preItem", preItem);
		
		/**
		 * 取下一篇
		 * 取比传入时间小的第一项
		 * */
		paramMap=this.getParamMap();
		paramMap.put("status", ADataEnumUtil.STATUS_ENABLE.getStatus());
		paramMap.put("columnsId", items.getColumnsId());
		paramMap.put("pubTimeXiao", items.getPubTime());
		List<AItems> nextItemList=this.itemsService.queryMultiObjService(pageInfoUtil, paramMap);
		AItems nextItem=null;
		if(nextItemList.size()>0)
		{
			nextItem=nextItemList.get(0);
		}
		req.setAttribute("nextItem", nextItem);
		return "/head/columnsInfo";
	}
	
	/**
	 * 问题详情信息
	 * @return 
	 * */
	@RequestMapping("/columns/{itemId}/start")
	public String questionInfo(@PathVariable String itemId,HttpServletRequest req)
	{
		ConstantFinalUtil.LoggerMsg.info("--questionInfo--");
		
		//按照id进行查询
		Map<String,Object> paramMap=this.getParamMap();
		paramMap.put("id", Integer.valueOf(itemId));
		AItems questionItem=this.itemsService.queryObjService(paramMap);
		req.setAttribute("questionItem", questionItem);
		return "/head/questionInfo";
	}
	
	/**
	 * REST方式获取url参数,用于前台columns页面动态包含时显示
	 * 
	 * */
	@RequestMapping("/header")
	public String header(String url,HttpServletRequest req)
	{
		ConstantFinalUtil.LoggerMsg.info("--header--");
		Map<String,Object> paramMap=this.getParamMap();
		if(url!=null && !"".equalsIgnoreCase(url))
		{
			//查询当前栏目的信息
			paramMap.put("url", url);
			AColumns column=this.columnsService.queryObjService(paramMap);
			req.setAttribute("column", column);
		}
		//查询当前所有已启用的栏目信息
		paramMap.clear();
		paramMap.put("status", ADataEnumUtil.STATUS_ENABLE.getStatus());
		List<AColumns> columnList=this.columnsService.queryMultiObjService(null, paramMap);
		req.setAttribute("columnList", columnList);
		//将结果存放到header.jsp页面中进行显示
		return "/head/header";
	}
	
}
