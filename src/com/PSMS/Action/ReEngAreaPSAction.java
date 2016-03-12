package com.PSMS.Action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.PSMS.Hibernate.M_user;
import com.PSMS.Hibernate.PS_information;
import com.PSMS.Service.IAreaService;
import com.PSMS.Service.IReEngAreaPSService;
import com.PSMS.Service.M_userService;
import com.PSMS.Service.M_userServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.impl.AreaServiceImpl;
import com.PSMS.Service.impl.ReEngAreaPSServiceImpl;
import com.PSMS.pojo.Area;
import com.PSMS.pojo.JointEngAreaPS;
import com.PSMS.pojo.ReEngAreaPowerStation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 添加区域工程师电站关系
 * 
 * @author Andy
 *
 */
public class ReEngAreaPSAction {

	private IReEngAreaPSService engAreaPsService;

	private M_userService userService;

	private IAreaService areaService;

	private PS_informationService ps_informationService;

	public String toReEngAreaPS() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			userService = new M_userServiceImpl();
			areaService = new AreaServiceImpl();
			ps_informationService = new PS_informationServiceImpl();
			List<M_user> userList = userService.getUserByRoleId(4);
			request.setAttribute("list_User", userList);// 查询工程师列表
			List<Area> areaList = areaService.getAll();
			request.setAttribute("list_area", areaList);// 查询区域列表
			List<PS_information> psList = ps_informationService.getAllStation();
			request.setAttribute("list_ps", psList);// 查询电站列表
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 添加关系信息
	 * 
	 * @param areaId
	 *            区域id
	 * @param userId
	 *            用户id
	 * @param psId
	 *            电站id
	 * @return
	 */
	public String addReEngAreaPS() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("utf-8");
			String areaId = request.getParameter("areaId");
			String userId = request.getParameter("userId");
			String psId = request.getParameter("psId");
			areaId = java.net.URLDecoder.decode(areaId, "UTF-8");
			userId = java.net.URLDecoder.decode(userId, "UTF-8");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			engAreaPsService = new ReEngAreaPSServiceImpl();
			ReEngAreaPowerStation reEngAreaPS = new ReEngAreaPowerStation();
			reEngAreaPS.setPsId(Integer.parseInt(psId));
			reEngAreaPS.setUserId(Integer.parseInt(userId));
			reEngAreaPS.setAreaId(areaId);
			engAreaPsService.addReEngAreaPS(reEngAreaPS);
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

	/**
	 * 修改
	 * 
	 * @param areaId
	 *            区域id
	 * @param userId
	 *            用户id
	 * @param psId
	 *            电站id
	 * @param id
	 *            主键
	 * @return
	 */
	public String UpdateReEngAreaPS() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("utf-8");
			String areaId = request.getParameter("areaId");
			String userId = request.getParameter("userId");
			String psId = request.getParameter("psId");
			String id = request.getParameter("id");
			areaId = java.net.URLDecoder.decode(areaId, "UTF-8");
			userId = java.net.URLDecoder.decode(userId, "UTF-8");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			id = java.net.URLDecoder.decode(id, "UTF-8");
			engAreaPsService = new ReEngAreaPSServiceImpl();
			ReEngAreaPowerStation reEngAreaPS = new ReEngAreaPowerStation();
			reEngAreaPS.setPsId(Integer.parseInt(psId));
			reEngAreaPS.setUserId(Integer.parseInt(userId));
			reEngAreaPS.setAreaId(areaId);
			reEngAreaPS.setId(id);
			engAreaPsService.UpdateReEngAreaPS(reEngAreaPS);
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

	/**
	 * 删除
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public String deleteReEngAreaPS() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			id = java.net.URLDecoder.decode(id, "UTF-8");
			engAreaPsService = new ReEngAreaPSServiceImpl();
			engAreaPsService.deleteReEngAreaPS(id);
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

	/**
	 * 通过主键获取
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public String getReEngAreaPSById() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			id = java.net.URLDecoder.decode(id, "UTF-8");
			engAreaPsService = new ReEngAreaPSServiceImpl();
			JointEngAreaPS joint = engAreaPsService.getById(id);
			List<JointEngAreaPS> list = new ArrayList<JointEngAreaPS>();
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

	/**
	 * 获取所有
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public String getAllReEngAreaPS() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			engAreaPsService = new ReEngAreaPSServiceImpl();
			List<JointEngAreaPS> list = engAreaPsService.getAll();
			JSONObject object = JSONObject.fromObject("{}");
			int size=0;
			if(list!=null&&list.size()>0){
				size=list.size();
			}
			object.put("total", size);
			object.put("rows", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据区域名搜索
	 * 
	 * @param areaName
	 *            区域名称
	 * @return
	 */
	public String searchByAreaName() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String areaName = request.getParameter("areaName");
			areaName = java.net.URLDecoder.decode(areaName, "UTF-8");
			engAreaPsService = new ReEngAreaPSServiceImpl();
			List<JointEngAreaPS> list = engAreaPsService.searchByAreaName(areaName);
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

	/**
	 * 根据用户名搜索
	 * 
	 * @param userName
	 *            用户名
	 * @return
	 */
	public String searchByUserName() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String UserName = request.getParameter("userName");
			UserName = java.net.URLDecoder.decode(UserName, "UTF-8");
			engAreaPsService = new ReEngAreaPSServiceImpl();
			List<JointEngAreaPS> list = engAreaPsService.searchByUserName(UserName);
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

	/**
	 * 根据电站名搜索
	 * 
	 * @param psName
	 * @return
	 */
	public String searchByPSName() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String psName = request.getParameter("psName");
			psName = java.net.URLDecoder.decode(psName, "UTF-8");
			engAreaPsService = new ReEngAreaPSServiceImpl();
			List<JointEngAreaPS> list = engAreaPsService.searchByPSName(psName);
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
	
	
	/**
	 * 校验
	 * @param areaId 区域id
	 * @param userId 用户id
	 * @param psId  电站id
	 * @return
	 * @throws Exception
	 */
	public String checkReEngAreaPSById() {
		try {
			String result =null;
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String areaId = request.getParameter("areaId");
			String userId = request.getParameter("userId");
			String psId = request.getParameter("psId");
			areaId = java.net.URLDecoder.decode(areaId, "UTF-8");
			userId = java.net.URLDecoder.decode(userId, "UTF-8");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			engAreaPsService = new ReEngAreaPSServiceImpl();
			List<JointEngAreaPS> lists = engAreaPsService.checkById(areaId, Integer.parseInt(userId), Integer.parseInt(psId));
			if(lists!=null&&lists.size()>0){
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

	public IReEngAreaPSService getEngAreaPsService() {
		return engAreaPsService;
	}

	public void setEngAreaPsService(IReEngAreaPSService engAreaPsService) {
		this.engAreaPsService = engAreaPsService;
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

}
