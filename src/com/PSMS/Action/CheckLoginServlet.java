package com.PSMS.Action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Hibernate.M_user;
/**
 * 检查用户是否登录
 * @author Andy
 *
 */
public class CheckLoginServlet extends HttpServlet implements Filter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3189763404611244449L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		M_user user=(M_user) session.getAttribute("user");
		if(user==null){
			//resp.sendRedirect("Login.jsp");
			//req.getRequestDispatcher("Login.jsp").forward(req,resp);//toLogin
			resp.sendRedirect(((HttpServletRequest) request).getContextPath() + "/toLogin.action"); 
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
