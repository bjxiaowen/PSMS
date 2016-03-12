/** * * 
* 文件名称: toFailureAlarmManageAction.java *
* 
* 故障信息管理，增删改查故障信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-1 下午7:45:33 *
* * @author jie.yang 
*/
package com.PSMS.Action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Hibernate.Failure_alarm;
import com.PSMS.Service.EquipmentService;
import com.PSMS.Service.EquipmentServiceImpl;
import com.PSMS.Service.Failure_alarmService;
import com.PSMS.Service.Failure_alarmServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 故障信息管理，加载页面,将需要显示的设备信息通过json传回前台,根据设备类型查询设备信息,新建或者编辑故障,删除选中的故障信息,
 * 校验故障信息是否符合要求,根据设备类型显示设备型号信息,根据设备类型和设备型号显示设备品牌信息,设置设备类型信息,跳转到故障历史数据页面
 * 
 * @author jie.yang
 * @date 2014-11-1
 * @param failure_alarmService
 * @param equipmentService
 */
public class toFailureAlarmManageAction {
	/**
	 * 故障信息
	 */
	private Failure_alarmService failure_alarmService;

	/**
	 * 设备信息
	 */
	private EquipmentService equipmentService;

	/**
	 * 加载页面*
	 * 
	 * @author jie.yang
	 * @date 2014-11-1
	 * @param list_type
	 */
	public String toFailureAlarmManage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		equipmentService = new EquipmentServiceImpl();
		List<String> list_type = equipmentService.getAllTypeName();
		request.setAttribute("list_type", list_type);// 返回给前台显示
		return "success";
	}

	/**
	 * 将需要显示的设备信息通过json传回前台*
	 * 
	 * @author jie.yang
	 * @date 2014-11-1
	 * @param list_failure_alarm
	 */
	public String getFailureAlarmInformation() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		failure_alarmService = new Failure_alarmServiceImpl();
		List<Failure_alarm> list_failure_alarm = failure_alarmService.getAllFailureAndAlarmInfo();// 得到所有逆变器信息

		ArrayList list = new ArrayList();
		JSONObject object = JSONObject.fromObject("{}");
		object.put("total", list_failure_alarm.size());
		object.put("rows", list_failure_alarm);
		try {
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据设备类型查询设备信息*
	 * 
	 * @author jie.yang
	 * @date 2014-11-1
	 * @param type
	 * @param model_list
	 * @param list_equipment
	 */
	public String queryEquipmentByType11() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String type = request.getParameter("type");
		try {
			type = java.net.URLDecoder.decode(type, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		failure_alarmService = new Failure_alarmServiceImpl();// 初始化
		List<Failure_alarm> model_list = new ArrayList<Failure_alarm>();
		List<Failure_alarm> list_equipment = failure_alarmService.getEquipmentByType(type);// 得到所有逆变器信息
		for (int i = 0; i < list_equipment.size(); i++) {
			Failure_alarm u = new Failure_alarm();
			u.setId(list_equipment.get(i).getId());
			u.setBrand(list_equipment.get(i).getBrand());
			u.setModel(list_equipment.get(i).getModel());
			u.setFailure_code(list_equipment.get(i).getFailure_code());
			u.setType(list_equipment.get(i).getType());
			u.setFailure_meaning(list_equipment.get(i).getFailure_meaning());
			u.setFailure_type(list_equipment.get(i).getFailure_type());

			model_list.add(u);
		}
		JSONObject obj = JSONObject.fromObject("{}");
		obj.put("total", model_list.size());
		obj.put("rows", model_list);
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
	 * 新建或者编辑故障*
	 * 
	 * @author jie.yang
	 * @date 2014-11-1
	 * @param id
	 * @param type
	 * @param brand
	 * @param model
	 * @param failure_code
	 * @param failure_meaning
	 * @param failure_type
	 * @param result
	 */
	public String addOrUpdateFA() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		failure_alarmService = new Failure_alarmServiceImpl();

		String id = request.getParameter("id");
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String type = request.getParameter("type");
		String failure_code = request.getParameter("failure_code");
		String failure_meaning = request.getParameter("failure_meaning");
		String failure_type = request.getParameter("failure_type");
		try {

			brand = java.net.URLDecoder.decode(brand, "UTF-8");
			model = java.net.URLDecoder.decode(model, "UTF-8");
			type = java.net.URLDecoder.decode(type, "UTF-8");
			failure_code = java.net.URLDecoder.decode(failure_code, "UTF-8");
			failure_meaning = java.net.URLDecoder.decode(failure_meaning, "UTF-8");
			failure_type = java.net.URLDecoder.decode(failure_type, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Failure_alarm failure_alarm = new Failure_alarm();
		if (id != null) {
			failure_alarm.setId(Integer.parseInt(id));
		}

		failure_alarm.setType(type);
		failure_alarm.setModel(model);
		failure_alarm.setBrand(brand);
		failure_alarm.setFailure_code(failure_code);
		failure_alarm.setFailure_meaning(failure_meaning);
		failure_alarm.setFailure_type(failure_type);
		failure_alarmService.addOrUpdateFA(failure_alarm);
		ArrayList list = new ArrayList();
		String result = "故障保存成功！";// 用result存放提示信息，并将其传回前台
		list.add(result);// 通过json将校验结果传回到前台显示
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

	/**
	 * 删除选中的故障信息 *
	 * 
	 * @author jie.yang
	 * @date 2014-11-1
	 * @param FA_id
	 * @param id
	 */
	public String deleteFA() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String FA_id = request.getParameter("id");
		int id = Integer.parseInt(FA_id);
		failure_alarmService = new Failure_alarmServiceImpl();
		failure_alarmService.deleteFA(id);
		return null;
	}

	/**
	 * 校验故障信息是否符合要求*
	 * 
	 * @author jie.yang
	 * @date 2014-11-1
	 * @param brand
	 * @param model
	 * @param type
	 * @param result
	 */
	public String checkFAInformation() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String type = request.getParameter("type");
		String failure_code = request.getParameter("failure_code");
		try {
			brand = java.net.URLDecoder.decode(brand, "UTF-8");
			model = java.net.URLDecoder.decode(model, "UTF-8");
			type = java.net.URLDecoder.decode(type, "UTF-8");
			failure_code = java.net.URLDecoder.decode(failure_code, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		failure_alarmService = new Failure_alarmServiceImpl();
		String result = "";// 用result存放提示信息，并将其传回前台
		if (!(failure_alarmService.checkFAIsExist(brand, model, type, failure_code)))
			result = "correct";
		else
			result = "wrong";
		ArrayList list = new ArrayList();
		list.add(result);// 通过json将校验结果传回到前台显示
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

	/**
	 * 根据设备类型显示设备型号信息*
	 * 
	 * @author jie.yang
	 * @date 2014-11-1
	 * @param type
	 * @param list_brand
	 */
	public String setBrandByType() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String type = request.getParameter("type");
		try {
			type = java.net.URLDecoder.decode(type, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		equipmentService = new EquipmentServiceImpl();
		List<String> list_brand = equipmentService.getAllBrandName(type);// 根据设备类型与设备品牌查询设备型号
		ArrayList list = new ArrayList();
		list.add(list_brand);// 通过json将校验结果传回到前台显示
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

	/**
	 * 根据设备类型和设备型号显示设备品牌信息*
	 * 
	 * @author jie.yang
	 * @date 2014-11-1
	 * @param type
	 * @param brand
	 * @param list_model
	 */
	public String setModelByTypeAndBrand() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String brand = request.getParameter("brand");
		String type = request.getParameter("type");
		try {
			brand = java.net.URLDecoder.decode(brand, "UTF-8");
			type = java.net.URLDecoder.decode(type, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		equipmentService = new EquipmentServiceImpl();
		List<String> list_model = equipmentService.getAllModelName(type, brand); // 根据设备类型与设备品牌查询设备型号
		ArrayList list = new ArrayList();
		list.add(list_model);// 通过json将校验结果传回到前台显示
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

	/**
	 * 设置设备类型信息*
	 * 
	 * @author jie.yang
	 * @date 2014-11-1
	 * @param list_type
	 */
	public String setType() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		equipmentService = new EquipmentServiceImpl();
		List<String> list_type = equipmentService.getAllTypeName(); // 查询所有设备类型
		ArrayList list = new ArrayList();
		list.add(list_type);
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

	/**
	 * 跳转到故障历史数据页面*
	 * 
	 * @author jie.yang
	 * @date 2014-11-1
	 * @param list_type
	 */
	public String toFailureHistoryPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "success";
	}
}
