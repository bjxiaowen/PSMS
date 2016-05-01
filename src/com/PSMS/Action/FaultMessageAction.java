package com.PSMS.Action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.PSMS.Hibernate.Equipment;
import com.PSMS.Hibernate.M_user;
import com.PSMS.Hibernate.PS_information;
import com.PSMS.Service.EquipmentService;
import com.PSMS.Service.EquipmentServiceImpl;
import com.PSMS.Service.IAreaService;
import com.PSMS.Service.IFaultMessageService;
import com.PSMS.Service.M_userService;
import com.PSMS.Service.M_userServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.impl.AreaServiceImpl;
import com.PSMS.Service.impl.FaultMessageServiceImpl;
import com.PSMS.pojo.Area;
import com.PSMS.pojo.FaultMessage;
import com.PSMS.pojo.JointFaultMessage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FaultMessageAction {

	private IFaultMessageService faultMessageService;

	private M_userService userService;

	private IAreaService areaService;

	private PS_informationService ps_informationService;
	
	private EquipmentService equipmentService;
	
	public String toFaultMessage(){
		try {
			HttpServletRequest request =ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			ps_informationService = new PS_informationServiceImpl();
			equipmentService = new EquipmentServiceImpl();
			areaService = new AreaServiceImpl();
			userService = new M_userServiceImpl();
			List<PS_information> list_ps=ps_informationService.getAllStation();
			request.setAttribute("list_ps", list_ps);// 查询电站列表
			List<Equipment> list_Equipment=equipmentService.getAllEquipment();
			request.setAttribute("list_Equipment", list_Equipment);// 查询设备列表
			List<Area> list_area=areaService.getAll();
			request.setAttribute("list_area", list_area);// 查询区域列表
			List<M_user> list_engineer= userService.getUserByRoleId(4);
			request.setAttribute("list_engineer", list_engineer);// 查询工程师列表
			List<M_user> list_check= userService.getUserByRoleId(5);
			request.setAttribute("list_check", list_check);// 查询检查人员列表
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "success";
	}
	
	public String addFaultMessage(){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			faultMessageService=new FaultMessageServiceImpl();
			faultMessageService.addFaultMessage(getFaultMessageVO());
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
	
	public String updateFaultMessage(){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			faultMessageService=new FaultMessageServiceImpl();
			faultMessageService.updateFaultMessage(getFaultMessageVO());
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
	
	public String deleteFaultMessage(){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			faultMessageService=new FaultMessageServiceImpl();
			faultMessageService.deleteFaultMessage(getFaultMessageVO());
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
	
	public String getFaultMessageById(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String faultMessageId = request.getParameter("faultMessageId");
			faultMessageId = java.net.URLDecoder.decode(faultMessageId, "UTF-8");
			faultMessageService=new FaultMessageServiceImpl();
			JointFaultMessage joint = faultMessageService.getFaultMessageById(faultMessageId);
			List<JointFaultMessage> list = new ArrayList<JointFaultMessage>();
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
	
	public String getFaultMessageByPsId(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			faultMessageService=new FaultMessageServiceImpl();
			List<JointFaultMessage> list = faultMessageService.getFaultMessageByPsId(psId);
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
	
	public String getAllFaultMessage(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			faultMessageService=new FaultMessageServiceImpl();
			HttpSession session=request.getSession();
			M_user user=(M_user) session.getAttribute("user");
			List<JointFaultMessage> list=null;
			if(user!=null){
				int roleId=user.getRole_id();
				if(roleId==5||roleId==4||roleId==3){
					list = faultMessageService.getFaultMessageByUserId(user.getId());
				}else{
					list = faultMessageService.getAllJointFaultMessage();
				}
			}else{
				list=new ArrayList<JointFaultMessage>();
			}
			
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
	
	private FaultMessage getFaultMessageVO() throws UnsupportedEncodingException{
		FaultMessage fau=new FaultMessage();
		HttpServletRequest request =ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		String faultMessageId = request.getParameter("faultMessageId");
		/**
		 * 主键
		 */
		if(null!=faultMessageId&&!faultMessageId.equals("")){
			faultMessageId = java.net.URLDecoder.decode(faultMessageId, "UTF-8");
			fau.setFaultMessageId(faultMessageId);
		}

		/**
		 * 区域id
		 */
		String areaId = request.getParameter("areaId");
		if(null!=areaId&&!areaId.equals("")){
			areaId = java.net.URLDecoder.decode(areaId, "UTF-8");
			fau.setAreaId(areaId);
		}
		/**
		 * 电站id
		 */
		String psId = request.getParameter("psId");
		if(null!=psId&&!psId.equals("")){
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			fau.setPsId(Integer.parseInt(psId));
		}

		/**
		 * 设备id
		 */
		String equipmentId = request.getParameter("equipmentId");
		if(null!=equipmentId&&!equipmentId.equals("")){
			equipmentId = java.net.URLDecoder.decode(equipmentId, "UTF-8");
			fau.setEquipmentId(Integer.parseInt(equipmentId));
		}
		/**
		 * 设备状态
		 */
		String equipmentStatus = request.getParameter("equipmentStatus");
		if(null!=equipmentStatus&&!equipmentStatus.equals("")){
			equipmentStatus = java.net.URLDecoder.decode(equipmentStatus, "UTF-8");
			fau.setEquipmentStatus(Integer.parseInt(equipmentStatus));
		}

		/**
		 * 工程师id
		 */
		String userId = request.getParameter("userId");
		if(null!=userId&&!userId.equals("")){
			userId = java.net.URLDecoder.decode(userId, "UTF-8");
			fau.setUserId(Integer.parseInt(userId));
		}

		/**
		 * 报警时间
		 */
		String alertTime = request.getParameter("alertTime");
		if(null!=userId&&!userId.equals("")){
			alertTime = java.net.URLDecoder.decode(alertTime, "UTF-8");
			fau.setAlertTime(alertTime);
		}

		/**
		 * 处理状态
		 */
		String status = request.getParameter("status");
		if(null!=status&&!status.equals("")){
			status = java.net.URLDecoder.decode(status, "UTF-8");
			fau.setStatus(Integer.parseInt(status));
		}

		/**
		 * 初步诊断
		 */
		String initialDiagnose = request.getParameter("initialDiagnose");
		if(null!=initialDiagnose&&!initialDiagnose.equals("")){
			initialDiagnose = java.net.URLDecoder.decode(initialDiagnose, "UTF-8");
			fau.setInitialDiagnose(initialDiagnose);
		}

		/**
		 * 预计完成日期
		 */
		String predictTime = request.getParameter("predictTime");
		if(null!=predictTime&&!predictTime.equals("")){
			predictTime = java.net.URLDecoder.decode(predictTime, "UTF-8");
			fau.setPredictTime(predictTime);
		}

		/**
		 * 故障原因
		 */
		String alertCause = request.getParameter("alertCause");
		if(null!=alertCause&&!alertCause.equals("")){
			alertCause = java.net.URLDecoder.decode(alertCause, "UTF-8");
			fau.setAlertCause(alertCause);
		}
		
		/**
		 * 处理状况
		 */
		String handleCondition = request.getParameter("handleCondition");
		if(null!=handleCondition&&!handleCondition.equals("")){
			handleCondition = java.net.URLDecoder.decode(handleCondition, "UTF-8");
			fau.setHandleCondition(handleCondition);
		}

		/**
		 * 维护日期
		 */
		String maintainDate = request.getParameter("maintainDate");
		if(null!=maintainDate&&!maintainDate.equals("")){
			maintainDate = java.net.URLDecoder.decode(maintainDate, "UTF-8");
			fau.setMaintainDate(maintainDate);
		}

		/**
		 * 检验人
		 */
		String checkPerson = request.getParameter("checkPerson");
		if(null!=checkPerson&&!checkPerson.equals("")){
			checkPerson = java.net.URLDecoder.decode(checkPerson, "UTF-8");
			fau.setCheckPerson(checkPerson);
		}
		/**
		 * 检验日期
		 */
		String checkDate = request.getParameter("checkDate");
		if(null!=checkDate&&!checkDate.equals("")){
			checkDate = java.net.URLDecoder.decode(checkDate, "UTF-8");
			fau.setCheckDate(checkDate);
		}
		
		String checkText = request.getParameter("checkText");
		if(null!=checkText&&!checkText.equals("")){
			checkText = java.net.URLDecoder.decode(checkText, "UTF-8");
			fau.setCheckText(checkText);;
		}
		
		String checkStatus = request.getParameter("checkStatus");
		if(null!=checkStatus&&!checkStatus.equals("")){
			checkStatus = java.net.URLDecoder.decode(checkStatus, "UTF-8");
			fau.setCheckStatus(Integer.parseInt(checkStatus));
		}
		return fau;
	}

	public IFaultMessageService getFaultMessageService() {
		return faultMessageService;
	}

	public void setFaultMessageService(IFaultMessageService faultMessageService) {
		this.faultMessageService = faultMessageService;
	}
}
