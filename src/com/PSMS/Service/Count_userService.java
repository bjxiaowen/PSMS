package com.PSMS.Service;

import com.PSMS.Hibernate.Count_user;

public interface Count_userService {

	int getCountByPsId(int ps_id);

	boolean updateCountByPsId(int usercount, int ps_id);

	boolean checkPsIsExitInCount(int ps_id);

	boolean addCount(Count_user count_user);

}
