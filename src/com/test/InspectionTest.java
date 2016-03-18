package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.PSMS.Dao.IInspectionDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.Inspection;
import com.PSMS.pojo.JointInspection;
import com.PSMS.util.IDGenerate;

public class InspectionTest {
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) {
		IInspectionDao dao=DAOFactory.getInspectionDaolInstance();
		try {
//			addInspection(dao);
//			UpdateInspection(dao);
//			deleteInspection(dao);
			//getById(dao);
			//getAll(dao);
			getOverdue(dao);//逾期查询
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getAll(IInspectionDao dao) throws Exception{
		List<JointInspection> list=dao.getAll();
		for(JointInspection joint:list){
			System.out.println(joint);
		}
	}
	
	public static void getById(IInspectionDao dao) throws Exception{
		System.out.println(dao.getById("In14579653603910000").toString());
	}

	public static void addInspection(IInspectionDao dao) throws Exception{
		Inspection ins=new Inspection();
		ins.setId("In"+IDGenerate.getId());
		ins.setManagerId("IM14577629363890000");
		ins.setShouldDate(formatter.format(new Date()));
		ins.setActualDate(formatter.format(new Date()));
		ins.setInspectionReport("我们正在努力处理中！！！！");
		ins.setInspectionStatus(2);
		System.out.println(dao.addInspection(ins));
	}
	
	public static void UpdateInspection(IInspectionDao dao) throws Exception{
		Inspection ins=new Inspection();
		ins.setId("In14577652263700000");
		ins.setManagerId("IM14577629363890000");
		ins.setShouldDate("2016-02-12");//formatter.format(new Date())
		ins.setActualDate("2016-02-12");
		ins.setInspectionReport("我们正在努力处理中！！！！我们已经处理完了！！");
		ins.setInspectionStatus(0);
		System.out.println(dao.UpdateInspection(ins));
	}
	
	
	
	public static void deleteInspection(IInspectionDao dao) throws Exception{
		Inspection ins=new Inspection();
		ins.setId("In14577651618860000");
		System.out.println(dao.deleteInspection(ins));
	}
	
	public static void getOverdue(IInspectionDao dao)throws Exception{//逾期查询
		List<JointInspection> list=dao.getOverdue();
		for(JointInspection joint:list){
			System.out.println(joint);
		}
	}
}
