package com.PSMS.Action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.PSMS.Service.IAreaService;
import com.PSMS.Service.impl.AreaServiceImpl;
import com.PSMS.pojo.Area;
import com.PSMS.util.IDGenerate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 区域信息
 * @author Andy
 *
 */
public class AreaAction {
	
	private IAreaService areaService;
	
	/** 
	* 区域
	* @author andy
	* @date 2016-3-5 
	*/
	public String toRegionList(){
		HttpServletRequest request =ServletActionContext.getRequest();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}	
		return "success";
	}
	
	/**
	 * 得到所有区域
	 * @return
	 */
	public String getRegionList(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			areaService = new AreaServiceImpl();
			List<Area> lArea = areaService.getAll();// 得到所有区域
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
	 * 添加区域
	 * @return
	 */
	public String addRegion(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("utf-8");
			String areaCode = request.getParameter("areaCode");
			String areaName = request.getParameter("areaName");	
			areaCode = java.net.URLDecoder.decode(areaCode, "UTF-8");
			areaName = java.net.URLDecoder.decode(areaName, "UTF-8");
			areaService = new AreaServiceImpl();
			Area area=new Area();
			area.setAreaId(IDGenerate.getId()+"");
			area.setAreaCode(areaCode);
			area.setAreaName(areaName);
			areaService.addArea(area);
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
	 * 修改区域
	 * @return
	 */
	public String updateRegion(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String areaId = request.getParameter("areaId");
			String areaCode = request.getParameter("areaCode");
			String areaName = request.getParameter("areaName");	
			areaCode = java.net.URLDecoder.decode(areaCode, "UTF-8");
			areaName = java.net.URLDecoder.decode(areaName, "UTF-8");
			areaService = new AreaServiceImpl();
			Area area=new Area();
			area.setAreaId(areaId);
			area.setAreaCode(areaCode);
			area.setAreaName(areaName);
			areaService.UpdateArea(area);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 得到所有区域
	 * @return
	 */
	public String getRegion(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String areaId = request.getParameter("areaId");
			areaService = new AreaServiceImpl();
			Area lArea = areaService.getById(areaId);// 得到所有区域
			List<Area> list=new ArrayList<Area>();
			list.add(lArea);
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
	 * 得到所有区域
	 * @return
	 */
	public String queryByCode(){
		try {
			String result =null;
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String areaCode = request.getParameter("areaCode");
			areaService = new AreaServiceImpl();
			List<Area> lArea = areaService.queryByCode(areaCode);
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
	
	/**
	 * 得到所有区域
	 * @return
	 */
	public String deleteRegion(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String areaId = request.getParameter("areaId");
			areaService = new AreaServiceImpl();
			boolean flag=areaService.deleteArea(areaId);
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
	
	public String queryByCodeAndName(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			String areaCode = request.getParameter("areaCode");
			String  areaName= request.getParameter("areaName");
			areaService = new AreaServiceImpl();
			List<Area> list=null;
			if(areaCode!=null&&areaName!=null){
				list=areaService.queryByCodeAndName(areaCode, areaName);
			}else if(areaCode==null&&areaName!=null){
				list=areaService.queryByName(areaName);
			}else if(areaCode!=null&&areaName==null){
				list=areaService.queryByCode(areaCode);
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
	

	public IAreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(IAreaService areaService) {
		this.areaService = areaService;
	}
}
