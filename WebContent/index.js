window.onload = function(){
	$("#loginBtn").on("click",submitData);
}
var submitData = function(){
	$.ajax({
		url:"./AccountManage",
		type:"post",
		data: $('#loginform').serialize(),
		complete:loginValidate
	});
}
var loginValidate = function(arg){
	console.log(arg);
}
var tosignup = function(){
	window.location.href="./signup/signup.html";
}