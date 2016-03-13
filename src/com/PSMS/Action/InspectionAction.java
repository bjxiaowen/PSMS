package com.PSMS.Action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Hibernate.Equipment;
import com.PSMS.Hibernate.M_user;
import com.PSMS.Hibernate.PS_information;
import com.PSMS.Service.EquipmentService;
import com.PSMS.Service.EquipmentServiceImpl;
import com.PSMS.Service.IAreaService;
import com.PSMS.Service.IInspectionService;
import com.PSMS.Service.M_userService;
import com.PSMS.Service.M_userServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.impl.AreaServiceImpl;
import com.PSMS.Service.impl.InspectionServiceImpl;
import com.PSMS.pojo.Area;
import com.PSMS.pojo.Inspection;
import com.PSMS.pojo.JointInspection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class InspectionAction {

	private IInspectionService inspectionService;

	private M_userService userService;

	private IAreaService areaService;

	private PS_informationService ps_informationService;

	private EquipmentService equipmentService;

	public String toInspection() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			ps_informationService = new PS_informationServiceImpl();
			equipmentService = new EquipmentServiceImpl();
			areaService = new AreaServiceImpl();
			userService = new M_userServiceImpl();
			List<PS_information> list_ps = ps_informationService.getAllStation();
			request.setAttribute("list_ps", list_ps);// 查询电站列表
			List<Equipment> list_Equipment = equipmentService.getAllEquipment();
			request.setAttribute("list_Equipment", list_Equipment);// 查询设备列表
			List<Area> list_area = areaService.getAll();
			request.setAttribute("list_area", list_area);// 查询区域列表
			List<M_user> list_engineer = userService.getUserByRoleId(4);
			request.setAttribute("list_engineer", list_engineer);// 查询工程师列表
			List<M_user> list_check = userService.getUserByRoleId(5);
			request.setAttribute("list_check", list_check);// 查询检查人员列表
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String addInspection() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			inspectionService = new InspectionServiceImpl();
			inspectionService.addInspection(getInspectionVO());
			List<String> list = new ArrayList<String>();
			String result = "添加成功！";// 用result存放提示信息，并将其传回前台
			list.add(result);// 通过json将校验结果传回到前台显示
			JSONArray obj = JSONArray.fromObject(list);
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String updateInspection() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			inspectionService = new InspectionServiceImpl();
			inspectionService.UpdateInspection(getInspectionVO());
			List<String> list = new ArrayList<String>();
			String result = "修改成功！";// 用result存放提示信息，并将其传回前台
			list.add(result);// 通过json将校验结果传回到前台显示
			JSONArray obj = JSONArray.fromObject(list);
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String deleteInspection() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			inspectionService = new InspectionServiceImpl();
			inspectionService.deleteInspection(getInspectionVO());
			List<String> list = new ArrayList<String>();
			String result = "删除成功！";// 用result存放提示信息，并将其传回前台
			list.add(result);// 通过json将校验结果传回到前台显示
			JSONArray obj = JSONArray.fromObject(list);
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getInspectionById() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String inspectionId = request.getParameter("inspectionId");
			inspectionId = java.net.URLDecoder.decode(inspectionId, "UTF-8");
			inspectionService = new InspectionServiceImpl();
			JointInspection joint = inspectionService.getInspectionById(inspectionId);
			List<JointInspection> list = new ArrayList<JointInspection>();
			list.add(joint);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", list.size());
			object.put("rows", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getInspectionAll() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			inspectionService = new InspectionServiceImpl();
			List<JointInspection> list = inspectionService.getInspectionAll();
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", list.size());
			object.put("rows", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
		}
		return null;
	}

	private Inspection getInspectionVO() throws UnsupportedEncodingException {
		Inspection ins = new Inspection();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		String inspectionId = request.getParameter("inspectionId");
		if (null != inspectionId && !inspectionId.equals("")) {
			inspectionId = java.net.URLDecoder.decode(inspectionId, "UTF-8");
			ins.setId(inspectionId);
		}

	
		String inspectionReport = request.getParameter("inspectionReport");
		if (null != inspectionReport && !inspectionReport.equals("")) {
			inspectionReport = java.net.URLDecoder.decode(inspectionReport, "UTF-8");
			ins.setInspectionReport(inspectionReport);
		}
		//shouldDate
		String shouldDate = request.getParameter("shouldDate");
		if (null != shouldDate && !shouldDate.equals("")) {
			shouldDate = java.net.URLDecoder.decode(shouldDate, "UTF-8");
			ins.setShouldDate(shouldDate);
		}
		//actualDate
		String actualDate = request.getParameter("actualDate");
		if (null != actualDate && !actualDate.equals("")) {
			actualDate = java.net.URLDecoder.decode(actualDate, "UTF-8");
			ins.setActualDate(actualDate);
		}
		
		String inspectionStatus = request.getParameter("inspectionStatus");
		if (null != inspectionStatus && !inspectionStatus.equals("")) {
			inspectionStatus = java.net.URLDecoder.decode(inspectionStatus, "UTF-8");
			ins.setInspectionStatus(Integer.parseInt(inspectionStatus));
		}
		return ins;
	}

	public IInspectionService getInspectionService() {
		return inspectionService;
	}

	public void setInspectionService(IInspectionService inspectionService) {
		this.inspectionService = inspectionService;
	}

}
