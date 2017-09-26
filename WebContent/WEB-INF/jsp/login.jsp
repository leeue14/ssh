<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0080)http://localhost:8080/mango/login.jhtml?redirectUrl=%2Fmango%2Fcart%2Flist.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>会员登录</title>
	<link href="${pageContext.request.contextPath}/css/common.css"	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/login.css"	rel="stylesheet" type="text/css" />
	
</head>
<body>

	<div class="container header">
		<!-- 引入头部左面  -->
		<%@ include file="home/header_left.jsp"%>
		
		<%@ include file="home/header.jsp"%>
		
		<%@ include file="home/menu.jsp"%>
	</div>

	<div class="container login">
		<div class="span12">
			<div class="ad">
				<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录" />
			</div>
		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN
					</div>
					
					<form id="loginForm"
						action="${ pageContext.request.contextPath }/user_login.action"	method="post" novalidate="novalidate">
						<table>
							<tbody>
								<tr>
									<th>用户名:</th>
									<td>
									<input type="text" id="username" name="username" class="text" maxlength="20" value="<s:property value="username" />" />
									<span><s:fielderror fieldName="username" /></span>
									</td>
								</tr>
								<tr>
									<th>密&nbsp;&nbsp;码:</th> 
									<td><input type="password" id="password" name="password" class="text" maxlength="20" />
									<span><s:fielderror fieldName="password" /></span>
									</td>
								</tr>
								<tr>
									<th>验证码:</th>
									<td>
									<span class="fieldSet">
									 <input type="text"	id="checkcode" name="checkcode" class="text captcha" maxlength="4"  />
									 <img id="checkImg" class="checkImg" src="${pageContext.request.contextPath}/checkImg.action" title="点击更换验证码"  />
									 </span>
									 <span id="span250">
										<s:actionerror />
									</span>
									 </td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td>
									<label> <input type="checkbox" id="day7" name="day7" value="day7" />7天免登陆 </label>
									 </td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td>
										<input type="submit" class="submit" value="登 录" />
										<input type="reset" class="submit" value="重置" />	
									</td>
								</tr>
								<tr class="register">
									<th>&nbsp;</th>
									<td>
										<dl>
											<dd>
												立即注册即可体验在线购物！ <a href="${ pageContext.request.contextPath }/user_registPage.action">立即注册</a>
											</dd>
										</dl>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 引入底部文件 font.jsp-->
	<%@ include file="home/font.jsp" %>
	
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
	<script	type="text/javascript" src="${pageContext.request.contextPath}/js/jsp/login.js"></script>
	
</body>
</html>