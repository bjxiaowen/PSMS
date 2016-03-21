package com.test;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class SendMessage {

	public static void main(String[] args) {
		String url="http://www.zjysms.com/send/ssend.asp?name=zxnygnjs&pwd=zxnygnjs87&dst=13520029132&msg=我在测试数据";
		// 构造HttpClient的实例  
        HttpClient httpClient = new HttpClient(); 
        GetMethod getMethod = new GetMethod(url);  
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler()); 
        int statusCode;
		try {
			statusCode = httpClient.executeMethod(getMethod);
			 System.out.println("statusCode:"+statusCode);
		        if (statusCode != HttpStatus.SC_OK) {  
		            System.err.println("Method failed: "  
		                    + getMethod.getStatusLine());  
		        }  
		        // 读取内容  
		        byte[] responseBody = getMethod.getResponseBody();  
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
}
