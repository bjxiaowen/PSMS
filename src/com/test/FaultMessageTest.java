package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.PSMS.Dao.IFaultMessageDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.FaultMessage;
import com.PSMS.pojo.JointFaultMessage;
import com.PSMS.util.IDGenerate;
import com.PSMS.util.MailUtils;

public class FaultMessageTest {

	private static SimpleDateFormat formatter =new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		IFaultMessageDao dao=DAOFactory.getFaultMessageInstance();
		try {
			//addFaultMessage(dao);
			//updateFaultMessage(dao);
			//deleteFaultMessage(dao);
			//getFaultMessageById(dao);
			//getAllJointFaultMessage(dao);
			getNeedSendMailFaultMessage(dao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	
	public static void getNeedSendMailFaultMessage(IFaultMessageDao dao)throws Exception{
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
	
	public static void getAllJointFaultMessage(IFaultMessageDao dao)throws Exception{
		List<JointFaultMessage> list=dao.getAllJointFaultMessage();
		for(JointFaultMessage message:list){
			System.out.println(message.toString());
		}
	}
	public static void getFaultMessageById(IFaultMessageDao dao)throws Exception{
		System.out.println(dao.getFaultMessageById("Fa14577667462680000"));
	}
	
	public static void deleteFaultMessage(IFaultMessageDao dao)throws Exception{
		FaultMessage fau=new FaultMessage();
		fau.setAreaId("14571890456820001");
		fau.setPsId(92);
		fau.setEquipmentId(24);
		fau.setFaultMessageId("Fa14575226268760000");
		fau.setUserId(65);
		System.out.println(dao.deleteFaultMessage(fau));
	}
	

	public static void updateFaultMessage(IFaultMessageDao dao)throws Exception{
		FaultMessage fau=new FaultMessage();
		fau.setFaultMessageId("Fa14575226268760000");
		fau.setAreaId("14571890456820001");
		fau.setPsId(92);
		fau.setEquipmentId(30);
		fau.setUserId(65);
		fau.setAlertTime(formatter.format(new Date()));
		fau.setStatus(2);
		fau.setInitialDiagnose("初步诊断是什么原因！！！！");
		fau.setPredictTime(formatter.format(new Date()));
		fau.setAlertCause("故障原因故障原因故障原因故障原因");
		fau.setHandleCondition("处理状况处理状况处理状况");
		fau.setMaintainDate(formatter.format(new Date()));
		fau.setCheckPerson("向小文");
		fau.setCheckDate(formatter.format(new Date()));
		fau.setCheckText("检查批语检查批语检查批语");
		fau.setCheckStatus(1);
		System.out.println(dao.updateFaultMessage(fau));
	}
	
	public static void addFaultMessage(IFaultMessageDao dao) throws Exception{
		FaultMessage fau=new FaultMessage();
		fau.setFaultMessageId("Fa"+IDGenerate.getId());
		fau.setAreaId("14571890456820001");
		fau.setPsId(92);
		fau.setEquipmentId(30);
		fau.setUserId(65);
		fau.setAlertTime(formatter.format(new Date()));
		fau.setStatus(2);
		fau.setInitialDiagnose("初步诊断是什么原因！！！！");
		fau.setPredictTime(formatter.format(new Date()));
		fau.setAlertCause("故障原因故障原因故障原因故障原因");
		fau.setHandleCondition("处理状况处理状况处理状况");
		fau.setMaintainDate(formatter.format(new Date()));
		fau.setCheckPerson("向小文");
		fau.setCheckDate(formatter.format(new Date()));
		fau.setCheckText("检查批语检查批语检查批语");
		fau.setCheckStatus(1);
		System.out.println(dao.addFaultMessage(fau));
	}
}
