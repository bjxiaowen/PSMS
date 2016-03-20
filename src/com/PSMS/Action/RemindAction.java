package com.PSMS.Action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class RemindAction {

	public String remind(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "success";
	}
}
