/** * * 
* 文件名称: Connection_bookServiceImpl.java *
* 
* 客户通讯录管理，增删改查客户信息*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-22 下午6:55:40 *
* @author liu.yang 
*/ 
package com.PSMS.Service;

import java.util.List;

import com.PSMS.Dao.Connection_bookDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.Connection_book;

public class Connection_bookServiceImpl implements Connection_bookService {
	/** 
	*客户通讯录管理需要的操作函数*
	* @author liu.yang 
	* @date 2014-10-22 
	*/ 
	Connection_bookDAO dao = DAOFactory.getConnection_bookDaoInstance();
	@Override
	public List<Connection_book> getAllInformation() {		
		// TODO Auto-generated method stub
		/** 
		* 获取所有客户通讯录的信息*
		* @author liu.yang 
	    * @date 2014-10-22  
		*/
		return dao.getAllInformation();
	}
	@Override
	public boolean addConnectionBook(Connection_book connection_book) {
		// TODO Auto-generated method stub
		/** 
		* 插入客户数据*
		* @author liu.yang 
	    * @date 2014-10-22  
		*/
		return dao.addConnectionBook(connection_book);
	}
	
	@Override
	public boolean deleteConnectionBookById(int id) {
		// TODO Auto-generated method stub
		/** 
		* 删除客户数据*
		* @author liu.yang 
	    * @date 2014-10-22  
		*/
		return dao.deleteConnectionBookById(id);
	}
	@Override
	public boolean checkConnectionBookNameExist(String company_name) {
		// TODO Auto-generated method stub
		/** 
		* 检查客户是否已存在*
		* @author liu.yang 
	    * @date 2014-10-22  
		*/
		return false;
	}

	@Override
	public List<Connection_book> getConnectionBookByCompany_name(
			String company_name) {
		// TODO Auto-generated method stub
		/** 
		* 根据公司名称查询客户*
		* @author liu.yang 
	    * @date 2014-10-22  
		*/
		return dao.getConnectionBookByCompany_name(
				 company_name);
	}

}
