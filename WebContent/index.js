window.onload = function(){
	$("#loginBtn").on("click",submitData);
}
var submitData = function(){
	$.post("RPC.DO?method=loginValidate", $('#loginform').serialize(), loginValidate)
}
var loginValidate = function(arg){
	console.log(arg);
}
var tosignup = function(){
	window.location.href="./signup/signup.html";
}