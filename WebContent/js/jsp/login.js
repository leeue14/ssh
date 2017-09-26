//function change() {
//	var img = document.getElementById("checkImg");
//	img.src  = "${pageContext.request.contextPath}/checkImg.action?" + new Date().getTime();
//	
//}

$("#checkImg").click(function() {
	var url = "${pageContext.request.contextPath}/checkImg.action?"+new Date().getTime();
	$("#checkImg").attr("src",url);
});


