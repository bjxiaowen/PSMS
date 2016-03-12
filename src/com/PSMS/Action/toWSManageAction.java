/** * * 
* 文件名称: toWSManageAction.java *
* 
* 气象站信息管理，增删改查气象站信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-1 下午2:22:33 *
* * @author jie.yang 
*/
package com.PSMS.Action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Adapter.inverter_parameter;
import com.PSMS.Adapter.monitor_dataAdapter;
import com.PSMS.Adapter.ws_parameter;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Hibernate.WS_parameter;
import com.PSMS.Hibernate.WeatherStation;
import com.PSMS.Service.EquipmentService;
import com.PSMS.Service.EquipmentServiceImpl;
import com.PSMS.Service.HistoryOfDayServiceImpl;
import com.PSMS.Service.HistoryOfYearServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.WS_parameterService;
import com.PSMS.Service.WS_parameterServiceImpl;
import com.PSMS.Service.WeatherStationService;
import com.PSMS.Service.WeatherStationServiceImpl;

public class toWSManageAction {
	/** 
	* 气象站设备信息管理，加载页面,将需要显示的设备信息通过json传回前台,删除选中的气象站信息,新建气象站,校验气象站 是否已存在,根据气象站名查询信息,根据所属电站查询气象站信息,根据设备类型和品牌显示对应的设备型号
	* @author jie.yang 
	* @date 2014-12-1
	* @param wsService 
	* @param ps_informationService 
	* @param equipmentService 		
	*/ 
	private WS_parameterService wsService;
	private PS_informationService ps_informationService;
	private EquipmentService equipmentService;
	public String toWSManage() {
		/** 
		*加载页面*
		* @author jie.yang 
		* @date 2014-12-1
	    * @param list_station_name
	    * @param list_type 
		* @param type 
		* @param list_brand  	
		*/ 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		equipmentService = new EquipmentServiceImpl();
		
		List<String> list_station_name = ps_informationService.getAllStationName();//显示所有电站名称
		List<String> list_type = equipmentService.getAllTypeName();//显示所有设备类型
		String type="气象站";//设备类型默认为气象站		
		List<String> list_brand = equipmentService.getAllBrandName(type); //根据设备类型查询设备品牌	
		request.setAttribute("list_station_name", list_station_name);//返回给前台显示
		request.setAttribute("list_type", list_type);//返回给前台显示		
		request.setAttribute("list_brand", list_brand);//返回给前台显示   
		return "success";
	}

