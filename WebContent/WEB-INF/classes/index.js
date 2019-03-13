window.onload = function(){
	$("#loginBtn").on("click",submitData);
}
var submitData = function(){
	$.ajax({
		url:"./AccountManage",
		type:"post",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data: $('#loginform').serialize(),
		success:loginValidate
	});
}
var loginValidate = function(arg){
	console.log("1");
}
var tosignup = function(){
	window.location.href="./signup/signup.html";
}