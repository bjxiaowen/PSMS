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
    <script src="HighCharts/highcharts.js"></script>
    <script src="HighCharts/modules/no-data-to-display.js"></script>
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
	<P style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">站内对比 -> 站内气象站对比</P>
	
    <div id="toolbar"  >
		<lable style="margin-left:10px">设置   选择时间:</lable>
  		<input id="dateFrom" name="dateFrom" class="easyui-datebox" style="width:160px;"></input>
  		<label>电站名称:</label>				
		<select  id="ps_name" name="ps_name"  class="easyui-combobox" style="width:160px;" data-options=" panelHeight:'auto'">       	
        </select>
   				
		
        <a id= "abc" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="searchData()">查 询</a> 
	 	
    </div>
    </div>
 	  <div id="container_line" style="height:85%;">

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
			var fromdate ="" + year+ "-" + month +"-" + date ;
			var todate = "" + year+ "-" + month +"-" + date ;
			$('#dateFrom').datebox('setValue', fromdate);
			//$('#dateTo').datebox('setValue', todate);
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
        var d1 = new Array();
        for(var k=0;k<7;k++)
        {
           d1[k]=new Array();
        }
        var d2 = new Array();
        for(var k=0;k<7;k++)
        {
           d2[k]=new Array();
        }
        var d3 = [];
        var d4 = [];
        var number;
		var tempStr = $("#dateFrom").datebox("getValue");  
		var dateFrom  = new Date(tempStr);
		var year1=dateFrom.getFullYear();
		var month1=dateFrom.getMonth()+1;
		var day1=dateFrom.getDate();
		var fromRangeDate = "" + month1 + "/" + day1 + "/" + year1;
		var ps_name = document.getElementsByName("ps_name")[0].value;
		var chartTitle =year1+"年"+month1+"月"+day1+"日"+ps_name +"站内气象站数据对比";
		$.ajax({
				url : 'getWSCompData1.action?fromRangeDate='
    		+ fromRangeDate + '&toRangeDate=' + fromRangeDate
    		+ '&ps_name='+ encodeURI(encodeURI(ps_name)),
				type : 'GET',
				dataType : 'json',
				async : false,
				success : function(obj) {
					for ( var i = 0; i < (obj.length-1); i++) {
					    for(var j=0;j<obj[i+1].length;j++)
					    {
					        d1[i].push(obj[i+1][j].irraditationValue);
					        d2[i].push(obj[i+1][j].temperature);
					    }
					}
					for(var i=0;i<obj[1].length;i++)
					{
					   var time=obj[1][i].time;
					   var time=time.substring(11,16);
					   d3.push(time);
					    	
					}
				    number=obj.length-1;
				    for(var i=0;i<obj[0].length;i++)
				    {
				    d4.push(obj[0][i]);
				    }
			        
				}

			});
			$("#container_line").highcharts({
        chart: {
         zoomType: 'xy'
        },
        title: {
           text: chartTitle,
			    style: {
			    	font: '16px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
			    	}
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
         noData: {
            style: {
                fontWeight: 'bold',
                fontSize: '20px',
                color: '#303030'
            }
        },
        credits : {
            enabled:false//不显示highCharts版权信息
          	},
        xAxis: {
           categories: d3,
			    type: 'category',
			    gridLineWidth: 0,
			    lineColor: '#999',
			    tickColor: '#999',
			    tickInterval: 4,
			    labels: {
			    	style: {
			    		color: '#999',
			    		fontWeight: 'bold',
			    		fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
			    	}
			    },
			    title: {
			        text: '时间',
			        //align: 'right'
			    }
        },
        yAxis: [ { // Secondary yAxis
            title: {
                text: '温度(°C)',
                style: {
                    color: '#4572A7'
                }
            },
            labels: {
                
                style: {
                    color: '#4572A7'
                }
            },
            opposite: true
        },{ // Primary yAxis
            labels: {
        
                style: {
                    color: '#89A54E'
                }
            },
            title: {
                text: '辐射值(W/m²)',
                style: {
                    color: '#89A54E'
                }
            }
        }],
       tooltip: {
                shared: true,
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
      
        series: [
            <%for(int i=0;i<2;i++){%>
            {
			    name: d4[<%=i%>]+'温度',
			    type: 'column',
			    data: d2[<%=i%>]
			},<%}%>/*{
			    name: '2#温度',
			    type: 'column',
			   
			    data: d2[1]
			},*/ <%for(int i=0;i<2;i++){%>
			{
			    name: d4[<%=i%>]+'辐照',
			    type: 'line',
			    yAxis: 1,
			    data: d1[<%=i%>]
			},<%}%> /*{
			    name: '2#辐照',
			    type: 'line',
			     yAxis: 1,
			    data: d1[1]
			}*/]
    });
		/*$("#container_line").highcharts(
			 /*chart: {
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
        noData: {
            style: {
                fontWeight: 'bold',
                fontSize: '20px',
                color: '#303030'
            }
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
			        text: '时间',
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
			        text: '辐射值(W/m²) ',
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
			}/*,
			{
			 title: {
			        text: '温度(°C)',
			        style: {
			        	color: '#333',
			        	font: '12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
			        	}
			    }，
			    labels: {
				 	style: {
				 		color: '#999',
				 	}
				 },
			   opposite: true
			},*/
			
			/*tooltip: {
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
			    name: '1#辐照',
			    //type: 'line',
			     //yAxis: 1,
			    data: d2
			}, {
			    name: '2#辐照',
			    //type: 'line',
			    //yAxis: 1,
			    data: d3
			},{
			    name: '1#温度',
			    type: 'column',
			    yAxis: 2,
			    data: d4
			},{
			    name: '2#温度',
			    type: 'column',
			    yAxis: 2,
			    data: d5
			}]
		});*/ 
     }
  	/*function searchData(){
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
       		 });*/
      	
   </script>
    
  </body>
</html>
