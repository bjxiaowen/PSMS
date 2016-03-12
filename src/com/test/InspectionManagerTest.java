package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.PSMS.Dao.IInspectionManagerDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.InspectionManager;
import com.PSMS.pojo.JointInspection;
import com.PSMS.util.IDGenerate;

public class InspectionManagerTest {

	private static SimpleDateFormat formatter =new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		IInspectionManagerDao dao = DAOFactory.getInspectionManagerDaoInstance();
		try {
			// addInspectionManagerTest(dao);
			// updateInspectionManagerTest(dao);
			// deleteInspectionManagerTest(dao);
			//getById(dao);
			//getAll(dao);
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

}
