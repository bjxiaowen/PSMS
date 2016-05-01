$(function(){
	//需要判断是第几个菜单栏被点击给出的参数
	var name = $.parseJSON(v)['name'];
	$("#nav li").each(function(i,e){
		if($(e).data('cur') == name){
			$(e).addClass('current').siblings().removeClass('current');
		};
	})
})