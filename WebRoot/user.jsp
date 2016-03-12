<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="UTF-8">
    <base href="<%=basePath%>user.jsp">
    <title></title>    
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyui/demo/demo.css"> 
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>   
    <script type="text/javascript" src="easyui/datagrid-detailview.js"></script> 
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script> 
    <style>
    	body{
    		border:0px;padding:5px;
    	}  	
    </style>
</head>
<body>
    <p style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">用户管理->用户查看</p>  
    <table id="dg" title="用户列表" class="easyui-datagrid" style="width:100%;height:95%;text-align:center" 
            url="getUserInformation.action"        
            toolbar="#toolbar" pagination="true"
            pageSize=20
             //读取分页条数，即向后台读取数据时传过去的值
		    pageList="[ 20, 30, 40 ]" //可以调整每页显示的数据，即调整pageSize每次向后台请求数据时的数据
            rownumbers="true" fitColumns="true" singleSelect="true"
             autoRowHeight="true",           
            striped="true" sortName="user_name" sortOrder="asc" remoteSort="flase"    >
        <thead>
            <tr>              
                <th field="user_name" align="center" width="20" sortable="true">用户名</th>
                <th field="password" align="center" width="20">密码</th>
                <th field="name" align="center" width="20" sortable="true">姓名</th>
                <th field="telephone" align="center" width="20">联系电话</th>
                <th field="email" align="center" width="20" >电子邮箱</th>
                <th field="role_name" align="center" width="20" sortable="true">角色名称</th>
                <th field="ps_name" align="center" width="20" sortable="true">电站名称</th>
            </tr>
        </thead>
    </table>
    
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新建用户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑用户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除用户</a>
        <input id="search_name" name="search_name" class="easyui-searchbox" data-options="prompt:'请输入用户名查找',searcher:doSearch" style="width:200px">
        <select class="easyui-combobox" name="search-station" style="width:200px;" data-options=" panelHeight:'auto'">       
        	<option value="">根据电站查询</option>
        	<option value="全部">中心电站查看用户</option>
        	<c:forEach items="${list_station_name}" var="list3">	  													
				<option value="${list3}">${list3}</option>
			</c:forEach>
        </select>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"  onclick="queryUserByPS_name()">查找用户</a>
       </div>
    
    <div id="dlg" class="easyui-dialog" style="width:450px;height:380px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">用户信息</div>
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>用户名</label>
                <input id="user_name" name="user_name" class="easyui-textbox" >(必填)
                <input id="ex_user_name" name="ex_user_name" type="hidden" ></input>  
            </div>
            
            <div class="fitem">
                <label>密码</label>
                <input id="password" name="password" class="easyui-textbox" type="password" >(必填)
                 <input id="ex_password" name="ex_password" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>确认密码</label>
                <input id="repassword" name="repassword" class="easyui-textbox" type="password" >(必填)
                <input id="ex_repassword" name="ex_repassword" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>姓名</label>
                <input id="name" name="name" class="easyui-textbox" >
                <input id="ex_name" name="ex_name" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>联系电话</label>
                <input id="telephone" name="telephone" class="easyui-textbox" >
                <input id="ex_telephone" name="ex_telephone" type="hidden" onkeydown="if(event.keyCode==13)event.keyCode=9" 
                	   onKeyPress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false"></input> 
            </div>
            <div class="fitem">
                <label>电子邮箱</label>
                <input id="email" name="email" class="easyui-textbox" validType="email">
                <input id="ex_email" name="ex_email" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>角色名称</label>
                <select id="role_name" class="easyui-combobox" name="role_name" style="width:160px;"  editable="false" data-options="onSelect:setPSbyrole, panelHeight:'auto'">
                <c:forEach items="${list_role_name}" var="list1">	  													
						<option value="${list1}">${list1}</option>
					</c:forEach>
   			 	</select>(必填)
   			 	<input id="ex_role_name" name="ex_role_name" type="hidden" ></input> 
            </div>          
             <div class="fitem">
                <label>电站名称</label>
                <select id="ps_name"  class="easyui-combobox" name="ps_name" style="width:160px;"  editable="false" data-options="panelHeight:'auto'",  valueField: 'id',textField: 'text'>           		 
   			 	</select>(必填,先选角色)
   			 	<input id="ex_ps_name" name="ex_ps_name" type="hidden" ></input> 
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        var flag = 0;
        function newUser(){       	
            $('#dlg').dialog('open').dialog('setTitle','新建用户');
            $('#fm').form('clear');          
        }
       
  	 function setPSbyrole(rec)
       {
       	        var role_name=rec.value;
            
       			$.ajax({
			       url:'setPSbyrole.action?role_name='+encodeURI(encodeURI(role_name)),	
			       	       
					type:'GET',
					dataType:'json',
					async:false,
			  	    success:function(obj){
			  	    		$('#ps_name').combobox('clear'); 
			  	  			var data = [];
				  		    for(var i=0;i<obj[0].length;i++){
		  						var ps_name = obj[0][i];		  						
								data.push({ "text":ps_name, "id":ps_name ,"value":ps_name});
			  				}
							 $('#ps_name').combobox('loadData',data);	   
				  		       		       				
				  	   }
				    });
			}        
         function doSearch(value){
            var user_name = value;           
            if(user_name!=""){
            	$.ajax({
	              		 url:'queryUserByName.action?user_name='+encodeURI(encodeURI(user_name)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){	                   
        					if(obj.total==0)
								{
									$.messager.confirm('提示','没有符合该条件的用户');
									$('#dg').datagrid('reload');
								}
				  		        else{ 
				  		        	$('#dg').datagrid('loadData',obj); 
				  		        }
				  	 	}             			
	            });
	          }
           
            else
            {
            	 $('#dg').datagrid('reload');
            }
            
           $('#search_name').searchbox('clear');   ////  是否需要查询后清除查询电站名称
        }
        
         function pagerFilter(data){//分页显示数据
            if (typeof data.length == 'number' && typeof data.splice == 'function'){    // is array
                data = {
                    total: data.length,
                    rows: data
                }
            }
            var dg = $(this);
            var opts = dg.datagrid('options');
            var pager = dg.datagrid('getPager');
            pager.pagination({
                onSelectPage:function(pageNum, pageSize){
                    opts.pageNumber = pageNum;
                    opts.pageSize = pageSize;
                    pager.pagination('refresh',{
                        pageNumber:pageNum,
                        pageSize:pageSize
                    });
                    dg.datagrid('loadData',data);
                }
            });
            if (!data.originalRows){
                data.originalRows = (data.rows);
            }
            var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
            var end = start + parseInt(opts.pageSize);
            data.rows = (data.originalRows.slice(start, end));
            return data;
        }
        
        $(function(){
            $('#dg').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());
        });
       function queryUserByPS_name(){       
       		var user_name = document.getElementsByName("search_name")[0].value;
        	var ps_name = trim(document.getElementsByName("search-station")[0].value);
  	//		var ps_name=rec.value;
       		
       		if(ps_name!=""||user_name!=""){
            	$.ajax({
	                url:'queryUserByPs_name.action?ps_name='+encodeURI(encodeURI(ps_name))
	                		+'&user_name='+encodeURI(encodeURI(user_name)),		
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
		                     if(obj.total==0)    				
	        				{
	        					$.messager.confirm('提示','没有符合该条件的用户');
	     			//			$('#dg').datagrid('loadData', { total: 0, rows: [] });
	        				}
	        				else
	        				{	$('#dg').datagrid('loadData',obj);
	        				}		                     
	                    }
	            });
            } 
             else
             {	$('#dg').datagrid('reload');
             }
          $('#search-station').searchbox('clear'); 
          $('#search_name').searchbox('clear');
        }
        
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                flag = 1;
                $('#dlg').dialog('open').dialog('setTitle','编辑用户信息');
                $('#fm').form('load',row); 
                var ex_user_name = document.getElementsByName("ex_user_name");
                ex_user_name[0].value= row.user_name;
                var ex_password = document.getElementsByName("ex_password");
                ex_password[0].value= row.password;
                var ex_name = document.getElementsByName("ex_name");
                ex_name[0].value= row.name;
                var ex_telephone = document.getElementsByName("ex_telephone");
                ex_telephone[0].value= row.telephone;
                var ex_email = document.getElementsByName("ex_email");
                ex_email[0].value= row.email;
                var ex_role_name = document.getElementsByName("ex_role_name");
                ex_role_name[0].value= row.role_name;
                var ex_ps_name = document.getElementsByName("ex_ps_name");
                ex_ps_name[0].value= row.ps_name;
            }
        }
        function saveUser(){            
            var user_name = trim(document.getElementsByName("user_name")[0].value);
	   	    var password = trim(document.getElementsByName("password")[0].value);
	   	    var repassword = trim(document.getElementsByName("repassword")[0].value);
	        var name = trim(document.getElementsByName("name")[0].value);
	        var telephone = trim(document.getElementsByName("telephone")[0].value);
	        var email = trim(document.getElementsByName("email")[0].value);
	        var role_name = document.getElementsByName("role_name")[0].value; 
            var ps_name = document.getElementsByName("ps_name")[0].value; 
		    //--------------------------以上为获取新建的值并且去掉输入值两边的空格	
		     var ex_user_name = document.getElementsByName("ex_user_name")[0].value;               
             var ex_password = document.getElementsByName("ex_password")[0].value;                         
             var ex_name = document.getElementsByName("ex_name")[0].value;             
             var ex_telephone = document.getElementsByName("ex_telephone")[0].value;               
             var ex_email = document.getElementsByName("ex_email")[0].value;              
             var ex_role_name = document.getElementsByName("ex_role_name")[0].value;              
             var ex_ps_name = document.getElementsByName("ex_ps_name")[0].value;
               
		    if(flag==0){//flag为0表示当前为新建用户
		    	if(!checkUserInformation(user_name,password,repassword,role_name,ps_name,telephone)){return false; }//校验用户信息
		    	else{
		    		
           		 $.ajax({
               
	                url:'addUser.action?user_name='+encodeURI(encodeURI(user_name))+'&name='+encodeURI(encodeURI(name))+
	        			'&password='+encodeURI(encodeURI(password))+'&telephone='+encodeURI(encodeURI(telephone))+'&email='+encodeURI(encodeURI(email))+
	       				'&role_name='+encodeURI(encodeURI(role_name))+'&ps_name='+encodeURI(encodeURI(ps_name)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
		                    var result = obj[0];
		                    if (result.errorMsg){
		                        $.messager.show({
		                            title: 'Error',
		                            msg: result.errorMsg
		                        });
		                    } else {
		                        $('#dlg').dialog('close');        // close the dialog
		                        $('#dg').datagrid('reload');    // reload the user data
		                    }
		                    $.messager.confirm('提示','新建用户成功！');
	                    }
	            	});
	            }
		    }
		    if(flag==1){//flag为1表示当前为编辑用户
		    	if(!checkUpdateUserInformation(user_name,password,repassword,role_name,ps_name,ex_user_name,ex_ps_name,ex_password,telephone)){return false;}//校验用户信息
		    	else{
		    	$.ajax({
			        url:'updateUser.action?user_name='+encodeURI(encodeURI(user_name))+'&name='+encodeURI(encodeURI(name))+
			        '&password='+encodeURI(encodeURI(password))+'&telephone='+encodeURI(encodeURI(telephone))+'&email='+encodeURI(encodeURI(email))+
			        '&role_name='+encodeURI(encodeURI(role_name))+'&ps_name='+encodeURI(encodeURI(ps_name))+
			        '&ex_user_name='+encodeURI(encodeURI(ex_user_name))+'&ex_ps_name='+encodeURI(encodeURI(ex_ps_name)),
					type:'GET',
					dataType:'json',
					async:false,
				  	success:function(){
				  	 	$('#dlg').dialog('close');        // close the dialog
		                $('#dg').datagrid('reload'); 
	  		
					  	}
				    });	
		    	}
		    	 $.messager.confirm('提示','编辑用户信息成功！');
		    	flag = 0;
		    }          
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要删除该用户吗?',function(r){
                    if (r){
	                    $.ajax({
	                    	 url:'deleteUser.action?id='+row.id,
							 type:'GET',
					         dataType:'json',
					         async:false,
				  	         success:function(){
				  		         $('#dg').datagrid('reload'); 
				  	          }                 
	                    });
	                  }
           
                });
             }
        }
        function checkUpdateUserInformation(user_name,password,repassword,role_name,ps_name,ex_user_name,ex_ps_name,ex_password,telephone){
			if(user_name==""){$.messager.alert('提示','用户名不能为空!');return false;}
			//判断用户名不能为空
			if(password == ex_password);
			else if((password=="") || (repassword=="")){$.messager.alert('提示','密码或者重复密码不能为空!');return false;}
			else if(password!= repassword){$.messager.alert('提示','密码不一致!');return false;}
			if(role_name==""){$.messager.alert('提示','角色名称不能为空!');return false;}
			if(ps_name==""){$.messager.alert('提示','电站名称不能为空!');return false;}
			if(isNaN(telephone)==true)
		    {
		    	$.messager.alert('提示','联系电话请输入数字!');
		    	return false;
		    }
			//判断密码是否被修改，若被修改则需保持与确认密码一致且不能为空，若没有修改，则不作为
			if(user_name == ex_user_name && ps_name == ex_ps_name)return true;//若没有修改用户名和所属电站，则不用判断用户名是否已存在
			else if(user_name == ex_user_name && ps_name == "全部" && ex_ps_name == "全部")return true;
			else 
			{	var result="";
				$.ajax({
		        url:'checkUserNameIsLegal.action?user_name='+encodeURI(encodeURI(user_name))
	             +'&ps_name='+encodeURI(encodeURI(ps_name)),
			     type:'GET',				
				dataType:'json',
				async:false,
			  	success:function(obj){
			  		 result = obj[0];
			  	}
		    });
				if(result == "wrong"){$.messager.confirm('警告','用户名已存在');return false;}
				if(result=="correct"){return true;} 
			}
			 return false;
		}
		
       
       function checkUserInformation(user_name,password,repassword,role_name,ps_name,telephone){
			
		    if(user_name==""){$.messager.alert('提示','用户名不能为空!');return false;}
			if((password=="") || (repassword=="")){$.messager.alert('提示','密码不能为空!');return false;}
			if(password!= repassword){$.messager.alert('提示','密码不一致!');return false;}
			if(role_name==""){$.messager.alert('提示','角色名称不能为空!');return false;}
			if(ps_name==""){$.messager.alert('提示','电站名称不能为空!');return false;}

			if(isNaN(telephone)==true)
		    {
		    	$.messager.alert('提示','联系电话请输入数字!');
		    	return false;
		    }
	
			var result="";
			$.ajax({
	        url:'checkUserNameIsLegal.action?user_name='+encodeURI(encodeURI(user_name))
	             +'&ps_name='+encodeURI(encodeURI(ps_name)),
			type:'GET',				
			dataType:'json',
			async:false,
		  	success:function(obj){
		  		 result = obj[0];
		  	}
	    });
			if(result == "wrong"){$.messager.confirm('警告','用户名已存在');return false;}
			if(result=="correct"){return true;}
			
		    return false;
		}
       
       
		function trim(str){//去掉两边空格   
   			 return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');   
        }
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
</body>

</html>
