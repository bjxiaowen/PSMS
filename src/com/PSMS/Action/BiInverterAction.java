package com.PSMS.Action;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Service.IBiPowerStationService;
import com.PSMS.Service.impl.BiPowerStationServiceImpl;
import com.PSMS.pojo.InParameter;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.GetTime;

import net.sf.json.JSONObject;

/**
 * 图表逆变器
 * @author Andy
 *
 */
public class BiInverterAction {
	
	private IBiPowerStationService biPSService;

	public String toBiInverter(){
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
			
			PowerStationBase ControlData=biPSService.getPSOutOneData(dateTime, pId,"控制器");
			object.put("ControlData", ControlData);//输出
			
			PowerStationBase ControlStatus=biPSService.getNewestStatus(dateTime, pId,"控制器");
			object.put("ControlStatus", ControlStatus);
			
			object.put("psId", pId);
			object.put("nibianqi", "nibianqi");
			
			InParameter inParameter=biPSService.getInParameter(dateTime, pId);//输入参数
			
			if(parameters!=null&&parameters.size()>0){
				request.setAttribute("parameter", parameters.get(0));//设备基本参数
			}
			request.setAttribute("inParameter", inParameter);
			request.setAttribute("outData", outData);//输出
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
