function manage(jurisdictionId){
	$.ajax({  
		   		type:'post',  
		   		url:'/user/Jurisdiction', 
		   		data:{"jurisdictionId":jurisdictionId},  
		   		success:function(data){
		   			if(data.code == 500){
						$(".nones").css("display", "none"); 
		   				return;
		   			}
		   			var dates = data.data;
		   			for(var i in dates){
		   				var tag = "#"+i;
		   				if(dates[i] == "0"){
		   					$(tag).css("display", "none"); 
		   				}
		   			}
		   		},error: function(XMLHttpRequest, textStatus, errorThrown) {
		   			$(".nones").css("display", "none"); 
		   			alert("网络异常");
				}
			}); 
}