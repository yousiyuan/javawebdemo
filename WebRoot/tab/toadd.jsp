<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/tab/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加</title>
</head>
<body>
	<form action="${root}/servlet/InsertServlet" method="post" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="3" border="1" width="60%" align="center">
			<tr style="display:none;">
				<th align="left">用户ID</th>
				<td><input type="text" name="userid" /></td>
			</tr>
			<tr>
				<th align="left">用户名</th>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<th align="left">密码</th>
				<td><input type="text" name="password" /></td>
			</tr>
			<tr>
				<th align="left">性别</th>
				<td>
					<label><input type="radio" name="gender" value="1" checked="checked" />男</label>
					<label><input type="radio" name="gender" value="0" />女</label></td>
			</tr>
			<tr>
				<th align="left">生日</th>
				<td><input type="text" name="birthday" /></td>
			</tr>
			<tr>
				<th align="left">地址</th>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<th align="left">余额</th>
				<td><input type="text" name="balance" /></td>
			</tr>
			<tr style="display:none;">
				<th align="left">创建日期</th>
				<td><input type="text" name="createtime" /></td>
			</tr>
			<tr style="display:none;">
				<th align="left">状态</th>
				<td><input type="text" name="status" /></td>
			</tr>
			<tr>
				<th align="left">头像</th>
				<td><input type="file" name="fileupload" value="" /></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="提交" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
