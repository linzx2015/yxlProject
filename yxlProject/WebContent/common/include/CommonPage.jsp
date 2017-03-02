<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- 设置项目根路径 -->
<c:set value="${pageContext.request.contextPath }" var="rootPath"/>
<!-- 设置当前时间 -->
<jsp:useBean id="current_Time" class="java.util.Date"/>