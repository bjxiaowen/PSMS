package com.PSMS.Action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Adapter.inverter_history;
import com.PSMS.Adapter.user;
import com.PSMS.Hibernate.Inverter;
import com.PSMS.Hibernate.M_user;
import com.PSMS.Service.InverterService;
import com.PSMS.Service.InverterServiceImpl;
import com.PSMS.Service.Inverter_parameterService;
import com.PSMS.Service.Inverter_parameterServiceImpl;
import com.PSMS.Service.JB_parameterService;
import com.PSMS.Service.JunctionBoxService;
import com.PSMS.Service.M_userServiceImpl;
import com.PSMS.Service.PM_parameterService;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.PowerMeterService;
import com.PSMS.Service.RoleServiceImpl;
import com.PSMS.Service.WS_parameterService;
import com.PSMS.Service.WeatherStationService;

public class TestAction {
	private PS_informationService ps_informationService ;
	private InverterService inverterService;
	private Inverter_parameterService inverter_parameterService;
	private PowerMeterService powermeterService;
	private PM_parameterService pm_parameterService;
	private WeatherStationService weatherstationService;
	private WS_parameterService ws_parameterService;
	private JunctionBoxService junctionboxService;
	private JB_parameterService jb_parameterService;
	
	public String test1(){
	HttpServletRequest request =ServletActionContext.getRequest();
	HttpServletResponse response =ServletActionContext.getResponse();	
	try {
		request.setCharacterEncoding("utf-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String fromRangeDate = request.getParameter("fromRangeDate");
	String toRangeDate = request.getParameter("toRangeDate");
	String ps_name = request.getParameter("ps_name") ;
	
	try {
		ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//------------------------------以上为获取前台数据，转码
		
		} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	inverterService = new InverterServiceImpl();
	inverter_parameterService = new Inverter_parameterServiceImpl();
	ps_informationService  = new PS_informationServiceImpl();
	
	System.out.println(ps_name+fromRangeDate+toRangeDate);
	int ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站id
	
	List<Integer>  list_parameter_id = inverter_parameterService.getParameterIDByPs_id(ps_id); //根据电站id获得所有属于该电站的逆变器id
	List<String> list_inverter_name = new ArrayList<String>();
	
	//--------------------------------------------------------根据parameter_id得到该设备的历史数据
	
	List<inverter_history> l_inverter_history = new ArrayList<inverter_history>();//最终数据存放list		
	int num=0;//计数器
	for(int i=0;i<list_parameter_id.size();i++){
		String inverter_name  = inverter_parameterService.getParameterNameByParameterID(list_parameter_id.get(i));//根据逆变器编号获得逆变器名称
		List<Inverter> list_inverter_history = inverterService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(i),fromRangeDate,toRangeDate);
		for(int j =0;j<list_inverter_history.size();j++){
			inverter_history ih = new inverter_history();
			num++;
			ih.setId(num);
			ih.setAc_current(list_inverter_history.get(j).getAcCurrent());
			ih.setAc_power(list_inverter_history.get(j).getAcPower());
			ih.setAc_voltage(list_inverter_history.get(j).getAcVoltage());
			ih.setDc_current(list_inverter_history.get(j).getDcCurrent());
			ih.setDc_power(list_inverter_history.get(j).getDcPower());
			ih.setDc_voltage(list_inverter_history.get(j).getDcVoltage());
			ih.setFrequency(list_inverter_history.get(j).getFrequency());
			ih.setInverter_name(inverter_name);
			ih.setState(list_inverter_history.get(j).getState().toString());
			
			ih.setTemperature(list_inverter_history.get(j).getTemperature());
			ih.setTime(list_inverter_history.get(j).getTime());
			l_inverter_history.add(ih);
		}
	}
	//ArrayList list = new ArrayList();
	//list.add(l_inverter_history);
	JSONObject object=JSONObject.fromObject("{}");		
	object.put("aaData", l_inverter_history);
	//String  s = object.toString();
	//s=s.replace("[", "");
	//s=s.replace("]", "");
	
	System.out.println("objectAjax="+object);
	try {
		ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().write(object.toString());
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}

}
