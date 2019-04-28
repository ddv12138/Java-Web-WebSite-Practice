window.onload = function(){
	layui.use("form", function () {
		layui.form.on("submit(loginform)", submitData)
	})
}
var submitData = function (arg) {
	console.log(arg)
	$.post("AccountManage?method=loginValidate", $(arg.elem).serialize(), loginValidate);
	return false;
}
var loginValidate = function(arg){
	console.log(arg);
}
var tosignup = function(){
	window.location.href="./signup/signup.html";
}