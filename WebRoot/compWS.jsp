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
    <base href="<%=basePath%>pshistorydata.jsp">   
    <title>psHistoryData</title>   
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyui/demo/demo.css"> 
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>    
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script> 
    <script type="text/javascript"></script>
    <style>
    	body{
    		border:0px;padding:5px;
    	}   	
    </style>
  </head>
  
  <body>
	<P style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">站内对比 -> 站内逆变器对比</P>
	
    <div id="toolbar"  >
		<lable style="margin-left:10px">设置   起始时间:</lable>
  		<input id="dateFrom" name="dateFrom" class="easyui-datebox" style="width:160px;"></input>
  		<label>结束时间:</label>
  		<input id="dateTo" name="dateTo" class="easyui-datebox" style="width:160px;"></input>
  		<label>电站名称:</label>				
		<select  id="ps_name" name="ps_name"  class="easyui-combobox" style="width:160px;" data-options=" panelHeight:'auto'">       	
        </select>
   				
		
        <a id= "abc" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchData()">查 询</a> 
        <P id="table_title" name="table_title" style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px"></P>
	 	
    </div>
    <div style="width:100%;height:92%">
    <table id="dg"  class="easyui-datagrid" style="width:100%;height:95%;text-align:center"
            pagination="true"
             pageSize=20 pageList="[ 20,30, 50]" 
             autoRowHeight="true"  fitcolumns="false" striped="true" 
             rownumbers="true" >
          <thead > </thead>
    </table>
    </div>
 	
     <script>
        var username = "<%=session.getAttribute("User_name")%>"; 
     $(document).ready(function() {  			    
			var myDate = new Date();
			var year = myDate.getFullYear();//获取当前年份   		
			var month = myDate.getMonth() + 1;//获取当前月份
			var date = myDate.getDate();//获取当前日期
			var from_month;
			var from_year;
			if(month==1)
				{from_month = 11;from_year = year-1;}
			else if(month==2)
				{from_month = 12;from_year  = year -1;}
			else {from_month = month - 2;from_year = year;}			
			var fromdate ="" + from_year +"-"+ from_month +"-" + date ;
			var todate = "" + year+ "-" + month +"-" + date ;
			$('#dateFrom').datebox('setValue', fromdate);
			$('#dateTo').datebox('setValue', todate);
			//----------------------赋值
			$.ajax({
				url : 'toAllStationHistoryData.action?username='+encodeURI(encodeURI(username)),
				type : 'GET',
				dataType : 'json',
				async : false,
				success : function(obj) {
				
					 $('#ps_name').combobox({
       					data:obj.aaData,
    					valueField:'ps_name',
    					textField:'ps_name'
     				});
     				$('#ps_name').combobox('setValue',obj.first_ps); 			
				}
			});	
			searchData();
	});
     
  	function searchData(){
  		 var tempStr = $("#dateFrom").datebox("getValue");  
		 var dateFrom  = new Date(tempStr);
		 var year1=dateFrom.getFullYear();
		 var month1=dateFrom.getMonth()+1;
		 var day1=dateFrom.getDate();

		 var tempStr2 = $("#dateTo").datebox("getValue");  
		 var dateTo  = new Date(tempStr2);
		 var year2=dateTo.getFullYear();
		 var month2=dateTo.getMonth()+1;
		 var day2=dateTo.getDate();
		 
         var fromRangeDate = "" + month1 + "/" + day1 + "/" + year1;
         var toRangeDate = "" + month2 + "/" + day2 + "/" + year2;

		 var ps_name = document.getElementsByName("ps_name")[0].value;
		 var table_title= ps_name + "逆变器功率数据对比";
		 $('#table_title').text(table_title);
		  $("#dg").datagrid({  
             url:"getWSCompData.action?fromRangeDate="
    		+ fromRangeDate + "&toRangeDate=" + toRangeDate
    		+ "&ps_name="+ encodeURI(encodeURI(ps_name)),
            columns: [[            
            { field: 'ps_name',title:'电站名称', width: '20%',align:'center' },  
            { field: 'ws_name1', title:'气象站名称', width: '12%',align:'center'},
             { field: 'irrad1', title:'辐射值(W/m²)', width: '8%',align:'center'},
             { field: 'time1', title:'时间', width: '20%',align:'center' },
            { field: 'ws_name2', title:'气象站名称', width: '12%',align:'center'},
             { field: 'irrad1', title:'辐射值(W/m²)', width: '8%',align:'center'},
             { field: 'time2', title:'时间', width: '20%',align:'center' }
                   
            ]]  
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
      	
   </script>
    
  </body>
</html>
