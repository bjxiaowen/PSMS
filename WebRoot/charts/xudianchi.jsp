<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.PSMS.pojo.PowerStationBase" %>
<%@ page import="com.PSMS.Hibernate.Inverter_parameter" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.PSMS.pojo.BIPSBaseData" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Inverter_parameter parameter=(Inverter_parameter)request.getAttribute("parameter");
	BIPSBaseData newes=(BIPSBaseData)request.getAttribute("newes");
	String failcode=newes.getX_Failcode_1()+"";
	String fail="正常";
	 if(!failcode.equals("0.00")){
		 fail="欠压";
	} 
	String state=newes.getChargeDischarge()+"";
	 String mState="充电";//0：电池充电,1：电池放电
	if(!state.equals("0.00")){
		mState="放电";
	} 
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
    <title>蓄电池</title>
  <%@ include file="sysJs.jsp"%>
  <!-- 公共文件引入 -->
  <script type="text/javascript">
    	//后台取出数据
    	v = '${list}';
    </script>
    <script type="text/javascript" src="charts/js/chart-pub.js"></script>
    <!-- ECharts单文件引入 -->
    <script type="text/javascript" src="charts/plugins/echart/echarts2.js"></script>
     <script type="text/javascript" src="charts/js/xudianchi-01.js"></script>
     <script type="text/javascript" src="charts/js/xudianchi-02.js"></script>
    <script>
      $(document).ready(function() {
        App.init();
      });
      var vv = data_json["newes"]["batteryVoltage"];
      var c = data_json["newes"]["x_Battery_Current"]; 
      console.log(vv);
      console.log(c);
    </script>
    
    <!--电池电量,温度计必要样式-->
    <link rel="stylesheet" type="text/css" href="charts/assets/css/component.css" />
    <link rel="stylesheet" type="text/css" href="charts/assets/css/custom-bars.css" />
    
    <style>
       .vertical{
         width: 135px;
    height: 8px;
    margin-left: -39px;
    transform: rotate(-90deg);
      }
      
       .progress {
         -webkit-border-radius: 0;
         -moz-border-radius: 0;
         border-radius: 10px;
       }
       .progress .progress-bar {
    background-image: none;
    background-color: #89231f;
}
    </style>
  </head>
  
  <body>
    <header class="header navbar navbar-fixed-top" role="banner">
      <jsp:include page="sysHead.jsp"/>
    </header>
    <div id="container">
      <jsp:include page="sysSidebar.jsp" />
      <div id="content">
        <div class="container">
          <div class="crumbs">
             <div class="current-time" style="    padding: 10px 15px;font-size: 14px;font-weight: bold;
    float: left;">系统时间：
            </div>
          </div>
          <div class="page-header">
            <div class="page-title" style="padding: 8px 0 0px 0px;">
              <h3 style="margin-bottom: 0px;">
              </h3>
            </div>
          </div>
          <div class="row">
            <div class="col-md-4">
              <div class="widget box" >
                <div class="widget-content u1" style="background: #f1f1f1">
                  <h4><strong  style="">设备基本参数</strong></h4>
                  <ul class="list-group ">
                    <li class="list-group-item">额定容量：<%=parameter.getBatteryCapacity() %> AH</li>
                    <li class="list-group-item">额定电压：<%=parameter.getRated_voltage() %> V</li>
                    <li class="list-group-item">无</li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="widget box">
                <div class="widget-content u2" style="background: #f1f1f1">
                  <h4><strong>输出参数</strong></h4>
                  <ul class="list-group ">
                    <li class="list-group-item">功率：<%=newes.getBatteryPower() %> KW</li>
                    <li class="list-group-item">电压：<%=newes.getBatteryVoltage() %> V</li>
                    <li class="list-group-item">电流 :<%=newes.getX_Battery_Current() %>A</li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="widget box">
                <div class="widget-content u3" style="background: #f1f1f1">
                  <h4><strong>状态值</strong></h4>
                  <ul class="list-group ">
                    <li class="list-group-item">蓄电池温度：<%=newes.getX_Battery_tem() %> °C</li>
                    <li class="list-group-item">蓄电池状态：<span class="label label-info"><%=mState %></span></li>
                    <li class="list-group-item">
                     		<%=fail %>
                      </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <!--<div class="row">-->
            <!--<div class="col-md-12">-->
              <!--<div class="widget box">-->
                <!--<div class="widget-content">-->
                  <!--<ul class="stats">-->
                    <!--<li>-->
                      <!--<strong>-->
                        <!--蓄电池电压：95.6 V-->
                      <!--</strong>-->
                    <!--</li>-->
                    <!--<li>-->
                      <!--<strong>-->
                        <!--蓄电池电压状态：<span class="label label-warning">电池欠压</span>-->
                      <!--</strong>-->
                    <!--</li>-->
                    <!--<li>-->
                      <!--<strong>-->
                        <!--蓄电池电流：6.7 A-->
                      <!--</strong>-->
                    <!--</li>-->
                    <!--<li>-->
                      <!--<strong>-->
                        <!--蓄电池功率：0.64 KW-->
                      <!--</strong>-->
                    <!--</li>-->
                  <!--</ul>-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
          <!--</div>-->
          <div class="row">
            <div class="col-md-3">
              <div class="widget box">
                <div class="widget-content" style="text-align: center;">


                  <article class="flexy-grid" style="height:200px;">
                    <h3>电池状态</h3>
                    <div class="flexy-column">
                      <div class="progress-factor flexy-item">
                        <div class="progress-bar">
                          <div class="bar has-rotation has-colors red heat-gradient move " role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100">

                            <div class="bar-face face-position roof percentage"></div>
                            <div class="bar-face face-position back percentage"></div>
                            <div class="bar-face face-position floor percentage volume-lights"></div>
                            <div class="bar-face face-position left"></div>
                            <div class="bar-face face-position right"></div>
                            <div class="bar-face face-position front percentage volume-lights shine">
                              <div class="tooltip heat-gradient-tooltip" style="display:none"></div>
                              <div style="line-height: 1em;
    font-size: 32px;
    position: absolute;
    height: 1em;
    width: 2em;
    margin: .4em .4em .4em .8em;"><%=newes.getX_Battery_Capacity() %></div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </article>


                </div>
                <div class="divider">
                </div>
                <div class="widget-content">
                  <ul class="stats" style="text-align: center;">
                    <li><strong>
                      状态：</strong> <span class="label label-info"><%=fail %></span>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
             <div class="col-md-3">
              <div class="widget box">
                <div class="widget-content" style="text-align: center;">
                  <!--<img src="./assets/img/demo/wenduji.png" alt="" style="height: 200px;">-->
                  <div class="bg_wenduji" style="height: 200px;">
                    <div class="progress vertical">
                      <div class="progress-bar " role="progressbar" aria-valuenow="60" aria-valuemin="-20" aria-valuemax="100" style="width: <%=newes.getX_Battery_tem() %>%;">
                        <span class="sr-only">60% Complete</span>
                      </div>
                    </div>
                  </div>
                  </div>
                <div class="divider">
                </div>
                <div class="widget-content">
                  <ul class="stats" style="text-align: center;">
                    <li>
                      <strong>
                        蓄电池温度：<%=newes.getX_Battery_tem() %>°C
                      </strong>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
             <div class="col-md-3">
              <div class="widget box">
                <div class="widget-content">
                  <div id="chart001" class="chart" style="height: 200px;">
                  </div>
                </div>
                <div class="divider">
                </div>
                <div class="widget-content">
                  <ul class="stats" style="text-align: center;">
                    <li>
                      <strong>
                        蓄电池电压
                      </strong>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
             <div class="col-md-3">
              <div class="widget box">
                <div class="widget-content">
                  <div id="chart002" class="chart" style="height: 200px;">
                  </div>
                </div>
                <div class="divider">
                </div>
                <div class="widget-content">
                  <ul class="stats" style="text-align: center;">
                    <li>
                      <strong>
                        蓄电池电流
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

</html>