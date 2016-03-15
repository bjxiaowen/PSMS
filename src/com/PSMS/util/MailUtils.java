package com.PSMS.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.activation.*;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
/**
 * <p>
 * Title: 使用javamail发送邮件
 * </p>
 */
public class MailUtils {
	
//	  sendmail.setHost("smtp.yeah.net");
//	  sendmail.setUserName("zonergy@yeah.net");
//	  sendmail.setPassWord("zxny2016");
//	  sendmail.setFrom("zonergy@yeah.net");
 
 private String to = "";// 收件人
 private String cc="";//抄送人
 private String bcc="";//暗送人
 private String from = "zonergy@yeah.net";// 发件人
 private String host = "smtp.yeah.net";// smtp主机
 private String username = "zonergy@yeah.net";//用户名
 private String password = "zxny2016";//密码
 private String filename = "";// 附件文件名
 private String subject = "";// 邮件主题
 private String content = "";// 邮件正文
 private Vector<String> file = new Vector<String>();// 附件文件集合
 private boolean sendhtml=false;//邮件发送方式 （text/html）
 
 
 
 /**
  * <br>
  * 方法说明：默认构造器 <br>
  * 输入参数： <br>
  * 返回类型：
  */
 public MailUtils() {
 }
 /**
  * <br>
  * 方法说明：构造器，提供直接的参数传入 <br>
  * 输入参数： <br>
  * 返回类型：
  */
 public MailUtils(String to, String from, String smtpServer,String username, String password, String subject, String content) {
  this.to = to;
  this.from = from;
  this.host = smtpServer;
  this.username = username;
  this.password = password;
  this.subject = subject;
  this.content = content;
 }
 /**
  * <br>
  * 方法说明：设置邮件服务器地址 <br>
  * 输入参数：String host 邮件服务器地址名称 <br>
  * 返回类型：
  */
 public void setHost(String host) {
  this.host = host;
 }
 /**
  * <br>
  * 方法说明：设置登录服务器校验密码 <br>
  * 输入参数： <br>
  * 返回类型：
  */
 public void setPassWord(String pwd) {
  this.password = pwd;
 }
 /**
  * <br>
  * 方法说明：设置登录服务器校验用户 <br>
  * 输入参数： <br>
  * 返回类型：
  */
 public void setUserName(String usn) {
  this.username = usn;
 }
 /**
  * <br>
  * 方法说明：设置邮件发送目的邮箱 <br>
  * 输入参数： <br>
  * 返回类型：
  */
 public void setTo(String to) {
  this.to = to;
 }
 /**
  * <br>
  * 方法说明：设置邮件发送源邮箱 <br>
  * 输入参数： <br>
  * 返回类型：
  */
 public void setFrom(String from) {
  this.from = from;
 }
 /**
  * <br>
  * 方法说明：设置邮件主题 <br>
  * 输入参数： <br>
  * 返回类型：
  */
 public void setSubject(String subject) {
  this.subject = subject;
 }
 /**
  * <br>
  * 方法说明：设置邮件内容 <br>
  * 输入参数： <br>
  * 返回类型：
  */
 public void setContent(String content) {
  this.content = content;
 }
 
 /**
  * <br>
  * 方法说明：抄送人 <br>
  * 输入参数： <br>
  * 返回类型：
  */
 public String getCc() {
  return cc;
 }
  /**
   * <br>
   * 方法说明：抄送人 <br>
   * 输入参数： <br>
   * 返回类型：
   */
 public void setCc(String cc) {
  this.cc = cc;
 }
  /**
   * <br>
   * 方法说明：密送人 <br>
   * 输入参数： <br>
   * 返回类型：
   */
 public String getBcc() {
  return bcc;
 }
  /**
   * <br>
   * 方法说明：密送人 <br>
   * 输入参数： <br>
   * 返回类型：
   */
 public void setBcc(String bcc) {
  this.bcc = bcc;
 }
 /**
  * <br>
  * 方法说明：把主题转换为中文 <br>
  * 输入参数：String strText <br>
  * 返回类型：
  */
 public String transferChinese(String strText) {
  try {
   strText = MimeUtility.encodeText(new String(strText.getBytes(),"UTF-8"), "UTF-8", "B");
// strText = MimeUtility.encodeText(new String(strText.getBytes(),"GB2312"), "GB2312", "B");
  } catch (Exception e) {
   e.printStackTrace();
  }
  return strText;
 }
 /**
  * <br>
  * 方法说明：往附件组合中添加附件 <br>
  * 输入参数： <br>
  * 返回类型：
  */
 public void attachfile(String fname) {
  file.addElement(fname);
 }
 /**
  * <br>
  * 方法说明：发送方式 <br>
  * 输入参数： <br>
  * 返回类型：boolean 成功为true，反之为false
  */
 public void setSendhtml(boolean sendhtml) {
  this.sendhtml = sendhtml;
 }
 
