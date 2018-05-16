$(function(){
	$("#header").load("../manage/head.html");
	$("#left").load("../manage/left.html");
});
function islogin(){
		   $.ajax({  
		   		type:'post',  
		   		url:'/user/islogin',   
		   		success:function(data){
		   			if(data.code == 500){
		   				$(window).attr('location','../../index.html');
		   				return;
		   			}
		   			if(data.code == 200){
		   				return true;
		   			}
		   		},error: function(XMLHttpRequest, textStatus, errorThrown) {
		   			alert("网络异常");
				}  
		   }); 
}
$(document).ready(function(){ 
	islogin();
});


