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
//var sideBarUrlArr = ['toBiIndex','toBiModule','toBattery','toBiControl','toBiInverter','toControlAndInverter'];
var sideBarUrlArr = [{
	name:"控逆一体机",
	url:'toControlAndInverter',
	icon:'icon-folder-open-alt'
},{
	name:"控制器",
	url:'toBiControl',
	icon:'icon-dashboard'
},{
	name:"逆变器",
	url:'toBiInverter',
	icon:'icon-adjust'
},{
	name:"蓄电池",
	url:'toBattery',
	icon:'icon-retweet'
},{
	name:"组件",
	url:'toBiModule',
	icon:'icon-cog'
},{
	name:"首页",
	url:'toBiIndex',
	icon:'icon-home'
}]
function sideBarUrl(){
	$("#nav li").each(function(i,v){
		for (var ii=0;ii<6;ii++)
		{
			if($(this).data("cur") == sideBarUrlArr[ii].name){
				console.log(sideBarUrlArr[ii].name);
				$(this).find("a").attr('href',sideBarUrlArr[ii].url+'.action?psId='+psId); 
				$(this).find("i").addClass(sideBarUrlArr[ii].icon);
			}
		}
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
//    $(".current-time").append(currenTime);
    sideBarUrl();
})