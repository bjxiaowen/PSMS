package com.PSMS.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.PSMS.Dao.ISendRecordDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.SendRecord;

import java.net.URLEncoder;
import java.util.Date;

public class HttpSender {
	
	public static void mian(String[] args){
		try {
			send("http://www.zjysms.com/send/gsend.asp?", "zxnygnjs", "zxnygnjs87", "13520029132", "我在测试短信接口", null, null, "ccdx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param url 
	 * @param account 企业用户登录名称 
	 * @param pswd 企业用户登录密码
	 * @param mobile 群发目标手机号
	 * @param msg 发送短信内容
	 * @param sender 长号码 
	 * @param time 定时时间
	 * @param txt  短信类型
	 * @return 
	 * @throws Exception
	 */
	public static String send(String url, String account, String pswd, String mobile, String msg,String sender, String time, String txt) throws Exception {
		HttpClient client = new HttpClient();
		GetMethod method =null;
		try {
			method = new GetMethod(url+"name="+account+"&pwd="+pswd+"&dst="+mobile+"&sender="+sender+"&time="+time+"&txt="+txt+"&msg="+URLEncoder.encode(msg, "GB2312"));
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			int statusCode  = client.executeMethod(method);
			System.out.println("statusCode:"+statusCode);
			if (statusCode  != HttpStatus.SC_OK) {
				return method.getStatusLine().toString();
			}
		    // 读取内容
            byte[] responseBody = method.getResponseBody();
            System.out.println("hello:"+new String(responseBody, "GB2312"));
            // 处理内容
            return (new String(responseBody, "GB2312"));
		} finally {
			method.releaseConnection();
		}

	}
	
	/**
	 * 
	 * @param email 
	 * @param emailContent
	 * @param emailStatus
	 * @param tel
	 * @param noteContent
	 * @param noteStatus
	 */
	public static void addSendRecord(String email,String emailContent,String emailStatus,String tel,String noteContent,String noteStatus){
		SendRecord send=new SendRecord();
		send.setId("Sd"+IDGenerate.getId());
		send.setEmail(email);
		send.setEmailContent(emailContent);
		send.setEmailDate(new Date());
		send.setEmailStatus(emailStatus);
		send.setTel(tel);
		send.setNoteContent(noteContent);
		send.setNoteDate(new Date());
		send.setNoteStatus(noteStatus);
		ISendRecordDao dao=DAOFactory.getSendRecordDaoInstance();
		try {
			dao.add(send);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
