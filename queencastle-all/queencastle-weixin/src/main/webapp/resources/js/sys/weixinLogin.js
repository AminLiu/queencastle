$(function(){
	if($(".pageLogin").height() < $(window).height()){
		$(".pageLogin").height($(window).height());
	}
	//实现用户密码MD5加密功能，post提交
	$("#loginBtn").click(function(){
		var userName = $("#j_username").val();
		var passWord = $("#j_password").val();
		if(userName == "" || passWord == ""){
			$("#userLoginMsg").html("用户名或密码为空");
			$("#loginInfo").html("");
			return false;
		}else{
			$('#j_password').val(CryptoJS.MD5($('#j_password').val()));
    		$("#loginFrm").submit();
		}
	
	});
});