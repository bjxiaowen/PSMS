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
    <base href="<%=basePath%>inspection.jsp">
    <title>运维管理</title>
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
   <p style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">故障管理->运维管理</p>
    <table id="dg" title="运维管理列表" class="easyui-datagrid" style="width:100%;height:95%;text-align:center" 
            url="getAllInspectionManager.action"        
            toolbar="#toolbar" pagination="true" pageSize=20 pageList="[ 20, 30, 40 ]" //读取分页条数，即向后台读取数据时传过去的值
            autoRowHeight="true" striped="true" rownumbers="true" fitColumns="true"
             singleSelect="true" sortName="areaId" sortOrder="asc" remoteSort="flase">
        <thead>
            <tr> 
                <th field="manageId" hidden="hidden" width="20">编号</th> 
                <th field="areaId" hidden="hidden" width="25">区域ID</th>
                <th field="userId" hidden="hidden" width="20">人员ID</th>
                <th field="psId" hidden="hidden" width="20">电站ID</th>
                <th field="areaName" width="20" align="center" sortable="true">所属区域</th>
                <th field="equipmentId" width="20" hidden="hidden" align="center" sortable="true">设备ID</th>
                <th field="userName" width="20" align="center" sortable="true">负责人</th>
                <th field="psName" width="20" align="center" sortable="true">电站名称</th>
                <th field="currDate" width="20" align="center" sortable="true">开始巡检日期</th>
                <th field="inspectionPeriod" width="20" align="center" sortable="true">巡检周期</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newLink()">新建巡检</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editLink()">编辑巡检</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyLink()">删除巡检</a>
    	<select class="easyui-combobox" name="search-station" style="width:160px;" data-options=" panelHeight:'auto'" >
        	<option value="">根据电站查询</option>       
        	<c:forEach items="${list_ps}" var="ps">											
				<option value="${ps.id}">${ps.name}</option>
			</c:forEach>
        </select>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"  onclick="queryInverterByPS()">查找</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:430px;height:320px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">请选择：</div>
        <form id="fm" method="post" novalidate>
        	<div class="fitem">
                <label>电站名称</label>
                <select id="psName" class="easyui-combobox" name="psName" editable="false" style="width:160px;" data-options=" panelHeight:'auto'">
               		<c:forEach items="${list_ps}" var="ps">											
						<option value="${ps.id}">${ps.name}</option>
					</c:forEach>
   			 	</select>(必填)
            </div>
        	<div class="fitem">
                <label>所属区域</label>
                <select id="areaName" class="easyui-combobox" name="areaName" editable="false" style="width:160px;" data-options=" panelHeight:'auto'">
               		 <c:forEach items="${list_area}" var="area">											
						<option value="${area.areaId}">${area.areaName}</option>
					</c:forEach>
   			 	</select>(必填)
            </div>
            <div class="fitem">
                <label>负责人员</label>
               	<select id="userName" class="easyui-combobox" name="userName" editable="false" style="width:160px;" data-options="panelHeight:'auto'" >
               		 <c:forEach items="${list_engineer}" var="engineer">	  													
						<option value="${engineer.id}">${engineer.name}</option>
					 </c:forEach>
   			 	</select>(必填)
            </div>
            <div class="fitem">
                <label>设备ID</label>
               	<select id="equipmentId" class="easyui-combobox" name="equipmentId" editable="false" style="width:160px;" data-options="panelHeight:'auto'" >
               		<c:forEach items="${list_Equipment}" var="equipment">	  													
						<option value="${equipment.id}">${equipment.id}</option>
					 </c:forEach>
   			 	</select>(必填)
            </div>
            <div class="fitem">
                <label>巡检开始日期</label>
                <input id="currDate" class="easyui-datebox" name="currDate"class="easyui-datetimebox" >
            </div>
            <div class="fitem">
                <label>巡检周期</label>
               	<select id="inspectionPeriod" class="easyui-combobox" name="inspectionPeriod" editable="false" style="width:160px;" data-options="panelHeight:'auto'" >
						<option value="30">30天</option>
						<option value="60">60天</option>
						<option value="90" selected="selected">90天</option>
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
	    var now = new Date();
		var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
		
		  function myformatter(date){
	            var y = date.getFullYear();
	            var m = date.getMonth()+1;
	            var d = date.getDate();
	            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	        }
		  
        function newLink(){
            $('#dlg').dialog('open').dialog('setTitle','关联信息');
            $('#fm').form('clear');
            $("#currDate").datebox("setValue",myformatter(d1));
  			$('#currDate').datebox('calendar').calendar({
  			    validator: function(date){
  			        return d1<=date;
  			    }
  			});
        }
        
        
        function queryInverterByPS(){
            var ps_id = document.getElementsByName("search-station")[0].value;
            var url = 'getManagerPsId.action?psId='+encodeURI(encodeURI(ps_id));
            if(ps_id!=""){
            	$.ajax({
	                url:url,
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
		                    if(obj.total==0)    				
	        				{
	        					$.messager.confirm('提示','没有符合该条件的电站');
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
                document.getElementsByName("psName")[0].value=row.psId;
                document.getElementsByName("equipmentName")[0].value=row.equipmentId;
                $('#currDate').datebox('calendar').calendar({
      			    validator: function(date){
      			        return d1<=date;
      			    }
      			});
            }
        }


        function saveLink(){          
        
	   	    var psId = trim(document.getElementsByName("psName")[0].value);
	   	    var areaId = trim(document.getElementsByName("areaName")[0].value);
	   	    var userId = trim(document.getElementsByName("userName")[0].value);
	   	 	var currDate = document.getElementsByName("currDate")[0].value;
	   	 	var equipmentId = document.getElementsByName("equipmentId")[0].value;
	   	 	var inspectionPeriod = document.getElementsByName("inspectionPeriod")[0].value;
	   	 	var v = $('#currDate').datebox('getValue'); 
	   		var s = (v.split('-'));
        	var y = parseInt(s[0],10);
        	var m = parseInt(s[1],10);
        	var d = parseInt(s[2],10);
        	var vv = new Date(y,m-1,d+Number(inspectionPeriod));
        	var yy = vv.getFullYear();  
            var mm = vv.getMonth()+1;  
            var dd = vv.getDate(); 
            var nextDate = yy+"-"+(mm<10?('0'+mm):mm)+"-"+(dd<10?('0'+dd):dd);
            console.log(psId);
            console.log(areaId);
            console.log(userId);
            console.log(equipmentId);
            console.log(inspectionPeriod);
            console.log(nextDate);
	   	 if(flag==0){//flag为0表示当前新建
			    if(!checkLinkInformation(areaId,userId,equipmentId,psId,currDate,inspectionPeriod)){return false; }//校验信息
			    else{
			    	var url='addInspectionManager.action?equipmentId='+encodeURI(encodeURI(equipmentId))+'&psId='+encodeURI(encodeURI(psId))+'&areaId='+encodeURI(encodeURI(areaId))+'&userId='+encodeURI(encodeURI(userId))+'&currDate='+encodeURI(encodeURI(currDate))+'&inspectionPeriod='+encodeURI(encodeURI(inspectionPeriod))+'&nextDate='+encodeURI(encodeURI(nextDate))
			    $.ajax({
	                url:url,
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
		    	if(!checkUpdateLinkInformation(areaId,userId,equipmentId,psId,currDate,inspectionPeriod,row)){return false;}//校验信息
		    	else{
		    		var url='updateInspectionManager.action?id='+encodeURI(encodeURI(row.manageId))+'&equipmentId='+encodeURI(encodeURI(equipmentId))+'&psId='+encodeURI(encodeURI(psId))+'&areaId='+encodeURI(encodeURI(areaId))+'&userId='+encodeURI(encodeURI(userId))+'&currDate='+encodeURI(encodeURI(currDate))+'&inspectionPeriod='+encodeURI(encodeURI(inspectionPeriod))+'&nextDate='+encodeURI(encodeURI(nextDate))
				    console.log(url);
		    	$.ajax({
			        url:url,
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
	                    	 url:'deleteInspectionManager.action?id='+row.manageId,
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
        function checkLinkInformation(areaId,userId,equipmentId,psId,currDate,inspectionPeriod){
        	if(psId==""){$.messager.confirm('警告','所属电站不能为空');return false;}
        	if(areaId==""){$.messager.confirm('警告','所属区域不能为空');return false;}
			if(userId==""){$.messager.confirm('警告','负责人不能为空');return false;}
			if(equipmentId==""){$.messager.confirm('警告','设备ID不能为空');return false;}
			if(currDate==""){$.messager.confirm('警告','开始日期不能为空');return false;}
			if(inspectionPeriod==""){$.messager.confirm('警告','巡检周期不能为空');return false;}
		
			var result="";
			$.ajax({
	        url:'getCheckById.action?areaId='+encodeURI(encodeURI(areaId))
	             +'&userId='+encodeURI(encodeURI(userId))+'&psId='+encodeURI(encodeURI(psId))+'&equipmentId='+encodeURI(encodeURI(equipmentId)),
			type:'GET',				
			dataType:'json',
			async:false,
		  	success:function(obj){
		  		console.log(obj);
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
        
        function checkUpdateLinkInformation(areaId,userId,equipmentId,psId,currDate,inspectionPeriod,row){
			if(areaId==""){$.messager.confirm('警告','所属区域不能为空');return false;}
			if((userId=="")){$.messager.confirm('警告','负责人不能为空');return false;}
			console.log(inspectionPeriod);
			console.log(row.inspectionPeriod);
			if(areaId == row.areaId && userId == row.userId && equipmentId == row.equipmentId && psId == row.psId && currDate == row.currDate && inspectionPeriod == row.inspectionPeriod)return true;//若没有修改名和所属电站，则不用判断名是否已存在
			else 
			{	
				var result="";
				$.ajax({
			        url:'getCheckById.action?areaId='+encodeURI(encodeURI(areaId))
			             +'&userId='+encodeURI(encodeURI(userId))+'&psId='+encodeURI(encodeURI(psId))+'&equipmentId='+encodeURI(encodeURI(equipmentId)),
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
