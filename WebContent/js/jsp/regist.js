//	function checkForm(){
//		// 校验用户名:
//		var username = document.getElementById("username").value;
//		if(username == ''){
//			alert("用户名不能为空!");
//			return false;
//		}
//			
//		// 校验密码:
//		var password = document.getElementById("password").value;
//		if(password == ''){
//			alert("密码不能为空!");
//			return false;
//		}
//		
//		// 校验确认密码
//		var repassword = document.getElementById("repassword").value;
//		if(password != repassword){
//			alert("两次密码不一致!");
//			return false;
//		}
//	}
	
$("#registerForm").submit(function() {
	if( $("#username").val() == ''){
		alert("前台：用户名不能为空!");
		return false;
	}	
	
	if ($("#password").val() == '') {
		alert("前台：密码不能为空");
		return false;
	}
	
	if($("#password").val() != $("#repassword").val()){
		alert("前台：两次密码不一致!");
		return false;
	}
});
	
	
//	function checkUserName(){
//		var username = $("#username").val();
//		var url = "${pageContext.request.contextPath}/user_checkUserName.action?"+new Date().getTime();
//		var parms = {'username':username};
//		
//		$("#span1").load(url , parms);
//	}
	
	$("#username").blur(function() {
		var username = $("#username").val();
		var url = "${pageContext.request.contextPath}/user_checkUserName.action?"+new Date().getTime();
		var parms = {'username':username};
		
		$("#span1").load(url , parms);
	});
	
//	function change(){
//		var img = document.getElementById("checkImg");
//		img.src="${pageContext.request.contextPath}/checkImg.action?"+new Date().getTime();
//	}
	
	$("#checkImg").click(function() {
		var url = "${pageContext.request.contextPath}/checkImg.action?"+new Date().getTime();
		$("#checkImg").attr("src" , url);
	});
	
//	$("#checkImg").click(function(){
//		var url = "${pageContext.request.contextPath}/checkImg.action?"+new Date().getTime();
//		$("#checkImg").attr("src",url);
//	});

//	function checkUserName(){
//		// 获得用户名的值:
//		var username = document.getElementById("username").value;
//		// 1.创建异步加载对象:
//		var xhr = createXMLHttpRequest();
//		// 2.设置监听
//		xhr.onreadystatechange = function(){
//			if(xhr.readyState == 4){
//				if(xhr.status == 200){
//					var data = xhr.responseText;
//					document.getElementById("span1").innerHTML = data;
//				}
//			}
//		}
//		// 3.打开连接:
//		xhr.open("GET","${pageContext.request.contextPath}/user_checkUserName.action?"+new Date().getTime()+"&username="+username,true);
//		// 4.发送
//		xhr.send(null);
//	}
//	
//	function createXMLHttpRequest() {
//		var xmlHttp;
//		try { // Firefox, Opera 8.0+, Safari
//			xmlHttp = new XMLHttpRequest();
//		} catch (e) {
//			try {// Internet Explorer
//				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
//			} catch (e) {
//				try {
//					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
//				} catch (e) {
//				}
//			}
//		}
//
//		return xmlHttp;
//	}
