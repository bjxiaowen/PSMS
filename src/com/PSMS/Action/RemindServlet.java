package com.PSMS.Action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PSMS.util.GetTime;

public class RemindServlet extends HttpServlet implements Filter  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String allow;


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String allowDate=getAllow();
        String sDate=GetTime.getCurrentTime3();
        if(allowDate.equals(sDate)){
        	resp.sendRedirect(((HttpServletRequest) request).getContextPath() + "/remind.action"); 
        	//request.getRequestDispatcher("/remind.action");
        	//request.getServletContext().getRequestDispatcher ("/remind.jsp"); 
        	return;
        }else{
        	 chain.doFilter(request, response);
        }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		setAllow(filterConfig.getInitParameter("allow"));
	}

	public String getAllow() {
		return allow;
	}

	public void setAllow(String allow) {
		this.allow = allow;
	}
	
}
