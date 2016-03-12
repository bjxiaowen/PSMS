package com.PSMS.Action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Adapter.comOfInverter;
import com.PSMS.Adapter.comOfWS;
import com.PSMS.Hibernate.Inverter;
import com.PSMS.Hibernate.WeatherStation;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.WS_parameterService;
import com.PSMS.Service.WS_parameterServiceImpl;
import com.PSMS.Service.WeatherStationService;
import com.PSMS.Service.WeatherStationServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class toCompOfWeatherStaionAction {
	public String toCompOfWeatherStaion(){
		return "success";
	}
	public void getWSCompData(){
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
			WeatherStationService weatherstationService = new WeatherStationServiceImpl();
			WS_parameterService ws_parameterService = new WS_parameterServiceImpl();
			PS_informationService ps_informationService  = new PS_informationServiceImpl();				
		int ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站id		
		List<Integer> list_parameter_id = ws_parameterService.getParameterIDByPs_id(ps_id); //根据电站id获得所有属于该电站的气象站id		
		List<comOfWS> l_final = new ArrayList<comOfWS>();//最终数据存放list
		List<WeatherStation> list1 = weatherstationService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(0),fromRangeDate,toRangeDate);
		List<WeatherStation> list2 = weatherstationService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(1),fromRangeDate,toRangeDate);
		int size1=list1.size();
		int size2=list2.size();
		int size=0;
		
		if(size1>=size2)size=size2;else size=size1;
		for(int i=0;i<size;i++){
			comOfWS ws_com = new comOfWS();
			ws_com.setId(i+1);
			ws_com.setWs_name1("1#");
			ws_com.setWs_name2("2#");
			ws_com.setIrrad1(list1.get(i).getIrraditationValue());
			ws_com.setIrrad2(list2.get(i).getIrraditationValue());
			ws_com.setPs_name(ps_name);
			ws_com.setTime1(list1.get(i).getTime());
			ws_com.setTime2(list2.get(i).getTime());
			l_final.add(ws_com);
		}
		JSONObject object=JSONObject.fromObject("{}");	
		object.put("total", l_final.size());
		object.put("rows", l_final);		
		try {
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getWSCompData1(){
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
			WeatherStationService weatherstationService = new WeatherStationServiceImpl();
			WS_parameterService ws_parameterService = new WS_parameterServiceImpl();
			PS_informationService ps_informationService  = new PS_informationServiceImpl();				
		int ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站id		
		List<Integer> list_parameter_id = ws_parameterService.getParameterIDByPs_id(ps_id); //根据电站id获得所有属于该电站的气象站id
		int ws_number=list_parameter_id.size();
		List<String> list_name = new ArrayList();
		for(int i=0;i<ws_number;i++)
		{
			list_name.add(ws_parameterService.getNameById(list_parameter_id.get(i)));
		}
		//List<comOfWS> l_final = new ArrayList<comOfWS>();//最终数据存放list
		ArrayList list = new ArrayList();
		list.add(list_name);
		for(int i=0;i<ws_number;i++)
		{
		    List<WeatherStation> list1 = weatherstationService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(i),fromRangeDate,toRangeDate);
		    //List<WeatherStation> list2 = weatherstationService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(1),fromRangeDate,toRangeDate);
		    //List<String> list_weatherstation = ws_parameterService.getWSnameByWs_id(list_parameter_id.get(i));
		    list.add(list1);
		}
		JSONArray obj=JSONArray.fromObject(list);	
		try {
			response.setHeader("Cache-Control","no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
