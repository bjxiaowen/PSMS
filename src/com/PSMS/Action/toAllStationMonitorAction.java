/** * * 
* 文件名称: toAllStationMonitorAction.java *
* 
* 电站地图信息显示 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-10 下午6:27:40 *
* * @author jie.yang 
*/ 
package com.PSMS.Action;

import java.io.IOException;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Adapter.monitor_dataAdapter;
import com.PSMS.Adapter.stationMonitorAdapter;
import com.PSMS.Hibernate.PS_information;
import com.PSMS.Service.HistoryOfDayService;
import com.PSMS.Service.HistoryOfDayServiceImpl;
import com.PSMS.Service.HistoryOfYearService;
import com.PSMS.Service.HistoryOfYearServiceImpl;
import com.PSMS.Service.InverterService;
import com.PSMS.Service.InverterServiceImpl;
import com.PSMS.Service.Inverter_parameterService;
import com.PSMS.Service.Inverter_parameterServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.PowerMeterService;
import com.PSMS.Service.WS_parameterService;
import com.PSMS.Service.WS_parameterServiceImpl;
import com.PSMS.Service.WeatherStationService;
import com.PSMS.Service.WeatherStationServiceImpl;

public class toAllStationMonitorAction {
	/** 
	* 加载地图电站具体实时信息 *
	* @author jie.yang  
	* @date 2014-12-10 
	* @param ps_informationService 
	* @param inverterParameterService
	* 
	* @param inverterService
	* @param weatherStationService
	* @param powerMeterService
	* @param wsParameterService
	* @param historyOfYearService 
	* @param historyOfDayService
	* @param list_ps_information
	* @param list_top 
	* @param s
	* @param ps_id
	* @param list_inverter_parameter_id
	* @param inverterPower 
	* @param parameter_id
	* @param power
	* @param inverter_name
	* @param list_ws_parameter_id 
	* @param irradiationValue
	* @param tempValue
	* @param iValue
	* @param temp
	* @param dayAccPower
	* @param onLineHour
	* @param list
	*/ 
	PS_informationService ps_informationService ;
	Inverter_parameterService inverterParameterService;
	InverterService inverterService;
	WeatherStationService weatherStationService;
	PowerMeterService powerMeterService;
	WS_parameterService wsParameterService;
	HistoryOfYearService historyOfYearService;
	HistoryOfDayService historyOfDayService;
	
	
	public String getAllStationMonitor(){

		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();		
		inverterParameterService = new Inverter_parameterServiceImpl();
		inverterService = new InverterServiceImpl();
		weatherStationService = new WeatherStationServiceImpl();
		wsParameterService = new WS_parameterServiceImpl();
		historyOfDayService  = new HistoryOfDayServiceImpl();		
		List<PS_information> list_ps_information = ps_informationService.getAllStation();//获取所有电站信息
		List<stationMonitorAdapter> list_top = new ArrayList<stationMonitorAdapter>();//存储各个电站实时数据
		for(int i=0;i<list_ps_information.size();i++){
			stationMonitorAdapter s = new stationMonitorAdapter();//初始化一个实例
			int ps_id = list_ps_information.get(i).getId();
			List<Integer> list_inverter_parameter_id = inverterParameterService.getParameterIDByPs_id(ps_id);
			if(list_inverter_parameter_id.size()==0){//如果电站查不到逆变器编号
				s.setInverterPower("逆变器无");
				s.setState("无逆变器状态");				
			}
			else {
				String inverterPower ="";
				for(int j=0;j<list_inverter_parameter_id.size();j++){
					int parameter_id  = list_inverter_parameter_id.get(j);
					String power = inverterService.searchTopPowerByParameter_id(parameter_id);//得到每个逆变器的实时功率
					
					String inverter_name = inverterParameterService.getParameterNameByParameterID(parameter_id);//得到每个逆变器的名称
					inverterPower = inverterPower + "逆变器" + inverter_name + "功率:" + power + "W   ";
				}
				s.setInverterPower(inverterPower);				
			}//获取逆变器交流功率实时数据			
			List<Integer> list_ws_parameter_id = wsParameterService.getParameterIDByPs_id(ps_id);
			if(list_ws_parameter_id.size()==0){
				s.setIrradiationValue("辐照度未知");
				s.setTemperature("当前气温未知");//用天气预报代替
			}
			else{
				String irradiationValue = "";
				String tempValue = "";
				for(int j=0;j<list_ws_parameter_id.size();j++){
					int ws_id = list_ws_parameter_id.get(j);
					String iValue = weatherStationService.searchTopFZLByParameter_id(ws_id);
					String temp = weatherStationService.searchTopTempByParameter_id(ws_id);
					String ws_name = wsParameterService.getNameById(ws_id);
					irradiationValue = irradiationValue +"气象站" + ws_name + "辐照度:" + iValue + " W/m²   ";
					tempValue = tempValue  +"气象站" + ws_name + "温度:" + temp + "℃   ";
				}
				s.setIrradiationValue(irradiationValue);
				s.setTemperature(tempValue);
			}//获取气象站实时数据
			
			double dayAccPower = historyOfDayService.getTodayAccPowerByPsID(ps_id);//获取实时当日发电量
			String onLineHour = historyOfDayService.getTodayOnLineHourByPsID(ps_id);//获取实时当日上网小时数
			
			if(dayAccPower==0){
				s.setAccPower("当日发电量0Kwh");
			}
			else{
				BigDecimal   b   =   new   BigDecimal(dayAccPower);  
				double  D_power   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); //转换成两位小数 
				
				s.setAccPower("当日发电量："+D_power+"Kwh ");
			}			
			
			s.setActivePower("并网电压: 未知");
			if(list_ps_information.get(i).getId()==80 || list_ps_information.get(i).getId()==92)s.setState("电站状态：在线");
			else s.setState("电站状态：离线");
			list_top.add(s);
		}
		ArrayList list = new ArrayList();
		list.add(list_top);
		list.add(list_ps_information);
		JSONArray obj = JSONArray.fromObject(list);
		
