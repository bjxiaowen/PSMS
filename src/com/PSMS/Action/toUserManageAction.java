/** * * 
* 文件名称: toUserManageAction.java *
* 
* 用户信息管理，增删改查用户信息*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-11 下午4:24:40 *
* * @author min.li 
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
import com.PSMS.Adapter.user;
import com.PSMS.Hibernate.Count_user;
import com.PSMS.Hibernate.M_user;
import com.PSMS.Service.Count_userService;
import com.PSMS.Service.Count_userServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.RoleService;
import com.PSMS.Service.RoleServiceImpl;
import com.PSMS.Service.M_userService;
import com.PSMS.Service.M_userServiceImpl;




/** 
*用户管理，加载页面时显示下拉框中电站名称,将需要显示的用户信息通过json传回前台,将需要显示的用户信息通过json传回前台,新建或编辑用户后保存,判断用户名是否符合要求,删除用户,新建用户,根据用户名查询用户信息,根据电站和用户名进行双重查询,根据角色显示对应的电站名称,根据用户名获得电站名称,根据用户名查询电站ID *
* @author min.li 
* @date 2014-10-11 
* @param userService 
* @param roleService
* @param ps_informationService
* @param count_userService
*/ 
public class toUserManageAction {
	
	private M_userService userService;
	
	private RoleService roleService;
	
	private PS_informationService ps_informationService;
	
	private Count_userService count_userService;
	
	/** 
	* 加载页面时显示下拉框中电站名称
	* @author min.li 
	* @date 2014-10-11 
	* @param list_role_name
	* @param list_station_name
	*/ 
	public String toUserManage(){
		
		HttpServletRequest request =ServletActionContext.getRequest();
		
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		roleService  = new RoleServiceImpl();
		ps_informationService = new PS_informationServiceImpl();
		List<String> list_role_name = roleService.getAllRoleName();//获取所有角色名称
		List<String> list_station_name = ps_informationService.getAllStationName();//获取所有电站名称
		request.setAttribute("list_role_name", list_role_name);
		request.setAttribute("list_station_name", list_station_name);//返回给前台显示
		return "success";
	}

