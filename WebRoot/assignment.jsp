
<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<!--<![endif]-->
<head>
<title>人员分配</title>

<!-- Meta -->
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />

<!-- Bootstrap -->
<link href="HD/bootstrap/css/bootstrap.min.css" rel="stylesheet" />

<!-- Bootstrap Extended -->
<link
	href="HD/bootstrap/extend/jasny-bootstrap/css/jasny-bootstrap.min.css"
	rel="stylesheet" />
<link
	href="HD/bootstrap/extend/jasny-bootstrap/css/jasny-bootstrap-responsive.min.css"
	rel="stylesheet" />
<link
	href="HD/bootstrap/extend/bootstrap-wysihtml5/css/bootstrap-wysihtml5-0.0.2.css"
	rel="stylesheet" />
<!-- JQueryUI v1.9.2 -->
<link rel="stylesheet"
	href="HD/theme/scripts/jquery-ui-1.9.2.custom/css/smoothness/jquery-ui-1.9.2.custom.min.css" />

<!-- Glyphicons -->
<link rel="stylesheet" href="HD/theme/css/glyphicons.css" />

<!-- Bootstrap Extended -->
<link rel="stylesheet"
	href="HD/bootstrap/extend/bootstrap-select/bootstrap-select.css" />
<link rel="stylesheet"
	href="HD/bootstrap/extend/bootstrap-toggle-buttons/static/stylesheets/bootstrap-toggle-buttons.css" />

<!-- Uniform -->
<link rel="stylesheet" media="screen"
	href="HD/theme/scripts/pixelmatrix-uniform/css/uniform.default.css" />

<!-- JQuery v1.8.2 -->
<script src="HD/theme/scripts/jquery-1.8.2.min.js"></script>

<!-- Modernizr -->
<script src="HD/theme/scripts/modernizr.custom.76094.js"></script>

<!-- MiniColors -->
<link rel="stylesheet" media="screen"
	href="HD/theme/scripts/jquery-miniColors/jquery.miniColors.css" />

<!-- Theme -->
<link rel="stylesheet" href="HD/theme/css/style.min.css?1361377752" />

<!-- LESS 2 CSS -->
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/datagrid-detailview.js"></script>
<script src="HD/theme/scripts/less-1.3.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<%
		String id = session.getAttribute("id").toString();
		String username = session.getAttribute("User_name").toString();
	%>

	<div class="navbar main">

		<div class="container">
			<div class="row">
				<div class="span12 relativeWrap">

					<button type="button" class="btn btn-navbar visible-phone">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>

					<ul id="menu" class="hidden-phone">
						<li class="active"><a href="javascript:void(0)"
							class="menuToggle" style="font-size: 30px;color:#fff;">个人信息修改</a>
						</li>
					</ul>

					<span class="profile">
						<span>
							<strong id="username"></strong>						
							<a href="javascript:void(0)" onclick="toDesktop()" class="icon-desktop" style="color:#fff"></a>
						</span>						
					</span>

				</div>
			</div>
		</div>
	</div>

	<!-- Start Content -->
	<div class="container-fluid fixed">


		<div id="content">
			<ul class="breadcrumb">
				<li><a href="javascript:void(0)" class="glyphicons home"><i></i>
						个人信息修改</a></li>
				<li class="divider"></li>
				<li>个人账户设置</li>
			</ul>
			<div class="separator"></div>

			<!-- <h3 class="glyphicons user"><i></i> My Account</h3> -->

			<div
				class="widget widget-2 widget-tabs widget-tabs-2 tabs-right border-bottom-none">
				<div class="widget-head">
					<ul>
						<li class="active"><a class="glyphicons settings"
							href="#account-settings" data-toggle="tab"><i></i>个人账户设置</a></li>
					</ul>
				</div>
			</div>

			<form class="form-horizontal" id="form">
				<div class="tab-content" style="padding: 0;">

					<div class="tab-pane active" id="account-settings">
						<div class="row-fluid">
							<div class="span3">
								<strong>修改密码</strong>
								<p class="muted">如果您需要重置密码，请在下方输入您要修改的信息</p>
							</div>
							<div class="span9">
								<label for="inputUsername">用户名</label> <input type="text"
									id="inputUsername" class="span10" value="" name="inputUsername"
									disabled="disabled" />
								<div class="separator"></div>

								<label for="inputPasswordOld">旧密码</label> <input type="password"
									id="inputPasswordOld" class="span10" value=""
									name="inputPasswordOld" placeholder="若不需要修改则保持空" /><input
									id="ex_password" name="ex_password" type="hidden"></input>
								<div class="separator"></div>

								<label for="inputPasswordNew">新密码</label> <input type="password"
									id="inputPasswordNew" class="span12" value=""
									name="inputPasswordNew" placeholder="若不需要修改则保持空" />
								<div class="separator"></div>

								<label for="inputPasswordNew2">确认新密码</label> <input
									type="password" id="inputPasswordNew2" class="span12" value=""
									name="inputPasswordNew2" placeholder="若不需要修改则保持空" />
								<div class="separator"></div>
							</div>
						</div>
						<hr class="separator line" />
						<div class="row-fluid">
							<div class="span3">
								<strong>修改联系方式</strong>
								<p class="muted">请在下方修改您的最新联系方式。</p>
							</div>
							<div class="span9">
								<div class="row-fluid">
									<div class="span6">
										<label for="inputName">姓名</label>
										<div class="input-prepend">
											<span class="add-on glyphicons phone"><i></i></span> <input
												type="text" id="inputName" class="input-large"
												name="inputName" placeholder="abcde" />
										</div>
										<div class="separator"></div>
										<label for="inputPhone">电话</label>
										<div class="input-prepend">
											<span class="add-on glyphicons phone"><i></i></span> <input
												type="text" id="inputPhone" class="input-large"
												name="inputPhone" placeholder="01234567897" />
										</div>
										<div class="separator"></div>

										<label for="inputEmail">E-mail</label>
										<div class="input-prepend">
											<span class="add-on glyphicons envelope"><i></i></span> <input
												type="text" id="inputEmail" class="input-large"
												name="inputEmail" placeholder="contact@mosaicpro.biz"
												validType="email" />
										</div>
										<div class="separator"></div>
									</div>
									<div class="span6">
										<label for="inputPS_name">所属电站</label>
										<div class="input-prepend">
											<span class="add-on glyphicons link"><i></i></span> <input
												type="text" id="inputPS_name" class="input-large"
												name="inputPS_name" placeholder="" disabled="disabled" />
										</div>
										<div class="separator"></div>


										<label for="inputRole_name">角色名称</label>
										<div class="input-prepend">
											<span class="add-on glyphicons twitter"><i></i></span> <input
												type="text" id="inputRole_name" class="input-large"
												name="inputRole_name" placeholder="/mosaicpro"
												disabled="disabled" />
										</div>
										<div class="separator"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-actions" style="margin: 0; padding-right: 0;">
							<button type="submit" onclick="saveChanges()"
								class="btn btn-icon btn-primary glyphicons circle_ok pull-right">
								<i></i>保存修改
							</button>
						</div>

					</div>
				</div>
			</form>
			<br /> <br /> <a style="width:100%;text-decoration:none;text-align:center;" href="javascript:void(0)">版权所有 ZONERGY &copy; 2015</a>

		</div>

	</div>

	<script type=text/javascript>
	var user_name ='<%=username%>';
	
	$(document).ready(function(){
	
			if(username=="null"){
				window.location.href="<%=basePath%>"+"Login.jsp";
			}
		  	else $("#username").html(user_name);
		
	 	$.ajax({
          		 url:'queryUserByName.action?user_name='+encodeURI(encodeURI(user_name)),
                 type:'GET',
	             dataType:'json',
	             async:false,
	             success:function(obj){	              	
	            	 if(obj.total)
	            	 $("#inputUsername")[0].value = obj.rows[0].user_name;
	            	 $("#ex_password")[0].value = obj.rows[0].password;
	            	 $("#inputName")[0].value = obj.rows[0].name;
	            	 $("#inputPhone")[0].value = obj.rows[0].telephone;
	            	 $("#inputEmail")[0].value = obj.rows[0].email;
	            	 $("#inputPS_name")[0].value = obj.rows[0].ps_name;
	            	 $("#inputRole_name")[0].value = obj.rows[0].role_name;
		  	 	}
        });
		
	});
 	function toDesktop(){
 	
      	$.ajax({
       				 url:'toDesktop.action?uName='+user_name,
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
	           				location.href = 'psResearchDesk.jsp';
	           			}
	           			else {
	           				location.href = 'Login.jsp';
	           			}
           			}          
    			});	
    } 

