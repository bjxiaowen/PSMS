/** * * 
* 文件名称: PS_periodDAOImpl.java *
* 
* 电站分期管理，增删改电站分期信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-12 下午9:46:33 *
* * @author jie.yang 
*/
package com.PSMS.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.PS_period;

public class PS_periodDAOImpl implements PS_periodDAO {
	/** 
	* 电站分期管理需要的操作函数*
	* @author jie.yang 
	* @date 2014-12-12 
	*/ 
	@Override
	public boolean addPS_period(PS_period ps_period) {
		// TODO Auto-generated method stub
		//新建电站分期
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.save(ps_period);
		/*Query query;
		query = session.createSQLQuery("{Call AutoInsertPSDevice(?,?,?,?)}");   
		int ps_id = ps_period.getPS_id();
		int period_num = ps_period.getPeriod_num();
		int period_unit_num = ps_period.getPeriod_unit_num();
		String period_time = ps_period.getPeriod_time();
		query.setInteger(0, ps_id);
		query.setString(1, period_time);
		query.setInteger(2, period_num);
		query.setInteger(3, period_unit_num);
		query.executeUpdate();*/
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();			
		return true;
	}

	@Override
	public List<PS_period> getPSPeriodDataByPSID(int ps_id) {
		
		// TODO Auto-generated method stub
		//根据电站获取电站分期数据
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "select from PS_period where PS_id = ps_id  order by period_nun asc";
		query = session.createQuery(hql); 
		List<PS_period> u =query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return u;
	}

	@Override
	public List<PS_period> getPsPeriodInformation() {
		// TODO Auto-generated method stub
		//获取电站分期信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "from PS_period  order by PS_id,Period_num asc";
		query = session.createQuery(hql); 
		List<PS_period> u =query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return u;
	}

	@Override
	public boolean editPeriodPartByID(int id,String period_capacity, Integer unit_num,
			String period_time) {
		// TODO Auto-generated method stub
		//编辑电站分期
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		
		String hql="update PS_period c set   c.Period_capacity=? , c.Period_unit_num=? ,c.Period_time=? where  c.id=?";
		Query query = session.createQuery(hql);
		query.setString(0, period_capacity);
		query.setInteger(1, unit_num);
		query.setString(2, period_time); 
		query.setInteger(3, id);		
		int ret=query.executeUpdate(); 
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deletePeriodByPid(int p_id) {
		// TODO Auto-generated method stub
		//删除电站分期
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "delete from PS_period where id = ?";
		query = session.createQuery(hql);
		query.setInteger(0, p_id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public int getPeriodNumByPsId(int ps_id) {
		// TODO Auto-generated method stub
		//根据电站id获取电站分期数目
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="select count(*) from  PS_period   where  PS_id=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);

		if(query.uniqueResult()==null){
			HibernateSessionFactory.commitHibernateTransaction();
			HibernateSessionFactory.closeHibernateSession();
			return 0;
		}
		else {
			int  num = Integer.parseInt(query.uniqueResult().toString());
			HibernateSessionFactory.commitHibernateTransaction();
			HibernateSessionFactory.closeHibernateSession();
			return  num;			
		}
	}

	@Override
	public Double getTotalCapacityByPsID(int ps_id) {
		// TODO Auto-generated method stub
		//根据电站id获取电站分期总容量
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();	

		String hql = "select SUM(Period_capacity) from PS_period where PS_id=?";	
		Query query = session.createQuery(hql);	
		query.setInteger(0, ps_id);	
		query.uniqueResult();
		if(query.uniqueResult()==null){
			HibernateSessionFactory.commitHibernateTransaction();
			HibernateSessionFactory.closeHibernateSession();
			return 0.0;    
			
		}
		else {
		String r=query.uniqueResult().toString();
		Double result =Double.parseDouble(r);	
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return result;}
	}

}
