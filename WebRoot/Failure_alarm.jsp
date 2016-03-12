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
    <base href="<%=basePath%>Failure_alarm.jsp">
    <title>equipment</title>
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
    <p style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">故障管理->故障管理</p>  
    <table id="dg" title="故障查看" class="easyui-datagrid" style="width:100%;height:95%;text-align:center" 
            url="getFailureAlarmInformation.action"        
            toolbar="#toolbar" pagination="true"pageSize=20 pageList="[ 20, 30, 40 ]" //读取分页条数，即向后台读取数据时传过去的值
            autoRowHeight="true" striped="true" rownumbers="true" fitColumns="true"
             singleSelect="true" sortName="id" sortOrder="asc" remoteSort="flase">
        <thead>
            <tr>   
                <th field="id" hidden="hidden" align="center" width="20" >设备编号</th>
                <th field="type" align="center" width="20" sortable="true">设备类型</th>           
                <th field="brand" align="center" width="20" sortable="true">设备品牌</th>
                <th field="model" align="center" width="20" sortable="true">设备型号</th>
                <th field="failure_code" align="center" width="20" sortable="true">故障编号</th>           
                <th field="failure_meaning" align="center" width="20" sortable="true">故障意义</th>
                <th field="failure_type" align="center" width="20" sortable="true">故障类型</th>              
                
            </tr>
        </thead>
    </table>

	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newFA()">新建故障</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editFA()">编辑故障</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyFA()">删除故障</a>
        <select  class="easyui-combobox" name="search-station" style="width:160px;" data-options="panelHeight:'auto'">
        	<option value="">根据设备类型查询</option>       
        		<c:forEach items="${list_type}" var="list_type">	  													
					<option value="${list_type}">${list_type}</option>
				</c:forEach>
        </select>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"  onclick="queryEquipmentByType()">查找</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:470px;height:360px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">故障信息</div>
        <form id="fm" method="post" novalidate  style="height:500px">      	
            <div class="fitem">
                <label>设备类型</label>
                <select id="type" name="type" class="easyui-combobox" editable="false" style="width:160px;" data-options="panelHeight:'auto',onSelect:setBrandByType">
                <c:forEach items="${list_type}" var="list_type">	  													
					<option value="${list_type}">${list_type}</option>
				</c:forEach>
   			 	</select>(必填)
   			 	<input id="ex_type" name="ex_type" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>设备品牌</label>
                <select id="brand"   name="brand" class="easyui-combobox" style="width:160px;" editable="false" data-options="panelHeight:'auto',valueField: 'id',textField: 'text',onSelect:setModelByTypeAndBrand ">
   			 	</select>(必填,先选类型)
   			 	<input id="ex_brand" name="ex_brand" type="hidden"></input> 
            </div>
            <div class="fitem">
                <label>设备型号</label>
                <select id="model" 	 name="model" class="easyui-combobox" style="width:160px;"  editable="false" data-options="panelHeight:'auto',valueField: 'id',textField: 'text'">
   			 	</select>(必填,先选品牌)
   			 	<input id="ex_model" name="ex_model" type="hidden"></input> 
            </div>
             <div class="fitem">
                <label>故障编码</label>                
                <input id="failure_code" name="failure_code" class="easyui-textbox" style="width:160px;" />(必填)
                <input id="ex_failure_code" name="ex_failure_code" type="hidden" ></input>  
            </div>
             <div class="fitem">
                <label>故障意义</label>                
                <input id="failure_meaning" name="failure_meaning" class="easyui-textbox" style="width:160px;"/>
                <input id="ex_failure_meaning" name="ex_failure_meaning" type="hidden" ></input>  
            </div>
             <div class="fitem">
                <label>故障类型</label>                
                <select id="failure_type" name="failure_type"  class="easyui-combobox" style="width:160px;"  data-options="panelHeight:'auto',editable:false">
                	<option value="故障">故障</option>
                	<option value="报警">报警</option>
                </select>
                <input id="ex_failure_type" name="ex_failure_type" type="hidden" ></input>  
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveFA()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        var flag = 0;
        function newFA(){
        	var flag = 0;
            $('#dlg').dialog('open').dialog('setTitle','新建故障');

            $('#fm').form('clear');
        	$('#brand').combobox.clear();
        	$('#model').combobox.clear();
        	 $('#brand').combobox('loadData',[]);	
        	 $('#model').combobox('loadData',[]);
        }


        function queryEquipmentByType(){
            
            var type = document.getElementsByName("search-station")[0].value;
            if(type!=""){
            
            	$.ajax({
	                url:'queryEquipmentByType11.action?type='+encodeURI(encodeURI(type)),
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
        }
     
       	
       	 function setBrandByType(rec)
       {
         	
       		var type=rec.value;
       			$.ajax({
			        url:'setBrandByType.action?type='+encodeURI(encodeURI(type)),			       
					type:'GET',
					dataType:'json',
					async:false,
			  	    success:function(obj){
			  	    	$('#brand').combobox('clear'); 
			  	    	$('#model').combobox('clear'); 
			  	  			var data = [];
				  		    for(var i=0;i<obj[0].length;i++){
		  						var brand = obj[0][i];		  						
								data.push({ "text":brand, "id":brand });
			  				}
							 $('#brand').combobox('loadData',data);			
				  	   }
				    });
			}
/*
        function fun(){
            var type = trim(document.getElementsByName("type")[0].value);
            var brand = trim(document.getElementsByName("brand")[0].value);
        	if(type==""){
        		$.messager.confirm('提示','请先选择设备类型');
        		return false;
        	}
        	if(brand==""){
        		$.messager.confirm('提示','请先选择设备品牌');
        		return false;
        	}
        }
*/       	 
       	 
        function setModelByTypeAndBrand()
        {

            var type = trim(document.getElementsByName("type")[0].value);
            var brand = trim(document.getElementsByName("brand")[0].value);
        	
        	$.ajax({
 			        url:'setModelByTypeAndBrand0.action?brand='+encodeURI(encodeURI(brand))+'&type='+encodeURI(encodeURI(type)),			       
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
		function trim(str){//去掉两边空格   
  			 return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');   
       }
		
		function editFA(){
            var row = $('#dg').datagrid('getSelected');
            
            if (row){
            	 flag = 1;
            	 $('#dlg').dialog('open').dialog('setTitle','编辑设备');
                               
 /*        	    
            	$("#ex_type").val(row.type);
            	$("#ex_brand").val(row.brand);
            	$("#ex_model").val(row.model);
            	$("#ex_failure_code").val(row.failure_code);      	
  */         	           	               
                var ex_brand = document.getElementsByName("ex_brand");ex_brand[0].value= row.brand;           	
                var ex_model = document.getElementsByName("ex_model");ex_model[0].value= row.model;
                var ex_type  = document.getElementsByName("ex_type");ex_type[0].value= row.type;
                var ex_failure_code=document.getElementsByName("ex_failure_code");ex_failure_code[0].value= row.failure_code;
            	var type=row.type;
            	var brand=row.brand;

                $.ajax({
 			        url:'setBrandByType.action?type='+encodeURI(encodeURI(type)),			       
 					type:'GET',
 					dataType:'json',
 					async:false,
 			  	    success:function(obj){	 			  	    	
		  	  			var data = [];
			  		    for(var i=0;i<obj[0].length;i++){
	  						var brand = obj[0][i];		  						
							data.push({ "text":brand, "id":brand });
		  				}
						 $('#brand').combobox('loadData',data);			 		
     				  		           		       				
 				  	   }
 				});
               
                $.ajax({
 			        url:'setModelByTypeAndBrand0.action?brand='+encodeURI(encodeURI(brand))+'&type='+encodeURI(encodeURI(type)),			       
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
		
		function destroyFA(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('提示','确定要删除该故障吗?',function(r){
                if (r){                    	
	                  	 $.ajax({
	                    	 url:'deleteFA.action?id='+row.id,
	                    			
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
		
		
        function saveFA(){  
     	
			var type = trim(document.getElementsByName("type")[0].value);
			var brand = trim(document.getElementsByName("brand")[0].value);
			var model = trim(document.getElementsByName("model")[0].value);	   	    
   			var failure_code=trim(document.getElementsByName("failure_code")[0].value);
			var failure_meaning=trim(document.getElementsByName("failure_meaning")[0].value);
			var failure_type=trim(document.getElementsByName("failure_type")[0].value);
			var ex_type = document.getElementsByName("ex_type")[0].value;
			var ex_brand = document.getElementsByName("ex_brand")[0].value; 
		    var ex_model = document.getElementsByName("ex_model")[0].value;                                       
 	   	    var ex_failure_code=document.getElementsByName("ex_failure_code")[0].value;  
 	   	   
		    if(flag==0){ 
		    	 if(!checkFAInformation(brand,model,type,failure_code))return false;
		    	 else{
		    	  
           			 $.ajax({
	             		 url:'addOrUpdateFA.action?brand='+encodeURI(encodeURI(brand))+'&model='+encodeURI(encodeURI(model))+
			        			'&type='+encodeURI(encodeURI(type))+'&failure_code='+encodeURI(encodeURI(failure_code))+
			        			'&failure_meaning='+encodeURI(encodeURI(failure_meaning))+
				        		'&failure_type='+encodeURI(encodeURI(failure_type)), 
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
		                        $('#dlg').dialog('close');       
		                        $('#dg').datagrid('reload');    
		                    }
		                    $.messager.confirm('提示','新建故障成功！');
	                    }
	            	});
	         }   
		    }
			
	 		 if(flag==1){
		    	 if(!checkUpdateFAInformation(type,brand,model,failure_code,ex_type,ex_brand,ex_model,ex_failure_code))return false;
		    	 else{
				    var row = $('#dg').datagrid('getSelected');          
		          	if (row){
		    			$.ajax({
			       	 	url:'addOrUpdateFA.action?id='+row.id+'&brand='+encodeURI(encodeURI(brand))+'&model='+encodeURI(encodeURI(model))+
					        '&type='+encodeURI(encodeURI(type))+'&failure_code='+encodeURI(encodeURI(failure_code))+
		        			'&failure_meaning='+encodeURI(encodeURI(failure_meaning))+
			        		'&failure_type='+encodeURI(encodeURI(failure_type)), 
						type:'GET',
						dataType:'json',
						async:false,
				  		success:function(){
				  	 		$('#dlg').dialog('close');       
		               	 	$('#dg').datagrid('reload'); 
	  		
					  	}
				     });
		          	}
		    	}
		    	 $.messager.confirm('提示','编辑故障成功！');
		    	flag = 0;
		    }  
	 	
	 		
	 		 
	 		 
	 		 
	 	function checkFAInformation(brand,model,type,failure_code){
			
	 		if(type=="")
			{$.messager.confirm('提示','设备类型不能为空！');return false;} 
 	 		if(brand=="")
 				{$.messager.confirm('提示','设备品牌不能为空！');return false;}
 		    if(model=="")
 				{$.messager.confirm('提示','设备型号不能为空！');return false;}

 			if(failure_code=="")
 			{$.messager.confirm('提示','故障编号不能为空！');return false;}
			
			var result="";			
			$.ajax({
		        url:'checkFAInformation.action?brand='+encodeURI(encodeURI(brand))
			        +'&model='+encodeURI(encodeURI(model))+'&type='+encodeURI(encodeURI(type))+
			        '&failure_code='+encodeURI(encodeURI(failure_code)),
				type:'GET',
				dataType:'json',
				async:false,
			  	success:function(obj){
			  	 result = obj[0];			  		
			  	}
		    });	
			if(result == "wrong"){$.messager.confirm('警告','故障已存在');return false;}
			if(result == "correct"){return true;}
		
			return false; 
	 	} 
	 	
	 	function checkUpdateFAInformation(type,brand,model,failure_code,ex_type,ex_brand,ex_model,ex_failure_code){
			
	 		if(type=="")
			{$.messager.confirm('提示','设备类型不能为空！');return false;} 
 	 		if(brand=="")
 				{$.messager.confirm('提示','设备品牌不能为空！');return false;}
 		    if(model=="")
 				{$.messager.confirm('提示','设备型号不能为空！');return false;}

 			if(failure_code=="")
 			{$.messager.confirm('提示','故障编号不能为空！');return false;}
	 		
	 		 if(ex_type==type && ex_brand==brand && ex_model==model && ex_failure_code==failure_code){
	 			
	 			 return true;
			    	
			  }
			 else{
			    var result="";
				$.ajax({
					url:'checkFAInformation.action?brand='+encodeURI(encodeURI(brand))
			       		 +'&model='+encodeURI(encodeURI(model))+'&type='+encodeURI(encodeURI(type))+
			       		 '&failure_code='+encodeURI(encodeURI(failure_code)),
					type:'GET',				
					dataType:'json',
					async:false,
				  	success:function(obj){
				  		 result = obj[0];			  		
				  	}
			    });	
					if(result == "wrong"){$.messager.confirm('警告','该故障已存在');return false;}
					if(result=="correct"){return true;} 
				}
			   		
			return false; 
	 	}
	 		 
	 	
	 	
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
