<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>会员注册 - Powered By Mango Team</title>
	<link href="${pageContext.request.contextPath}/css/common.css"	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/register.css"	rel="stylesheet" type="text/css" />
	
</head>
<body>
	<div class="container header">
		<!-- 引入头部左面  -->
		<%@ include file="home/header_left.jsp"%>
	
		<!-- 引入header.jsp -->
		<jsp:include page="home/header.jsp" />
		<!-- 引入menu.jsp -->
		<jsp:include page="home/menu.jsp" />
	</div>	
	
	<div class="container register">
		<div class="span24">
			<div class="wrap">
				<div class="main clearfix">
					<div class="title">
						<strong>会员注册</strong>USER REGISTER
					</div>
					
					<form id="registerForm"
						action="${pageContext.request.contextPath }/user_regist.action"
						method="post" novalidate="novalidate" >
						<table>
							<tbody>
								<tr>
									<th><span class="requiredField">*</span>用户名:</th>
									<td><input type="text" id="username" name="username" value="<s:property value="username" />" class="text"  maxlength="20"  />
										<span id="span1"><s:fielderror fieldName="username"></s:fielderror> </span></td>
								</tr>
								<tr>
									<th><span class="requiredField">*</span>密&nbsp;&nbsp;码:</th>
									<td><input type="password" id="password" name="password" value="<s:property value="password" />" class="text" maxlength="20" autocomplete="off" />
										<span><s:fielderror fieldName="password"></s:fielderror> </span></td>
								</tr>
								<tr>
									<th><span class="requiredField">*</span>确认密码:</th>
									<td><input type="password" id="repassword"	name="repassword" class="text" maxlength="20" autocomplete="off" />
									</td>
								</tr>
								<tr>
									<th><span class="requiredField">*</span>E-mail:</th>
									<td><input type="text" id="email" name="email" value="<s:property value="email" />" class="text" maxlength="200" />
										<span><s:fielderror fieldName="email"></s:fielderror> </span></td>
								</tr>
								<tr>
									<th><span class="requiredField">*</span>电话:</th>
									<td><input type="text" id="phone" name="phone" value="<s:property value="phone" />" class="text" maxlength="200"/></td>
								</tr>
								<tr>
									<th>姓名:</th>
									<td><input type="text" name="name" value="<s:property value="name" />" class="text"	maxlength="200" /></td>
								</tr>
								<tr>
									<th>性别:</th>
									<td><span class="fieldSet">
										 <label><input	type="radio" name="sex" value="男" checked="checked" />男 </label> 
										 <label><input type="radio" name="sex" value="女" />女 </label>
									</span></td>
								</tr>


								<tr>
									<th>地址:</th>
									<td><input type="text" name="addr" value="<s:property value="addr" />" class="text"	maxlength="200" /></td>
								</tr>
								<tr>
									<th><span class="requiredField">*</span>验证码:</th>
									<td><span class="fieldSet"> 
										<input type="text" id="checkcode" name="checkcode" class="text captcha"	maxlength="4" autocomplete="off" />
										<img id="checkImg" class="checkImg"	src="${pageContext.request.contextPath}/checkImg.action" title="点击更换验证码"  />
										<!-- 验证吗错误回显 -->
										<s:actionerror />
										</span>
										
									</td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><input type="submit" class="submit" value="同意以下协议并注册" /></td>
								</tr>
								
							</tbody>
						</table>
						<div class="login">
							<div class="ad">
								<dl>
									<dt>注册即享受</dt>
									<dd>正品保障、正规发票</dd>
									<dd>货到付款、会员服务</dd>
									<dd>自由退换、售后上门</dd>
								</dl>
							</div>
							<dl>
								<dt>已经拥有账号了？</dt>
								<dd>
									立即登录即可体验在线购物！ <a href="${ pageContext.request.contextPath }/user_loginPage.action">立即登录</a>
								</dd>
							</dl>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<!-- 引入底部文件 font.jsp-->
	<%@ include file="home/font.jsp" %>

	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
	<script	src="${pageContext.request.contextPath}/js/jsp/regist.js"  language="JavaScript"></script>
	
</body>
</html>