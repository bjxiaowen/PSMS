/** * * 
* 文件名称: EquipmentDAOImpl.java *
* 
* 设备信息管理，增删改查设备信息*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-6 下午6:55:40 *
* @author liu.yang 
*/ 
package com.PSMS.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.Equipment;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Hibernate.JB_parameter;

public class EquipmentDAOImpl implements EquipmentDAO{

	@Override
	public List<String> getAllBrandName(String type) {
		// TODO Auto-generated method stub
		//获取所有设备的信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "select distinct brand from Equipment where type=?";		
		query = session.createQuery(hql); 
		query.setString(0,type);
		List<String> u =query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return u;
	}

	@Override
	public List<String> getAllModelName(String type,String brand) {
		// TODO Auto-generated method stub
		//根据设备品牌和类型显示设备型号
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "select distinct model from Equipment where type=? and brand=?";
		query = session.createQuery(hql); 
		query.setString(0,type);
		query.setString(1,brand);
		List<String> k =query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<String> getAllTypeName() {
		// TODO Auto-generated method stub
		//获取所有设备的类型信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "select distinct type from Equipment ";
		query = session.createQuery(hql); 
		List<String> p =query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public List<Equipment> getAllEquipment() {
		// TODO Auto-generated method stub
		//获取所有设备的信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from Equipment";
		Query query=session.createQuery(hql);
		List<Equipment> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public boolean deleteEquipmentById(int id) {
		// TODO Auto-generated method stub
		//删除设备信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="delete from Equipment where id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0,id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean addEquipment(Equipment equipment) {
		// TODO Auto-generated method stub
		//新建设备
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(equipment);
		
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public List<Equipment> getEquipmentByIts_name(String brand) {
		// TODO Auto-generated method stub
		//根据设备品牌查询设备信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from Equipment where brand = ? ";
		Query query = session.createQuery(hql);
		query.setString(0,brand);
		List<Equipment> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Equipment> getEquipmentByM_name(String model) {
		// TODO Auto-generated method stub
		//根据设备型号查询设备信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from Equipment where model = ? ";
		Query query = session.createQuery(hql);
		query.setString(0,model);
		List<Equipment> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Equipment> getEquipmentByType(String type) {
		// TODO Auto-generated method stub
		//根据设备类型查询设备信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from Equipment where type = ? ";
		Query query = session.createQuery(hql);
		query.setString(0,type);
		List<Equipment> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public boolean checkEquipmentExist(String brand, String model, String type) {
		// TODO Auto-generated method stub
		//判断设备是否已存在
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		
		String hql="from  Equipment p where p.brand= ? and p.model = ? and p.type = ?";
		Query query = session.createQuery(hql);
		query.setString(0, brand);
		query.setString(1, model);
		query.setString(2, type);
		
		Equipment p = (Equipment) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		if (p!=null)
			return true;
		return false;
	}

	@Override
	public List<String> getAllModelNameJ(String type, String brand) {
		// TODO Auto-generated method stub
		//根据设备品牌和类型显示设备型号
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "select distinct model from Equipment where type=? and brand=?";
		query = session.createQuery(hql); 
		query.setString(0,type);
		query.setString(1,brand);
		List<String> k =query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Equipment> getEquipmentByTBM(String type, String brand,
			String model) {
		// TODO Auto-generated method stub
		//		根据设备类型,型号和品牌查询设备信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from Equipment where type = ? and brand=? and model=?";
		Query query = session.createQuery(hql);
		query.setString(0,type);
		query.setString(1,brand);
		query.setString(2,model);
		List<Equipment> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Equipment> getEquipmentByTB(String type, String brand) {
		// TODO Auto-generated method stub
		//根据设备类型和品牌查询设备信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from Equipment where type = ? and brand=?";
		Query query = session.createQuery(hql);
		query.setString(0,type);
		query.setString(1,brand);
		List<Equipment> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Equipment> getEquipmentByTM(String type, String model) {
		// TODO Auto-generated method stub
		//根据设备类型型号查询设备信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from Equipment where type = ? and model=?";
		Query query = session.createQuery(hql);
		query.setString(0,type);
		
		query.setString(1,model);
		List<Equipment> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Equipment> getEquipmentByBM(String brand, String model) {
		// TODO Auto-generated method stub
		//根据设备型号和品牌查询设备信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from Equipment where  brand=? and model=?";
		Query query = session.createQuery(hql);
		query.setString(0,brand);
		query.setString(1,model);
		List<Equipment> k=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}



}
