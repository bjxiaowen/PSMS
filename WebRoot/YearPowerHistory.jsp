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
    <base href="<%=basePath%>YearPowerHistory.jsp">   
    <title>年发电量</title>   
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
    		border:0px;padding:3px;
    	}
    	
    </style>
  </head>
  
  <body>
  <div style="height:11%;">
	<P style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">发电量数据 -> 年发电量数据</P>	
    <div>
  		<label>设置	选择年份查看:</label>				
		<select  id="h_year" name="h_year"  class="easyui-combobox" style="width:160px;" data-options=" panelHeight:'auto'">       	
        </select>
        <a id= "abc" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchYearData()">查 询</a>  	
    </div>
  </div>  
 <div style="height:89%;">
 	<div style="float:left; width:33%;height:100%;">
 	<table id="dg"  class="easyui-datagrid" style="text-align:center;height:95%"
            pagination="true"
             pageSize=18 pageList="[18,30, 50]" 
             autoRowHeight="true"  fitcolumns="true" striped="true" 
             rownumbers="true" >
          <thead > </thead>
    </table>
    </div>
    <div id="container_year" style="float:left;width:67%; height:100%; "></div>
</div>
<script>
     $(document).ready(function() { 
     		$.ajax({
				url : 'createHistoryData.action',
				type : 'GET',
				dataType : 'json',
				async : false,
				success : function(obj) {
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
			searchYearData(); 
	});
     
  	function initYearChart(){
		var h_year = document.getElementsByName("h_year")[0].value;
		
  	        var d1 = [];
					$.ajax({//获取后台数据
						url : 'toHistoryOfYear_Chart.action?year=' + h_year,
						type : 'GET',
						dataType : 'json',
						async : false,
						success : function(obj) {
							for ( var i = 0; i < obj[0].length; i += 1) {
								d1.push([obj[1][i], obj[0][i].total_power]);
							}
							
						}
					});
        $('#container_year').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: ''+h_year+'年各电站年发电量数据'
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
            credits : {
            enabled:false//不显示highCharts版权信息
          },
            
            xAxis: {
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
                    text: '发电量值 (MWh)'
                }
            },
            legend: {
                enabled: false
            },
            tooltip: {
                pointFormat: '发电量: <b>{point.y:.1f} MWh</b>',
            },
            series: [{
                name: 'Population',
                data:d1,
                color:'#4876ff',
                dataLabels: {
                    enabled: true,
                    rotation: -90,
                    color: '#ffffff',
                    align: 'right',
                    x: 4,
                    y: 10,
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                        
                    }
                }
            }]
        });
		}
			
	
  	function searchYearData(){
  		var h_year = document.getElementsByName("h_year")[0].value;
        $("#dg").datagrid({  
             url:'toHistoryOfYear.action?year='+h_year,
            columns: [[            
            { field: 'ps_name',title:'电站名称',align:'center'},  
            { field: 'total_power', title:'年发电量(MWh)',align:'center'},
            { field: 'total_hour', title:'电量小时数',align:'center'},
            { field: 'year', title:'年份',align:'center'}
          ]]  
        });
        //$("#container_year").html("");
		initYearChart();
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
