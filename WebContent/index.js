window.onload = function(){
	$("#loginBtn").on("click",submitData);
}
var submitData = function(){
	$.post("AccountManage?method=loginInValidate", $('#loginform').serialize(), loginValidate)
}
var loginValidate = function(arg){
	console.log(arg);
}
var tosignup = function(){
	window.location.href="./signup/signup.html";
}