<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="UTF-8">
    <base href="<%=basePath%>page2.jsp">
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
 
 
	$(document).ready(function() {//页面准备方法，更新系统时间
		var time1=current();
		setInterval(function(){$("#time").html(current)},1000); 
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
			$("#content").find("iframe").attr("src",t.name+".jsp");
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
	<%
		String username = session.getAttribute("User_name").toString();
		
	%>    
    
    <div class="easyui-layout" style="width:100%;height:100%;">
        <div data-options="region:'north'" style="height:14%;background:#3366cc;">
        
        		<div id="north-left" style="color:#ffffff;font-size:30px;vertical-align:middle;">管理平台    		
  				</div>  						    					                 			
		    	<div id="north-center" >
		    		<div  style="width:100%;height:40%;">	
		    		</div>		    	
  					<div style="width:100%;height:60%;">
  						<div  id="name1" style="color:#ffffff;magrin-left:30px;text-align:right;" >欢迎您！${User_name}</div>	
		   				<div id="time" style="color:#ffffff;magrin-left:30px;text-align:right;" ></div>	 						
  					</div>	
		    	</div>		    	
  				<div id="north-right"> 	
  		            <div  style="width:100%;height:59%;">	
		    		</div>	
		    		<div  style="width:100%;height:40%;">	
		    				<a href="Home.jsp" class="icon-home" title="首页" style="font-size: 20px;color:#ffffff;margin-left:20px;"></a>																               	                             					
							<a href="javascript:void(0)"  onclick="addHeart('中兴能源首页','http://121.0.0.1:8080/PSMS/Home.jsp')" class="icon-heart" style="font-size: 20px;color:#ffffff;margin-left:20px;" title="收藏本站"></a>																	                	                                					
							<a href="Contact_us.jsp" class="icon-envelope-alt" style="font-size: 20px;color:#ffffff;margin-left:20px;"  title="联系我们"></a>																                	                	    			                  					
							<a href="userLogin" class="icon-user" style="font-size: 20px;color:#ffffff;margin-left:20px;" title="用户登录"></a>
							<a href="javascript:void(0)" onclick="toDesktop()" class="icon-desktop" title="返回桌面" style="font-size: 20px;color:#ffffff;margin-left:20px;"></a>	
							
		    		</div>	         					
																												
	                				
  				</div>	  
        </div>
       
        
        <div data-options="region:'west',split:true,align:'center'" title="管理平台" style="width:14%;text-align:center;">
        	<div class="easyui-accordion" data-options="fit:true,border:false,align:'left'">
	                <div title="用户管理" data-options="selected:true" >
	                   	<div style="width:100%;border:0px;height:30px;background:#f0f0f0;text-align:left;line-height:30px;">
	                   		<a href="javascript:void(0)"  onclick="opeanHtml(this)" name="partuser"  style="text-decoration:none;padding:25px;color:#000000;">用户查看</a>
	                   	</div>
	                   	
	                   	
	                </div>
            </div>
        </div>
        <div id="content" data-options="region:'center'">
           <iframe id="display" style="width:100%;height:99%;border:0px;"></iframe>
        </div>
         <div data-options="region:'south',split:true" style="height:5%;text-align:center;padding:3px;">版权所有  盗版必究</div>
    </div>

	<script type=text/javascript>
	var username ='<%=username%>'; 
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
	           			else if(result=="Role2"){
	           				location.href = 'superResearchDesk.jsp';
	           			}
	           			else if(result=="Role3"){
	           				location.href = 'psManageDesk.jsp';
	           			}
	           			else if(result=="Role4"){
	           				location.href = 'psResearchDesk.jsp';
	           			}
           			}          
    			});	
    }  

</script>
</body>
</html>
