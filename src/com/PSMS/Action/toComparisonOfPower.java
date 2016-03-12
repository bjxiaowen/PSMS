/** * * 
* 文件名称: toCompofPowerAndIrrad.java *
* 
* 数据对比，发电量辐射量天数对比与理论实际发电量天数对比 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-1-12 下午08:21:34 *
* * @author jie.yang & jiaojiao.wang
*/
package com.PSMS.Action;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import com.PSMS.Hibernate.HistoryOfYear;
import com.PSMS.Service.HistoryOfDayService;
import com.PSMS.Service.HistoryOfDayServiceImpl;
import com.PSMS.Service.HistoryOfYearService;
import com.PSMS.Service.HistoryOfYearServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;

public class toComparisonOfPower {
	/** 
	* 数据对比，发电量辐射量天数对比与理论实际发电量天数对比 *
	* @author jie.yang & jiaojiao.wang
	* @date 2014-1-16 
	* @param ps_informationService 
	* @param historyOfYearService
	* @param historyOfDayService
	*/
	PS_informationService ps_informationService;
	HistoryOfYearService historyOfYearService;
	HistoryOfDayService historyOfDayService;
	public String toComparisonOfPower(){
		/** 
		* 理论实际发电量天数对比 *
		* @author jie.yang 
		* @date 2014-12-1 
		* @param year 
		* @param list_history_year
		* @param list_ps_name
		* @param list_ps_actualValue 
		* @param list_ps_theoreticalValue
		* @param ps_id
		* @param ps_actualValue
		*/		
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int year = Integer.parseInt(request.getParameter("year"));//从前台获取年份
		ps_informationService  = new PS_informationServiceImpl();
		historyOfYearService = new HistoryOfYearServiceImpl();		
		List<HistoryOfYear> list_history_year  = historyOfYearService.getDataByYear(year);
		List<String> list_ps_name = new ArrayList<String>(); 
		List<Double>list_ps_actualValue=new ArrayList<Double>(); //存放电站年实际发电量
		List<Double>list_ps_theoreticalValue=new ArrayList<Double>(); //存放电站年理论发电量	
		for(int i=0;i<list_history_year.size();i++){
				int ps_id  = list_history_year.get(i).getPS_id();
				String ps_name = ps_informationService.getStationNameById(ps_id);
				list_ps_name.add(ps_name);
				double ps_actualValue=list_history_year.get(i).getTotal_power()/1000;
				list_ps_actualValue.add(ps_actualValue);
				String ps_theoreticalValue_s=ps_informationService.getCapacityById(ps_id);//根据电站ID获取其装机容量，即理论发电量
				Double ps_theoreticalValue=Double.parseDouble(ps_theoreticalValue_s);
				list_ps_theoreticalValue.add(ps_theoreticalValue);
				
		}	
		ArrayList list = new ArrayList();
		list.add(list_ps_name);
		list.add(list_ps_theoreticalValue);
		list.add(list_ps_actualValue);		
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
	public String toComparisonOfPower2(){
		/** 
		* 实际发电量天数对比 *
		* @author jie.yang 
		* @date 2014-12-1 
		* @param year 
		* @param list_history_year
		* @param list_table
		* @param list_count 
		* @param count
		*/
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		int year = Integer.parseInt(request.getParameter("year"));//从前台获取年份
		ps_informationService  = new PS_informationServiceImpl();
		historyOfYearService = new HistoryOfYearServiceImpl();		
		List<HistoryOfYear> list_history_year  = historyOfYearService.getDataByYear(year);
		List<String> list_lable = new ArrayList<String>(); 
		List<Integer>list_count=new ArrayList<Integer>();
		int count[]=new int[8];
		for(int i=0;i<list_history_year.size();i++){
				double power=list_history_year.get(i).getTotal_power();
				if(power>=0&&power<100){count[0]++;}
				else if(power>=100&&power<200){count[1]++;}
				else if(power>=200&&power<300){count[2]++;}
				else if(power>=300&&power<400){count[3]++;}
				else if(power>=400&&power<500){count[4]++;}
				else if(power>=500&&power<600){count[5]++;}
				else if(power>=600&&power<700){count[6]++;}
				else if(power>=700){count[7]++;}				
		}
		list_lable.add("0~100KM");
		list_lable.add("100KM~200KM");
		list_lable.add("200KM~300KM");
		list_lable.add("300KM~400KM");
		list_lable.add("400KM~500KM");
		list_lable.add("500KM~600KM");
		list_lable.add("600KM~700KM");
		list_lable.add("700KM以上");
		for (int i=0;i<8;i++){
			list_count.add(count[i]);
			System.out.println(count[i]);
		}
		ArrayList list = new ArrayList();
		list.add(list_lable);
		list.add(list_count);		
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
	public String toComparisonOfPower3(){
		/** 
		* 发电量天数对比 *
		* @author jie.yang 
		* @date 2014-12-1 
		* @param year 
		* @param ps_name
		* @param ps_id
		* @param list_history_day_power 
		* @param list_lable
		* @param list_count 
		* @param count
		* @param sum
		* @param percentage
		* @param percentage1
		*/		
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		int year = Integer.parseInt(request.getParameter("year"));
		String ps_name= request.getParameter("h_ps_name");
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService  = new PS_informationServiceImpl();
		historyOfDayService = new HistoryOfDayServiceImpl();
		int ps_id=ps_informationService.getPS_idByName(ps_name);
		List<Double> list_history_day_power= historyOfDayService.getPowerDataByYearAndPSid(year,ps_id);
		if(list_history_day_power==null){
			System.out.println("发电量无");
		}
		List<String> list_lable = new ArrayList<String>(); 
		List<Double>list_count=new ArrayList<Double>();
		int count[]=new int[5];	
		for(int i=0;i<list_history_day_power.size();i++){
				double power=list_history_day_power.get(i).doubleValue();
				if(power>=0&&power<5){count[0]++;}
				else if(power>=5&&power<10){count[1]++;}
				else if(power>=10&&power<15){count[2]++;}
				else if(power>=15&&power<20){count[3]++;}
				else if(power>=20){count[4]++;}				
		}
		list_lable.add("0~5KW	"+count[0]+"天");
		list_lable.add("5~10KW	"+count[1]+"天");
		list_lable.add("10~15KW	"+count[2]+"天");
		list_lable.add("15~20KW	"+count[3]+"天");	
		list_lable.add("20KW以上	"+count[4]+"天");
		double sum = count[0]+count[1]+count[2]+count[3]+count[4];
		for (int i=0;i<5;i++){
			Double	percentage = (double) ((count[i]/sum)*100);
			Double percentage1 = Math.ceil(percentage*100+.5)/100;
			
			list_count.add(percentage1);
		}
		ArrayList list = new ArrayList();
		list.add(list_lable);
		list.add(list_count);		
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
	
	public String toComparisonOfPower4(){
		/** 
		* 辐射量天数对比 *
		* @author jiaojiao.wang 
		* @date 2014-12-5 
		* @param year 
		* @param ps_name
		* @param ps_id
		* @param list_history_day_irradiation 
		* @param list_lable
		* @param list_count 
		* @param count
		* @param sum
		* @param percentage
		* @param percentage1
		*/
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		int year = Integer.parseInt(request.getParameter("year"));
		String ps_name= request.getParameter("h_ps_name");
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService  = new PS_informationServiceImpl();
		historyOfDayService = new HistoryOfDayServiceImpl();
		int ps_id=ps_informationService.getPS_idByName(ps_name);		
		List<Double> list_history_day_irradiation= historyOfDayService.getIrradiationDataByYearAndPSid(year,ps_id);
		if(list_history_day_irradiation==null){
			System.out.println("无辐射量数据"); 
		}
		List<String> list_lable = new ArrayList<String>(); 
		List<Double>list_count=new ArrayList<Double>();
		int count[]=new int[5];			
		for(int i=0;i<list_history_day_irradiation.size();i++){
				double irradiation=list_history_day_irradiation.get(i).doubleValue();
				if(irradiation>=0&&irradiation<10){count[0]++;}
				else if(irradiation>=10&&irradiation<20){count[1]++;}
				else if(irradiation>=20&&irradiation<30){count[2]++;}
				else if(irradiation>=30&&irradiation<40){count[3]++;}
				else if(irradiation>=40){count[4]++;}				
		}
		list_lable.add("0~10Wh	"+count[0]+"天");
		list_lable.add("10~20Wh	"+count[1]+"天");
		list_lable.add("20~30Wh	"+count[2]+"天");
		list_lable.add("30~40Wh	"+count[3]+"天");	
		list_lable.add("40Wh以上	"+count[4]+"天");
		double sum = count[0]+count[1]+count[2]+count[3]+count[4];
		for (int i=0;i<5;i++){
			Double	percentage = (double) ((count[i]/sum)*100);
			Double percentage1 = Math.ceil(percentage*100+.5)/100;
			
			list_count.add(percentage1);
		}
		ArrayList list = new ArrayList();
		list.add(list_lable);
		list.add(list_count);		
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
	
	public String toCompHistoryDayPage(){
		/** 
		* 发电量辐射量天数对比界面跳转*
		* @author jie.yang
		* @date 2014-1-12  
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
	public String toCompTheoreticalAndActualPage(){
		/** 
		* 理论实际发电量天数对比界面跳转*
		* @author jie.yang
		* @date 2014-1-12  
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
}

