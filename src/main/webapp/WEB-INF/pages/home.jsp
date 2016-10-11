<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Home page</title>
<%@include file="includes/commoncss.jsp" %>
</head>
<body>	
	<%@include file="includes/header.jsp" %>
	<h1><spring:message code="page.home.title" /></h1>
	<h3><spring:message code="${messageCode}" text="" /></h3>	
	<p>		
		<br />
		<br />
		<a href="${pageContext.request.contextPath}/organization/add.html"><spring:message code="link.addOrganization" /></a><br />
		<a href="${pageContext.request.contextPath}/organization/list.html"><spring:message code="link.listOrganization" /></a><br /> 
		<a href="${pageContext.request.contextPath}/team/add.html"><spring:message code="link.addTeam" /></a><br />
		<a href="${pageContext.request.contextPath}/team/list.html"><spring:message code="link.listTeam" /></a><br />
		<a href="${pageContext.request.contextPath}/member/add.html"><spring:message code="link.addMember" /></a><br />
		<a href="${pageContext.request.contextPath}/member/list.html"><spring:message code="link.listMember" /></a><br />
	</p>
	<%@include file="includes/commonjs.jsp" %>	 
</body>
</html>