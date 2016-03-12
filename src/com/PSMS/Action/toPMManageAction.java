/** * * 
* 文件名称: toPMManageAction.java *
* 
* 电表信息管理，增删改查电表信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-6 下午1:52:33 *
* * @author jiaojiao.wang 
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


import com.PSMS.Adapter.pm_parameter;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Hibernate.PM_parameter;
import com.PSMS.Service.EquipmentService;
import com.PSMS.Service.EquipmentServiceImpl;
import com.PSMS.Service.PM_parameterService;
import com.PSMS.Service.PM_parameterServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;




public class toPMManageAction {
	/** 
	* 电表设备信息管理， 加载页面,将需要显示的设备信息通过json传回前台,删除选中的电表信息,新建电表,校验电表是否已存在,根据电表名查询信息,根据所属电站查询电表信息,根据设备类型和品牌显示对应的设备型号
	* @author jiaojiao.wang
	* @date 2014-12-6
	* @param PMService 
	* @param ps_informationService 
	* @param equipmentService 		
	*/ 
	private PM_parameterService PMService;
	private PS_informationService ps_informationService;
	private EquipmentService equipmentService;
	
	public String toPMManage(){
		/** 
		*加载页面*
		* @author jiaojiao.wang
		* @date 2014-12-6
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
		String type="电表";//设备类型默认为电表
		
		List<String> list_brand = equipmentService.getAllBrandName(type); //根据设备类型查询设备品牌
		request.setAttribute("list_station_name", list_station_name);//返回给前台显示
		request.setAttribute("list_type", list_type);//返回给前台显示
		request.setAttribute("list_brand", list_brand);//返回给前台显示
   
		return "success";
	}

	public String getPMInformation(){
		/** 
		*将需要显示的设备信息通过json传回前台*
		* @author jiaojiao.wang
		* @date 2014-12-6
	    * @param list_all_PM
	    * @param list_ps_name 
		* @param PM_parameter_list 	
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
		PMService = new PM_parameterServiceImpl();
		List<PM_parameter> list_all_PM = PMService.getAllPM();// 得到所有电表信息
		List<String> list_ps_name = new ArrayList<String>();// 这个list保存查到的name
		List<pm_parameter> PM_parameter_list = new ArrayList<pm_parameter>();
		for (int i = 0; i < list_all_PM.size(); i++) {
			int ps_id = list_all_PM.get(i).getPS_id();// 获取每个电表对应的所属电站id			
			String	ps_name = ps_informationService.getStationNameById(ps_id);// 获取电站id对应的name
			list_ps_name.add(ps_name);
		}
		for (int i = 0; i < list_all_PM.size(); i++) {
			pm_parameter u = new pm_parameter();
			u.setId(list_all_PM.get(i).getId());
			u.setType(list_all_PM.get(i).getType());
			u.setModel(list_all_PM.get(i).getModel());
			u.setName(list_all_PM.get(i).getName());
			u.setBrand(list_all_PM.get(i).getBrand());
			u.setPs_name(list_ps_name.get(i));
			u.setPurchase_time(list_all_PM.get(i).getPurchase_time());
			u.setMax_current(list_all_PM.get(i).getMax_current());
			PM_parameter_list.add(u);
		}
				
		ArrayList list = new ArrayList();
		list.add(PM_parameter_list);//通过json将校验结果传回到前台显示
		
		JSONObject object = JSONObject.fromObject("{}");
		object.put("total", PM_parameter_list.size());
		object.put("rows", PM_parameter_list);
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
	
	public String checkPMNameIsLegal() {
		/** 
		*校验电表 是否已存在*
		* @author jiaojiao.wang 
		* @date 2014-12-6
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
		PMService = new PM_parameterServiceImpl();
		ps_informationService = new PS_informationServiceImpl();
		String result = "";		//用result存放提示信息，并将其传回前台
			int ps_id = ps_informationService.getPS_idByName(ps_name);//根据电站名称获得对应的电站id
			if (!(PMService.checkPMNameExistById(name, ps_id)))//校验汇流箱 在该电站中是否已存在
				result = "correct";
			else
				result = "wrong";		// 电站内部电表不重名时返回correct
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
	
	public String addPM()
	{
		/** 
		*新建电表*
		* @author jiaojiao.wang 
		* @date 2014-12-6
	    * @param id
	    * @param ps_name 
		* @param name  
		* @param type
	    * @param brand 
		* @param model 
		* @param purchase_time   
		* @param max_current
	    * @param i_parameter 
		* @param ps_id  
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
		String max_current = request.getParameter("max_current");
		
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");
			name = java.net.URLDecoder.decode(name, "UTF-8");
			type = java.net.URLDecoder.decode(type, "UTF-8");
			brand = java.net.URLDecoder.decode(brand, "UTF-8");
			model = java.net.URLDecoder.decode(model, "UTF-8");
			purchase_time = java.net.URLDecoder.decode(purchase_time, "UTF-8");
			// 以上为获取前台数据，转码
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		PMService = new PM_parameterServiceImpl();
		// 以上为初始化
		PM_parameter i_parameter = new PM_parameter();
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
		if(max_current==""){//判断最大承受电流是否为数字
			i_parameter.setMax_current(null);
		}else {	i_parameter.setMax_current(max_current);}

		PMService.addPM(i_parameter);
		
		ArrayList list = new ArrayList();
		String result = "电表保存成功！";//用result存放提示信息，并将其传回前台
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
	
	public String deletePM() {
		/** 
		* 删除选中的电表信息 *
		* @author jiaojiao.wang 
		* @date 2014-12-6
	    * @param PM_id
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
		String PM_id = request.getParameter("id");
		int id = Integer.parseInt(PM_id);
		PMService = new PM_parameterServiceImpl();
		PMService.deletePMById(id);// 根据id删除该电表
		return null;
	}

	public String queryPMByName(){
		/** 
		*根据电表名查询信息*
		* @author jiaojiao.wang 
		* @date 2014-12-6
	    * @param PM_name
	    * @param list_PM
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
		String PM_name = request.getParameter("name");
		try {
			PM_name = java.net.URLDecoder.decode(PM_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PMService = new PM_parameterServiceImpl();
		ps_informationService = new PS_informationServiceImpl();//初始化
		List<PM_parameter> list_PM = PMService.getPMByIts_name(PM_name);//得到所有电表信息
		List<String> list_ps_name= new ArrayList<String>();//这个list保存查到的name 
		List<pm_parameter> i_list = new ArrayList<pm_parameter>();
		for(int i=0;i<list_PM.size();i++){
			int ps_id = list_PM.get(i).getPS_id();
			String ps_name="";
			ps_name = ps_informationService.getStationNameById(ps_id);//获取电站id对应的name
			list_ps_name.add(ps_name);
		}
		for (int i = 0; i < list_PM.size(); i++) {
			pm_parameter u = new pm_parameter();
			u.setId(list_PM.get(i).getId());
			u.setType(list_PM.get(i).getType());
			u.setModel(list_PM.get(i).getModel());
			u.setName(list_PM.get(i).getName());
			u.setBrand(list_PM.get(i).getBrand());
			u.setPs_name(list_ps_name.get(i));
			u.setPurchase_time(list_PM.get(i).getPurchase_time());
			u.setMax_current(list_PM.get(i).getMax_current());
			i_list.add(u);
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
	
	public String queryPMByPS_name(){
		/** 
		*根据所属电站查询电表信息*
		* @author jiaojiao.wang 
		* @date 2014-12-6
	    * @param ps_name
	    * @param PM_name 
		* @param ps_id  
		* @param list_PM	 
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
		String PM_name = request.getParameter("name");
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");
			PM_name = java.net.URLDecoder.decode(PM_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PMService = new PM_parameterServiceImpl();
		ps_informationService = new PS_informationServiceImpl();//初始化	
		int ps_id =1;
		List<PM_parameter> list_PM = new ArrayList<PM_parameter>();
		if(ps_name!=""&&PM_name!=""){//根据电站名称和电表名查询信息
			 ps_id = ps_informationService.getPS_idByName(ps_name);//根据用户名获取
			 list_PM = PMService.getPMByPsandname(ps_id,PM_name);			 
		}
		else{
			if(ps_name!=""){
				ps_id = ps_informationService.getPS_idByName(ps_name);//根据用户名获取
				list_PM = PMService.getPMByPs_name(ps_id);				
			}
			else{ 
				list_PM = PMService.getPMByIts_name(PM_name);//得到所有电表信息			
			}
		}	
		List<String> list_ps_name= new ArrayList<String>();//这个list保存查到的name 
		List<pm_parameter> i_list = new ArrayList<pm_parameter>();
		
		for(int i=0;i<list_PM.size();i++){
			int ps_id1 = list_PM.get(i).getPS_id();
			String ps_name1="";
			ps_name1 = ps_informationService.getStationNameById(ps_id1);//获取电站id对应的name
			list_ps_name.add(ps_name1);
		}
		for (int i = 0; i < list_PM.size(); i++) {
			pm_parameter u = new pm_parameter();
			u.setId(list_PM.get(i).getId());
			u.setType(list_PM.get(i).getType());
			u.setModel(list_PM.get(i).getModel());
			u.setName(list_PM.get(i).getName());
			u.setBrand(list_PM.get(i).getBrand());
			u.setPs_name(list_ps_name.get(i));
			u.setPurchase_time(list_PM.get(i).getPurchase_time());
			u.setMax_current(list_PM.get(i).getMax_current());

			i_list.add(u);
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

	public String  setModelByTypeAndBrandP()
	{	
		/** 
		*根据设备类型和品牌显示对应的设备型号*
		* @author jiaojiao.wang 
		* @date 2014-12-6
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
		String type="电表";//设备类型默认为电表
		List<String> list_model = equipmentService.getAllModelName(type,brand); //根据设备类型与设备品牌查询设备型号	 
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
}
