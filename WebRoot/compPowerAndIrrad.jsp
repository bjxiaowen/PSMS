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
    <base href="<%=basePath%>compPowerAndIrrad.jsp">   
    <title>发电量-辐射量对比</title>   
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyui/demo/demo.css">
   
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>    
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script> 
    <script src="HighCharts/highcharts.js"></script>
	<script src="HighCharts/modules/exporting.js"></script> 
    <script type="text/javascript"></script>
    <style>
    	body{
    		border:0px;padding:5px;
    	}
    	
    </style>
  </head>
  
  <body>
  <div style="height:15%;">
	<P style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">数据对比 -> 发电量-辐射量对比</P>	
    <div>
    	<label>设置	统计方式:</label> 
    		<select id="statistic_type" name="statistic_type"  class="easyui-combobox" style="width: 100px;" data-options="onSelect:chooseType, panelHeight:'auto'">
				<option value="按年统计" selected>按年统计</option>
				<option value="按月统计">按月统计</option>
			</select>
      	<label>选择电站:</label>				
		<select  id="ps_name" name="ps_name"  class="easyui-combobox" style="width:160px;" data-options=" panelHeight:'auto'">       	
        </select>
  		<label>选择年份:</label>				
		<select  id="statistic_year" name="statistic_year"  onSelect="chooseYear()" class="easyui-combobox" style="width:80px;" data-options=" panelHeight:'auto'">       	
        </select>
        <label>选择月份:</label>				
		<select  id="statistic_month"  name="statistic_month" disabled="true" class="easyui-combobox" style="width:80px;" data-options=" panelHeight:'auto'">       	
        				
        </select>
        <a id= "abc" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchData()">查 询</a>  	
    </div>
  </div>  
 <div id="container_line" style="height:85%;">

</div>
     <script >
       
 	  var username = "<%=session.getAttribute("User_name")%>";  
	$(document).ready(
			function() {	

				$.ajax({//将电站加入下拉菜单
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
				$.ajax({//将年份加入下拉菜单
					url : 'getAllHistoryYear.action',
					type : 'GET',
					dataType : 'json',
					async : false,
					success : function(obj) {
						 $('#statistic_year').combobox({
       						data:obj.aaData,
    						valueField:'year_name',
    						textField:'year_name'
     						});
     						$('#statistic_year').combobox('setValue',obj.first_year); 			
					}

				});
				
 
				initChart();
			});
	
	function searchData() {
		initChart();
	}
	function chooseType(){
		var type = document.getElementsByName("statistic_type")[0].value;
		if (type == "按年统计") {
				$('#statistic_month').combobox({  disabled:true}); 
		} else{
				$('#statistic_month').combobox({  disabled:false});
				$.ajax({
				url : 'getAllMonth.action',
				type : 'GET',
				dataType : 'json',
				async : false,
				success : function(obj) {
					 $('#statistic_month').combobox({
       					data:obj.aaData,
    					valueField:'year_name',
    					textField:'year_name'
     				});
     				$('#statistic_month').combobox('setValue',obj.first_year); 			
				}
			});
		} 
	}
	function initChart() {
		
		var d1 = [];
		var d2 = [];
		var d3 = [];
		var year = document.getElementsByName("statistic_year")[0].value;
		var ps_name =document.getElementsByName("ps_name")[0].value;
		var type = document.getElementsByName("statistic_type")[0].value;
		var month = document.getElementsByName("statistic_month")[0].value;	
		var chartTitle =year + '年' + ps_name + '发电量-辐射量对比';
		 if (type == "按年统计") {
			$.ajax({
				url : 'toCompOfPowerAndIrradByYear.action?year='
						+ year + '&ps_name='
						+ encodeURI(encodeURI(ps_name)),
				type : 'GET',
				dataType : 'json',
				async : false,
				success : function(obj) {
					for ( var i = 0; i < obj[0].length; i++) {
						d1.push([ obj[0][i].month]);
						d2.push([ obj[0][i].total_power ]);
						d3.push([ obj[1][i] ]);
					}
				}

			});
		} else if (type == "按月统计") {
			chartTitle =year + '年' +month+'月'+ ps_name + '发电量-辐射量对比';
			$.ajax({
				url : 'toCompOfPowerAndIrradByMonth.action?year='
						+ year + '&ps_name='
						+ encodeURI(encodeURI(ps_name)) + '&month='
						+ month,
				type : 'GET',
				dataType : 'json',
				async : false,
				success : function(obj) {
					for ( var i = 0; i < obj[0].length; i++) {
						d1.push([ obj[0][i].day ]);
						d2.push([ obj[0][i].total_power ]);
						d3.push([ obj[0][i].max_irradiation ]);		
					}
				}

			});
		}
		
			$("#container_line").highcharts({
			 chart: {
		                type: 'line',
		                backgroundColor: 'rgba(68, 170, 213, 0.1)'
		            },
			 title: {
			    text: chartTitle,
			    style: {
			    	font: '16px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
			    	}
			},
			credits : {
            enabled:false//不显示highCharts版权信息
          	},
			lang: {
            contextButtonTitle: '图表导出菜单',
            decimalPoint: '.',
            downloadJPEG: "下载JPEG图片",
            downloadPDF: "下载PDF文件",
            downloadPNG: "下载PNG文件",
            downloadSVG: "下载SVG文件",
            drillUpText: "返回 {series.name}",
            loading: '加载中...',
            months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            noData: "没有数据",
            numericSymbols: ['k', 'M', 'G', 'T', 'P', 'E'],
            printChart: "打印图表",
            resetZoom: '重置缩放比例',
            resetZoomTitle: '重置为原始大小',
            shortMonths: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            thousandsSep: ',',
            weekdays: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期天'],
        },
            
			xAxis: {
			    categories: d1,
			    type: 'category',
			    gridLineWidth: 0,
			    lineColor: '#999',
			    tickColor: '#999',
			    labels: {
			    	style: {
			    		color: '#999',
			    		fontWeight: 'bold',
			    		fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
			    	}
			    },
			    title: {
			        text: '日期',
			        //align: 'right'
			    },
               
			},
			
			 yAxis: {
				 alternateGridColor: null,
				 minorTickInterval: null,
				 gridLineColor: '#999',
				 minorGridLineColor: 'rgba(25,255,255,0.07)',
				 lineWidth: 0,
				 tickWidth: 0,
				 labels: {
				 	style: {
				 		color: '#999',
				 	}
				 },
			    title: {
			        text: '发电量 （KWh)/ 辐射量(W/m²)',
			        style: {
			        	color: '#333',
			        	font: '12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
			        	}
			    },
			    plotLines: [{
			        value: 0,
			        width: 1,
			        color: '#808080'
			    }]
			},
			
			tooltip: {
			    valueSuffix: '',
			},
			legend: {
			    borderWidth: 0,
			    itemStyle: {
			    	color: '#CCC'
			    	},
			    	itemHoverStyle: {
			    	color: '#606060'
			    	},
			    	itemHiddenStyle: {
			    	color: '#333'
			    	}
			},
			series: [{
			    name: '发电量(KWh)',
			    data: d2
			}, {
			    name: '辐射量(W/m²)',
			    data: d3
			}]
		}); 
	}       
	
        	
        
    </script>

  </body>
</html>
