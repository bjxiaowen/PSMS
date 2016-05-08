var now = new Date();   
var year = now.getFullYear();       //年
var month = now.getMonth() + 1;     //月
var day = now.getDate();            //日   
var hh = now.getHours();            //时
var mm = now.getMinutes();          //分
var v,data_json,psId;
//公共变量
data_json = $.parseJSON(v);
psId = data_json["psId"];
var pageName = data_json["pageName"];
var sideBarUrlArr = ['toBiIndex','toBiModule','toBattery','toBiControl','toBiInverter','toControlAndInverter'];
function sideBarUrl(){
	$("#nav li").each(function(i,v){
		$(this).find("a").attr('href',sideBarUrlArr[i]+'.action?psId='+psId); 
		if($(this).data("cur") == pageName){
			$(this).addClass("current").siblings().removeClass("current");
		}
	});
}
function CurentTime()
    { 
        
       
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
    sideBarUrl();
})