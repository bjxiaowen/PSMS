package com.PSMS.Dao;

import com.PSMS.Hibernate.Count_user;

public interface Count_userDAO {

	int getCountByPsId(int ps_id);//---------------WJJ20141225

	boolean addCount(Count_user count_user);

	boolean updateCountByPsId(int usercount, int ps_id);

	boolean checkPsIsExitInCount(int ps_id);

}
