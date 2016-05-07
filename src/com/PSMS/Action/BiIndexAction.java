package com.PSMS.Action;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.PSMS.Service.IBiIndexService;
import com.PSMS.Service.impl.BiIndexServiceImpl;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.GetTime;

import net.sf.json.JSONObject;

public class BiIndexAction {
	
	private IBiIndexService biIndeService;
	
	
	public String toBiIndex(){
		try {
			HttpServletRequest request =ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			biIndeService = new BiIndexServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			int pId=Integer.parseInt(psId);
			JSONObject object = JSONObject.fromObject("{}");
			List<PowerStationBase> currDayQ=biIndeService.getCurrDayQ(dateTime, pId);//当天日发电量
			object.put("currDayQ", currDayQ);
			PowerStationBase currDayCountQ=biIndeService.getCurrDayCountQ(dateTime, pId);//当天累计发电量
			object.put("currDayCountQ", currDayCountQ);
			String currMonth=GetTime.getCurrentMonth();
			List<PowerStationBase> currMonthQ=biIndeService.getCurrMonthQ(currMonth, pId);//当月发电量
			object.put("currMonthQ", currMonthQ);
			PowerStationBase currMonthCountQ=biIndeService.getCurrMonthCountQ(currMonth, pId);//当月累计发电量
			object.put("currMonthCountQ", currMonthCountQ);
			String currYear=GetTime.getCurrentYear();
			List<PowerStationBase> currYearQ=biIndeService.getCurrYearQ(currYear, pId);//当年每月发电量
			object.put("currYearQ", currYearQ);
			PowerStationBase currYearCountQ=biIndeService.getCurrYearCountQ(currYear, pId);//当年总发电量
			object.put("currYearCountQ", currYearCountQ);
			PowerStationBase dashboard=biIndeService.getCurrDashboard(pId);//查询电站仪表盘实时数据（最新数据）
			object.put("dashboard", dashboard);
			PowerStationBase history=biIndeService.getHistoryQAndObligate(pId);//历史发电量  减排二氧化碳
			object.put("history", history);
			object.put("psId", pId);
			object.put("total", "total");
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
//			ServletActionContext.getResponse().getWriter().write(object.toString());
			request.setAttribute("list", object.toString());
			request.setAttribute("dashboard", dashboard);
			request.setAttribute("history", history);//currDayCountQ
			request.setAttribute("currDayCountQ", currDayCountQ);
			request.setAttribute("currYearCountQ", currYearCountQ);
			request.setAttribute("currMonthCountQ",currMonthCountQ);
			System.out.println(object.toString());
		}catch(IOException e){
			e.printStackTrace();
		}
		return "success";
	}


	public IBiIndexService getBiIndeService() {
		return biIndeService;
	}

	public void setBiIndeService(IBiIndexService biIndeService) {
		this.biIndeService = biIndeService;
	}
}
