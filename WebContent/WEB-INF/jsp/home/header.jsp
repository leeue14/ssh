<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<s:if test="#session.existUser!=null">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<s:property value="#session.existUser.name"/>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${ pageContext.request.contextPath }/user_quit.action">退出</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${ pageContext.request.contextPath }/order_findByUid.action">我的订单</a>
					</li>
				</s:if>
				<s:else>
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="${ pageContext.request.contextPath }/user_loginPage.action">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${ pageContext.request.contextPath }/user_registPage.action">注册</a>
					</li>
				</s:else>
			</ul>
		</div>
		<div class="cart">
			<a  href="${ pageContext.request.contextPath }/cart_myCart.action">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>#18081782961#</strong>
			</div>
	</div>