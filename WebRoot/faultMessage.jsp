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
    <base href="<%=basePath%>engineerArea.jsp">  
    <title>异常信息列表</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyui/demo/demo.css">
    <link rel="stylesheet" href="kindeditor/themes/default/default.css" />
		<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
		<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/datagrid-detailview.js"></script>
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script> 
    <script>
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="handleCondition"]', {
					allowFileManager : true,
					items:[
					        'undo', 'redo', '|', 'cut', 'copy', 'paste','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image'
					]
				});
			});
		</script>

    <style>
    	body{
    		border:0px;padding:5px;
    	}
    	
    </style>
</head>
<body>
   <p style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">人员管控->异常信息列表</p>
    <table id="dg" title="异常信息" class="easyui-datagrid" style="width:100%;height:95%;text-align:center" 
            url="getAllFaultMessage.action"        
            toolbar="#toolbar" pagination="true" pageSize=20 pageList="[ 20, 30, 40 ]" //读取分页条数，即向后台读取数据时传过去的值
            autoRowHeight="true" striped="true" rownumbers="true" fitColumns="true"
             singleSelect="true" sortName="psId" sortOrder="asc" remoteSort="false">
        <thead>
            <tr> 
                <th field="faultMessageId" hidden="hidden" width="20">主键id</th> 
                <th field="initialDiagnose" hidden="hidden" width="20">初步诊断报告</th>
                <th field="areaId" hidden="hidden" width="20">区域id</th>
                <th field="psId" hidden="hidden" width="20">电站id</th>
                <th field="userId" hidden="hidden" width="20">人员id</th>
                <th field="predictTime" hidden="hidden" width="20">预计完成日期</th>
                <th field="alertCause" hidden="hidden" width="20">故障原因</th>
                <th field="handleCondition" width="20">处理状况</th>
                <th field="maintainDate" hidden="hidden" width="20">维护日期</th>
                <th field="checkDate" hidden="hidden" width="20">检验日期</th>
                <th field="checkPerson" hidden="hidden" width="20">检验人</th>
                <th field="checkText" width="20">检查批语</th>
                <th field="psName" width="20" align="center" sortable="true">电站名称</th>
                <th field="areaName" width="20" align="center" sortable="true">区域名称</th>
                <th field="equipmentId" width="20" align="center" hidden="hidden" data-options="formatter:eIdChangeToString" sortable="true">设备ID</th>
                <th field="equipmentStatus" formatter="managerstr" width="20" align="center" sortable="true">设备状态</th>
                <th field="alertTime" width="20" align="center" sortable="true">报警时间</th>
                <th field="userName" width="20" align="center" sortable="true">维护工程师</th>
                <th field="status" width="20" align="center" data-options="formatter:statusChange" sortable="true">状态</th>
                <th data-options="field:'aa',width:20,formatter:go,align:'center'">操作</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
     <select class="easyui-combobox" name="search-station" style="width:160px;" data-options=" panelHeight:'auto'" >
        	<option value="">根据电站查询</option>       
        	<c:forEach items="${list_ps}" var="ps">											
				<option value="${ps.id}">${ps.name}</option>
			</c:forEach>
        </select>
       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"  onclick="queryInverterByPS()">查找</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:630px;height:450px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">查看详情</div>
        <form id="fm" method="post" novalidate  style="height:">
        	<div class="fitem">
                <label style="width:60px;">电站名称</label>
                <input id="psName" name="psName" class="easyui-textbox" readonly/> &nbsp; &nbsp; &nbsp; &nbsp;
                <label style="width:60px;">所属区域</label>
                <input id="areaName" name="areaName" class="easyui-textbox" readonly/>
            </div>
            <div class="fitem">
            	<label style="width:60px;">报警时间</label>
                <input id="alertTime" name="alertTime" class="easyui-textbox" readonly/> &nbsp; &nbsp; &nbsp; &nbsp;
                <label style="width:60px;">预计完成</label>
                <span id="currDateStatus">
                <input id="currDate" class="easyui-datebox" name="predictTime" class="easyui-datetimebox">
                </span>
                <span id="predictDateStatus" style="display:none;">
                <input id="predictTime" name="predictTime" class="easyui-textbox" readonly/>
                </span>
            </div>
            <div class="fitem">
            <label style="width:120px;margin:10px 10px 10px 0px;">初步诊断报告(必填)</label>
                <textarea cols="30" rows="5" name="initialDiagnose" id="initialDiagnose" onpropertychange="if(this.value.length>100){this.value=this.value.substr(0,100)}" class="easyui-validatebox" style="width: 99%; height: 50px;"></textarea>
            </div>
            <div class="fitem alertCauseContent">
            <label style="width:120px;margin:10px 10px 10px 0px;">故障原因(必填)</label>
                <textarea cols="30" rows="5" name="alertCause" id="alertCause" onpropertychange="if(this.value.length>100){this.value=this.value.substr(0,100)}" class="easyui-validatebox" style="width: 99%; height: 50px;"></textarea>
            </div>
            <div class="fitem handleConditionContent">
            <label style="width:120px;margin:10px 10px 10px 0px;">处理情况(必填)</label>
            	<textarea id="handleCondition" name="handleCondition" style="width:100%;height:200px;visibility:hidden;">KindEditor</textarea>
            </div>
            <div class="fitem checkTextContent">
            <label style="width:120px;margin:10px 10px 10px 0px;">检验评语(必填)</label>
                <textarea cols="30" rows="5" name="checkText" id="checkText" onpropertychange="if(this.value.length>100){this.value=this.value.substr(0,100)}" class="easyui-validatebox" style="width: 99%; height: 50px;"></textarea>
            </div>
            <div class="fitem nameContent" >
            	<label style="width:60px;">维修人</label>
                <input id="userName" name="userName" class="easyui-textbox" readonly/> &nbsp; &nbsp; &nbsp; &nbsp;
                <label style="width:60px;">检验人</label>
                <input id="checkPerson" name="checkPerson" class="easyui-textbox" readonly/>
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveLink()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        var flag = 0;
        var now = new Date();
		var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
		var roleId = "<%=session.getAttribute("Role_id")%>";
		var roleId_t = 5;
		
		function queryInverterByPS(){
            var ps_id = document.getElementsByName("search-station")[0].value;
            var url = 'getFaultMessageByPsId.action?psId='+encodeURI(encodeURI(ps_id));
            if(ps_id!=""){
            	$.ajax({
	                url:url,
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
		                    if(obj.total==0)    				
	        				{
	        					$.messager.confirm('提示','没有符合该条件的电站');
	     			//			$('#dg').datagrid('loadData', { total: 0, rows: [] });
	        				}
	        				else
	        				{	$('#dg').datagrid('loadData',obj);
	        				}
	                    }
	            });
            } 
             else
             {	$('#dg').datagrid('reload');
             }
          $('#search-station').searchbox('clear'); 
        }
		
		
		
		 function managerstr(value, row, index) {
			 if(value == 0){
				 return "故障"
			 }
         }
		 
		 function eIdChangeToString(v,r,i){
			 var aa = v+"";
			 return aa
		 }
		 
		function statusChange(value,row,index){
			if(value == 0 ||value ==""){
				 return "待处理"
			 }else if(value == 1){
				 return "跟进"
			 }
			 else if(value == 2){
				 return "等待确认"
			 }
			 else if(value == 3){
				 return "完成"
			 }
		}
		function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        }
		
         
        function  go(val,row,index){
        		return '<a href="javascript:void(0)" style="text-decoration:none;" onclick="constructionManager('+index+')">查看</a>';  
        }
        
        function constructionManager(index){
        	$('#dg').datagrid('selectRow',index);
            var row = $('#dg').datagrid('getSelected');  
            row.equipmentId = row.equipmentId+"";
            if (row){  
	    		if(row.status=="0" || row.status==""){
	    	                $('#dlg').dialog('open').dialog('setTitle','巡检记录');
	    	                $('#fm').form('load',row);
	    	    			$('#currDateStatus').show();
	    	    			$('#predictDateStatus').hide();
	    	    			$('.alertCauseContent').hide();
	    	    			$('.handleConditionContent').hide();
	    	    			$('.checkTextContent').hide();
	    	    			$('.nameContent').hide();
	    	    			$(".c6").show();
	    	    			$('#initialDiagnose').attr("readonly",false);
	    	                $("#currDate").datebox("setValue",myformatter(d1));
	    	                $('#currDate').datebox('calendar').calendar({
	    	      			    validator: function(date){
	    	      			        return d1<=date;
	    	      			    }
	    	      			});
	    		}else if(row.status=="1"){
	    			$('#dlg').dialog('open').dialog('setTitle','巡检记录');
	    			$('#fm').form('load',row);
	    			editor.html('');
	    			$('#currDateStatus').hide();
	    			$('#predictDateStatus').show();
	    			$('.alertCauseContent').show();
	    			$('.handleConditionContent').show();
	    			$('.checkTextContent').hide();
	    			$('.nameContent').hide();
	    			$('#initialDiagnose').attr("readonly",true);
	    			$('#alertCause').attr("readonly",false);
	    			editor.readonly(false);
	    		}else if(row.status=="2"){
	    			$('#dlg').dialog('open').dialog('setTitle','巡检记录');
	    			$('#fm').form('load',row);
	    			$('#currDateStatus').hide();
	    			$('#predictDateStatus').show();
	    			$('.alertCauseContent').show();
	    			$('.handleConditionContent').show();
	    			$('#initialDiagnose').attr("readonly",true);
	    			$('.nameContent').show();
	    			editor.readonly();
	    			$('#alertCause').attr("readonly",true);
	    			$('#handleCondition').attr("readonly",true);
	    			$('#checkText').attr("readonly",false);
	    			$('.checkTextContent').show();
	    			if( roleId_t != 5){
	    				$('.nameContent').hide();
	    				$(".c6").hide();
	    				$('.checkTextContent').hide();
	    			}
	    		}else if(row.status=="3"){
	    			$('#dlg').dialog('open').dialog('setTitle','巡检记录');
	    			$('#fm').form('load',row);
	    			$('#currDateStatus').hide();
	    			$('#predictDateStatus').show();
	    			$('.alertCauseContent').show();
	    			$('.handleConditionContent').show();
	    			$('.nameContent').show();
	    			$('#initialDiagnose').attr("readonly",true);
	    			$('#alertCause').attr("readonly",true);
	    			editor.html(row.handleCondition);
	    			$('#checkText').attr("readonly",true);
	    			editor.readonly();
	    			$('.checkTextContent').show();
	    			$(".c6").hide();
	    		}
    		}
        }		
        
        function doSearch(value){
            var name = value;
            if(name!=""){
            	$.ajax({
	                url:'searchByAreaName.action?areaName='+encodeURI(encodeURI(name)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
				             if(obj.total==0)    				
	        				{
	        					$.messager.confirm('提示','没有符合该条件的区域');
	     			//			$('#dg').datagrid('loadData', { total: 0, rows: [] });
	        				}
	        				else
	        				{	$('#dg').datagrid('loadData',obj);
	        				}
	                   }
	            });
            }
           else
            {	$('#dg').datagrid('reload');
            }
           $('#search_name').searchbox('clear'); 
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
        });
        

        function saveLink(){          
        	var row = $('#dg').datagrid('getSelected');
	   	    var initialDiagnose = trim(document.getElementsByName("initialDiagnose")[0].value);
	   	    var alertCause = trim(document.getElementsByName("alertCause")[0].value);
	   	 	editor.sync();
	   	    var handleCondition = document.getElementById('handleCondition').value;
	   	    var checkText = trim(document.getElementsByName("checkText")[0].value);
	   	 	var predictTime = $('#currDate').datebox('getValue'); 
	   	    var faultMessageId = row.faultMessageId;
		   	 var areaId = row.areaId;
	         var psId = row.psId;
	         var equipmentId = row.equipmentId;
	         var equipmentStatus = row.equipmentStatus;
	         var userId = row.userId;
	         var alertTime = row.alertTime;
	         var status = Number(row.status)+1;
	         var maintainDate = row.maintainDate;
	         var checkPerson = row.checkPerson;
	         var checkDate = myformatter(d1);
	         var url = 'updateFaultMessage.action?faultMessageId='+encodeURI(encodeURI(faultMessageId))+'&areaId='+encodeURI(encodeURI(areaId))+'&psId='+encodeURI(encodeURI(psId))+'&equipmentId='+encodeURI(encodeURI(equipmentId))+'&equipmentStatus='+encodeURI(encodeURI(equipmentStatus))+'&userId='+encodeURI(encodeURI(userId))+'&alertTime='+encodeURI(encodeURI(alertTime))+'&status='+encodeURI(encodeURI(status))+'&initialDiagnose='+encodeURI(encodeURI(initialDiagnose))+'&predictTime='+encodeURI(encodeURI(predictTime))+'&alertCause='+encodeURI(encodeURI(alertCause))+'&handleCondition='+encodeURI(encodeURI(handleCondition))+'&maintainDate='+encodeURI(encodeURI(maintainDate))+'&checkPerson='+encodeURI(encodeURI(checkPerson))+'&checkDate='+encodeURI(encodeURI(checkDate))+'&checkText='+encodeURI(encodeURI(checkText));
		    	
	   	 if(row.status=="0"||row.status==""){//flag为0表示添加初步诊断报告
			    if(!checkLinkInformation(initialDiagnose)){return false; }//校验信息
			    else{
			    	$.ajax({
	                	 url:url,
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
		                        $('#dg').datagrid('reload');    // reload the user data
		                    }
		                    $.messager.confirm('提示','初步诊断报告完成！'); 
	                    }
	            });
	            }
	          }
	   	 
          if(row.status=="1"){//1表示添加故障原因与处理情况
        	  
		    	if(!checkUpdateLinkInformation(handleCondition,alertCause)){return false;}//校验信息
		    	else{
					$.messager.confirm('确认','保存后不能修改，是否继续？',function(r){
						if (r){
							$.ajax({
						        url:url,
						        type:'GET',
								dataType:'json',
								async:false,
							  	success:function(){
							  	 	$('#dlg').dialog('close');        // close the dialog
					                $('#dg').datagrid('reload'); 
								  	}
							    });	
						}
				    	$.messager.confirm('提示','处理报告完成！');
					});
		    	}
		    } 
          if(row.status=="2"){//2表示检验人添加评价报告
        	  if(!checkUpdateLinkInformation_0(checkText)){return false;}//校验信息
		    	else{
		    	$.ajax({
			        url:url,
			        type:'GET',
					dataType:'json',
					async:false,
				  	success:function(){
				  	 	$('#dlg').dialog('close');        // close the dialog
		                $('#dg').datagrid('reload'); 
					  	}
				    });	
		    	}
		    	$.messager.confirm('提示','评价报告已完成！');
		    } 
        }
        function checkLinkInformation(initialDiagnose){
			if(initialDiagnose==""){$.messager.confirm('警告','初步诊断报告不能为空');return false;}
			return true;
		}
		function trim(str){//去掉两边空格   
   			 return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');   
        }
        
        function checkUpdateLinkInformation(handleCondition,alertCause){
			if(handleCondition==""){$.messager.confirm('警告','处理情况不能为空');return false;}
			if((alertCause=="")){$.messager.confirm('警告','故障原因不能为空');return false;}
			return true;
		}
        function checkUpdateLinkInformation_0(checkText){
			if(checkText==""){$.messager.confirm('警告','处理情况不能为空');return false;}
		    return true;
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
