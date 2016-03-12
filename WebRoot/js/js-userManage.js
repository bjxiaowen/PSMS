$(document).ready(function() {//页面准备
	dispTableContent();//显示所有用户
});

function dispTableContent(){//显示所有用户
	$.ajax({
			url:'getUserInformation.action',
			type:'GET',
			dataType:'json',
			async:false,
			success:function(obj){	
		    		$("#userTable").empty();
  					for(var i = 0;i<obj[0].length;i++){
				   		var str = "<tr>"+
				   		        "<td><input type='checkbox'/></td>"+
					   			"<td >"+(i+1)+"</td>"+
					   			"<td class='user_name' value='"+obj[0][i].user_name+"'>"+obj[0][i].user_name+"</td>"+
					   			"<td class='password' value='"+obj[0][i].password+"'>"+obj[0][i].password+"</td>"+
					   			"<td class='name' value='"+obj[0][i].name+"'>"+obj[0][i].name+"</td>"+
					   			"<td class='telephone' value='"+obj[0][i].telephone+"'>"+obj[0][i].telephone+"</td>"+
					   			"<td class='email' value='"+obj[0][i].email+"'>"+obj[0][i].email+"</td>"+
					   			"<td class='role_name' value='"+obj[1][i]+"'>"+obj[1][i]+"</td>"+
					   			"<td class='ps_name' value='"+obj[2][i]+"'>"+obj[2][i]+"</td>"+
					   			"<td><a onclick='deleteUser(this);' value='"+obj[0][i].id+"' >删   |</a><a onclick='updateUser(this);' value='"+obj[0][i].id+"' >改 </a></td>"+
				   			    "</tr>"; 
				   		$("#userTable").append(str);
		    	}
			}
			});     
}
function deleteUser(t)//删除所选用户
{
	var id = $(t).attr("value");
	if(!confirm("确定删除?")){return false;}
	else 
	{
		$.ajax({
		        url:'deleteUser.action?id='+encodeURI(encodeURI(id)),
				type:'GET',
				dataType:'json',
				async:false,
			  	success:function(){
			  		dispTableContent();
			  	}
		});
	}
}
function updateUser(t){
	$("#content_update_user").show();
	var user_name = $(t).parents("tr").find(".user_name").attr("value");
	var password = $(t).parents("tr").find(".password").attr("value");
	var name = $(t).parents("tr").find(".name").attr("value");
	var telephone = $(t).parents("tr").find(".telephone").attr("value");
	var email = $(t).parents("tr").find(".email").attr("value");
	var role_name = $(t).parents("tr").find(".role_name").attr("value");
	var ps_name = $(t).parents("tr").find(".ps_name").attr("value");
	//-------------------以上为获取点击某行时该行的所有值
	$("#ex_user_name").val(user_name);
	$("#ex_password").val(password);
	$("#ex_name").val(name);
	$("#ex_telephone").val(telephone);
	$("#ex_email").val(email);
	$("#ex_role_name").attr("value",role_name);
	$("#ex_ps_name").attr("value",ps_name);//用hidden起来的输入框保存原始值
	$("#af_user_name").val(user_name);
	$("#af_password").val(password);
	$("#af_name").val(name);
	$("#af_telephone").val(telephone);
	$("#af_email").val(email);
	$("#af_role_name").attr("value",role_name);
	if(ps_name == "全部")$("#af_ps_name").attr("value","all");
	else $("#af_ps_name").attr("value",ps_name);
	//以上为将现有的值录入输入框内

}

