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
		   				$("#logins").html(data.msg);
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