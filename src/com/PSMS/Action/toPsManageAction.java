/** * * 
* 文件名称: toWSManageAction.java *
* 
* 电站信息管理，增删改查电站信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-11 下午3:22:33 *
* * @author jie.yang 
*/
package com.PSMS.Action;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Service.M_userService;
import com.PSMS.Service.M_userServiceImpl;
import com.PSMS.Service.PM_parameterServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.PS_periodService;
import com.PSMS.Service.PS_periodServiceImpl;
import com.PSMS.Adapter.ps_information;
import com.PSMS.Hibernate.PS_information;
import com.PSMS.Hibernate.PS_period;

import freemarker.core.ParseException;

public class toPsManageAction {
	/** 
	* 设备信息管理，加载页面,将需要显示的设备信息通过json传回前台，新建电站, 删除选中的气象站信息,编辑电站后保存信息,根据电站名称查询电站信息,根据电站省份查询电站信息,根据电站建站时间查询电站信息,校验电站是否已存在,获取电站名字,获取用户所在电站信息,恢复曾经建立过的电站,根据电站id获取电站名称
	* @author jie.yang 
	* @date 2014-10-11
	* @param ps_informationService 
	* @param userService 		
	*/ 
	PS_informationService ps_informationService;
	private M_userService userService;
	
