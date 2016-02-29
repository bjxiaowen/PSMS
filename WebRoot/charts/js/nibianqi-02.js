$(function(){
	var canvas = document.getElementById('canvas');            
    var stage = new JTopo.Stage(canvas);
    
    var scene = new JTopo.Scene(stage);
    
 // 控制器
    var guangfu = new JTopo.Node("控制器");
    guangfu.alpha = 0.9;
    guangfu.setImage('./charts/assets/img/demo/kongzhiqi.jpg');
    guangfu.setBound(50, 200, 100, 100);
    guangfu.font = 'bold 20px 微软雅黑';
    guangfu.fontColor = "255,255,255";
    guangfu.textOffsetY = 8;
    guangfu.showSelected = false; // 不显示选中矩形
    scene.add(guangfu); 
    // 逆变器
    var dcac = new JTopo.Node("DC/AC");
    dcac.alpha = 1;
    dcac.setImage('./charts/assets/img/demo/nibianqi.png');
    dcac.setBound(320, 160, 200, 200);
    dcac.font = 'bold 20px 微软雅黑';
    dcac.fontColor = "255,255,255";
    dcac.layout = {type: 'circle', radius: 160};
    dcac.textOffsetY = 8;
    dcac.showSelected = false; // 不显示选中矩形
    scene.add(dcac); 

    // ac
    var ac = new JTopo.Node("AC");
    ac.alpha = 0.9;
    ac.setImage('./charts/assets/img/demo/ac.png');
    ac.setBound(650, 210, 100, 100);
    ac.font = 'bold 20px 微软雅黑';
    ac.fontColor = "255,255,255";
    ac.textOffsetY = 8;
    ac.showSelected = false; // 不显示选中矩形
    scene.add(ac); 
    
    // 连线一
    var l1 = new JTopo.AnimateNode('./charts/assets/img/demo/gifgif.png', 1, 5, 1000, 0);
    l1.setSize(120, 12);
    l1.setLocation(180, 260);                                
    l1.repeatPlay = true;            
    l1.play();
    scene.add(l1); 
    
    
    
    // 连线三
    var l3 = new JTopo.AnimateNode('./charts/assets/img/demo/gifgif.png', 1, 5, 1000, 0);
    l3.setSize(120, 12);
    l3.setLocation(510, 260);                                
    l3.repeatPlay = true;            
    l3.play();
    scene.add(l3); 

    
    

    
    
    function nodeBlock(x, y,n){
    	 var obj = {};
    	 var node = new JTopo.Node();
    	 node.text = ""// 文字
         node.textPosition = 'Middle_Left';// 文字居中
    	 node.textOffsetY = 8; // 文字向下偏移8个像素
    	 node.font = '14px 微软雅黑'; // 字体
    	 node.setLocation(x, y); // 位置
    	 node.setSize(180, 80);  // 尺寸
    	 node.borderRadius = 5; // 圆角
    	 node.borderWidth = 2; // 边框的宽度
    	 node.borderColor = '255,255,255'; //边框颜色            
    	 node.alpha = 0.7; //透明度
     scene.add(node);
         obj.node = node;
         for(var i =0; i<n;i++){
        	 var textName = "textNode"+i;
        	 textName = new JTopo.TextNode("");
        	 textName.font = 'bold 16px 微软雅黑';
        	 textName.setLocation(x+10, y+25*i+5);
             scene.add(textName);
             obj["textNode"+i] = textName;
         }

         return obj;
         
    }   
   
    var nodeB1 = nodeBlock(40,10,3);
    nodeB1.textNode0.text = '电流：'+ data_json["newes"]["x_Coutpout_Voltage"]+" V";
    nodeB1.textNode1.text = '电压：'+ data_json["newes"]["x_Coutpout_Current"]+" A";
    nodeB1.textNode2.text = '功率：'+ data_json["newes"]["x_Coutpout_Power"]+" KW";
    
    var nodeB2 = nodeBlock(330,10,2);
    nodeB2.textNode0.text = '内部温度：'+ data_json["newes"]["x_Inerin_tem"]+" °C";
    nodeB2.textNode1.text = data_json["newes"]["machineState "]?"放电":"充电";
    
    var nodeB3 = nodeBlock(620,10,3);
    nodeB3.textNode0.text = '电流：'+ data_json["newes"]["outputVoltage"]+" V";
    nodeB3.textNode1.text = '电压：'+ data_json["newes"]["outputCurrent"]+" A";
    nodeB3.textNode2.text = '功率：'+ data_json["newes"]["exchangeOutPower"]+" KW";
    nodeB3.node.setSize(150,80);
    
    
    
   

})