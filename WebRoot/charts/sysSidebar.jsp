<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.PSMS.pojo.PowerStationBase" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.PSMS.pojo.PSEquipment" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	PowerStationBase currDayCountQs=(PowerStationBase)request.getAttribute("currDayCountQ");
	PowerStationBase currYearCountQ=(PowerStationBase)request.getAttribute("currYearCountQ");
	PowerStationBase currMonthCountQ=(PowerStationBase)request.getAttribute("currMonthCountQ");
	PowerStationBase history=(PowerStationBase)request.getAttribute("history");
	//String psName=(String)session.getAttribute("psName");
	ArrayList<PSEquipment> pslist=(ArrayList<PSEquipment>)session.getAttribute("equipments");
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script>
     /*  var pageName = data_json["pageName"]; */
     
    </script>
<div id="sidebar" class="sidebar-fixed">
        <div id="sidebar-content">
          <ul id="nav">
          		<li data-cur="首页" >
	              <a href="#">
	                <i class="icon-home" style="font-size: 20px;width: 30px;"></i>
	                首页
	              </a>
            	</li>
            	<%
            	if(pslist!=null&&pslist.size()>0){
					for(PSEquipment join:pslist){%>
						 <li data-cur="<%=join.getType() %>">
						 	<a href="#">
						 		<i style="font-size: 20px;width: 30px;"></i>
                				<%=join.getType() %>
						 	</a>	
						 </li> 
					<% }
            	}
				%>
            <!-- <li data-cur="zujian">
              <a href="zujian.html">
                <i class="icon-cog" style="font-size: 20px;width: 30px;"></i>
                组件
              </a>
            </li>
            <li data-cur="xudianchi">
              <a href="xudianchi.html">
                <i class="icon-retweet" style="font-size: 20px;width: 30px;">
                </i>
                蓄电池
              </a>
            </li>
            <li data-cur="kongzhiqi">
              <a href="kongzhiqi.html">
                <i class="icon-dashboard" style="font-size: 20px;width: 30px;">
                </i>
                控制器
              </a>
            </li>
            <li data-cur="nibianqi">
              <a href="nibianqi.html">
                <i class="icon-adjust" style="font-size: 20px;width: 30px;">
                </i>
                逆变器
              </a>
            </li>
            <li data-cur="yitiji">
              <a href="yitiji.html">
                <i class="icon-folder-open-alt " style="font-size: 20px;width: 30px;">
                </i>
                控制逆变一体机
              </a>
            </li> -->
          </ul>
        </div>
        <div id="divider" class="resizeable">
        </div>
      </div>