package com.test;

import java.util.List;

import com.PSMS.Dao.IReEngineerAreaDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.ReEngineerArea;

public class ReEngineerAreaTest {

	public static void main(String[] args) throws Exception {
		IReEngineerAreaDao dao=DAOFactory.getReEngineerAreaDaoInstance();
		//addReEngineerArea(dao);
		//update(dao);
		//getById(dao);
		//delete(dao);
		//searchByAreaNameTest(dao);
		//searchByUserNameTest(dao);
		check(dao);
	}
	
	public static void check(IReEngineerAreaDao dao) throws Exception{
		List<ReEngineerArea> reList=dao.checkByAreaIdAndUserId("14571890869610003", "63");
		for(ReEngineerArea re:reList){
			System.out.println(re.toString());
		}
	}
	
	
	//按区域名称查询
	public static void searchByAreaNameTest(IReEngineerAreaDao dao) throws Exception{
		List<ReEngineerArea> reList=dao.searchByAreaName("海");
		for(ReEngineerArea re:reList){
			System.out.println(re.toString());
		}
	}
	
		//按用户名称查询
		public static void searchByUserNameTest(IReEngineerAreaDao dao) throws Exception{
			List<ReEngineerArea> reList=dao.searchByUserName("王");
			for(ReEngineerArea re:reList){
				System.out.println(re.toString());
			}
		}
	
	//获取
	public static void getById(IReEngineerAreaDao dao) throws Exception{
		ReEngineerArea re=dao.getById("Re1457189045682001");
		System.out.println(re.toString());
	}
	
	//删除
	public static void delete(IReEngineerAreaDao dao) throws Exception{
		dao.deleteReEngineerArea("Re1457189045682001");
	}
	
	//修改
	public static void update(IReEngineerAreaDao dao) throws Exception{
		ReEngineerArea reEngineerArea=new ReEngineerArea();
		reEngineerArea.setId("Re1457189045682001");
		reEngineerArea.setUserId(3);
		reEngineerArea.setAreaId("14571890456820001");
		dao.UpdateReEngineerArea(reEngineerArea);
	}
	
	//添加
	public static void addReEngineerArea(IReEngineerAreaDao dao) throws Exception{
		ReEngineerArea reEngineerArea=new ReEngineerArea();
		reEngineerArea.setAreaId("14571890456820001");
		reEngineerArea.setUserId(3);
		dao.addReEngineerArea(reEngineerArea);
	}
	
	//查询所有数据
	public static void getAll(IReEngineerAreaDao dao) throws Exception{
		List<ReEngineerArea> reList=dao.getAll();
		for(ReEngineerArea re:reList){
			System.out.println(re.toString());
		}
	}

}
