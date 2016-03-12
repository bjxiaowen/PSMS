var s = false;//标记用户是否执行onclick方法，若执行，则设置为true
var n = false;
function openNavigation1(obj,id){
	$("#f1").css("background-color","#fff");//更改导航栏字体和背景颜色
	$("#f1").css("color","#35abd9");
	var objDiv = $("#"+id+"");
    $(objDiv).css("display","block");
    $(objDiv).css("left", "0");
    $(objDiv).css("top", "169px");//设置提示框绝对坐标
    s = true;
    n = true;

}
function dispNavigation1(obj,id){
	$("#f1").css("background-color","#fff");
	$("#f1").css("color","#35abd9");
	var objDiv = $("#"+id+"");

    $(objDiv).css("display","block");

    $(objDiv).css("left", "0");

    $(objDiv).css("top", "169px");  

}
function closeNavigation1(obj,id){
	if(s){}
	else{var objDiv = $("#"+id+"");
	$(objDiv).css("display", "none");
	$("#f1").css("background-color","#35abd9");
	$("#f1").css("color","#fff");
	s = false;
	}
	
}
function closeNavigation11(obj,id){
	var objDiv = $("#"+id+"");
	$(objDiv).css("display", "none");
	$("#f1").css("background-color","#35abd9");
	$("#f1").css("color","#fff");
	s = false;
}

function opeanHtml(t){
	$("#content").find("iframe").attr("src","toUserManage.action");
}

