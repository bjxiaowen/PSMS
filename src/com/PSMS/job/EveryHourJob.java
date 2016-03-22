package com.PSMS.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.PSMS.Dao.IFaultMessageDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.JointFaultMessage;
import com.PSMS.util.HttpSender;
import com.PSMS.util.MailUtils;

@Component
public class EveryHourJob {//0 0 0/1 * * ?
	
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void send() {
		try {
			System.out.println("###################################每小时执行的定时任务开始！###############################################");
			IFaultMessageDao dao=DAOFactory.getFaultMessageInstance();
			getNeedSendMailFaultMessage(dao);
			System.out.println("###################################每小时执行的定时任务结束！################################################");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getNeedSendMailFaultMessage(IFaultMessageDao dao)throws Exception{
		List<JointFaultMessage> list=dao.getNeedSendMailFaultMessage();
		SimpleDateFormat format =new  SimpleDateFormat("yyyy-MM-dd");
		MailUtils sendmail = new MailUtils();
		if(null==list||list.size()==0)return ;
		for(JointFaultMessage message:list){
			String mial=message.getEmail();
			if(mial!=null&&!mial.equals("")){
				System.out.println(message.getTel());
				String tel=message.getTel();
				String date=format.format(new Date());
				String content=message.getAreaName()+"的"+message.getPsName()+message.getFailureMeaning()+"需要你"+date+"去检查";
				sendmail.setTo(mial);
				sendmail.setSendhtml(false);
				sendmail.setSubject("异常信息处理");
				sendmail.setContent(message.getUserName()+"你好！\n\n       "+content);
				boolean sendStatus=sendmail.sendMail();
				if(sendStatus){
					dao.updateById("true", date,message.getFaultMessageId());
				}
				//发送短信添加记录
				String telResult=HttpSender.send("http://www.zjysms.com/send/gsend.asp?", "zxnygnjs", "zxnygnjs87", tel, content, null, null, "ccdx");
				HttpSender.addSendRecord(mial, content, sendStatus+"", tel, content, telResult);
			}
		}
	}
}
