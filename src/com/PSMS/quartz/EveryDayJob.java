package com.PSMS.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.PSMS.Dao.IInspectionDao;
import com.PSMS.Dao.IInspectionManagerDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.Inspection;
import com.PSMS.pojo.JointInspection;
import com.PSMS.util.DataUtils;
import com.PSMS.util.GetTime;
import com.PSMS.util.IDGenerate;
import com.PSMS.util.MailUtils;

public class EveryDayJob {
	public void work()  {  
		System.out.println("定时任务开始！！！");  
		IInspectionManagerDao manageDao = DAOFactory.getInspectionManagerDaoInstance();
		IInspectionDao inspectDao=DAOFactory.getInspectionDaolInstance();
		try {
			addInspection(manageDao, inspectDao);//生成巡检排版记录
			everyDaySendMail(inspectDao);//发送巡检邮件
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("定时任务结束！！！");  
    }
	
	/**
	 * 定期添加添加巡检记录，定时任务一天执行一次
	 * @param dao
	 * @param idao
	 * @throws Exception 
	 */
	public  void addInspection(IInspectionManagerDao dao,IInspectionDao idao) throws Exception{
		SimpleDateFormat format =new  SimpleDateFormat("yyyy-MM-dd");
		//1.查询所有的巡检管理
		List<JointInspection> mList=dao.getAll();
		for(JointInspection mIns:mList){
			//2.判断下次巡检日期是否登录当前日期如果相等,生成管理数据和巡检记录
			String currDate=GetTime.getCurrentTime3();//当前日期
			String nextDate=mIns.getNextDate();
			if(currDate.equals(nextDate)){//如果日期相等则更新管理添加巡检
				mIns.setCurrDate(nextDate);
				//当前巡检日期=现在日期+1
				Date currIn=GetTime.addDate(new Date(),1);
				//下次巡检日期=现在日期+1+周期
				Date  nextIn=GetTime.addDate(new Date(), mIns.getInspectionPeriod()+1);//下次巡检的日期
				String shouldDate=format.format(currIn);
				dao.updateInspectionManager(DataUtils.toInspectionManager(mIns,shouldDate, format.format(nextIn)));
				Inspection inspect=new Inspection();
				inspect.setId("In"+IDGenerate.getId());
				inspect.setManagerId(mIns.getManageId());
				inspect.setShouldDate(shouldDate);
				inspect.setInspectionStatus(0);
				idao.addInspection(inspect);
			}
		}
	}
	
	/**
	 * 每天执行一次
	 * @param dao
	 * @throws Exception
	 */
	public  void everyDaySendMail(IInspectionDao dao) throws Exception{
		SimpleDateFormat format =new  SimpleDateFormat("yyyy-MM-dd");
		List<JointInspection> noList=dao.getNoInspection();
		for(JointInspection inspect:noList){
			Date currIn=GetTime.addDate(new Date(),1);//当前日期+1=应该巡检的日期就发邮件或者短信
			String tomorrow=format.format(currIn);
			String shouldDate=inspect.getShouldDate();
			if(tomorrow.equals(shouldDate)){
				System.out.println();
				String mial=inspect.getEmail();
				if(mial!=null&&!mial.equals("")){
					System.out.println(inspect.getTel());
					MailUtils sendmail = new MailUtils();
					sendmail.setTo(mial);
					sendmail.setSendhtml(false);
					sendmail.setSubject("定期巡检");
					sendmail.setContent(inspect.getUserName()+"你好！\n\n       "+inspect.getAreaName()+"的"+inspect.getPsName()+inspect.getEquipmentName()+"需要你"+shouldDate+"去检查");
					sendmail.sendMail();
				}
				
			}
		}
	} 
	
}
