<%@page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.JDBCType"%>
<%@ page import="org.lnson.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/tab/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>编辑</title>
</head>
<body>
	<form action="${root}/servlet/UpdateServlet" method="post" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="3" border="1" width="60%" align="center">
			<tr>
				<th align="left">用户ID</th>
				<td><input type="text" name="userid" readonly="readonly" value="${UserInfo.userId}" /></td>
			</tr>
			<tr>
				<th align="left">用户名</th>
				<td><input type="text" name="username" value="${UserInfo.userName}" /></td>
			</tr>
			<tr>
				<th align="left">上传头像</th>
				<td><input type="file" name="fileupload" value="" /></td>
			</tr>
			<tr>
				<th align="left">密码</th>
				<td><input type="text" name="password" value="${UserInfo.passWord}" /></td>
			</tr>
			<tr>
				<th align="left">性别</th>
				<td>
					<label><input type="radio" name="gender" value="1" <c:if test="${UserInfo.gender==1}">checked='checked'</c:if> />男</label>
					<label><input type="radio" name="gender" value="0" <c:if test="${UserInfo.gender==0}">checked='checked'</c:if> />女</label></td>
			</tr>
			<tr>
				<th align="left">生日</th>
				<td><input type="text" name="birthday" value="${UserInfo.birthday}" /></td>
			</tr>
			<tr>
				<th align="left">地址</th>
				<td><input type="text" name="address" value="${UserInfo.address}" /></td>
			</tr>
			<tr>
				<th align="left">余额</th>
				<td><input type="text" name="balance" value="<fmt:formatNumber value="${UserInfo.balance}" pattern="#.00"></fmt:formatNumber>" /></td>
			</tr>
			<tr>
				<th align="left">创建日期</th>
				<td><input type="text" name="createtime" value="${UserInfo.createTime}" readonly="readonly" /></td>
			</tr>
			<tr>
				<th align="left">状态</th>
				<td><input type="text" name="status" value="<c:if test="${UserInfo.status==1}">有效</c:if><c:if test="${UserInfo.status==0}">无效</c:if>" readonly="readonly" /></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="提交" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
