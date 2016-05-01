<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">  
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"
    />
    <title>组件动态实时曲线</title>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"
    />
    <!--[if lt IE 9]>
      <link rel="stylesheet" type="text/css" href="plugins/jquery-ui/jquery.ui.1.10.2.ie.css"
      />
    <![endif]-->
    <link href="assets/css/main.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/responsive.css" rel="stylesheet" type="text/css"
    />
    <link href="assets/css/icons.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="assets/css/fontawesome/font-awesome.min.css">
    <!--[if IE 7]>
      <link rel="stylesheet" href="assets/css/fontawesome/font-awesome-ie7.min.css">
    <![endif]-->
    <!--[if IE 8]>
      <link href="assets/css/ie8.css" rel="stylesheet" type="text/css" />
    <![endif]-->
    <!-- <link href='http://fonts.useso.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'> -->
    <script type="text/javascript" src="assets/js/libs/jquery-1.10.2.min.js">
    </script>
    <script type="text/javascript" src="plugins/jquery-ui/jquery-ui-1.10.2.custom.min.js">
    </script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js">
    </script>
    <script type="text/javascript" src="assets/js/libs/lodash.compat.min.js">
    </script>
    <!--[if lt IE 9]>
      <script src="assets/js/libs/html5shiv.js">
      </script>
    <![endif]-->
    <script type="text/javascript" src="plugins/touchpunch/jquery.ui.touch-punch.min.js">
    </script>
    <script type="text/javascript" src="plugins/event.swipe/jquery.event.move.js">
    </script>
    <script type="text/javascript" src="plugins/event.swipe/jquery.event.swipe.js">
    </script>
    <script type="text/javascript" src="assets/js/libs/breakpoints.js">
    </script>
    <script type="text/javascript" src="plugins/respond/respond.min.js">
    </script>
    <script type="text/javascript" src="plugins/cookie/jquery.cookie.min.js">
    </script>
    <script type="text/javascript" src="plugins/slimscroll/jquery.slimscroll.min.js">
    </script>
    <script type="text/javascript" src="plugins/slimscroll/jquery.slimscroll.horizontal.min.js">
    </script>
    <!--[if lt IE 9]>
      <script type="text/javascript" src="plugins/flot/excanvas.min.js">
      </script>
    <![endif]-->
    <script type="text/javascript" src="assets/js/app.js">
    </script>
    <!-- 公共文件引入 -->
    <script type="text/javascript" src="js/chart-pub.js"></script>
    <!-- ECharts单文件引入 -->
    <script type="text/javascript" src="plugins/echart/echarts2.js"></script>
    <script type="text/javascript" src="js/kongzhiqi-01.js"></script>
    <script>
      $(document).ready(function() {
        App.init();
        // Plugins.init();
        // FormComponents.init()
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
.page-title h3{
  font-weight: bold;
}
.u1 strong,.u2 strong,.u3 strong{
  color: #1d1d1d ;font-size: 28px; padding: 5px;
}
.u1 .list-group-item{
  background: #9f00a7; color: white
}
.u2 .list-group-item{
  background: #b91d47; color: white
}
.u3 .list-group-item{
  background: #2b5797; color: white
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
          <img src="assets/img/logo.png" alt="logo" height="30" width="30" />
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
              <a href="total.html">
                <i class="icon-cog" style="font-size: 20px;width: 30px;"></i>
                首页
              </a>
            </li>
            <li>
              <a href="zujian.html">
                <i class="icon-cog" style="font-size: 20px;width: 30px;"></i>
                组件
              </a>
            </li>
            <li>
              <a href="xudianchi.html">
                <i class="icon-retweet" style="font-size: 20px;width: 30px;">
                </i>
                蓄电池
              </a>
            </li>
            <li>
              <a href="kongzhiqi.html">
                <i class="icon-dashboard" style="font-size: 20px;width: 30px;">
                </i>
                控制器
              </a>
            </li>
            <li class="current">
              <a href="nibianqi.html">
                <i class="icon-folder-open-alt " style="font-size: 20px;width: 30px;">
                </i>
                逆变器
              </a>
            </li>
            <li>
              <a href="yitiji.html">
                <i class="icon-folder-open-alt " style="font-size: 20px;width: 30px;">
                </i>
                控制逆变一体机
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
            <div class="current-time" style="    padding: 10px 15px;font-size: 14px;font-weight: bold;
    float: left;">采集时刻：
            </div>
          </div>
          <div class="page-header">
          </div>
          <div class="row">
          <div class="col-md-4">
          <div class="widget box" >
                <div class="widget-content u1" style="background: #f1f1f1">
                  <h4><strong  style="">设备基本参数</strong></h4>
                  <ul class="list-group ">
                    <li class="list-group-item">控制器额定功率：32 KW</li>
                    <li class="list-group-item">直流电压等级：2</li>
                    <li class="list-group-item">额定输出电压：220 V</li>
                    <li class="list-group-item">额定输出频率：49.8 Hz</li>
                  </ul>
                </div>
            </div>
          </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <div class="widget box">
                <div class="widget-content">
                  <div id="chart001" class="chart" style="height: 422px;">
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="widget box">
                <div class="widget-content">
                  <img src="./assets/img/demo/nibianqi.gif" alt="" width="900">
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </body>

</html>