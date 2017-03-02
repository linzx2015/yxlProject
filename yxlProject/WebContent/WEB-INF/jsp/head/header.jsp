<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/include/CommonPage.jsp" %>    
<div class="new-header">
	<div class="wrapper">
		<div class="logo">
			<a href="http://www.xinli001.com/"><img src="http://lapp.xinli001.com/images/website-www/logo.png"/></a>
		</div>
		<div class="nav">
			<ul>
				<li><a href="http://www.xinli001.com/" class="tit">首页</a></li>
				<li><a href="http://www.xinli001.com/info" class="tit">文章</a></li>
				<li><a href="http://qa.xinli001.com/" class="tit">问答</a></li>
				<li><a href="http://www.xinli001.com/zx/" class="tit">咨询</a></li>
				<li class="selected"><a href="${rootPath}/index.html" class="tit isNew">测试<span class="newBrand"></span></a></li>
				<li><a href="http://fm.xinli001.com/" class="tit">心理FM</a></li>
				<li><a href="http://xy.xinli001.com/" class="tit">课程</a></li>
				<li class="droplist">
					<a href="#" class="tit tit-more">更多</a>
					<dl>
						<dd><a href="http://www.xinli001.com/oxygen/">心灵氧吧</a></dd>
						<dd><a href="http://www.xinli001.com/site/">心理圈</a></dd>
						<dd><a href="http://group.xinli001.com/">小组</a></dd>
						<dd><a href="http://app.xinli001.com/">心理应用</a></dd>
						<dd><a href="http://www.xinli001.com/daka/">打卡学习</a></dd>
						<dd><a href="http://www.xinli001.com/dream/">每日一梦</a></dd>
					</dl>
				</li>
			</ul>
		</div>
		<div id="header-auth-user-div">


			<div class="reg">
				<ul>
					<li><a href="http://account.xinli001.com/login/" target="_self">登录</a></li>
					<li><span class="line"></span></li>
					<li><a href="http://account.xinli001.com/register/" target="_self">注册</a></li>
				</ul>
			</div>

		</div>
		<div class="sear">
			<form target="_blank" action="http://so.xinli001.com/cse/search" method="get">
				<!--
				<input type="hidden" name="s" value="11434743956290276987">
				-->
				<input type="hidden" name="s" value="14517920152699641259">
				<input type="hidden" name="nsid" value="0">
				<input type="text" class="text" name="q" placeholder="搜索" />
				<input type="submit" class="submit" value=""/>
			</form>
		</div>
	</div>
</div>
<!-- nav end -->
<div class="header cleara">
	<div class="wrap">
		<div class="lf_header">
			<c:choose>
				<c:when test="${requestScope.column!=null}">
					<a href="http://www.xinli001.com/ceshi" class="headerlogo">${requestScope.column.name}</a>
				</c:when>
				<c:otherwise>
					<a href="${rootPath }/index.htm" class="headerlogo">心理测试</a>
				</c:otherwise>
			</c:choose>
			<ul class="hd_menu infos-menu">
				<c:forEach items="${columnList}" var="mcolumn" varStatus="stat">
					<li class="${mcolumn.flag=='10'?'isNew':''}">
						<a href="${rootPath}/${mcolumn.url}.html">${mcolumn.name }</a>
						<c:if test="${mcolumn.flag=='10'}">
							<span class="newBrand"></span>
						</c:if>
					</li>
				</c:forEach>
			</ul>
				<!-- <li><a href="http://www.xinli001.com/ceshi/amor">爱情测试</a></li>
				<li><a href="http://www.xinli001.com/ceshi/personality">性格测试</a></li>
				<li><a href="http://www.xinli001.com/ceshi/vocational">能力测试</a></li>
				<li><a href="http://www.xinli001.com/ceshi/member">会员测试</a></li>
				<li><a href="http://www.xinli001.com/ceshi/professional">精选测试</a></li> 
				<li class="isNew"><a href="http://www.xinli001.com/ceshi/fufei">专业测试</a>
					</li>-->
			

		</div>
		<div class="rt_header infos-search">
			<form method="get" action="http://so.xinli001.com/cse/search"
				target="_blank">
				<p class="hd_searchBox">
					<input name="q" class="hd_searchText" type="text" maxlength="30"
						placeholder="搜索心理学内容/心理测试/心理圈"> <input type="hidden" name="s"
						value="14517920152699641259"> <input type="hidden" name="nsid"
						value="0"> <input class="hd_search_btn" type="submit" value="搜索">
				</p>
			</form>
		</div>
	</div>
</div>
<!-- header end -->