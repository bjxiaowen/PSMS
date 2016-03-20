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
    <base href="<%=basePath%>engineerArea.jsp">  
    <title>巡检列表</title>
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
   <p style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">人员管控->巡检列表</p>
    <table id="dg" title="定期巡检" class="easyui-datagrid" style="width:100%;height:95%;text-align:center" 
            url="getInspectionAll.action"        
            toolbar="#toolbar" pagination="true" pageSize=20 pageList="[ 20, 30, 40 ]" //读取分页条数，即向后台读取数据时传过去的值
            autoRowHeight="true" striped="true" rownumbers="true" fitColumns="true"
             singleSelect="true" sortName="shouldDate" sortOrder="asc" remoteSort="flase">
        <thead>
            <tr> 
                <th field="inspectionId" width="20">巡检id</th> 
                <th field="manageId" width="20">巡检管理</th> 
                <th field="actualDate" hidden="hidden" width="20">实检日期</th> 
                <th field="inspectionReport" hidden="hidden" width="20">巡检报告</th> 
                <th field="areaName" width="20" align="center" sortable="true">区域名称</th>
                <th field="psName" width="20" align="center" sortable="true">电站名称</th>
                <th field="equipmentId" width="20" hidden="hidden" align="center" sortable="true">设备ID</th>
                <th field="userName" width="20" align="center" sortable="true">维护工程师</th>
                <th field="shouldDate" width="20" align="center" sortable="true">应检日期</th>
                <th field="inspectionStatus" width="5" data-options="formatter:statusChange" align="center" sortable="true">状态</th>
                <th data-options="field:'aa',width:20,formatter:go,align:'center'">操作</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar"></div>
    
    <div id="dlg" class="easyui-dialog" style="width:630px;height:350px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">查看详情</div>
        <form id="fm" method="post" novalidate  style="height:">
        	<div class="fitem">
                <label style="width:60px;">电站名称</label>
                <input id="psName" name="psName" class="easyui-textbox" readonly/> &nbsp; &nbsp; &nbsp; &nbsp;
                <label style="width:60px;">所属区域</label>
                <input id="areaName" name="areaName" class="easyui-textbox" readonly/>
            </div>
            <div class="fitem">
                <label style="width:60px;">设备ID</label>
                <input id="equipmentId" name="equipmentId" class="easyui-textbox" readonly/> &nbsp; &nbsp; &nbsp; &nbsp;
                <label style="width:60px;">巡检日期</label>
                <input id="nowDate" name="nowDate" class="easyui-textbox" readonly/>
            </div>
            <div class="fitem">
            <label style="width:120px;margin:10px 10px 10px 0px;">定期巡检报告(必填)</label>
                <textarea cols="30" rows="5" name="inspectionReport" id="inspectionReport" onpropertychange="if(this.value.length>100){this.value=this.value.substr(0,100)}" class="easyui-validatebox" style="width: 99%; height: 50px;"></textarea>
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
        var nowDate = myformatter(d1);
        
        function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        }
        
        
        function newLink(){
            $('#dlg').dialog('open').dialog('setTitle','关联信息');
            $('#fm').form('clear');
        }
         
        
        
        function statusChange(value,row,index){
			if(value == 0 ||value ==""){
				 return "<span style='color:red;'>待处理<span>"
			 }else if(value == 1){
				 return "完成"
			 }
		}
        
        
        function  go(value,row,index){
        	if(row.inspectionStatus=="0"){
        		return '<a href="javascript:void(0)" style="text-decoration:none;" onclick="constructionManager(\'' + index+ '\')">填写报告</a>'
        	}else{
        		return '<a href="javascript:void(0)" style="text-decoration:none;" onclick="constructionManager(\'' + index+ '\')">查看</a>'
            }
        	
        }
        function constructionManager(index){
        	$('#dg').datagrid('selectRow',index);
            var row = $('#dg').datagrid('getSelected');  
            
            row.equipmentId = row.equipmentId+"";
            $('#nowDate').textbox('setValue',nowDate);
            if (row){  
	    		if(row.inspectionStatus=="0" || row.inspectionStatus==""){
	    			$('#dlg').dialog('open').dialog('setTitle','巡检记录');
	                $('#fm').form('load',row);
	    			$('#inspectionReport').attr("readonly",false);
	    			$(".c6").show();
	    			
	    		}else if(row.inspectionStatus=="1"){
	    			$('#dlg').dialog('open').dialog('setTitle','巡检记录');
	    			$('#fm').form('load',row);
	    			$('#inspectionReport').attr("readonly",true);
	    			$(".c6").hide();
	    		}
    		}
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
        
        

        function saveLink(){          
        	var row = $('#dg').datagrid('getSelected');
	   	    var inspectionReport = trim(document.getElementsByName("inspectionReport")[0].value);
		   	 var inspectionId = row.inspectionId;
	         var managerId = row.manageId;
	         var shouldDate = row.shouldDate;
	         var actualDate = nowDate;
	         var inspectionStatus = Number(row.inspectionStatus)+1;
	         var url = 'updateInspection.action?inspectionId='+encodeURI(encodeURI(inspectionId))+'&managerId='+encodeURI(encodeURI(managerId))+'&shouldDate='+encodeURI(encodeURI(shouldDate))+'&actualDate='+encodeURI(encodeURI(actualDate))+'&inspectionStatus='+encodeURI(encodeURI(inspectionStatus))+'&inspectionReport='+encodeURI(encodeURI(inspectionReport));
		    	console.log(url);
	   	    
	   	 if(row.inspectionStatus=="0"||row.inspectionStatus==""){
			    if(!checkLinkInformation(inspectionReport)){return false; }
			    else{
			    	$.messager.confirm('确认','保存后不能修改，是否继续？',function(r){
						if (r){
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
				                    $.messager.confirm('提示','报告添加成功！'); 
			                    }
			            });
						}
					});
			    	
			    	
			    	
			    	
	           		
	            }
	          }
        }
        function checkLinkInformation(inspectionReport){
			if(inspectionReport==""){$.messager.confirm('警告','报告内容不能为空');return false;}
		    return true;
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
