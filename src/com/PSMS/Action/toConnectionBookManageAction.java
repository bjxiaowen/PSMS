/** * * 
* 文件名称: toConnectionBookManageAction.java *
* 
* 客户通讯录管理，增删改查客户信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-22 下午6:55:40 *
* * @author liu.yang 
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
import com.PSMS.Hibernate.Connection_book;
import com.PSMS.Hibernate.M_user;
import com.PSMS.Service.Connection_bookService;
import com.PSMS.Service.Connection_bookServiceImpl;

import com.PSMS.Service.PS_informationServiceImpl;



public class toConnectionBookManageAction {
	/** 
	*客户通讯录管理，加载页面,将编辑客户 判断客户是否已存在,需要显示的客户信息通过json传回前台,删除客户,新建客户,根据公司名称查询客户信息*
	* @author liu.yang 
	* @date 2014-10-22
	* @param connectionBookService 	
	*/ 

	private Connection_bookService connectionBookService;
	
	public String toConnectionBookManage(){
		/** 
		*加载页面*
		* @author liu.yang 
		* @date 2014-10-22
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "success";
	}
	
	public String getConnectionBookInformation(){
		/** 
		* 将需要显示的客户信息通过json传回前台*
		* @author liu.yang 
		* @date 2014-10-22
		* @param list_all_information 	
		*/ 
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connectionBookService = new Connection_bookServiceImpl();//初始化全局变量
		List<Connection_book> list_all_information = connectionBookService.getAllInformation();//得到所有用户信息
		JSONObject object=JSONObject.fromObject("{}");		
		object.put("total", list_all_information.size());
		object.put("rows", list_all_information);
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
	
	public String addConnectionBook(){
		/** 
		* 新建客户*
		* @author liu.yang 
		* @date 2014-10-22
		* @param list_all_information 	
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
		String id =request.getParameter("id");
		String company_name = request.getParameter("company_name");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String fax = request.getParameter("fax");
		String QQ = request.getParameter("QQ");
		String home_page = request.getParameter("home_page");
		String addr = request.getParameter("addr");
		String more = request.getParameter("more");
		try {
			company_name = java.net.URLDecoder.decode(company_name, "UTF-8");
			telephone = java.net.URLDecoder.decode(telephone, "UTF-8");
			email = java.net.URLDecoder.decode(email, "UTF-8");
			fax = java.net.URLDecoder.decode(fax, "UTF-8");
			QQ = java.net.URLDecoder.decode(QQ, "UTF-8");
			home_page = java.net.URLDecoder.decode(home_page, "UTF-8");
			addr = java.net.URLDecoder.decode(addr, "UTF-8");
			more = java.net.URLDecoder.decode(more, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connectionBookService = new Connection_bookServiceImpl();		//初始化
		Connection_book connection_book = new Connection_book();
		connection_book.setCompany_name(company_name);
		connection_book.setTelephone(telephone);
		connection_book.setEmail(email);
		connection_book.setFax(fax);
		connection_book.setQQ(QQ);
		connection_book.setHome_page(home_page);
		connection_book.setAddr(addr);
		connection_book.setMore(more);		//将数据存入connection_book类中
		connectionBookService.addConnectionBook(connection_book);
		ArrayList list = new ArrayList();
		String result = "客户保存成功！";//用result存放提示信息，并将其传回前台
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
	
     public String editConnectionBook(){
    	 /** 
 		* 编辑客户*
 		* @author liu.yang 
 		* @date 2014-10-22
 		* @param id1 
 	    * @param connection_book
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
		String id =request.getParameter("id");
		String company_name = request.getParameter("company_name");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String fax = request.getParameter("fax");
		String QQ = request.getParameter("QQ");
		String home_page = request.getParameter("home_page");
		String addr = request.getParameter("addr");
		String more = request.getParameter("more");
		try {
			company_name = java.net.URLDecoder.decode(company_name, "UTF-8");
			telephone = java.net.URLDecoder.decode(telephone, "UTF-8");
			email = java.net.URLDecoder.decode(email, "UTF-8");
			fax = java.net.URLDecoder.decode(fax, "UTF-8");
			QQ = java.net.URLDecoder.decode(QQ, "UTF-8");
			home_page = java.net.URLDecoder.decode(home_page, "UTF-8");
			addr = java.net.URLDecoder.decode(addr, "UTF-8");
			more = java.net.URLDecoder.decode(more, "UTF-8");			//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		connectionBookService = new Connection_bookServiceImpl();
		Connection_book connection_book = new Connection_book();
		if(id!=null){
			int id1 =  Integer.parseInt(id);
			connection_book.setId(Integer.parseInt(id));
		}
    	connection_book.setCompany_name(company_name);
		connection_book.setTelephone(telephone);
		connection_book.setEmail(email);
		connection_book.setFax(fax);
		connection_book.setQQ(QQ);
		connection_book.setHome_page(home_page);
		connection_book.setAddr(addr);
		connection_book.setMore(more);		//将数据存入connection_book类中
		connectionBookService.addConnectionBook(connection_book);
		ArrayList list = new ArrayList();
		String result = "客户编辑成功！";//用result存放提示信息，并将其传回前台
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

	public String deleteConnectionBook(){
		 /** 
 		* 删除客户*
 		* @author liu.yang 
 		* @date 2014-10-22
 		* @param company_id 
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
		String company_id =  request.getParameter("id");		
		int id = Integer.parseInt(company_id);
		connectionBookService = new Connection_bookServiceImpl();		
		connectionBookService.deleteConnectionBookById(id);//根据user_id删除该用户
		return null;
	}

	public String checkConnectionBookNameIsLegal(){
		 /** 
 		* 判断客户是否已存在*
 		* @author liu.yang 
 		* @date 2014-10-22
 		* @param company_name 
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
		String company_name = request.getParameter("company_name");		
		try {
			company_name = java.net.URLDecoder.decode(company_name, "UTF-8");//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connectionBookService = new Connection_bookServiceImpl();
		String result = "";//用result存放提示信息，并将其传回前台
		if(company_name=="")result="correct";
			else result="wrong";		
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


	public String queryConnectionBookByName(){
		 /** 
			* 根据公司名称查询客户信息*
			* @author liu.yang 
			* @date 2014-10-22
			* @param list_company 
		    * @param company_list
			*/ 
		
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String company_name = request.getParameter("company_name");
		try {
			company_name = java.net.URLDecoder.decode(company_name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		connectionBookService = new Connection_bookServiceImpl();
		List<Connection_book> list_company = connectionBookService.getConnectionBookByCompany_name(company_name);//根据公司名称得到所有客户名
		List<Connection_book> company_list = new ArrayList<Connection_book>();
		for(int i=0;i<list_company.size();i++){
			Connection_book u = new Connection_book();
			u.setCompany_name(list_company.get(i).getCompany_name());
			u.setTelephone(list_company.get(i).getTelephone());
			u.setEmail(list_company.get(i).getEmail());
			u.setFax(list_company.get(i).getFax());
			u.setQQ(list_company.get(i).getQQ());
			u.setHome_page(list_company.get(i).getHome_page());
			u.setAddr(list_company.get(i).getAddr());
			u.setMore(list_company.get(i).getMore());
			company_list.add(u);
		}		
		JSONObject obj=JSONObject.fromObject("{}");		
		obj.put("total", company_list.size());
		obj.put("rows", company_list);		
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
	}	
		
			
		