function saveChanges(){
	 var id ='<%=id%>';
		
			var user_name = trim(document.getElementsByName("inputUsername")[0].value)
			var oldpassword = trim(document
					.getElementsByName("inputPasswordOld")[0].value);
			var ex_password = trim(document.getElementsByName("ex_password")[0].value);
			var password = trim(document.getElementsByName("inputPasswordNew")[0].value);
			var repassword = trim(document
					.getElementsByName("inputPasswordNew2")[0].value);
			var name = trim(document.getElementsByName("inputName")[0].value);
			var telephone = trim(document.getElementsByName("inputPhone")[0].value);
			var email = trim(document.getElementsByName("inputEmail")[0].value);
			var ps_name = trim(document.getElementsByName("inputPS_name")[0].value);
			var role_name = trim(document.getElementsByName("inputRole_name")[0].value);

			if (isNaN(telephone)) {
				alert('电话请输入数字!');
				return false;
			}
			if (!checkUpdateUserInformation(oldpassword, password, repassword,
					ex_password)) {
				return false;
			}

			else {
				if ((password == "") && (repassword == "")) {
					password = document.getElementsByName("ex_password")[0].value;
				}

				$.ajax({
					url : 'addUser.action?id=' + encodeURI(encodeURI(id))
							+ '&user_name=' + encodeURI(encodeURI(user_name))
							+ '&name=' + encodeURI(encodeURI(name))
							+ '&password=' + encodeURI(encodeURI(password))
							+ '&telephone=' + encodeURI(encodeURI(telephone))
							+ '&email=' + encodeURI(encodeURI(email))
							+ '&role_name=' + encodeURI(encodeURI(role_name))
							+ '&ps_name=' + encodeURI(encodeURI(ps_name)),

					type : 'GET',
					dataType : 'json',
					async : false,
					success : function(obj) {
						alert(obj);

					}
				});

			}

		}
		function checkUpdateUserInformation(oldpassword, password, repassword,
				ex_password) {
			if ((password == "") && (repassword == "")) {
				return true;
			} else {
				if ((oldpassword != "") || (password != "")
						|| (repassword != "")) {
					if (oldpassword == "") {
						alert('旧密码不能为空!');
						return false;
					} else {
						if (oldpassword != ex_password) {
							alert('旧密码输入错误！');
							return false;
						} else if ((password == "") || (repassword == "")) {
							alert('新密码或确认密码不能为空!');
							return false;
						} else if (password != repassword) {
							alert('新密码与确认密码不一致!');
							return false;
						}
					}
				}
			}
			//判断密码是否被修改，若被修改则需保持新旧密码不为空，且新密码与确认密码一致，若没有修改，则不作为

			return true;

		}

		function trim(str) {//去掉两边空格   
			return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');
		}
	</script>
</html>