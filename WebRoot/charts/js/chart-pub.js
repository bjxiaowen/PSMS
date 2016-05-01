function CurentTime()
    { 
        var now = new Date();
       
        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日
       
        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分
       
        var clock = year + "-";
       
        if(month < 10)
            clock += "0";
       
        clock += month + "-";
       
        if(day < 10)
            clock += "0";
           
        clock += day + " ";
       
        if(hh < 10)
            clock += "0";
           
        clock += hh + ":";
        if (mm < 10) clock += '0'; 
        clock += mm; 
        return(clock); 
    }
var currenTime = CurentTime();
$(function(){
    $(".current-time").append(currenTime);
    
  //需要判断是第几个菜单栏被点击给出的参数
	var name = $.parseJSON(v)['name'];
	$("#nav li").each(function(i,e){
		if($(e).data('cur') == name){
			$(e).addClass('current').siblings().removeClass('current');
		};
	})
})