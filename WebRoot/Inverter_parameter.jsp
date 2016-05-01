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
   <p style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">设备管理->设备信息</p>
    <table id="dg" title="设备列表" class="easyui-datagrid" style="width:100%;height:95%;text-align:center" 
            url="getInverterInformation.action"        
            toolbar="#toolbar" pagination="true" pageSize=20 pageList="[ 20, 30, 40 ]" //读取分页条数，即向后台读取数据时传过去的值
            autoRowHeight="true" striped="true" rownumbers="true" fitColumns="true"
             singleSelect="true" sortName="id" sortOrder="asc" remoteSort="flase">
        <thead>
            <tr> 
                <th field="id" hidden="hidden" width="20">编号</th>  
                <th field="ps_name" width="25" align="center" sortable="true">所属电站</th>
                <th field="name" width="20" align="center" sortable="true">设备id</th>
                <th field="brand" width="20" align="center" sortable="true">设备品牌</th>
                <th field="type" width="20" align="center" >设备类型</th>
                <th field="model" width="20" align="center" sortable="true">设备型号</th>
                <th field="purchase_time" width="30" align="center" sortable="true">购买时间</th>
                <th field="rate_power" width="15" align="center" sortable="true">额定功率</th>
                <th field="rated_voltage" width="15" align="center" sortable="true">额定电压</th>
                <th field="max_power" width="15" align="center" sortable="true">最大功率</th>
                <th field="power_factor" width="15" align="center" sortable="true">功率因数</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newInverter()">新建设备</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editInverter()">编辑设备</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyInverter()">删除设备</a>
        <input id="search_name" name="search_name" class="easyui-searchbox" data-options="prompt:'根据设备名称查询',searcher:doSearch" style="width:130px"></input>
        <select class="easyui-combobox" name="search-station" style="width:160px;" data-options=" panelHeight:'auto'" >
        	<option value="">根据电站查询</option>       
        	<c:forEach items="${list_station_name}" var="list3">	  													
				<option value="${list3}">${list3}</option>
			</c:forEach>
        </select>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"  onclick="queryInverterByPS_name()">查找</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:430px;height:450px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
        <div class="ftitle">设备信息</div>
        <form id="fm" method="post" novalidate  style="height:">
        	<div class="fitem">
                <label>所属电站</label>
                <select id="ps_name" class="easyui-combobox" name="ps_name" editable="false" style="width:160px;" data-options=" panelHeight:'auto'">
               		 <c:forEach items="${list_station_name}" var="list2">	  													
						<option value="${list2}">${list2}</option>
					 </c:forEach>
   			 	</select>(必填)
   			 	<input id="ex_ps_name" name="ex_ps_name" type="hidden"></input> 
            </div>
            <div class="fitem">
                <label>设备id</label>
                <input id="name" name="name" class="easyui-textbox" >(必填)</input>
                <input id="ex_name" name="ex_name" type="hidden" ></input>  
            </div>
          
          <div class="fitem">
                <label>设备品牌</label>
               		 <select id="brand" class="easyui-combobox" name="brand" editable="false" style="width:160px;" data-options="panelHeight:'auto', onSelect:getTypeByBrand" >
               		 <c:forEach items="${list_brand}" var="list71">	  													
						<option value="${list71}">${list71}</option>
					 </c:forEach>
   			 	</select>
                <input id="ex_brand" name="ex_brand" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>设备类型</label>
                <select id="type" class="easyui-combobox" name="type" editable="false" style="width:160px;" data-options=" panelHeight:'auto', valueField: 'id',textField: 'text', onSelect:getModelByBrandAndType" >
                </select>(必选类型)
                <input id="ex_type" name="ex_type" type="hidden" ></input> 
            </div>
              <div class="fitem">
                <label>设备型号</label>
                <select id="model" class="easyui-combobox" name="model" editable="false" style="width:160px;"  data-options=" panelHeight:'auto', valueField: 'id',textField: 'text' ">
   			 	</select>(先选品牌)
                <input id="ex_model" name="ex_model" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>购买时间</label>
                <input id="purchase_time" class="easyui-datebox" name="purchase_time"class="easyui-datetimebox" >
                <input id="ex_purchase_time" name="ex_purchase_time" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>额定功率</label>
                <input id="rate_power" class="easyui-textbox" name="rate_power"  onkeyup="check();"> 
                <input id="ex_rate_power" name="ex_rate_power" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>额定电压</label>
                <input id="rated_voltage" class="easyui-textbox" name="rated_voltage" style="width:160px;"  >
   			 	<input id="ex_rated_voltage" name="ex_rated_voltage" type="hidden" ></input> 
            </div>
             <div class="fitem">
                <label>最大功率</label>
                <input id="max_power"  class="easyui-textbox" name="max_power"  >
                <input id="ex_max_power" name="ex_max_power" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>功率因数</label>
                <input id="power_factor" class="easyui-textbox" name="power_factor" >
                <input id="ex_power_factor" name="ex_power_factor" type="hidden" ></input>  
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveInverter()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
    	$('#dt').datetimebox('setValue', purchase_time);
        var url;
        var flag = 0;
        function newInverter(){
            $('#dlg').dialog('open').dialog('setTitle','新建设备');
            $('#fm').form('clear');
/*             $('#type').textbox('setValue','逆变器');
            $('#type').textbox('readonly',true); */
            
            $('#model').combobox('loadData',[]); //重新选择设备类型后，需要清空设备型号
            $('#type').combobox('loadData',[]); 
        	var curr_time = new Date();
  		    var strDate = curr_time.getFullYear()+"-";
  			strDate += curr_time.getMonth()+1+"-";
  			strDate += curr_time.getDate();
  			$('#purchase_time').datebox('setValue', strDate); //,默认购买时间为当天
        }
         
         function setModelByTypeAndBrandI(rec){
       	        var brand=rec.value;
       			$.ajax({
			        url:'setModelByTypeAndBrandI.action?brand='+encodeURI(encodeURI(brand)),			       
					type:'GET',
					dataType:'json',
					async:false,
			  	    success:function(obj){
			  	    		$('#model').combobox('clear');//清楚设备型号
			  	  			var data = [];
				  		    for(var i=0;i<obj[0].length;i++){
		  						var model = obj[0][i];		  						
								data.push({ "text":model, "id":model });
			  				}
							$('#model').combobox('loadData',data);	   
				  	   }
				    });
			}
         
         function getTypeByBrand(rec){//通过品牌查询类型
        	 var brand=rec.value;
        	 $.ajax({
			        url:'getTypeByBrand.action?brand='+encodeURI(encodeURI(brand)),			       
					type:'GET',
					dataType:'json',
					async:false,
			  	    success:function(obj){
			  	    		$('#type').combobox('clear');//清楚设备型号
			  	    		$('#model').combobox('clear');//清楚设备型号
			  	  			var data = [];
				  		    for(var i=0;i<obj[0].length;i++){
		  						var model = obj[0][i];		  						
								data.push({ "text":model, "id":model });
			  				}
							$('#type').combobox('loadData',data);	   
				  	   }
				    });
         }
         
         function getModelByBrandAndType(){//通过品牌类型查询型号
       	 		var brand=$('#brand').combobox('getValue');
        		var type=$('#type').combobox('getValue');
        		/* 	console.log("brand:"+brand);
        			console.log("type:"+type); */
        			$.ajax({
 			        url:'getModelByBrandAndType.action?brand='+encodeURI(encodeURI(brand))+'&type='+encodeURI(encodeURI(type)),			       
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
	                url:'queryInverterByName.action?name='+encodeURI(encodeURI(name)),
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
        
        function queryInverterByPS_name(){
            var ps_name = document.getElementsByName("search-station")[0].value;
            
            var name = document.getElementsByName("search_name")[0].value;
            if(ps_name!=""||name!=""){
            	$.ajax({
	                url:'queryInverterByPS_name.action?ps_name='+encodeURI(encodeURI(ps_name))
	                +'&name='+encodeURI(encodeURI(name)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
		                    if(obj.total==0)    				
	        				{
	        					$.messager.confirm('提示','没有符合该条件的设备');
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
        function editInverter(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
            	flag = 1;
                $('#dlg').dialog('open').dialog('setTitle','编辑设备');
                $('#fm').form('load',row); 
/*                 $('#type').textbox('setValue','逆变器');
                $('#type').textbox('readonly',true); */
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
                var ex_rate_power = document.getElementsByName("ex_rate_power");
                ex_rate_power[0].value= row.rate_power;
                var ex_rated_voltage = document.getElementsByName("ex_rated_voltage");
                ex_rated_voltage[0].value= row.rated_voltage;
                var ex_max_power = document.getElementsByName("ex_max_power");
                ex_max_power[0].value= row.max_power;
                var ex_power_factor = document.getElementsByName("ex_power_factor");
                ex_power_factor[0].value= row.power_factor;
                var brand=row.brand;
                 $.ajax({
 			        url:'setModelByTypeAndBrandI.action?brand='+encodeURI(encodeURI(brand)),			       
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
        function saveInverter(){          
            var ps_name = document.getElementsByName("ps_name")[0].value;
	   	    var name = trim(document.getElementsByName("name")[0].value);
	   	    var type = trim(document.getElementsByName("type")[0].value);
	        var brand = trim(document.getElementsByName("brand")[0].value);
	        //var model = trim(document.getElementsByName("model")[0].value);
	        var model=$('#model').combobox('getValue');
	        console.log("model:"+model);
	        var purchase_time = document.getElementsByName("purchase_time")[0].value;
	        var rate_power = trim(document.getElementsByName("rate_power")[0].value); 
            var rated_voltage = trim(document.getElementsByName("rated_voltage")[0].value);
            var max_power = trim(document.getElementsByName("max_power")[0].value);  
            var power_factor = trim(document.getElementsByName("power_factor")[0].value);  
		    //--------------------------以上为获取新建的值并且去掉输入值两边的空格
		    var ex_ps_name = document.getElementsByName("ex_ps_name")[0].value;               
            var ex_name = document.getElementsByName("ex_name")[0].value;              
            var ex_type = document.getElementsByName("ex_type")[0].value;             
            var ex_brand = document.getElementsByName("ex_brand")[0].value;               
            var ex_model = document.getElementsByName("ex_model")[0].value;              
            var ex_purchase_time = document.getElementsByName("ex_purchase_time")[0].value;              
            var ex_rate_power = document.getElementsByName("ex_rate_power")[0].value;	 
            var ex_rated_voltage = document.getElementsByName("ex_rated_voltage")[0].value;
            var ex_max_power = document.getElementsByName("ex_max_power")[0].value;	
            var ex_power_factor = document.getElementsByName("ex_power_factor")[0].value;
            	
            if(flag==0){//flag为0表示当前为新建
		    if(!checkInverterInformation(ps_name,name,rate_power,rated_voltage,max_power,power_factor)){//校验逆变器信息
		    	return false; 
		    	
		    }else{
           		 $.ajax({
                 url:'addInverter.action?ps_name='+encodeURI(encodeURI(ps_name))+'&name='+encodeURI(encodeURI(name))+
        			'&type='+encodeURI(encodeURI(type))+'&brand='+encodeURI(encodeURI(brand))+'&model='+encodeURI(encodeURI(model))+
       				'&purchase_time='+encodeURI(encodeURI(purchase_time))+'&rate_power='+rate_power+
       				'&rated_voltage='+rated_voltage+'&max_power='+max_power+
       				'&power_factor='+power_factor,
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
	                    $.messager.confirm('提示','新建设备成功！'); 
                    }
            });
            }
          }
          
          if(flag==1){//flag为1表示当前为编辑用户
          var row = $('#dg').datagrid('getSelected');
		    	if(!checkUpdateInverterInformation(ps_name,name,ex_ps_name,ex_name,rate_power,rated_voltage,max_power,power_factor)){return false;}//校验逆变器信息
		    	else{
		    	$.ajax({
			        url:'addInverter.action?id='+encodeURI(encodeURI(row.id))+'&ps_name='+encodeURI(encodeURI(ps_name))+'&name='+encodeURI(encodeURI(name))+
        			'&type='+encodeURI(encodeURI(type))+'&brand='+encodeURI(encodeURI(brand))+'&model='+encodeURI(encodeURI(model))+
       				'&purchase_time='+encodeURI(encodeURI(purchase_time))+'&rate_power='+rate_power+
       				'&rated_voltage='+rated_voltage+'&max_power='+max_power+
       				'&power_factor='+power_factor+'&ex_name='+encodeURI(encodeURI(ex_name))+
       				'&ex_ps_name='+encodeURI(encodeURI(ex_ps_name)),
					type:'GET',
					dataType:'json',
					async:false,
				  	success:function(){
				  	 	$('#dlg').dialog('close');        // close the dialog
		                $('#dg').datagrid('reload'); 
					  	}
				    });	
		    	}
		    	$.messager.confirm('提示','编辑逆变器成功！');
		    	flag = 0;
		    } 
        }
        function destroyInverter(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','确定要删除这个逆变器吗？',
                function(r){
                    if (r){
	                    $.ajax({
	                    	 url:'deleteInverter.action?id='+row.id,
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
        
        //校验
        function checkInverterInformation(ps_name,name,rate_power,rated_voltage,max_power,power_factor){
	
			if(ps_name==""){$.messager.confirm('警告','所属电站不能为空');return false;}
			if((name=="")){$.messager.confirm('警告','设备id不能为空');return false;}
			if( isNaN(rate_power)==true){
		    	$.messager.alert('提示','额定功率请输入数字!');
		    	return false;
		    }
		    if( isNaN(rated_voltage)==true){
		    	$.messager.alert('提示','额定电压请输入数字!');
		    	return false;
		    }
		    if( isNaN(max_power)==true){
		    	$.messager.alert('提示','最大功率请输入数字!');
		    	return false;
		    }
		    if( isNaN(power_factor)==true){
		    	$.messager.alert('提示','功率因数请输入数字!');
		    	return false;
		    }
			var result="";
			$.ajax({
	        url:'checkInverterNameIsLegal.action?name='+encodeURI(encodeURI(name))+'&ps_name='+encodeURI(encodeURI(ps_name)),
			type:'GET',				
			dataType:'json',
			async:false,
		  	success:function(obj){
		  		 result = obj[0];
		  		}
	    	});
			if(result == "wrong"){$.messager.confirm('警告','设备在此电站中已存在');return false;}
			if(result=="correct"){return true;}
		    return false;
		}
		function trim(str){//去掉两边空格   
   			 return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');   
        }
        
        function checkUpdateInverterInformation(ps_name,name,ex_ps_name,ex_name,rate_power,rated_voltage,max_power,power_factor){
			if(ps_name==""){$.messager.confirm('警告','所属电站不能为空');return false;}
			if((name=="")){$.messager.confirm('警告','逆变器名不能为空');return false;}
			//判断逆变器名和所属电站不能为空
			if( isNaN(rate_power)==true)
		    {
		    	$.messager.alert('提示','额定功率请输入数字!');
		    	return false;
		    }
		    if( isNaN(rated_voltage)==true)
		    {
		    	$.messager.alert('提示','额定电压请输入数字!');
		    	return false;
		    }
		    if( isNaN(max_power)==true)
		    {
		    	$.messager.alert('提示','最大功率请输入数字!');
		    	return false;
		    }
		    if( isNaN(power_factor)==true)
		    {
		    	$.messager.alert('提示','功率因数请输入数字!');
		    	return false;
		    }
			if(name == ex_name && ps_name == ex_ps_name)return true;//若没有修改逆变器名和所属电站，则不用判断逆变器名是否已存在
			else 
			{	var result="";
				$.ajax({
		        url:'checkInverterNameIsLegal.action?name='+encodeURI(encodeURI(name))
		             +'&ps_name='+encodeURI(encodeURI(ps_name)),
				type:'GET',				
				dataType:'json',
				async:false,
			  	success:function(obj){
			  		 result = obj[0];
			  	}
		    });
				if(result == "wrong"){$.messager.confirm('警告','逆变器名在此电站中已存在');return false;}
				if(result=="correct"){return true;} 
			}
			 return false;
		}
		
		function check(){
		  if (isNaN(rate_power.value))
		  {$.messager.confirm('提示','非法字符，请输入数字');
		  rate_power.value="";}
		}
		function checknum(){
		  if (isNaN(rated_voltage.value))
		  {$.messager.confirm('提示','非法字符，请输入数字');
		  rated_voltage.value="";}
		}
		function checknum1(){
		  if (isNaN(max_power.value))
		  {$.messager.confirm('提示','非法字符，请输入数字');
		  max_power.value="";}
		}
		function checknum2(){
		  if (isNaN(power_factor.value))
		  {$.messager.confirm('提示','非法字符，请输入数字');
		  power_factor.value="";}
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
