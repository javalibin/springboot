
$(function(){
	var Request = new Object();  
	Request = GetRequest();  
	var uuid= Request["uuid"];
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
			    window.location = "/manage/index.html";
		   }, "json");
		});
		$("#regist").click(function(){
			window.location = "/regist.html"
		});
		$("#regists").click(function(){
			var name = $("#username").val();
			var pwd =  $("#password").val();
			var repwd =  $("#repassword").val();
			var age = $("#age").val();
			var birthday = $("#birthday").val();
			if(pwd != repwd){
				alert("两次输入的密码不一致");
				return false;
			}
			if(pwd.length < 6){
				alert("密码不能少于6位");
				return false;
			}
			if(age <0 || age >160){
				alert("年龄格式不正确");
				return false;
			}
			if(birthday.length < 10 || birthday.indexOf("-")  == 0){
				alert("生日格式不正确,请重新输入");
				return false;
			}
			$.post("/user/register", { "name": name,"pwd":pwd,"age":age,"birthday":birthday,"jurisdictionId":0},
			   function(data){
				    if(data.code == 500){
				    	alert(data.msg);
				    	return false;
				    }
				    if(data.code == 200){
				    	alert("注册成功");
				    	if(uuid == 1){
					    	window.location = "/manage/userList.html"
					    }else{
					    	window.location = "/index.html"
					    }
				    }
			   }, "json");
		});
});


function GetRequest() {  
    var url = location.search; //获取url中"?"符后的字串   
    var theRequest = new Object();  
    if (url.indexOf("?") != -1) {  
        var str = url.substr(1);  
        strs = str.split("&");  
        for (var i = 0; i < strs.length; i++) {  
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);  
        }  
    }  
    return theRequest;  
} 