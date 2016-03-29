package com.PSMS.Action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.PSMS.Service.IBiPowerStationService;
import com.PSMS.Service.impl.BiPowerStationServiceImpl;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.GetTime;
import net.sf.json.JSONObject;
public class BiPowerStationAction {
	
	private IBiPowerStationService biPowerStationService;
	
	/**
	 * 组件
	 * @return
	 */
	public String toBiCharts(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			int pId=Integer.parseInt(psId);
			PowerStationBase total=biPowerStationService.getPowerStationDayByDate(dateTime,pId);
			List<PowerStationBase> list=biPowerStationService.getPowerStationHourByDate(dateTime,pId);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", total);
			object.put("list", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "success";
	}
	
	/**
	 * 蓄电池
	 * @return
	 */
	public String getBatteryVoltage(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			int pId=Integer.parseInt(psId);
			PowerStationBase total=biPowerStationService.getBatteryVoltageDayByDate(dateTime,pId);
			List<PowerStationBase> list=biPowerStationService.getBatteryVoltageHourByDate(dateTime,pId);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", total);
			object.put("list", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	/**
	 * 组件
	 * @return
	 */
	public String getModule(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			int pId=Integer.parseInt(psId);
			PowerStationBase total=biPowerStationService.getPowerStationDayByDate(dateTime,pId);
			List<PowerStationBase> list=biPowerStationService.getPowerStationHourByDate(dateTime,pId);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", total);
			object.put("list", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	

}