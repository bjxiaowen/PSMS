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
    <base href="<%=basePath%>compTheoreticalAndActual.jsp">   
    <title>理论电量-实际发电量对比</title>   
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
	<P style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">数据对比 -> 理论电量-实际发电量对比</P>	
    <div>
    	<label>设置	选择年份查询:</label> 				
		<select  id="h_year" name="h_year"  class="easyui-combobox" style="width:120px;" data-options=" panelHeight:'auto'">       	
        </select>      
        <a id= "abc" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchData()">查 询</a>  	
    </div>
  </div>  
 <div id="container" style="height:85%;">

</div>
     <script>
        var username = "<%=session.getAttribute("User_name")%>";   
 	$(document).ready(
			function() {

				$.ajax({ 
						url : 'getAllHistoryYear.action',
					type : 'GET',
					dataType : 'json',
					async : false,
					success : function(obj) {
						 $('#h_year').combobox({
       						data:obj.aaData,
    						valueField:'year_name',
    						textField:'year_name'
     						});
     						$('#h_year').combobox('setValue',obj.first_year); 			
					}

				});
				initChart();
			});

	function searchData() {
		initChart();
	}
	function initChart() {

		var d1 = [];
		var d2 = [];
		var d3 = [];
		var h_year = document.getElementsByName("h_year")[0].value;

					$.ajax({//获取后台数据
							url : 'toComparisonOfPower.action?&year=' + h_year,
							type : 'GET',
							dataType : 'json',
							async : false,
							success : function(obj) {
								for ( var i = 0; i < obj[0].length; i += 1) {
									d1.push([obj[0][i]]);
									d2.push([obj[1][i]]);
									d3.push([obj[2][i]]);
								}
							}
						});
				


	    $("#container").highcharts({
	       
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text:h_year+ '年理论发电量和实际发电量的比较'
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
	            labels: {
	                    rotation: -30,
	                    align: 'right',
	                    style: {
	                        fontSize: '13px',
	                        fontFamily: 'Verdana, sans-serif'
	                    }
	                }
	            },
	       yAxis: {
	                min: 0,
	                title: {
	                    text: '发电量 (MWh)'
	                }
	            },
	       series: [{
	                name: '理论发电量',
	                data: d2
	            }, {
	                name: '实际发电量',
	                data: d3
	            }],
	            tooltip: {
	                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                    '<td style="padding:0"><b>{point.y:.1f} MWh</b></td></tr>',
	                footerFormat: '</table>',
	                shared: true,
	                useHTML: true
	            }
	    });
	}
   </script>
  </body>
</html>
