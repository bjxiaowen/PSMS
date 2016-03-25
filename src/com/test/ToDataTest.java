package com.test;

import com.PSMS.Dao.IToDataDao;
import com.PSMS.Factory.DAOFactory;

public class ToDataTest {

	public static void main(String[] args) {
		IToDataDao dao=DAOFactory.getToDataDaoInstance();
		try {
			System.out.println(dao.getMax());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
