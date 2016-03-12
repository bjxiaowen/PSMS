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
    <base href="<%=basePath%>connectionBook.jsp">
    <title>connectionBook</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyui/demo/demo.css">
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/datagrid-detailview.js"></script>
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">  
    </script>
    <style>
    	body{
    		border:0px;padding:5px;
    	}
    	
    </style>
</head>
<body>
    <p style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">用户管理->客户通讯录</p>  
    <table id="dg" title="用户通讯录" class="easyui-datagrid" style="width:100%;height:95%;text-align:center" 
            url="getConnectionBookInformation.action"        
             toolbar="#toolbar" pagination="true" pageSize=20 pageList="[ 20, 30, 40 ]" //读取分页条数，即向后台读取数据时传过去的值
            autoRowHeight="true" striped="true" rownumbers="true" fitColumns="true" singleSelect="true"
            striped="true" sortName="id"  sortOrder="asc" remoteSort="flase" >
        <thead>
            <tr>   
                <th field="id" hidden="true" align="center" width="20" >公司名称</th>           
                <th field="company_name" align="center" width="20" sortable="true">公司名称</th>
                <th field="telephone" align="center" width="20">联系电话</th>
                <th field="email" align="center" width="20">邮箱</th>
                <th field="fax" align="center" width="20">传真</th>
                <th field="QQ" align="center" width="20">QQ号</th>
                <th field="home_page" align="center" width="20">主页网址</th>
                <th field="addr" align="center" width="20">公司地址</th>
                <th field="more" align="center" width="20">备注</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newConnectionBook()">新建客户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editConnectionBook()">编辑客户</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyConnectionBook()">删除客户</a>
         
         <input id="search_name" name="search_name" class="easyui-searchbox" data-options="prompt:'请输入公司名称查找',searcher:doSearch" style="width:200px"></input>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search"  onclick="queryCBByPS_name()">查找客户</a>
    
    <div id="dlg" class="easyui-dialog" style="width:400px;height:400px;padding:10px 20px"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">客户信息</div>
        <form id="fm" method="post" novalidate>
           <div class="fitem">
                <label>公司名称</label>
                <input id="company_name" name="company_name" class="easyui-textbox">(必填)
                <input id="ex_company_name" name="ex_company_name" type="hidden" ></input> 
            </div>
             <div class="fitem">
                <label>联系电话</label>
                <input id="telephone" name="telephone" class="easyui-textbox" ></input>
                <input id="ex_telephone" name="ex_telephone" type="hidden" ></input>  
            </div>
            
            
            <div class="fitem">
                <label>邮箱</label>
                <input id="email" name="email" class="easyui-textbox" validType="email">
                <input id="ex_email" name="ex_email" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>传真</label>
                <input id="fax" name="fax" class="easyui-textbox" >
                <input id="ex_fax" name="ex_fax" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>QQ号</label>
                <input id="QQ" name="QQ" class="easyui-textbox" >
                <input id="ex_QQ" name="ex_QQ" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>主页网址</label>
                <input id="home_page" name="home_page" class="easyui-textbox" >
                <input id="ex_home_page" name="ex_home_page" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>公司地址</label>
                <input id="addr" name="addr" class="easyui-textbox" >
                <input id="ex_addr" name="ex_addr" type="hidden" ></input> 
            </div>
            <div class="fitem">
                <label>备注</label>
                <input id="more" name="more" class="easyui-textbox" >
                <input id="ex_more" name="ex_more" type="hidden" ></input> 
            </div>
        
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveConnectionBook()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>
    <script type="text/javascript">
        var url;
        var flag = 0;
        function newConnectionBook(){
        	var flag = 0;
            $('#dlg').dialog('open').dialog('setTitle','新建客户');
            $('#fm').form('clear');
           
        }
        
        
       
            function queryCBByPS_name(){       
           		var company_name = document.getElementsByName("search_name")[0].value;
           		if(company_name!=""){
                	$.ajax({
                		url:'queryConnectionBookByName.action?company_name='+encodeURI(encodeURI(company_name)),
	                     type:'GET',
    			             dataType:'json',
    			             async:false,
    			             success:function(obj){
    		                     if(obj.total==0)    				
    	        				{
    	        					$.messager.confirm('提示','没有符合该条件的公司');
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
                    
          function doSearch(value){
            var company_name = value;
            if(company_name!=""){
            	$.ajax({
	                url:'queryConnectionBookByName.action?company_name='+encodeURI(encodeURI(company_name)),
	                     type:'GET',
			             dataType:'json',
			             async:false,
			             success:function(obj){
		                    if(obj.total==0)    				
        					{
        						$.messager.confirm('提示','没有符合该条件的公司');
        						$('#dg').datagrid('reload'); 
        					}
        					else
        					{
        					$('#dg').datagrid('loadData',obj);
        					}
	                    }
	            });
            }
            else
            {
            	 $('#dg').datagrid('reload');
            }
           $('#search_name').searchbox('clear');   ////  是否需要查询后清除查询公司名称名称  
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
        
        
     
        function editConnectionBook(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                flag = 1;
                $('#dlg').dialog('open').dialog('setTitle','编辑客户信息');
                $('#fm').form('load',row); 
                var ex_company_name = document.getElementsByName("ex_company_name");
                ex_company_name[0].value= row.company_name;
                 var ex_telephone = document.getElementsByName("ex_telephone");
                ex_telephone[0].value= row.telephone;
                var ex_email = document.getElementsByName("ex_email");
                ex_email[0].value= row.email;
                var ex_fax = document.getElementsByName("ex_fax");
                ex_fax[0].value= row.fax;
                var ex_QQ = document.getElementsByName("ex_QQ");
                ex_QQ[0].value= row.QQ;
                var ex_home_page = document.getElementsByName("ex_home_page");
                ex_home_page[0].value= row.home_page;
                var ex_addr = document.getElementsByName("ex_addr");
                ex_addr[0].value= row.addr;
                var ex_more = document.getElementsByName("ex_more");
                ex_more[0].value= row.more;
            }
        }
        function saveConnectionBook(){  
           var row = $('#dg').datagrid('getSelected');
            var company_name = trim(document.getElementsByName("company_name")[0].value);
            var telephone = trim(document.getElementsByName("telephone")[0].value);
	   	    var email = trim(document.getElementsByName("email")[0].value);
	   	    var fax = trim(document.getElementsByName("fax")[0].value);
	        var QQ = trim(document.getElementsByName("QQ")[0].value);
	        var home_page = trim(document.getElementsByName("home_page")[0].value);
	        var addr = trim(document.getElementsByName("addr")[0].value);
	        var more = document.getElementsByName("more")[0].value; 
		    //--------------------------以上为获取新建的值并且去掉输入值两边的空格	
		     var ex_company_name = document.getElementsByName("ex_company_name")[0].value; 
		     var ex_telephone = document.getElementsByName("ex_telephone")[0].value;              
             var ex_email = document.getElementsByName("ex_email")[0].value;              
             var ex_fax = document.getElementsByName("ex_fax")[0].value;             
             var ex_QQ = document.getElementsByName("ex_QQ")[0].value;               
             var ex_home_page = document.getElementsByName("ex_home_page")[0].value;              
             var ex_addr = document.getElementsByName("ex_addr")[0].value;              
             var ex_more = document.getElementsByName("ex_more")[0].value;   
		   	 if(flag==0){//flag为0表示当前为新建用户
		    	 if(!checkConnectionBookInformation(company_name,telephone,fax,QQ))return false;//校验用户信息
		    	  else{
           		 $.ajax({
	              url:'addConnectionBook.action?id=null&company_name='+encodeURI(encodeURI(company_name))+'&telephone='+encodeURI(encodeURI(telephone))+
			        '&email='+encodeURI(encodeURI(email))+'&fax='+encodeURI(encodeURI(fax))+'&QQ='+encodeURI(encodeURI(QQ))+
			        '&home_page='+encodeURI(encodeURI(home_page))+'&addr='+encodeURI(encodeURI(addr))+
			        '&more='+encodeURI(encodeURI(more)), 
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
		                    $.messager.confirm('提示','新建客户信息成功！');
	                    }
	            	});
	         }   
		    }
	  if(flag==1){//flag为1表示当前为编辑用户
		    	 if(!checkConnectionBookInformation(company_name,telephone,fax,QQ))return false;//校验用户信息
		    	else{
		    	$.ajax({
			        url:'editConnectionBook.action?id='+encodeURI(encodeURI(row.id))+'&company_name='+encodeURI(encodeURI(company_name))+'&telephone='+encodeURI(encodeURI(telephone))+
					        '&email='+encodeURI(encodeURI(email))+'&fax='+encodeURI(encodeURI(fax))+'&QQ='+encodeURI(encodeURI(QQ))+
					        '&home_page='+encodeURI(encodeURI(home_page))+'&addr='+encodeURI(encodeURI(addr))+
					        '&more='+encodeURI(encodeURI(more)), 
					type:'GET',
					dataType:'json',
					async:false,
				  	success:function(){
				  	 	$('#dlg').dialog('close');        // close the dialog
		                $('#dg').datagrid('reload'); 
	  		
					  	}
				    });	
		    	}
		    	 $.messager.confirm('提示','编辑用户成功！');
		    	flag = 0;
		    }          
        }
	          
        function destroyConnectionBook(){
        	//alert("qqqqqqqqqqqqqqq")
            var row = $('#dg').datagrid('getSelected');
        	//alert("qqqqq4444444444qq")
            if (row){
                $.messager.confirm('提示','确定要删除该客户吗?',function(r){
                    if (r){
                    	
	                    $.ajax({
	                    	 url:'deleteConnectionBook.action?id='+row.id,
	                    			
							 type:'GET',
					         dataType:'json',
					         async:false,
				  	         success:function(){
				  		         $('#dg').datagrid('reload'); 
				  	          }                 
	                    });
	                    //alert("q2222222222224qq")
	                  }
           
                });
             }
        }
       
        function checkConnectionBookInformation(company_name,telephone,fax,QQ){
	
			if(company_name=="")
			
			{$.messager.confirm('提示','公司名称不能为空！');return false;}
		  //  return true;
		    
		     if(isNaN(telephone)==true)
		    {
		    	$.messager.alert('提示','联系电话请输入数字!');
		    	return false;
		    }
		    if(isNaN(fax)==true)
		    {
		    	$.messager.alert('提示','传真请输入数字!');
		    	return false;
		    }
		    if(isNaN(QQ)==true)
		    {
		    	$.messager.alert('提示','QQ号请输入数字!');
		    	return false;
		    }
		    return true;
		}
		/* function checkemail(email)
		{
				if(email==""){return true}
				else
				{
					apos=value.indexOf("@")
					dotpos=value.lastIndexOf(".")
				if (apos<1||dotpos-apos<2) 
  				{alert(alerttxt);return false}
				else {return true}
				}
			} */
		
		
		 
		function trim(str){//去掉两边空格   
   			 return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');   
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
