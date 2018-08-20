<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/tab/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>

<body>
	<div style="padding:0 0 6px 0;">
		<a href="<c:out value="${root}"></c:out>/tab/toadd.jsp" target="_blank">添加</a>
	</div>
	<table cellspacing="1" cellpadding="3" border="1" width="100%">
		<thead>
			<tr>
				<th>用户ID</th>
				<th>用户名</th>
				<th>头像</th>
				<th>密码</th>
				<th>性别</th>
				<th>生日</th>
				<th>地址</th>
				<th>余额</th>
				<th>创建日期</th>
				<th>状态</th>
				<th>编辑</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="item">
			<tr>
				<td>${item.userId}</td>
				<td>${item.userName}</td>
				<td><c:if test="${empty item.picture}">
						<label>查看头像</label>
					</c:if>
					<c:if test="${not empty item.picture}">
						<a href="${fileroot}${item.picture}" target="_blank">查看头像</a>
					</c:if></td>
				<td>${item.passWord}</td>
				<td>
					<c:if test="${item.gender==1}">男</c:if>
					<c:if test="${item.gender==0}">女</c:if>
				</td>
				<td>${item.birthday}</td>
				<td>${item.address}</td>
				<td><fmt:formatNumber value="${item.balance}" pattern="#,###.00"></fmt:formatNumber></td>
				<td>${item.createTime}</td>
				<td>
					<c:if test="${item.status==1}">有效</c:if>
					<c:if test="${item.status==0}">无效</c:if>
				</td>
				<td align="center">
					<a href="<c:out value="${root}"></c:out>/servlet/UpdateServlet?id=${item.userId}" target="_blank">编辑</a>&nbsp;&nbsp;
					<a href="<c:out value="${root}"></c:out>/servlet/DeleteServlet?id=${item.userId}" target="_blank">删除</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>