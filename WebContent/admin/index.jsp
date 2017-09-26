<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>ItcastShop 管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- <引入样式 Bootstrap ${pageContext.request.contextPath } -->
    <link href="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
	<s:actionerror />
	<form method="post"
		action="${pageContext.request.contextPath }/adminUser_login.action"
		name='theForm'>
		<table cellspacing="0" cellpadding="0" class="table table-hover">
			<tr>
				<td><img src="${pageContext.request.contextPath }/images/login.png" width="178" height="256"
					border="0" alt="SHOP" /></td>
				<td style="padding-left: 50px">
					<table class="table table-hover">
						<tr>
							<td>管理员姓名：</td>
							<td><input type="text" name="username"  value="" /></td>
						</tr>
						<tr>
							<td>管理员密码：</td>
							<td><input type="password" name="password" value="" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" value="进入管理中心" class="btn btn-default" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	
	<!-- 引入jquery  static/js/jquery-1.12.4.min.js -->
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.12.4.min.js" ></script>
	<!-- 引入js Bootstrap -->
	<script src="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	
</body>
</html>