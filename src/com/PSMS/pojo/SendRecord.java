package com.PSMS.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 发送信息记录
 * 
 * @author Andy
 *
 */
public class SendRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1235974017743477351L;

	private String id;
	private String email;
	private String emailContent;
	private Date emailDate;
	private String emailStatus;
	private String tel;
	private String noteContent;
	private Date noteDate;
	private String noteStatus;
	
	
	
	
	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getEmailContent() {
		return emailContent;
	}




	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}




	public Date getEmailDate() {
		return emailDate;
	}




	public void setEmailDate(Date emailDate) {
		this.emailDate = emailDate;
	}




	public String getEmailStatus() {
		return emailStatus;
	}




	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}




	public String getTel() {
		return tel;
	}




	public void setTel(String tel) {
		this.tel = tel;
	}




	public String getNoteContent() {
		return noteContent;
	}




	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}




	public Date getNoteDate() {
		return noteDate;
	}




	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
	}




	public String getNoteStatus() {
		return noteStatus;
	}




	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}




	@Override
	public String toString() {
		return "SendRecord [id=" + id + ", email=" + email + ", emailContent=" + emailContent + ", emailDate="
				+ emailDate + ", emailStatus=" + emailStatus + ", tel=" + tel + ", noteContent=" + noteContent
				+ ", noteDate=" + noteDate + ", noteStatus=" + noteStatus + "]";
	}
	
	
	

}