	public String toPsSearch() 
	{
		/** 
		*加载页面*
		* @author jie.yang 
		* @date 2014-10-11 	
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
	
	public String getPsInformation() //获得所有电站的所有信息
	{
		/** 
		*将需要显示的设备信息通过json传回前台*
		* @author liu.yang 
		* @date 2014-10-11
	    * @param ps_list
	    * @param ps_list2 
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		List<PS_information> ps_list=ps_informationService.getAllStation();
		
		List<ps_information> ps_list2=new ArrayList<ps_information>();
		for(int i=0;i<ps_list.size();i++)
		{			
				ps_information ps=new ps_information();
				ps.setId(ps_list.get(i).getId());
				ps.setName(ps_list.get(i).getName());
				ps.setCapacity(ps_list.get(i).getCapacity());
				ps.setArea(ps_list.get(i).getArea());
				if(ps_list.get(i).getPart_num()!=null){ps.setPart_num(ps_list.get(i).getPart_num());}
				else {ps.setPart_num(null);}
				ps.setOwner(ps_list.get(i).getOwner());
				ps.setInvestor(ps_list.get(i).getInvestor());
				ps.setProvince(ps_list.get(i).getProvince());
				ps.setLongitude(ps_list.get(i).getLongitude());
				ps.setLatitude(ps_list.get(i).getLatitude());
				ps.setBuild_time(ps_list.get(i).getBuild_time());
          		ps_list2.add(ps);
		}	
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", ps_list2.size());
		object.put("rows", ps_list2);		
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
	
	
	
	
	public String addPs() 
	{
		/** 
		*新建电站*
		* @author jie.yang 
		* @date 2014-10-11
	    * @param ps_name 
		* @param ps_owner  
		* @param ps_investor
	    * @param ps_province 
		* @param ps_build_time 
		* @param first_capacity   
		* @param capacity
		* @param area  
		* @param part_num
		* @param longitude
		* @param latitude	
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
		String ps_name = request.getParameter("name");		
		String ps_owner = request.getParameter("owner");
		String ps_investor = request.getParameter("investor");
		String ps_province = request.getParameter("province");
		String ps_build_time = request.getParameter("build_time");
		String first_capacity = request.getParameter("first_capacity");
		String capacity = request.getParameter("capacity");
		String area = request.getParameter("area");
		String part_num = request.getParameter("part_num");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");		
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");      	
			ps_owner = java.net.URLDecoder.decode(ps_owner, "UTF-8");
			ps_investor = java.net.URLDecoder.decode(ps_investor, "UTF-8");
			ps_province = java.net.URLDecoder.decode(ps_province, "UTF-8");
			ps_build_time = java.net.URLDecoder.decode(ps_build_time, "UTF-8");		
			//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		PS_information ps = new PS_information();
		ps.setName(ps_name);		
		ps.setOwner(ps_owner);
		ps.setInvestor(ps_investor);
		ps.setProvince(ps_province);
		ps.setBuild_time(ps_build_time);		
		ps.setDelete_flag(0);
		if(capacity==""){
			ps.setCapacity(null);
		}else {ps.setCapacity(capacity);}
		if(area==""){
			ps.setArea(null);
		}else {ps.setArea(area);}		
		if(part_num!=""){
			int ps_part_num=Integer.parseInt(part_num);
			ps.setPart_num(ps_part_num);
		}else {ps.setPart_num(null);}
		if(longitude==""){
			ps.setLongitude(null);
		}else {ps.setLongitude(longitude);}
		if(latitude==""){
			ps.setLatitude(null);
		}else {	ps.setLatitude(latitude);}	

		ps_informationService.addPs(ps);
		
		int psid=ps_informationService.getPS_idByName(ps_name);
		PS_periodService ps_periodService =new PS_periodServiceImpl();
		PS_period ps_period =new PS_period();
		ps_period.setPS_id(psid);		
		ps_period.setPeriod_num(1);
		ps_period.setPeriod_capacity(first_capacity);
		ps_period.setPeriod_time(ps_build_time);
		Integer first_unit_num=Integer.parseInt(first_capacity);
		ps_period.setPeriod_unit_num(first_unit_num);
		ps_periodService.addPS_period(ps_period);		
		//临时生成历史数据
		
		ArrayList list = new ArrayList();
		String result = "电站保存成功！";//用result存放提示信息，并将其传回前台
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
	
	public String deletePs(){ 
		/** 
		* 删除选中的气象站信息 *
		* @author jie.yang 
		* @date 2014-10-11
	    * @param ps_id
	    * @param id 	
	    * @param ps
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ps_id =  request.getParameter("id");
		int id = Integer.parseInt(ps_id);
		PS_information ps = new PS_information();
		ps_informationService = new PS_informationServiceImpl();
		ps=ps_informationService.getPSByID(id);       //根据电站ID返回电站信息
		ps.setDelete_flag(1);
		ps_informationService.updatePs(ps); //    删除电站（不删除数据库）（只修改flag）
										//删除电站的所有用户
		M_userService userService=new M_userServiceImpl();
		userService.deleteUserByPSId(id);
		return null;
	}
	
	public String updatePs()
	{
		/** 
		* 编辑电站后保存信息 *
		* @author jie.yang 
		* @date 2014-10-11
	    * @param ps_name 
		* @param ps_owner  
		* @param ps_investor
	    * @param ps_province 
		* @param ps_build_time 
		* @param id   
		* @param capacity
		* @param area  
		* @param part_num
		* @param longitude
		* @param latitude	
		* @param user_num
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
		String ps_name = request.getParameter("name");		
		String ps_owner = request.getParameter("owner");
		String ps_investor = request.getParameter("investor");
		String ps_province = request.getParameter("province");
		String ps_build_time = request.getParameter("build_time");
		String id=request.getParameter("id");
		String capacity = request.getParameter("capacity");
		String area = request.getParameter("area");
		String part_num = request.getParameter("part_num");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		String user_num=request.getParameter("user_num");		
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");      	
			ps_owner = java.net.URLDecoder.decode(ps_owner, "UTF-8");
			ps_investor = java.net.URLDecoder.decode(ps_investor, "UTF-8");
			ps_province = java.net.URLDecoder.decode(ps_province, "UTF-8");
			ps_build_time = java.net.URLDecoder.decode(ps_build_time, "UTF-8");		
			//-以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		PS_information ps = new PS_information();
		ps.setName(ps_name);		
		ps.setOwner(ps_owner);
		ps.setInvestor(ps_investor);
		ps.setProvince(ps_province);
		ps.setBuild_time(ps_build_time);		
		ps.setDelete_flag(0);
		if(id!=null){
			ps.setId(Integer.parseInt(id));
		}
		if(capacity==""){
			ps.setCapacity(null);
		}else {ps.setCapacity(capacity);}
		if(area==""){
			ps.setArea(null);
		}else {ps.setArea(area);}		
		if(part_num!=""){
			int ps_part_num=Integer.parseInt(part_num);
			ps.setPart_num(ps_part_num);
		}else {ps.setPart_num(null);}
		if(longitude==""){
			ps.setLongitude(null);
		}else {ps.setLongitude(longitude);}
		if(latitude==""){
			ps.setLatitude(null);
		}else {	ps.setLatitude(latitude);}		
		ps_informationService.updatePs(ps);
		ArrayList list = new ArrayList();
		String result = "电站修改成功！";//用result存放提示信息，并将其传回前台
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
	
	public String searchPsByName()
	{
		/** 
		*根据电站名称查询电站信息*
		* @author jie.yang 
		* @date 2014-10-11
	    * @param ps_name
	    * @param ps_list 
		* @param ps_list2          
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
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");	//-以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ps_informationService = new PS_informationServiceImpl();				
		List<PS_information> ps_list=ps_informationService.getStationByName(ps_name);//根据电站名称获取电站		
		List<ps_information> ps_list2=new ArrayList<ps_information>();
		for(int i=0;i<ps_list.size();i++)
		{
			
				ps_information ps=new ps_information();
				ps.setId(ps_list.get(i).getId());
				ps.setName(ps_list.get(i).getName());
				ps.setCapacity(ps_list.get(i).getCapacity());
				ps.setArea(ps_list.get(i).getArea());
				ps.setPart_num(ps_list.get(i).getPart_num());
				ps.setOwner(ps_list.get(i).getOwner());
				ps.setInvestor(ps_list.get(i).getInvestor());
				ps.setProvince(ps_list.get(i).getProvince());
				ps.setLongitude(ps_list.get(i).getLongitude());
				ps.setLatitude(ps_list.get(i).getLatitude());
				ps.setBuild_time(ps_list.get(i).getBuild_time());
				ps_list2.add(ps);
			
		}	
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", ps_list2.size());
		object.put("rows", ps_list2);		
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
	
	public String searchPsByProvince()
	{
		/** 
		*根据电站省份查询电站信息*
		* @author jie.yang 
		* @date 2014-10-11
	    * @param ps_province
	    * @param ps_list 
		* @param ps_list2          
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ps_province = request.getParameter("ps_province");
		
		try {
			ps_province = java.net.URLDecoder.decode(ps_province, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
		ps_informationService = new PS_informationServiceImpl();
		List<PS_information> ps_list=ps_informationService.getStationByProvince(ps_province);		
		List<ps_information> ps_list2=new ArrayList<ps_information>();
		for(int i=0;i<ps_list.size();i++)
		{
			if(ps_list.get(i).getDelete_flag()==0)
			{
			
				ps_information ps=new ps_information();
				ps.setId(ps_list.get(i).getId());
				ps.setName(ps_list.get(i).getName());
				ps.setCapacity(ps_list.get(i).getCapacity());
				ps.setArea(ps_list.get(i).getArea());
				ps.setPart_num(ps_list.get(i).getPart_num());
				ps.setOwner(ps_list.get(i).getOwner());
				ps.setInvestor(ps_list.get(i).getInvestor());
				ps.setProvince(ps_list.get(i).getProvince());
				ps.setLongitude(ps_list.get(i).getLongitude());
				ps.setLatitude(ps_list.get(i).getLatitude());
				ps.setBuild_time(ps_list.get(i).getBuild_time());
				ps_list2.add(ps);
			}
		}	
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", ps_list2.size());
		object.put("rows", ps_list2);
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
	
	public String searchPsByTime(){
		/** 
		*根据电站建站时间查询电站信息*
		* @author jie.yang 
		* @date 2014-10-11
	    * @param date1
	    * @param ps_list
		* @param ps_list2          
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String date1=request.getParameter("date1");
		String date2=request.getParameter("date2");
		ps_informationService = new PS_informationServiceImpl();
		List<PS_information> ps_list=ps_informationService.getStationByTime(date1,date2);//根据电站建站起止时间查询电站信息		
		List<ps_information> ps_list2=new ArrayList<ps_information>();
		for(int i=0;i<ps_list.size();i++)
		{
			if(ps_list.get(i).getDelete_flag()==0)
			{
			
				ps_information ps=new ps_information();
				ps.setId(ps_list.get(i).getId());
				ps.setName(ps_list.get(i).getName());
				ps.setCapacity(ps_list.get(i).getCapacity());
				ps.setArea(ps_list.get(i).getArea());
				ps.setPart_num(ps_list.get(i).getPart_num());
				ps.setOwner(ps_list.get(i).getOwner());
				ps.setInvestor(ps_list.get(i).getInvestor());
				ps.setProvince(ps_list.get(i).getProvince());
				ps.setLongitude(ps_list.get(i).getLongitude());
				ps.setLatitude(ps_list.get(i).getLatitude());
				ps.setBuild_time(ps_list.get(i).getBuild_time());
				ps_list2.add(ps);
			}
			
		}	
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", ps_list2.size());
		object.put("rows", ps_list2);
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
	
	public String checkPsNameIsLegal() {
		/** 
		*校验电站是否已存在*
		* @author jie.yang 
		* @date 2014-10-11
	    * @param name
	    * @param result  	
		*/ 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		try {
			name = java.net.URLDecoder.decode(name, "UTF-8");
				// 以上为获取前台数据，转码
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		String result = "";//用result存放提示信息，并将其传回前台
			if (!(ps_informationService.checkPsNameIsExist(name)))
				result = "correct";
			else                    //如果 电站名称已经存在则检查电站是否为被删除状态（delete_flag）
			{
				int delete_flag=ps_informationService.getDeleteFlagByPSName(name); //根据电站名称查询该电站是否已经被删除
				if(delete_flag==0)
						result = "wrong";
				else 	result="wrongDelete"; //电站名称为删除状态
			}
		// 电站内部电表不重名时返回correct
		ArrayList list = new ArrayList();
		list.add(result);//通过json将校验结果传回到前台显示
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
	
	public String getPsNames(){
		/** 
		*获取电站名字*
		* @author jie.yang 
		* @date 2014-10-11
	    * @param list_ps_name 	
		*/ 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ps_informationService = new PS_informationServiceImpl();
		List<String> list_ps_name = ps_informationService.getAllStationName();
	
		ArrayList list = new ArrayList();
		list.add(list_ps_name);//通过json将校验结果传回到前台显示
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
	
	public String getAuthority(){
		/** 
		*获取用户所在电站信息*
		* @author jie.yang 
		* @date 2014-10-11
	    * @param user_name 
	    * @param list_authority	
		*/ 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String user_name = request.getParameter("username") ;		
		try {
			user_name = java.net.URLDecoder.decode(user_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userService = new M_userServiceImpl();
		ps_informationService = new PS_informationServiceImpl();		
		int ps_id = userService.getPsIdByUsername(user_name);
		List<PS_information> list_authority = ps_informationService.getStationByPsId(ps_id);//得到该用户所在电站信息		
		ArrayList list = new ArrayList();
		list.add(list_authority);//通过json将校验结果传回到前台显示
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
	
								
	public String recoverPS(){ 
		/** 
		*恢复曾经建立过的电站*
		* @author jie.yang 
		* @date 2014-10-11
	    * @param ps_name 
	    * @param id	
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
		String ps_name = request.getParameter("name");		
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");			
			//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		PS_information ps = new PS_information();
		ps_informationService = new PS_informationServiceImpl();
		int id=ps_informationService.getPS_idByName(ps_name);
		ps=ps_informationService.getPSByID(id);       //根据电站ID返回电站信息
		ps.setDelete_flag(0);
		ps_informationService.updatePs(ps); //    恢复电站（只修改flag）
		
		String result="success";
		ArrayList list = new ArrayList();
		list.add(result);//通过json将校验结果传回到前台显示
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
	
	public String getPSNameByPSId(){
		/** 
		*根据电站id获取电站名称*
		* @author jie.yang 
		* @date 2014-10-11
	    * @param ps_id 
	    * @param ps_name	
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
		int ps_id = Integer.parseInt(request.getParameter("ps_id"));		
		ps_informationService = new PS_informationServiceImpl();
		String  ps_name = ps_informationService.getStationNameById(ps_id);
		ArrayList list = new ArrayList();
		list.add(ps_name);//通过json将校验结果传回到前台显示
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
	public String checkPsCanDelete()
	{
		/** 
		*根据是否有分期判断能否删除*
		* @author yaning.liu
		* @date 2015-10-9
	    * @param ps_province
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
		int ps_id=Integer.parseInt(id);
		PS_periodService ps_periodService = new PS_periodServiceImpl();
		int number = ps_periodService.getPeriodNumByPsId(ps_id);
		String result = "";
		if(number==0)
		{
			result = "can";
		}else
		{
			result = "cant";
		}
		ArrayList list = new ArrayList();
		list.add(result);//通过json将校验结果传回到前台显示
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
	
	public String checkCapacity()
	{
		/** 
		*判断是否可以修改电站容量*
		* @author yaning.liu
		* @date 2015-10-9
	    * @param ps_name
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
		String ps_name = request.getParameter("name");
		String capacity = request.getParameter("capacity");
		try {
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");// 以上为获取前台数据，转码
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Double ps_capacity=Double.parseDouble(capacity);
		PS_periodServiceImpl ps_periodService =new PS_periodServiceImpl();	
		PS_informationService ps_informationService =new PS_informationServiceImpl();
		int ps_id=ps_informationService.getPS_idByName(ps_name);
		Double totalCapacity=ps_periodService.getTotalCapacityByPsID(ps_id); //获取电站已有的总容量
		String result = "";
		if(ps_capacity<totalCapacity)
		{
			result = "wrong";
		}else
		{
			result = "correct";
		}
		ArrayList list = new ArrayList();
		list.add(result);//通过json将校验结果传回到前台显示
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
