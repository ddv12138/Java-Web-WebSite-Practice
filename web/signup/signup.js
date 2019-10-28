window.onload = function(){
	$("#signup").on("click",signupSubmit);
};
var signupSubmit = function(){
	$.ajax({
		url: "/AccountManage?method=signupValidate",
		type:"post",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data: $('#signupform').serialize(),
		success:signupValidate
	});
};
var signupValidate = function (arg) {
	console.log(arg);
};