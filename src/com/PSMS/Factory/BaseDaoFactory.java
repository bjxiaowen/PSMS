package com.PSMS.Factory;

import com.PSMS.Dao.IFromInverterInfo;
import com.PSMS.Dao.impl.FromInverterInfoImpl;

public class BaseDaoFactory {

	public static IFromInverterInfo getFromInverterInfoDaoInstance(){
		return new FromInverterInfoImpl();
	}
}
