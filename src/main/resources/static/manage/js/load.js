$(function(){
	$("#header").load("../manage/head.html");
	$("#left").load("../manage/left.html");
	userList();
	movein();
	moveout();
	update();
	subjectUpdates();
});

function userList(){
	var name = $("#userText").val();
	console.info(name);
	var parentDom = $("#userList");
	parentDom.siblings().remove();
	$.ajax({  
		   		type:'post',  
		   		url:'/user/list',
		   		data:{"name":name},   
		   		success:function(data){
		   			if(data.code == 500){
		   				parentDom.after("<tr><td colspan = \"7\">暂无用户信息</td></tr>");
		   				return false;
		   			}
		   			if(data.code == 200){
		   				var wocao = data.data;
						
		   				//for(var i=0;i<wocao.length;i++){
			   			$.each(wocao, function(key,value){
			   				var jurisdictionId = wocao[key].jurisdictionId;
			   				var jurisdictionName = "";
			   				if(jurisdictionId == "0"){
			   					jurisdictionName = "学生";
			   				}
			   				if(jurisdictionId == "1"){
			   					jurisdictionName = "老师";
			   				}
			   				if(jurisdictionId == "2"){
			   					jurisdictionName = "管理员";
			   				}
			   				var updates = "update.html?id="+wocao[key].id+"&name="+wocao[key].name+"&age="+wocao[key].age+"&birthday="+wocao[key].birthday+"&jurisdictionId="+jurisdictionId+"&pwd="+wocao[key].pwd;
			   				var deletess = "deletes("+wocao[key].id+")";
			   				parentDom.after("<tr>"
				   				+"<td name = \"id\">"+wocao[key].id+"</td>"
				   				+"<td name = \"name\">"+wocao[key].name+"</td>"
				   				+"<td name = \"pwd\">"+wocao[key].pwd+"</td>"
				   				+"<td name = \"age\">"+wocao[key].age+"</td>"
				   				+"<td name = \"birthday\">"+wocao[key].birthday+"</td>"
				   				+"<td name = \"jurisdictionId\">"+ jurisdictionName+"</td>"
				   				+"<td><a href=\""+updates+"\"><img src=\"img/xiugai.png\" alt=\"修改\" title=\"修改\"/></a><a href=\"javascript:void(0);\"  onclick = \""+deletess+"\" class=\"removeUser\"><img src=\"img/schu.png\" alt=\"删除\" title=\"删除\"/></a></td>"
				   				+"</tr>");
			   				}
			   			);
		   				return;
		   			}
		   		},error: function(XMLHttpRequest, textStatus, errorThrown) {
		   			alert("网络异常");
				}  
		   }); 
}


function changepwd(){
	var renewpass = $("#reNewPassword").val();
	var newPassword = $("#newPwd").val();
	var oldPassword = $("#pwd").val();
	var id = $("#id").val();
	if(renewpass != newPassword){
		alert("两次输入的密码不一致");
		return false;
	}
	 if(renewpass.length == 0){
		alert("新密码不能为空");
		return false;
	}
	if(renewpass.length < 6){
		alert("新密码不能少于6位");
		return false;
	}
	$.post("/user/changePwd", { "pwd": oldPassword,"newPwd":newPassword,"id":id},
		   function(data){
			    if(data.code == 500){
			    	alert(data.msg);
			    	return false;
			    }
			    if(data.code == 200){
		   				alert("密码修改成功");
		   				$(window).attr('location','../../index.html');
		   		}
		   }, "json");
}

var oldStr;
var oldclass;
function movein(){
$(".buttonsexsex").each(function(index){
	$(this).mouseover(function(){
	    oldStr = $(this).val();
		oldclass = $(this).attr("class");
	    if(oldStr != "+"){
	    	$(this).attr("class","buttonsexsex wuli");
		    var ids = $(this).attr("ID");
		    var spanvalue = "#"+ids+"span";
		    var abc = $(spanvalue).html();
		    $(this).val(abc);
	    }
	});
});

}

function moveout(){
$(".buttonsexsex").each(function(index){
	$(this).mouseleave(function(){
		if(oldStr != "+"){
			$(this).attr("class",oldclass);
		    $(this).val(oldStr);
	    }
	});
});

}

function push(){
	var values = $("#push").val();
	$("#pushMassageInfo").after("<span>"+values+"</span><br/>");
	$("#push").val("");
}

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

