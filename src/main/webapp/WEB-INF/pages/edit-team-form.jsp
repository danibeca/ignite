<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<?xml version="1.0" encoding="ISO-8859-1" ?>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Edit team page</title>
<%@include file="includes/commoncss.jsp" %>
</head>
<body>
	<%@include file="includes/header.jsp" %>
	<h1><spring:message code="page.editTeam.title" /></h1>
	<p><spring:message code="page.editTeam.description" /></p>	
	<form:form method="POST" commandName="team"	action="${pageContext.request.contextPath}/team/edit/${team.id}.html">
		<table>
			<tbody>
				<tr>
					<td><spring:message code="label.name" /></td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.rating" /></td>
					<td><form:input path="rating" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.organization" /></td>
					<td><form:select path="organization">
							<c:forEach var="org" items="${organizations}">
								<c:choose>
									<c:when	test="${team.organization.id == org.id}">
										<form:option selected="true" value="${org.id}" label="${org.name}" />
									</c:when>
									<c:otherwise>
										<form:option value="${org.id}" label="${org.name}" />
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</form:select>
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="Edit" name="btnEdit"/></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</form:form>

	<p>
		<a href="${pageContext.request.contextPath}/index.html"><spring:message code="link.home" /></a>
	</p>
	<%@include file="includes/commonjs.jsp" %>
</body>
</html>