		try {
			response.setHeader("Cache-Control","no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getWholeData(){
		/** 
		* 电站实时概况数据加载 *
		* @author jie.yang  
		* @date 2014-12-10 
		* @param ps_num 
		* @param ps_year
		* @param ps_day
		* @param irad_day
		* @param l_data
		* @param m1
		* @param m2
		* @param m3
		* @param m4 
		*/
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ps_informationService = new PS_informationServiceImpl();
		historyOfYearService = new HistoryOfYearServiceImpl();
		historyOfDayService = new HistoryOfDayServiceImpl();
		int ps_num = ps_informationService.getPSNum();
		double ps_year = historyOfYearService.getSumPowerOfYear();
		if(ps_year!=0){
			ps_year  = ps_year / 1000;
		}
		
		BigDecimal   b   =   new   BigDecimal(ps_year);  
		double  Power_year   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); //转换成两位小数 
		double ps_day = historyOfDayService.getSumPowerOfDay();
		BigDecimal   b1   =   new   BigDecimal(ps_day);  
		double  Power_day   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); //转换成两位小数 
		double irad_day = historyOfDayService.getSumIradOfDay();		
		List<monitor_dataAdapter> l_data = new ArrayList<monitor_dataAdapter>();
		monitor_dataAdapter  m1 = new monitor_dataAdapter();
		monitor_dataAdapter  m2 = new monitor_dataAdapter();
		monitor_dataAdapter  m3 = new monitor_dataAdapter();
		monitor_dataAdapter  m4 = new monitor_dataAdapter();
		m1.setName("当前电站总数");m1.setValue(ps_num+"");m1.setGroup("电站实时概况"); //m1.setEditor("text");
		l_data.add(m1);
		m2.setName("年电站发电量(Mwh)");m2.setValue(Power_year+"");m2.setGroup("电站实时概况");//m2.setEditor("text");
		l_data.add(m2);
		m3.setName("日电站发电量(Kwh)");m3.setValue(Power_day+"");m3.setGroup("电站实时概况");//m3.setEditor("text");
		l_data.add(m3);
		m4.setName("日电站辐射量(W/m²)");m4.setValue(irad_day+"");m4.setGroup("电站实时概况");//m4.setEditor("text");
		l_data.add(m4);
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", l_data.size());
		object.put("rows", l_data);
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
