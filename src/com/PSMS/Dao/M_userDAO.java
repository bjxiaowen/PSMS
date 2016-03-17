/** * * 
* 文件名称: M_userDAO.java *
* 
* 用户登陆，用户管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-18 下午6:27:40 *
* * @author jiaojiao.wang & min。li
*/ 
package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Hibernate.M_user;

public interface M_userDAO {
	/** 
	*用户登陆、用户管理需要的操作函数*
	* @author jiaojiao.wang & min。li
	* @date 2014-12-18 
	*/ 
	List<M_user> getAllUser();//获取全部user值

	boolean addUser(M_user m_user);//插入一条数据

	boolean checkUserNameExist(String user_name);//查询没有电站从属的用户是否已存在

	boolean checkUserNameExistById(String user_name, int ps_id);//查询有电站从属的用户是否已存在

	boolean deleteUserById(int id);//根据id删除用户

	boolean updateUserByNameAndPS_id(String user_name, String password,
			String name, String telephone, String email, int role_id, int ps_id,String ex_user_name,int ex_ps_id);//更新用户信息
	
	List<M_user> getUserByUser_name(String user_name);//根据用户名查询用户信息
	List<M_user> getUserByPS_name(int user_ps_id);//根据电站名查询用户信息
	
	public List<String> getAllStationName();//获得所有电站名称
	
	public Integer getPsIdByName(String station_name);//根据电站名查询电站id
	
	public Integer getRoleIdByName(String User_name);//根据用户名查询角色id
	
	public boolean checkUserIsExist2(String User_name, String password);//检查该高级用户是否存在
	
	public Integer getRoleIdByName2(String User_name);//得到高级用户的角色
	
	public boolean checkUserIsExist(String User_name, String password);
	
	boolean checkUserName(String user_name);//检查用户名是否已存在

	Integer getPsIdByUsername(String user_name);//根据用户名查询电站id

	List<M_user> getPartUserByPsId(int ps_id);//根据电站id查询该电站的所有用户信息

	int getPsIdByUserId(int id);//根据用户id查询其所属电站id

	List<M_user> getAllUserbyname(String user_user_name);//根据用户名查询用户信息(重复)

	List<M_user> getUserByPSandname(int user_ps_id, String user_user_name);//根据电站id和用户名查询用户信息

	boolean deleteUserByPSId(int id);//根据电站id删除该电站所有用户信息
	
	List<M_user> getUserByRoleId(int roleId);//通过角色查询用户
	
	M_user getUserByUserName(String User_name);//通过用户名查询
	
}
