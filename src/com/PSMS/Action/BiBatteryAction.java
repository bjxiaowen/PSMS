package com.PSMS.Action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.PSMS.Service.IBiBatteryService;
import com.PSMS.Service.impl.BiBatteryServiceImpl;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.GetTime;
import net.sf.json.JSONObject;

public class BiBatteryAction {
	
	private IBiBatteryService biBatteryService;

	public String toBattery(){
		try {
			HttpServletRequest request =ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			biBatteryService = new BiBatteryServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			int pId=Integer.parseInt(psId);
			JSONObject object = JSONObject.fromObject("{}");
			PowerStationBase batteryData=biBatteryService.getBatteryNewestDate(dateTime, pId);
			object.put("batteryData", batteryData);
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

	public IBiBatteryService getBiBatteryService() {
		return biBatteryService;
	}

	public void setBiBatteryService(IBiBatteryService biBatteryService) {
		this.biBatteryService = biBatteryService;
	}
	
	
}
