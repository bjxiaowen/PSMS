package com.PSMS.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.PSMS.Dao.IFaultMessageDao;
import com.PSMS.Dao.IFromInverterInfo;
import com.PSMS.Dao.IReEngAreaPS;
import com.PSMS.Dao.IToDataDao;
import com.PSMS.Dao.Inverter_parameterDAO;
import com.PSMS.Factory.BaseDaoFactory;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.pojo.FaultMessage;
import com.PSMS.pojo.FromData;
import com.PSMS.pojo.JointEngAreaPS;
import com.PSMS.pojo.JointFaultMessage;
import com.PSMS.pojo.ToData;
import com.PSMS.util.DataTransformTools;
import com.PSMS.util.HttpSender;
import com.PSMS.util.IDGenerate;
import com.PSMS.util.MailUtils;

/**
 * 定时任务每15分钟执行一次
 * @author Andy
 */
//@Component
public class QuarterHourJob {
	//@Scheduled(cron = "0 0/15 0 * * ?")
	public void transformData(){
		//查询出目标数据中最大值去查来源数据
		IToDataDao todao=DAOFactory.getToDataDaoInstance();//目的数据
		IFromInverterInfo  origindao=BaseDaoFactory.getFromInverterInfoDaoInstance();//来源数据
		try {
			int max=todao.getMax();
			List<FromData> list=origindao.getById(max);
			if(list!=null&&list.size()>0){
				for(FromData fromData:list){
					ToData toData=DataTransformTools.transform(fromData);
					System.out.println(toData.toString());
					try {
						if(todao.addToData(toData)){//保存转化数据
							sendFaultMessage(toData);//如果机器不正常才会发送错误信息
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendFaultMessage(ToData toData){
		try {
			if(toData.getMachineState()==1){//机器失效toData
				String inverterID=toData.getInverterID();//设备id
				Inverter_parameterDAO InDao=DAOFactory.getInverter_parameterDAOInstance();
				//通过设备id查询电站id
				List<Inverter_parameter> list=InDao.getInverterByItName(inverterID);
				if(null!=list&&list.size()>0){
					Inverter_parameter par=list.get(0);
					int psId=par.getPS_id();
					if(psId!=0){
						queryReEngAreaPS(psId);//通过电站id查询信息
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过电站id查询出相关信息
	 * @param psId
	 */
	public void queryReEngAreaPS(int psId){
		try {
			IReEngAreaPS reEAP=DAOFactory.getReEngAreaPSInstance();
			List<JointEngAreaPS> list=reEAP.searchByPSId(psId);
			if(list!=null&&list.size()>0){
				addFaultMessage(list.get(0));//添加错误信息
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加错误信息
	 * @param joint
	 */
	public void addFaultMessage(JointEngAreaPS joint){
		try {
			if(joint==null){
				return;
			}
			SimpleDateFormat formatter =new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			IFaultMessageDao dao=DAOFactory.getFaultMessageInstance();
			FaultMessage fau=new FaultMessage();
			fau.setFaultMessageId("Fa"+IDGenerate.getId());
			fau.setAreaId(joint.getAreaId());
			fau.setPsId(joint.getPsId());
			fau.setUserId(joint.getUserId());
			fau.setAlertTime(formatter.format(new Date()));
			dao.addFaultMessage(fau);
			sendMailFaultMessage(dao);//发送信息
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送信息
	 * @param dao
	 * @throws Exception
	 */
	public void sendMailFaultMessage(IFaultMessageDao dao)throws Exception{
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
