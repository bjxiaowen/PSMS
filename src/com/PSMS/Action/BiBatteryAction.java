package com.PSMS.Action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Service.IBiBatteryService;
import com.PSMS.Service.IBiModuleService;
import com.PSMS.Service.IBiPowerStationService;
import com.PSMS.Service.Inverter_parameterService;
import com.PSMS.Service.Inverter_parameterServiceImpl;
import com.PSMS.Service.impl.BiBatteryServiceImpl;
import com.PSMS.Service.impl.BiModuleServiceImpl;
import com.PSMS.Service.impl.BiPowerStationServiceImpl;
import com.PSMS.pojo.BIPSBaseData;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.GetTime;
import net.sf.json.JSONObject;
/**
 * 蓄电池
 * @author Andy
 *
 */
public class BiBatteryAction {
	
	private IBiBatteryService biBatteryService;//
	
	private IBiPowerStationService biPSService;
	

	public String toBattery(){
		
		try {
			HttpServletRequest request =ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			biBatteryService = new BiBatteryServiceImpl();
			biPSService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			int pId=Integer.parseInt(psId);
			JSONObject object = JSONObject.fromObject("{}");
			List<Inverter_parameter> parameters=biPSService.getParameter(pId, "蓄电池");
			object.put("parameters", parameters);//设备基本参数
			
			object.put("psId", pId);
			object.put("pageName", "蓄电池");
			if(parameters!=null&&parameters.size()>0){
				request.setAttribute("parameter", parameters.get(0));//设备基本参数
			}
			List<PowerStationBase> hourlyData=biPSService.getPSHourlyData(dateTime, pId);
			object.put("hourlyData", hourlyData);//实时数据
			request.setAttribute("hourlyData", hourlyData);
			BIPSBaseData newes=biPSService.getNewesData(dateTime, pId);
			object.put("newes", newes);
			request.setAttribute("newes", newes);
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