	public String getWSInformation() {
		/** 
		*将需要显示的设备信息通过json传回前台*
		* @author jie.yang 
		* @date 2014-12-1
	    * @param list_all_junction
	    * @param list_ps_name 
		* @param jb_parameter_list 	
		*/ 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		wsService = new WS_parameterServiceImpl();
		List<WS_parameter> list_all_ws = wsService.getAllWS();// 得到所有逆变器信息
		List<String> list_ps_name = new ArrayList<String>();// 这个list保存查到的name
		List<ws_parameter> ws_parameter_list = new ArrayList<ws_parameter>();
		for (int i = 0; i < list_all_ws.size(); i++) {
			int ps_id = list_all_ws.get(i).getPS_id();// 获取每个逆变器对应的所属电站id
			String ps_name = ps_informationService.getStationNameById(ps_id);// 获取电站id对应的name
			list_ps_name.add(ps_name);
		}
		for (int i = 0; i < list_all_ws.size(); i++) {
			ws_parameter u = new ws_parameter();
			u.setId(list_all_ws.get(i).getId());
			u.setType(list_all_ws.get(i).getType());
			u.setModel(list_all_ws.get(i).getModel());
			u.setName(list_all_ws.get(i).getName());
			u.setBrand(list_all_ws.get(i).getBrand());
			u.setPs_name(list_ps_name.get(i));
			u.setPurchase_time(list_all_ws.get(i).getPurchase_time());
			u.setMax_temperature(list_all_ws.get(i).getMax_temperature());
			ws_parameter_list.add(u);
		}

		ArrayList list = new ArrayList();
		list.add(ws_parameter_list);//通过json将校验结果传回到前台显示
		JSONObject object = JSONObject.fromObject("{}");
		object.put("total", ws_parameter_list.size());
		object.put("rows", ws_parameter_list);
		try {
			ServletActionContext.getResponse().setContentType(
					"application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter()
					.write(object.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public String checkWSNameIsLegal() {
		/** 
		*校验气象站是否已存在*
		* @author jie.yang 
		* @date 2014-12-1
	    * @param name
	    * @param ps_name 
		* @param result  
		* @param ps_id	
		*/ 
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String ps_name = request.getParameter("ps_name");
		try {
			name = java.net.URLDecoder.decode(name, "UTF-8");
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");// 以上为获取前台数据，转码
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wsService = new WS_parameterServiceImpl();
		ps_informationService = new PS_informationServiceImpl();
		String result = "";//用result存放提示信息，并将其传回前台

		int ps_id = ps_informationService.getPS_idByName(ps_name);//根据电站名称获得对应的电站id
		if (!(wsService.checkWSNameExistById(name, ps_id)))//校验汇流箱 在该电站中是否已存在
			result = "correct";
		else
			result = "wrong";	// 电站内部逆变器不重名时返回correct
		ArrayList list = new ArrayList();
		list.add(result);//通过json将校验结果传回到前台显示
		JSONArray obj = JSONArray.fromObject(list);
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String addWS()
	{
		/** 
		*新建气象站*
		* @author jie.yang 
		* @date 2014-12-1
	    * @param id
	    * @param ps_name 
		* @param name  
		* @param type
	    * @param brand 
		* @param model 
		* @param purchase_time   
		* @param max_temperature
		* @param ps_id  
		* @param w_parameter
		* @param result	
		*/ 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = request.getParameter("id");
		String ps_name = request.getParameter("ps_name");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String purchase_time = request.getParameter("purchase_time");
		String max_temperature = request.getParameter("max_temperature");

		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");
			name = java.net.URLDecoder.decode(name, "UTF-8");
			type = java.net.URLDecoder.decode(type, "UTF-8");
			brand = java.net.URLDecoder.decode(brand, "UTF-8");
			model = java.net.URLDecoder.decode(model, "UTF-8");
			purchase_time = java.net.URLDecoder.decode(purchase_time, "UTF-8");
			// -以上为获取前台数据，转码
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ps_informationService = new PS_informationServiceImpl();
		wsService = new WS_parameterServiceImpl();
		// 以上为初始化
		WS_parameter w_parameter = new WS_parameter();
		int ps_id = ps_informationService.getPS_idByName(ps_name);
		if (id != null) {
			w_parameter.setId(Integer.parseInt(id));
		}
		w_parameter.setType(type);
		w_parameter.setModel(model);
		w_parameter.setName(name);
		w_parameter.setBrand(brand);
		w_parameter.setPS_id(ps_id);
		w_parameter.setPurchase_time(purchase_time);// 将数据存入u类中
		if(max_temperature==""){
			w_parameter.setMax_temperature(null);
		}else {	w_parameter.setMax_temperature(max_temperature);}		
		wsService.addWS(w_parameter);
		ArrayList list = new ArrayList();
		String result = "气象站保存成功！";//用result存放提示信息，并将其传回前台
		list.add(result);//通过json将校验结果传回到前台显示
		JSONArray obj = JSONArray.fromObject(list);
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String deleteWS() {
		/** 
		* 删除选中的气象站信息 *
		* @author jie.yang 
		* @date 2014-12-1
	    * @param ws_id
	    * @param id 	
		*/ 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ws_id = request.getParameter("id");
		int id = Integer.parseInt(ws_id);
		wsService = new WS_parameterServiceImpl();
		wsService.deleteWSById(id);// 根据id删除该逆变器
		return null;
	}
	
	public String queryWSByName(){
		/** 
		*根据气象站名查询信息*
		* @author jie.yang 
		* @date 2014-12-1
	    * @param ws_name
	    * @param list_ws
	    * @param list_ps_name 
		* @param w_list  
		* @param ps_id	 
		* @param ps_name
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ws_name = request.getParameter("name");
		try {
			ws_name = java.net.URLDecoder.decode(ws_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wsService = new WS_parameterServiceImpl();
		ps_informationService = new PS_informationServiceImpl();//初始化
		List<WS_parameter> list_ws = wsService.getWSByIts_name(ws_name);//得到所有逆变器信息
		List<String> list_ps_name= new ArrayList<String>();//这个list保存查到的name 
		List<ws_parameter> w_list = new ArrayList<ws_parameter>();
		for(int i=0;i<list_ws.size();i++){
			int ps_id = list_ws.get(i).getPS_id();
			String ps_name="";
			ps_name = ps_informationService.getStationNameById(ps_id);//获取电站id对应的name
			list_ps_name.add(ps_name);
		}
		for (int i = 0; i < list_ws.size(); i++) {
			ws_parameter w = new ws_parameter();
			w.setId(list_ws.get(i).getId());
			w.setType(list_ws.get(i).getType());
			w.setModel(list_ws.get(i).getModel());
			w.setName(list_ws.get(i).getName());
			w.setBrand(list_ws.get(i).getBrand());
			w.setPs_name(list_ps_name.get(i));
			w.setPurchase_time(list_ws.get(i).getPurchase_time());
			w.setMax_temperature(list_ws.get(i).getMax_temperature());
			w_list.add(w);
		}
		JSONObject obj=JSONObject.fromObject("{}");		
		obj.put("total", w_list.size());
		obj.put("rows", w_list);
	
		try {
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(obj.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String queryWSByPS_name(){
		/** 
		*根据所属电站查询气象站信息*
		* @author jie.yang 
		* @date 2014-12-1
	    * @param ps_name
	    * @param WS_name 
		* @param ps_id  
		* @param list_ws	 
		* @param list_ps_name
		* @param w_list	 
		* @param ps_id1
		* @param ps_name1         
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ps_name = request.getParameter("ps_name");
		String WS_name = request.getParameter("name");
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");
			WS_name = java.net.URLDecoder.decode(WS_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wsService = new WS_parameterServiceImpl();
		ps_informationService = new PS_informationServiceImpl();//初始化
	
		int ps_id =1;
		List<WS_parameter> list_ws = new ArrayList<WS_parameter>();
		if(ps_name!=""&&WS_name!=""){//根据电站名称和气象站名查询信息
			 ps_id = ps_informationService.getPS_idByName(ps_name);//根据电站名称获取电站id
			 list_ws = wsService.getWSByPsandname(ps_id,WS_name);			 
		}
		else{
			if(ps_name!=""){//根据电站名称查询信息
				ps_id = ps_informationService.getPS_idByName(ps_name);//根据电站名称获取电站id
				list_ws = wsService.getWSByPs_name(ps_id);				
			}
			else{ //根据气象站名查询信息
				list_ws = wsService.getWSByIts_name(WS_name);//得到所有气象站信息						
			}
		}	
		List<String> list_ps_name= new ArrayList<String>();//这个list保存查到的name 
		List<ws_parameter> w_list = new ArrayList<ws_parameter>();
		
		for(int i=0;i<list_ws.size();i++){
			int ps_id1 = list_ws.get(i).getPS_id();
			String ps_name1="";
			ps_name1 = ps_informationService.getStationNameById(ps_id1);//获取电站id对应的name
			list_ps_name.add(ps_name1);
		}
		for (int i = 0; i < list_ws.size(); i++) {
			ws_parameter u = new ws_parameter();
			u.setId(list_ws.get(i).getId());
			u.setType(list_ws.get(i).getType());
			u.setModel(list_ws.get(i).getModel());
			u.setName(list_ws.get(i).getName());
			u.setBrand(list_ws.get(i).getBrand());
			u.setPs_name(list_ps_name.get(i));
			u.setPurchase_time(list_ws.get(i).getPurchase_time());
			u.setMax_temperature(list_ws.get(i).getMax_temperature());
			w_list.add(u);
		}
		JSONObject obj=JSONObject.fromObject("{}");		
		obj.put("total", w_list.size());
		obj.put("rows", w_list);
		
		try {
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(obj.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String  setModelByTypeAndBrandW()
	{	
		/** 
		*根据设备类型和品牌显示对应的设备型号*
		* @author jie.yang 
		* @date 2014-12-1
	    * @param brand
	    * @param type 
		* @param list_model          
		*/ 

		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String brand = request.getParameter("brand");
		try {
			brand = java.net.URLDecoder.decode(brand, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("brand"+brand);		
		equipmentService = new EquipmentServiceImpl();
		String type="气象站";//设备类型默认为气象站
		List<String> list_model = equipmentService.getAllModelName(type,brand); //根据设备类型与设备品牌查询设备型号
	  for(int i=0;i<list_model.size();i++)
	  {
		  System.out.println(list_model.get(i));
	  }

		ArrayList list = new ArrayList();		
		list.add(list_model);//通过json将校验结果传回到前台显示	
		JSONArray obj = JSONArray.fromObject(list);
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getWSRealTimeData(){
		/** 
		*获取气象站数据*
		* @author jie.yang 
		* @date 2014-12-1
	    * @param ps_id
	    * @param period 
		* @param wsParameterService  
		* @param wsService
	    * @param l_ws 
		* @param l_data 
		* @param p_id   
		* @param m1
		* @param m2  
		* @param m3
		* @param m4	
		* @param m5
		* @param m6  
		* @param m7	
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ps_id = Integer.parseInt(request.getParameter("ps_id"));
		int period = Integer.parseInt(request.getParameter("period"));
		WS_parameterService wsParameterService = new WS_parameterServiceImpl();
		WeatherStationService wsService = new WeatherStationServiceImpl();
		List<WS_parameter> l_ws = wsParameterService.getWSByPsIdAndPeriod(ps_id,period);
		List<monitor_dataAdapter> l_data = new ArrayList<monitor_dataAdapter>();
		monitor_dataAdapter  m1 = new monitor_dataAdapter();
		monitor_dataAdapter  m2 = new monitor_dataAdapter();
		monitor_dataAdapter  m3 = new monitor_dataAdapter();
		monitor_dataAdapter  m4 = new monitor_dataAdapter();
		monitor_dataAdapter  m5 = new monitor_dataAdapter();
		monitor_dataAdapter  m6 = new monitor_dataAdapter();
		monitor_dataAdapter  m7 = new monitor_dataAdapter();
		if(l_ws.size()>0){
			int p_id = l_ws.get(0).getId();
			WeatherStation ws = wsService.getRealTimeWSDataByParameterID(p_id);
			m1.setName("光伏组件温度(℃)");m1.setValue(ws.getPvTemperature()+"");m1.setGroup("实时气象数据"); 
			l_data.add(m1);
			m2.setName("当前辐照度(W/m²)");m2.setValue(ws.getIrraditationValue()+"");m2.setGroup("实时气象数据");
			l_data.add(m2);
			m3.setName("当前环境温度(℃)");m3.setValue(ws.getTemperature()+"");m3.setGroup("实时气象数据");
			l_data.add(m3);
			m4.setName("风速(m/s)");m4.setValue(ws.getWindSpeed()+"");m4.setGroup("实时气象数据");
			l_data.add(m4);
			m7.setName("时间");m7.setValue(ws.getTime()+"");m7.setGroup("实时气象数据");
			l_data.add(m7);
			m5.setName("风向");m5.setValue(ws.getWindDirection()+"");m5.setGroup("实时气象数据");
			l_data.add(m5);
			m6.setName("湿度");m6.setValue(ws.getHumidity()+"");m6.setGroup("实时气象数据");
			l_data.add(m6);
			
		}
		else{
			m1.setName("光伏组件温度(℃)");m1.setValue("未知");m1.setGroup("暂无气象数据"); 
			l_data.add(m1);
			m2.setName("当前辐照度(W/m²)");m2.setValue("未知");m2.setGroup("暂无气象数据");
			l_data.add(m2);
			m3.setName("当前环境温度(℃)");m3.setValue("未知");m3.setGroup("暂无气象数据");
			l_data.add(m3);
			m4.setName("风速(m/s)");m4.setValue("未知");m4.setGroup("暂无气象数据");
			l_data.add(m4);
			m7.setName("时间");m7.setValue("未知");m7.setGroup("暂无气象数据");
			l_data.add(m7);
			m5.setName("风向");m5.setValue("未知");m5.setGroup("暂无气象数据");
			l_data.add(m5);
			m6.setName("湿度");m6.setValue("未知");m6.setGroup("暂无气象数据");
			l_data.add(m6);
			
		}
		
		
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", l_data.size());
		object.put("rows", l_data);
		try {
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
