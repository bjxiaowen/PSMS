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
    <base href="<%=basePath%>equipment.jsp">
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
    <p style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">品牌管理->品牌型号</p>  
    <table id="dg" title="设备查看" class="easyui-datagrid" style="width:100%;height:95%;text-align:center" 
            url="getEquipmentInformation.action"        
            toolbar="#toolbar" pagination="true" pageSize=20 pageList="[ 20, 30, 40 ]" //读取分页条数，即向后台读取数据时传过去的值
            autoRowHeight="true" striped="true" rownumbers="true" fitColumns="true" singleSelect="true"
            striped="true" sortName="id"  sortOrder="asc" remoteSort="flase" >
        <thead>
            <tr>   
                <th field="id" hidden="hidden" align="center" width="20" >设备编号</th>           
                <th field="brand" align="center" width="20" sortable="true">设备品牌</th>
                <th field="type" align="center" width="20" sortable="true">设备类型</th>
                <th field="model" align="center" width="20" sortable="true">设备型号</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newEquipment()">新建设备型号</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editEquipment()">编辑设备型号</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyEquipment()">删除设备型号</a>
         
         <input id="search_name" name="search_name" class="easyui-searchbox" data-options="prompt:'请输入设备品牌查找',searcher:doSearch" style="width:200px"></input>
      <input id="search_model" name="search_model" class="easyui-searchbox" data-options="prompt:'请输入设备型号查找',searcher:doSearch2" style="width:200px"/>
    <select class="easyui-combobox" name="search-station" style="width:160px;" data-options=" panelHeight:'auto'">
        	<option value="">根据设备类型查询</option>
        	<option value="组件">组件</option>
        	<option value="控制器">控制器</option>       
        	<option value="逆变器">逆变器</option>	
        	<option value="蓄电池">蓄电池</option>
        	<option value="控逆一体机">控逆一体机</option>										
			<option value="汇流箱">汇流箱</option>
			<option value="电表">电表</option>
			<option value="气象站">气象站</option>	
        </select>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"  onclick="queryEquipmentByType()">查找</a>
    </div>
    <div id="dlg" class="easyui-dialog" style="width:450px;height:250px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">设备信息</div>
      <form id="fm" method="post" novalidate  style="height:">
        	
            <div class="fitem">
                <label>设备品牌</label>
                
                <input id="brand" name="brand" class="easyui-textbox" />(必填)
                <input id="ex_brand" name="ex_brand" type="hidden" ></input>  
            </div>
            <div class="fitem">
                <label>设备类型</label>
                <select id="type" class="easyui-combobox" name="type" style="width:160px;" editable="false" data-options=" panelHeight:'auto'" >
               		  <option value="组件">组件</option>
			        	<option value="控制器">控制器</option>       
			        	<option value="逆变器">逆变器</option>	
			        	<option value="蓄电池">蓄电池</option>
			        	<option value="控逆一体机">控逆一体机</option>										
						<option value="汇流箱">汇流箱</option>
						<option value="电表">电表</option>
						<option value="气象站">气象站</option>				
   			 	</select>(必填,先选型号)
   			 	<input id="ex_type" name="ex_type" type="hidden"></input> 
            </div>
            <div class="fitem">
                <label>设备型号</label>
                <input id="model" name="model" class="easyui-textbox"  />(必填,先选品牌)
                <input id="ex_model" name="ex_model" type="hidden" ></input>  
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveEquipment()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        var flag = 0;
        function newEquipment(){
        	var flag = 0;
            $('#dlg').dialog('open').dialog('setTitle','新建设备型号');
            $('#fm').form('clear');
           
        }
        function queryEquipmentByType(){
       
            var type = document.getElementsByName("search-station")[0].value;
            var brand = document.getElementsByName("search_name")[0].value;
            var model = document.getElementsByName("search_model")[0].value;
            if(type!=""||brand!=""||model!=""){
            
            	$.ajax({
	                url:'queryEquipmentByType.action?type='+encodeURI(encodeURI(type))
	                +'&brand='+encodeURI(encodeURI(brand))
	                +'&model='+encodeURI(encodeURI(model)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
		                    if(obj.total==0)    				
	        				{
	        					$.messager.confirm('提示','没有符合该条件的设备型号');
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
          $('#search_model').searchbox('clear');
        }
       
           
          function doSearch(value){
            var brand = value;
            if(brand!=""){
            	$.ajax({
	                url:'queryEquipmentByName.action?brand='+encodeURI(encodeURI(brand)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
			             if(obj.total==0)
								{
									$.messager.confirm('提示','没有符合该条件的设备型号');
									$('#dg').datagrid('reload');
								}
				  		        else{ 
				  		        	$('#dg').datagrid('loadData',obj);
				  		        }
		                    //$('#dg').datagrid('loadData',obj);
	                    }
	            });
            }
            else
            {
            	 $('#dg').datagrid('reload');
            }
           $('#search_name').searchbox('clear');   ////  是否需要查询后清除查询公司名称名称  
        } 
        
        function doSearch2(value){
            var model = value;
            if(model!=""){
            	$.ajax({
	                url:'queryEquipmentByMName.action?model='+encodeURI(encodeURI(model)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
			             if(obj.total==0)
								{
									$.messager.confirm('提示','没有符合该条件的设备型号');
									$('#dg').datagrid('reload');
								}
				  		        else{ 
				  		        	$('#dg').datagrid('loadData',obj);
				  		        }
		                    //$('#dg').datagrid('loadData',obj);
	                    }
	            });
            }
            else
            {
            	 $('#dg').datagrid('reload');
            }
           $('#search_model').searchbox('clear');   ////  是否需要查询后清除查询公司名称名称  
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
        
        
        function editEquipment(){
            var row = $('#dg').datagrid('getSelected');
            
            //alert("wwwwwwwwwwww")
            if (row){
                flag = 1;
                $('#dlg').dialog('open').dialog('setTitle','编辑设备型号');
                $('#fm').form('load',row); 
                var ex_brand = document.getElementsByName("ex_brand");
                ex_brand[0].value= row.brand;
                 var ex_model = document.getElementsByName("ex_model");
                ex_model[0].value= row.model;
                var ex_type = document.getElementsByName("ex_type");
                ex_type[0].value= row.type;
                
            }
        }
        function saveEquipment(){  
           var row = $('#dg').datagrid('getSelected');
            // var id;
            // alert(row.id);
            var brand = trim(document.getElementsByName("brand")[0].value);
            var model = trim(document.getElementsByName("model")[0].value);
	   	    var type = trim(document.getElementsByName("type")[0].value);
	   	    
		    //--------------------------以上为获取新建的值并且去掉输入值两边的空格	
		     var ex_brand = document.getElementsByName("ex_brand")[0].value; 
		     var ex_model = document.getElementsByName("ex_model")[0].value;              
             var ex_type = document.getElementsByName("ex_type")[0].value;              
             
              
		    if(flag==0){//flag为0表示当前为新建用户
		    	 //alert("qqqqqqqqqqq+home_page"+home_page)
		    	 if(!checkEquipmentInformation(brand,model,type))return false;//校验用户信息
		    	  else{
		    	  
           		 $.ajax({
	              url:'addEquipment.action?brand='+encodeURI(encodeURI(brand))+'&model='+encodeURI(encodeURI(model))+
			        '&type='+encodeURI(encodeURI(type)), 
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
		                    $.messager.confirm('提示','新建设备型号成功！');
	                    }
	            	});
	         }   
		    }
			
	  if(flag==1){//flag为1表示当前为编辑用户
		    	 if(!checkEquipmentInformation(brand,model,type))return false;//校验用户信息
		    	else{
		    	
		    	$.ajax({
			        url:'addEquipment.action?id='+encodeURI(encodeURI(row.id))+'&brand='+encodeURI(encodeURI(brand))+'&model='+encodeURI(encodeURI(model))+
					        '&type='+encodeURI(encodeURI(type)), 
					type:'GET',
					dataType:'json',
					async:false,
				  	success:function(){
				  	 	$('#dlg').dialog('close');        // close the dialog
		                $('#dg').datagrid('reload'); 
	  		
					  	}
				    });	
		    	}
		    	 $.messager.confirm('提示','编辑设备型号成功！');
		    	flag = 0;
		    }          
        }
	          
        function destroyEquipment(){
        	//alert("qqqqqqqqqqqqqqq")
            var row = $('#dg').datagrid('getSelected');
        	//alert("qqqqq4444444444qq")
            if (row){
                $.messager.confirm('提示','确定要删除该设备型号吗?',function(r){
                    if (r){
                    	
	                    $.ajax({
	                    	 url:'deleteEquipment.action?id='+row.id,
	                    			
							 type:'GET',
					         dataType:'json',
					         async:false,
				  	         success:function(){
				  		         $('#dg').datagrid('reload'); 
				  	          }                 
	                    });
	                    //alert("q2222222222224qq")
	                  }
           
                });
             }
        }
       
        function checkEquipmentInformation(brand,model,type){
	
			if(brand=="")
			
			{$.messager.confirm('提示','设备品牌不能为空！');return false;}
		  //  return true;
		   if(model=="")
			
			{$.messager.confirm('提示','设备型号不能为空！');return false;}
			if(type=="")
			
			{$.messager.confirm('提示','设备类型不能为空！');return false;} 
			
			var result="";
						
		$.ajax({
		        url:'checkEquipmentIsLegal.action?brand='+encodeURI(encodeURI(brand))
			             +'&model='+encodeURI(encodeURI(model))+'&type='+encodeURI(encodeURI(type)),
				type:'GET',
				dataType:'json',
				async:false,
			  	success:function(obj){
			  	 result = obj[0];			  		
			  	}
		    });	
			if(result == "wrong"){$.messager.confirm('警告','设备型号已存在');return false;}
			if(result=="correct"){return true;}
			return false;   //////////////////////////
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
