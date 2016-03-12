package com.PSMS.Service;

import com.PSMS.Dao.Count_userDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.Count_user;

public class Count_userServiceImpl implements Count_userService{
	Count_userDAO dao = DAOFactory.getCount_userDAOInstance();
	@Override
	public int getCountByPsId(int ps_id) {
		// TODO Auto-generated method stub
		return dao.getCountByPsId(ps_id);
	}
	
	@Override
	public boolean updateCountByPsId(int usercount, int ps_id) {
		return dao.updateCountByPsId(usercount,ps_id);
	}
	@Override
	public boolean checkPsIsExitInCount(int ps_id) {
		// TODO Auto-generated method stub
		return dao.checkPsIsExitInCount(ps_id);
	}
	@Override
	public boolean addCount(Count_user count_user) {
		// TODO Auto-generated method stub
		return dao.addCount(count_user);
	}

}
