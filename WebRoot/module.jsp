<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:out value="${total.totalCapacity}"/><br/>
<c:out value="${total.totalPower}"/><br/>
currHour:<c:out value="${total.currHour}"/><br/>


<c:forEach items="${list}" var="item">
<c:choose>
   <c:when test="${item.power==null}"> 
   		0
   </c:when>
   <c:otherwise>
   		${item.power}
   </c:otherwise>
  </c:choose>
</c:forEach>
<script>
var step = "<c:out value='${list[0].power}'/>";
console.log(step);
</script>
<a href="http://localhost:8080/PSMS/charts.action" target="_blank">点我点我~！</a>

