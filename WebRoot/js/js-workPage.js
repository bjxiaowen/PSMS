$(document).ready(function() {//页面准备方法，更新系统时间
	var time=current();
	setInterval(function(){$("#top-center").html(current)},1000); 
});

function check()   
{   
    if (confirm("确认要退出登录吗？"))  
    {   
     return true;   
    }   
    else return false;   
} 

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

var mapObj;
//初始化地图对象，加载地图
function mapInit() {
    mapObj = new AMap.Map("content-left-down",{
        rotateEnable:true,
        dragEnable:true,
        zoomEnable:true,
        zooms:[3,18],
        //二维地图显示视口
        view: new AMap.View2D({
            center:new AMap.LngLat(116.397428,39.90923),//地图中心点
            zoom:13 //地图显示的缩放级别
        })
    }); 
}


