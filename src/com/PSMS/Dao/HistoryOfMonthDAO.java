package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Hibernate.HistoryOfMonth;

public interface HistoryOfMonthDAO {

	List<HistoryOfMonth> getDataByPs_idAndYear(int ps_id, int year);

	List<Integer> getAllHistoryYear();//------WJJ

	boolean insertMonthData(HistoryOfMonth monthData);

	void clearDataByPsID(int id);

	void createDataByMonthAndPsId(String str, int id);

	boolean isEmptyByPsId(int id);

}
