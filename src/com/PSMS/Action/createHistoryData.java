/** * * 
* 文件名称: RawDataChartController.java *
* 
* 原始数据图表Controller，负责为前台的图表取数据 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2015-01-30 下午7:27:48 *
* * @author guanghua.liu 
*/ 


/** 
* 通过最小ID和pointPath获取最新数据，并通过JSON返回到前台 
* * @author guanghua.liu
* @date 2015-01-30 
* @param id 
* @param pointPath 
*/ 

package com.PSMS.Action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Hibernate.PS_information;
import com.PSMS.Hibernate.PS_picture;
import com.PSMS.Service.HistoryOfDayService;
import com.PSMS.Service.HistoryOfDayServiceImpl;
import com.PSMS.Service.HistoryOfMonthService;
import com.PSMS.Service.HistoryOfMonthServiceImpl;
import com.PSMS.Service.HistoryOfYearService;
import com.PSMS.Service.HistoryOfYearServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.PS_pictureServiceImpl;
import com.PSMS.Service.PowerMeterService;
import com.PSMS.Service.PowerMeterServiceImpl;


public class createHistoryData {
	
	public String creatHistory() throws ParseException{
		/** 
		* 生成历史数据*
		* @author min.li 
		* @date 2015-07-06
		* @param name
		* @param ps_id
		* @param id
		* @param psID
		* @param psP
		* @param result  		
		*/
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createHistoryDay();
		createHistoryMonth();
		createHistoryYear();
		ArrayList list = new ArrayList();
		String result = "图片上传成功！";//用result存放提示信息，并将其传回前台
		list.add(result);//通过json将校验结果传回到前台显示
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
	private void createHistoryYear() {
		// TODO Auto-generated method stub
		PS_informationService psInformationService = new PS_informationServiceImpl();
		List <PS_information> l_ps = psInformationService.getAllStation();
		HistoryOfYearService historyYearService = new HistoryOfYearServiceImpl();
		HistoryOfDayService historyDayService = new HistoryOfDayServiceImpl();
		for(int i=0;i<l_ps.size();i++){
			String startTime = historyDayService.getMinMonthTimeByPSId(l_ps.get(i).getId());
			String endTime = historyDayService.getMaxMonthTimeByPSId(l_ps.get(i).getId());
			int sYear = Integer.parseInt(startTime.substring(0, 4));
			int eYear = Integer.parseInt(endTime.substring(0, 4));
			if(!historyYearService.isEmptyByPsId(l_ps.get(i).getId())){
				historyYearService.clearDataByPsID(l_ps.get(i).getId());
			}	
			for(int j=sYear;j<eYear+1;j++){
				historyYearService.createDataByYearAndPsId(j,l_ps.get(i).getId());
			}
		}
	}
	private void createHistoryMonth() throws ParseException {
		// TODO Auto-generated method stub
		HistoryOfDayService historyDayService = new HistoryOfDayServiceImpl();
		HistoryOfMonthService historyMonthService = new HistoryOfMonthServiceImpl();
		PS_informationService psInformationService = new PS_informationServiceImpl();
		List <PS_information> l_ps = psInformationService.getAllStation();
		for(int i=0;i<l_ps.size();i++){
			String hisDate = historyDayService.getMaxDateByPsId(l_ps.get(i).getId());
			if(hisDate!=null){//historyDay表里不为空,再生成historyMonth表数据
				if(!historyMonthService.isEmptyByPsId(l_ps.get(i).getId())){
					historyMonthService.clearDataByPsID(l_ps.get(i).getId());
					
				}
				String startTime = historyDayService.getMinMonthTimeByPSId(l_ps.get(i).getId());
				String endTime = historyDayService.getMaxMonthTimeByPSId(l_ps.get(i).getId());
				Date d1 = new SimpleDateFormat("yyyy-MM").parse(startTime);//定义起始日期
				Date d2 = new SimpleDateFormat("yyyy-MM").parse(endTime);//定义结束日期
				Calendar dd = Calendar.getInstance();//定义日期实例
				dd.setTime(d1);//设置日期起始时间
				while(dd.getTime().before(d2)){//判断是否到结束日期
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					String str = sdf.format(dd.getTime());
					historyMonthService.createDataByMonthAndPsId(str,l_ps.get(i).getId());
					dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
				}
			}
		}
	}
	public void createHistoryDay(){
		PS_informationService psInformationService = new PS_informationServiceImpl();
		PowerMeterService powerMeterService = new PowerMeterServiceImpl();
		HistoryOfDayService historyDayService = new HistoryOfDayServiceImpl();
		List <PS_information> l_ps = psInformationService.getAllStation();
		
		for(int i=0;i<l_ps.size();i++){//循环生成每一电站的历史数据
			String hisDate = historyDayService.getMaxDateByPsId(l_ps.get(i).getId());
			if(hisDate!=null)historyDayService.clearData(l_ps.get(i).getId());//清空每个电站数据
			String maxDate = powerMeterService.getMaxDateByPsId(l_ps.get(i).getId());
			String minDate = powerMeterService.getMinDateByPsId(l_ps.get(i).getId());
			String max = maxDate.substring(0, 10);
			String min = minDate.substring(0, 10);
			 System.out.println(max+"---"+min+"---"+l_ps.get(i).getId());
			 try {
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			        Date start = sdf.parse(min);
			        Date end = sdf.parse(max);
			        List<Date> lists = dateSplit(start, end);
			        if (!lists.isEmpty()) {
			        	for (Date date : lists) {
			                String Time = sdf.format(date);
			               System.out.println(Time);
			                double s = historyDayService.createDayAccPowerByPSIdAndDate(Time,l_ps.get(i).getId());
			    		}
			        }
			    } catch (Exception e) {
			 }
			
					
		}
		
	}
	
	private static List<Date> dateSplit(Date startDate, Date endDate)throws Exception {
		if (!startDate.before(endDate))
				throw new Exception("开始时间应该在结束时间之后");
		Long spi = endDate.getTime() - startDate.getTime();
		Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

		List<Date> dateList = new ArrayList<Date>();
		dateList.add(endDate);
		for (int i = 1; i <= step; i++) {
			dateList.add(new Date(dateList.get(i - 1).getTime()
            - (24 * 60 * 60 * 1000)));// 比上一天减一
		}
		return dateList;
	}

}
