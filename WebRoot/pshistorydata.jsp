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
    <script type="text/javascript" src="easyui/datagrid-detailview.js"></script>
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script> 
    <script type="text/javascript"></script>
    <style>
    	body{
    		border:0px;padding:5px;
    	}   	
    </style>
  </head>
  
  <body>
	<P style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">历史数据 -> 电站数据223</P>
	 <table id="dg" title="巡检管理列表" class="easyui-datagrid" style="width:100%;height:95%;text-align:center" 
            url="getAllHistoryData.action"        
            toolbar="#toolbar" pagination="true" pageSize=20 pageList="[ 20, 30, 40 ]" 
            autoRowHeight="true" striped="true" rownumbers="true" fitColumns="true"
             singleSelect="true"  remoteSort="flase">
        <thead>
            <tr> 
                <th field="x_Coutpout_Current"  width="20">直流电流(A)</th> 
                <th field="x_Coutpout_Voltage"  width="20">直流电压(V)</th> 
                <th field="x_Coutpout_Power"  width="20">直流功率(W)</th> 
                <th field="outputCurrent"  width="20">交流电流(A)</th> 
                <th field="outputVoltage"  width="20">交流电压(V)</th> 
                <th field="exchangeOutPower"  width="20">交流功率(W)</th> 
                 <th field="lineFrequency"  width="20">频率(Hz)</th> 
                <th field="x_Inerin_tem"  width="20">温度(°C)</th> 
                <th field="machineState"  width="20">机器状态</th> 
                <th field="operateDate"  width="20">时间</th> 
                <th field="id"   hidden="hidden">编号</th> 
                <th field="name"    hidden="hidden">编号</th> 
            </tr>
        </thead>
    </table>
    <div id="toolbar"  >
    	<lable style="margin-left:10px">开始时间:</lable>
  		<input id="dateFrom" name="dateFrom" class="easyui-datebox" style="width:160px;"></input>
  		<label>结束时间:</label>
  		<input id="dateTo" name="dateTo" class="easyui-datebox" style="width:160px;"></input>
  		<label>电站名称:</label>				
        <select class="easyui-combobox" name="search-station" style="width:160px;" data-options=" panelHeight:'auto'" >
        	<c:forEach items="${psDate}" var="ps">		
				<option value="${ps.id}">${ps.name}</option>
			</c:forEach>
        </select>
        
        <a id= "abc" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchData()">查 询</a>
    </div>
 	
     <script>
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
	});
     
  	function searchData(){
  		var psId = document.getElementsByName("search-station")[0].value;
  		console.log(psId);
		weatherStationHistory(psId);	 
  	}
  	
  	function exportData(){
  	
        var myDate = new Date();
		var year = myDate.getFullYear();//获取当前年份   		
		var month = myDate.getMonth() + 1;//获取当前月份
		var date = myDate.getDate();//获取当前日期
		var filetime =year+ "-" + month +"-" + date ;
  		var device_type = document.getElementsByName("device_type")[0].value;
  		
  		if (device_type == "逆变器"){	
			exportInverterHistory();
			var filename = filetime+'Inverter.xls';
			document.getElementById("exportExcel").href='/PSMS/backup/'+filename;
			}			
		else if (device_type == "电表"){
			exportPowerMeterHistory();
			var filename = filetime+'PowerMeter.xls';
			document.getElementById("exportExcel").href='/PSMS/backup/'+filename;
				}		
		else if (device_type == "汇流箱")  {		
			exportJunctionBoxHistory();
			var filename = filetime+'JunctionBox.xls';
			document.getElementById("exportExcel").href='/PSMS/backup/'+filename;
			}
		else{
			exportWeatherStationHistory(); 
			var filename = filetime+'WeatherStation.xls';
			document.getElementById("exportExcel").href='/PSMS/backup/'+filename;
			}
  	}
  	
  	
  	function getInverterHistory() {
        	
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
		 var table_title= ps_name + "逆变器历史数据";
		 
		$('#table_title').text(table_title);
        $("#dg").datagrid({  
             url:"getInverterHistoryData.action?fromRangeDate="
    		+ fromRangeDate + "&toRangeDate=" + toRangeDate
    		+ "&ps_name="+ encodeURI(encodeURI(ps_name)),
            columns: [[            
            { field: 'inverter_name',title:'逆变器名称111', width: '8%',align:'center' },  
            { field: 'dc_current', title:'直流电流(A)', width: '8%',align:'center'},
            { field: 'dc_voltage', title:'直流电压(V)', width: '8%',align:'center' },
            { field: 'dc_power', title:'直流功率(W)', width: '8%',align:'center'},
            { field: 'ac_current', title:'交流电流(A)', width: '8%',align:'center' },
            { field: 'ac_voltage', title:'交流电压(V)', width: '8%',align:'center' },
            { field: 'ac_power', title:'交流功率(W)', width: '8%',align:'center' },
            { field: 'frequency', title:'频率(Hz)', width: '8%',align:'center' },
            { field: 'temperature', title:'温度(℃)', width: '8%',align:'center' },
            { field: 'state', title:'状态', width: '8%',align:'center' },
            { field: 'time', title:'时间', width: '20%',align:'center' }          
            ]]  
        }); 
	 }
	 
  	function exportInverterHistory() {
    	
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
       
       $.ajax({
           
           url:'exportInverterHistoryData.action?fromRangeDate='
          		+ fromRangeDate + '&toRangeDate=' + toRangeDate
           		+ '&ps_name='+ encodeURI(encodeURI(ps_name)),
           type:'GET',
           dataType:'json',
           async:false,
           success:function(obj){
            var result = obj[0];
                            
            }
    	});
	 }
	 function exportPowerMeterHistory(){
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
		  $.ajax({
           
           url:'exportPowerMeterHistoryData.action?fromRangeDate='
          		+ fromRangeDate + '&toRangeDate=' + toRangeDate
           		+ '&ps_name='+ encodeURI(encodeURI(ps_name)),
           type:'GET',
           dataType:'json',
           async:false,
           success:function(obj){
            var result = obj[0];
                            
            }
    	});
        
	 }
	  function exportWeatherStationHistory() {
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
		  $.ajax({
           
           url:'exportWSHistoryData.action?fromRangeDate='
          		+ fromRangeDate + '&toRangeDate=' + toRangeDate
           		+ '&ps_name='+ encodeURI(encodeURI(ps_name)),
           type:'GET',
           dataType:'json',
           async:false,
           success:function(obj){
            var result = obj[0];
                            
            }
    	});
	 }
	 
	 function exportJunctionBoxHistory(){
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
		  $.ajax({
           
           url:'exportJBHistoryData.action?fromRangeDate='
          		+ fromRangeDate + '&toRangeDate=' + toRangeDate
           		+ '&ps_name='+ encodeURI(encodeURI(ps_name)),
           type:'GET',
           dataType:'json',
           async:false,
           success:function(obj){
            var result = obj[0];
                             
            }
    	});
		
	 }
  	
  	
	  function PowerMeterHistory() {
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
		 var table_title = ps_name+ "电表历史数据";
		 $('#table_title').text(table_title);
         $("#dg").datagrid({  
             url:"getPowerMeterHistoryData.action?fromRangeDate="
    		+ fromRangeDate + "&toRangeDate=" + toRangeDate
    		+ "&ps_name="+ encodeURI(encodeURI(ps_name)),
            columns: [[            
            { field: 'powermeter_name',title:'电表名称', width:'10%',align:'center' },  
            { field: 'acc_power', title:'累计电量(KWh)', width:'10%',align:'center' },
            { field: 'active_power', title:'有功功率(W)', width: '10%',align:'center' },
            { field: 'reactive_power', title:'无功功率(W)', width: '10%',align:'center' },
            { field: 'power_factor', title:'功率因数', width: '10%',align:'center' },
            { field: 'ac_current', title:'交流电流(A)', width: '10%',align:'center' },
            { field: 'ac_voltage', title:'交流电压(V)', width: '10%',align:'center' },
            { field: 'ac_power', title:'交流功率(W)', width: '10%',align:'center' },         
            { field: 'time', title:'时间', width: '20%',align:'center' }          
            ]]  
         }); 
	  }
	  function JunctionBoxHistory() {
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
		var table_title=ps_name+ "汇流箱历史数据";
		$('#table_title').text(table_title);
        $("#dg").datagrid({  
             url:"getJunctionBoxHistoryData.action?fromRangeDate="
    		+ fromRangeDate + "&toRangeDate=" + toRangeDate
    		+ "&ps_name="+ encodeURI(encodeURI(ps_name)),
            columns: [[            
            { field: 'junctionbox_name',title:'汇流箱名称', width: '20%',align:'center'},  
            { field: 'pv_current', title:'电流', width: '20%',align:'center' },
            { field: 'pv_unit', title:'组串单元',sortable:'true' , width: '20%',align:'center'},
            { field: 'pv_voltage', title:'电压',sortable:'true', width: '20%',align:'center' },                  
            { field: 'time', title:'时间',sortable:'true' , width: '20%',align:'center'}          
            ]]  
        }); 
	 }
	  function weatherStationHistory(psId) {
		  	var startTime = $("#dateFrom").datebox("getValue");  
			var endTime = $("#dateTo").datebox("getValue"); 
			/*debugger*/
		  if(startTime==""||endTime==""){
			  alert("开始时间或结束时间不能为空");
		  }else{
		        $("#dg").datagrid({  
		             url:"getHistoryDataByPsId.action?psId="+psId+"&startTime="+startTime+"&endTime="+endTime,
		            columns: [[     
		            { field: 'x_Coutpout_Current',title:'直流电流(A)', width: '12%',align:'center' },
		            { field: 'x_Coutpout_Voltage',title:'直流电压(V)', width: '12%',align:'center' },  
		            { field: 'x_Coutpout_Power', title:'直流功率(W)', width: '11%',align:'center' },
		            { field: 'outputCurrent', title:'交流电流(A)', width: '11%',align:'center' },
		            { field: 'outputVoltage', title:'交流电压(V)', width: '11%',align:'center' },
		            { field: 'exchangeOutPower', title:'交流功率(W)', width: '11%',align:'center' },    
		            { field: 'lineFrequency', title:'频率(Hz)', width: '11%',align:'center' },
		            { field: 'x_Inerin_tem', title:'温度(°C)', width: '11%',align:'center' },   
		            { field: 'machineState', title:'机器状态', width: '22%',align:'center' } ,
		            { field: 'operateDate', title:'时间', width: '22%',align:'center' } 
		            ]]  
		        }); 
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
         			 
   		$(function(){
   		 
            $('#dg').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());        
       	});
       	
       	
        
    </script>
    
  </body>
</html>
