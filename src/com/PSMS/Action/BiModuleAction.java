package com.PSMS.Action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Service.IBiModuleService;
import com.PSMS.Service.impl.BiIndexServiceImpl;
import com.PSMS.Service.impl.BiModuleServiceImpl;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.GetTime;

import net.sf.json.JSONObject;

/**
 * 组件
 * @author Andy
 *
 */
public class BiModuleAction {
	
	private IBiModuleService biModuleService;

	public String toBiModule(){
		try {
			HttpServletRequest request =ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			biModuleService = new BiModuleServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			int pId=Integer.parseInt(psId);
			JSONObject object = JSONObject.fromObject("{}");
			PowerStationBase moduleBase=biModuleService.getPowerStationDayByDate(dateTime, pId);
			object.put("moduleBase", moduleBase);
			List<PowerStationBase> hourByDate=biModuleService.getPowerStationHourByDate(dateTime, pId);
			object.put("hourByDate", hourByDate);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			request.setAttribute("list", object.toString());
			System.out.println(object.toString());
		}catch(IOException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
