/** * * 
* 文件名称: Connection_bookDAOImpl.java *
* 
* 客户通讯录管理，增删改查客户信息*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-22 下午6:55:40 *
* @author liu.yang 
*/ 
package com.PSMS.Dao;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.Connection_book;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.M_user;


public class Connection_bookDAOImpl implements Connection_bookDAO {

	@Override
	public List<Connection_book> getAllInformation() {
		// TODO Auto-generated method stub
		//获取所有客户通讯录的信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();		
		String hql="from Connection_book";
		Query query=session.createQuery(hql);
		List<Connection_book> p=query.list();		
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public boolean addConnectionBook(Connection_book connection_book) {
		// TODO Auto-generated method stub
		//插入客户数据
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(connection_book);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deleteConnectionBookById(int id) {
		// TODO Auto-generated method stub
		//删除客户数据
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "delete from Connection_book where  id = ?";
		query = session.createQuery(hql);
		query.setInteger(0, id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	
	}

	@Override
	public List<Connection_book> getConnectionBookByCompany_name(
			String company_name) {
		// TODO Auto-generated method stub
		//根据公司名称查询客户
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from Connection_book where Company_name = ? ";
		Query query=session.createQuery(hql);
		query.setString(0, company_name);
		List<Connection_book> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
		
	}

}
