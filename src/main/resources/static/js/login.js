
$(function(){
	$("#button").click(function(){
		var name = $("#username").val();
		var pwd =  $("#password").val();
		if(null == name){
			alert("用户名不能为空");
			return;
		}
		if(null == pwd){
			alert("密码不能为空");
			return;
		}
		$.post("/user/login", { "name": name,"pwd":pwd },
		   function(data){
			    if(data.code == 500){
			    	alert(data.msg);
			    	return;
			    }
		    	window.location = "/manage/index.html"
		   }, "json");
		});
});