/** * * 
* 文件名称: userLoginAction.java *
* 
* 用户登陆，分角色登陆 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-10 下午6:27:40 *
* * @author jiaojiao.wang 
*/ 
package com.PSMS.Action;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Hibernate.M_user;
import com.PSMS.Service.M_userService;
import com.PSMS.Service.M_userServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.RoleService;
import com.PSMS.Service.RoleServiceImpl;



/** 
*用户登陆，分角色（超级管理员、中心查看人员、子电站运维人员、电站查看人员）登陆 *
* @author jiaojiao.wang 
* @date 2014-10-10 
* @param User_name 
* @param password
* @param PS_id
* @param station_name
* @param Role_id
* @param m_userService
* @param ps_informationService 
* @param roleService
* @param uName
*/ 
public class userLoginAction {
	

	private String User_name;
	
	private String password;
	
	private Integer PS_id;
	
	private String station_name;
	
	private Integer Role_id;
	
	private M_userService m_userService;
	
	private PS_informationService ps_informationService;
	
	private RoleService roleService;
	
	private String uName;
	
	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getUser_name() {
		return User_name;
	}

	public void setUser_name(String user_name) {
		User_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPS_id() {
		return PS_id;
	}

	public void setPS_id(Integer pS_id) {
		PS_id = pS_id;
	}



	public String getStation_name() {
		return station_name;
	}

	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}

	public Integer getRole_id() {
		return Role_id;
	}

	public void setRole_id(Integer role_id) {
		Role_id = role_id;
	}

	public M_userService getM_userService() {
		return m_userService;
	}

	public void setM_userService(M_userService m_userService) {
		this.m_userService = m_userService;
	}
	public PS_informationService getPs_informationService() {
		return ps_informationService;
	}

