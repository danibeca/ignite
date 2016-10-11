<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>List of members</title>
<%@include file="includes/commoncss.jsp" %>
</head>
<body>
	<%@include file="includes/header.jsp" %>
	<h1><spring:message code="page.listMember.title" /></h1>
	<p><spring:message code="page.listMember.description" /></p>
	<table border="1px" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th width="5%"><spring:message code="label.id" /></th>
				<th width="15%"><spring:message code="label.name" /></th>				
				<th width="15%"><spring:message code="label.teams" /></th>
				<th width="10%"><spring:message code="label.actions" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="${members}">
				<tr>
					<td class="memberId">${member.id}</td>
					<td class="memberName">${member.name}</td>
					<td class="memberTeams">
						<c:forEach var="team" items="${member.teams}">
							${team.name}<br/>
						</c:forEach>
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/member/edit/${member.id}.html">Edit</a><br />
						<a href="${pageContext.request.contextPath}/member/delete/${member.id}.html">Delete</a><br />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="${pageContext.request.contextPath}/index.html"><spring:message code="link.home" /></a>
	</p>
	<%@include file="includes/commonjs.jsp" %>

</body>
</html>