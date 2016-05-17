/** * * 
* 文件名称: toEquipmentManageAction.java *
* 
* 设备信息管理，增删改查设备信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-6 下午5:55:33 *
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

import com.PSMS.Hibernate.Equipment;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Service.EquipmentService;
import com.PSMS.Service.EquipmentServiceImpl;


import com.PSMS.Service.PS_informationServiceImpl;
/** 
* 设备信息管理，加载页面,将需要显示的设备信息通过json传回前台,删除设备，新建设备，根据设备品牌查询设备信息，根据设备型号查询设备信息，根据设备类型或型号或品牌查询设备信息，判断设备是否已存在，
* @author liu.yang 
* @date 2014-11-6
* @param equipmentService 	
*/ 
public class toEquipmentManageAction {

	private EquipmentService equipmentService;
	
	public String toEquipmentManage(){
		/** 
		*加载页面*
		* @author liu.yang 
		* @date 2014-11-6
	    * @param list_type 	
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		equipmentService = new EquipmentServiceImpl();
		List<String> list_type = equipmentService.getAllTypeName();//获取所有设备类型
		request.setAttribute("list_type", list_type);//返回给前台显示		
		return "success";
	}
	
	/** 
	*将需要显示的设备信息通过json传回前台*
	* @author liu.yang 
	* @date 2014-11-6
    * @param list_all_equipment 	
	*/ 
	public String getEquipmentInformation(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		equipmentService = new EquipmentServiceImpl();
		List<Equipment> list_all_equipment = equipmentService.getAllEquipment();// 得到所有设备信息
		ArrayList list = new ArrayList();
		JSONObject object = JSONObject.fromObject("{}");
		object.put("total", list_all_equipment.size());
		object.put("rows", list_all_equipment);
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
	
	public String deleteEquipment() {
		/** 
		*删除设备*
		* @author liu.yang 
		* @date 2014-11-6
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
		String equipment_id = request.getParameter("id");
		int id =Integer.parseInt(equipment_id);
		equipmentService = new EquipmentServiceImpl();
		equipmentService.deleteEquipmentById(id);// 根据id删除该设备
		return null;
	}
	
	/** 
	*新建设备*
	* @author liu.yang 
	* @date 2014-11-6
    * @param id 
    * @param brand 
    * @param model 
    * @param type 
    * @param equipment 	
	*/ 
	public String addEquipment(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String id = request.getParameter("id");		
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String type = request.getParameter("type");		
		try {			
			brand = java.net.URLDecoder.decode(brand, "UTF-8");
			model = java.net.URLDecoder.decode(model, "UTF-8");
			type = java.net.URLDecoder.decode(type, "UTF-8");		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		equipmentService = new EquipmentServiceImpl();		// 初始化
		Equipment equipment= new Equipment();		
		if(id!=null){
			equipment.setId(Integer.parseInt(id));
		}		
		equipment.setType(type);
		equipment.setModel(model);
		equipment.setBrand(brand);		
		equipmentService.addEquipment(equipment);		
		ArrayList list = new ArrayList();
		String result = "设备保存成功！";//用result存放提示信息，并将其传回前台
		list.add(result);//通过json将校验结果传回到前台显示
		JSONArray obj = JSONArray.fromObject(list);
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	public String queryEquipmentByName(){
		/** 
		*根据设备品牌查询设备信息*
		* @author liu.yang 
		* @date 2014-11-6	   
	    * @param brand 
	    * @param brand_list 
	    * @param list_equipment 	
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
		List<Equipment> brand_list = new ArrayList<Equipment>();
		List<Equipment> list_equipment = equipmentService.getEquipmentByIts_name(brand);//根据设备品牌查询设备信息	
		for (int i = 0; i < list_equipment.size(); i++) {
			Equipment u = new Equipment();
			u.setId(list_equipment.get(i).getId());
			u.setBrand(list_equipment.get(i).getBrand());
			u.setModel(list_equipment.get(i).getModel());
			u.setType(list_equipment.get(i).getType());			
			brand_list.add(u);
		}
		JSONObject obj=JSONObject.fromObject("{}");		
		obj.put("total", brand_list.size());
		obj.put("rows", brand_list);	
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
	
	public String queryEquipmentByMName(){
		/** 
		*根据设备型号查询设备信息*
		* @author liu.yang 
		* @date 2014-11-6	   
	    * @param model 
	    * @param model_list 
	    * @param list_equipment 	
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String model = request.getParameter("model");
		try {
			model = java.net.URLDecoder.decode(model, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		equipmentService = new EquipmentServiceImpl();
		List<Equipment> model_list = new ArrayList<Equipment>();
		List<Equipment> list_equipment = equipmentService.getEquipmentByM_name(model);//根据设备型号得到设备信息
		
		for (int i = 0; i < list_equipment.size(); i++) {
			Equipment u = new Equipment();
			u.setId(list_equipment.get(i).getId());
			u.setBrand(list_equipment.get(i).getBrand());
			u.setModel(list_equipment.get(i).getModel());
			u.setType(list_equipment.get(i).getType());			
			model_list.add(u);
		}
		JSONObject obj=JSONObject.fromObject("{}");		
		obj.put("total", model_list.size());
		obj.put("rows", model_list);	
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
	
	public String queryEquipmentByType(){
		/** 
		*根据设备类型或型号或品牌查询设备信息*
		* @author liu.yang 
		* @date 2014-11-6	   
	    * @param type 
	    * @param model 
	    * @param brand 	
	    * @param model_list 
	    * @param list_equipment 
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String type = request.getParameter("type");
		String model = request.getParameter("model");
		String brand = request.getParameter("brand");
		try {
			type = java.net.URLDecoder.decode(type, "UTF-8");
			model = java.net.URLDecoder.decode(model, "UTF-8");
			brand = java.net.URLDecoder.decode(brand, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		equipmentService = new EquipmentServiceImpl();//初始化
		List<Equipment> model_list = new ArrayList<Equipment>();
		List<Equipment> list_equipment = new ArrayList<Equipment>();		
		if(type!=""&&brand!=""&&model!=""){	//	根据设备类型,型号和品牌查询设备信息	 
			list_equipment = equipmentService.getEquipmentByTBM(type,brand,model);
		}
		else{
			if(type!=""&&brand!=""&&model.equals("")){//根据设备类型和品牌查询设备信息
				list_equipment = equipmentService.getEquipmentByTB(type,brand);	
			}
			else{ 
				if(type!=""&&model!=""&&brand.equals("")){//根据设备类型型号查询设备信息
					list_equipment = equipmentService.getEquipmentByTM(type,model);	
				}
				else{ 
					if(brand!=""&&model!=""&&type.equals("")){//根据设备型号和品牌查询设备信息
						list_equipment = equipmentService.getEquipmentByBM(brand,model);	
					}
					else{ 
						if(brand!=""&&model.equals("")&&type.equals("")){//根据设备品牌查询设备信息
							list_equipment = equipmentService.getEquipmentByIts_name(brand);	
						}
						else{ 
							if(model!=""&&brand.equals("")&&type.equals("")){//根据设备型号查询设备信息
								list_equipment = equipmentService.getEquipmentByM_name(model);	
							}
							else{ //根据设备类型查询设备信息
								
								list_equipment = equipmentService.getEquipmentByType(type);
							}
						}
					}
				}
			}
		}	
		
		
		for (int i = 0; i < list_equipment.size(); i++) {
			
			Equipment u = new Equipment();
			u.setId(list_equipment.get(i).getId());
			u.setBrand(list_equipment.get(i).getBrand());
			u.setModel(list_equipment.get(i).getModel());
			u.setType(list_equipment.get(i).getType());			
			model_list.add(u);
		}
		JSONObject obj=JSONObject.fromObject("{}");		
		obj.put("total", model_list.size());
		obj.put("rows", model_list);		
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
	*判断设备是否已存在*
	* @author liu.yang 
	* @date 2014-11-6	   
    * @param type 
    * @param model 
    * @param brand 	
    * @param result 
	*/
	public String checkEquipmentIsLegal() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
			String brand = request.getParameter("brand");
			String model = request.getParameter("model");
			String type = request.getParameter("type");
			brand = java.net.URLDecoder.decode(brand, "UTF-8");
			model = java.net.URLDecoder.decode(model, "UTF-8");
			type = java.net.URLDecoder.decode(type, "UTF-8");//以上为获取前台数据，转码
			equipmentService = new EquipmentServiceImpl();//初始化		
			String result = "";//用result存放提示信息，并将其传回前台
				if (!(equipmentService.checkEquipmentExist(brand,model,type)))//判断设备是否已存在
					result = "correct";
				else
					result = "wrong";		// 电站内部设备不重名时返回correct
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
	
	

}	