	public void setPs_informationService(PS_informationService ps_informationService) {
		this.ps_informationService = ps_informationService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	
	/** 
	* 通过前台传来的用户名和密码登录到系统
	* @author jiaojiao.wang
	* @date 2014-10-10 
	* @param user_name 
	* @param password 
	* @param s1
	* @param Role_id
	* @param id
	*/ 
	public String userLogin(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String user_name = request.getParameter("user_name");//获取从前台传来的用户名
		String password = request.getParameter("password");//获取从前台传来的密码
		try {
			user_name = java.net.URLDecoder.decode(user_name, "UTF-8");//将从前台获取的用户名转码
			password = java.net.URLDecoder.decode(password, "UTF-8");//将从前台获取的密码转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m_userService = new M_userServiceImpl();
		String s1="fail";//区分登陆的不同角色
			if(m_userService.checkUserIsExist(user_name,password))
			{
					Integer Role_id = m_userService.getRoleIdByName(user_name);//根据用户名获取该用户角色id
					switch (Role_id){//根据用户角色跳转不同用户界面
						case 1 : s1="Role1"; break; 
						case 2 : s1="Role2"; break;
						case 3 : s1="Role3"; break; 
						case 4 : s1="Role4"; break;
					}
			}									
			session.setAttribute("User_name", user_name);//将用户名保存在session中传给前台使用
			int id = m_userService.getPsIdByUsername(user_name);//根据用户名获取该用户所属电站id
			session.setAttribute("id", id);//将电站id保存在session中传给前台使用
			request.setAttribute("User_name",User_name);//将用户名传给前台请求页面
			ArrayList list = new ArrayList();
	        list.add(s1);//把角色用json传给前台
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
	* 超级管理员登录到系统
	* @author jiaojiao.wang
	* @date 2014-10-10 
	* @param PS_name 
	* @param Role_name 
	* @param user_list
	* @param role_id
	* @param id
	*/ 
	public String toSuperManageUser(){
		
		HttpServletRequest request =ServletActionContext.getRequest();	
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		m_userService = new M_userServiceImpl();		
		roleService = new RoleServiceImpl(); 
		String PS_name = "全部",Role_name="";
		List<M_user> user_list=m_userService.getUserByUser_name(User_name);//通过用户名获得用户记录
		int role_id = user_list.get(0).getRole_id();//获取用户的角色id
		Role_name = roleService.getRoleNameById(role_id);//根据角色id获取角色名称
		session.setAttribute("User_name", User_name);//将用户名保存在session中传给前台使用
		session.setAttribute("id", user_list.get(0).getId());//将用户id保存在session中传给前台使用
		
		request.setAttribute("User_name",User_name);//将用户名传给前台请求页面 		
		return "success";
	}
	
	/** 
	* 中心查看人员登录到系统
	* @author jiaojiao.wang
	* @date 2014-10-10 
	* @param user_list
	*/ 
	public String toSuperUser(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		m_userService = new M_userServiceImpl();
		List<M_user> user_list=m_userService.getUserByUser_name(User_name);//通过用户名获得用户记录
		session.setAttribute("User_name", User_name);//将用户名保存在session中传给前台使用
		session.setAttribute("id", user_list.get(0).getId());//将用户id保存在session中传给前台使用
		request.setAttribute("User_name",User_name); //将用户名传给前台请求页面 
		return "success";
	}
	
	/** 
	*子电站运维人员登录到系统
	* @author jiaojiao.wang
	* @date 2014-10-10 
	* @param user_list
	* @param PS_name
	* @param ps_id
	*/ 
	public String toCommonManageUser(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		m_userService = new M_userServiceImpl();
		ps_informationService = new PS_informationServiceImpl();
		roleService = new RoleServiceImpl(); 
		
		String PS_name = "";
		List<M_user> user_list=m_userService.getUserByUser_name(User_name);//通过用户名获得用户记录
		
		int ps_id = user_list.get(0).getPS_id();//获取用户所属电站id
		if(ps_id == 0) ;//如果是“全部”则什么也不做
		else{
		PS_name = ps_informationService.getStationNameById(ps_id);//获取电站id对应的电站名称
		}
		session.setAttribute("User_name", User_name);//将用户名保存在session中传给前台使用
		session.setAttribute("id", user_list.get(0).getId());//将用户id保存在session中传给前台使用
		session.setAttribute("PS_name",PS_name);//将电站名称保存在session中传给前台使用
		request.setAttribute("User_name",User_name);//将用户名传给前台请求页面  
		return "success";
	}
	
	/** 
	*电站查看人员登录到系统
	* @author jiaojiao.wang
	* @date 2014-10-10 
	* @param user_list
	* @param PS_name
	* @param ps_id
	*/ 
	public String toCommonUser(){//电站查看人员登录
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		m_userService = new M_userServiceImpl();
		ps_informationService = new PS_informationServiceImpl();
		roleService = new RoleServiceImpl(); 
		
		String PS_name = "";
		List<M_user> user_list=m_userService.getUserByUser_name(User_name);//通过用户名获得用户记录
		
		int ps_id = user_list.get(0).getPS_id();//获取用户所属电站id
		if(ps_id == 0) ;//如果是“全部”则什么也不做
		else{
		PS_name = ps_informationService.getStationNameById(ps_id);//获取电站id对应的电站名称
		}
		
		session.setAttribute("User_name", User_name);//将用户名保存在session中传给前台使用
		session.setAttribute("id", user_list.get(0).getId());//将用户id保存在session中传给前台使用
		session.setAttribute("PS_name",PS_name);//将电站名称保存在session中传给前台使用
		
		request.setAttribute("User_name",User_name);//将用户名传给前台请求页面   
		
		return "success";
	}
	
	
	/** 
	*获得用户的名字,传回前台显示 
	* @author jiaojiao.wang
	* @date 2014-12-01
	* @param user_name
	* @param list
	*/ 
	public String getUserName(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		String user_name =  (String) request.getSession().getAttribute("User_name");//获取登录用户名	
		ArrayList list = new ArrayList();
		list.add(user_name);//通过json将用户名传回到前台
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
	*校验用户名和密码的正确性
	* @author jiaojiao.wang
	* @date 2014-12-01
	* @param user_name
	* @param password
	* @param result
	*/ 
	public String checkUsernameAndPassword(){ //校验用户名和密码的正确性
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String user_name = request.getParameter("user_name");//从前台获取登录用户名	
		String password = request.getParameter("password");//从前台获取密码
		try {
			user_name = java.net.URLDecoder.decode(user_name, "UTF-8");//将前台传来的用户名转码
			password = java.net.URLDecoder.decode(password, "UTF-8");//将前台传来的密码转码
			} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		m_userService = new M_userServiceImpl();
		String result = "";//用result存放提示信息，并将其传回前台
			if(!(m_userService.checkUserName(user_name)))result="usernameIsNotExist";
			else {
				if(!(m_userService.checkUserIsExist(user_name,password)))
					{result="pw_wrong";}
				else{result="correct"; }
			}
			
		ArrayList list = new ArrayList();
		list.add(result);//通过json将校验结果传回到前台显示
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
	* 根据用户名返回不同桌面
	* @author jie.yang
	* @date 2015-01-16
	* @param user_name
	* @param Role_id
	* @param s1
	*/ 
	public String toDesktop(){//根据User_name返回桌面	2015-01-16 YJ
			HttpServletRequest request =ServletActionContext.getRequest();
			HttpServletResponse response =ServletActionContext.getResponse();
			HttpSession session = request.getSession();
			try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}				
			String user_name=request.getParameter("uName");//从前台获取用户名	
			try {
				user_name = java.net.URLDecoder.decode(uName, "UTF-8");//转码
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			m_userService = new M_userServiceImpl();
			Integer Role_id = m_userService.getRoleIdByName(user_name);//根据用户名获得角色id
			String s1="";
			switch (Role_id){//根据角色id跳转到相应的桌面
					case 1 : s1="Role1"; break; 
					case 2 : s1="Role2"; break;
					case 3 : s1="Role3"; break; 
			}
			session.setAttribute("User_name", user_name);//将用户名保存在session中传给前台使用
			request.setAttribute("User_name",user_name); //将用户名传给前台请求页面 
			ArrayList list = new ArrayList();
	        list.add(s1);//通过json将s1传回到前台
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
	
}
