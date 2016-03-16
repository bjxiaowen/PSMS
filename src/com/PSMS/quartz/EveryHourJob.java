package com.PSMS.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.PSMS.Dao.IFaultMessageDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.JointFaultMessage;
import com.PSMS.util.MailUtils;

public class EveryHourJob {

	public void send() {
		try {
			IFaultMessageDao dao=DAOFactory.getFaultMessageInstance();
			getNeedSendMailFaultMessage(dao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getNeedSendMailFaultMessage(IFaultMessageDao dao)throws Exception{
		List<JointFaultMessage> list=dao.getNeedSendMailFaultMessage();
		SimpleDateFormat format =new  SimpleDateFormat("yyyy-MM-dd");
		MailUtils sendmail = new MailUtils();
		for(JointFaultMessage message:list){
			String mial=message.getEmail();
			if(mial!=null&&!mial.equals("")){
				System.out.println(message.getTel());
				String date=format.format(new Date());
				sendmail.setTo(mial);
				sendmail.setSendhtml(false);
				sendmail.setSubject("异常信息处理");
				sendmail.setContent(message.getUserName()+"你好！\n\n       "+message.getAreaName()+"的"+message.getPsName()+message.getEquipmentName()+message.getFailureMeaning()+"需要你"+date+"去检查");
				if(sendmail.sendMail()){
					dao.updateById("true", date,message.getFaultMessageId());
				}
			}
		}
	}
}
