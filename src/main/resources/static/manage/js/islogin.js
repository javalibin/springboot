function islogin(){
		   $.ajax({  
		   		type:'post',  
		   		url:'/user/islogin',   
		   		success:function(data){
		   			if(data.code == 500){
		   				alert("登录超时,请重新登录");
		   				$(window).attr('location','../../index.html');
		   				return;
		   			}
		   			if(data.code == 200){
		   				var a = JSON.parse(data.msg);
		   				console.info(a);
		   				for(var i in a){
		   					if(i == "name"){
		   						$(".logins").html(a[i]);
		   						$("#logins").html(a[i]);
		   					}
		   					if(i == "id"){
		   						$("#id").val(a[i]);
		   					}
		   					if(i == "jurisdictionId"){
		   						manage(a[i]);
		   						loadSubject(a[i]);
		   					}
		   				}
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