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
    <base href="<%=basePath%>Inverter_parameter.jsp">
    <title>工程师区域关系表</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyui/demo/demo.css">
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/datagrid-detailview.js"></script>
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script> 
    <script type="text/javascript">  
    </script>
    <style>
    	body{
    		border:0px;padding:5px;
    	}
    	
    </style>
</head>
<body>
   <p style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">人员管控->区域人员关联表</p>
    <table id="dg" title="区域人员列表" class="easyui-datagrid" style="width:100%;height:95%;text-align:center" 
            url="getAll.action"        
            toolbar="#toolbar" pagination="true" pageSize=20 pageList="[ 20, 30, 40 ]" //读取分页条数，即向后台读取数据时传过去的值
            autoRowHeight="true" striped="true" rownumbers="true" fitColumns="true"
             singleSelect="true" sortName="areaId" sortOrder="asc" remoteSort="flase">
        <thead>
            <tr> 
                <th field="id" hidden="hidden" width="20">编号</th> 
                <th field="areaId" hidden="hidden" width="25">区域ID</th>
                <th field="userId" hidden="hidden" width="20">人员ID</th>
                <th field="areaName" width="20" align="center" sortable="true">所属区域</th>
                <th field="userName" width="20" align="center" sortable="true">负责人</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newLink()">新建关联</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editLink()">编辑关联</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyLink()">删除关联</a>
        <input id="search_name" name="search_name" class="easyui-searchbox" data-options="prompt:'根据区域名称查询',searcher:doSearch" style="width:150px"></input>
        <input id="search_name2" name="search_name2" class="easyui-searchbox" data-options="prompt:'根据人员名称查询',searcher:doSearch2" style="width:150px"></input>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:430px;height:250px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">请选择：</div>
        <form id="fm" method="post" novalidate  style="height:">
        	<div class="fitem">
                <label>所属区域</label>
                <select id="areaName" class="easyui-combobox" name="areaName" editable="false" style="width:160px;" data-options=" panelHeight:'auto'">
               		 <c:forEach items="${list_Area}" var="list2">	  													
						<option value="${list2.areaId}">${list2.areaName}</option>
					 </c:forEach>
   			 	</select>(必填)
            </div>
            <div class="fitem">
                <label>负责人员</label>
               		 <select id="userName" class="easyui-combobox" name="userName" editable="false" style="width:160px;" data-options="panelHeight:'auto'" >
               		 <c:forEach items="${list_User}" var="user">	  													
						<option value="${user.id}">${user.name}</option>
					 </c:forEach>
   			 	</select>(必填)
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveLink()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        var flag = 0;
        function newLink(){
            $('#dlg').dialog('open').dialog('setTitle','关联信息');
            $('#fm').form('clear');
        }
         
        
        function doSearch(value){
            var name = value;
            if(name!=""){
            	$.ajax({
	                url:'searchByAreaName.action?areaName='+encodeURI(encodeURI(name)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
				             if(obj.total==0)    				
	        				{
	        					$.messager.confirm('提示','没有符合该条件的区域');
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
           $('#search_name').searchbox('clear'); 
        }
        
        
        
        function doSearch2(value){
            var name = value;
            if(name!=""){
            	$.ajax({
	                url:'searchByUserName.action?userName='+encodeURI(encodeURI(name)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
				             if(obj.total==0)    				
	        				{
	        					$.messager.confirm('提示','没有符合该条件的人员');
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
           $('#search_name2').searchbox('clear'); 
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
        
        
        function editLink(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	flag = 1;
                $('#dlg').dialog('open').dialog('setTitle','编辑关联');
                $('#fm').form('load',row); 
                document.getElementsByName("areaName")[0].value=row.areaId;
                document.getElementsByName("userName")[0].value=row.userId;
            }
        }


        function saveLink(){          
        
	   	    var areaId = trim(document.getElementsByName("areaName")[0].value);
	   	    var userId = trim(document.getElementsByName("userName")[0].value);
	   	    
	   	 if(flag==0){//flag为0表示当前新建
			    if(!checkLinkInformation(areaId,userId)){return false; }//校验信息
			    else{
	           		 $.ajax({
	                url:'addReEngineerArea.action?areaId='+encodeURI(encodeURI(areaId))+'&userId='+encodeURI(encodeURI(userId)),
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
		                    $.messager.confirm('提示','新建关联成功！'); 
	                    }
	            });
	            }
	          }
	   	 
          if(flag==1){//flag为1表示当前为编辑用户
          var row = $('#dg').datagrid('getSelected');
		    	if(!checkUpdateLinkInformation(areaId,userId,row)){return false;}//校验信息
		    	else{
		    	$.ajax({
			        url:'updateReEngineerArea.action?id='+encodeURI(encodeURI(row.id))+'&areaId='+encodeURI(encodeURI(areaId))+'&userId='+encodeURI(encodeURI(userId)),
					type:'GET',
					dataType:'json',
					async:false,
				  	success:function(){
				  	 	$('#dlg').dialog('close');        // close the dialog
		                $('#dg').datagrid('reload'); 
					  	}
				    });	
		    	}
		    	$.messager.confirm('提示','编辑成功！');
		    	flag = 0;
		    } 
        }
        function destroyLink(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','确定要删除这个关联吗？',
                function(r){
                    if (r){
	                    $.ajax({
	                    	 url:'deleteReEngineerArea.action?id='+row.id,
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
        function checkLinkInformation(areaId,userId){
			if(areaId==""){$.messager.confirm('警告','所属区域不能为空');return false;}
			if(userId==""){$.messager.confirm('警告','负责人不能为空');return false;}
		
			var result="";
			$.ajax({
	        url:'checkByAreaIdAndUserId.action?areaId='+encodeURI(encodeURI(areaId))
	             +'&userId='+encodeURI(encodeURI(userId)),
			type:'GET',				
			dataType:'json',
			async:false,
		  	success:function(obj){
		  		 result = obj[0];
		  	}
	    });
			if(result == "wrong"){$.messager.confirm('警告','此条目已在列表中，请重新选择');return false;}
			if(result=="correct"){return true;}
			
		    return false;
		}
		function trim(str){//去掉两边空格   
   			 return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');   
        }
        
        function checkUpdateLinkInformation(areaId,userId,row){
			if(areaId==""){$.messager.confirm('警告','所属区域不能为空');return false;}
			if((userId=="")){$.messager.confirm('警告','负责人不能为空');return false;}
			if(areaId == row.areaId && userId == row.userId)return true;//若没有修改名和所属电站，则不用判断名是否已存在
			else 
			{	
				var result="";
				$.ajax({
			        url:'checkByAreaIdAndUserId.action?areaId='+encodeURI(encodeURI(areaId))
			             +'&userId='+encodeURI(encodeURI(userId)),
					type:'GET',				
					dataType:'json',
					async:false,
				  	success:function(obj){
				  		 result = obj[0];
				  	}
		    	});
				if(result == "wrong"){$.messager.confirm('警告','此条目已在列表中，请重新选择');return false;}
				if(result=="correct"){return true;}
			}
		    return false;
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
