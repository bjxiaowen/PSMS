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
      <link rel="stylesheet" type="text/css" href="charts/plugins/jquery-ui/jquery.ui.1.10.2.ie.css"
      />
    <![endif]-->
    <link href="charts/assets/css/main.css" rel="stylesheet" type="text/css" />
    <link href="charts/assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <link href="charts/assets/css/responsive.css" rel="stylesheet" type="text/css"
    />
    <link href="charts/assets/css/icons.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="charts/assets/css/fontawesome/font-awesome.min.css">
    <!--[if IE 7]>
      <link rel="stylesheet" href="charts/assets/css/fontawesome/font-awesome-ie7.min.css">
    <![endif]-->
    <!--[if IE 8]>
      <link href="charts/assets/css/ie8.css" rel="stylesheet" type="text/css" />
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
      <script src="charts/assets/js/libs/html5shiv.js">
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
    
      <script type="text/javascript" src="charts/plugins/flot/excanvas.min.js">
      </script>
    <![endif]-->
    <script type="text/javascript" src="charts/assets/js/app.js">
    </script>
    <!-- ECharts单文件引入 -->
    <script type="text/javascript" src="charts/plugins/echart/echarts2.js"></script>

    <script type="text/javascript" src="charts/js/control000.js"></script>
    <script type="text/javascript" src="charts/js/control001.js"></script>
    <script type="text/javascript" src="charts/js/control002.js"></script>
    <script type="text/javascript" src="charts/js/control003.js"></script>
    <script>
      $(document).ready(function() {
        App.init(); 
      });
      var outList,outTotal,inTotal,voltageTotal,voltageList,modelTotal,modelList;
      var inList = [];
      outList = [<c:out value='${outList[0].power}'/>,
             <c:out value='${outList[1].power}'/>,
             <c:out value='${outList[2].power}'/>,
             <c:out value='${outList[3].power}'/>,
             <c:out value='${outList[4].power}'/>,
             <c:out value='${outList[5].power}'/>,
             <c:out value='${outList[6].power}'/>,
             <c:out value='${outList[7].power}'/>,
             <c:out value='${outList[8].power}'/>,
             <c:out value='${outList[9].power}'/>,
             <c:out value='${outList[10].power}'/>,
             <c:out value='${outList[11].power}'/>,
             <c:out value='${outList[12].power}'/>,
             <c:out value='${outList[13].power}'/>,
             <c:out value='${outList[14].power}'/>,
             <c:out value='${outList[15].power}'/>,
             <c:out value='${outList[16].power}'/>,
             <c:out value='${outList[17].power}'/>,
             <c:out value='${outList[18].power}'/>,
             <c:out value='${outList[19].power}'/>,
             <c:out value='${outList[20].power}'/>,
             <c:out value='${outList[21].power}'/>,
             <c:out value='${outList[22].power}'/>,
             <c:out value='${outList[23].power}'/>,];
      
         inList = [<c:out value='${inList[0].power}'/>,
            <c:out value='${inList[1].power}'/>,
            <c:out value='${inList[2].power}'/>,
            <c:out value='${inList[3].power}'/>,
            <c:out value='${inList[4].power}'/>,
            <c:out value='${inList[5].power}'/>,
            <c:out value='${inList[6].power}'/>,
            <c:out value='${inList[7].power}'/>,
            <c:out value='${inList[8].power}'/>,
            <c:out value='${inList[9].power}'/>,
            <c:out value='${inList[10].power}'/>,
            <c:out value='${inList[11].power}'/>,
            <c:out value='${inList[12].power}'/>,
            <c:out value='${inList[13].power}'/>,
            <c:out value='${inList[14].power}'/>,
            <c:out value='${inList[15].power}'/>,
            <c:out value='${inList[16].power}'/>,
            <c:out value='${inList[17].power}'/>,
            <c:out value='${inList[18].power}'/>,
            <c:out value='${inList[19].power}'/>,
            <c:out value='${inList[20].power}'/>,
            <c:out value='${inList[21].power}'/>,
            <c:out value='${inList[22].power}'/>,
            <c:out value='${inList[23].power}'/>,];
         
         
         
         voltageList = [<c:out value='${voltageList[0].power}'/>,
                   <c:out value='${voltageList[1].power}'/>,
                   <c:out value='${voltageList[2].power}'/>,
                   <c:out value='${voltageList[3].power}'/>,
                   <c:out value='${voltageList[4].power}'/>,
                   <c:out value='${voltageList[5].power}'/>,
                   <c:out value='${voltageList[6].power}'/>,
                   <c:out value='${voltageList[7].power}'/>,
                   <c:out value='${voltageList[8].power}'/>,
                   <c:out value='${voltageList[9].power}'/>,
                   <c:out value='${voltageList[10].power}'/>,
                   <c:out value='${voltageList[11].power}'/>,
                   <c:out value='${voltageList[12].power}'/>,
                   <c:out value='${voltageList[13].power}'/>,
                   <c:out value='${voltageList[14].power}'/>,
                   <c:out value='${voltageList[15].power}'/>,
                   <c:out value='${voltageList[16].power}'/>,
                   <c:out value='${voltageList[17].power}'/>,
                   <c:out value='${voltageList[18].power}'/>,
                   <c:out value='${voltageList[19].power}'/>,
                   <c:out value='${voltageList[20].power}'/>,
                   <c:out value='${voltageList[21].power}'/>,
                   <c:out value='${voltageList[22].power}'/>,
                   <c:out value='${voltageList[23].power}'/>,];
         
         
  	 modelList = [<c:out value='${modelList[0].power}'/>,
                    <c:out value='${modelList[1].power}'/>,
                    <c:out value='${modelList[2].power}'/>,
                    <c:out value='${modelList[3].power}'/>,
                    <c:out value='${modelList[4].power}'/>,
                    <c:out value='${modelList[5].power}'/>,
                    <c:out value='${modelList[6].power}'/>,
                    <c:out value='${modelList[7].power}'/>,
                    <c:out value='${modelList[8].power}'/>,
                    <c:out value='${modelList[9].power}'/>,
                    <c:out value='${modelList[10].power}'/>,
                    <c:out value='${modelList[11].power}'/>,
                    <c:out value='${modelList[12].power}'/>,
                    <c:out value='${modelList[13].power}'/>,
                    <c:out value='${modelList[14].power}'/>,
                    <c:out value='${modelList[15].power}'/>,
                    <c:out value='${modelList[16].power}'/>,
                    <c:out value='${modelList[17].power}'/>,
                    <c:out value='${modelList[18].power}'/>,
                    <c:out value='${modelList[19].power}'/>,
                    <c:out value='${modelList[20].power}'/>,
                    <c:out value='${modelList[21].power}'/>,
                    <c:out value='${modelList[22].power}'/>,
                    <c:out value='${modelList[23].power}'/>,];
      
      
      
   var outList_0 = _.map(outList, function(n) { if(n == undefined){ return 0;}else{return n}});
   var inList_0 = _.map(inList, function(n) { if(n == undefined){ return 0;}else{return n}});
   var voltageList_0 = _.map(voltageList, function(n) { if(n == undefined){ return 0;}else{return n}});
   var modelList_0 = _.map(modelList, function(n) { if(n == undefined){ return 0;}else{return n}});
   console.log(outList_0);
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
            <li>
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
            <li class="current">
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
             输入显示区
              </h3>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="widget box">
               <div class="widget-content">
                  <ul class="stats">
                    <li>
                      <strong>
                        蓄电池电压：${inTotal.totalVoltage}
                      </strong>
                    </li>
                    <li>
                      <strong>
                        蓄电池电压状态：
                      </strong>
                    </li>
                    <li>
                      <strong>
                        蓄电池电流：${inTotal.totalCurrent }
                      </strong>
                    </li>
                    <li>
                      <strong>
                        电池温度：${inTotal.mpptTemp }
                      </strong>
                    </li>
                  </ul>
                </div>
                <div class="divider">
                </div>
                <div class="widget-content">
                  <div id="chart_filled_blue" class="chart">
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="page-header">
            <div class="page-title" style="padding: 8px 0 0px 0px;">
              <h3 style="margin-bottom: 0px;">
              输出显示区
              </h3>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="widget box">
               <div class="widget-content">
                  <ul class="stats">
                    <li>
                      <strong>
                        当前直流电压：${outTotal.totalVoltage }
                      </strong>
                    </li>
                    <li>
                      <strong>
                        当前直流电流：${outTotal.totalCurrent }
                      </strong>
                    </li>
                    <li>
                      <strong>
                        输出状态：
                      </strong><span class="label label-info">输出正常</span>
                    </li>
                  </ul>
                </div>
                <div class="divider">
                </div>
                <div class="widget-content">
                  <div id="chart1" class="chart">
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="page-header">
            <div class="page-title" style="padding: 8px 0 0px 0px;">
              <h3 style="margin-bottom: 0px;">
              电池输入显示区
              </h3>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="widget box">
               <div class="widget-content">
                  <ul class="stats">
                    <li>
                      <strong>
                        蓄电池电压：93
                      </strong>
                    </li>
                    <li>
                      <strong>
                        蓄电池电压状态：<span class="label label-warning">电池欠压</span>
                      </strong>
                    </li>
                    <li>
                      <strong>
                        蓄电池电流：电池充电
                      </strong>
                    </li>
                    <li>
                      <strong>
                        蓄电池电流：23
                      </strong>
                    </li>
                    <li>
                      <strong>
                       电池温度：22
                      </strong>
                    </li>
                  </ul>
                </div>
                <div class="divider">
                </div>
                <div class="widget-content">
                  <div id="chart2" class="chart">
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="widget box">
               <div class="widget-content">
                  <ul class="stats">
                    <li>
                      <strong>
                        蓄电池电压：93
                      </strong>
                    </li>
                    <li>
                      <strong>
                        蓄电池电压状态：<span class="label label-warning">电池欠压</span>
                      </strong>
                    </li>
                    <li>
                      <strong>
                        蓄电池电流：电池充电
                      </strong>
                    </li>
                    <li>
                      <strong>
                        蓄电池电流：23
                      </strong>
                    </li>
                    <li>
                      <strong>
                       电池温度：22
                      </strong>
                    </li>
                  </ul>
                </div>
                <div class="divider">
                </div>
                <div class="widget-content">
                  <div id="chart3" class="chart">
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>

</html>