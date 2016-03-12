/** * * 
* 文件名称: toCompofPowerAndIrrad.java *
* 
* 数据对比，发电量与辐射量数据对比 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-1-12 下午04:30:04 *
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

import com.PSMS.Hibernate.HistoryOfDay;
import com.PSMS.Hibernate.HistoryOfMonth;
import com.PSMS.Service.HistoryOfDayService;
import com.PSMS.Service.HistoryOfDayServiceImpl;
import com.PSMS.Service.HistoryOfMonthService;
import com.PSMS.Service.HistoryOfMonthServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;

/** 
* 数据对比，发电量与辐射量数据对比 *
* @author jie.yang & jiaojiao.wang
* @date 2014-1-14 
* @param ps_informationService 
* @param historyOfMonthService
* @param historyOfDayService
*/ 
public class toCompOfPowerAndIrrad {
	
	HistoryOfDayService historyOfDayService;
	
	HistoryOfMonthService historyOfMonthService;
	
	PS_informationService ps_informationService;
	
	public String toCompOfPowerAndIrradByYear(){
		/** 
		* 按年统计发电量和辐射量的对比值*
		* @author jie.yang
		* @date 2014-12-1 
		* @param year 
		* @param ps_name
		* @param l_month
		* @param l_day
		* @param l_month_irradiation
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
		String ps_name = request.getParameter("ps_name");
		try {
			
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ps_informationService = new PS_informationServiceImpl();
		historyOfDayService = new HistoryOfDayServiceImpl();
		historyOfMonthService = new HistoryOfMonthServiceImpl();
		int ps_id = ps_informationService.getPS_idByName(ps_name);//获取所要查询电站的电站id
		List<HistoryOfMonth> l_month = historyOfMonthService.getDataByPs_idAndYear(ps_id,year);//获取月度历史数据
		List<Double> l_month_irradiation = new ArrayList();
		for(int i=0;i<l_month.size();i++){
			int month = l_month.get(i).getMonth();			
			List<HistoryOfDay> l_day = historyOfDayService.getDataByMonth(ps_id, year, month);
			double irrad = 0.0;
			for(int j=0;j<l_day.size();j++){
				irrad = irrad + l_day.get(j).getMax_irradiation();
			}			
			l_month_irradiation.add(irrad);
		}
		ArrayList list = new ArrayList();
		list.add(l_month);
		list.add(l_month_irradiation);
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
	
	public String toCompOfPowerAndIrradByMonth(){
		/** 
		* 按月统计发电量和辐射量的对比值*
		* @author jiaojiao.wang
		* @date 2014-12-2 
		* @param year 
		* @param ps_name
		* @param month
		* @param l_month_data
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
		String ps_name = request.getParameter("ps_name");//从前台获取电站名称
		int month = Integer.parseInt(request.getParameter("month"));//从前台获取月份
		try {
			
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//------------------------------以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ps_informationService = new PS_informationServiceImpl();
		historyOfDayService = new HistoryOfDayServiceImpl();		
		int ps_id = ps_informationService.getPS_idByName(ps_name);//获取所要查询电站的电站id
		List<HistoryOfDay> l_day_data = historyOfDayService.getDataByMonth(ps_id, year, month);			
		ArrayList list = new ArrayList();		
		list.add(l_day_data);
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
	public String toCompPowerAndIrradPage(){
		/** 
		* 发电量与辐射量数据对比界面跳转*
		* @author jie.yang
		* @date 2015-1-12  
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
