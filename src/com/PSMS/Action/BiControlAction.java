package com.PSMS.Action;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Service.IBiModuleService;
import com.PSMS.Service.IBiPowerStationService;
import com.PSMS.Service.impl.BiModuleServiceImpl;
import com.PSMS.Service.impl.BiPowerStationServiceImpl;
import com.PSMS.pojo.BIPSBaseData;
import com.PSMS.pojo.InParameter;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.GetTime;
import net.sf.json.JSONObject;

/**
 * 控制器
 * @author Andy
 *
 */
public class BiControlAction {
	
	private IBiModuleService biModuleService;
	
	private IBiPowerStationService biPSService;

	public String toBiControl(){
		try {
			
			HttpServletRequest request =ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			biModuleService = new BiModuleServiceImpl();
			biPSService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			int pId=Integer.parseInt(psId);
			JSONObject object = JSONObject.fromObject("{}");
			
			List<PowerStationBase> hourlyData=biPSService.getPSHourlyData(dateTime, pId,"");//dateTime
			object.put("hourlyData", hourlyData);//曲线数据
			
			
			List<Inverter_parameter> parameters=biPSService.getParameter(pId, "控制器");
			object.put("parameters", parameters);//设备基本参数
			
			
			object.put("psId", pId);
			object.put("pageName", "kongzhiqi");
			
			BIPSBaseData newes=biPSService.getNewesData(dateTime, pId);
			object.put("newes", newes);
			request.setAttribute("newes", newes);
			
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			
			if(parameters!=null&&parameters.size()>0){
				request.setAttribute("parameter", parameters.get(0));//设备基本参数
			}
			request.setAttribute("list", object.toString());
			System.out.println(object.toString());
		}catch(IOException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public IBiModuleService getBiModuleService() {
		return biModuleService;
	}

	public void setBiModuleService(IBiModuleService biModuleService) {
		this.biModuleService = biModuleService;
	}

	public IBiPowerStationService getBiPSService() {
		return biPSService;
	}

	public void setBiPSService(IBiPowerStationService biPSService) {
		this.biPSService = biPSService;
	}
	
	
}
