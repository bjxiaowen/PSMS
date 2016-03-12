package com.PSMS.Action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Adapter.comOfInverter;
import com.PSMS.Adapter.inverter_history;
import com.PSMS.Hibernate.Inverter;
import com.PSMS.Service.InverterService;
import com.PSMS.Service.InverterServiceImpl;
import com.PSMS.Service.Inverter_parameterService;
import com.PSMS.Service.Inverter_parameterServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;

public class toComOfInverterAction {
	public String toComOfInverter(){
		return "success";
	}
	public void getInverterCompData(){
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
			InverterService inverterService = new InverterServiceImpl();
			Inverter_parameterService inverter_parameterService = new Inverter_parameterServiceImpl();
			PS_informationService  ps_informationService  = new PS_informationServiceImpl();		
			int ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站id	
			List<Integer>  list_parameter_id = inverter_parameterService.getParameterIDByPs_id(ps_id); //根据电站id获得所有属于该电站的逆变器id	
			List<Inverter> list1 = inverterService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(0),fromRangeDate,toRangeDate);
			List<Inverter> list2 = inverterService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(1),fromRangeDate,toRangeDate);
			int size1=list1.size();
			int size2=list2.size();
			int size=0;
			List<comOfInverter> l_final = new ArrayList<comOfInverter>();//最终数据存放list
			if(size1>=size2)size=size2;else size=size1;
			for(int i=0;i<size;i++){
				comOfInverter inverter_com = new comOfInverter();
				inverter_com.setId(i+1);
				inverter_com.setInverter_name1("3#");
				inverter_com.setInverter_name2("4#");
				inverter_com.setPower1(list1.get(i).getAcVoltage());
				inverter_com.setPower2(list2.get(i).getAcVoltage());
				inverter_com.setPs_name(ps_name);
				inverter_com.setTime1(list1.get(i).getTime());
				inverter_com.setTime2(list2.get(i).getTime());
				l_final.add(inverter_com);
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
	public void getInverterCompData1() throws ParseException{
		HttpServletResponse response =ServletActionContext.getResponse();	
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
			InverterService inverterService = new InverterServiceImpl();
			Inverter_parameterService inverter_parameterService = new Inverter_parameterServiceImpl();
			PS_informationService  ps_informationService  = new PS_informationServiceImpl();		
			int ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站id	
			List<Integer>  list_parameter_id = inverter_parameterService.getParameterIDByPs_id(ps_id); //根据电站id获得所有属于该电站的逆变器id	
			List<String> list_parameter_name=new ArrayList<String>();
			String   parameter_name="";
			for(int i=0;i<list_parameter_id.size();i++){
			parameter_name = inverter_parameterService.getParameterNameByParameterID(list_parameter_id.get(i));
			list_parameter_name.add(parameter_name);
			}
			List<Inverter> list1 = inverterService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(0),fromRangeDate,toRangeDate);
			List<Inverter> list2 = inverterService.searchHistoryDataByDateAndParameterID(list_parameter_id.get(1),fromRangeDate,toRangeDate);
			int size1=list1.size();
			int size2=list2.size();
			int size=0;
			List<comOfInverter> l_final = new ArrayList<comOfInverter>();//最终数据存放list
			if(size1>=size2)size=size2;else size=size1;
			for(int i=0;i<size;i++){
				comOfInverter inverter_com = new comOfInverter();
				inverter_com.setId(i+1);
				inverter_com.setInverter_name1(list_parameter_name.get(0));
				inverter_com.setInverter_name2(list_parameter_name.get(1));
				inverter_com.setPower1(list1.get(i).getAcPower());
				inverter_com.setPower2(list2.get(i).getAcPower());
				inverter_com.setPs_name(ps_name);
				inverter_com.setTime1(list1.get(i).getTime().substring(11, 16));
				inverter_com.setTime2(list2.get(i).getTime().substring(11, 16));
				l_final.add(inverter_com);
			}
			ArrayList list = new ArrayList();
			list.add(l_final);
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
