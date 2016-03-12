/** * * 
* 文件名称: toPSPeriodMamageAction.java *
* 
* 电站分期管理，增删改电站分期信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-12 下午9:46:33 *
* * @author jie.yang 
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

import com.PSMS.Adapter.ps_information;
import com.PSMS.Adapter.ps_period;
import com.PSMS.Hibernate.PS_information;
import com.PSMS.Hibernate.PS_period;
import com.PSMS.Service.M_userService;
import com.PSMS.Service.M_userServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.PS_periodService;
import com.PSMS.Service.PS_periodServiceImpl;


public class toPSPeriodMamageAction {
	/** 
	* 电站分期管理，加载页面,将需要显示的设备信息通过json传回前台,新建电站分期,编辑电站分期,删除电站分期,根据电站名称获取电站分期数,校验分期容量是否符合要求
	* @author jie.yang 
	* @date 2014-12-12
	* @param ps_periodService 		
	*/ 
	PS_periodService ps_periodService;
	public String toPsPeriod()
	{	
		/** 
		*加载页面*
		* @author jie.yang 
		* @date 2014-12-12 	
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return"success";
	}
	
	public String getPsPeriodInformation()
	{
		/** 
		*将需要显示的设备信息通过json传回前台*
		* @author jie.yang 
		* @date 2014-12-12
	    * @param p_list
	    * @param list  	
		*/
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PS_informationService ps_informationService=new PS_informationServiceImpl();
		ps_periodService =new PS_periodServiceImpl();	
		List<PS_period> p_list=ps_periodService.getPsPeriodInformation();		
		List<ps_period> list=new ArrayList<ps_period>();
		for(int i=0;i<p_list.size();i++)
		{
			ps_period p=new ps_period();
			p.setId(p_list.get(i).getId());
			String ps_name=ps_informationService.getStationNameById(p_list.get(i).getPS_id());// 获取电站id对应的name
			p.setName(ps_name);
			p.setPeriod_num(p_list.get(i).getPeriod_num());
			p.setPeriod_capacity(p_list.get(i).getPeriod_capacity());
			p.setPeriod_unit_num(p_list.get(i).getPeriod_unit_num());
			p.setPeriod_time(p_list.get(i).getPeriod_time());
			list.add(p);
		}
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", list.size());
		object.put("rows", list);		
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
	
	public String addPeriod()  
	{
		/** 
		*新建电站分期*
		* @author jie.yang 
		* @date 2014-12-12
	    * @param ps_name 
		* @param period_num  
		* @param period_capacity
	    * @param period_time 
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
		String ps_name = request.getParameter("ps_name");		
		String period_num = request.getParameter("period_num");
		String period_capacity = request.getParameter("period_capacity");
		String period_time = request.getParameter("period_time");		
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");      	
			period_time = java.net.URLDecoder.decode(period_time, "UTF-8");		//-以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PS_informationService ps_informationService =new PS_informationServiceImpl();
		int psid=ps_informationService.getPS_idByName(ps_name);
				
		PS_periodService ps_periodService =new PS_periodServiceImpl();
		PS_period ps_period =new PS_period();
		ps_period.setPS_id(psid);
		Integer num=Integer.parseInt(period_num);		
		ps_period.setPeriod_num(num);
		ps_period.setPeriod_capacity(period_capacity);
		ps_period.setPeriod_time(period_time);
		Double capacity_d=Double.parseDouble(period_capacity); 
		Integer unit_num= Integer.parseInt(new java.text.DecimalFormat("0").format(capacity_d));
		ps_period.setPeriod_unit_num(unit_num);
		ps_periodService.addPS_period(ps_period);
		
		ArrayList list = new ArrayList();
		String result = "分期保存成功！";//用result存放提示信息，并将其传回前台
		list.add(result);	//通过json将校验结果传回到前台显示	
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
	
	public String editPeriod()  
	{
		/** 
		*编辑电站分期*
		* @author jie.yang 
		* @date 2014-12-12
	    * @param id 
		* @param period_capacity  
		* @param period_time
	    * @param capacity_d 
		* @param unit_num	
		*  @param pID
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
			
		String id = request.getParameter("id");		
		String period_capacity = request.getParameter("period_capacity");
		String period_time = request.getParameter("period_time");		
		try {     	
			period_time = java.net.URLDecoder.decode(period_time, "UTF-8");		
			//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		ps_periodService =new PS_periodServiceImpl();		
		Double capacity_d=Double.parseDouble(period_capacity);		
		Integer unit_num= Integer.parseInt(new java.text.DecimalFormat("0").format(capacity_d));
		Integer pID=Integer.parseInt(id);	
		ps_periodService.editPeriodPartByID(pID,period_capacity,unit_num,period_time);
		ArrayList list = new ArrayList();
		String result = "分期保存成功！";// 电站内部逆变器不重名时返回correct
		list.add(result);	//通过json将校验结果传回到前台显示	
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
	
	public String deletePeriod(){
		/** 
		* 删除电站分期 *
		* @author jie.yang 
		* @date 2014-12-12
	    * @param p_id
	    * @param id 	
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String  id =  request.getParameter("id");
		int p_id = Integer.parseInt(id);
		PS_periodService ps_periodService =new PS_periodServiceImpl();
		ps_periodService.deletePeriodByPid(p_id);
		return null;
	}
	
	
	public String getPeriodNumByPsName()  
	{
		/** 
		*根据电站名称获取电站分期数*
		* @author jie.yang 
		* @date 2014-12-12
	    * @param ps_name
		* @param ps_id	 
		* @param period_num
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		String ps_name = request.getParameter("ps_name");				
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");      			//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		PS_informationService ps_informationService =new PS_informationServiceImpl();
		int ps_id=ps_informationService.getPS_idByName(ps_name);//根据电站名字获取id		
		ps_periodService =new PS_periodServiceImpl();		
		int period_num=ps_periodService.getPeriodNumByPsId(ps_id);				
		ArrayList list = new ArrayList();
		list.add(period_num+1);//通过json将校验结果传回到前台显示
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
	
	public String checkAddPeriodCapacityIsLegal() {
		/** 
		*校验分期容量是否符合要求*
		* @author jie.yang 
		* @date 2014-12-12
	    * @param ps_name
	    * @param capacity 
		* @param result  
		* @param period_capacity
		* @param ps_id  
		* @param PSCapacity		
		* @param PsCapacity  
		* @param totalCapacity
		*/ 
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ps_name = request.getParameter("ps_name");
		String capacity = request.getParameter("capacity");
		String flag=request.getParameter("flag");
		String period_capacity_edit=request.getParameter("period_capacity_edit");
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");// 以上为获取前台数据，转码
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = "";//用result存放提示信息，并将其传回前台
		Double period_capacity=Double.parseDouble(capacity);
		Double Period_capacity_edit=Double.parseDouble(period_capacity_edit);
		ps_periodService =new PS_periodServiceImpl();	
		PS_informationService ps_informationService =new PS_informationServiceImpl();
		int ps_id=ps_informationService.getPS_idByName(ps_name);
		String PSCapacity=ps_informationService.getCapacityById(ps_id); 

		Double PsCapacity=Double.parseDouble(PSCapacity);				//获取电站计划总容量
		Double totalCapacity=ps_periodService.getTotalCapacityByPsID(ps_id); //获取电站已有的总容量
		if(flag.equals("0"))
		{
		if (PsCapacity>=(totalCapacity+period_capacity))
				result = "correct";
		else                     
			{
				result = "wrong";
			} 
		}else if(flag.equals("1"))
		{
			if (PsCapacity>=(totalCapacity+period_capacity-Period_capacity_edit))
				result = "correct";
		else                     
			{
				result = "wrong";
			} 
		}
		ArrayList list = new ArrayList();
		list.add(result);//通过json将校验结果传回到前台显示
		list.add(PSCapacity);//通过json将校验结果传回到前台显示
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
}
