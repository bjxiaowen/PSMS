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
    <base href="<%=basePath%>psSearch.jsp">   
    <title>psSearch</title>   
	<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyui/demo/demo.css">
    
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>    
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script> 
    <script type="text/javascript"></script>
    <style>
    	body{
    		border:0px;padding:5px;
    	}
    	
    </style>
  </head>
  
  <body>
	<P style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">电站管理->电站查看</P>
	<table id="psL" title="电站列表" class="easyui-datagrid" style="width:100%;height:95%;text-align:center"
            url="getPsInformation.action" 
             toolbar="#toolbar"	pagination="true"
             pageSize=20 pageList="[ 10,20, 30]" 
            rownumbers="true" fitColumns="true" singleSelect="true"
             autoRowHeight="true"    
            striped="true" sortName="id" sortOrder="asc" remoteSort="flase">
        <thead>
            <tr> 
                <th field="id"             align="center"width="20" hidden="true">电站id</th>        
                <th field="name"           align="left"  width="25" sortable="true">电站名称</th>
                <th field="capacity"       align="center"width="20" sortable="true">电站容量(KW)</th>
                <th field="area"           align="center"width="20" sortable="true">占地面积(公顷)</th>
                <th field="part_num"       align="center"width="15" sortable="true">光伏板组件数量</th>
                <th field="owner"          align="left"  width="20" sortable="true">业主</th>
                <th field="investor"       align="left"  width="20" sortable="true">投资方</th>
                <th field="province"       align="left"  width="20" sortable="true">所属省份</th> 
                <th field="longitude"      align="right" width="20" sortable="true">经度</th>
                <th field="latitude"       align="right" width="20" sortable="true">纬度</th>
                <th field="build_time"     align="center"width="20" sortable="true">建站时间</th>
        
            </tr>
        </thead>
    </table>
    <div id="toolbar" >
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newPs()">新建电站</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editPs()">编辑电站</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyPs()">删除电站</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editPsP()">编辑图片</a>		
		<input  id="search_name" class="easyui-searchbox" data-options="prompt:'请输入电站名查找',searcher:searchPsByName" style="width:180px"></input>
		<lable >省份查询</lable>
		<select id="search_province"  name="search_province" class="easyui-combobox" style="width:110px" data-options="onSelect:searchPsByProvince">
					<option selected ></option>
     			   <option value="安徽">安徽</option>
     			   <option value="北京" >北京</option>
				   <option value="重庆" >重庆</option>
				   <option value="广东">广东</option>
				   <option value="广西">广西</option>
				   <option value="贵州">贵州</option>
				   <option value="甘肃">甘肃</option>
				   <option value="福建">福建</option>
				   <option value="河北">河北</option>
				   <option value="黑龙江">黑龙江</option>
				   <option value="海南">海南</option>
				   <option value="湖北">湖北</option>
				   <option value="湖南">湖南</option>
     			   <option value="河南">河南</option>
     			   <option value="江苏">江苏</option>
				   <option value="吉林">吉林</option>
				   <option value="江西">江西</option>
				   <option value="辽宁">辽宁</option>
				   <option value="内蒙古" >内蒙古</option>
				   <option value="宁夏">宁夏</option>				  
				   <option value="青海">青海</option>
				   <option value="上海">上海</option>
				   <option value="山东">山东</option>
				   <option value="山西">山西</option>
				   <option value="四川">四川</option>
				   <option value="陕西">陕西</option>
				   <option value="天津">天津</option>	
				   <option value="西藏">西藏</option>
				   <option value="新疆">新疆</option>
				   <option value="云南">云南</option>
				   <option value="浙江">浙江</option>  	   				      
   		</select>
   		   	<lable style="margin-left:10px">建站时间查询  起始时间</lable>
  			<input id="search_build_time1" name="build_time1" class="easyui-datebox" data-options="onSelect:searchPsByTime2"></input>
  			<label>结束时间</label>
  			<input id="search_build_time2" name="build_time2" class="easyui-datebox" data-options="onSelect:searchPsByTime"></input>
    </div>
    
    
    <div id="dlg" class="easyui-dialog" style="width:420px;height:450px;padding:10px 20px;" 
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">电站信息</div>
        <form id="fm" method="post" >
            <div class="fitem">
                <label style="width:30%">电站名称</label>
                <input id="ps_name" name="name" class="easyui-textbox" >(必填)</input>
                <input id="ps_ex_name" name="ex_name" type="hidden" ></input>  
            </div>
            <div class="fitem">
                <label style="width:30%">电站容量(KW)</label>
				<input id="ps_capacity" name="capacity"   class="easyui-textbox">(必填)</input>
                <input id="ex_ps_capacity" name="ex_ps_capacity" type="hidden" ></input>    
            </div>
            
           <div class="fitem" id="first_capacity_fitem">
                <label style="width:30%">首期容量(KW)</label>
				<input id="ps_first_capacity" name="first_capacity"   class="easyui-textbox">(必填)</input>   
            </div>
            
            
            <div class="fitem">
                <label style="width:30%">占地面积(公顷)</label>
				<input id="ps_area" name="area" class="easyui-textbox"  ></input>
                <input id="ex_ps_area" name="ex_ps_area" type="hidden" ></input>    
            </div>
            <div class="fitem">
                <label style="width:30%">光伏板组件数量</label>
				<input id="ps_part_num" name="part_num" class="easyui-textbox" ></input>
                <input id="ex_ps_part_num" name="ex_ps_part_num" type="hidden" ></input>    
            </div>
            <div class="fitem">
                <label style="width:30%">业主</label>
				<input id="ps_owner" name="owner" class="easyui-textbox" ></input>
                <input id="ex_ps_owner" name="ex_ps_owner" type="hidden" ></input>    
            </div>
            <div class="fitem">
                <label style="width:30%">投资方</label>
				<input id="ps_investor" name="investor" class="easyui-textbox" ></input>
                <input id="ex_ps_investor" name="ex_ps_investor" type="hidden" ></input>    
            </div>
            <div class="fitem">
                <label style="width:30%">所属省份</label>		
				<select  id="ps_province" name="province" class="easyui-combobox" style="width:160px">
     			   <option value="安徽">安徽</option>
     			   <option value="北京" >北京</option>
				   <option value="重庆">重庆</option>
				   <option value="广东">广东</option>
				   <option value="广西">广西</option>
				   <option value="贵州">贵州</option>
				   <option value="甘肃">甘肃</option>
				   <option value="福建">福建</option>
				   <option value="河北">河北</option>
				   <option value="黑龙江">黑龙江</option>
				   <option value="海南">海南</option>
				   <option value="湖北">湖北</option>
				   <option value="湖南">湖南</option>
     			   <option value="河南">河南</option>
     			   <option value="江苏">江苏</option>
				   <option value="吉林">吉林</option>
				   <option value="江西">江西</option>
				   <option value="辽宁">辽宁</option>
				   <option value="内蒙古">内蒙古</option>
				   <option value="宁夏">宁夏</option>				  
				   <option value="青海">青海</option>
				   <option value="上海">上海</option>
				   <option value="山东">山东</option>
				   <option value="山西">山西</option>
				   <option value="四川">四川</option>
				   <option value="陕西">陕西</option>
				   <option value="天津">天津</option>	
				   <option value="西藏">西藏</option>
				   <option value="新疆">新疆</option>
				   <option value="云南">云南</option>
				   <option value="浙江">浙江</option>			   				      
   				</select>
								
                <input id="ex_ps_province" name="ex_ps_province" type="hidden" ></input>    
            </div>
            <div class="fitem">
                <label style="width:30%">经度</label>
				<input id="ps_longitude" name="longitude" class="easyui-textbox">(必填)</input>
                <input id="ex_ps_longitude" name="ex_ps_longitude" type="hidden" ></input>    
            </div>
            <div class="fitem">
                <label style="width:30%">纬度</label>
				<input id="ps_latitude" name="latitude" class="easyui-textbox" >(必填)</input>
                <input id="ex_ps_latitude" name="ex_ps_latitude" type="hidden" ></input>    
            </div>
            <div class="fitem">
                <label style="width:30%">建站时间</label>
				<input id="ps_build_time" name="build_time" class="easyui-datebox" ></input>
                <input id="ex_ps_build_time" name="ex_ps_build_time" type="hidden" ></input>  
            </div>         
        </form>
        
    </div>
    <div id="dlgP" class="easyui-dialog" style="width:400px;height:340px;padding:10px 20px;display:none;"
            closed="true" >
         <form id="fmP" name="fmp" action="upLoadPicture.action" method="post"  enctype="multipart/form-data" style="padding: 0px; margin: 0px;">
				<input type="file" name="pic" id="fileToUpload" multiple="multiple" onchange="fileSelected();" />                 
				<input id="uploadFile" type="submit" style=" display:none" value="上传图片" />
				<div id="fileName" style="padding: 10px"></div>
                <div id="fileSize" style="padding: 10px"></div> 
                <a href="javascript:void(0)" id="deletePic" name="deletePic"  style="align:center;display:none;"class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyPic()">删除图片</a>
                <input id="IDp" name="IDp" type="hidden"></input>
                <input id="IDps" name="IDps" type="hidden"></input>
                <div><img name="picimg" style="width:320px;height:180px;" id="picimg"></img></div>
                
           </form>        
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="savePs()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
        <script type="text/javascript">
        $('#dt').datebox('setValue', ps_build_time);
        var url;
        var flag = 0;

        function newPs(){
        	flag = 0; 
            $('#dlg').dialog('open').dialog('setTitle','新建电站');
            $('#fm').form('clear');
  			var curr_time = new Date();
  		    var strDate = curr_time.getFullYear()+"-";
  			strDate += curr_time.getMonth()+1+"-";
  			strDate += curr_time.getDate();
  			$('#ps_build_time').datebox('setValue', strDate); //默认购买时间为当天
  	  
  			
$('#ps_first_capacity').textbox('setValue',''); 
document.getElementById("first_capacity_fitem").style.display="";
  			
        }
        function editPs(){
            var row = $('#psL').datagrid('getSelected');
            if (row){
                flag = 1;                
                $('#dlg').dialog('open').dialog('setTitle','编辑电站');
                $('#fm').form('load',row); 
                $('#ps_build_time').datebox('setValue', row.build_time);
               var ex_name = document.getElementsByName("ex_name");
                ex_name[0].value= row.name;
                
				document.getElementById("first_capacity_fitem").style.display="none";          

                
               /* var ex_ps_name = document.getElementsByName("ex_ps_name");ex_ps_name[0].value= row.ps_name;
                var ex_ps_capacity = document.getElementsByName("ex_ps_capacity");ex_ps_capacity[0].value= row.ps_capacity;
                var ex_ps_area = document.getElementsByName("ex_ps_area");ex_ps_area[0].value= row.ps_area;
                var ex_ps_part_num = document.getElementsByName("ex_ps_part_num");ex_ps_part_num[0].value= row.ps_part_num;
                var ex_ps_owner = document.getElementsByName("ex_ps_owner");ex_ps_owner[0].value= row.ps_owner;
                var ex_ps_investor = document.getElementsByName("ex_ps_investor");ex_ps_investor[0].value= row.ps_investor;
                var ex_ps_province = document.getElementsByName("ex_ps_province");ex_ps_province[0].value= row.ps_province;
                var ex_ps_name = document.getElementsByName("ex_ps_name");ex_ps_name[0].value= row.ps_name;
                var ex_ps_name = document.getElementsByName("ex_ps_name");ex_ps_name[0].value= row.ps_name;
                var ex_ps_name = document.getElementsByName("ex_ps_name");ex_ps_name[0].value= row.ps_name;
                */
            }
        }
        
        function editPsP(){
        	 $("#uploadFile").hide();   
        	 $("#deletePic").hide(); 
            var row = $('#psL').datagrid('getSelected');   
            if (row){
            	var divPSP=document.getElementById("dlgP");
            	divPSP.style.display="";
                $('#dlgP').dialog('open').dialog('setTitle','编辑电站图片');
                document.fmp.reset();
                var IDps = document.getElementsByName("IDps"); IDps[0].value= row.id;
     			$.ajax({
                    
                    url:'getPSPName.action?ps_id='+row.id,
                    type:'GET',
       	         	dataType:'json',
       	         	async:false,
       	         	success:function(obj){
                     	  var result = obj[0];
                         if (result.errorMsg){
                             $.messager.show({
                                 title: 'Error',
                                 msg: result.errorMsg
                             });
                         } else {
 							if(result=="no"){
 				                document.getElementById('fileName').innerHTML ="该电站无图片";
 				               var IDp = document.getElementsByName("IDp"); IDp[0].value=-1;
 				              document.picimg.src="";
 				               
 							}
 							else{
 				                document.getElementById('fileName').innerHTML ="该电站图片为:"; 				                
 				               var IDp = document.getElementsByName("IDp"); IDp[0].value=obj[1];
 				              document.picimg.src="upload/"+result;
 				              $("#deletePic").show();
 							}
                         }
                     }
             		});
                document.getElementById('fileSize').innerHTML ="";                               
            }
            else{
            	$.messager.alert('提示','请先选择要编辑的电站!');
            }
        }
        function fileSelected() {
            var file = document.getElementById('fileToUpload').files[0];
            var fileName = file.name;
            var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
        
            if ((file_typename == '.jpg')||(file_typename == '.bmp')||(file_typename == '.png')) {//这里限定上传文件文件类型
                if (file) {              
                    var fileSize = 0;
                    if (file.size > 1024 * 1024)
                        fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
                    else
                        fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
                    
                	document.getElementById('fileName').innerHTML = '文件名: ' + file.name;    
                    document.getElementById('fileSize').innerHTML = '大小: ' + fileSize;               
                    $("#uploadFile").show();
                }

            }
            else {

                $("#uploadFile").hide();
                document.getElementById('fileName').innerHTML = "<span style='color:Red'>错误提示:上传文件应该是.jpg|.bmp|.png后缀而不应该是" + file_typename + ",请重新选择文件</span>"
                document.getElementById('fileSize').innerHTML ="";            

            }
        }
        
        function destroyPic(){
        	var IDp = document.getElementsByName("IDp"); 
        	var idp=IDp[0].value;
            if (IDp){
                $.messager.confirm('提示','确定要删除该图片吗?',function(r){
                    if (r){
	                    $.ajax({
	                    	 url:'deletePic.action?id='+idp,
							 type:'GET',
					         dataType:'json',
					         async:false,
				  	         success:function(){
				  	        	$('#dlgP').dialog('close'); 
				  	          }                 
	                    });
	                  }
           
                });
             }
        }     
        function savePs(){    
       		 //--------------------------以为获取新建的值并且去掉输入值两边的空格        
            var name = trim(document.getElementsByName("name")[0].value);       
 			var capacity = trim(document.getElementsByName("capacity")[0].value);
 			
 			var first_capacity = trim(document.getElementsByName("first_capacity")[0].value);
 			
 			var area = trim(document.getElementsByName("area")[0].value);
 			var part_num = trim(document.getElementsByName("part_num")[0].value);
 			var owner = trim(document.getElementsByName("owner")[0].value);
 			var investor = trim(document.getElementsByName("investor")[0].value);			
 		 	var province = trim(document.getElementsByName("province")[0].value);
 			var longitude = trim(document.getElementsByName("longitude")[0].value);
 			var latitude = trim(document.getElementsByName("latitude")[0].value);
 			var build_time = trim(document.getElementsByName("build_time")[0].value);
		//    var station_manage = trim(document.getElementsByName("station_manage")[0].value);
 		//	var user_manage = trim(document.getElementsByName("user_manage")[0].value);
 		//	var device_manage = trim(document.getElementsByName("device_manage")[0].value);  
 		//	var station_num = trim(document.getElementsByName("station_num")[0].value); 
 		//	var user_num = trim(document.getElementsByName("user_num")[0].value);      
 			
		    // 为了修改属性保留的原来的值	
		
		    var ex_name = document.getElementsByName("ex_name")[0].value;
		  /*              
 			var ex_ps_capacity = document.getElementsByName("ex_ps_capacity")[0].value;
 			var ex_ps_area = document.getElementsByName("ex_ps_area")[0].value;
 			var ex_ps_part_num = document.getElementsByName("ex_ps_part_num")[0].value;
 			var ex_ps_owner = document.getElementsByName("ex_ps_owner")[0].value;
 			var ex_ps_investor = document.getElementsByName("ex_ps_investor")[0].value;			
 		 	var ex_ps_province = document.getElementsByName("ex_ps_province")[0].value;
 			var ex_ps_longitude = document.getElementsByName("ex_ps_longitude")[0].value;
 			var ex_ps_latitude = document.getElementsByName("ex_ps_latitude")[0].value;
 			var ex_ps_build_time =document.getElementsByName("ex_ps_build_time")[0].value;	
          */     
		  	if(flag==0){//flag为0表示当前为新建电站
	   	 	  if(checkPsInformation(name,capacity,first_capacity,area,part_num,owner,investor,province,longitude,latitude,build_time)==true)
	   	 	  {	
	   	 	
	      		 $.ajax({
              
	                   url:'addPs.action?name='+encodeURI(encodeURI(name))+'&capacity='+capacity+
	        			'&area='+area+'&part_num='+part_num+'&owner='+encodeURI(encodeURI(owner))+
	       				'&investor='+encodeURI(encodeURI(investor))+'&province='+encodeURI(encodeURI(province))+
	       				'&longitude='+longitude+'&latitude='+latitude+
	       				'&build_time='+encodeURI(encodeURI(build_time))
	       				+'&first_capacity='+first_capacity,
	       			//	'&user_manage='+encodeURI(encodeURI(user_manage))
	       			//	+'&user_num='+user_num, 
	                   type:'GET',
			           dataType:'json',
			           async:false,
			           success:function(obj){
		                var result = obj[0];
		                    if (result.errorMsg){
		                        $.messager.show({
		                            title: 'Error',
		                            msg: result.errorMsg
		                        });
		                    } else {
		                        $('#dlg').dialog('close');        // close the dialog
		                        $('#psL').datagrid('reload');    // reload the ps data
		                    }
		                 $.messager.confirm('提示','新建电站成功！');
	                    }
	            	});
	          }	           		
		    }
		    if(flag==1){//flag为1表示当前为编辑电站 
		   	 if(checkUpdatePsInformation(name,ex_name,capacity,area,part_num,owner,investor,province,longitude,latitude,build_time)==true)
	   	     {
		    	var row = $('#psL').datagrid('getSelected');          
          		if (row){
          		  $.ajax({
			         url:'updatePs.action?id='+row.id+'&name='+encodeURI(encodeURI(name))+'&capacity='+capacity+
	        			'&area='+area+'&part_num='+part_num+'&owner='+encodeURI(encodeURI(owner))+
	       				'&investor='+encodeURI(encodeURI(investor))+'&province='+encodeURI(encodeURI(province))+
	       				'&longitude='+longitude+'&latitude='+latitude+'&build_time='+encodeURI(encodeURI(build_time)),
	       			//	'&user_manage='+encodeURI(encodeURI(user_manage))+
	       			//	'&user_num='+user_num, 
					dataType:'json',
					async:false,
				  	success:function(){
				  	 	$('#dlg').dialog('close');        // close the dialog
		                $('#psL').datagrid('reload'); 		// reload the ps data		
					  	}
				    });	
		    	}
          		$.messager.confirm('提示','编辑电站成功！');
		    	flag = 0;         			
				}
			}
		}
     
        function destroyPs(){
            var row = $('#psL').datagrid('getSelected');
            var id=row.id;
             var result="";
              	  
			$.ajax({
		        url:'CanDelete.action?id='+id,
				type:'GET',				
				dataType:'json',
				async:false,
			  	success:function(obj){
			  		 result = obj[0];			
			  	}
		    });	
			if(result == "cant"){$.messager.confirm('警告','此电站存在分期！请先删除分期！');return false;}
            
            if (row){
                $.messager.confirm('提示','删除电站后会将电站所有用户删除，确定要继续删除该电站吗?',function(r){
                    if (r){
	                    $.ajax({
	                    	 url:'deletePs.action?id='+row.id,
							 type:'GET',
					         dataType:'json',
					         async:false,
				  	         success:function(){
				  		         $('#psL').datagrid('reload'); 
				  	          }                 
	                    });
	                  }
           
                });
             }
        }
        
        
        function checkIsNaN(x)
        {
        	var y=document.getElementById(x).value;
        	 if( isNaN(y)==true)
		    {
		    	$.messager.alert('提示','装机容量请输入数字!');
		    }
        }
        
         function searchPsByName(value){
            var ps_name = value;
            if(ps_name!=""){
            	$.ajax({
	              		 url:'searchPsByName.action?ps_name='+encodeURI(encodeURI(ps_name)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){	                   
        					if(obj.total==0)    				
        					{
        						$.messager.confirm('提示','没有符合该条件的电站');
        						$('#psL').datagrid('reload'); 
     						//	$('#psL').datagrid('loadData', { total: 0, rows: [] });
        					}
        					else
        					{
        					$('#psL').datagrid('loadData',obj);
        					}
	                  	}
	            });
            }
            else
            {
            	 $('#psL').datagrid('reload');
            }
           $('#search_name').searchbox('clear');   ////  是否需要查询后清除查询电站名称
        }
        
        function searchPsByProvince(rec)
        {
        	var ps_province=rec.value;
        	if(ps_province!=""){
        		$.ajax({
        			url:'searchPsByProvince.action?ps_province='+encodeURI(encodeURI(ps_province)),
        			type:'GET',
        			dataType:'json',
        			async:false,
        			success:function(obj){
        				
        				if(obj.total==0)    				
        				{
        					$.messager.confirm('提示','没有符合该条件的电站');
        					$('#psL').datagrid('reload');
  					// 		$('#psL').datagrid('loadData', { total: 0, rows: [] });
        				}
        				else
        				{
        					$('#psL').datagrid('loadData',obj);
        				}
        			}       			
        		});
        	}
        }
        
        function searchPsByTime(date){
        	var date1 = $('#search_build_time1').datebox('getValue'); 
        	var date2 = $('#search_build_time2').datebox('getValue');
			if(date1=="")
				$.messager.confirm('提示','请先选择起始时间');
			else 
			{			
				 $.ajax({
	                    	 url:'searchPsByTime.action?date1='+date1+'&date2='+date2,
							 type:'GET',
					         dataType:'json',
					         async:false,
				  	         success:function(obj){
								if(obj.total==0)
								{
									$.messager.confirm('提示','没有符合该条件的电站');
									$('#psL').datagrid('reload');
								}
				  		        else{ 
				  		        	$('#psL').datagrid('loadData',obj); 
				  		        }
				  	          }                 
	                    });
			}
        }
        
         function searchPsByTime2(date){
        	var date1 = $('#search_build_time1').datebox('getValue'); 
        	var date2 = $('#search_build_time2').datebox('getValue');
			if(date2!="")
			{	
				 $.ajax({
	                    	 url:'searchPsByTime.action?date1='+date1+'&date2='+date2,
							 type:'GET',
					         dataType:'json',
					         async:false,
				  	         success:function(obj){
								if(obj.total==0)
								{
									$.messager.confirm('提示','没有符合该条件的电站');
									$('#psL').datagrid('reload');
								}
				  		        else{ 
				  		        	$('#psL').datagrid('loadData',obj); 
				  		        }
				  	          }                 
	                    });
			}
        }

   		function setStationNumByStationManage(rec)
   		{
   			var stationManage=rec.value;
   			if(stationManage=="否"){ $('#ps_station_num').combobox('setValue',null); $('#ps_station_num').combobox('readonly',true);	}
   			if(stationManage=="是"){ $('#ps_station_num').combobox('readonly',false);}
   		}
      	function setUserNumByUserManage(rec)
   		{
   			var userManage=rec.value;
   			if(userManage=="否"){ $('#ps_user_num').combobox('setValue',null); $('#ps_user_num').combobox('readonly',true);	}
   			if(userManage="是"){ $('#ps_user_num').combobox('readonly',false);}
   			$('#ps_user_num').combobox('readonly',true);
   		}
   		
        function checkPsInformation(name,capacity,first_capacity,area,part_num,owner,investor,province,longitude,latitude,build_time){	
	
        	
			if(name=="")
		    {
		    	$.messager.alert('提示','电站名称必须填写!');
		    	return false;
		    }
			if(capacity=="")
		    {
		    	$.messager.alert('提示','电站容量必须填写!');
		    	return false;
		    }
			if(first_capacity=="")
		    {
		    	$.messager.alert('提示','首期容量必须填写!');
		    	return false;
		    }
			if(longitude=="")
		    {
		    	$.messager.alert('提示','电站经度必须填写!');
		    	return false;
		    }
			if(latitude=="")
		    {
		    	$.messager.alert('提示','电站纬度必须填写!');
		    	return false;
		    }
		    
		    if( isNaN(capacity)==true)
		    {
		    	$.messager.alert('提示','电站容量请输入数字!');
		    	return false;
		    }
		    if( isNaN(first_capacity)==true)
		    {
		    	$.messager.alert('提示','首期容量请输入数字!');
		    	return false;
		    }
		    if(Number(capacity)<Number(first_capacity)){
		    	$.messager.alert('提示','首期容量不能超过电站容量!');
		    	return false;
		    }
		    if( isNaN(area)==true)
		    {
		    	$.messager.alert('提示','占地面积请输入数字!');
		    	return false;
		    }
		    if( isNaN(part_num)==true)
		    {
		    	$.messager.alert('提示','光伏板组件数量请输入数字!');
		    	return false;
		    }		    
		    if( isNaN(longitude)==true)
		    {
		    	$.messager.alert('提示','经度请输入数字!');
		    	return false;
		    }
		    if( isNaN(latitude)==true)
		    {
		    	$.messager.alert('提示','纬度请输入数字!');
		    	return false;
		    }
		    var result="";
			$.ajax({
		        url:'checkPsNameIsLegal.action?name='+encodeURI(encodeURI(name)),
				type:'GET',				
				dataType:'json',
				async:false,
			  	success:function(obj){
			  		 result = obj[0];			  		
			  	}
		    });	
			if(result == "wrong"){$.messager.confirm('警告','电站名已存在');return false;}
			if(result=="wrongDelete"){
                $.messager.confirm('提示','该电站名称曾经被注册过，如果想重新注册，则需修改电站名称，若继续使用该电站名称视为恢复被删除电站。您现在想恢复此电站所有数据吗?',function(r){
                    if (r){
	                    $.ajax({
	                    	 url:'recoverPS.action?name='+encodeURI(encodeURI(name)),
							 type:'GET',
					         dataType:'json',
					         async:false,
							  	success:function(){
							  	 	$('#dlg').dialog('close');        // close the dialog
					                $('#psL').datagrid('reload'); 		// reload the ps data		
								}                 
	                    });
	                  }
           
                });
				return false;}
			if(result=="correct"){return true;}
		
			return false;   //////////////////////////
		}       
        
        function checkUpdatePsInformation(name,ex_name,capacity,area,part_num,owner,investor,province,longitude,latitude,build_time){	
	
			if(name=="")
		    {
		    	$.messager.alert('提示','电站名称必须填写!');
		    	return false;
		    }
			if(capacity=="")
		    {
		    	$.messager.alert('提示','电站容量必须填写!');
		    	return false;
		    }
			if(longitude=="")
		    {
		    	$.messager.alert('提示','电站经度必须填写!');
		    	return false;
		    }
			if(latitude=="")
		    {
		    	$.messager.alert('提示','电站纬度必须填写!');
		    	return false;
		    }
		    if( isNaN(capacity)==true)
		    {
		    	$.messager.alert('提示','电站容量请输入数字!');
		    	return false;
		    }
		    if( isNaN(area)==true)
		    {
		    	$.messager.alert('提示','占地面积请输入数字!');
		    	return false;
		    }
		    if( isNaN(part_num)==true)
		    {
		    	$.messager.alert('提示','光伏板组件数量请输入数字!');
		    	return false;
		    }		    
		    if( isNaN(longitude)==true)
		    {
		    	$.messager.alert('提示','经度请输入数字!');
		    	return false;
		    }
		    if( isNaN(latitude)==true)
		    {
		    	$.messager.alert('提示','纬度请输入数字!');
		    	return false;
		    }else
		    {
		    	var result="";
		    	$.ajax({
		      	  	url:'checkCapacity.action?name='+encodeURI(encodeURI(name))+'&capacity='+capacity,
					type:'GET',				
					dataType:'json',
					async:false,
			  		success:function(obj){
			  		 	result = obj[0];			  		
			  		}
		    	});	
		    	if(result == "wrong"){$.messager.confirm('警告','电站容量小于分期容量之和！若要修改电站容量，请先修改分期容量！');return false;}
		    	if(result=="correct"){return true;} 
		    }
		    
		    if(ex_name==name)
		    {
		    	return true;
		    }
		    else {
		    	result="";
				$.ajax({
		      	  	url:'checkPsNameIsLegal.action?name='+encodeURI(encodeURI(name)),
					type:'GET',				
					dataType:'json',
					async:false,
			  		success:function(obj){
			  		 	result = obj[0];			  		
			  		}
		    	});	
				if(result == "wrong"){$.messager.confirm('警告','电站名已存在');return false;}
		    	if(result == "wrongDelete"){$.messager.confirm('警告','此电站名曾经注册过，请您换一个电站名称');return false;}
				if(result=="correct"){return true;} 
			}
		   		
			return false;   //////////////////////////
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
       
		function trim(str){//去掉两边空格   
   			 return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, ''); 
   			 
   		$(function(){

            $('#psL').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());
         //   $('#search_province').combobox('select','北京');           
       		 }); 
        }
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            
            width:80px;
        }
        .fitem input{
            width:160px;
        }




    </style>
  </body>
</html>
