/** * * 
* 文件名称: toGuestPageAction.java *
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Adapter.monitor_dataAdapter;
import com.PSMS.Hibernate.HistoryOfDay;
import com.PSMS.Hibernate.HistoryOfYear;
import com.PSMS.Hibernate.PS_information;
import com.PSMS.Service.HistoryOfDayService;
import com.PSMS.Service.HistoryOfDayServiceImpl;
import com.PSMS.Service.HistoryOfYearService;
import com.PSMS.Service.HistoryOfYearServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.PS_pictureService;
import com.PSMS.Service.PS_pictureServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/** 
* 电站概览 *
* @author jie.yang  
* @date 2014-12-10 
* @param id 
* @param name
* @param PS_id
* @param capacity
* @param area
* @param Part_num
* @param owner 
* @param investor
* @param province
* @param longitude 
* @param latitude
* @param Build_time
* @param ps_informationService
* @param historyOfYearService 
* @param historyOfDayService
* @param psPictureService
*/ 
public class toGuestPageAction {
	
	private Integer id;
	private String name;
	private String capacity;
	private String area;
	private Integer Part_num;
	private String owner;
	private String investor;
	private String province;
	private String longitude;
	private String latitude;
	private String Build_time;
	
	private PS_informationService	ps_informationService;
	HistoryOfYearService historyOfYearService;
	HistoryOfDayService historyOfDayService;	
	PS_pictureService psPictureService;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPart_num() {
		return Part_num;
	}
	public void setPart_num(Integer part_num) {
		Part_num = part_num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getInvestor() {
		return investor;
	}
	public void setInvestor(String investor) {
		this.investor = investor;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getBuild_time() {
		return Build_time;
	}
	public void setBuild_time(String build_time) {
		Build_time = build_time;
	}

	/** 
	* 加载页面 *
	* @author jie.yang  
	* @date 2014-12-10 
	*/ 
	public String toGuestPage(){
		
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/** 
	*  获取电站在地图上的位置*
	* @author jie.yang  
	* @date 2014-12-10 
	* @param list_psInformation 
	* @param list_psPicture
	* @param PSid
	* @param psP
	*/ 
	public String getStationPosition(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		psPictureService =new PS_pictureServiceImpl();	
        List<PS_information> list_psInformation = ps_informationService.getAllStation();
        List<String>list_psPicture=new ArrayList<String>();
        for (int i=0;i<list_psInformation.size();i++){
        	int PSid=list_psInformation.get(i).getId();
        	String psP=psPictureService.getNameByPSid(PSid);
        	list_psPicture.add(psP);
        }
        ArrayList list = new ArrayList();
        list.add(list_psInformation);
        list.add(list_psPicture);
		JSONArray obj = JSONArray.fromObject(list);
		try {
			response.setHeader("Cache-Control","no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	*  获取电站概况数据*
	* @author jie.yang  
	* @date 2014-12-10 
	* @param y 
	* @param cal
	* @param ps_num
	* @param ps_year
	* @param ps_day
	* @param irad_day
	* @param ps_list 
	* @param l_data
	* @param s2
	* @param s3 
	* @param s4
	*/ 
	public String getGuestPageData(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int y;//获取当前年份    
		Calendar cal=Calendar.getInstance();    
		y=cal.get(Calendar.YEAR);     
		ps_informationService = new PS_informationServiceImpl();
		historyOfYearService = new HistoryOfYearServiceImpl();
		historyOfDayService = new HistoryOfDayServiceImpl();
		
		int ps_num = ps_informationService.getPSNum();
		double ps_year = historyOfYearService.getSumPowerOfYear();
		if(ps_year!=0)ps_year = ps_year/1000;
		double ps_day = historyOfDayService.getSumPowerOfDay();
		double irad_day = historyOfDayService.getSumIradOfDay();
		List<PS_information> ps_list=ps_informationService.getAllStation();
		
		String str_group1="当前电站总数："+ps_num;
		String str_group2="年电站发电量(MWh)："+ps_year;
		String str_group3="日电站发电量(KWh)："+ps_day;
		String str_group4="日电站辐射量(W/m²)："+irad_day;
		List<monitor_dataAdapter> l_data = new ArrayList<monitor_dataAdapter>();
		for(int i=0;i<ps_list.size();i++){
			monitor_dataAdapter a1 = new monitor_dataAdapter();
			monitor_dataAdapter a2 = new monitor_dataAdapter();
			monitor_dataAdapter a3 = new monitor_dataAdapter();
			monitor_dataAdapter a4 = new monitor_dataAdapter();
		
			a1.setName(ps_list.get(i).getName());
			a1.setValue(ps_list.get(i).getProvince());
			a1.setGroup(str_group1);
			l_data.add(a1);
			
			a2.setName(ps_list.get(i).getName());
			double s2=historyOfYearService.getPowerByPsIDAndYear(ps_list.get(i).getId(), y);//根据电站id获取当年的发电量
			if(s2==0){ a2.setValue("0MWh");}
			else {s2 = s2/1000;a2.setValue(s2+"MWh");}
			a2.setGroup(str_group2);
			l_data.add(a2);			
			a3.setName(ps_list.get(i).getName());
			double s3=historyOfDayService.getTodayAccPowerByPsID(ps_list.get(i).getId());//根据电站id获取当天的发电量
			
			if(s3==0){a3.setValue("0KWh");}
			else {a3.setValue(s3+"KWh");}			
			a3.setGroup(str_group3);
			l_data.add(a3);			
			a4.setName(ps_list.get(i).getName());
			double s4=historyOfDayService.getTodayAccIradByPsID(ps_list.get(i).getId());//根据电站id获取当天的辐射量
			if(s4==0){a4.setValue("0W/m²");}
			else {a4.setValue(s4+"W/m²");}
			a4.setGroup(str_group4);

			l_data.add(a4);
		}
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", l_data.size());
		object.put("rows", l_data);
		try {
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