 /**
  * <br>
  * 方法说明：发送邮件 <br>
  * 输入参数： <br>
  * 返回类型：boolean 成功为true，反之为false
  */
public boolean isSendhtml() {
 return sendhtml;
}
/**
  * <br>
  * 方法说明：发送邮件 <br>
  * 输入参数： <br>
  * 返回类型：boolean 成功为true，反之为false
  */
 public boolean sendMail() {
  /***
   * 由于在服务器发送邮件报错才加了以下代码
   */
  MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
     mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
     mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
     mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
     mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
     mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
     CommandMap.setDefaultCommandMap(mc);
 
  // 构造mail session
  Properties props = new Properties();
  props.put("mail.smtp.host", host);
  props.put("mail.smtp.auth", "true");
  Session session = Session.getDefaultInstance(props,
    new Authenticator() {
     public PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(username, password);
     }
    });
  try {
    // 构造MimeMessage 并设定基本的值
    MimeMessage msg = new MimeMessage(session);
    msg.setFrom(new InternetAddress(from));
    // //这个只能是给一个人发送email
    msg.setRecipients(Message.RecipientType.TO,getAddress(to));// 多个收件人
    msg.setRecipients(Message.RecipientType.CC,getAddress(cc));//抄送人
    msg.setRecipients(Message.RecipientType.BCC,getAddress(bcc));//暗送人
 // subject = transferChinese(subject);
    msg.setSubject(subject);
    // 构造Multipart
    Multipart mp = new MimeMultipart();
    // 向Multipart添加正文
//	 BodyPart html = new MimeBodyPart();
//	 // 设置HTML内容
//	 html.setContent(content, "text/html; charset=utf-8");
//	 mp.addBodyPart(html);
    MimeBodyPart mbpContent = new MimeBodyPart();
    String sendtype="text/plain;charset=utf-8";
    if(sendhtml){
     sendtype="text/html;charset=utf-8";
    }
    mbpContent.setContent(content, sendtype);// text/html
//	 向MimeMessage添加（Multipart代表正文）
    mp.addBodyPart(mbpContent);
    // 向Multipart添加附件
 // msg.setText(text);
    Enumeration<String> efile = file.elements();
    while (efile.hasMoreElements()) {
     MimeBodyPart mbpFile = new MimeBodyPart();
     filename = efile.nextElement().toString();
     FileDataSource fds = new FileDataSource(filename);
     mbpFile.setDataHandler(new DataHandler(fds));
     //mbpFile.setFileName(new String(fds.getName().getBytes("UTF-8"),"UTF-8"));
     mbpFile.setFileName(MimeUtility.encodeText(fds.getName()));//修改了中文附件乱码的问题
     // 向MimeMessage添加（Multipart代表附件）
     mp.addBodyPart(mbpFile);
    }
    file.removeAllElements();
    // 向Multipart添加MimeMessage
    msg.setContent(mp);
    msg.setSentDate(new Date());
    msg.saveChanges();
    // 发送邮件
    Transport transport = session.getTransport("smtp");
    transport.connect(host, username, password);
    transport.sendMessage(msg, msg.getAllRecipients());
    transport.close();
  } catch (Exception mex) {
   mex.printStackTrace();
   return false;
  }
  return true;
 }
 
 
 
 /**
  * <br>
  * 方法说明：获取邮件地址<br>
  * 输入参数： <br>
  * 返回类型：
 * @throws Exception
  */
 public Address[] getAddress(String address) throws Exception{
  Address[] ads = null;
     String[] addArray = address.split(";");
     if (address!=null&&!address.equals("")&&addArray != null){
      ads = new InternetAddress[addArray.length];
      for(int i=0; i<addArray.length; i++){
          String s = addArray[i];
          ads[i] = new InternetAddress(s);
      }
     }
     return ads;
 }
 /**
  * <br>
  * 方法说明：主方法，用于测试 <br>
  * 输入参数： <br>
  * 返回类型：
 * @throws UnsupportedEncodingException
  */
public static void main(String[] args) throws UnsupportedEncodingException {
 MailUtils sendmail = new MailUtils();
//  sendmail.setHost("smtp.yeah.net");
//  sendmail.setUserName("zonergy@yeah.net");
//  sendmail.setPassWord("zxny2016");
//  sendmail.setFrom("zonergy@yeah.net");
  sendmail.setTo("pubservice@126.com");
  sendmail.setSendhtml(false);
  sendmail.setSubject("你好，这是测试！");
  sendmail.setContent("你好这是一个带多<br>附件的测试！");
  System.out.println(sendmail.sendMail());
 }
}