function saveUser(){//点击修改页面的保存按钮的事件响应，先获得修改之前和修改之后的值，再判断输入值是否合法，如果合法则保存
	var user_name = trim($("#af_user_name").val());
	var name = trim($("#af_name").val());
	var password = trim($("#af_password").val());
	var repassword = trim($("#af_repassword").val());
	var telephone = trim($("#af_telephone").val());
	var email = trim($("#af_email").val());
	var role_name = $("#af_role_name").attr("value");
	var ps_name = $("#af_ps_name").attr("value");//--------------------------以上为获取修改的值并且去掉输入值两边的空格
	var ex_user_name = trim($("#ex_user_name").val());
	var ex_ps_name = $("#ex_ps_name").attr("value");
	var ex_password = trim($("#ex_password").val());
	if(checkUpdateUserInformation(user_name,password,repassword,ps_name,ex_user_name,ex_ps_name,ex_password)==false)return false;//校验用户信息
	$.ajax({
        url:'updateUser.action?user_name='+encodeURI(encodeURI(user_name))+'&name='+encodeURI(encodeURI(name))+
        '&password='+encodeURI(encodeURI(password))+'&telephone='+encodeURI(encodeURI(telephone))+'&email='+encodeURI(encodeURI(email))+
        '&role_name='+encodeURI(encodeURI(role_name))+'&ps_name='+encodeURI(encodeURI(ps_name))+
        '&ex_user_name='+encodeURI(encodeURI(ex_user_name))+'&ex_ps_name='+encodeURI(encodeURI(ex_ps_name)),
		type:'GET',
		dataType:'json',
		async:false,
	  	success:function(){
	  		
	  		dispTableContent();//刷新表格数据
	  	}
    });	
}



function showBg() { 
	$("#content_add_user").show();
}
function trim(str){//去掉两边空格   
    return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');   
} 
//关闭灰色 jQuery 遮罩 //显示灰色 jQuery 遮罩层 
function closeBg() { 
	$("#fullbg,#content_add_user,#content_update_user").hide(); 
} 
function newUser(){//新建用户
	var user_name = trim($("#user_name").val());
	var name = trim($("#name").val());
	var password = trim($("#password").val());
	var repassword = trim($("#repassword").val());
	var telephone = trim($("#telephone").val());
	var email = trim($("#email").val());
	var role_name = $("#role_name").attr("value");
	var ps_name = $("#ps_name").attr("value");//--------------------------以上为获取新建的值并且去掉输入值两边的空格
	if(checkUserInformation(user_name,password,repassword,ps_name)==false)return false;//校验用户信息
	$.ajax({
        url:'addUser.action?user_name='+encodeURI(encodeURI(user_name))+'&name='+encodeURI(encodeURI(name))+
        '&password='+encodeURI(encodeURI(password))+'&telephone='+encodeURI(encodeURI(telephone))+'&email='+encodeURI(encodeURI(email))+
        '&role_name='+encodeURI(encodeURI(role_name))+'&ps_name='+encodeURI(encodeURI(ps_name)),
		type:'GET',
		dataType:'json',
		async:false,
	  	success:function(){
	  		dispTableContent();//刷新表格数据
	  	}
        });	
}
function checkUserInformation(user_name,password,repassword,ps_name){
	
	if(user_name==""){alert("用户名不能为空");return false;}
	if((password=="") || (repassword=="")){alert("密码不能为空");return false;}
	if(password!= repassword){alert("密码不一致");return false;}
	$.ajax({
        url:'checkUserNameIsLegal.action?user_name='+encodeURI(encodeURI(user_name))
             +'&ps_name='+encodeURI(encodeURI(ps_name)),
		type:'GET',
		dataType:'json',
		async:false,
	  	success:function(obj){
	  		var result = obj[0];
	  		if(result == "wrong"){alert("用户名已存在");return false;}
	  	}
    });	
}
function checkUpdateUserInformation(user_name,password,repassword,ps_name,ex_user_name,ex_ps_name,ex_password){
	if(user_name==""){alert("用户名不能为空");return false;}
	//判断用户名不能为空
	if(password == ex_password);
	else if((password=="") || (repassword=="")){alert("密码或者重复密码不能为空");return false;}
	else if(password!= repassword){alert("密码不一致");return false;}
	//判断密码是否被修改，若被修改则需保持与确认密码一致且不能为空，若没有修改，则不作为
	if(user_name == ex_user_name && ps_name == ex_ps_name)return true;//若没有修改用户名和所属电站，则不用判断用户名是否已存在
	else if(user_name == ex_user_name && ps_name == "all" && ex_ps_name == "全部")return true;
	else 
	{
		
		$.ajax({
	        url:'checkUserNameIsLegal.action?user_name='+encodeURI(encodeURI(user_name))
	             +'&ps_name='+encodeURI(encodeURI(ps_name)),
			type:'GET',
			dataType:'json',
			async:false,
		  	success:function(obj){
		  		var result = obj[0];
		  		if(result == "wrong"){alert("用户名已存在");return false;}
		  		else return true;
		  	}
		});	
	}
}
