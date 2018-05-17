$(function(){
	$("#header").load("../manage/head.html");
	$("#left").load("../manage/left.html");
	userList();
});

function userList(){
	$.ajax({  
		   		type:'post',  
		   		url:'/user/list',   
		   		success:function(data){
		   			if(data.code == 500){
		   				$("#userList").append("<tr><td colspan = \"7\">暂无用户信息</td></tr>");
		   				return;
		   			}
		   			if(data.code == 200){
		   				var parentDom = $("#userList");
		   				var wocao = data.data;
			   			$.each(wocao, function(key,value){
			   				parentDom.after("<tr>"
			   				+"<td name = \"id\">"+wocao[key].id+"</td>"
			   				+"<td name = \"name\">"+wocao[key].name+"</td>"
			   				+"<td name = \"sex\">"+wocao[key].sex+"</td>"
			   				+"<td name = \"age\">"+wocao[key].age+"</td>"
			   				+"<td name = \"birthday\">"+wocao[key].birthday+"</td>"
			   				+"<td name = \"jurisdictionId\">"+ wocao[key].jurisdictionId+"</td>"
			   				+"<td><a href=\"userView.html\"><img src=\"img/read.png\" alt=\"查看\" title=\"查看\"/></a><a href=\"userUpdate.html\"><img src=\"img/xiugai.png\" alt=\"修改\" title=\"修改\"/></a><a href=\"#\" class=\"removeUser\"><img src=\"img/schu.png\" alt=\"删除\" title=\"删除\"/></a></td>"
			   				+"</tr>");
			   			});
		   				return;
		   			}
		   		},error: function(XMLHttpRequest, textStatus, errorThrown) {
		   			alert("网络异常");
				}  
		   }); 
}


