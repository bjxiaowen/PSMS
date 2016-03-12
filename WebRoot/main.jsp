<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <title>管理平台</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" /> 
    <link rel="stylesheet" type="text/css" href="easyui/demo.css">
    <script src="js/jquery-1.6.2.min.js" type="text/javascript"></script>  
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    
    <style>
    	body{
    		border:0px;
    		margin: 0;padding: 0;font-family: Arial, Verdana, Helvetica, sans-serif;font-family:"雅黑宋体", "微软雅黑", "新宋体", "宋体";
    	}
    	    
			#north-left
			{
			 width:45%;float:left;height:100%;border:0px;		
			}
			
			#north-center
			{
				width:32%;float:left;height:100%;line-height:1.7em;font-size: 15px;
			}
			
			#north-right
			{
				width:23%;float:right;height:100%;clear:right;
			}
			
			

	
    </style>
   
    <script type=text/javascript>
     var username = "<%=session.getAttribute("User_name")%>";
	$(document).ready(function() {//页面准备方法，更新系统时间	
		var time1=current();
		setInterval(function(){$("#time").html(current)},1000); 
		//if(username=="null"){
			//window.location.href="<%=basePath%>"+"Login.jsp";
		//}
	});

		function current()//获取系统时间的方法
		{ 
			var d=new Date(),str='今天是  '; 
			str +=d.getFullYear()+'-'; //获取当前年份 
			str +=d.getMonth()+1+'-'; //获取当前月份（0——11）
			str +=d.getDate()+'日 '; 
			str +=d.getHours()+':'; 
			str +=d.getMinutes()+':'; 
			str +=d.getSeconds()+'';
			return str;
		} 
		function opeanHtml(t){
			$("#content").find("iframe").attr("src",t.name+".action");
		}
		 function addHeart(title,url){
			 if(confirm("要收藏本页吗？")==true)   
				  try {
				      window.external.addFavorite(url, title);
				  }
					catch (e) {
				     try {
				       window.sidebar.addPanel(title, url, "");
				    }
				     catch (e) {
				         alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加");
				     }
				  }	    
		}

</script>
</head>
	
<body>    
    <div class="easyui-layout" style="width:100%;height:100%;">
        <div data-options="region:'north'"  style="height:13%;background-color:#43cd80 ;">					    					                 			
		    	<div id="north-left">
		    	<div  style="width:100%;height:30%;">	
		    		</div><div style="color:#ffffff;font: bold 16px '宋体','微软雅黑'; font-size:40px;text-align:center;vertical-align:middle;">室内空气质量评价   		
  				</div></div>
  				<div id="north-center" >
		    		<div  style="width:100%;height:40%;">	
		    		</div>		    	
  					<div style="width:100%;height:60%;">
  						<div style="color:#ffffff;font: bold 16px '宋体','微软雅黑';magrin-left:30px;text-align:right;" >欢迎您！${User_name}</div>	
		   				<div id="time" style="color:#ffffff;font: bold 16px '宋体','微软雅黑';magrin-left:30px;text-align:right;" ></div>	 						
  					</div>	
		    	</div>		    	
  				<div id="north-right"> 	
  		            <div  style="width:100%;height:59%;">	
		    		</div>	
		    		<div  style="width:100%;height:40%;">	
		    				<a href="Login.jsp" style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;" title="用户登录">&nbsp&nbsp&nbsp用户登录</a>															
		    		</div>	         																																		                				
  				</div>	  
        </div>       
        <div data-options="region:'west',split:true,align:'center'" title="管理平台" style="width:14%;text-align:left;">
        	<div class="easyui-accordion" data-options="fit:true,border:false,align:'center'">
	                <div title="数据设置" data-options="selected:true" >
	                   	<div style="width:100%;border:0px;height:30px;background:#f0f0f0;text-align:left;line-height:30px; ">
	                   		<a href="javascript:void(0)"  onclick="opeanHtml(this)" name="toUserManage" style="text-decoration:none;padding:0px 30px 0px 30px;color:#000000;font:'宋体','微软雅黑';">创建医院</a>
	                   	</div>
	                   	<div style="width:100%;border:0px;height:30px;background:#E0ECFF;text-align:left;line-height:30px;">
	                   		<a href="javascript:void(0)"onclick="opeanHtml(this)" name="toConnectionBookManage" style="text-decoration:none;padding:0px 30px 0px 30px;color:#000000;font:'宋体','微软雅黑';">评价标准维护</a>
	                   	</div>
	                   	<div style="width:100%;border:0px;height:30px;background:#f0f0f0;text-align:left;line-height:30px; ">
	                   		<a href="javascript:void(0)"  onclick="opeanHtml(this)" name="toUserManage" style="text-decoration:none;padding:0px 30px 0px 30px;color:#000000;font:'宋体','微软雅黑';">分级标准维护</a>
	                   	</div>	                   	
	                </div>
	                <div title="数据导入"  >
	                    <div style="width:100%;border:0px;height:30px;background:#f0f0f0;text-align:left;line-height:30px;">
	                   		<a href="javascript:void(0)" onclick="opeanHtml(this)" name="toPsSearch" style="text-decoration:none;padding:0px 30px 0px 30px;color:#000000;font:  '宋体','微软雅黑';">导入文件</a>
	                   	</div>
	                   	<div style="width:100%;border:0px;height:30px;background:#E0ECFF;text-align:left;line-height:30px;">
	                   		<a href="javascript:void(0)" onclick="opeanHtml(this)" name="toPsPeriod" style="text-decoration:none;padding:0px 30px 0px 30px;color:#000000;">生成报告</a>
	                   	</div>
	                   	
	                </div>
	                <div title="数据查询"  >
	                   	<div style="width:100%;border:0px;height:30px;background:#f0f0f0;text-align:left;line-height:30px;">
	                   		<a href="javascript:void(0)" onclick="opeanHtml(this)" name="toEquipmentManage" style="text-decoration:none;padding:0px 30px 0px 30px;color:#000000;">横向数据查询</a>
	                   	</div>
	                   	<div style="width:100%;border:0px;height:30px;background:#E0ECFF;text-align:left;line-height:30px;">
	                   		<a href="javascript:void(0)" onclick="opeanHtml(this)" name="toPsPeriod" style="text-decoration:none;padding:0px 30px 0px 30px;color:#000000;">纵向数据查询</a>
	                   	</div>
	                </div>
	               
            </div>
        </div>
        <div id="content" data-options="region:'center'">
           <iframe id="display" style="width:100%;height:99%;border:0px;background-image:url('images/logo2.jpg');background-repeat:no-repeat;background-position:center;
     		"></iframe>
        </div>
         <div data-options="region:'south',split:true" style="height:5%;text-align:center;padding:3px;font-size:12px;color:#999999;">绿谷科技 &copy; 2015</div>
    </div>
 
<script type=text/javascript>
	function toDesktop(){
       		$.ajax({
       				 url:'toDesktop.action?uName='+username,
       				 type:'GET',
             		 dataType:'json',
             		 async:false,
             		 success:function(obj){
	           			var result = obj[0];
	           			if(result=="Role1"){
	           				location.href = 'superManageDesk.jsp';
	           			}
	           			else location.href = 'Login.jsp';
           			}          
    		});	
    } 

</script>
</body>
</html>
