/** * * 
* 文件名称: toJunctionManageAction.java *
* 
* 汇流箱信息管理，增删改查汇流箱信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-16 下午1:52:33 *
* * @author liu.yang 
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
import org.hibernate.criterion.Junction;



import com.PSMS.Adapter.jb_parameter;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Hibernate.JB_parameter;
import com.PSMS.Service.EquipmentService;
import com.PSMS.Service.EquipmentServiceImpl;

import com.PSMS.Service.JB_parameterService;
import com.PSMS.Service.JB_parameterServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;

public class toJunctionManageAction {
	/** 
	* 汇流箱设备信息管理，加载页面,将需要显示的设备信息通过json传回前台,删除选中的汇流箱信息,新建汇流箱,校验汇流箱 是否已存在,根据汇流箱名查询信息,根据所属电站查询汇流箱信息,根据设备类型和品牌显示对应的设备型号
	* @author liu.yang 
	* @date 2014-12-16
	* @param jbService 
	* @param ps_informationService 
	* @param equipmentService 		
	*/ 
	private JB_parameterService jbService;
	private PS_informationService ps_informationService;
	private EquipmentService equipmentService;
	
	public String toJunctionManage(){
		/** 
		*加载页面*
		* @author liu.yang 
		* @date 2014-12-16
	    * @param list_station_name
	    * @param list_type 
		* @param type 
		* @param list_brand  	
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
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
		String type="汇流箱";//设备类型默认为汇流箱
		List<String> list_brand = equipmentService.getAllBrandName(type); //根据设备类型查询设备品牌
		request.setAttribute("list_station_name", list_station_name);//返回给前台显示
		request.setAttribute("list_type", list_type);//返回给前台显示		
		request.setAttribute("list_brand", list_brand);//返回给前台显示
		return "success";		
	}
	
	public String getJunctionInformation(){
		/** 
		*将需要显示的设备信息通过json传回前台*
		* @author liu.yang 
		* @date 2014-12-16
	    * @param list_all_junction
	    * @param list_ps_name 
		* @param jb_parameter_list 	
		*/ 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		ps_informationService = new PS_informationServiceImpl();
		jbService = new JB_parameterServiceImpl();
		List<JB_parameter> list_all_junction = jbService.getAllJunction();// 得到所有汇流箱信息
		List<String> list_ps_name = new ArrayList<String>();// 这个list保存查到的name
		List<jb_parameter> jb_parameter_list = new ArrayList<jb_parameter>();
		for (int i = 0; i < list_all_junction.size(); i++) {
			int ps_id = list_all_junction.get(i).getPS_id();// 获取每个汇流箱对应的所属电站id			
			String	ps_name = ps_informationService.getStationNameById(ps_id);// 获取电站id对应的name
			list_ps_name.add(ps_name);
		}
		for (int i = 0; i < list_all_junction.size(); i++) {
			jb_parameter t = new jb_parameter();
			t.setId(list_all_junction.get(i).getId());
			t.setType(list_all_junction.get(i).getType());
			t.setModel(list_all_junction.get(i).getModel());
			t.setName(list_all_junction.get(i).getName());
			t.setBrand(list_all_junction.get(i).getBrand());
			t.setPs_name(list_ps_name.get(i));
			t.setPurchase_time(list_all_junction.get(i).getPurchase_time());
			t.setMax_dc_voltage(list_all_junction.get(i).getMax_dc_voltage());
			t.setRoad_num(list_all_junction.get(i).getRoad_num());			
			jb_parameter_list.add(t);
		}				
		ArrayList list = new ArrayList();
		list.add(jb_parameter_list);//通过json将校验结果传回到前台显示		
		JSONObject object = JSONObject.fromObject("{}");
		object.put("total", jb_parameter_list.size());
		object.put("rows", jb_parameter_list);     
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
			
	public String deleteJunction() {
		/** 
		* 删除选中的汇流箱信息 *
		* @author liu.yang 
		* @date 2014-12-16
	    * @param junction_id
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
		String junction_id = request.getParameter("id");
		int id =Integer.parseInt(junction_id);
		jbService = new JB_parameterServiceImpl();
		jbService.deleteJunctionById(id);// 根据id删除该汇流箱
		return null;
	}
	
	
	
	public String addJunction()
	{
		/** 
		*新建汇流箱*
		* @author liu.yang 
		* @date 2014-12-16
	    * @param id
	    * @param ps_name 
		* @param name  
		* @param type
	    * @param brand 
		* @param model 
		* @param purchase_time   
		* @param max_dc_voltage
	    * @param road_num 
		* @param ps_id  
		* @param i_parameter
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
		String max_dc_voltage = request.getParameter("max_dc_voltage");
		String road_num = request.getParameter("road_num");				
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");
			name = java.net.URLDecoder.decode(name, "UTF-8");
			type = java.net.URLDecoder.decode(type, "UTF-8");
			brand = java.net.URLDecoder.decode(brand, "UTF-8");
			model = java.net.URLDecoder.decode(model, "UTF-8");
			purchase_time = java.net.URLDecoder.decode(purchase_time, "UTF-8");								
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		jbService = new JB_parameterServiceImpl();		// 初始化				
		JB_parameter i_parameter = new JB_parameter();
		int ps_id = ps_informationService.getPS_idByName(ps_name);//根据电站名称获得对应的电站id
		if(id!=null){
			i_parameter.setId(Integer.parseInt(id));
		}		
		i_parameter.setType(type);
		i_parameter.setModel(model);
		i_parameter.setName(name);
		i_parameter.setBrand(brand);
		i_parameter.setPS_id(ps_id);
		i_parameter.setPurchase_time(purchase_time);// 将数据存入u类中
		i_parameter.setMax_dc_voltage(max_dc_voltage);
		i_parameter.setRoad_num(road_num);
		if(max_dc_voltage==""){//判断最大直流输入电压若没有填写则默认为空
			i_parameter.setMax_dc_voltage(null);
		}else {	i_parameter.setMax_dc_voltage(max_dc_voltage);}
		if(road_num==""){//判断光伏组件输入路数若没有填写则默认为空
			i_parameter.setRoad_num(null);
		}else {	i_parameter.setRoad_num(road_num);}
		jbService.addJunction(i_parameter);
		
		ArrayList list = new ArrayList();
		String result = "汇流箱保存成功！";//用result存放提示信息，并将其传回前台
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
		
	public String checkJunctionNameIsLegal() {
		/** 
		*校验汇流箱 是否已存在*
		* @author liu.yang 
		* @date 2014-12-16
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
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jbService = new JB_parameterServiceImpl();
		ps_informationService = new PS_informationServiceImpl();
		String result = "";//用result存放提示信息，并将其传回前台
			int ps_id = ps_informationService.getPS_idByName(ps_name);//根据电站名称获得对应的电站id
			if (!(jbService.checkJunctionNameExistById(name, ps_id)))//校验汇流箱 在该电站中是否已存在
				result = "correct";
			else
				result = "wrong";		// 电站内部汇流箱不重名时返回correct
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
		
	public String queryJunctionByName(){
		/** 
		*根据汇流箱名查询信息*
		* @author liu.yang 
		* @date 2014-12-16
	    * @param junction_name
	    * @param list_junction
	    * @param list_ps_name 
		* @param i_list  
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
		String junction_name = request.getParameter("name");
		try {
			junction_name = java.net.URLDecoder.decode(junction_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jbService = new JB_parameterServiceImpl();
		ps_informationService = new PS_informationServiceImpl();//初始化
		List<JB_parameter> list_junction = (List<JB_parameter>) jbService.getJunctionByIts_name(junction_name);//得到所有汇流箱信息
		List<String> list_ps_name= new ArrayList<String>();//这个list保存查到的name 
		List<jb_parameter> i_list = new ArrayList<jb_parameter>();
		for(int i=0;i<list_junction.size();i++){
			int ps_id = list_junction.get(i).getPS_id();
			String ps_name="";
			ps_name = ps_informationService.getStationNameById(ps_id);//获取电站id对应的name
			list_ps_name.add(ps_name);
		}
		for (int i = 0; i < list_junction.size(); i++) {			
			jb_parameter t = new jb_parameter();
			t.setId(list_junction.get(i).getId());
			t.setType(list_junction.get(i).getType());
			t.setModel(list_junction.get(i).getModel());
			t.setName(list_junction.get(i).getName());
			t.setBrand(list_junction.get(i).getBrand());
			t.setPs_name(list_ps_name.get(i));
			t.setPurchase_time(list_junction.get(i).getPurchase_time());
			t.setMax_dc_voltage(list_junction.get(i).getMax_dc_voltage());
			t.setRoad_num(list_junction.get(i).getRoad_num());
			i_list.add(t);
		}
		JSONObject obj=JSONObject.fromObject("{}");		
		obj.put("total", i_list.size());
		obj.put("rows", i_list);
	
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
	
	public String queryJunctionByPS_name(){
		/** 
		*根据所属电站查询汇流箱信息*
		* @author liu.yang 
		* @date 2014-12-16
	    * @param ps_name
	    * @param junction_name 
		* @param ps_id  
		* @param list_junction	 
		* @param list_ps_name
		* @param i_list	 
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
		String junction_name = request.getParameter("name");
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");
			junction_name = java.net.URLDecoder.decode(junction_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jbService = new JB_parameterServiceImpl();
		ps_informationService = new PS_informationServiceImpl();//初始化
		int ps_id =1;
		List<JB_parameter> list_junction = new ArrayList<JB_parameter>();
		if(ps_name!=""&&junction_name!=""){//根据电站名称和汇流箱名查询信息
			 ps_id = ps_informationService.getPS_idByName(ps_name);//根据用户名获取
			 list_junction = jbService.getJunctionByPsandname(ps_id,junction_name);			 
		}
		else{
			if(ps_name!=""){//根据电站名称查询信息
				ps_id = ps_informationService.getPS_idByName(ps_name);//根据用户名获取
				list_junction = jbService.getJunctionByPs_name(ps_id);
				
			}
			else{ //根据汇流箱名查询信息
				list_junction = jbService.getJunctionByIts_name(junction_name);		
			}
		}	
		
		List<String> list_ps_name= new ArrayList<String>();//这个list保存查到的name 
		List<jb_parameter> i_list = new ArrayList<jb_parameter>();		
		for(int i=0;i<list_junction.size();i++){
			int ps_id1 = list_junction.get(i).getPS_id();
			String ps_name1="";
			ps_name1 = ps_informationService.getStationNameById(ps_id1);//获取电站id对应的name
			list_ps_name.add(ps_name1);
		}
		for (int i = 0; i < list_junction.size(); i++) {
			jb_parameter t = new jb_parameter();
			t.setId(list_junction.get(i).getId());
			t.setType(list_junction.get(i).getType());
			t.setModel(list_junction.get(i).getModel());
			t.setName(list_junction.get(i).getName());
			t.setBrand(list_junction.get(i).getBrand());
			t.setPs_name(list_ps_name.get(i));
			t.setPurchase_time(list_junction.get(i).getPurchase_time());
			t.setMax_dc_voltage(list_junction.get(i).getMax_dc_voltage());
			t.setRoad_num(list_junction.get(i).getRoad_num());
			i_list.add(t);
		}
		JSONObject obj=JSONObject.fromObject("{}");		
		obj.put("total", i_list.size());
		obj.put("rows", i_list);
		
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
	
	public String  setModelByTypeAndBrandJ()
	{	
		/** 
		*根据设备类型和品牌显示对应的设备型号*
		* @author liu.yang 
		* @date 2014-12-16
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
		equipmentService = new EquipmentServiceImpl();
		String type="汇流箱";//设备类型默认为汇流箱
		List<String> list_model = equipmentService.getAllModelName(type,brand); //根据设备类型与设备品牌查询设备型号 
	   request.setAttribute("list_model", list_model);//返回给前台显示
		ArrayList list = new ArrayList();		
		list.add(list_model);	//通过json将校验结果传回到前台显示
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
}
