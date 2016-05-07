/** * * 
* 文件名称: toInverterManageAction.java *
* 
* 逆变器信息管理，增删改查逆变器信息*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-18 下午2:27:40 *
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


import com.PSMS.Adapter.inverter_parameter;
import com.PSMS.Adapter.user;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Hibernate.M_user;
import com.PSMS.Service.EquipmentService;
import com.PSMS.Service.EquipmentServiceImpl;
import com.PSMS.Service.Inverter_parameterService;
import com.PSMS.Service.Inverter_parameterServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.RoleServiceImpl;
/** 
* 逆变器设备信息管理，加载页面,将需要显示的设备信息通过json传回前台,
* 删除选中的逆变器信息,
* 新建逆变器,校验逆变器是否已存在,
* 根据逆变器名查询信息,
* 根据所属电站查询逆变器信息,
* 根据设备类型和品牌显示对应的设备型号
* @author jiaojiao.wang 
* @date 2014-11-18
* @param inverterService 
* @param ps_informationService 
* @param equipmentService 		
*/ 
public class toInverterManageAction {
	
	private Inverter_parameterService inverterService;
	
	private PS_informationService ps_informationService;
	
	private EquipmentService equipmentService;
	
	private String BatteryCapacity ;
	
	/** 
	*加载页面*
	* @author jiaojiao.wang 
	* @date 2014-11-18
    * @param list_station_name
    * @param list_type 
	* @param type 
	* @param list_brand  	
	*/ 
	public String toInverterManage(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			ps_informationService = new PS_informationServiceImpl();
			equipmentService = new EquipmentServiceImpl();
			List<String> list_station_name = ps_informationService.getAllStationName();//显示所有电站名称
//			List<String> list_type = equipmentService.getAllTypeName();//显示所有设备类型
			List<String> list_brand = equipmentService.getAllBrand(); //获取所有的品牌		
			request.setAttribute("list_station_name", list_station_name);//返回给前台显示
			request.setAttribute("list_brand", list_brand);//返回给前台显示   
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/** 
	*将需要显示的设备信息通过json传回前台*
	* @author jiaojiao.wang
	* @date 2014-11-18
    * @param list_all_inverter
    * @param list_ps_name 
	* @param inverter_parameter_list 	
	*/ 
	public String getInverterInformation(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		inverterService = new Inverter_parameterServiceImpl();
		List<Inverter_parameter> list_all_inverter = inverterService.getAllInverter();// 得到所有逆变器信息		
		List<String> list_ps_name = new ArrayList<String>();// 这个list保存查到的name
		List<inverter_parameter> inverter_parameter_list = new ArrayList<inverter_parameter>();
		for (int i = 0; i < list_all_inverter.size(); i++) {
			int ps_id = list_all_inverter.get(i).getPS_id();// 获取每个逆变器对应的所属电站id			
			String	ps_name = ps_informationService.getStationNameById(ps_id);// 获取电站id对应的name
			list_ps_name.add(ps_name);
		}
		for (int i = 0; i < list_all_inverter.size(); i++) {
			inverter_parameter u = new inverter_parameter();
			u.setId(list_all_inverter.get(i).getId());
			u.setType(list_all_inverter.get(i).getType());
			u.setModel(list_all_inverter.get(i).getModel());
			u.setName(list_all_inverter.get(i).getName());
			u.setBrand(list_all_inverter.get(i).getBrand());
			u.setPs_name(list_ps_name.get(i));
			u.setPurchase_time(list_all_inverter.get(i).getPurchase_time());
			u.setRate_power(list_all_inverter.get(i).getRate_power());
			u.setRated_voltage(list_all_inverter.get(i).getRated_voltage());
			u.setMax_power(list_all_inverter.get(i).getMax_power());
			u.setPower_factor(list_all_inverter.get(i).getPower_factor());
			u.setBatteryCapacity(list_all_inverter.get(i).getBatteryCapacity());
			inverter_parameter_list.add(u);
		}		
	
		ArrayList list = new ArrayList();
		list.add(inverter_parameter_list);//通过json将校验结果传回到前台显示
		JSONObject object = JSONObject.fromObject("{}");
		object.put("total", inverter_parameter_list.size());
		object.put("rows", inverter_parameter_list);
		try {
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
			System.out.println(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/** 
	* 校验逆变器 是否已存在*
	* @author jiaojiao.wang
	* @date 2014-11-18
    * @param name
    * @param ps_name 
	* @param result  
	* @param ps_id	
	*/ 
	public String checkInverterNameIsLegal() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");
			String ps_name = request.getParameter("ps_name");
			name = java.net.URLDecoder.decode(name, "UTF-8");
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");// 以上为获取前台数据，转码
			inverterService = new Inverter_parameterServiceImpl();
			ps_informationService = new PS_informationServiceImpl();
			String result = "";//用result存放提示信息，并将其传回前台
			
			int ps_id = ps_informationService.getPS_idByName(ps_name);//根据电站名称获得对应的电站id
			if (!(inverterService.checkInverterNameExistById(name, ps_id)))//校验逆变器 在该电站中是否已存在
				result = "correct";
			else
				result = "wrong";		// 电站内部逆变器不重名时返回correct
			ArrayList list = new ArrayList();
			list.add(result);//通过json将校验结果传回到前台显示
			JSONArray obj = JSONArray.fromObject(list);
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	*新建逆变器*
	* @author jiaojiao.wang
	* @date 2014-11-18
    * @param id
    * @param ps_name 
	* @param name  
	* @param type
    * @param brand 
	* @param model 
	* @param purchase_time   
	* @param rate_power
    * @param rated_voltage 
	* @param max_power  
	* @param power_factor
	* @param i_parameter  
	* @param ps_id
	* @param result	
	*/
	public String addInverter(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			String ps_name = request.getParameter("ps_name");
			String name = request.getParameter("name");
			String type = request.getParameter("type");
			String brand = request.getParameter("brand");
			String model = request.getParameter("model");
			String purchase_time = request.getParameter("purchase_time");
			String rate_power = request.getParameter("rate_power");
			String rated_voltage = request.getParameter("rated_voltage");
			String max_power = request.getParameter("max_power");
			String power_factor = request.getParameter("power_factor");
			String BatteryCapacity=request.getParameter("batteryCapacity");
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");
			name = java.net.URLDecoder.decode(name, "UTF-8");
			type = java.net.URLDecoder.decode(type, "UTF-8");
			brand = java.net.URLDecoder.decode(brand, "UTF-8");
			model = java.net.URLDecoder.decode(model, "UTF-8");
			BatteryCapacity= java.net.URLDecoder.decode(BatteryCapacity, "UTF-8");
			purchase_time = java.net.URLDecoder.decode(purchase_time, "UTF-8");
			
			ps_informationService = new PS_informationServiceImpl();
			inverterService = new Inverter_parameterServiceImpl();		// 以上为初始化
			
			inverter_parameter u = new inverter_parameter();
			Inverter_parameter i_parameter = new Inverter_parameter();
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
			if(rate_power==""){//判断额定功率若没有填写则默认为空
				i_parameter.setRate_power(null);
			}else {	i_parameter.setRate_power(rate_power);}
			if(rated_voltage==""){//判断额定电压若没有填写则默认为空
				i_parameter.setRated_voltage(null);
			}else {	i_parameter.setRated_voltage(rated_voltage);}
			if(max_power==""){//判断最大功率若没有填写则默认为空
				i_parameter.setMax_power(null);
			}else {	i_parameter.setMax_power(max_power);}
			if(power_factor==""){//判断功率因数若没有填写则默认为空
				i_parameter.setPower_factor(null);
			}else {	i_parameter.setPower_factor(power_factor);
			
			}
			if(BatteryCapacity!=null&&BatteryCapacity!=""){
				i_parameter.setBatteryCapacity(BatteryCapacity);
			}else{
				i_parameter.setBatteryCapacity("0");
			}
			inverterService.addInverter(i_parameter);
			
			ArrayList list = new ArrayList();
			String result = "设备保存成功！";//用result存放提示信息，并将其传回前台
			list.add(result);//通过json将校验结果传回到前台显示
			JSONArray obj = JSONArray.fromObject(list);
			
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	* 删除选中的逆变器信息 *
	* @author jiaojiao.wang 
	* @date 2014-11-18
    * @param inverter_id
    * @param id 	
	*/
	public String deleteInverter() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String inverter_id = request.getParameter("id");
		int id = Integer.parseInt(inverter_id);
		inverterService = new Inverter_parameterServiceImpl();
		inverterService.deleteInverterById(id);// 根据id删除该逆变器
		return null;
	}
	
	/** 
	*根据逆变器名查询信息*
	* @author jiaojiao.wang 
	* @date 2014-11-18
    * @param inverter_name
    * @param list_inverter 
	* @param list_ps_name 
	* @param i_list 
	* @param ps_id	 
	* @param ps_name
	*/
	public String queryInverterByName(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String inverter_name = request.getParameter("name");
		try {
			inverter_name = java.net.URLDecoder.decode(inverter_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inverterService = new Inverter_parameterServiceImpl();
		ps_informationService = new PS_informationServiceImpl();//初始化
		List<Inverter_parameter> list_inverter = inverterService.getInverterByIts_name(inverter_name);//得到所有逆变器信息
		List<String> list_ps_name= new ArrayList<String>();//这个list保存查到的name 
		List<inverter_parameter> i_list = new ArrayList<inverter_parameter>();
		for(int i=0;i<list_inverter.size();i++){
			int ps_id = list_inverter.get(i).getPS_id();
			String ps_name="";
			ps_name = ps_informationService.getStationNameById(ps_id);//获取电站id对应的name
			list_ps_name.add(ps_name);
		}
		for (int i = 0; i < list_inverter.size(); i++) {
			inverter_parameter u = new inverter_parameter();
			u.setId(list_inverter.get(i).getId());
			u.setType(list_inverter.get(i).getType());
			u.setModel(list_inverter.get(i).getModel());
			u.setName(list_inverter.get(i).getName());
			u.setBrand(list_inverter.get(i).getBrand());
			u.setPs_name(list_ps_name.get(i));
			u.setPurchase_time(list_inverter.get(i).getPurchase_time());
			u.setRate_power(list_inverter.get(i).getRate_power());
			u.setRated_voltage(list_inverter.get(i).getRated_voltage());
			u.setMax_power(list_inverter.get(i).getMax_power());
			u.setPower_factor(list_inverter.get(i).getPower_factor());
			u.setBatteryCapacity(list_inverter.get(i).getBatteryCapacity());
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
	
	/** 
	*根据所属电站查询逆变器信息*
	* @author jiaojiao.wang 
	* @date 2014-11-18
    * @param ps_name
    * @param inverter_name 
	* @param ps_id  
	* @param list_inverter	 
	* @param list_ps_name
	* @param i_list	 
	* @param ps_id
	* @param ps_name1         
	*/
	public String queryInverterByPS_name(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ps_name = request.getParameter("ps_name");
		String inverter_name = request.getParameter("name");
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");
			inverter_name = java.net.URLDecoder.decode(inverter_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inverterService = new Inverter_parameterServiceImpl();
		ps_informationService = new PS_informationServiceImpl();//初始化
		int ps_id =1;
		List<Inverter_parameter> list_inverter = new ArrayList<Inverter_parameter>();
		if(ps_name!=""&&inverter_name!=""){//根据电站名称和逆变器名查询信息
			 ps_id = ps_informationService.getPS_idByName(ps_name);//根据用户名获取
			 list_inverter = inverterService.getInverterByPsandname(ps_id,inverter_name);
			 
		}
		else{
			if(ps_name!=""){//根据电站名称查询信息
				ps_id = ps_informationService.getPS_idByName(ps_name);//根据用户名获取
				list_inverter = inverterService.getInverterByPs_name(ps_id);
				
			}
			else{ //根据逆变器名查询信息
				list_inverter = inverterService.getInverterByIts_name(inverter_name);//得到所有逆变器信息
			
			
			}
		}	
		List<String> list_ps_name= new ArrayList<String>();//这个list保存查到的name 
		List<inverter_parameter> i_list = new ArrayList<inverter_parameter>();
		
		for(int i=0;i<list_inverter.size();i++){
			int ps_id1 = list_inverter.get(i).getPS_id();
			String ps_name1="";
			ps_name1 = ps_informationService.getStationNameById(ps_id1);//获取电站id对应的name
			list_ps_name.add(ps_name1);
		}
		for (int i = 0; i < list_inverter.size(); i++) {
			inverter_parameter u = new inverter_parameter();
			u.setId(list_inverter.get(i).getId());
			u.setType(list_inverter.get(i).getType());
			u.setModel(list_inverter.get(i).getModel());
			u.setName(list_inverter.get(i).getName());
			u.setBrand(list_inverter.get(i).getBrand());
			u.setPs_name(list_ps_name.get(i));
			u.setPurchase_time(list_inverter.get(i).getPurchase_time());
			u.setRate_power(list_inverter.get(i).getRate_power());
			u.setRated_voltage(list_inverter.get(i).getRated_voltage());
			u.setMax_power(list_inverter.get(i).getMax_power());
			u.setPower_factor(list_inverter.get(i).getPower_factor());
			u.setBatteryCapacity(list_inverter.get(i).getBatteryCapacity());
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
			e.printStackTrace();
		}
		return null;
	}

	/** 
	*根据品牌显示对应的设备类型和设备型号*
	* @author jiaojiao.wang 
	* @date 2014-11-18
    * @param brand
    * @param type 
	* @param list_model          
	*/ 
	public String  setModelByTypeAndBrandI(){	
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
			String brand = request.getParameter("brand");
			brand = java.net.URLDecoder.decode(brand, "UTF-8");
			equipmentService = new EquipmentServiceImpl();
			List<String> list_model = equipmentService.getAllModelName(null,brand); //根据设备类型与设备品牌查询设备型号
			ArrayList list = new ArrayList();	//返回给前台显示	
			list.add(list_model);	//通过json将校验结果传回到前台显示
			JSONArray obj = JSONArray.fromObject(list);
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过品牌查询类型
	 * @return
	 */
	public String  getTypeByBrand(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
			String brand = request.getParameter("brand");
			brand = java.net.URLDecoder.decode(brand, "UTF-8");
			equipmentService = new EquipmentServiceImpl();
			List<String> types = equipmentService.getTypeByBrand(brand); //根据设备类型与设备品牌查询设备型号
			ArrayList list = new ArrayList();	//返回给前台显示	
			list.add(types);	//通过json将校验结果传回到前台显示
			JSONArray obj = JSONArray.fromObject(list);
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
//	 * 通过品牌,类型查询型号
	 * @return
	 */
	public String  getModelByBrandAndType(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
			String brand = request.getParameter("brand");
			String type = request.getParameter("type");
			brand = java.net.URLDecoder.decode(brand, "UTF-8");
			type = java.net.URLDecoder.decode(type, "UTF-8");
			equipmentService = new EquipmentServiceImpl();
			List<String> list_model = equipmentService.getModelByBrandAndType(brand,type); //根据设备类型与设备品牌查询设备型号
			ArrayList list = new ArrayList();	//返回给前台显示	
			list.add(list_model);	//通过json将校验结果传回到前台显示
			JSONArray obj = JSONArray.fromObject(list);
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getBatteryCapacity() {
		return BatteryCapacity;
	}

	public void setBatteryCapacity(String batteryCapacity) {
		BatteryCapacity = batteryCapacity;
	}
	
}
