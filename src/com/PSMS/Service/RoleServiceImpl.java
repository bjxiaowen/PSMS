package com.PSMS.Service;

import java.util.List;

import com.PSMS.Dao.RoleDAO;
import com.PSMS.Factory.DAOFactory;

public class RoleServiceImpl implements RoleService {
	RoleDAO dao = DAOFactory.getRoleDaoInstance();

	@Override
	public List<String> getAllRoleName() {//-----------------LM
		// TODO Auto-generated method stub
		return dao.getAllRoleName();
	}

	@Override
	public String getRoleNameById(int role_id) {//-------------------LM
		// TODO Auto-generated method stub
		return dao.getRoleNameById(role_id);
	}

	@Override
	public int getRoleIdByName(String role_name) {//---------------LM
		// TODO Auto-generated method stub
		return dao.getRoleIdByName(role_name);
	}
}
