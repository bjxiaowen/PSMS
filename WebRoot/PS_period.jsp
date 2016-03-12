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
    <base href="<%=basePath%>PS_period.jsp">
    <title>电站分期</title>
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
    <p style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">电站管理->分期信息</p>  
    <table id="dg" title="分期查看" class="easyui-datagrid" style="width:100%;height:95%;text-align:center" 
            url="getPsPeriodInformation.action"        
             toolbar="#toolbar"      pagination="true" pageSize=20 pageList="[ 20, 30, 40 ]" //读取分页条数，即向后台读取数据时传过去的值
            autoRowHeight="true" striped="true" rownumbers="true" fitColumns="true" singleSelect="true"
            striped="true" data-options="onLoadSuccess:onLoadSuccess" >
        <thead>
            <tr>   
                <th field="id" hidden="hidden" align="center" width="20" >设备编号</th>           
                <th field="name" align="center" width="30"  >电站名称</th>
                <th field="period_num" align="center" width="30"  >电站分期</th>
                <th field="period_capacity" align="center" width="30"  >分期容量(MW)</th>
                <th field="period_unit_num" align="center" width="30"  >分期单元数目</th>
                <th field="period_time" align="center" width="30"  >分期时间</th>
                
            </tr>
        </thead>
    </table>
 	<div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newPeriod()">新建分期</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editPeriod()">编辑分期</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyPeriod()">删除分期</a>
         
    </div>
    <div id="dlg" class="easyui-dialog" style="width:500px;height:380px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">分期信息</div>
        <form id="fm" method="post" >
        	 <div class="fitem">
                <label style="width:32%">电站分期</label>
                <input id="period_num" name="period_num" class="easyui-textbox" >(不可编辑)</input>
            </div>
            <div class="fitem">
                <label style="width:32%">分期容量(MW)</label>
                <input id="period_capacity" name="period_capacity" class="easyui-textbox" ></input>
            </div>
            <div class="fitem">
                <label style="width:32%">分期时间</label>
                <input id="period_time" name="period_time" class="easyui-datebox" ></input>
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="savePeriod()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
   
    var flag = 0;
    var period_capacity_edit=0;//用于编辑分期时使用
    function newPeriod(){
    	var row = $('#dg').datagrid('getSelected');
    	if(!(row)){
    		$.messager.confirm('提示','请先选择要增期的电站');
    	}
    	else {
   			var nextNum=1;
    		$.ajax({
                url:'getPeriodNumByPsName.action?ps_name='+encodeURI(encodeURI(row.name)),
                     type:'GET',
    	             dataType:'json',
    	             async:false,
    	             success:function(obj){
    	            	     nextNum=obj[0];	             
                    }
    		}),
    		
    		 $('#dlg').dialog('open').dialog('setTitle',row.name);
    	     $('#fm').form('clear');
    	      $('#period_num').textbox('setValue',nextNum);
    	      $('#period_num').textbox('readonly',true);
    	     var curr_time = new Date();
   		    var strDate = curr_time.getFullYear()+"-";
   			strDate += curr_time.getMonth()+1+"-";
   			strDate += curr_time.getDate();
   			$('#period_time').datebox('setValue', strDate);
    	}
              
    }
    function editPeriod(){
        var row = $('#dg').datagrid('getSelected');
    	if(!(row)){
    		$.messager.confirm('提示','请先选择要编辑的电站分期');
    	}
    	else {
        	flag = 1;
            $('#dlg').dialog('open').dialog('setTitle',row.name);
            $('#fm').form('load',row); 
            period_capacity_edit = trim(document.getElementsByName("period_capacity")[0].value);
            $('#period_num').textbox('readonly',true);            

        }
    }
    function savePeriod(){          
    
    	var row = $('#dg').datagrid('getSelected');
   	    var period_num = trim(document.getElementsByName("period_num")[0].value);
   	    var period_capacity = trim(document.getElementsByName("period_capacity")[0].value);
   	    var period_time = trim(document.getElementsByName("period_time")[0].value);

        if(flag==0){ 
	    if(checkPeriodInformation(row.name,period_capacity,flag)==false)
	    { return false; } 
	    else{
       		 $.ajax({
            url:'addPeriod.action?ps_name='+encodeURI(encodeURI(row.name))+'&period_num='+
            		period_num+'&period_capacity='+period_capacity+
				'&period_time='+encodeURI(encodeURI(period_time)),
                 type:'GET',
	             dataType:'json',
	             async:false,
	             success:function(obj){
	            	 
                        $('#dlg').dialog('close');        
                        $('#dg').datagrid('reload'); 
                        
                    	$.messager.confirm('提示','新建分期成功！');
                }
        });
        }
      }
      
      if(flag==1){ 
     
	    	if(checkPeriodInformation(row.name,period_capacity,flag)==false){return false;} 
	    	else{
	    	$.ajax({
	    		 url:'editPeriod.action?id='+encodeURI(encodeURI(row.id))+'&period_capacity='+period_capacity+
	   				'&period_time='+encodeURI(encodeURI(period_time)),
		       
				type:'GET',
				dataType:'json',
				async:false,
			  	success:function(){
			  	 	$('#dlg').dialog('close');        // close the dialog
	                $('#dg').datagrid('reload'); 
				  	}
			    });	
	    	}
	    	$.messager.confirm('提示','编辑分期成功！');
	    	flag = 0;
	    } 
    }
    function destroyPeriod(){
        var row = $('#dg').datagrid('getSelected');
    	if(!(row)){
    		$.messager.confirm('提示','请先选择要编辑的电站分期');
    	}
    	else {

            $.messager.confirm('确认','确定要删除这个分期吗？',
            function(r){
                if (r){
                    $.ajax({
                    	 url:'deletePeriod.action?id='+row.id,
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
    function checkPeriodInformation(ps_name,period_capacity,flag){

		if(period_capacity=="")
	    {
	    	$.messager.alert('提示','电站容量必须填写!');
	    	return false;
	    }
	    if( isNaN(period_capacity)==true)
	    {
	    	$.messager.alert('提示','电站容量请输入数字!');
	    	return false;
	    }
	    var result="";
	    var totalCapacity="";
		$.ajax({
	        url:'checkAddPeriodCapacityIsLegal.action?ps_name='+encodeURI(encodeURI(ps_name))+'&capacity='+period_capacity+'&flag='+flag+'&period_capacity_edit='+period_capacity_edit,
			type:'GET',				
			dataType:'json',
			async:false,
		  	success:function(obj){
		  		 result = obj[0];
		  	 	totalCapacity = obj[1];
		  	}
	    });	
		if(result == "wrong"){$.messager.confirm('警告','此次增期容量已经超过该电站总容量'+totalCapacity+'MW，请重新输入容量');return false;}
	    if(result=="correct"){ return true;}
	    
    	
    }
    function mergeCellsByField(tableID,colList){
    	
    	    var ColArray = colList.split(",");   	
    	    var tTable = $('#'+tableID);    	
    	    var TableRowCnts=tTable.datagrid("getRows").length;   	
    	    var tmpA;   	
    	    var tmpB;  	
    	    var PerTxt = "";   	
    	    var CurTxt = "";  	
    	    var alertStr = "";   	
    	    for (j=ColArray.length-1;j>=0 ;j-- )    	
    	    {   	
    	        PerTxt="";    	
    	        tmpA=1;
    	        tmpB=0;
    	        for (i=0;i<=TableRowCnts ;i++ )
    	
    	        {    	
    	            if (i==TableRowCnts)   	
    	            {   	
    	                CurTxt="";
    	            }  	
    	            else   	
    	            {   	
    	                CurTxt=tTable.datagrid("getRows")[i][ColArray[j]];    	
    	            }   	
    	            if (PerTxt==CurTxt) 	
    	            {    	
    	                tmpA+=1;   	
    	            }   	
    	            else
    	
    	            {
    	
    	                tmpB+=tmpA;
    	
    	                tTable.datagrid('mergeCells',{
    	
    	                    index:i-tmpA,
    	
    	                    field:ColArray[j],
    	
    	                    rowspan:tmpA,
    	
    	                    colspan:null
    	
    	                });
    	
    	                tmpA=1;   	
    	            }
    	
    	            PerTxt=CurTxt;   	
    	        }   	
    	    }	
    	}

    function onLoadSuccess(data){
    	
// 	alert("LoadSuccess");
 
    	if (data.rows.length>0)   		
    		    {
				 mergeCellsByField("dg","name");   		
    		    }

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
 		function trim(str){//去掉两边空格   
   			return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');   
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
