package com.PSMS.Action;

import java.io.IOException;
import java.text.DateFormat;
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
import com.PSMS.util.TimeHelper;

public class RemindServlet extends HttpServlet implements Filter  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String allow;


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException{
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if(TimeHelper.getAllow()){
        	resp.sendRedirect(((HttpServletRequest) request).getContextPath() + "/remind.action"); 
        	return;
        }else{
        	 chain.doFilter(request, response);
        }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		filterConfig.getServletContext().setInitParameter("allow", TimeHelper.getAllow()+"");
		setAllow(filterConfig.getInitParameter("allow"));
	}

	public String getAllow() {
		return allow;
	}

	public void setAllow(String allow) {
		this.allow = allow;
	}
	
}
