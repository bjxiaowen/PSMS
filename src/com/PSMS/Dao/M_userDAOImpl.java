/** * * 
* 文件名称: M_userDAOImpl.java *
* 
* 用户登陆，用户管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-18 下午6:27:40 *
* * @author jiaojiao.wang & min.li
*/ 
package com.PSMS.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.M_user;
import com.PSMS.Hibernate.PS_information;

public class M_userDAOImpl implements M_userDAO {

	@Override
	public List<M_user> getAllUser() {
		// TODO Auto-generated method stub 
		// 获取全部user
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from M_user";
		Query query=session.createQuery(hql);
		List<M_user> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public boolean addUser(M_user m_user) {
		// TODO Auto-generated method stub
		// 插入用户数据
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.saveOrUpdate(m_user);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean checkUserNameExist(String user_name) {
		// TODO Auto-generated method stub 
		// 查询没有电站从属的用户是否已存在
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="from M_user p where p.User_name= ? ";
		Query query = session.createQuery(hql);
		query.setString(0, user_name);
		M_user p = (M_user) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		if (p!=null)
			return true;
		return false;
	}

	@Override
	public boolean checkUserNameExistById(String user_name, int ps_id) {
		// TODO Auto-generated method stub 
		// 查询有电站从属的用户是否已存在
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="from M_user p where p.User_name= ?";
		Query query = session.createQuery(hql);
		query.setString(0, user_name);
		
		M_user p = (M_user) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		if (p!=null)
			return true;
		return false;
	}

	@Override
	public boolean deleteUserById(int id) {
		// TODO Auto-generated method stub 
		// 删除用户
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "delete from M_user where  id = ?";
		query = session.createQuery(hql);
		query.setInteger(0, id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean updateUserByNameAndPS_id(String user_name, String password,
			String name, String telephone, String email, int role_id, int ps_id,String ex_user_name,int ex_ps_id) {
		// TODO Auto-generated method stub 
		// 更新用户信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="update M_user c set c.User_name=? , c.password=? , c.name=? ,c.telephone=?," +
				"c.email=? ,c.Role_id=?, c.PS_id=? where c.User_name=? and c.PS_id=?";
		Query query = session.createQuery(hql);
		query.setString(0, user_name);
		query.setString(1, password);
		query.setString(2, name);
		query.setString(3, telephone);
		query.setString(4, email);
		query.setInteger(5, role_id);
		query.setInteger(6, ps_id);
		query.setString(7, ex_user_name);
		query.setInteger(8, ex_ps_id);
		int ret=query.executeUpdate(); 
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}
	
	public Integer getPsIdByName(String station_name){
		// TODO Auto-generated method stub 
		// 通过电站名称得到电站的id
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "select id from PS_information where name=?";
		query = session.createQuery(hql);
		query.setString(0, station_name);
		Integer n =(Integer) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return n;
	}

	public List<String> getAllStationName() {
		// TODO Auto-generated method stub
		// 得到所有电站的名称
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "select name from PS_information ";
		query = session.createQuery(hql); 
		
		List<String> u =query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return u;
	}



	public boolean checkUserIsExist(String User_name, String password) {
		// TODO Auto-generated method stub
		// 检查该普通用户是否存在
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from M_user where User_name = ? and password = ? ";
		Query query = session.createQuery(hql);
		query.setString(0, User_name);
		query.setString(1, password);
		M_user a = (M_user) query.uniqueResult();
		
		
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
	
		if(a!=null)
			return true;
		else 
			return false;
	}
	
	public boolean checkUserIsExist2(String User_name, String password) {
		// TODO Auto-generated method stub 
		// 检查该高级用户是否存在
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from M_user where User_name = ? and password = ? and PS_id = 0";
		Query query = session.createQuery(hql);
		query.setString(0, User_name);
		query.setString(1, password);
		M_user a = (M_user) query.uniqueResult();				
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();	
		if(a!=null)
			return true;
		else 
			return false;
	}
	@Override
	public Integer getRoleIdByName(String User_name) {
		// TODO Auto-generated method stub
		// 得到普通用户的角色
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" select Role_id from M_user where User_name = ? ";
		Query query = session.createQuery(hql);
		query.setString(0, User_name);
		Integer k =(Integer) query.uniqueResult();
		
		
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}

	public Integer getRoleIdByName2(String User_name) {
		// TODO Auto-generated method stub
		// 得到高级用户的角色
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" select Role_id from M_user where User_name = ? and PS_id = 0";
		Query query = session.createQuery(hql);
		query.setString(0, User_name);
		Integer k =(Integer) query.uniqueResult();
		
		
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return k;
	}
	
	public List<M_user> allUserInfo() {
		// TODO Auto-generated method stub
		// 所有用户信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql="from M_user";
		query=session.createQuery(hql);
		List<M_user> s =  query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return s;
	}

	@Override
	public List<M_user> getUserByUser_name(String user_name) {
		// TODO Auto-generated method stub
		// 根据用户名查询用户信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from M_user where User_name = ? ";
		Query query=session.createQuery(hql);
		query.setString(0, user_name);
		List<M_user> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public List<M_user> getUserByPS_name(int user_ps_id) {
		// TODO Auto-generated method stub 
		// 根据电站名查询用户信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from M_user where PS_id = ? ";
		Query query=session.createQuery(hql);
		query.setInteger(0, user_ps_id);
		List<M_user> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}
	
	public boolean checkUserName(String user_name) {
		// TODO Auto-generated method stub 
		// 检查用户名是否已存在
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from M_user where User_name = ?  ";
		Query query = session.createQuery(hql);
		query.setString(0, user_name);
		M_user a = (M_user) query.uniqueResult();
		
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
	
		if(a!=null)
			return true;
		else 
			return false;
	}

	@Override
	public Integer getPsIdByUsername(String user_name) {
		// TODO Auto-generated method stub
		// 根据用户名查询电站id
				Session session=HibernateSessionFactory.getHibernateSession();
				HibernateSessionFactory.begainHibernateTransaction();
				
				String hql=" from M_user where User_name = ?  ";
				Query query = session.createQuery(hql);
				query.setString(0, user_name);
				M_user a = (M_user) query.uniqueResult();
				int ps_id = a.getPS_id();
				
				HibernateSessionFactory.commitHibernateTransaction();
				HibernateSessionFactory.closeHibernateSession();
				return ps_id;
	}

	@Override
	public List<M_user> getPartUserByPsId(int ps_id) {
		// TODO Auto-generated method stub 
		// 根据电站id查询该电站的所有用户信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql="from M_user where PS_id = ?";
		Query query=session.createQuery(hql);
		query.setInteger(0,ps_id);
		List<M_user> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public int getPsIdByUserId(int id) {
		// TODO Auto-generated method stub 根据用户id查询其所属电站id
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		
		String hql=" select PS_id from M_user where id = ?  ";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		Integer a = (Integer) query.uniqueResult();
		
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return a;
	}

	@Override
	public List<M_user> getAllUserbyname(String user_user_name) {
		// TODO Auto-generated method stub 
		// 根据用户名查询用户信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from M_user where User_name = ? ";
		Query query=session.createQuery(hql);
		query.setString(0, user_user_name);
		List<M_user> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
		
	}

	@Override
	public List<M_user> getUserByPSandname(int user_ps_id, String user_user_name) {// 根据电站id和用户名查询用户信息
		// TODO Auto-generated method stub
		
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from M_user where PS_id = ? and user_name =?";
		Query query=session.createQuery(hql);
		query.setInteger(0, user_ps_id);
		query.setString(1, user_user_name);
		List<M_user> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public boolean deleteUserByPSId(int id) {
		// TODO Auto-generated method stub
		// 根据电站id删除该电站所有用户信息
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "delete from M_user where  PS_id = ?";
		query = session.createQuery(hql);
		query.setInteger(0, id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public List<M_user> getUserByRoleId(int roleId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql=" from M_user where Role_id =? ";
		Query query=session.createQuery(hql);
		query.setInteger(0, roleId);
		List<M_user> p=query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}	

}
