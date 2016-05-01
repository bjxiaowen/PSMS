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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"
    />
    <title>组件动态实时曲线</title>
    <link href="charts/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"
    />
    <!--[if lt IE 9]>
      <link rel="stylesheet" type="text/css" href="plugins/jquery-ui/jquery.ui.1.10.2.ie.css"
      />
    <![endif]-->
    <link href="charts/assets/css/main.css" rel="stylesheet" type="text/css" />
    <link href="charts/assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <link href="charts/assets/css/responsive.css" rel="stylesheet" type="text/css"
    />
    <link href="charts/assets/css/icons.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="charts/assets/css/fontawesome/font-awesome.min.css">
    <!--[if IE 7]>
      <link rel="stylesheet" href="assets/css/fontawesome/font-awesome-ie7.min.css">
    <![endif]-->
    <!--[if IE 8]>
      <link href="assets/css/ie8.css" rel="stylesheet" type="text/css" />
    <![endif]-->
    <link href='charts/css.css' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="charts/assets/js/libs/jquery-1.10.2.min.js">
    </script>
    <script type="text/javascript" src="charts/plugins/jquery-ui/jquery-ui-1.10.2.custom.min.js">
    </script>
    <script type="text/javascript" src="charts/bootstrap/js/bootstrap.min.js">
    </script>
    <script type="text/javascript" src="charts/assets/js/libs/lodash.compat.min.js">
    </script>
    <!--[if lt IE 9]>
      <script src="assets/js/libs/html5shiv.js">
      </script>
    <![endif]-->
    <script type="text/javascript" src="charts/plugins/touchpunch/jquery.ui.touch-punch.min.js">
    </script>
    <script type="text/javascript" src="charts/plugins/event.swipe/jquery.event.move.js">
    </script>
    <script type="text/javascript" src="charts/plugins/event.swipe/jquery.event.swipe.js">
    </script>
    <script type="text/javascript" src="charts/assets/js/libs/breakpoints.js">
    </script>
    <script type="text/javascript" src="charts/plugins/respond/respond.min.js">
    </script>
    <script type="text/javascript" src="charts/plugins/cookie/jquery.cookie.min.js">
    </script>
    <script type="text/javascript" src="charts/plugins/slimscroll/jquery.slimscroll.min.js">
    </script>
    <script type="text/javascript" src="charts/plugins/slimscroll/jquery.slimscroll.horizontal.min.js">
    </script>
    <!--[if lt IE 9]>
      <script type="text/javascript" src="plugins/flot/excanvas.min.js">
      </script>
    <![endif]-->
    <script type="text/javascript" src="charts/assets/js/app.js">
    </script>
    <!-- ECharts单文件引入 -->
    <script type="text/javascript" src="charts/plugins/echart/echarts2.js"></script>
    <script>
      $(document).ready(function() {
        App.init();
        // Plugins.init();
        // FormComponents.init()
        
        
  var step = "<c:out value='${fn:length(list)}'/>";
  var arr = [];
  arr = [<c:out value='${list[0].power}'/>,
         <c:out value='${list[1].power}'/>,
         <c:out value='${list[2].power}'/>,
         <c:out value='${list[3].power}'/>,
         <c:out value='${list[4].power}'/>,
         <c:out value='${list[5].power}'/>,
         <c:out value='${list[6].power}'/>,
         <c:out value='${list[7].power}'/>,
         <c:out value='${list[8].power}'/>,
         <c:out value='${list[9].power}'/>,
         <c:out value='${list[10].power}'/>,
         <c:out value='${list[11].power}'/>,
         <c:out value='${list[12].power}'/>,
         <c:out value='${list[13].power}'/>,
         <c:out value='${list[14].power}'/>,
         <c:out value='${list[15].power}'/>,
         <c:out value='${list[16].power}'/>,
         <c:out value='${list[17].power}'/>,
         <c:out value='${list[18].power}'/>,
         <c:out value='${list[19].power}'/>,
         <c:out value='${list[20].power}'/>,
         <c:out value='${list[21].power}'/>,
         <c:out value='${list[22].power}'/>,
         <c:out value='${list[23].power}'/>,];
  var arr_lodash = _.map(arr, function(n) { if(n == undefined){ return 0;}else{return n}});
        
        
        
        
 var myChart = echarts.init(document.getElementById('chart_filled_blue')); 

option = {
    title : {
        text: '输出功率实时曲线'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['总功率']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['00:00','01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00','09:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00']
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value} kW'
            }
        }
    ],
    series : [
        {
            name:'总功率',
            type:'line',
            data:arr_lodash,
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        }
    ]
};
                    

        // 为echarts对象加载数据 
        myChart.setOption(option); 
      });
    </script>
    <style>
      .navbar{
        background: #2677af;
        border-bottom: 2px solid #2a4053;
      }
      ul.stats li strong, ul.stats li small {
    /* padding: 5px 0; */
     display: inline; 
    /* text-align: center; */
}
ul.stats li {
    display: table-cell;
    padding: 10px;
}
ul.stats li strong {
    font-size: 15px;
    font-weight: bold;
}
    </style>
  </head>
  
  <body>
    <header class="header navbar navbar-fixed-top" role="banner">
      <div class="container">
        <ul class="nav navbar-nav">
          <li class="nav-toggle">
            <a href="javascript:void(0);" title="">
              <i class="icon-reorder">
              </i>
            </a>
          </li>
        </ul>
        <a class="navbar-brand" href="index.html">
          <img src="charts/assets/img/logo.png" alt="logo" height="30" width="30" />
          <strong>
            中兴能源 PSMS
          </strong>
        </a>
        <a href="#" class="toggle-sidebar bs-tooltip" data-placement="bottom"
        data-original-title="Toggle navigation">
          <i class="icon-reorder">
          </i>
        </a>
        <ul class="nav navbar-nav navbar-left hidden-xs hidden-sm">
          <li>
            <a href="#">
              控制台
            </a>
          </li>
        </ul>
      </div>
    </header>
    <div id="container">
      <div id="sidebar" class="sidebar-fixed">
        <div id="sidebar-content">
          <ul id="nav">
            <li class="current">
              <a href="charts.action">
                <i class="icon-cog" style="font-size: 20px;width: 30px;"></i>
                组件
              </a>
            </li>
            <li>
              <a href="toBatteryVoltage.action">
                <i class="icon-retweet" style="font-size: 20px;width: 30px;">
                </i>
                蓄电池
              </a>
            </li>
            <li>
              <a href="toControlOutShow.action">
                <i class="icon-dashboard" style="font-size: 20px;width: 30px;">
                </i>
                控制器
              </a>
            </li>
          </ul>
        </div>
        <div id="divider" class="resizeable">
        </div>
      </div>
      <div id="content">
        <div class="container">
          <div class="crumbs">
            <ul id="breadcrumbs" class="breadcrumb">
              <li>
                <i class="icon-home">
                </i>
                <a href="index.html">
                  控制台
                </a>
              </li>
              <li class="current">
                <a href="pages_calendar.html" title="">
                  日历
                </a>
              </li>
            </ul>
          </div>
          <div class="page-header">
            <div class="page-title" style="padding: 8px 0 0px 0px;">
              <h3 style="margin-bottom: 0px;">
              </h3>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="widget box">
                <div class="widget-content">
                  <div id="chart_filled_blue" class="chart">
                  </div>
                </div>
                <div class="divider">
                </div>
                <div class="widget-content">
                  <ul class="stats">
                    <li>
                      <strong>
                          组件容量：${total.totalCapacity}
                      </strong>
                    </li>
                    <li>
                      <strong>
                        总输出功率：${total.totalPower}
                      </strong>
                    </li>
                    <li>
                      <strong>
                        采集时刻：${total.currHour}:00
                      </strong>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </body>
  <script type="text/javascript">
 
  </script>

</html>