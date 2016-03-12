package com.PSMS.Dao;

import java.util.List;

public interface RoleDAO {

	List<String> getAllRoleName();

	String getRoleNameById(int role_id);

	int getRoleIdByName(String role_name);

}
