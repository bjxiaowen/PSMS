/** * * 
* 文件名称: M_userServiceImpl.java *
* 
* 用户登陆，用户管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-18 下午6:27:40 *
* * @author jiaojiao.wang & min.li
*/ 
package com.PSMS.Service;


import java.util.List;

import com.PSMS.Dao.M_userDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.M_user;

public class M_userServiceImpl implements M_userService {
	/** 
	*用户登陆、用户管理需要的操作函数*
	* @author jiaojiao.wang & min。li
	* @date 2014-12-18 
	* @param dao
	*/ 
	M_userDAO dao = DAOFactory.getUserDaoInstance();

	@Override
	public List<M_user> getAllUser() {
		// TODO Auto-generated method stub
		/** 
		* 获取全部user*
		* @author min。li
		* @date 2014-12-18 
		*/
		return dao.getAllUser();
	}

	@Override
	public boolean addUser(M_user m_user) {
		// TODO Auto-generated method stub
		/** 
		* 插入用户数据*
		* @author min。li
		* @date 2014-12-18 
		*/
		return dao.addUser(m_user);
	}

	@Override
	public boolean checkUserNameExist(String user_name) {
		// TODO Auto-generated method stub
		/** 
		* 插入用户数据*
		* @author min。li
		* @date 2014-12-18 
		*/
		return dao.checkUserNameExist(user_name);
	}

	@Override
	public boolean checkUserNameExistById(String user_name, int ps_id) {
		// TODO Auto-generated method stub
		/** 
		* 查询有电站从属的用户是否已存在*
		* @author min。li
		* @date 2014-12-18 
		*/
		return dao.checkUserNameExistById(user_name,ps_id);
	}

	@Override
	public boolean deleteUserById(int id) {
		// TODO Auto-generated method stub
		/** 
		* 删除用户*
		* @author min。li
		* @date 2014-12-18 
		*/
		return dao.deleteUserById(id);
	}

	@Override
	public boolean updateUserByNameAndPS_id(String user_name, String password,
			String name, String telephone, String email, int role_id, int ps_id,String ex_user_name,int ex_ps_id) {
		// TODO Auto-generated method stub
		/** 
		* 更新用户信息*
		* @author jioajiao。wang
		* @date 2014-12-18 
		*/
		return dao.updateUserByNameAndPS_id(user_name,password,name,telephone,email,role_id,ps_id,ex_user_name,ex_ps_id);
	}
	@Override
	public boolean checkUserIsExist(String User_name, String password) {
		// TODO Auto-generated method stub
		/** 
		* 检查登录的用户名和密码的正确性*
		* @author jioajiao。wang
		* @date 2014-12-18 
		*/
		return dao.checkUserIsExist(User_name, password);
	}

	@Override
	public List<String> getAllStationName() {
		// TODO Auto-generated method stub
		/** 
		* 获得所有电站名称*
		* @author jioajiao。wang
		* @date 2014-12-18 
		*/
		return dao.getAllStationName();
	}

	@Override
	public Integer getPsIdByName(String station_name) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站名查询电站id*
		* @author jioajiao。wang
		* @date 2014-12-18 
		*/
		return dao.getPsIdByName(station_name);
	}

	@Override
	public Integer getRoleIdByName(String User_name) {
		// TODO Auto-generated method stub
		/** 
		* 根据用户名查询角色id*
		* @author jioajiao。wang
		* @date 2014-12-18 
		*/
		return dao.getRoleIdByName(User_name);
	}

	@Override
	public boolean checkUserIsExist2(String User_name, String password) {
		// TODO Auto-generated method stub
		/** 
		* 检查该高级用户是否存在*
		* @author jioajiao。wang
		* @date 2014-12-18 
		*/
		return dao.checkUserIsExist2(User_name, password);
	}

	@Override
	public Integer getRoleIdByName2(String User_name) {
		// TODO Auto-generated method stub
		/** 
		* 得到高级用户的角色*
		* @author jioajiao。wang
		* @date 2014-12-18 
		*/
		return dao.getRoleIdByName2(User_name);
	}

	@Override
	public List<M_user> getUserByUser_name(String user_name) {
		// TODO Auto-generated method stub
		/** 
		* 根据用户名查询用户信息*
		* @author  min。li
		* @date 2014-12-18 
		*/
		return dao.getUserByUser_name(user_name);
	}

	@Override
	public List<M_user> getUserByPS_name(int user_ps_id) {
		/** 
		* 根据电站名查询用户信息*
		* @author  min。li
		* @date 2014-12-18 
		*/
		// TODO Auto-generated method stub
		return dao.getUserByPS_name(user_ps_id);
	}

	@Override
	public boolean checkUserName(String user_name) {
		/** 
		* 检查用户名是否已存在*
		* @author jioajiao。wang
		* @date 2014-12-18 
		*/
		// TODO Auto-generated method stub
		return dao.checkUserName(user_name);
	}

	@Override
	public Integer getPsIdByUsername(String user_name) {
		// TODO Auto-generated method stub
		/** 
		* 根据用户名查询电站id*
		* @author jioajiao。wang
		* @date 2014-12-18 
		*/
		return dao.getPsIdByUsername(user_name);
	}

	@Override
	public List<M_user> getPartUserByPsId(int ps_id) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站id查询该电站的所有用户信息*
		* @author  min。li
		* @date 2014-12-18 
		*/
		return dao.getPartUserByPsId(ps_id);
	}

	@Override
	public int getPsIdByUserId(int id) {
		// TODO Auto-generated method stub
		/** 
		* 根据用户id查询其所属电站id*
		* @author  min。li
		* @date 2014-12-18 
		*/
		return dao.getPsIdByUserId(id);
	}

	@Override
	public List<M_user> getAllUserbyname(String user_user_name) {
		// TODO Auto-generated method stub
		/** 
		* 根据用户名查询用户信息*
		* @author  min。li
		* @date 2014-12-18 
		*/
		return dao.getAllUserbyname(user_user_name);
	}

	@Override
	public List<M_user> getUserByPSandname(int user_ps_id, String user_user_name) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站id和用户名查询用户信息*
		* @author  min。li
		* @date 2014-12-18 
		*/
		return dao.getUserByPSandname(user_ps_id, user_user_name);
	}

	/** 
	* 根据电站id删除该电站所有用户信息*
	* @author  min。li
	* @date 2014-12-18 
	*/
	@Override
	public boolean deleteUserByPSId(int id) {
		return dao.deleteUserByPSId(id);
	}

	/**
	 * 通过角色查询用户
	 */
	public List<M_user> getUserByRoleId(int roleId) {
		return dao.getUserByRoleId(roleId);
	}

	@Override
	public M_user getUserByUserName(String User_name) {
		return dao.getUserByUserName(User_name);
	}

}
