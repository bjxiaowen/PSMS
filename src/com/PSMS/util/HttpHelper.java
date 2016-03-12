package com.PSMS.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class HttpHelper {

	/* @brief requesetFactory */
	/* @return request */
	public static HttpServletRequest requestFactory() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return request;
	}

	/* @brief responseFactory */
	public static HttpServletResponse responseFactory() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		return response;
	}

	/* @brief printJson */
	public static void print(String result) {
		HttpServletResponse response = HttpHelper.responseFactory();
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
