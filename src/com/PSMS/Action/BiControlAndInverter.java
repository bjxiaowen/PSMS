package com.PSMS.Action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Service.IBiPowerStationService;
import com.PSMS.Service.impl.BiPowerStationServiceImpl;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.GetTime;

import net.sf.json.JSONObject;

/**
 * 控逆一体机
 * @author Andy
 *
 */
public class BiControlAndInverter {
	
	
	private IBiPowerStationService biPSService;

	public String toControlAndInverter(){
		try {
			HttpServletRequest request =ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			biPSService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			int pId=Integer.parseInt(psId);
			JSONObject object = JSONObject.fromObject("{}");
			PowerStationBase outData=biPSService.getPSOutOneData(dateTime, pId,"逆变器");
			object.put("outData", outData);//输出
			List<PowerStationBase> hourlyData=biPSService.getPSHourlyData(dateTime, pId,"逆变器");
			object.put("hourlyData", hourlyData);//实时数据
			List<Inverter_parameter> parameters=biPSService.getParameter(pId, "逆变器");
			object.put("parameters", parameters);//设备基本参数
			
			//组件
			PowerStationBase modelData=biPSService.getPSOutOneData(dateTime, pId,"组件");
			object.put("modelData", modelData);
			PowerStationBase modelStatus=biPSService.getNewestStatus(dateTime, pId,"组件");
			object.put("modelStatus", modelStatus);
			
			//蓄电池
			PowerStationBase batteryData=biPSService.getPSOutOneData(dateTime, pId,"蓄电池");
			object.put("batteryData", batteryData);
			
			object.put("psId", pId);
			object.put("yitiji", "yitiji");
			
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
	
	public IBiPowerStationService getBiPSService() {
		return biPSService;
	}

	public void setBiPSService(IBiPowerStationService biPSService) {
		this.biPSService = biPSService;
	}
}
