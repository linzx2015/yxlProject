<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/include/CommonPage.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>${requestScope.column.name }题_大全 - kkk-壹心理</title>
		<%@ include file="/common/include/title.jsp" %>
	</head>
	<body>
<%-- 动态包含采用jsp:param来取值 --%>
<jsp:include page="/header.html">
	<jsp:param value="${requestScope.column.url }" name="url"/>
</jsp:include>
<div class="infos-wrap">
	<div class="fish-left main-left">
	  <div class="list_rmd">
		<div class="title bor_bot">${requestScope.column.name }人气推荐</div>
		<div class="lists bor_top fixed">
		  <ul>
			<c:forEach items="${requestScope.hotItemList}" var="item" varStatus="stat">
				<li class="fl payTest">
				  <a href="${rootPath}/columns/${item.id}.html"> 
				    <img src="${item.imgPath}"
						width="180" height="120" hover="true" alt="${item.name}" title="${item.name}">
					<p>${item.name}</p>
				  </a>
				</li>
			</c:forEach>
		  </ul>
		 </div>
		</div>
		<!--推荐-->
		<h2 class="list_title">${requestScope.column.name }：</h2>
		<div class="list_show">
		<dl>
			<c:forEach items="${requestScope.itemList}" var="item" varStatus="stat">
			<dd class="fixed">
				<p class="pbox fl">
					<a href="${rootPath}" target="_blank"> 
					<img class="lazyload"
						src="${item.imgPath}"
						width="120" height="80" alt="测测今年的桃花运：什么时候遇到对的人" hover="true" />
					</a>
				</p>
				<p class="linfo fl">
					<a href="${rootPath}/columns/${item.id}.html" target="_blank">${item.name}</a><br />
					  <c:choose>
					  	<c:when test="${fn:length(item.content) > 100}">
							${fn:substring(item.content,0,100)}....<br />
						</c:when>
						<c:otherwise>
							${item.content }
						</c:otherwise>
					  </c:choose>
					 <span class="total"><span class="icons" title="测试人数"></span>${item.testedNum}人测试过</span>
				</p>
			</dd>
			</c:forEach>
		</dl>
		</div>
		<!--列表-->
		<!-- 当页数大于1并且小于10页时,页数全部显示-->
		<!-- 判断是不是当前页,为是不现实当前页,则不显示链接 -->
		<!-- 当大于10页时,则只显示部分页  1 2 3 4 5 ... 倒数4页 -->
		<!-- 先显示前5页 -->
		<!-- 中间以 ... 分隔开 -->
		<!-- 显示倒数4页 -->
		<div class="pagebar" id="pages">
			<%-- <c:if test="${requestScope.pageInfoUtil.currentPage>1}">
				<a href="${rootPath}/${requestScope.column.url}.html?page=${requestScope.pageInfoUtil.prePage}" style="display:bolck;" class="next" target="_self">上一页</a>
			</c:if>
			<c:if test="${requestScope.pageInfoUtil.currentPage<=1}">
				<a href="${rootPath}/${requestScope.column.url}.html?page=${requestScope.pageInfoUtil.prePage}" style="display:none" class="next" target="_self">上一页</a>
			</c:if> --%>
			<a href="${rootPath}/${requestScope.column.url}.html?page=${requestScope.pageInfoUtil.prePage}" 
				style="display:${requestScope.pageInfoUtil.currentPage>1?';':'none'}" class="next" target="_self">上一页</a>
			<c:choose>
			<c:when test="${requestScope.pageInfoUtil.totalPage>=1 && requestScope.pageInfoUtil.totalPage<=10}">
				<c:forEach begin="1" end="${requestScope.pageInfoUtil.totalPage-1}" step="1" var="currPage">
				  <c:choose>
				     <c:when test="${requestScope.pageInfoUtil.currentPage==currPage}">
						${currPage}
					</c:when>
					<c:otherwise>
						<a href="${rootPath}/${requestScope.column.url}.html?page=${currPage}" 
							target="_self">${currPage}</a>|
					</c:otherwise>
				  </c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach begin="1" end="7" step="1" var="currPage">
				 <c:choose>
					 <c:when test="${requestScope.pageInfoUtil.currentPage==currPage}">
						${currPage}|
					</c:when>
					<c:otherwise>
						<a href="${rootPath}/${requestScope.column.url}.html?page=${currPage}">${currPage }</a>|
					</c:otherwise>
				 </c:choose>
				</c:forEach>	
				&nbsp;&nbsp;&nbsp;...&nbsp;&nbsp;&nbsp;|
				<c:forEach begin="${requestScope.pageInfoUtil.totalPage-2}" end="${requestScope.pageInfoUtil.totalPage-1}" step="1" var="currPage">
					<c:choose>
						<c:when test="${requestScope.pageInfoUtil.currentPage==currPage}">
							${currPage}|
						</c:when>
						<c:otherwise>
							<a href="${rootPath}/${requestScope.column.url}.html?page=${currPage}">${currPage }</a>|
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>		
			</c:choose>
			<a href="${rootPath}/${requestScope.column.url}.html?page=${requestScope.pageInfoUtil.totalPage}" target="_self">${requestScope.pageInfoUtil.totalPage}</a>
			<a href="${rootPath}/${requestScope.column.url}.html?page=${requestScope.pageInfoUtil.nextPage}" class="next" style="display:${requestScope.pageInfoUtil.currentPage>=requestScope.pageInfoUtil.totalPage?'none':''}" target="_self">下一页</a>
			<%--下一页加上此代码会换行显示  --%>
		</div>
	</div>
	<!--left-->
	<div class="fish-left main-right">
		<div class="multi_ads fixed">
			<div class="mt">
				<strong>编辑推荐<span class="mq">：</span><span class="ms">······
				</span></strong>
			</div>
			<dl>
				<dd>
					<a href="http://www.xinli001.com/info/100360756?from=cheshi"
						title="心探社" target="_blank"> <img
						src="http://ossimg.xinli001.com/20161219/22c35b0aef597efe978b7a084dcf3030.jpg"
						width="135" height="135" hover="true" title="心探社" alt="心探社">
					</a>
				</dd>

				<dd>
					<a href="http://www.xinli001.com/ceshi/99897526" title="心理需求测评"
						target="_blank"> <img
						src="http://image.xinli001.com/20160805/030954q4tnzb0jb0nyq06s.jpg"
						width="135" height="135" hover="true" title="心理需求测评" alt="心理需求测评">
					</a>
				</dd>

				<dt>
					<a href="http://www.xinli001.com/ceshi/99897572?from=ceshi"
						title="抑郁症测试" target="_blank"> <img
						src="http://ossimg.xinli001.com/20161024/16f19e49dab19387649e80eae35df090.jpg"
						width="280" height="135" hover="true" title="抑郁症测试" alt="抑郁症测试">
					</a>
				</dt>
				<br class="clear">
			</dl>
		</div>
		<!-- 1-->
		<div class="test_rmd_list fixed">
			<div class="st rbor">
				<span class="fb">${requestScope.column.name }推荐 ：</span><span class="more"><a
					href="/ceshi">更多&raquo;</a></span>
			</div>
			<dl>
				<dd>
					<p class="pbox fl">
						<a href="http://www.xinli001.com/ceshi/99897649"> <img
							src="http://ossimg.xinli001.com/20170103/d5cb5f660ac54bce83af3905ea51d7e5.jpg!90x60"
							width="90" height="60" hover="true" alt="测测今年的桃花运：什么时候遇到对的人" />
						</a>
					</p>
					<p class="tinfo fl">
						<a href="http://www.xinli001.com/ceshi/99897649">测测今年的桃花运：什么时候遇到对的人</a>
						<span class="total"><span class="icons" title="测试人数"></span>4188人测试过</span>
					</p>
				</dd>
				<dd>
					<p class="pbox fl">
						<a href="http://www.xinli001.com/ceshi/99897642"> <img
							src="http://ossimg.xinli001.com/20161226/4a27832cb7f3b1b6982a4ad4cfce3e5e.jpg!90x60"
							width="90" height="60" hover="true" alt="测你的爱情像蜗牛一样慢热吗？" />
						</a>
					</p>
					<p class="tinfo fl">
						<a href="http://www.xinli001.com/ceshi/99897642">测你的爱情像蜗牛一样慢热吗？</a>
						<span class="total"><span class="icons" title="测试人数"></span>9495人测试过</span>
					</p>
				</dd>
				<dd>
					<p class="pbox fl">
						<a href="http://www.xinli001.com/ceshi/99897640"> <img
							src="http://ossimg.xinli001.com/20161220/0a840aa96a8246a1911380690de0fde4.jpg!90x60"
							width="90" height="60" hover="true" alt="测测现在的你适合谈恋爱吗？" />
						</a>
					</p>
					<p class="tinfo fl">
						<a href="http://www.xinli001.com/ceshi/99897640">测测现在的你适合谈恋爱吗？</a>
						<span class="total"><span class="icons" title="测试人数"></span>28793人测试过</span>
					</p>
				</dd>
				<dd>
					<p class="pbox fl">
						<a href="http://www.xinli001.com/ceshi/568"> <img
							src="http://ossimg.xinli001.com/20161216/dcbbe25a34f791481236a4be25d05340.jpg!90x60"
							width="90" height="60" hover="true" alt="谁是打开你心锁的人？ " />
						</a>
					</p>
					<p class="tinfo fl">
						<a href="http://www.xinli001.com/ceshi/568">谁是打开你心锁的人？ </a> <span
							class="total"><span class="icons" title="测试人数"></span>166308人测试过</span>
					</p>
				</dd>
				<dd>
					<p class="pbox fl">
						<a href="http://www.xinli001.com/ceshi/749"> <img
							src="http://image.xinli001.com/20161201/03102993q1gz8lxx2fgo3x.jpg!90x60"
							width="90" height="60" hover="true" alt="你是哪个童话故事的主角？" />
						</a>
					</p>
					<p class="tinfo fl">
						<a href="http://www.xinli001.com/ceshi/749">你是哪个童话故事的主角？</a> <span
							class="total"><span class="icons" title="测试人数"></span>114898人测试过</span>
					</p>
				</dd>
			</dl>
		</div>
		<div class="tags fixed">
			<div class="st rbor">
				<span class="fb">热门标签 ：</span>
			</div>
			<ul>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E6%80%A7%E6%A0%BC%E6%B5%8B%E8%AF%95">性格测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E7%88%B1%E6%83%85%E6%B5%8B%E8%AF%95">${requestScope.column.name }</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E8%83%BD%E5%8A%9B%E6%B5%8B%E8%AF%95">能力测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E5%BF%83%E7%90%86%E6%B5%8B%E8%AF%95">心理测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E8%B6%A3%E5%91%B3%E6%B5%8B%E8%AF%95">趣味测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E4%B8%93%E4%B8%9A%E6%B5%8B%E8%AF%95">专业测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E5%A7%BB%E7%BC%98%E6%B5%8B%E8%AF%95">姻缘测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E6%B5%8B%E8%AF%95%E7%88%B1%E6%83%85">测试爱情</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E8%81%8C%E4%B8%9A%E6%B5%8B%E8%AF%95">职业测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E9%A2%84%E8%A8%80%E6%B5%8B%E8%AF%95">预言测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E4%BC%9A%E5%91%98%E6%B5%8B%E8%AF%95">会员测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E7%88%B1%E6%83%85">爱情</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E7%BF%BB%E8%AF%91%E6%B5%8B%E8%AF%95">翻译测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E4%B8%93%E4%B8%9A%E9%87%8F%E8%A1%A8">专业量表</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E6%BD%9C%E6%84%8F%E8%AF%86%E6%B5%8B%E8%AF%95">潜意识测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E5%A4%96%E5%9B%BD%E6%B5%8B%E8%AF%95">外国测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E6%80%A7%E6%A0%BC">性格</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E8%81%8C%E5%9C%BA%E6%B5%8B%E8%AF%95">职场测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E5%81%A5%E5%BA%B7%E6%B5%8B%E8%AF%95">健康测试</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E5%BF%83%E7%90%86%E5%81%A5%E5%BA%B7">心理健康</a></li>
				<li class="fl"><a
					href="http://www.xinli001.com/ceshi/tag?name=%E6%81%8B%E7%88%B1">恋爱</a></li>
			</ul>
		</div>
		<!--标签-->
	</div>
	<!--right-->
</div>
		<%@ include file="/common/include/footer.jsp" %>
	</body>
</html>