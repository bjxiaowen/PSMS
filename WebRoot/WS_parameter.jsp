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
    <base href="<%=basePath%>WS_parameter.jsp">
    <title>Build CRUD Application with edit form in expanded row details - jQuery EasyUI Demo</title>
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
   <p style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">设备管理->气象站</p>
    <table id="dg" title="气象站列表" class="easyui-datagrid" style="width:100%;height:95%;text-align:center" 
            url="getWSInformation.action"        
            toolbar="#toolbar" pagination="true" pageSize=20 pageList="[ 20, 30, 40 ]" //读取分页条数，即向后台读取数据时传过去的值
            autoRowHeight="true" striped="true" rownumbers="true" fitColumns="true"
             singleSelect="true" sortName="id" sortOrder="asc" remoteSort="flase">
        <thead>
            <tr> 
                <th field="id" hidden="hidden" width="20">编号</th>  
                <th field="ps_name" width="25" align="center" sortable="true">所属电站</th>
                <th field="name" width="20" align="center" sortable="true">设备名称</th>
                <th field="type" width="20" align="center" >设备类型</th>
                <th field="brand" width="20" align="center" sortable="true">设备品牌</th>
                <th field="model" width="20" align="center">设备型号</th>
                <th field="purchase_time" width="30" align="center" sortable="true">购买时间</th>
                <th field="max_temperature" width="15" align="center" sortable="true">最大承受温度</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newWS()">新建设备</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editWS()">编辑设备</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyWS()">删除设备</a>
        <input id="search_name" name="search_name" class="easyui-searchbox" data-options="prompt:'根据气象站名查询',searcher:doSearch" style="width:130px"></input>
        <select class="easyui-combobox"  name="search-station" style="width:160px;" data-options="panelHeight:'auto'">
  
        <option value="">根据电站查询</option>
        	<c:forEach items="${list_station_name}" var="list3">	  													
				<option value="${list3}">${list3}</option>
			</c:forEach>
        </select>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"  onclick="queryWSByPS_name()">查找</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:430px;height:380px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">设备信息</div>
        <form id="fm" method="post" novalidate  style="height:">
        	<div class="fitem">
                <label>所属电站</label>
                <select id="ps_name" class="easyui-combobox" name="ps_name" editable="false" style="width:160px;" data-options=" panelHeight:'auto'" >
               		 <c:forEach items="${list_station_name}" var="list2">	  													
						<option value="${list2}">${list2}</option>
					 </c:forEach>
   			 	</select>(必填)
   			 	<input id="ex_ps_name" name="ex_ps_name" type="hidden"></input> 
            </div>
            <div class="fitem">
                <label>设备名称</label>
                <input id="name" name="name" class="easyui-textbox" >(必填)</input>
                <input id="ex_name" name="ex_name" type="hidden" ></input>  
            </div>
            <div class="fitem">
                <label>设备类型</label>
                 <input id="type" name="type" class="easyui-textbox" ></input>
                 <input id="ex_type" name="ex_type" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>设备品牌</label>
               		 <select id="brand" class="easyui-combobox" editable="false" name="brand" style="width:160px;" data-options="panelHeight:'auto', onSelect:setModelByTypeAndBrand" >
               		 <c:forEach items="${list_brand}" var="list7">	  													
						<option value="${list7}">${list7}</option>
					 </c:forEach>
   			 	</select>
                <input id="ex_brand" name="ex_brand" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>设备型号</label>
                <select id="model" class="easyui-combobox" editable="false" name="model" style="width:160px;" data-options=" panelHeight:'auto',  valueField: 'id',textField: 'text'">
   			 	</select>(先选品牌)
                <input id="ex_model" name="ex_model" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>购买时间</label>
                <input id="purchase_time" name="purchase_time"class="easyui-datebox" >
                <input id="ex_purchase_time" name="ex_purchase_time" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>最大承受温度</label>
                <input id="max_temperature" name="max_temperature"  class="easyui-textbox" > 
                <input id="ex_max_temperature" name="ex_max_temperature" type="hidden" ></input> 
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveWS()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
    	$('#dt').datetimebox('setValue', purchase_time);
        var url;
        var flag = 0;
        function newWS(){
            $('#dlg').dialog('open').dialog('setTitle','新建设备');
            $('#fm').form('clear');
            $('#type').textbox('setValue','气象站');
            $('#type').textbox('readonly',true);
            
            $('#model').combobox('loadData',[]); //重新选择设备类型后，需要清空设备型号
        	var curr_time = new Date();
  		    var strDate = curr_time.getFullYear()+"-";
  			strDate += curr_time.getMonth()+1+"-";
  			strDate += curr_time.getDate();
  			$('#purchase_time').datebox('setValue', strDate); //,默认购买时间为当天
        }
        
        function setModelByTypeAndBrand(rec)
       {var type="气象站";
       	var brand=rec.value;
       			$.ajax({
			        url:'setModelByTypeAndBrandW.action?brand='+encodeURI(encodeURI(brand))+'&type='+encodeURI(encodeURI(type)),			       
					type:'GET',
					dataType:'json',
					async:false,
			  	    success:function(obj){
			  	    		$('#model').combobox('clear'); 
			  	  			var data = [];
				  		    for(var i=0;i<obj[0].length;i++){
		  						var model = obj[0][i];		  						
								data.push({ "text":model, "id":model });
			  				}
							 $('#model').combobox('loadData',data);    
				  		           		       				
				  	   }
				    });
			}	  
        
        function doSearch(value){
            var name = value;
            if(name!=""){
            	$.ajax({
	                url:'queryWSByName.action?name='+encodeURI(encodeURI(name)),
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
           $('#search_name').searchbox('clear'); 
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
        
        function queryWSByPS_name(){
            var ps_name = document.getElementsByName("search-station")[0].value;
            var name = document.getElementsByName("search_name")[0].value;
            if(ps_name!=""||name!=""){
            
            	$.ajax({
	                url:'queryWSByPS_name.action?ps_name='+encodeURI(encodeURI(ps_name))
	                +'&name='+encodeURI(encodeURI(name)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
		                    if(obj.total==0)    				
	        				{
	        					$.messager.confirm('提示','没有符合该条件的气象站');
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
        function editWS(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	flag = 1;
                $('#dlg').dialog('open').dialog('setTitle','编辑设备');
                $('#fm').form('load',row); 
                $('#type').textbox('setValue','气象站');
                $('#type').textbox('readonly',true);
                var ex_ps_name = document.getElementsByName("ex_ps_name");
                ex_ps_name[0].value= row.ps_name;
                var ex_name = document.getElementsByName("ex_name");
                ex_name[0].value= row.name;
                var ex_type = document.getElementsByName("ex_type");
                ex_type[0].value= row.type;
                var ex_brand = document.getElementsByName("ex_brand");
                ex_brand[0].value= row.brand;
                var ex_model = document.getElementsByName("ex_model");
                ex_model[0].value= row.model;
                var ex_purchase_time = document.getElementsByName("ex_purchase_time");
                ex_purchase_time[0].value= row.purchase_time;
                var ex_max_temperature = document.getElementsByName("ex_max_temperature");
                ex_max_temperature[0].value= row.max_temperature;
                var type="气象站";
                var brand=row.brand;
                 $.ajax({
 			        url:'setModelByTypeAndBrandW.action?brand='+encodeURI(encodeURI(brand))+'&type='+encodeURI(encodeURI(type)),			       
 					type:'GET',
 					dataType:'json',
 					async:false,
 			  	    success:function(obj){
 			  	    	var data = [];
			  		    for(var i=0;i<obj[0].length;i++){
	  						var model = obj[0][i];		  						
							data.push({ "text":model, "id":model });
		  				}
						 $('#model').combobox('loadData',data);	   
 				  		           		       				
 				  	   }
 				    });               
              $('#fm').form('load',row); 
            }
        }
        function saveWS(){          
        
            var ps_name = document.getElementsByName("ps_name")[0].value;
	   	    var name = trim(document.getElementsByName("name")[0].value);
	   	    var type = trim(document.getElementsByName("type")[0].value);
	        var brand = trim(document.getElementsByName("brand")[0].value);
	        var model = trim(document.getElementsByName("model")[0].value);
	        var purchase_time = document.getElementsByName("purchase_time")[0].value;
            var max_temperature = trim(document.getElementsByName("max_temperature")[0].value);  
		    //--------------------------以上为获取新建的值并且去掉输入值两边的空格
		    var ex_ps_name = document.getElementsByName("ex_ps_name")[0].value;               
            var ex_name = document.getElementsByName("ex_name")[0].value;              
            var ex_type = document.getElementsByName("ex_type")[0].value;             
            var ex_brand = document.getElementsByName("ex_brand")[0].value;               
            var ex_model = document.getElementsByName("ex_model")[0].value;              
            var ex_purchase_time = document.getElementsByName("ex_purchase_time")[0].value;              
            var ex_max_temperature = document.getElementsByName("ex_max_temperature")[0].value;	
            	
            if(flag==0){//flag为0表示当前为新建逆变器
		    if(!checkWSInformation(ps_name,name,max_temperature)){return false; }//校验逆变器信息
		    else{
           		 $.ajax({
                url:'addWS.action?ps_name='+encodeURI(encodeURI(ps_name))+'&name='+encodeURI(encodeURI(name))+
        			'&type='+encodeURI(encodeURI(type))+'&brand='+encodeURI(encodeURI(brand))+'&model='+encodeURI(encodeURI(model))+
       				'&purchase_time='+encodeURI(encodeURI(purchase_time))+'&max_temperature='+max_temperature,
       				
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
	                    $.messager.confirm('提示','新建气象站成功！');
                    }
            });
            }
          }
          
          if(flag==1){//flag为1表示当前为编辑用户
          var row = $('#dg').datagrid('getSelected');
		    	if(!checkUpdateWSInformation(ps_name,name,ex_ps_name,ex_name,max_temperature)){return false;}//校验逆变器信息
		    	else{
		    	$.ajax({
			        url:'addWS.action?id='+encodeURI(encodeURI(row.id))+'&ps_name='+encodeURI(encodeURI(ps_name))+'&name='+encodeURI(encodeURI(name))+
        			'&type='+encodeURI(encodeURI(type))+'&brand='+encodeURI(encodeURI(brand))+'&model='+encodeURI(encodeURI(model))+
       				'&purchase_time='+encodeURI(encodeURI(purchase_time))+'&max_temperature='+max_temperature+
       				'&ex_name='+encodeURI(encodeURI(ex_name))+'&ex_ps_name='+encodeURI(encodeURI(ex_ps_name)),
			       
					type:'GET',
					dataType:'json',
					async:false,
				  	success:function(){
				  	 	$('#dlg').dialog('close');        // close the dialog
		                $('#dg').datagrid('reload'); 
					  	}
				    });	
		    	}
		    	$.messager.confirm('提示','编辑气象站成功！');
		    	flag = 0;
		    } 
        }
        function destroyWS(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','确定要删除这个气象站吗？',
                function(r){
                    if (r){
	                    $.ajax({
	                    	 url:'deleteWS.action?id='+row.id,
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
        function checkWSInformation(ps_name,name,max_temperature){
	
			if(ps_name==""){$.messager.confirm('警告','所属电站不能为空');return false;}
			if((name=="")){$.messager.confirm('警告','气象站名不能为空');return false;}
			if( isNaN(max_temperature)==true)
		    {
		    	$.messager.alert('提示','最大承受温度请输入数字!');
		    	return false;
		    }
			var result = "";
			$.ajax({
		        url:'checkWSNameIsLegal.action?name='+encodeURI(encodeURI(name))
		             +'&ps_name='+encodeURI(encodeURI(ps_name)),
				type:'GET',				
				dataType:'json',
				async:false,
			  	success:function(obj){
			  		  result = obj[0];
			  	}
		    });	
			if(result == "wrong"){$.messager.confirm('警告','气象站名已存在');return false;}
		    if(result == "correct"){return true;}
		    else{return false;}
		}
		function trim(str){//去掉两边空格   
   			 return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');   
        }
        
        function checkUpdateWSInformation(ps_name,name,ex_ps_name,ex_name,max_temperature){
			if(ps_name==""){$.messager.confirm('警告','所属电站不能为空');return false;}
			if((name=="")){$.messager.confirm('警告','气象站名不能为空');return false;}
			if( isNaN(max_temperature)==true)
		    {
		    	$.messager.alert('提示','最大承受温度请输入数字!');
		    	return false;
		    }
			//判断气象站名和所属电站不能为空
			if(name == ex_name && ps_name == ex_ps_name)return true;//若没有修改逆变器名和所属电站，则不用判断逆变器名是否已存在
			else 
			{
				var result = "";
				$.ajax({
			        url:'checkWSNameIsLegal.action?name='+encodeURI(encodeURI(name))
			             +'&ps_name='+encodeURI(encodeURI(ps_name)),
					type:'GET',				
					dataType:'json',
					async:false,
				  	success:function(obj){
				  		  result = obj[0];
				  	}
			    });	
				if(result == "wrong"){$.messager.confirm('警告','气象站名已存在');return false;}
			    if(result == "correct"){return true;}
			    
			}
			 return false;
		}
		
		function check(){
		  if (isNaN(max_temperature.value))
		  {$.messager.confirm('提示','非法字符，请输入数字');
		  max_temperature.value="";}
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
