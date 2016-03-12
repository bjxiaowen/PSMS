package com.PSMS.Action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.PSMS.Hibernate.M_user;
import com.PSMS.Service.IAreaService;
import com.PSMS.Service.IReEngineerAreaService;
import com.PSMS.Service.M_userService;
import com.PSMS.Service.M_userServiceImpl;
import com.PSMS.Service.impl.AreaServiceImpl;
import com.PSMS.Service.impl.ReEngineerAreaServiceImpl;
import com.PSMS.pojo.Area;
import com.PSMS.pojo.ReEngineerArea;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 工程师与区域关系
 * @author Andy
 *
 */
public class ReEngineerAreaAction {

	private IReEngineerAreaService reEngineerAreaService;
	
	private M_userService userService;
	
	private IAreaService areaService;
	
	/**
	 * 工程师与区域关系列表
	 * @return
	 */
	public String toEngineerArea(){
		HttpServletRequest request =ServletActionContext.getRequest();	
		try {
			request.setCharacterEncoding("utf-8");
			userService = new M_userServiceImpl();//人员查询
			areaService = new AreaServiceImpl();
			List<Area> lArea = areaService.getAll();// 得到所有区域
			//查询人员和区域
			request.setAttribute("list_Area", lArea);//返回给前台显示
			List<M_user> list=userService.getUserByRoleId(4);
			request.setAttribute("list_Area", lArea);//返回给前台显示
			request.setAttribute("list_User", list);//返回给前台显示
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "success";
	}
	
	/**
	 * 工程师与区域关系
	 * @param areaId 区域id
	 * @param userId 用户id
	 * @return
	 */
	public String addReEngineerArea(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("utf-8");
			String areaId = request.getParameter("areaId");
			String userId = request.getParameter("userId");	
			areaId = java.net.URLDecoder.decode(areaId, "UTF-8");
			userId = java.net.URLDecoder.decode(userId, "UTF-8");
			reEngineerAreaService = new ReEngineerAreaServiceImpl();
			ReEngineerArea reEngineerArea=new ReEngineerArea();
			reEngineerArea.setAreaId(areaId);
			reEngineerArea.setUserId(Integer.valueOf(userId));
			reEngineerAreaService.addReEngineerArea(reEngineerArea);
			List<String> list = new ArrayList<String>();
			String result = "区域保存成功！";//用result存放提示信息，并将其传回前台
			list.add(result);//通过json将校验结果传回到前台显示
			JSONArray obj = JSONArray.fromObject(list);
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 修改工程师与区域关系
	 * @param areaId 区域id
	 * @param userId 用户id
	 * @param id 区域主键
	 * @return
	 */
	public String updateReEngineerArea(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			String areaId = request.getParameter("areaId");
			String userId = request.getParameter("userId");	
			id = java.net.URLDecoder.decode(id, "UTF-8");
			areaId = java.net.URLDecoder.decode(areaId, "UTF-8");
			userId = java.net.URLDecoder.decode(userId, "UTF-8");
			reEngineerAreaService = new ReEngineerAreaServiceImpl();
			ReEngineerArea reEngineerArea=new ReEngineerArea();
			reEngineerArea.setId(id);
			reEngineerArea.setUserId(Integer.valueOf(userId));
			reEngineerArea.setAreaId(areaId);
			reEngineerAreaService.UpdateReEngineerArea(reEngineerArea);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除工程师与区域关系
	 * @param id 区域主键
	 * @return
	 */
	public String deleteReEngineerArea(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			id = java.net.URLDecoder.decode(id, "UTF-8");
			reEngineerAreaService = new ReEngineerAreaServiceImpl();
			boolean flag=reEngineerAreaService.deleteReEngineerArea(id);
			System.out.println("areaService.deleteArea:"+flag);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * 通过主键获取工程师与区域关系
	 * @param id 区域主键
	 * @return String
	 */
	public String getById(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			id = java.net.URLDecoder.decode(id, "UTF-8");
			reEngineerAreaService = new ReEngineerAreaServiceImpl();
			ReEngineerArea re= reEngineerAreaService.getById(id);
			List<ReEngineerArea> list=new ArrayList<ReEngineerArea>();
			list.add(re);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", list.size());
			object.put("rows", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * 获取所有工程师与区域关系
	 * @return
	 */
	public String getAll(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			reEngineerAreaService = new ReEngineerAreaServiceImpl();
			List<ReEngineerArea> lArea = reEngineerAreaService.getAll();// 得到所有区域
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", lArea.size());
			object.put("rows", lArea);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	

	/**
	 * 通过区域名称模糊获取工程师与区域关系
	 * @param areaName 区域名称
	 * @return String
	 */
	public String searchByAreaName(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String areaName = request.getParameter("areaName");
			areaName = java.net.URLDecoder.decode(areaName, "UTF-8");
			reEngineerAreaService = new ReEngineerAreaServiceImpl();
			ReEngineerArea re= (ReEngineerArea) reEngineerAreaService.searchByAreaName(areaName);
			List<ReEngineerArea> list=new ArrayList<ReEngineerArea>();
			list.add(re);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", list.size());
			object.put("rows", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * 通过区用户名称模糊获取工程师与区域关系
	 * @param userName 用户名称
	 * @return String
	 */
	public String searchByUserName(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String userName = request.getParameter("userName");
			userName = java.net.URLDecoder.decode(userName, "UTF-8");
			reEngineerAreaService = new ReEngineerAreaServiceImpl();
			ReEngineerArea re= (ReEngineerArea) reEngineerAreaService.searchByUserName(userName);
			List<ReEngineerArea> list=new ArrayList<ReEngineerArea>();
			list.add(re);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", list.size());
			object.put("rows", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	/**
	 * 通过区用户名称模糊获取工程师与区域关系
	 * @param areaId 
	 * @param userId 
	 * @return String
	 */
	public String checkByAreaIdAndUserId(){
		try {
			String result =null;
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String areaId = request.getParameter("areaId");
			String userId = request.getParameter("userId");
			reEngineerAreaService = new ReEngineerAreaServiceImpl();
			List<ReEngineerArea> lArea = reEngineerAreaService.checkByAreaIdAndUserId(areaId,userId);
			if(lArea!=null&&lArea.size()>0){
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
			System.out.println(object.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}

	public IReEngineerAreaService getReEngineerAreaService() {
		return reEngineerAreaService;
	}

	public void setReEngineerAreaService(IReEngineerAreaService reEngineerAreaService) {
		this.reEngineerAreaService = reEngineerAreaService;
	}

	public M_userService getUserService() {
		return userService;
	}

	public void setUserService(M_userService userService) {
		this.userService = userService;
	}
}