function update(){
	var Request = new Object();  
    Request = GetRequest();  
    var id= Request["id"];
    var name= Request["name"]; 
    var age= Request["age"];
    var birthday= Request["birthday"];
    var jurisdictionId= Request["jurisdictionId"];
    var pwd= Request["pwd"];
    $("#id1").val(id);
    $("#username1").val(name);
    $("#age1").val(age);
    $("#birthday1").val(birthday);
    $("#pwd1").val(pwd);
    $("#updatesss").click(function(){
    	var id = $("#id1").val();
	    var name = $("#username1").val();
	    var age =$("#age1").val();
	    var birthday =$("#birthday1").val();
	    var pwd =$("#pwd1").val();
	    var jurisdictionId =$("#jurisdictionId1").val(); 
	    if(null == name){
			alert("用户名不能为空");
			return false;
		}
		if(null == pwd){
			alert("密码不能为空");
			return false;
		}
		if(null == jurisdictionId || jurisdictionId.length == 0){
			alert("权限不能为空");
			return false;
		}
		if(null == age){
			alert("年龄不能为空");
			return false;
		}
		if(null == birthday){
			alert("生日不能为空");
			return false;
		}
    	 $.post("/user/update", { "pwd": pwd,"newPwd":pwd,"name":name,"age":age,"birthday":birthday,"jurisdictionId":jurisdictionId,"id":id},
		   function(data){
			    if(data.code == 500){
			    	alert(data.msg);
			    	return false;
			    }
			    if(data.code == 200){
		   				alert("资料修改成功");
		   				$(window).attr('location','../manage/userList.html');
		   		}
		   }, "json");
    });
 }
   
 function deletes(id){
   	  var result = confirm("确认删除该用户吗？");
   	  if(result){
   	  	 $.post("/user/delete", { "id":id},
		   function(data){
			    if(data.code == 500){
			    	alert(data.msg);
			    	return false;
			    }
			    if(data.code == 200){
		   				alert("删除成功");
		   				$(window).attr('location','../manage/userList.html');
		   		}
		   }, "json");
   	  }
}


function add_course(row,cloun){
	var abc =  $("#jurisdictionIdsIDS").html();
	if(abc != "1"){
		alert("您没有权限修改或新增课程哦,请联系老师对课程进行变更");
		return false;
	}
	var hrefs = "../manage/addOrUpdateSubject.html?row="+row+"&cloun="+cloun;
	$(window).attr('location',hrefs);
}

function subjectUpdates(){
	var Request = new Object();  
    Request = GetRequest();  
    var cloun= Request["row"];
    var dataid= Request["cloun"]; 
    $("#cloun").val(cloun);
    $("#dataid").val(dataid);
	$("#subjectUpdates").click(function(){
		 var subjectName=$("#subjectName").val();
		 var times=$("#times").val();
		 var teacher=$("#teacher").val();
		 var other = "时间:"+times+"讲师:"+teacher;
		 var cloun=$("#cloun").val();
		 var dataid=$("#dataid").val();
		 $.post("/user/updateSubject", { "subjectName":subjectName,"datesId":cloun,"cloun":dataid,"other":other},
		   function(data){
			    if(data.code == 500){
			    	alert(data.msg);
			    	return false;
			    }
			    if(data.code == 200){
		   				alert(data.msg);
		   				$(window).attr('location','../manage/subject.html');
		   		}
		   }, "json");
	});
}

function loadSubject(jurisdictionIds){
	$("#jurisdictionIdsIDS").html(jurisdictionIds);
	 $.post("/user/subjectList",
		   function(data){
			    if(data.code == 500){
			    	alert(data.msg);
			    	return false;
			    }
			    if(data.code == 200){
		   				$(".buttonsexsex").each(function(index){
							var id =  $(this).attr["ID"];
							if(jurisdictionIds == "1"){
								$(this).val("+");
							}
						});
						var datas = data.data;
						$.each(datas, function(key,value){
							var rondom = Math.floor(Math.random()*9+1);
							var cloun = datas[key].datesId;
							var row = datas[key].cloun;
							var button_id= "#"+cloun+"_"+row;
							var span_id = button_id+"span";
							$(button_id).val(datas[key].subjectName);
							$(span_id).html(datas[key].other);
							  switch (rondom) {
				                    case (1):
				                    	$(button_id).attr("class","buttonsexsex shuxue");
				                        break;
				                    case (2):
				                       $(button_id).attr("class","buttonsexsex yingyu");
				                        break;
				                    case (3):
				                      	 $(button_id).attr("class","buttonsexsex zhengzhi");
				                        break;
				                    case (4):
				                      	 $(button_id).attr("class","buttonsexsex wuli");
				                        break;
				                    case (5):
				                      	$(button_id).attr("class","buttonsexsex meishu");
				                        break;
				                    case (6):
				                    	$(button_id).attr("class","buttonsexsex huaxue");
				                        break;
				                    case (7):
				                     	$(button_id).attr("class","buttonsexsex dili");
				                        break; 
				                    case (8):
				                      	$(button_id).attr("class","buttonsexsex shengwu");
				                        break;
				                    case (9):
				                      	$(button_id).attr("class","buttonsexsex tiyu");
				                        break;
			                }
						});
		   		}
		   }, "json");
	
		
}


