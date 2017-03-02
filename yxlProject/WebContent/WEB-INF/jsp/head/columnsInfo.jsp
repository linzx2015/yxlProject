<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include/CommonPage.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>${requestScope.items.name} - 壹心理</title>
		<%@ include file="/common/include/title.jsp" %>
		<style>
			.mleft .qcShareQQDiv {
				position: inherit;
				margin-left: 100px;
				margin-top: -38px;
				display: block;
			}
			
			.mleft .btn {
				text-align: center;
				margin-top: 20px;
			}
			
			.mleft .btn a {
				color: #FFFFFF;
				font-size: 14px;
				padding: 10px 50px;
				background: #88BD58;
				border-radius: 3px;
				margin-right: 30px;
				text-decoration: none;
			}
		</style>
	</head>
	<body>
		<%--动态包含可采用jsp:param来取值 ,详情页--%>
		<jsp:include page="/header.html">
			<jsp:param value="${requestScope.column.url }" name="url"/>
		</jsp:include>
		<div class="infos-wrap">
			<div class="fish-left main-left">
				<div class="mbox tshow_index">
					<div class="title bor_bot fixed">
						<p class="items fl" title="心理测试"></p>
						<div class="tinfo fl">
							<h2>${requestScope.items.name }</h2>
							<span class="icons" title="测试人数"></span>${requestScope.items.testedNum }人测试过 &nbsp; 
							<span class="icons fav" title="收藏次数"></span>${requestScope.items.favNum }次收藏 &nbsp; 
							<span class="icons tdates" title="发布时间"></span><fmt:formatDate value="${requestScope.items.pubTime }" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp; 
							<span class="icons comments" title="评论数"></span><a href="#comments">${requestScope.items.suggNum }条评论</a> 
							<span class="total">本测试共 <span class="fgreen fb">53</span> &nbsp;题</span>
						</div>
					</div>
					<div class="tshow bor_top">
						<p class="pbox">
							<img src="${requestScope.items.imgPath }"
								width="400" alt="${requestScope.items.name }" title="${requestScope.items.name }">
						</p>
						<p class="tdesc">
							${requestScope.items.content }
						</p>
	
						<p class="tbtns">
							<a href="${rootPath}/columns/${requestScope.items.id}/start.html" class="test_btn" target="_self">开始测试</a>
						</p>
					</div>
				</div>
				<!--测试显示首页-->
				<div class="shares nshares">
					<span class="icons fav"></span> <a
						href="http://www.xinli001.com/ceshi/99897646/favorite"
						class="add_fav" onclick="return favorite(this)">喜欢？！收藏</a>
					<div class="bdsharebuttonbox sbar">
						<a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
						<a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
						<a href="#" class="bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a> <a
							href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a> <a
							href="#" class="bds_douban" data-cmd="douban" title="分享到豆瓣网"></a>
						<a class="bds_count" data-cmd="count"></a> <a href="#"
							class="bds_more" data-cmd="more"></a>
					</div>
					<script>
						window._bd_share_config = {
							"common" : {
								"bdSnsKey" : {},
								"bdText" : "",
								"bdMini" : "1",
								"bdMiniList" : false,
								"bdPic" : "",
								"bdStyle" : "0",
								"bdSize" : "24"
							},
							"share" : {}
						};
						with (document)
							0[(getElementsByTagName('head')[0] || body)
									.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89343201.js?cdnversion='
									+ ~(-new Date() / 36e5)];
					</script>
				</div>
	
				<!-- 排序顺序：按照发布时间 和 id 倒着排 
					上一篇: 大于  当前项的发布时间
					下一篇: 小于  当前项的发布时间
				-->
				<div class="prevnext">
				  <span class="fgrey">上一篇：</span>
				    <a href="${rootPath}/columns/${requestScope.preItem.id}.html" class="prev" title="${requestScope.preItem.name}">${requestScope.preItem.name}</a> 
				  <span class="r">
				  <span class="fgrey">下一篇：</span>
				    <a href="${rootPath}/columns/${requestScope.nextItem.id}.html" class="next" title="${requestScope.nextItem.name}">${requestScope.nextItem.name}</a>
				  </span>
				</div>
	
				<div class="mbox comments_show" id="comments_area">
					<h2>
						发表留言 ：<a name="comments"></a>
					</h2>
					<form id="id_comment_form" class="comments_form"
						style="display: none"
						action="http://www.xinli001.com/ceshi/99897646/comment"
						method="post" onsubmit="return false">
						<textarea id="id_comment_content" class="txt" name="content"
							nullmsg="请输入留言内容"></textarea>
						<p class="cbtns">
							<span id="id_comment_content_msg" class="inputmsg"></span> <input
								id="id_comment_submit" type="submit" class="btns" value="发表留言" />
						</p>
					</form>
				</div>
				<div id="id_comment_page">
					<div class="mbox comments_list fixed" id="comments_list">
						<p id="id_no_comments">
							还没有留言，<a href="#comments">抢沙发</a>
						</p>
						<!--items-->
					</div>
					<!-- start div为了就是让引擎抓取-->
					<div class="mbox comments_list fixed" id="comments_list"
						style="display: none">
						<p id="id_no_comments">
							还没有留言，<a href="#comments">抢沙发</a>
						</p>
						<!--items-->
					</div>
					<!--end-->
					<!--留言列表-->
	
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
				<!-- 6-->
				<div class="test_rmd_list fixed">
					<div class="st rbor">
						<span class="fb">专业测评推荐 ：</span><span class="more"><a
							href="/ceshi">更多&raquo;</a></span>
					</div>
					<dl>
						<dd>
							<p class="pbox fl">
								<a href="http://www.xinli001.com/ceshi/99897501"> <img
									src="http://image.xinli001.com/20160302/0911095s49p480y18rrqfr.jpg!90x60"
									width="90" height="60" hover="true" alt="人格倾向测评" />
								</a>
							</p>
							<p class="tinfo fl">
								<a href="http://www.xinli001.com/ceshi/99897501">人格倾向测评</a> <span
									class="total"><span class="icons" title="测试人数"></span>1259人测试过</span>
							</p>
						</dd>
						<dd>
							<p class="pbox fl">
								<a href="http://www.xinli001.com/ceshi/99897646"> <img
									src="http://ossimg.xinli001.com/20161230/ea5f758bfe51573a7264c794b3a9c6f1.jpg!90x60"
									width="90" height="60" hover="true" alt="心理内耗指数评估" />
								</a>
							</p>
							<p class="tinfo fl">
								<a href="http://www.xinli001.com/ceshi/99897646">心理内耗指数评估</a> <span
									class="total"><span class="icons" title="测试人数"></span>357人测试过</span>
							</p>
						</dd>
						<dd>
							<p class="pbox fl">
								<a href="http://www.xinli001.com/ceshi/99897631"> <img
									src="http://ossimg.xinli001.com/20161128/75596fb78eab70698b889c525ee2de50.jpg!90x60"
									width="90" height="60" hover="true" alt="印象管理能力评估" />
								</a>
							</p>
							<p class="tinfo fl">
								<a href="http://www.xinli001.com/ceshi/99897631">印象管理能力评估</a> <span
									class="total"><span class="icons" title="测试人数"></span>115人测试过</span>
							</p>
						</dd>
						<dd>
							<p class="pbox fl">
								<a href="http://www.xinli001.com/ceshi/99897499"> <img
									src="http://image.xinli001.com/20160302/093617q6aja2texc98o9ft.jpg!90x60"
									width="90" height="60" hover="true" alt="职业性格测评" />
								</a>
							</p>
							<p class="tinfo fl">
								<a href="http://www.xinli001.com/ceshi/99897499">职业性格测评</a> <span
									class="total"><span class="icons" title="测试人数"></span>4337人测试过</span>
							</p>
						</dd>
						<dd>
							<p class="pbox fl">
								<a href="http://www.xinli001.com/ceshi/99897638"> <img
									src="http://ossimg.xinli001.com/20161212/34a81b38d8046753c94b8495a08436bd.jpg!90x60"
									width="90" height="60" hover="true" alt="心理独立性评估" />
								</a>
							</p>
							<p class="tinfo fl">
								<a href="http://www.xinli001.com/ceshi/99897638">心理独立性评估</a> <span
									class="total"><span class="icons" title="测试人数"></span>644人测试过</span>
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
							href="http://www.xinli001.com/ceshi/tag?name=%E7%88%B1%E6%83%85%E6%B5%8B%E8%AF%95">爱情测试</a></li>
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
	
		<div class="footer">
			<div class="wrap">
				<div class="flinks">
					<a href="http://www.xinli001.com/about">关于我们</a> <a
						href="http://www.xinli001.com/join">加入我们</a> <a
						href="http://www.xinli001.com/contact">联系我们</a> <a
						href="http://www.xinli001.com/copyright">免责声明</a> <a
						href="http://www.xinli001.com/friendlink">友情链接</a> <a
						href="http://www.xinli001.com/sitemap.html">网站地图</a> <a
						href="http://m.xinli001.com/">手机版</a> <a
						href="http://app.xinli001.com/mobile/">手机应用</a>
				</div>
				<p class="copy_right">
					© 2005－2014 &nbsp;xinli001.com &nbsp;All Rights Reserved. &nbsp; <a
						href="http://www.miibeian.gov.cn/" class="copy" rel="nofollow">粤ICP备12051153号</a>&nbsp;
					<a key="549261433b05a3da0fbcb548" logo_size="83x30"
						logo_type="business" href="http://www.anquan.org" target="_blank"><script
							src="http://static.anquan.org/static/outer/js/aq_auth.js"></script></a>
	
				</p>
			</div>
	
		</div>
	
		<div style="display: none">
			<div id="login_popup" class="lpcont">
				<h2>登录壹心理</h2>
				<p class="words">开始心理测试，收听FM，用心理学改变自己！</p>
				<div class="lpbox">
					<div class="lpart">
						<form id="lp_form" action="login-jsonp/" method="post">
							<p class="eml">
								<label for="email">邮箱</label> <input type="text" id="lp_username"
									name="username" class="area">
							</p>
							<p class="psw">
								<label for="password">密码</label> <input type="password"
									id="lp_password" name="password" class="area">
							</p>
							<input id="lp_submit" type="submit" class="log_btn" value="登 录">
							<br> <a href="forgetpwd/" class="forget" target="_blank">忘记密码</a>
							<a href="register/" class="signup" target="_blank">没有账号，去注册＞</a>
						</form>
					</div>
					<div class="rpart">
						<span>使用其它账号登录</span> <a href="qwb/login/" class="qq"
							target="_blank">使用腾讯账号登录</a> <a href="xwb/login/" class="wb"
							target="_blank">使用微博账号登录</a> <a href="renren/login/" class="rr"
							target="_blank">使用人人账号登录</a>
					</div>
					<br style="clear: both">
				</div>
			</div>
		</div>
	
		<div id="background"></div>
	
		<script>
			var userinfoUrl = 'http://www.xinli001.com/userinfo/';
		</script>
	
		<script src="http://lapp.xinli001.com/jsmin/index.min.js"></script>
	
		<script>
			$(function() {
				setFocusSlid('#roll_sels', '#rolls_imgs', 229);
				$.get(headerAuthUserUrl, function(resp) {
					$('#header-auth-user-div').html(resp);
				});
			});
		</script>
	
	
		<script>
			var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
					: " http://");
			//document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fd64469e9d7bdbf03af6f074dffe7f9b5' type='text/javascript'%3E%3C/script%3E"));
			var _bdurl = unescape("%3Cscript src='"
					+ _bdhmProtocol
					+ "hm.baidu.com/h.js%3Fd64469e9d7bdbf03af6f074dffe7f9b5' type='text/javascript'%3E%3C/script%3E");
			document.write(_bdurl);
		</script>
		<script>
			(function(i, s, o, g, r, a, m) {
				i['GoogleAnalyticsObject'] = r;
				i[r] = i[r] || function() {
					(i[r].q = i[r].q || []).push(arguments)
				}, i[r].l = 1 * new Date();
				a = s.createElement(o), m = s.getElementsByTagName(o)[0];
				a.async = 1;
				a.src = g;
				m.parentNode.insertBefore(a, m)
			})(window, document, 'script',
					'//www.google-analytics.com/analytics.js', 'ga');
	
			ga('create', 'UA-74218902-1', 'auto');
			ga('send', 'pageview');
		</script>
	</body>
</html>
<script src="http://lapp.xinli001.com/ceshi/js/jquery.plugin.js"></script>
<script src="http://lapp.xinli001.com/ceshi/js/show.js"></script>
<script>
	var pageUrl = 'http://www.xinli001.com/ceshi/99897646/comment';
	$(function() {
		$('#quoteBox a.close').click(rmCommentReply);
		$('.items .reply').click(addCommentReply);
		$('#id_comment_form').submit(postCommentByAjax);
		$('#id_comment_form').show();
	});
</script>
