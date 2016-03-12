package com.PSMS.Action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Hibernate.Inverter;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Hibernate.PM_parameter;
import com.PSMS.Hibernate.PowerMeter;
import com.PSMS.Hibernate.WS_parameter;
import com.PSMS.Service.InverterService;
import com.PSMS.Service.InverterServiceImpl;
import com.PSMS.Service.Inverter_parameterService;
import com.PSMS.Service.Inverter_parameterServiceImpl;
import com.PSMS.Service.MonitoringService;
import com.PSMS.Service.MonitoringServiceImpl;
import com.PSMS.Service.PM_parameterService;
import com.PSMS.Service.PM_parameterServiceImpl;
import com.PSMS.Service.WS_parameterService;
import com.PSMS.Service.WS_parameterServiceImpl;
import com.PSMS.Service.WeatherStationService;
import com.PSMS.Service.WeatherStationServiceImpl;

public class toRealTimeDataAction {
	
	public String getPMRealTimeDataByPSId(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ps_id = Integer.parseInt(request.getParameter("ps_id"));
		int period_num = Integer.parseInt(request.getParameter("period"));
		String inverter_name = request.getParameter("inverter_name");				
		try {
			inverter_name = java.net.URLDecoder.decode(inverter_name,"UTF-8");										
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MonitoringService monitor = new MonitoringServiceImpl();
		Inverter_parameterService in_para = new Inverter_parameterServiceImpl();
		PM_parameterService pm_para = new PM_parameterServiceImpl();
		List<Inverter_parameter> l_inverter = in_para.getInverterByPs_name(ps_id);//根据电站id获得属于该电站的所有逆变器名称
		List<PM_parameter> l_pm = pm_para.getPMByPs_name(ps_id);
		int pm_parameter_id=0;
		for(int i=0;i<l_inverter.size();i++){
			if(inverter_name.equals(l_inverter.get(i).getName())){
				pm_parameter_id=l_pm.get(i).getId();
			}
		}
		
		List<PowerMeter> pm = monitor.getPMRealTimeData(ps_id,period_num,pm_parameter_id);
		ArrayList list = new ArrayList();
		list.add(pm);
		JSONArray obj = JSONArray.fromObject(list);
		
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getInverterNamesByPSId(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ps_id = Integer.parseInt(request.getParameter("ps_id"));
		int period_num = Integer.parseInt(request.getParameter("period"));
		Inverter_parameterService in_para = new Inverter_parameterServiceImpl();
		
		List<String> l_in_name = in_para.getInverterNamesByPsId(ps_id,period_num);
		ArrayList list = new ArrayList();
		list.add(l_in_name);
		JSONArray obj = JSONArray.fromObject(list);
		
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getInverterRealTimeDataByPSId(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ps_id = Integer.parseInt(request.getParameter("ps_id"));
		int period_num = Integer.parseInt(request.getParameter("period"));
		String inverter_name = request.getParameter("inverter_name");				
		try {
			inverter_name = java.net.URLDecoder.decode(inverter_name,"UTF-8");										
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Inverter_parameterService in_para = new Inverter_parameterServiceImpl();
		InverterService inverterService = new InverterServiceImpl();
		Integer parameter_id = in_para.getParameterIdByPsId(ps_id,period_num,inverter_name);
		//System.out.println(ps_id+"------"+period_num+"--------"+inverter_name);
		WS_parameterService ws_para = new WS_parameterServiceImpl();
		WeatherStationService ws = new WeatherStationServiceImpl();
		List<Inverter_parameter> l_inverter = in_para.getInverterByPs_name(ps_id);//根据电站id获得属于该电站的所有逆变器名称
		List<WS_parameter> l_ws = ws_para.getWSByPs_name(ps_id);
		int ws_parameter_id=0;
		for(int i=0;i<l_inverter.size();i++){
			if(inverter_name.equals(l_inverter.get(i).getName())){
				ws_parameter_id=l_ws.get(0).getId();
			}
		}
		
		//-----------------------以上获取要查询的逆变器id和气象站id----------------------
		Date date=new Date();
		int h = date.getHours();
		List<Double> power = new ArrayList();
		for(int j=0;j<h+1;j++){//获取当天该小时的实时功率和辐射量的值
			
			Double ac_power = inverterService.getZhengDianPowerByParaIdAndHour(parameter_id,j);
			power.add(ac_power);
				
		}
		for(int i=h+1;i<24;i++){
			power.add(0.0);
		}
		//-------------------以上查询获得24小时逆变器功率数据，数据除以1000，得到单位为kw的数据------------
		List<Double> irrad = new ArrayList();
		for(int j=0;j<h+1;j++){//获取当天该小时的实时功率和辐射量的值
			
			Double ac_irrad = ws.getZhengDianAccByParaIdAndHour(ws_parameter_id,j);
			irrad.add(ac_irrad);
				
		}
		for(int i=h+1;i<24;i++){
			irrad.add(0.0);
		}
		List<String> hours = new ArrayList();
		for(int i=0;i<24;i++){
			hours.add(i+"h");
		}
		
		ArrayList list = new ArrayList();
		list.add(power);
		list.add(irrad);
		list.add(hours);
		
		JSONArray obj = JSONArray.fromObject(list);
		//System.out.println(obj);
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
