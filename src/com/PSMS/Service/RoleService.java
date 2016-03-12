package com.PSMS.Service;

import java.util.List;

public interface RoleService {

	List<String> getAllRoleName();//获取所有角色-----LM

	String getRoleNameById(int role_id);//通过角色id获取角色名称---LM

	int getRoleIdByName(String role_name);//通过角色名称获得id-------------------LM

}
