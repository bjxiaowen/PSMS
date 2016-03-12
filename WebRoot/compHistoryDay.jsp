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
    <base href="<%=basePath%>compHistoryDay.jsp">   
    <title>发电量辐射量天数对比</title>   
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
	<P style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">数据对比 -> 发电量辐射量天数对比</P>	  
   <div>
  		<label>设置	选择年份:</label>				
		<select  id="h_year" name="h_year"  class="easyui-combobox" style="width:120px;" data-options=" panelHeight:'auto'">       	
        	</select>
		<label>选择电站:</label>				
		<select  id="h_ps_name" name="h_ps_name"  class="easyui-combobox" style="width:160px;" data-options=" panelHeight:'auto'">       	
        </select>
        <a id= "abc" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchData()">查 询</a>  	
    </div>
  </div>  
 <div style="height:85%;">
 	<div id="container_D_P" style="float:left; width:50%;height:100%;"></div>
    	<div id="container_D_I" style="float:left;width:50%; height:100%; "></div>
</div>
     <script>
       
       var username = "<%=session.getAttribute("User_name")%>";   
     
	$(document).ready(
			function() {
				$.ajax({ 
					url : 'toAllStationHistoryData.action?username='+encodeURI(encodeURI(username)),
					type : 'GET',
					dataType : 'json',
					async : false,
					success : function(obj) {
						 $('#h_ps_name').combobox({
       						data:obj.aaData,
    						valueField:'ps_name',
    						textField:'ps_name'
     						});
     						$('#h_ps_name').combobox('setValue',obj.first_ps);			
					}
				});
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

				initPieChart1();
				initPieChart2();
			});

	function searchData() {
		var h_year = document.getElementsByName("h_year")[0].value;
		var h_ps_name =document.getElementsByName("h_ps_name")[0].value;
		initPieChart1();
		initPieChart2();
	}
	function initPieChart1() {
		var h_year = document.getElementsByName("h_year")[0].value;
		var h_ps_name =document.getElementsByName("h_ps_name")[0].value;
	var d1 = [];
	
		$.ajax({//获取后台数据
						url : 'toComparisonOfPower3.action?&year=' + h_year
								+ '&h_ps_name='+ encodeURI(encodeURI(h_ps_name)),
								
						type : 'GET',
						dataType : 'json',
						async : false,
						success : function(obj) {
							for ( var i = 0; i < obj[0].length; i += 1) {
								d1.push([obj[0][i], obj[1][i]]);
							}
						}
				});
    $('#container_D_P').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: h_ps_name+h_year + '年发电量天数统计对比 '
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
            
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: '比例',
            data:d1
        }]
    });
}
	function initPieChart2() {
		var h_year = document.getElementsByName("h_year")[0].value;
		var h_ps_name =document.getElementsByName("h_ps_name")[0].value;
	var d2 = [];
	
	
					$.ajax({//获取后台数据
						url : 'toComparisonOfPower4.action?&year=' + h_year
								+ '&h_ps_name='
								+ encodeURI(encodeURI(h_ps_name)),
						type : 'GET',
						dataType : 'json',
						async : false,
						success : function(obj) {
							for ( var i = 0; i < obj[0].length; i += 1) {
								d2.push([obj[0][i], obj[1][i]]);
							}
						}
					});
	
    $('#container_D_I').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: h_ps_name+h_year + '年辐射量天数统计对比 '
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
            
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: '比例',
            data:d2
        }]
    });
}	
	
       	        
    </script>

  </body>
</html>
