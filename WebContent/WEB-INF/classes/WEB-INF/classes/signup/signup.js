window.onload = function(){
	$("#signup").on("click",signupSubmit);
	$("#username").on("input",syncData);
	$("#passwd").on("input",syncData);
	$("#confirmpasswd").on("input",syncData);
	$("#tel").on("input",syncData);
	$("#mail").on("input",syncData);
}
var signupSubmit = function(){
	$.ajax({
		url:"../AccountManage",
		type:"post",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data: $('#signupform').serialize(),
		success:signupValidate
	});
}
var signupValidate = function(){
	
}
var syncData = function(arg){
	$("#realusername").val($("#username").val());
	$("#realpasswd").val($("#passwd").val());
	$("#realconfirmpasswd").val($("#confirmpasswd").val());
	$("#realtel").val($("#tel").val());
	$("#realmail").val($("#mail").val());
}