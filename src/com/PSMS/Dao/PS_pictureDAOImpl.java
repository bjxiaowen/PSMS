/** * * 
* 文件名称: PS_pictureDAOImpl.java *
* 
* 电站图片管理 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-11 下午7:42:32 *
* * @author jie.yang 
*/
package com.PSMS.Dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.PS_picture;

public class PS_pictureDAOImpl implements PS_pictureDAO{
	/** 
	* 电站图片管理需要的操作函数*
	* @author jie.yang 
	* @date 2014-11-11 
	*/ 
	@Override
	public boolean addpsP(PS_picture psP) {
		// TODO Auto-generated method stub
		//上传电站图片
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();

		session.saveOrUpdate(psP);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public String getNameByPSid(int psID) {
		// TODO Auto-generated method stub
		//根据电站id获取图片
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();		
		String hql = "select name from PS_picture where PS_id=? ";	
		Query query = session.createQuery(hql);	
		query.setInteger(0, psID);
		query.uniqueResult();		
		if(query.uniqueResult()==null){
			HibernateSessionFactory.commitHibernateTransaction();
			HibernateSessionFactory.closeHibernateSession();
			return "no";    
			
		}
		else {
		String result =(String)query.uniqueResult();	
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return result;}
	}

	@Override
	public int getIDByPSidAndName(int psID, String name) {
		// TODO Auto-generated method stub
		//根据电站id和名称获取图片id
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();		
		String hql = "select id from PS_picture where PS_id=? and name=?";	
		Query query = session.createQuery(hql);	
		query.setInteger(0, psID);
		query.setString(1, name);
		query.uniqueResult();		
		if(query.uniqueResult()==null){
			HibernateSessionFactory.commitHibernateTransaction();
			HibernateSessionFactory.closeHibernateSession();
			return -1;    
			
		}
		else {
		int r =(Integer) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return r;}
	}

	@Override
	public boolean deletePicById(int id) {
		// TODO Auto-generated method stub
		//删除图片
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "delete from PS_picture where id = ?";
		query = session.createQuery(hql);
		query.setInteger(0, id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

}
