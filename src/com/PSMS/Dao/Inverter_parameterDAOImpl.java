/** * * 
* 文件名称: Inverter_parameterDAOImpl.java *
* 
* 逆变器信息管理，增删改查逆变器信息*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-18 下午2:27:40 *
* * @author jiaojiao.wang 
*/
package com.PSMS.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.Inverter_parameter;

/**
 * 逆变器信息管理需要的操作函数*
 * 
 * @author jiaojiao.wang
 * @date 2014-11-18
 */
public class Inverter_parameterDAOImpl implements Inverter_parameterDAO {

	@Override
	public List<Inverter_parameter> getAllInverter() {
		// TODO Auto-generated method stub
		// 获取所有逆变器的信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT A.id,PS_id,A.name,type,brand,model,"
				+ "Purchase_time,Rate_power,Rated_voltage,Max_power,Power_factor,Period_num,BatteryCapacity "
				+ "from Inverter_parameter A,PS_information B where A.PS_id = B.id and B.Delete_flag = 0";
		Query query = session.createSQLQuery(hql).addEntity(Inverter_parameter.class);
		List<Inverter_parameter> p = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;

	}

	@Override
	public boolean checkInverterNameExistById(String name, int ps_id) {// 校验
																		// 逆变器是否已存在于电站中
		Session session = HibernateSessionFactory.getHibernateSession();
		String hql = "from  Inverter_parameter p where p.name= ? and p.PS_id = ?";
		Query query = session.createQuery(hql);
		query.setString(0, name);
		query.setInteger(1, ps_id);
		List<Inverter_parameter> p = (List<Inverter_parameter>) query.list();
		HibernateSessionFactory.closeHibernateSession();
		if (p != null && p.size() != 0)
			return true;
		return false;
	}

	@Override
	public boolean addInverter(Inverter_parameter i_parameter) {
		// TODO Auto-generated method stub
		// 新建逆变器
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(i_parameter);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deleteInverterById(int id) {
		// TODO Auto-generated method stub
		// 根据汇流箱id 删除选中逆变器信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "delete from Inverter_parameter where id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public List<Inverter_parameter> getInverterByIts_name(String inverter_name) {
		// TODO Auto-generated method stub
		// 根据逆变器名查询设备
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = " from Inverter_parameter where name = ? ";
		Query query = session.createQuery(hql);
		query.setString(0, inverter_name);
		List<Inverter_parameter> k = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Inverter_parameter> getInverterByPs_name(int ps_id) {
		// TODO Auto-generated method stub
		// 根据电站id查询逆变器信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = " from Inverter_parameter where PS_id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		List<Inverter_parameter> k = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Integer> getParameterIDByPs_id(int ps_id) {
		// TODO Auto-generated method stub
		// 根据电站id查询逆变器id
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = " select id from Inverter_parameter where PS_id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		List<Integer> k = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public String getParameterNameByParameterID(Integer id) {
		// TODO Auto-generated method stub
		// 根据设备id获得设备名称
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = " select name from Inverter_parameter where id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		String k = (String) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Inverter_parameter> getInverterByPsandname(int ps_id, String inverter_name) {
		// TODO Auto-generated method stub
		// 根据电站名称和设备名称查询逆变器信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = " from Inverter_parameter where PS_id = ? and name=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		query.setString(1, inverter_name);
		List<Inverter_parameter> k = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Inverter_parameter> getInverterByPsId(int ps_id) {
		// TODO Auto-generated method stub
		// 根据电站id获得逆变器信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = " from Inverter_parameter where PS_id = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		List<Inverter_parameter> k = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Integer> getPeriodIndexByPsId(int ps_id) {
		// TODO Auto-generated method stub
		// 根据电站id分期
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "select count(*) from PS_period where PS_id = ?  group by Period_num";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		List<Integer> k = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override // 根据电站id和电站期数查询所有逆变器名称
	public List<String> getInverterNamesByPsId(int ps_id, int period_num) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = " select name from Inverter_parameter where PS_id = ? and Period_num = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		query.setInteger(1, period_num);
		List<String> k = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public Integer getParameterIdByPsId(int ps_id, int period_num, String inverter_name) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = " select id from Inverter_parameter where PS_id = ? and Period_num = ? and name = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		query.setInteger(1, period_num);
		query.setString(2, inverter_name);
		Integer k = (Integer) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Inverter_parameter> getInverterByItName(String name) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = " from Inverter_parameter where name = ? ";
		Query query = session.createQuery(hql);
		query.setString(0, name);
		List<Inverter_parameter> k = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	@Override
	public List<Inverter_parameter> getParameter(int ps_id, String type) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = " from Inverter_parameter where PS_id = ? and type=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		query.setString(1, type);
		List<Inverter_parameter> parame = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return parame;
	}

}
