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
    <base href="<%=basePath%>regionList.jsp">
    <title>region</title>
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
    <p style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">人员管理->区域列表</p>  
    <table id="dg" title="设备查看" class="easyui-datagrid" style="width:100%;height:95%;text-align:center"
    		url="getRegionList.action"
            toolbar="#toolbar" pagination="true" pageSize=20 pageList="[ 20, 30, 40 ]" //读取分页条数，即向后台读取数据时传过去的值
            autoRowHeight="true" striped="true" rownumbers="true" fitColumns="true" singleSelect="true"
            striped="true" sortName="areaCode"  sortOrder="asc" remoteSort="flase" >
        <thead>
            <tr>              
            	<th field="areaId" hidden="hidden" align="center" width="20" >区域id</th>   
                <th field="areaCode" align="center" width="20" sortable="true">区域编号</th>
                <th field="areaName" align="center" width="20" sortable="true">区域名称</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newRegion()">新建区域</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRegion()">编辑区域</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyRegion()">删除区域</a>
        <input id="search_name" name="search_name" class="easyui-searchbox" data-options="prompt:'请输入区域编号查找',searcher:doSearch" style="width:200px"></input>
        <input id="search_model" name="search_model" class="easyui-searchbox" data-options="prompt:'请输入区域名称查找',searcher:doSearch2" style="width:200px"/>
    </div>
    <div id="dlg" class="easyui-dialog" style="width:450px;height:250px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">区域信息</div>
      <form id="fm" method="post" novalidate  style="height:">
        	
            <div class="fitem">
                <label>区域编号</label>
                <input id="areaCode" name="areaCode" class="easyui-textbox" />(必填)
            </div>
            <div class="fitem">
                <label>区域名称</label>
                <input id="areaName" name="areaName" class="easyui-textbox" />(必填)
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRegion()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        var flag = 0;
        function newRegion(){
        	var flag = 0;
            $('#dlg').dialog('open').dialog('setTitle','新建区域名称');
            $('#fm').form('clear');
           
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
        
        
        function editRegion(){
            var row = $('#dg').datagrid('getSelected');
            
            //alert("wwwwwwwwwwww")
            if (row){
                flag = 1;
                $('#dlg').dialog('open').dialog('setTitle','编辑区域');
                $('#fm').form('load',row); 
            }
        }
        function saveRegion(){  
           var row = $('#dg').datagrid('getSelected');
            
            // var id;
            var areaCode = trim(document.getElementsByName("areaCode")[0].value);
            var areaName = trim(document.getElementsByName("areaName")[0].value);
		    //--------------------------以上为获取新建的值并且去掉输入值两边的空格	        
             
              
		    if(flag==0){//flag为0表示当前为新建用户
		    	 //alert("qqqqqqqqqqq+home_page"+home_page)
		    	 if(!checkRegionInformation(areaCode,areaName))return false;//校验用户信息
		    	  else{
		    	  
           		 $.ajax({
	              url:'addRegion.action?areaCode='+encodeURI(encodeURI(areaCode))+'&areaName='+encodeURI(encodeURI(areaName)), 
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
		                    $.messager.confirm('提示','新建区域成功！');
	                    }
	            	});
	         }   
		    }
			
	  if(flag==1){//flag为1表示当前为编辑用户
		    	 if(!checkRegionInformationEdit(areaCode,areaName,row.areaCode))return false;//校验用户信息
		    	else{
		    	
		    	$.ajax({
			        url:'updateRegion.action?areaId='+encodeURI(encodeURI(row.areaId))+'&areaCode='+encodeURI(encodeURI(areaCode))+'&areaName='+encodeURI(encodeURI(areaName)), 
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
	          
        function destroyRegion(){
        	//alert("qqqqqqqqqqqqqqq")
            var row = $('#dg').datagrid('getSelected');
            console.log(row.areaId);
        	//alert("qqqqq4444444444qq")
            if (row){
                $.messager.confirm('提示','确定要删除该区域吗?',function(r){
                    if (r){
                    	
	                    $.ajax({
	                    	 url:'deleteRegion.action?areaId='+row.areaId,
	                    			
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
       
        function checkRegionInformation(areaCode,areaName){
        	if(areaCode=="")
    			
			{$.messager.confirm('提示','区域编号不能为空！');return false;}
	
			if(areaName=="")
			
			{$.messager.confirm('提示','区域名称不能为空！');return false;}
		  //  return true;
			var result="";
						
			$.ajax({
		        url:'queryByCode.action?areaCode='+encodeURI(encodeURI(areaCode)),
				type:'GET',
				dataType:'json',
				async:false,
			  	success:function(obj){
			  	 result = obj[0];			  		
			  	}
		    });	
			if(result == "wrong"){$.messager.confirm('警告','区域编号已存在');return false;}
			if(result=="correct"){return true;}
		
			return false;   //////////////////////////
		}
        
        
        function checkRegionInformationEdit(areaCode,areaName,rowCode){
        	if(areaCode=="")
    			
			{$.messager.confirm('提示','区域编号不能为空！');return false;}
	
			if(areaName=="")
			
			{$.messager.confirm('提示','区域名称不能为空！');return false;}
		  //  return true;
			var result="";
					if(areaCode != rowCode)	{
			$.ajax({
		        url:'queryByCode.action?areaCode='+encodeURI(encodeURI(areaCode)),
				type:'GET',
				dataType:'json',
				async:false,
			  	success:function(obj){
			  	 result = obj[0];			  		
			  	}
		    });	
			if(result == "wrong"){$.messager.confirm('警告','区域编号已存在');return false;}
			if(result=="correct"){return true;}
					}
			return true;   //////////////////////////
		}
		
		 
		function trim(str){//去掉两边空格   
   			 return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');   
        }
		
		function doSearch(value){
            var areaCode = value;
            if(areaCode!=""){
            	$.ajax({
	                url:'queryByCodeAndName.action?areaCode='+encodeURI(encodeURI(areaCode)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
			             if(obj.total==0)
								{
									$.messager.confirm('提示','没有符合该条件的区域');
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
            var areaName = value;
            if(model!=""){
            	$.ajax({
	                url:'queryByCodeAndName.action?areaName='+encodeURI(encodeURI(areaName)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
			             if(obj.total==0)
								{
									$.messager.confirm('提示','没有符合该条件的区域');
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
