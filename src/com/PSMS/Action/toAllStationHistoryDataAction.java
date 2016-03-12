/** * * 
* 文件名称: toAllStationHistoryDataAction.java *
* 
* 历史数据，查看电站设备的历史数据 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-1-8 下午6:06:40 *
* * @author min.li & jie.yang & jiaojiao.wang
*/ 
package com.PSMS.Action;

import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.IIOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import com.PSMS.util.GetTime;

import com.PSMS.Adapter.inverter_history;
import com.PSMS.Adapter.junctionbox_history;
import com.PSMS.Adapter.powermeter_history;
import com.PSMS.Adapter.ps_name_list;
import com.PSMS.Adapter.weatherstation_history;
import com.PSMS.Hibernate.Inverter;
import com.PSMS.Hibernate.JunctionBox;
import com.PSMS.Hibernate.PowerMeter;
import com.PSMS.Hibernate.WeatherStation;
import com.PSMS.Service.InverterService;
import com.PSMS.Service.InverterServiceImpl;
import com.PSMS.Service.Inverter_parameterService;
import com.PSMS.Service.Inverter_parameterServiceImpl;
import com.PSMS.Service.JB_parameterService;
import com.PSMS.Service.JB_parameterServiceImpl;
import com.PSMS.Service.JunctionBoxService;
import com.PSMS.Service.JunctionBoxServiceImpl;
import com.PSMS.Service.M_userService;
import com.PSMS.Service.M_userServiceImpl;
import com.PSMS.Service.PM_parameterService;
import com.PSMS.Service.PM_parameterServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.PowerMeterService;
import com.PSMS.Service.PowerMeterServiceImpl;
import com.PSMS.Service.WS_parameterService;
import com.PSMS.Service.WS_parameterServiceImpl;
import com.PSMS.Service.WeatherStationService;
import com.PSMS.Service.WeatherStationServiceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class toAllStationHistoryDataAction {
	/** 
	* 历史数据，查看电站设备的历史数据  *
	* @author min.li & jie.yang & jiaojiao.wang 
	* @date 2014-1-8 
	* @param ps_informationService 
	* @param inverterService
	* @param inverter_parameterService
	* @param powermeterService
	* @param pm_parameterService
	* @param weatherstationService
	* @param ws_parameterService 
	* @param junctionboxService
	* @param jb_parameterService
	*/ 
	private PS_informationService ps_informationService ;
	private InverterService inverterService;
	private Inverter_parameterService inverter_parameterService;
	private PowerMeterService powermeterService;
	private PM_parameterService pm_parameterService;
	private WeatherStationService weatherstationService;
	private WS_parameterService ws_parameterService;
	private JunctionBoxService junctionboxService;
	private JB_parameterService jb_parameterService;
	
	public String toAllStationHistoryData(){
		/** 
		* 获取所有电站名称列表*
		* @author min.li
		* @date 2014-1-2 
		* @param list_station_name 
		* @param ps_list 
		*/
		HttpServletRequest request =ServletActionContext.getRequest();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username = request.getParameter("username") ;		
		try {
			username = java.net.URLDecoder.decode(username, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		ps_informationService  = new PS_informationServiceImpl();
		List<String> list_station_name = new ArrayList<String>();
		List<ps_name_list> ps_list = new ArrayList<ps_name_list>();
		M_userService m_userService = new M_userServiceImpl();
		int Role_id = m_userService.getRoleIdByName(username);
		if(Role_id==1 || Role_id==2){
			list_station_name=	ps_informationService.getAllStationName();//获取所有电站名称	
		}
		else{
			int ps_id  = m_userService.getPsIdByUsername(username);
			String ps_name = ps_informationService.getStationNameById(ps_id);
			list_station_name.add(ps_name);
		}
		for(int i=0;i<list_station_name.size();i++){
			ps_name_list ps_name = new ps_name_list();
			ps_name.setPs_name(list_station_name.get(i));
			ps_name.setPs_num(i+1);
			ps_list.add(ps_name);
		}
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("aaData", ps_list);
		object.put("first_ps", list_station_name.get(0) );
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
	
	public String getInverterHistoryData(){
		/** 
		* 获取某电站的逆变器历史数据*
		* @author min.li
		* @date 2014-1-2 
		* @param fromRangeDate 
		* @param toRangeDate 
		* @param ps_name
		* @param ps_id
		* @param list_parameter_id
		* @param l_inverter_history
		* @param num
		* @param inverter_name
		* @param list_inverter_history
		* @param ih
		*/
		HttpServletRequest request =ServletActionContext.getRequest();	
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
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inverterService = new InverterServiceImpl();
		inverter_parameterService = new Inverter_parameterServiceImpl();
		ps_informationService  = new PS_informationServiceImpl();		
		int ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站id	
		List<Integer>  list_parameter_id = inverter_parameterService.getParameterIDByPs_id(ps_id); //根据电站id获得所有属于该电站的逆变器id
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
				if(list_inverter_history.get(j).getState().equals("0"))
					ih.setState("在线");
				else ih.setState("离线");
				ih.setTemperature(list_inverter_history.get(j).getTemperature());
				ih.setTime(list_inverter_history.get(j).getTime());
				l_inverter_history.add(ih);
			}
		}
		JSONObject object=JSONObject.fromObject("{}");	
		object.put("total", l_inverter_history.size());
		object.put("rows", l_inverter_history);		
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
	
	public String getPowerMeterHistoryData(){
		/** 
		* 获取某电站的电表历史数据*
		* @author min.li
		* @date 2014-1-4 
		* @param fromRangeDate 
		* @param toRangeDate 
		* @param ps_name
		* @param ps_id
		* @param list_parameter_id
		* @param l_powermeter_history
		* @param num
		* @param powermeter_name
		* @param list_powermeter_history
		* @param pmh
		*/
		HttpServletRequest request =ServletActionContext.getRequest();	
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
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		powermeterService = new PowerMeterServiceImpl();
		pm_parameterService = new PM_parameterServiceImpl();
		ps_informationService  = new PS_informationServiceImpl();				
		int ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站id		
		List<Integer> list_parameter_id = pm_parameterService.getParameterIDByPs_id(ps_id); //根据电站id获得所有属于该电站的电表id
		List<powermeter_history> l_powermeter_history = new ArrayList<powermeter_history>();//最终数据存放list
		int num=0;//计数器
		for(int i=0;i<list_parameter_id.size();i++){
			String powermeter_name  = pm_parameterService.getParameterNameByParameterID(list_parameter_id.get(i));//根据电表编号获得电表名称
			List<PowerMeter> list_powermeter_history = powermeterService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(i),fromRangeDate,toRangeDate);
			for(int j =0;j<list_powermeter_history.size();j++){
				powermeter_history pmh = new powermeter_history();
				num++;
				pmh.setId(num);
				pmh.setAcc_power(list_powermeter_history.get(j).getAccPower());
				pmh.setActive_power(list_powermeter_history.get(j).getActivePower());
				pmh.setReactive_power(list_powermeter_history.get(j).getReactivePower());
				pmh.setPower_factor(list_powermeter_history.get(j).getPowerFactor());
				pmh.setAc_current(list_powermeter_history.get(j).getAcCurrent());
				pmh.setAc_voltage(list_powermeter_history.get(j).getAcVoltage());
				pmh.setAc_power(list_powermeter_history.get(j).getAcPower());
				pmh.setPowermeter_name(powermeter_name);
				pmh.setTime(list_powermeter_history.get(j).getTime());
				l_powermeter_history.add(pmh);
			}
		}		
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("rows", l_powermeter_history);
		object.put("total", l_powermeter_history.size());
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
	
	public String getWeatherStationHistoryData(){
		/** 
		* 获取某电站的气象站历史数据*
		* @author jie.yang
		* @date 2014-1-6 
		* @param fromRangeDate 
		* @param toRangeDate 
		* @param ps_name
		* @param ps_id
		* @param list_weatherstation_id
		* @param l_weatherstation_history
		* @param num
		* @param weatherstation_name
		* @param list_weatherstation_history
		* @param wh
		*/
		HttpServletRequest request =ServletActionContext.getRequest();	
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
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		weatherstationService = new WeatherStationServiceImpl();
		ws_parameterService = new WS_parameterServiceImpl();
		ps_informationService  = new PS_informationServiceImpl();				
		int ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站id		
		List<Integer> list_parameter_id = ws_parameterService.getParameterIDByPs_id(ps_id); //根据电站id获得所有属于该电站的气象站id		
		List<weatherstation_history> l_weatherstation_history = new ArrayList<weatherstation_history>();//最终数据存放list
		int num=0;//计数器
		for(int i=0;i<list_parameter_id.size();i++){
			String weatherstation_name  = ws_parameterService.getParameterNameByParameterID(list_parameter_id.get(i));//根据气象站编号获得气象站名称
			List<WeatherStation> list_weatherstation_history = weatherstationService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(i),fromRangeDate,toRangeDate);
			for(int j =0;j<list_weatherstation_history.size();j++){
				weatherstation_history pmh = new weatherstation_history();
				num++;
				pmh.setId(num);
				pmh.setPv_temperature(list_weatherstation_history.get(j).getPvTemperature());
				pmh.setWind_direction(list_weatherstation_history.get(j).getWindDirection());
				pmh.setWind_speed(list_weatherstation_history.get(j).getWindSpeed());
				pmh.setTemperature(list_weatherstation_history.get(j).getTemperature());
				pmh.setIrraditation_value(list_weatherstation_history.get(j).getIrraditationValue());
				pmh.setHumidity(list_weatherstation_history.get(j).getHumidity());
				pmh.setWeatherstation_name(weatherstation_name);
				pmh.setTime(list_weatherstation_history.get(j).getTime());
				l_weatherstation_history.add(pmh);
			}
		}		
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("rows", l_weatherstation_history);
		object.put("total", l_weatherstation_history.size());
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
	
	public String getJunctionBoxHistoryData(){
		/** 
		* 获取某电站的汇流箱历史数据*
		* @author jiaojiao.wang
		* @date 2014-1-8 
		* @param fromRangeDate 
		* @param toRangeDate 
		* @param ps_name
		* @param ps_id
		* @param list_junctionbox_id
		* @param l_junctionbox_history
		* @param num
		* @param junctionbox_name
		* @param list_junctionbox_history
		* @param jbh
		*/
		HttpServletRequest request =ServletActionContext.getRequest();	
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
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		junctionboxService = new JunctionBoxServiceImpl();
		jb_parameterService = new JB_parameterServiceImpl();
		ps_informationService  = new PS_informationServiceImpl();				
		int ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站id		
		List<Integer> list_parameter_id = jb_parameterService.getParameterIDByPs_id(ps_id); //根据电站id获得所有属于该电站的汇流箱id		
		List<junctionbox_history> l_junctionbox_history = new ArrayList<junctionbox_history>();//最终数据存放list
		int num=0;//计数器
		for(int i=0;i<list_parameter_id.size();i++){
			String junctionbox_name  = jb_parameterService.getParameterNameByParameterID(list_parameter_id.get(i));//根据汇流箱编号获得汇流箱名称
			List<JunctionBox> list_junctionbox_history = junctionboxService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(i),fromRangeDate,toRangeDate);
			for(int j =0;j<list_junctionbox_history.size();j++){
				junctionbox_history jbh = new junctionbox_history();
				num++;
				jbh.setId(num);
				jbh.setPv_current(list_junctionbox_history.get(j).getPvCurrent());
				jbh.setPv_unit(list_junctionbox_history.get(j).getPvUnit());
				jbh.setPv_voltage(list_junctionbox_history.get(j).getPvVoltage());
				jbh.setJunctionbox_name(junctionbox_name);
				jbh.setTime(list_junctionbox_history.get(j).getTime());
				l_junctionbox_history.add(jbh);
			}
		}		
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("rows", l_junctionbox_history);
		object.put("total", l_junctionbox_history.size());
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
	
	public String toPsHistoryData(){
		/** 
		* 历史数据界面跳转*
		* @author min.li
		* @date 2014-10-25 
		*/		
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "success";
	}
	
	
	public String exportInverterHistoryData() throws IOException{
		/** 
		* 导出某电站的逆变器历史数据*
		* @author min.li
		* @date 2014-7-19 
		* @param fromRangeDate 
		* @param toRangeDate 
		* @param ps_name
		* @param ps_id
		* @param list_parameter_id
		* @param l_inverter_history
		* @param num
		* @param inverter_name
		* @param list_inverter_history
		* @param ih
		*/
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
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 		
		inverterService = new InverterServiceImpl();
		inverter_parameterService = new Inverter_parameterServiceImpl();
		ps_informationService  = new PS_informationServiceImpl();		
		int ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站id	
		List<Integer>  list_parameter_id = inverter_parameterService.getParameterIDByPs_id(ps_id); //根据电站id获得所有属于该电站的逆变器id
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
				if(list_inverter_history.get(j).getState().equals("0"))
					ih.setState("在线");
				else ih.setState("离线");			
				ih.setTemperature(list_inverter_history.get(j).getTemperature());
				ih.setTime(list_inverter_history.get(j).getTime());
				l_inverter_history.add(ih);
			}
		}	
		//----------------------
        //创建Workbook对象（这一个对象代表着对应的一个Excel文件）   
        //HSSFWorkbook表示以xls为后缀名的文件   
		Workbook wb = new HSSFWorkbook();   
		CreationHelper helper = wb.getCreationHelper();   //创建Sheet并给名字(表示Excel的一个Sheet)   
		Sheet sheet1 = wb.createSheet("Sheet1");          
		Row row = null;   
		Cell cell = null; 
		//设置excel的表头内容
		row = sheet1.createRow(0); row.setHeightInPoints(20);
		int j=0;
		sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j);   
		cell.setCellValue("逆变器名称"); 
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("直流电流(A)"); 
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("直流电压(V)"); 	
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("直流功率(W)");	
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("交流电流(A)"); 
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("交流电压(V)"); 	
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("交流功率(W)");
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("频率(Hz)"); 
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("温度(℃)"); 	
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("状态");
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("时间");
		for(int i=0;i<l_inverter_history.size();i++){   //获得这个sheet的第i行   
			row = sheet1.createRow(i+1);   //  在excel的第二行开始写入数据 
			row.setHeightInPoints(20);    
			//写入excel的每一行的列数据
				j=0;
				sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j);   
				cell.setCellValue(l_inverter_history.get(i).getInverter_name()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_inverter_history.get(i).getAc_current()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_inverter_history.get(i).getAc_voltage()); 	
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_inverter_history.get(i).getAc_power()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_inverter_history.get(i).getDc_current()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_inverter_history.get(i).getDc_voltage()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_inverter_history.get(i).getDc_power()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_inverter_history.get(i).getFrequency()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_inverter_history.get(i).getTemperature()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_inverter_history.get(i).getState()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_inverter_history.get(i).getTime()); 
            
      }   
		//输出  
		
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH)+1; 
		int date = c.get(Calendar.DATE); 
		String backup_time = year+"-"+month+"-"+date;
		String filepath=ServletActionContext.getServletContext().getRealPath("backup");
		
		File file=new File(filepath);
		
		if  (!file .exists())      
		{       
		    System.out.println("//不存在");
		    file.mkdir();
		    File f=new File(filepath+"/"+backup_time+"Inverter.xls");
		    
		    if(!f.exists())    
		    {    
		        try {    
		            f.createNewFile();    
		        } catch (IOException e) {    
		            // TODO Auto-generated catch block    
		            e.printStackTrace();    
		        }    
		    }    
		    
		    
		   
			OutputStream os = new FileOutputStream(file);   
			wb.write(os);   
			os.close(); 
		} else   
		{  
		    System.out.println("//目录存在");  
		}  
		

		
		ArrayList list = new ArrayList();
		list.add("success");//通过json将校验结果传回到前台显示
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
	public void exportPowerMeterHistoryData() throws IOException{
		/** 
		* 导出某电站的电表历史数据*
		* @author min.li
		* @date 2014-7-19 
		* @param fromRangeDate 
		* @param toRangeDate 
		* @param ps_name
		* @param ps_id
		* @param list_parameter_id
		* @param l_inverter_history
		* @param num
		* @param inverter_name
		* @param list_inverter_history
		* @param ih
		*/
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
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		powermeterService = new PowerMeterServiceImpl();
		pm_parameterService = new PM_parameterServiceImpl();
		ps_informationService  = new PS_informationServiceImpl();				
		int ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站id		
		List<Integer> list_parameter_id = pm_parameterService.getParameterIDByPs_id(ps_id); //根据电站id获得所有属于该电站的电表id
		List<powermeter_history> l_powermeter_history = new ArrayList<powermeter_history>();//最终数据存放list
		int num=0;//计数器
		for(int i=0;i<list_parameter_id.size();i++){
			String powermeter_name  = pm_parameterService.getParameterNameByParameterID(list_parameter_id.get(i));//根据电表编号获得电表名称
			List<PowerMeter> list_powermeter_history = powermeterService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(i),fromRangeDate,toRangeDate);
			for(int j =0;j<list_powermeter_history.size();j++){
				powermeter_history pmh = new powermeter_history();
				num++;
				pmh.setId(num);
				pmh.setAcc_power(list_powermeter_history.get(j).getAccPower());
				pmh.setActive_power(list_powermeter_history.get(j).getActivePower());
				pmh.setReactive_power(list_powermeter_history.get(j).getReactivePower());
				pmh.setPower_factor(list_powermeter_history.get(j).getPowerFactor());
				pmh.setAc_current(list_powermeter_history.get(j).getAcCurrent());
				pmh.setAc_voltage(list_powermeter_history.get(j).getAcVoltage());
				pmh.setAc_power(list_powermeter_history.get(j).getAcPower());
				pmh.setPowermeter_name(powermeter_name);
				pmh.setTime(list_powermeter_history.get(j).getTime());
				l_powermeter_history.add(pmh);
			}
		}	
		//----------------------
        //创建Workbook对象（这一个对象代表着对应的一个Excel文件）   
        //HSSFWorkbook表示以xls为后缀名的文件   
		Workbook wb = new HSSFWorkbook();   
		CreationHelper helper = wb.getCreationHelper();   //创建Sheet并给名字(表示Excel的一个Sheet)   
		Sheet sheet1 = wb.createSheet("Sheet1");          
		Row row = null;   
		Cell cell = null; 
		//设置excel的表头内容
		row = sheet1.createRow(0); row.setHeightInPoints(20);
		int j=0;
		sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j);   
		cell.setCellValue("电表名称"); 
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("累计电量(KWh)"); 
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("有功功率(W)"); 	
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("无功功率(W)");	
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("功率因数"); 
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("交流电流(A)"); 	
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("交流电压(V)");
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("交流功率(W)"); 
		
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("时间");
		for(int i=0;i<l_powermeter_history.size();i++){   //获得这个sheet的第i行   
			row = sheet1.createRow(i+1);   //  在excel的第二行开始写入数据 
			row.setHeightInPoints(20);    
			//写入excel的每一行的列数据
				j=0;
				sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j);   
				cell.setCellValue(l_powermeter_history.get(i).getPowermeter_name()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_powermeter_history.get(i).getAcc_power()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_powermeter_history.get(i).getActive_power()); 	
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_powermeter_history.get(i).getReactive_power()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_powermeter_history.get(i).getPower_factor()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_powermeter_history.get(i).getAc_current()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_powermeter_history.get(i).getAc_voltage()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_powermeter_history.get(i).getAc_power()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_powermeter_history.get(i).getTime()); 
				 
            
      }   
		//输出   
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH)+1; 
		int date = c.get(Calendar.DATE); 
		String backup_time = year+"-"+month+"-"+date;
		String filepath=ServletActionContext.getServletContext().getRealPath("backup");
		OutputStream os = new FileOutputStream(new File(filepath+"/"+backup_time+"PowerMeter.xls"));   
		wb.write(os);   
		os.close();  
		
		ArrayList list = new ArrayList();
		list.add("success");//通过json将校验结果传回到前台显示
		JSONArray obj = JSONArray.fromObject(list);
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String exportWSHistoryData()throws IOException{
		/** 
		* 导出某电站的气象站历史数据*
		* @author jie.yang
		* @date 2015-1-6 
		* @param fromRangeDate 
		* @param toRangeDate 
		* @param ps_name
		* @param ps_id
		* @param list_weatherstation_id
		* @param l_weatherstation_history
		* @param num
		* @param weatherstation_name
		* @param list_weatherstation_history
		* @param wh
		*/
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
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		weatherstationService = new WeatherStationServiceImpl();
		ws_parameterService = new WS_parameterServiceImpl();
		ps_informationService  = new PS_informationServiceImpl();				
		int ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站id		
		List<Integer> list_parameter_id = ws_parameterService.getParameterIDByPs_id(ps_id); //根据电站id获得所有属于该电站的气象站id		
		List<weatherstation_history> l_weatherstation_history = new ArrayList<weatherstation_history>();//最终数据存放list
		int num=0;//计数器
		for(int i=0;i<list_parameter_id.size();i++){
			String weatherstation_name  = ws_parameterService.getParameterNameByParameterID(list_parameter_id.get(i));//根据气象站编号获得气象站名称
			List<WeatherStation> list_weatherstation_history = weatherstationService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(i),fromRangeDate,toRangeDate);
			for(int j =0;j<list_weatherstation_history.size();j++){
				weatherstation_history pmh = new weatherstation_history();
				num++;
				pmh.setId(num);
				pmh.setPv_temperature(list_weatherstation_history.get(j).getPvTemperature());
				pmh.setWind_direction(0);
				pmh.setWind_speed(list_weatherstation_history.get(j).getWindSpeed());
				pmh.setTemperature(list_weatherstation_history.get(j).getTemperature());
				pmh.setIrraditation_value(list_weatherstation_history.get(j).getIrraditationValue());
				pmh.setHumidity(0);
				pmh.setWeatherstation_name(weatherstation_name);
				pmh.setTime(list_weatherstation_history.get(j).getTime());
				l_weatherstation_history.add(pmh);
			}
		}		
		//----------------------
        //创建Workbook对象（这一个对象代表着对应的一个Excel文件）   
        //HSSFWorkbook表示以xls为后缀名的文件   
		Workbook wb = new HSSFWorkbook();   
		CreationHelper helper = wb.getCreationHelper();   //创建Sheet并给名字(表示Excel的一个Sheet)   
		Sheet sheet1 = wb.createSheet("Sheet1");          
		Row row = null;   
		Cell cell = null; 
		//设置excel的表头内容
		row = sheet1.createRow(0); row.setHeightInPoints(20);
		int j=0;
		sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j);   
		cell.setCellValue("气象站名称"); 
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("电池板温度(℃)"); 
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("风向"); 	
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("风速（m/s）");	
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("温度(℃)"); 
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("辐射值(W/m²)"); 	
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("湿度");
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("时间"); 
		
		for(int i=0;i<l_weatherstation_history.size();i++){   //获得这个sheet的第i行   
			row = sheet1.createRow(i+1);   //  在excel的第二行开始写入数据 
			row.setHeightInPoints(20);    
			//写入excel的每一行的列数据
				j=0;
				sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j);   
				cell.setCellValue(l_weatherstation_history.get(i).getWeatherstation_name()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_weatherstation_history.get(i).getPv_temperature()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_weatherstation_history.get(i).getWind_direction()); 	
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_weatherstation_history.get(i).getWind_speed()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_weatherstation_history.get(i).getTemperature()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_weatherstation_history.get(i).getIrraditation_value()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_weatherstation_history.get(i).getHumidity()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_weatherstation_history.get(i).getTime()); 
				 
            
      }   
		//输出   
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH)+1; 
		int date = c.get(Calendar.DATE); 
		String backup_time = year+"-"+month+"-"+date;
		String filepath=ServletActionContext.getServletContext().getRealPath("backup");
		OutputStream os = new FileOutputStream(new File(filepath+"/"+backup_time+"WeatherStation.xls"));   
		wb.write(os);   
		os.close(); 
		ArrayList list = new ArrayList();
		list.add("success");//通过json将校验结果传回到前台显示
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
	public String exportJBHistoryData()throws IOException{
		/** 
		* 导出某电站的气象站历史数据*
		* @author jie.yang
		* @date 2015-1-6 
		* @param fromRangeDate 
		* @param toRangeDate 
		* @param ps_name
		* @param ps_id
		* @param list_weatherstation_id
		* @param l_weatherstation_history
		* @param num
		* @param weatherstation_name
		* @param list_weatherstation_history
		* @param wh
		*/
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
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			junctionboxService = new JunctionBoxServiceImpl();
			jb_parameterService = new JB_parameterServiceImpl();
			ps_informationService  = new PS_informationServiceImpl();				
			int ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站id		
			List<Integer> list_parameter_id = jb_parameterService.getParameterIDByPs_id(ps_id); //根据电站id获得所有属于该电站的汇流箱id		
			List<junctionbox_history> l_junctionbox_history = new ArrayList<junctionbox_history>();//最终数据存放list
			int num=0;//计数器
			for(int i=0;i<list_parameter_id.size();i++){
				String junctionbox_name  = jb_parameterService.getParameterNameByParameterID(list_parameter_id.get(i));//根据汇流箱编号获得汇流箱名称
				List<JunctionBox> list_junctionbox_history = junctionboxService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(i),fromRangeDate,toRangeDate);
				for(int j =0;j<list_junctionbox_history.size();j++){
					junctionbox_history jbh = new junctionbox_history();
					num++;
					jbh.setId(num);
					jbh.setPv_current(list_junctionbox_history.get(j).getPvCurrent());
					jbh.setPv_unit(list_junctionbox_history.get(j).getPvUnit());
					jbh.setPv_voltage(list_junctionbox_history.get(j).getPvVoltage());
					jbh.setJunctionbox_name(junctionbox_name);
					jbh.setTime(list_junctionbox_history.get(j).getTime());
					l_junctionbox_history.add(jbh);
				}
			}		
		//----------------------
        //创建Workbook对象（这一个对象代表着对应的一个Excel文件）   
        //HSSFWorkbook表示以xls为后缀名的文件   
		Workbook wb = new HSSFWorkbook();   
		CreationHelper helper = wb.getCreationHelper();   //创建Sheet并给名字(表示Excel的一个Sheet)   
		Sheet sheet1 = wb.createSheet("Sheet1");          
		Row row = null;   
		Cell cell = null; 
		//设置excel的表头内容
		row = sheet1.createRow(0); row.setHeightInPoints(20);
		int j=0;
		sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j);   
		cell.setCellValue("汇流箱名称"); 
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("电流"); 
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("组串单元"); 	
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("电压");	
		j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
		cell.setCellValue("时间"); 
		
		for(int i=0;i<l_junctionbox_history.size();i++){   //获得这个sheet的第i行   
			row = sheet1.createRow(i+1);   //  在excel的第二行开始写入数据 
			row.setHeightInPoints(20);    
			//写入excel的每一行的列数据
				j=0;
				sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j);   
				cell.setCellValue(l_junctionbox_history.get(i).getJunctionbox_name()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_junctionbox_history.get(i).getPv_current()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_junctionbox_history.get(i).getPv_unit()); 	
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_junctionbox_history.get(i).getpv_voltage()); 
				j++; sheet1.autoSizeColumn(j+1, true);cell = row.createCell(j); 
				cell.setCellValue(l_junctionbox_history.get(i).getTime()); 
				 
            
      }   
		//输出   
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH)+1; 
		int date = c.get(Calendar.DATE); 
		String backup_time = year+"-"+month+"-"+date;
		String filepath=ServletActionContext.getServletContext().getRealPath("backup");
		OutputStream os = new FileOutputStream(new File(filepath+"/"+backup_time+"JunctionBox.xls"));   
		wb.write(os);   
		os.close(); 
		
		ArrayList list = new ArrayList();
		list.add("success");//通过json将校验结果传回到前台显示
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
}
