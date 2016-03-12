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

import com.PSMS.Adapter.stationDeviceListAdapter;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Hibernate.JB_parameter;
import com.PSMS.Hibernate.PM_parameter;
import com.PSMS.Hibernate.WS_parameter;
import com.PSMS.Service.Inverter_parameterService;
import com.PSMS.Service.Inverter_parameterServiceImpl;
import com.PSMS.Service.JB_parameterService;
import com.PSMS.Service.JB_parameterServiceImpl;
import com.PSMS.Service.PM_parameterService;
import com.PSMS.Service.PM_parameterServiceImpl;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.WS_parameterService;
import com.PSMS.Service.WS_parameterServiceImpl;

public class toDeviceListAction {
	
	public String getStationDeviceList(){
		
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ps_id = Integer.parseInt(request.getParameter("ps_id"));//获取从前台传来的ps_id
		Inverter_parameterService IPService = new Inverter_parameterServiceImpl();
		JB_parameterService JBService = new JB_parameterServiceImpl();
		PM_parameterService PMService = new PM_parameterServiceImpl();
		WS_parameterService WSService = new WS_parameterServiceImpl();
		
		List<Inverter_parameter> list_inverter = IPService.getInverterByPsId(ps_id);
		List<JB_parameter> list_jb = JBService.getJBByPsId(ps_id);
		List<PM_parameter> list_pm = PMService.getPMByPsId(ps_id);
		List<WS_parameter> list_ws = WSService.getWSByPsId(ps_id);
		List<stationDeviceListAdapter> list_stationDevice = new ArrayList<stationDeviceListAdapter>();
		int i_length,j_length,p_length,w_length;
		i_length = list_inverter.size();j_length = list_jb.size();p_length = list_pm.size();w_length = list_ws.size();
		int max_length = maxLength(i_length,j_length,p_length,w_length);
		for(int i=0;i<max_length;i++){
			stationDeviceListAdapter s = new stationDeviceListAdapter();
			if(list_inverter.size()>i)
				s.setInverter_name(list_inverter.get(i).getName());
			else s.setInverter_name(null);
			if(list_jb.size()>i)
				s.setJb_name(list_jb.get(i).getName());
			else s.setJb_name(null);
			if(list_pm.size()>i)
				s.setPm_name(list_pm.get(i).getName());
			else s.setPm_name(null);
			if(list_ws.size()>i)
				s.setWs_name(list_ws.get(i).getName());
			else s.setWs_name(null);
			list_stationDevice.add(s);
		}
		List<Integer> list_period_index = IPService.getPeriodIndexByPsId(ps_id);
		int k=0;
		for(int i=0;i<list_period_index.size();i++){
			Integer t=Integer.parseInt(String.valueOf(list_period_index.get(i)));
			for(int j=0;j<t;j++){
				list_stationDevice.get(k).setPeriod_id(i+1);
				list_stationDevice.get(k).setPeriod("第"+(i+1)+"期");
				k++;
			}
			
		}
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", list_stationDevice.size());
		object.put("rows", list_stationDevice);
		
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
	public String getPeriodIndexByPsId(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ps_id = Integer.parseInt(request.getParameter("ps_id"));
		Inverter_parameterService IPService = new Inverter_parameterServiceImpl();
		List<Integer> list_period = IPService.getPeriodIndexByPsId(ps_id);
		List<Integer> list_period_index = new ArrayList<Integer>();
		List<Integer> list_period_merge = new ArrayList<Integer>();
		int n ;
		list_period_index.add(0);
		for(int i=1;i<list_period.size();i++){
			n = 0;
			for(int j=0;j<i;j++){
				n = n + Integer.parseInt(String.valueOf(list_period.get(j)));	
			}
			list_period_index.add(n);
		}
		for(int i=0;i<list_period_index.size();i++){
			int s =Integer.parseInt(String.valueOf(list_period.get(i)));
			list_period_merge.add(s);
		}
		
		ArrayList list = new ArrayList();
		list.add(list_period_index);
		list.add(list_period_merge);
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
	
	public int maxLength(int i,int j,int p,int w){
		int max1=i;int max2=p;
		if(i<j)max1=j;
		if(p<w)max2=w;
		int max;
		if(max1>=max2)max=max1;
		else max=max2;
		return max;
		
	}
	

}
