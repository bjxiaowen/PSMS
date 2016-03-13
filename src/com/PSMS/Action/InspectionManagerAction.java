package com.PSMS.Action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.PSMS.Service.IInspectionManagerService;
import com.PSMS.Service.M_userService;
import com.PSMS.Service.M_userServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.impl.AreaServiceImpl;
import com.PSMS.Service.impl.InspectionManagerServiceImpl;
import com.PSMS.Service.impl.ReEngAreaPSServiceImpl;
import com.PSMS.pojo.Area;
import com.PSMS.pojo.InspectionManager;
import com.PSMS.pojo.JointEngAreaPS;
import com.PSMS.pojo.JointInspection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class InspectionManagerAction {

	private M_userService userService;

	private IAreaService areaService;

	private PS_informationService ps_informationService;

	private EquipmentService equipmentService;
	
	private IInspectionManagerService inspectionManagerService;
	
	public String toInspectionManager() {
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
	
	public String updateInspectionManager() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			inspectionManagerService = new InspectionManagerServiceImpl();
			inspectionManagerService.updateInspectionManager(getInspectionManagerVO());
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
	
	public String addInspectionManager() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			inspectionManagerService = new InspectionManagerServiceImpl();
			inspectionManagerService.addInspectionManager(getInspectionManagerVO());
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
	
	public String deleteInspectionManager() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			inspectionManagerService = new InspectionManagerServiceImpl();
			inspectionManagerService.deleteInspectionManager(getInspectionManagerVO());
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
	
	public String getInspectionManagerById() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String inspectionId = request.getParameter("inspectionId");
			inspectionId = java.net.URLDecoder.decode(inspectionId, "UTF-8");
			inspectionManagerService = new InspectionManagerServiceImpl();
			JointInspection joint = inspectionManagerService.getById(inspectionId);
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

	public String getAllInspectionManager() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			inspectionManagerService = new InspectionManagerServiceImpl();
			List<JointInspection> list = inspectionManagerService.getAll();
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
	
	public String getCheckById(){
		try {

			String result =null;
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String areaId = request.getParameter("areaId");
			String userId = request.getParameter("userId");
			String psId = request.getParameter("psId");
			String equipmentId= request.getParameter("equipmentId");
			areaId = java.net.URLDecoder.decode(areaId, "UTF-8");
			userId = java.net.URLDecoder.decode(userId, "UTF-8");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			equipmentId = java.net.URLDecoder.decode(equipmentId, "UTF-8");
			inspectionManagerService = new InspectionManagerServiceImpl();
			JointInspection lists = inspectionManagerService.checkById(areaId,Integer.valueOf(psId) ,Integer.parseInt(userId) ,Integer.parseInt(equipmentId) );
			if(lists!=null&&lists.getManageId()==null){
				result="wrong";
			}else{
				result="correct";
			}
			List<String> list=new ArrayList<String>();
			list.add(result);
			JSONArray object = JSONArray.fromObject(list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public M_userService getUserService() {
		return userService;
	}

	public void setUserService(M_userService userService) {
		this.userService = userService;
	}

	public IAreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(IAreaService areaService) {
		this.areaService = areaService;
	}

	public PS_informationService getPs_informationService() {
		return ps_informationService;
	}

	public void setPs_informationService(PS_informationService ps_informationService) {
		this.ps_informationService = ps_informationService;
	}

	public EquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	public IInspectionManagerService getInspectionManagerService() {
		return inspectionManagerService;
	}

	public void setInspectionManagerService(IInspectionManagerService inspectionManagerService) {
		this.inspectionManagerService = inspectionManagerService;
	}
	
	private InspectionManager getInspectionManagerVO() throws UnsupportedEncodingException {
		InspectionManager ins = new InspectionManager();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		id = java.net.URLDecoder.decode(id, "UTF-8");
		if (null != id && !id.equals("")) {
			ins.setId(id);
		}
	
		String psId = request.getParameter("psId");
		psId = java.net.URLDecoder.decode(psId, "UTF-8");
		if (null != psId && !psId.equals("")) {
			ins.setPsId(Integer.parseInt(psId));
		}
		
		String areaId = request.getParameter("areaId");
		areaId = java.net.URLDecoder.decode(areaId, "UTF-8");
		if (null != areaId && !areaId.equals("")) {
			ins.setAreaId(areaId);
		}
		
		String equipmentId = request.getParameter("equipmentId");
		equipmentId = java.net.URLDecoder.decode(equipmentId, "UTF-8");
		if (null != equipmentId && !equipmentId.equals("")) {
			ins.setEquipmentId(Integer.parseInt(equipmentId));
		}
		
		String userId = request.getParameter("userId");
		userId = java.net.URLDecoder.decode(userId, "UTF-8");
		if (null != userId && !userId.equals("")) {
			ins.setUserId(Integer.parseInt(userId));
		}
		
		String currDate = request.getParameter("currDate");
		currDate = java.net.URLDecoder.decode(currDate, "UTF-8");
		if (null != currDate && !currDate.equals("")) {
			ins.setCurrDate(currDate);
		}
		
		String inspectionPeriod = request.getParameter("inspectionPeriod");
		inspectionPeriod = java.net.URLDecoder.decode(inspectionPeriod, "UTF-8");
		if (null != inspectionPeriod && !inspectionPeriod.equals("")) {
			ins.setInspectionPeriod(Integer.parseInt(inspectionPeriod));
		}
		
		String nextDate = request.getParameter("nextDate");
		nextDate = java.net.URLDecoder.decode(nextDate, "UTF-8");
		if (null != nextDate && !nextDate.equals("")) {
			ins.setNextDate(nextDate);
		}
		return ins;
	}
	
}
