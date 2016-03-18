package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.PSMS.Dao.IInspectionDao;
import com.PSMS.Dao.IInspectionManagerDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.Inspection;
import com.PSMS.pojo.InspectionManager;
import com.PSMS.pojo.JointInspection;
import com.PSMS.util.DataUtils;
import com.PSMS.util.GetTime;
import com.PSMS.util.IDGenerate;

public class InspectionManagerTest {

	private static SimpleDateFormat formatter =new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		IInspectionManagerDao dao = DAOFactory.getInspectionManagerDaoInstance();
		IInspectionDao idao=DAOFactory.getInspectionDaolInstance();
		try {
			// addInspectionManagerTest(dao);
			// updateInspectionManagerTest(dao);
			// deleteInspectionManagerTest(dao);
			//getById(dao);
			//getAll(dao);
			//addInspection(dao, idao);
			everyDaySendMail(idao);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void getAll(IInspectionManagerDao dao) throws Exception{
		List<JointInspection> list=dao.getAll();
		for(JointInspection joint:list){
			System.out.println(joint.toString());
		}
	}

	public static void getById(IInspectionManagerDao dao) throws Exception {
		System.out.println(dao.getById("IM14577629769830000"));
	}

	public static void deleteInspectionManagerTest(IInspectionManagerDao dao) throws Exception {
		InspectionManager ins = new InspectionManager();
		ins.setId("IM14577624569350000");
		dao.deleteInspectionManager(ins);
	}

	public static void updateInspectionManagerTest(IInspectionManagerDao dao) throws Exception {
		InspectionManager ins = new InspectionManager();
		ins.setId("IM14577629769830000");
		ins.setPsId(82);
		ins.setAreaId("14571890869610003");
		ins.setUserId(65);
		ins.setEquipmentId(10);
		ins.setCurrDate(formatter.format(new Date()));
		ins.setInspectionPeriod(30);
		ins.setNextDate(formatter.format(new Date()));
		dao.updateInspectionManager(ins);
	}

	public static void addInspectionManagerTest(IInspectionManagerDao dao) throws Exception {
		InspectionManager ins = new InspectionManager();
		ins.setId("IM" + IDGenerate.getId());
		ins.setPsId(82);
		ins.setAreaId("14571890869610003");
		ins.setUserId(65);
		ins.setEquipmentId(10);
		ins.setCurrDate(formatter.format(new Date()));
		ins.setInspectionPeriod(30);
		dao.addInspectionManager(ins);
	}

	/**
	 * 定期添加添加巡检记录，定时任务一天执行一次
	 * @param dao
	 * @param idao
	 * @throws Exception 
	 */
	public static void addInspection(IInspectionManagerDao dao,IInspectionDao idao) throws Exception{
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
	public static void everyDaySendMail(IInspectionDao dao) throws Exception{
		SimpleDateFormat format =new  SimpleDateFormat("yyyy-MM-dd");
		List<JointInspection> noList=dao.getNoInspection();
		for(JointInspection inspect:noList){
			Date currIn=GetTime.addDate(new Date(),1);//当前日期+1=应该巡检的日期就发邮件或者短信
			String tomorrow=format.format(currIn);
			String shouldDate=inspect.getShouldDate();
			System.out.println(inspect.toString());
			if(tomorrow.equals(shouldDate)){
				System.out.println(inspect.getEmail());
				System.out.println(inspect.getTel());
				System.out.println(inspect.getUserName()+"你好！\n\n       "+inspect.getAreaName()+"的"+inspect.getPsName()+inspect.getEquipmentName()+"需要你"+shouldDate+"去检查");
			}
		}
	} 
	
}
