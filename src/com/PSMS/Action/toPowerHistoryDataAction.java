/** * * 
* 文件名称: toPowerHistoryAction.java *
* 
* 发电量数据，查看电站发电量数据 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-1-12 下午10:36:34 *
* * @author min.li & jie.yang & jiaojiao.wang
*/
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
import com.PSMS.Adapter.history_year_list;
import com.PSMS.Adapter.powerhistory_day;
import com.PSMS.Adapter.powerhistory_month;
import com.PSMS.Adapter.powerhistory_year;
import com.PSMS.Hibernate.HistoryOfDay;
import com.PSMS.Hibernate.HistoryOfMonth;
import com.PSMS.Hibernate.HistoryOfYear;
import com.PSMS.Service.HistoryOfDayService;
import com.PSMS.Service.HistoryOfDayServiceImpl;
import com.PSMS.Service.HistoryOfMonthService;
import com.PSMS.Service.HistoryOfMonthServiceImpl;
import com.PSMS.Service.HistoryOfYearService;
import com.PSMS.Service.HistoryOfYearServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;

public class toPowerHistoryDataAction {
	/** 
	* 发电量数据，查看电站发电量数据 *
	* @author min.li & jie.yang & jiaojiao.wang
	* @date 2014-1-12 
	* @param ps_informationService 
	* @param historyOfYearService
	* @param historyOfMonthService
	* @param historyOfDayService
	*/ 
	PS_informationService ps_informationService;
	HistoryOfYearService historyOfYearService;
	HistoryOfMonthService historyOfMonthService;
	HistoryOfDayService historyOfDayService;
	public String toHistoryOfYear(){
		/** 
		* 获取年发电量数据*
		* @author min.li
		* @date 2014-11-22 
		* @param year 
		* @param list_history_year 
		* @param list_ps_name
		* @param ps_name
		* @param list_power_history_year
		*/
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int year=0;
		if(request.getParameter("year")!=""){
		year  = Integer.parseInt(request.getParameter("year"));//从前台获取年份	
		}
		ps_informationService  = new PS_informationServiceImpl();
		historyOfYearService = new HistoryOfYearServiceImpl();
		List<HistoryOfYear> list_history_year  = historyOfYearService.getDataByYear(year);//根据前台传过来的年份查询该年份所有电站发电量数据
		List<String> list_ps_name = new ArrayList<String>();
		for(int i=0;i<list_history_year.size();i++){
				int ps_id  = list_history_year.get(i).getPS_id();
				String ps_name = ps_informationService.getStationNameById(ps_id);//获得每个ps_id对应的电站名称
				list_ps_name.add(ps_name);
		}
		List<powerhistory_year> list_power_history_year = new ArrayList<powerhistory_year>();
		for(int i=0;i<list_history_year.size();i++){
			powerhistory_year phy = new powerhistory_year();
			phy.setId(i+1);
			phy.setPs_name(list_ps_name.get(i));
			phy.setTotal_hour(list_history_year.get(i).getTotal_hour());
			phy.setTotal_power(list_history_year.get(i).getTotal_power()/1000);
			phy.setYear(list_history_year.get(i).getYear());
			list_power_history_year.add(phy);
		}
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", list_power_history_year.size());
		object.put("rows", list_power_history_year);
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
	
	public String toHistoryOfYear_Chart(){
		/** 
		* 获取年发电量数据柱状图显示*
		* @author min.li
		* @date 2014-11-24 
		* @param year 
		* @param list_history_year 
		* @param list_ps_name
		* @param ps_id
		* @param ps_name
		*/		
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int year=0;
		if(request.getParameter("year")!=""){
		year  = Integer.parseInt(request.getParameter("year"));//从前台获取年份	
		}	
		ps_informationService  = new PS_informationServiceImpl();
		historyOfYearService = new HistoryOfYearServiceImpl();		
		List<HistoryOfYear> list_history_year  = historyOfYearService.getDataByYear(year);//根据前台传过来的年份查询该年份所有电站发电量数据
		List<String> list_ps_name = new ArrayList<String>();
		for(int i=0;i<list_history_year.size();i++){
				int ps_id  = list_history_year.get(i).getPS_id();
				double d = list_history_year.get(i).getTotal_power()/1000;
				list_history_year.get(i).setTotal_power(d);
				String ps_name = ps_informationService.getStationNameById(ps_id);//获得每个ps_id对应的电站名称
				list_ps_name.add(ps_name);
		}
		ArrayList list = new ArrayList();
		list.add(list_history_year);
		list.add(list_ps_name);
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
	
	
	public String toHistoryOfYearPart(){  
		/** 
		* 获取年发电量数据*
		* @author jiaojiao.wang
		* @date 2014-11-28 
		* @param year 
		* @param list_history_year 
		* @param list_ps_name
		* @param ps_name
		* @param list_power_history_year
		*/		
		HttpServletRequest request =ServletActionContext.getRequest();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int year=0;
		if(request.getParameter("year")!=""){
		year  = Integer.parseInt(request.getParameter("year"));//从前台获取年份	
		}String ps_name = request.getParameter("ps_name");//从前台获取电站名称
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");// 以上为获取前台数据，转码
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		ps_informationService  = new PS_informationServiceImpl();
		historyOfYearService = new HistoryOfYearServiceImpl();		
		int ps_id = ps_informationService.getPS_idByName(ps_name);
		List<HistoryOfYear> list_history_year  = historyOfYearService.getDataByYearAndPs_id(year,ps_id);//根据前台传过来的年份查询该年份所有电站发电量数据	
		List<String> list_ps_name = new ArrayList<String>();
		list_ps_name.add(ps_name);		
		List<powerhistory_year> list_power_history_year = new ArrayList<powerhistory_year>();
		for(int i=0;i<list_history_year.size();i++){
			powerhistory_year phy = new powerhistory_year();
			phy.setId(i+1);
			phy.setPs_name(list_ps_name.get(i));
			phy.setTotal_hour(list_history_year.get(i).getTotal_hour());
			phy.setTotal_power(list_history_year.get(i).getTotal_power());
			phy.setYear(list_history_year.get(i).getYear());
			list_power_history_year.add(phy);
		}
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("aaData", list_power_history_year);
		System.out.println(object);
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

	public String getAllHistoryYear(){
		/** 
		* 年发电量中查询所有有数据的年份*
		* @author min.li
		* @date 2014-11-22 
		* @param list_history_year 
		* @param year_list
		*/		
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		historyOfYearService = new HistoryOfYearServiceImpl();
		List<Integer> list_history_year = historyOfYearService.getAllHistoryYear();
		List<history_year_list> year_list = new ArrayList<history_year_list>();
		for(int i=0;i<list_history_year.size();i++){
			history_year_list y_list = new history_year_list();
			y_list.setYear_name(list_history_year.get(i).toString());
			y_list.setYear_num(i+1);
			year_list.add(y_list);
		}			
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("aaData", year_list);
		if(list_history_year.size()==0)object.put("first_year", "" );
		else object.put("first_year", list_history_year.get(0) );
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
	public String getAllMonth(){
		/** 
		* 日发电量中查询所有有数据的月份*
		* @author min.li
		* @date 2014-11-22 
		* @param list_history_year 
		* @param year_list
		*/		
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		historyOfDayService = new HistoryOfDayServiceImpl();
		List<Integer> list_history_month = historyOfDayService.getAllHistoryMonth();
		List<history_year_list> year_list = new ArrayList<history_year_list>();
		for(int i=0;i<list_history_month.size();i++){
			history_year_list y_list = new history_year_list();
			y_list.setYear_name(list_history_month.get(i).toString());
			y_list.setYear_num(i+1);
			year_list.add(y_list);
		}			
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("aaData", year_list);
		if(list_history_month.size()==0)object.put("first_year", "" );
		else object.put("first_year", list_history_month.get(0) );
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

	public String toHistoryOfMonth(){
		/** 
		* 获取月发电量数据*
		* @author jie.yang
		* @date 2014-11-23 
		* @param year 
		* @param list_history_month 
		* @param list_ps_name
		* @param ps_name
		* @param list_power_history_month
		*/		
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int year=0;
		if(request.getParameter("year")!=""){
		year  = Integer.parseInt(request.getParameter("year"));//从前台获取年份	
		}
		String ps_name =request.getParameter("ps_name");
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");// 以上为获取前台数据，转码
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		ps_informationService  = new PS_informationServiceImpl();
		historyOfMonthService = new HistoryOfMonthServiceImpl();		
		int ps_id = ps_informationService.getPS_idByName(ps_name);
		List<HistoryOfMonth> list_history_month  = historyOfMonthService.getDataByPs_idAndYear(ps_id,year);//根据前台传过来的电站名和年份查询该年份所有电站发电量数据
		List<String> list_ps_name = new ArrayList<String>();
		for(int i=0;i<list_history_month.size();i++){
				list_ps_name.add(ps_name);
		}
		List<powerhistory_month> list_power_history_month = new ArrayList<powerhistory_month>();
		for(int i=0;i<list_history_month.size();i++){
			powerhistory_month phm = new powerhistory_month();
			phm.setId(i+1);
			phm.setPs_name(list_ps_name.get(i));
			phm.setTotal_hour(list_history_month.get(i).getTotal_hour());
			phm.setTotal_power(list_history_month.get(i).getTotal_power());
			phm.setYear(list_history_month.get(i).getYear());
			phm.setMonth(list_history_month.get(i).getMonth());
			list_power_history_month.add(phm);
		}
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", list_power_history_month.size());
		object.put("rows", list_power_history_month);
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
	
	public String toHistoryOfMonth_Chart(){
		/** 
		* 获取月发电量数据柱状图显示*
		* @author jie.yang
		* @date 2014-11-23 
		* @param year 
		* @param list_history_month 
		* @param list_ps_name
		* @param ps_name
		*/		
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int year=0;
		if(request.getParameter("year")!=""){
		year  = Integer.parseInt(request.getParameter("year"));//从前台获取年份	
		}
		String ps_name =request.getParameter("ps_name");
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");// 为获取前台数据，转码
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		ps_informationService  = new PS_informationServiceImpl();
		historyOfMonthService = new HistoryOfMonthServiceImpl();		
		int ps_id = ps_informationService.getPS_idByName(ps_name);
		List<HistoryOfMonth> list_history_month  = historyOfMonthService.getDataByPs_idAndYear(ps_id,year);//根据前台传过来的电站名和年份查询该年份所有电站发电量数据
		List<String> list_ps_name = new ArrayList<String>();
		for(int i=0;i<list_history_month.size();i++){
				list_ps_name.add(ps_name);
		}
		ArrayList list = new ArrayList();
		list.add(list_history_month);
		list.add(list_ps_name);
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
	
	
	public String toHistoryOfDay(){
		/** 
		* 获取日发电量数据*
		* @author jiaojiao.wang
		* @date 2014-11-28 
		* @param year 
		* @param month
		* @param list_history_day 
		* @param list_ps_name
		* @param ps_name
		* @param list_power_history_day
		*/		
		HttpServletRequest request =ServletActionContext.getRequest();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		int year = 0;
		int month = Integer.parseInt(request.getParameter("month"));//从前台获取月份
		String syear = request.getParameter("year");
		if(syear!=""){
			 year = Integer.parseInt(syear);//从前台获取年份
		}
		String ps_name =request.getParameter("ps_name");
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");// ------------------------------以上为获取前台数据，转码
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService  = new PS_informationServiceImpl();
		historyOfDayService = new HistoryOfDayServiceImpl();		
		int ps_id = ps_informationService.getPS_idByName(ps_name);		
		List<HistoryOfDay> list_history_day  = historyOfDayService.getDataByMonth(ps_id,year,month);//根据前台传过来的电站名和年份查询该年份所有电站发电量数据		
		List<String> list_ps_name = new ArrayList<String>();
		for(int i=0;i<list_history_day.size();i++){
				list_ps_name.add(ps_name);
		}	
		List<powerhistory_day> list_power_history_day = new ArrayList<powerhistory_day>();
		for(int i=0;i<list_history_day.size();i++){
			powerhistory_day phd = new powerhistory_day();
			phd.setId(i+1);
			phd.setPs_name(list_ps_name.get(i));			
			phd.setDay(list_history_day.get(i).getDay());
			phd.setTotal_power(list_history_day.get(i).getTotal_power());
			phd.setYear(list_history_day.get(i).getYear());
			phd.setDiffuse_radiation(list_history_day.get(i).getDiffuse_radiation());
			phd.setDirect_radiation(list_history_day.get(i).getDirect_radiation());
			phd.setGrid_connection_power(list_history_day.get(i).getGrid_connection_power());
			phd.setMax_irradiation(list_history_day.get(i).getMax_irradiation());
			phd.setMonth(month);
			phd.setPower_consumption(list_history_day.get(i).getPower_consumption());
			phd.setTotal_irradiation(list_history_day.get(i).getTotal_irradiation());			
			list_power_history_day.add(phd);
		}
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", list_power_history_day.size());
		object.put("rows", list_power_history_day);
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
	
	public String toHistoryOfDay_Chart(){
		/** 
		* 获取日发电量数据柱状图显示*
		* @author jiaojiao.wang
		* @date 2014-11-28 
		* @param year 
		* @param month
		* @param list_history_day 
		* @param list_ps_name
		* @param ps_name
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
		int month = Integer.parseInt(request.getParameter("month"));//从前台获取年份		
		String ps_name =request.getParameter("ps_name");
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");// 以上为获取前台数据，转码
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService  = new PS_informationServiceImpl();
		historyOfDayService = new HistoryOfDayServiceImpl();		
		int ps_id = ps_informationService.getPS_idByName(ps_name);		
		List<HistoryOfDay> list_history_day  = historyOfDayService.getDataByMonth(ps_id,year,month);//根据前台传过来的电站名和年份查询该年份所有电站发电量数据		
		List<String> list_ps_name = new ArrayList<String>();
		for(int i=0;i<list_history_day.size();i++){
				list_ps_name.add(ps_name);
		}	
		ArrayList list = new ArrayList();
		list.add(list_history_day);
		list.add(list_ps_name);	
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
	public String getAllHistoryMonth(){
		/** 
		* 月发电量中查询所有电站名称与有数据的年份*
		* @author jie.yang
		* @date 2014-11-24 
		* @param list_station_name
		* @param list_history_year 
		*/		
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		historyOfMonthService = new HistoryOfMonthServiceImpl();
		ps_informationService  = new PS_informationServiceImpl();		
		List<String> list_station_name = ps_informationService.getAllStationName();//获取所有电站名称	
		List<Integer> list_history_year = historyOfMonthService.getAllHistoryYear();
		ArrayList list = new ArrayList();
		list.add(list_station_name);
		list.add(list_history_year);		
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
	
	public String getAllHistoryDay(){
		/** 
		* 日发电量中查询所有电站名称与有数据的年份*
		* @author jie.yang
		* @date 2014-11-24 
		* @param list_station_name
		* @param list_history_year 
		*/		
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		ps_informationService  = new PS_informationServiceImpl();
		historyOfDayService = new HistoryOfDayServiceImpl();		
		List<String> list_station_name = ps_informationService.getAllStationName();//获取所有电站名称	
		List<Integer> list_history_year = historyOfDayService.getAllHistoryYear();//获取表中所有年份		
		ArrayList list = new ArrayList();
		list.add(list_station_name);
		list.add(list_history_year);				
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
	
	/** 
	* 年发电量界面跳转*
	* @author jie.yang
	* @date 2014-1-12  
	*/
	public String toYearPowerHistory(){
		HttpServletRequest request =ServletActionContext.getRequest();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		return "success";
	}
	public String toMonthPowerHistory(){
		/** 
		* 月发电量界面跳转*
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
	public String toDayPowerHistory(){
		/** 
		* 日发电量界面跳转*
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
