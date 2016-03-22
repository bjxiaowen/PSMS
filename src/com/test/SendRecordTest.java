package com.test;

import java.util.Date;
import java.util.List;

import com.PSMS.Dao.ISendRecordDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.SendRecord;
import com.PSMS.util.IDGenerate;

public class SendRecordTest {

	public static void main(String[] args) {

		ISendRecordDao dao=DAOFactory.getSendRecordDaoInstance();
		//add(dao);
		getAll(dao);
	}
	
	public static void getAll(ISendRecordDao dao){
		List<SendRecord> list = null;
		try {
			list = dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(SendRecord send:list){
			System.out.println(send.toString());
		}
	}
	
	
	public static void add(ISendRecordDao dao){
		SendRecord send=new SendRecord();
		send.setId("Sd"+IDGenerate.getId());
		send.setEmail("bjxiaowen@126.com");
		send.setEmailContent("你好我在中国");
		send.setEmailDate(new Date());
		send.setEmailStatus("true");
		send.setTel("13520029132");
		send.setNoteContent("发送短信内容");
		send.setNoteDate(new Date());
		send.setNoteStatus("true");
		try {
			dao.add(send);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
