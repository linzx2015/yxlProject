<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/include/CommonPage.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>${requestScope.questionItem.name} - 壹心理</title>
		<%@ include file="/common/include/title.jsp" %>
		<script type="text/javascript">
			var count = 1; 
			var totalCount = '${requestScope.questionItem.questionNum}';
			$(function()
			{
				/* 测试页面一加载就弹框 */
				//alert("-----");
				//alert($(".test_contents .sels input").length);
				/* 取所有class="test_contents" 下面的class="sels" 下面的input标签
					其实就是单选框*/
				var inputs = $(".test_contents .sels input") ;
				/* 为所有的单选框加一个单击事件 */
				inputs.click(function()
				{
					/*
						测试一下,单选框的单击事件添加成功,练习一下Jquery的操作
						$(this)当前对象(dom元素)的jquery对象
						$(this).parent().next("label").html():
						取到单选框右边的文字
						html();text();取的都是开始标签和结束标签中间的内容
						html():包含文本和标签
						text():只包含文本
					*/
					//alert('----' + $(this).parent().next("label").html());
						
					/* 点击radio,所在的问题隐藏,下一个显示 */
					//alert($("#question_"+ questionId).nextAll(".test_contents").length)
					$(".test_contents").hide();
					//alert($(this).parent(".test_contents").html());
					//alert($(this).attr("question"));
					var questionId = $(this).attr("question") ; 
					//alert($("#question_"+ questionId).html());
					$("#question_"+ questionId).next(".test_contents").show();
					/* 如果是最后一题,显示提交 */
					//alert($("#question_"+ questionId).nextAll(".test_contents").length);
					//alert("count+=====" + count)
					//if(count == (totalCount - 1))
					if($("#question_"+ questionId).nextAll(".test_contents").length == 1)
					{
						$("#id_show_result").show();
					}
					
					/* 上一题 */
					if(count >= 1 )
					{
						$("#id_pre_link").show();
					}
					/* 让当前的选项卡选中 */
					//$(this).checked();
					count ++ ; 
				});
			});
		</script>
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
							<h2>${requestScope.questionItem.name }</h2>
							<span class="icons" title="测试人数"></span>${requestScope.questionItem.testedNum }人测试过 &nbsp; 
							<span class="icons fav" title="收藏次数"></span>${requestScope.questionItem.favNum }次收藏 &nbsp; 
							<span class="icons tdates" title="发布时间"></span><fmt:formatDate value="${requestScope.questionItem.pubTime }" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp; 
							<span class="icons comments" title="评论数"></span><a href="#comments">${requestScope.questionItem.suggNum }条评论</a> 
							<span class="total">本测试共 <span class="fgreen fb">${requestScope.questionItem.questionNum}</span> &nbsp;题</span>
						</div>
					</div>
					<div class="ctest bor_top">
						当前第 <span class="fgreen fb" id="id_cur_index">1</span> &nbsp;题 <span
							class="total">本测试共 <span class="fgreen fb">${requestScope.questionItem.questionNum }</span> &nbsp;题
						</span>
					</div>
				</div>
	
				<form id='id_form' action="http://www.xinli001.com/ceshi/99897639/start" method="post">
					<c:forEach items="${requestScope.questionItem.questionList }" var="question" varStatus="stat">
						<!-- 每一个问题 -->
						<div id="question_${question.id }" class="test_contents" style="display:${stat.count == 1 ? '' : 'none'}">
							<p class="descs fb">${stat.count }. ${question.name }</p>
							<div class="sels_list">
								<c:forEach items="${question.answerList }" var="answer" varStatus="stat">
									<!--单选测试-->
									<div class="items">
										<p class="i_top"></p>
										<p class="i_mid">
											<span class="sels">
												<input id="ans_${answer.id }" type="radio" name="question-1" value="99996219" question="${question.id }"/>
											</span>
											<label for="ans_${answer.id }">
												${answer.name}
											</label>
										</p>
										<p class="i_bot"></p>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:forEach>
					<input type="hidden" name="choice" id="id_choice" />
					<p class="tbtns">
						<a id="id_pre_link" style="display: none" href="javascript:void(0)"
							class="test_btn" onclick="return do_prev()">上一题</a> &nbsp;&nbsp; 
						<input id="id_show_result" style="display: none" type="submit" class="btns" value="提 交" />
					</p>
				</form>
				<!--测试内容-->
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
				<!-- 2-->
				<div class="test_rmd_list fixed">
					<div class="st rbor">
						<span class="fb">性格测试推荐 ：</span><span class="more"><a
							href="/ceshi">更多&raquo;</a></span>
					</div>
					<dl>
						<dd>
							<p class="pbox fl">
								<a href="http://www.xinli001.com/ceshi/350"> <img
									src="http://ossimg.xinli001.com/20170109/8f87a0714269c45b38bc4319f0bf0692.jpg!90x60"
									width="90" height="60" hover="true" alt="你是五大天使中的哪个？" />
								</a>
							</p>
							<p class="tinfo fl">
								<a href="http://www.xinli001.com/ceshi/350">你是五大天使中的哪个？</a> <span
									class="total"><span class="icons" title="测试人数"></span>24579人测试过</span>
							</p>
						</dd>
						<dd>
							<p class="pbox fl">
								<a href="http://www.xinli001.com/ceshi/99897650"> <img
									src="http://ossimg.xinli001.com/20170105/ac7d3f456ba93ea38004d002d27a74e6.jpg!90x60"
									width="90" height="60" hover="true" alt="测试：你是阴阳师中的谁？" />
								</a>
							</p>
							<p class="tinfo fl">
								<a href="http://www.xinli001.com/ceshi/99897650">测试：你是阴阳师中的谁？</a>
								<span class="total"><span class="icons" title="测试人数"></span>6735人测试过</span>
							</p>
						</dd>
						<dd>
							<p class="pbox fl">
								<a href="http://www.xinli001.com/ceshi/657"> <img
									src="http://ossimg.xinli001.com/20170104/62e4e1ea495e47ad08dd00a0f6c6f23c.jpg!90x60"
									width="90" height="60" hover="true" alt="拯救偶像透露你的性格" />
								</a>
							</p>
							<p class="tinfo fl">
								<a href="http://www.xinli001.com/ceshi/657">拯救偶像透露你的性格</a> <span
									class="total"><span class="icons" title="测试人数"></span>32697人测试过</span>
							</p>
						</dd>
						<dd>
							<p class="pbox fl">
								<a href="http://www.xinli001.com/ceshi/99897648"> <img
									src="http://ossimg.xinli001.com/20161230/a92ee1d868f962a0e7c464e5b9497387.jpg!90x60"
									width="90" height="60" hover="true" alt="从食物喜好测你的性格是什么？" />
								</a>
							</p>
							<p class="tinfo fl">
								<a href="http://www.xinli001.com/ceshi/99897648">从食物喜好测你的性格是什么？</a>
								<span class="total"><span class="icons" title="测试人数"></span>21933人测试过</span>
							</p>
						</dd>
						<dd>
							<p class="pbox fl">
								<a href="http://www.xinli001.com/ceshi/99897543"> <img
									src="http://ossimg.xinli001.com/20161229/a27159970e70756c6b7563a137d7a694.jpg!90x60"
									width="90" height="60" hover="true" alt="你最在意别人说你什么？" />
								</a>
							</p>
							<p class="tinfo fl">
								<a href="http://www.xinli001.com/ceshi/99897543">你最在意别人说你什么？</a>
								<span class="total"><span class="icons" title="测试人数"></span>118189人测试过</span>
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
		<%@ include file="/common/include/footer.jsp" %>
	</body>
</html>