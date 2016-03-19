package com.test;


import java.util.List;

import com.PSMS.Dao.IReEngAreaPS;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.JointEngAreaPS;
import com.PSMS.pojo.ReEngAreaPowerStation;
import com.PSMS.util.IDGenerate;

public class ReEngAreaPSTest {

	public static void main(String[] args) throws Exception {
		IReEngAreaPS dao=DAOFactory.getReEngAreaPSInstance();
		//addReEngAreaPS(dao);
		//UpdateReEngAreaPS(dao);
		//deleteReEngAreaPS(dao);
		//getById(dao);
		//getAll(dao);
		//searchByUserName(dao);
		//searchByAreaName(dao);
		//searchByPSName(dao);
		//checkById(dao);
	}
	
	public static void checkById(IReEngAreaPS dao) throws Exception{

		List<JointEngAreaPS> list=dao.checkById("14571890456820001",63,80);
		for(JointEngAreaPS join:list){
			System.out.println(join.toString());
		}
	}
	
	
	
	public static void searchByPSName(IReEngAreaPS dao) throws Exception{
/*
		List<JointEngAreaPS> list=dao.searchByPSName("天津");
		for(JointEngAreaPS join:list){
			System.out.println(join.toString());
		}*/
	}
	
	public static void searchByUserName(IReEngAreaPS dao) throws Exception{
		/*List<JointEngAreaPS> list=dao.searchByUserName("tianjin");
		for(JointEngAreaPS join:list){
			System.out.println(join.toString());
		}*/
	}
	
	public static void searchByAreaName(IReEngAreaPS dao) throws Exception{
		/*List<JointEngAreaPS> list=dao.searchByAreaName("北京");
		for(JointEngAreaPS join:list){
			System.out.println(join.toString());
		}*/
	}
	
	public static void getAll(IReEngAreaPS dao) throws Exception{
		List<JointEngAreaPS> list=dao.getAll();
		for(JointEngAreaPS join:list){
			System.out.println(join.toString());
		}
	}
	
	public static void getById(IReEngAreaPS dao) throws Exception{
		System.out.println(dao.getById("Re14574502485890000"));
	}
	
	public static void deleteReEngAreaPS(IReEngAreaPS dao) throws Exception{
		System.out.println(dao.deleteReEngAreaPS("Re14574496964160000"));
	}
	
	public static void UpdateReEngAreaPS(IReEngAreaPS dao) throws Exception{
		ReEngAreaPowerStation reEng=new ReEngAreaPowerStation();
		reEng.setPsId(80);
		reEng.setUserId(65);
		reEng.setAreaId("14571890456820001");
		reEng.setId("Re"+IDGenerate.getId());
		System.out.println(dao.UpdateReEngAreaPS(reEng));
	}
	

	/**
	 * 添加
	 * @param dao
	 * @throws Exception
	 */
	public static void addReEngAreaPS(IReEngAreaPS dao) throws Exception{
		ReEngAreaPowerStation reEng=new ReEngAreaPowerStation();
		reEng.setPsId(80);
		reEng.setUserId(63);
		reEng.setAreaId("14571890456820001");
		reEng.setId("Re"+IDGenerate.getId());
		System.out.println(dao.addReEngAreaPS(reEng));
	}
}