	/** 
	*将需要显示的用户信息通过json传回前台 *
	* @author min.li 
	* @date 2014-10-11 
	* @param list_all_user 
	* @param list_role_name
	* @param list_ps_name
	* @param user_list
	*/ 
	public  String getUserInformation(){
		
		ps_informationService = new PS_informationServiceImpl();
		roleService = new RoleServiceImpl();
		userService = new M_userServiceImpl();//以上为初始化
		List<M_user> list_all_user = userService.getAllUser();//得到所有用户信息
		List<String> list_role_name = new ArrayList<String>();//得到用户名称
		List<String> list_ps_name= new ArrayList<String>();//这两个list保存查到的name 
		List<user> user_list = new ArrayList<user>();
		for(int i=0;i<list_all_user.size();i++){
			int ps_id = list_all_user.get(i).getPS_id();
			int role_id = list_all_user.get(i).getRole_id();//获取每个用户对应的所属电站和角色id
			String ps_name="",role_name="";
			if(ps_id == 0)ps_name = "全部";
			else ps_name = ps_informationService.getStationNameById(ps_id);//获取电站id对应的name
			role_name = roleService.getRoleNameById(role_id);//获取角色id对应的角色名称
			list_role_name.add(role_name);
			list_ps_name.add(ps_name);
		}
		for(int i=0;i<list_all_user.size();i++){
			user u = new user();
			u.setId(list_all_user.get(i).getId());
			u.setEmail(list_all_user.get(i).getEmail());
			u.setName(list_all_user.get(i).getName());
			u.setPassword(list_all_user.get(i).getPassword());
			u.setPs_name(list_ps_name.get(i));
			u.setRole_name(list_role_name.get(i));
			u.setTelephone(list_all_user.get(i).getTelephone());
			u.setUser_name(list_all_user.get(i).getUser_name());
			user_list.add(u);
		}
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", user_list.size());
		object.put("rows", user_list);
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
	
	/** 
	*将需要显示的用户信息通过json传回前台*
	* @author min.li 
	* @date 2014-10-11 
	* @param user_name 
	* @param ps_id
	* @param list_part_user
	* @param list_role_name
	* @param list_ps_name
	* @param user_list
	*/ 
	public  String getUserPartInformation(){
		
			HttpServletRequest request =ServletActionContext.getRequest();
			HttpServletResponse response =ServletActionContext.getResponse();	
			try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String user_name = request.getParameter("user_name");
		try {
			user_name = java.net.URLDecoder.decode(user_name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		roleService = new RoleServiceImpl();
		userService = new M_userServiceImpl();
		int ps_id = userService.getPsIdByUsername(user_name);
		List<M_user> list_part_user = userService.getPartUserByPsId(ps_id);//得到该电站所有用户信息
		List<String> list_role_name = new ArrayList<String>();
		List<String> list_ps_name= new ArrayList<String>();//这两个list保存查到的name 
		List<user> user_list = new ArrayList<user>();
		for(int i=0;i<list_part_user.size();i++){
			ps_id = list_part_user.get(i).getPS_id();
			int role_id = list_part_user.get(i).getRole_id();//获取每个用户对应的所属电站和角色id
			String ps_name="",role_name="";
			ps_name = ps_informationService.getStationNameById(ps_id);//获取电站id对应的name
			role_name = roleService.getRoleNameById(role_id);
			list_role_name.add(role_name);
			list_ps_name.add(ps_name);
		}
		for(int i=0;i<list_part_user.size();i++){
			user u = new user();
			u.setId(list_part_user.get(i).getId());
			u.setEmail(list_part_user.get(i).getEmail());
			u.setName(list_part_user.get(i).getName());
			u.setPassword(list_part_user.get(i).getPassword());
			u.setPs_name(list_ps_name.get(i));
			u.setRole_name(list_role_name.get(i));
			u.setTelephone(list_part_user.get(i).getTelephone());
			u.setUser_name(list_part_user.get(i).getUser_name());
			user_list.add(u);
		}
		ArrayList list = new ArrayList();
		list.add(list_part_user);
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", user_list.size());
		object.put("rows", user_list);
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
	
	public String addUser()
	{
		/** 
		*新建或编辑用户后保存*
		* @author min.li 
		* @date 2014-10-11 
		* @param m_user 
		* @param count_user
		* @param ps_id
		* @param role_id
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
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String role_name = request.getParameter("role_name");
		String ps_name = request.getParameter("ps_name");
		try {
			user_name = java.net.URLDecoder.decode(user_name, "UTF-8");
			password = java.net.URLDecoder.decode(password, "UTF-8");
			name = java.net.URLDecoder.decode(name, "UTF-8");
			telephone = java.net.URLDecoder.decode(telephone, "UTF-8");
			email = java.net.URLDecoder.decode(email, "UTF-8");
			role_name = java.net.URLDecoder.decode(role_name, "UTF-8");
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		roleService = new RoleServiceImpl();
		userService = new M_userServiceImpl();
		count_userService = new Count_userServiceImpl();//以上为初始化
		M_user m_user = new M_user();
		Count_user count_user= new Count_user();
		int ps_id;
		int role_id = roleService.getRoleIdByName(role_name);//获取角色名称对应的角色id
		String result = "";
		if(ps_name.equals("全部"))
			{ps_id = 0;//ps_id为0表示不属于某个电站
			if(id!=null){
				m_user.setId(Integer.parseInt(id));
			}
			m_user.setEmail(email);
			m_user.setName(name);
			m_user.setPassword(password);
			m_user.setTelephone(telephone);
			m_user.setUser_name(user_name);//将数据存入m_user类中
			m_user.setPS_id(ps_id);
			m_user.setRole_id(role_id);
			userService.addUser(m_user);
			result = "用户保存成功！";
			}
		else 
			{ps_id = ps_informationService.getPS_idByName(ps_name);//获取电站名称对应的电站id
				if(id!=null){
					m_user.setId(Integer.parseInt(id));
				}
				m_user.setEmail(email);
				m_user.setName(name);
				m_user.setPassword(password);
				m_user.setTelephone(telephone);
				m_user.setUser_name(user_name);//将数据存入m_user类中
				m_user.setPS_id(ps_id);
				m_user.setRole_id(role_id);
				userService.addUser(m_user);
				result = "用户保存成功！";
			}			
		ArrayList list = new ArrayList();
		list.add(result);		
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
	*判断用户名是否符合要求*
	* @author min.li 
	* @date 2014-10-11 
	* @param m_user 
	* @param count_user
	* @param ps_id
	* @param role_id
	*/ 
	public String checkUserNameIsLegal(){
		
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String user_name = request.getParameter("user_name");
		String ps_name = request.getParameter("ps_name");
		try {
			user_name = java.net.URLDecoder.decode(user_name, "UTF-8");
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userService = new M_userServiceImpl();
		ps_informationService = new PS_informationServiceImpl();
		String result = "";//用result存放提示信息，并将其传回前台
		if(ps_name.equals("全部")){//判断如果是超级用户，则ps_id=0的用户名不存在时返回correct
			if(!(userService.checkUserNameExist(user_name)))result="correct";//判断用户名是否存在
		}
		else{//如果是电站管理员，电站内部用户不重名时返回correct
			int ps_id = ps_informationService.getPS_idByName(ps_name);//根据电站名称获得对应的电站的id
			if(!(userService.checkUserNameExistById(user_name,ps_id)))result="correct";//判断电站的id里用户名是否存在
			else result="wrong";
		}
		ArrayList list = new ArrayList();
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
	
	public String deleteUser(){
		/** 
		*删除用户*
		* @author min.li 
		* @date 2014-10-11 
		* @param id 
		* @param ps_id		
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String user_id =  request.getParameter("id");
		int id = Integer.parseInt(user_id);		
		userService = new M_userServiceImpl();
		count_userService = new Count_userServiceImpl();		
		int ps_id = userService.getPsIdByUserId(id);
		userService.deleteUserById(id);//根据user_id删除该用户		
		return null;
	}
	
	public String updateUser()
	{
		/** 
		*新建用户*
		* @author min.li 
		* @date 2014-10-11 
		* @param ps_id 
		* @param ex_ps_id
		* @param role_id				
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String role_name = request.getParameter("role_name");
		String ps_name = request.getParameter("ps_name");
		String ex_user_name = request.getParameter("ex_user_name");
		String ex_ps_name = request.getParameter("ex_ps_name");
		try {
			user_name = java.net.URLDecoder.decode(user_name, "UTF-8");
			password = java.net.URLDecoder.decode(password, "UTF-8");
			name = java.net.URLDecoder.decode(name, "UTF-8");
			telephone = java.net.URLDecoder.decode(telephone, "UTF-8");
			email = java.net.URLDecoder.decode(email, "UTF-8");
			role_name = java.net.URLDecoder.decode(role_name, "UTF-8");
			ps_name = java.net.URLDecoder.decode(ps_name, "UTF-8");//以上为获取前台数据，转码
			ex_user_name = java.net.URLDecoder.decode(ex_user_name, "UTF-8");
			ex_ps_name = java.net.URLDecoder.decode(ex_ps_name, "UTF-8");
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		roleService = new RoleServiceImpl();
		userService = new M_userServiceImpl();//以上为初始化
		int ps_id;
		if(ps_name.equals("全部"))ps_id = 0;//ps_id为0表示不属于某个电站
		else ps_id = ps_informationService.getPS_idByName(ps_name);//根据电站名称获得对应电站id		
		int ex_ps_id;
		if(ex_ps_name.equals("全部"))ex_ps_id = 0;//ps_id为0表示不属于某个电站
		else ex_ps_id = ps_informationService.getPS_idByName(ex_ps_name);//根据电站名称获得对应电站id			
		int role_id = roleService.getRoleIdByName(role_name);//根据角色名称获得对应角色id
		userService.updateUserByNameAndPS_id(user_name,password,name,telephone,email,role_id,ps_id,ex_user_name,ex_ps_id);
		return null;
	}
	
	public String queryUserByName(){
		/** 
		*查询用户信息*
		* @author min.li 
		* @date 2014-10-11 
		* @param list_user 
		* @param list_role_name
		* @param list_ps_name
		* @param user_list 				
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String user_name = request.getParameter("user_name");
		try {
			user_name = java.net.URLDecoder.decode(user_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		roleService = new RoleServiceImpl();
		userService = new M_userServiceImpl();//以上为初始化
		List<M_user> list_user = userService.getUserByUser_name(user_name);//得到所有用户信息
		List<String> list_role_name = new ArrayList<String>();
		List<String> list_ps_name= new ArrayList<String>();//这两个list保存查到的name 
		List<user> user_list = new ArrayList<user>();
		for(int i=0;i<list_user.size();i++){
			int ps_id = list_user.get(i).getPS_id();
			int role_id = list_user.get(i).getRole_id();//获取每个用户对应的所属电站和角色id
			String ps_name="",role_name="";
			if(ps_id == 0)ps_name = "全部";
			else ps_name = ps_informationService.getStationNameById(ps_id);//获取电站id对应的name
			role_name = roleService.getRoleNameById(role_id);//根据角色id获取角色名称
			list_role_name.add(role_name);
			list_ps_name.add(ps_name);
		}
		for(int i=0;i<list_user.size();i++){
			user u = new user();
			u.setId(list_user.get(i).getId());
			u.setEmail(list_user.get(i).getEmail());
			u.setName(list_user.get(i).getName());
			u.setPassword(list_user.get(i).getPassword());
			u.setPs_name(list_ps_name.get(i));
			u.setRole_name(list_role_name.get(i));
			u.setTelephone(list_user.get(i).getTelephone());
			u.setUser_name(list_user.get(i).getUser_name());
			user_list.add(u);
		}		
		JSONObject obj=JSONObject.fromObject("{}");		
		obj.put("total", user_list.size());
		obj.put("rows", user_list);
		System.out.println(obj);
		try {
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(obj.toString());			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String queryUserByPs_name(){
		/** 
		*根据电站和用户名进行双重查询*
		* @author min.li 
		* @date 2014-10-11 
		* @param user_ps_id 
		* @param list_user
		* @param list_ps_name
		* @param user_list 				
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String user_ps_name = request.getParameter("ps_name");
		String user_user_name = request.getParameter("user_name");
		try {
			user_ps_name = java.net.URLDecoder.decode(user_ps_name, "UTF-8");
			user_user_name = java.net.URLDecoder.decode(user_user_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();
		roleService = new RoleServiceImpl(); 
		userService = new M_userServiceImpl();//以上为初始化
		int user_ps_id = 0;
		List<M_user> list_user = new ArrayList<M_user>();		
		if(user_ps_name!=""&user_user_name!=""){//用户名和电站名称都不为空，进行查询
			if(user_ps_name.equals("全部")){//用户名和电站名称都不为空，而且电站名称为全部，进行查询
				user_ps_id = 0;
				list_user = userService.getUserByPSandname(user_ps_id,user_user_name);//根据用户名和电站名称为全部得到所有用户信息
			}
			else{//用户名不为空，电站名称不是全部
				 user_ps_id = ps_informationService.getPS_idByName(user_ps_name);//根据用户的电站名获取电站id
				 list_user = userService.getUserByPSandname(user_ps_id,user_user_name);//根据用户名和电站名称得到所有用户信息				 
			}
		}
		else{//用户名和电站名称有空
				if(user_ps_name!="")
				{//用户名为空，根据电站名称查询
					if(user_ps_name.equals("全部")){//电站名称为全部
						user_ps_id = 0;
						list_user = userService.getUserByPS_name(user_ps_id);					
					}
					else{//电站名称为空，根据用户名查询
						 user_ps_id = ps_informationService.getPS_idByName(user_ps_name);//根据用户名获取
						 list_user = userService.getUserByPS_name(user_ps_id);//根据电站名称得到所有用户信息						
					}					
				}
				else{//电站名称为空，根据用户名查询 
					list_user = userService.getAllUserbyname(user_user_name);//根据用户名得到所有用户信息					
				    }
		}
		List<String> list_role_name = new ArrayList<String>();
		List<String> list_ps_name= new ArrayList<String>();//这两个list保存查到的name 
		List<user> user_list = new ArrayList<user>();
		for(int i=0;i<list_user.size();i++){
			int ps_id = list_user.get(i).getPS_id();
			int role_id = list_user.get(i).getRole_id();//获取每个用户对应的所属电站和角色id
			String ps_name="",role_name="";
			if(ps_id == 0)ps_name = "全部";
			else ps_name = ps_informationService.getStationNameById(ps_id);//获取电站id对应的name
			role_name = roleService.getRoleNameById(role_id);
			list_role_name.add(role_name);//通过json将用户名传回到前台
			list_ps_name.add(ps_name);//通过json将电站名称传回到前台
		}
		for(int i=0;i<list_user.size();i++){
			user u = new user();
			u.setId(list_user.get(i).getId());
			u.setEmail(list_user.get(i).getEmail());
			u.setName(list_user.get(i).getName());
			u.setPassword(list_user.get(i).getPassword());
			u.setPs_name(list_ps_name.get(i));
			u.setRole_name(list_role_name.get(i));
			u.setTelephone(list_user.get(i).getTelephone());
			u.setUser_name(list_user.get(i).getUser_name());
			user_list.add(u);//通过json将用户信息传回到前台
		}		
		JSONObject obj=JSONObject.fromObject("{}");		
		obj.put("total", user_list.size());
		obj.put("rows", user_list);		
		try {
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(obj.toString());			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/** 
	*根据角色显示对应的电站名称*
	* @author min.li 
	* @date 2014-10-11 
	* @param list_ps_name 
	* @param ps_name			
	*/ 	
	public String  setPSbyrole()
	{	
		
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String role_name = request.getParameter("role_name");
		try {
			role_name = java.net.URLDecoder.decode(role_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		ps_informationService = new PS_informationServiceImpl();		
		List<String> list_ps_name= new ArrayList<String>();//这个list保存查到的name
		String ps_name="";//用ps_name存放提示信息，并将其传回前台
		if(role_name.equals("超级管理员")||role_name.equals("中心查看人员"))
		{//如果用户角色是超级管理员或者中心查看人员，则可以查看所有电站
			ps_name = "全部";list_ps_name.add(ps_name);
		}
		else{//如果用户角色不是超级管理员或者中心查看人员，则只可以查看某个电站
			list_ps_name = ps_informationService.getAllStationName();//获取电站名称
		}		
		ArrayList list = new ArrayList();
		list.add(list_ps_name);//通过json将电站名称传回到前台
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
	
	public String getPSNameByUsername()
	{
		/** 
		*根据用户名获得电站名称*
		* @author min.li 
		* @date 2014-10-11 
		* @param user_name 
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
		String user_name = request.getParameter("username");		
		try {
			user_name = java.net.URLDecoder.decode(user_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps_informationService = new PS_informationServiceImpl();		
		userService = new M_userServiceImpl();
		user_name = user_name.replaceAll(" ","");		
		Integer ps_id = userService.getPsIdByUsername(user_name);//根据用户名获得电站id
		String ps_name = ps_informationService.getStationNameById(ps_id);//根据电站id获得电站名称
		ArrayList list = new ArrayList();		
		list.add(ps_name);	//通过json将校验结果传回到前台显示	
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
           
	public String getPSIDByUserName(){ 
		/** 
		*根据用户名查询电站ID*
		* @author min.li 
		* @date 2014-10-11 
		* @param username 
		* @param ps_id			
		*/ 	
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username =  request.getParameter("username");
		try {
			username = java.net.URLDecoder.decode(username, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		userService = new M_userServiceImpl();		
		int ps_id = userService.getPsIdByUsername(username);//根据用户名获得电站id
		ArrayList list = new ArrayList();
		list.add(ps_id);//通过json将校验结果传回到前台显示
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
	* 通过角色查询用户
	*/ 
	public String getUserByRoleId(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			String roleId = request.getParameter("roleId");
			roleId = java.net.URLDecoder.decode(roleId, "UTF-8");
			userService = new M_userServiceImpl();//以上为初始化
			List<M_user> list_user =userService.getUserByRoleId(Integer.parseInt(roleId));
			JSONObject obj=JSONObject.fromObject("{}");		
			obj.put("total", list_user.size());
			obj.put("rows", list_user);	
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(obj.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}


