/** * * 
* 文件名称: toAllFailureHistoryAction.java *
* 
* 历史数据，查看电站设备的故障数据 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-1-8 下午9:06:40 *
* * @author min.li & jie.yang 
*/
package com.PSMS.Action;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import com.PSMS.Hibernate.HistoryOfFailure;
import com.PSMS.Service.HistoryOfFailureService;
import com.PSMS.Service.HistoryOfFailureServiceImpl;
public class toAllFailureHistoryAction {
	/** 
	* 历史数据，查看电站设备的故障数据  *
	* @author min.li & jie.yang 
	* @date 2014-12-18 
	* @param historyoffailureService 
	*/ 
	private HistoryOfFailureService historyoffailureService;	
	public String getFailureHistory(){
		/** 
		* 查看电站设备的故障数据  *
		* @author min.li & jie.yang 
		* @date 2014-12-18 
		* @param fromRangeDate 
		* @param toRangeDate 
		* @param ps_name
		* @param device_type
		* @param list_failure
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
		String device_type = request.getParameter("device_type") ;		
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");
			device_type = java.net.URLDecoder.decode(device_type, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		historyoffailureService  = new HistoryOfFailureServiceImpl();
		historyoffailureService.deleteAllData();
		historyoffailureService.geAllFailureInformation();//从各个表联合查询获取故障历史表的数据
		historyoffailureService.geAllFailureInformationJB();
		List<HistoryOfFailure> list_failure = historyoffailureService.searchFailureHistoryData(ps_name,device_type,fromRangeDate,toRangeDate);;//最终数据存放list				
		for(int i=0;i<list_failure.size();i++){
			list_failure.get(i).setId(i+1);
		}
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", list_failure.size());
		object.put("rows", list_failure);
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
	
	

}
