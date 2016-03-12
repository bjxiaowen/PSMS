package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Hibernate.HistoryOfDay;

public interface HistoryOfDayDAO {

	List<Integer> getAllHistoryYear();

	List<Integer> getMonthByPsAndYear(int ps_id, int year);

	List<HistoryOfDay> getDataByMonth(int ps_id, int year, int month);
	
    double getTodayAccPowerByPsID(int ps_id);//----------------LM20141124

	String getTodayOnLineHourByPsID(int ps_id);//----------------LM20141124

	double getSumPowerOfDay();//----------------LM20141124

	double getSumIradOfDay();//----------------LM20141124

	List<Double> getPowerDataByYearAndPSid(int year, int ps_id);//YJ20141130

	List<Double> getIrradiationDataByYearAndPSid(int year, int ps_id); //YJ20141201	

	double getTodayAccIradByPsID(int ps_id); //-----------YJ 20141208

	boolean insertDayData(HistoryOfDay dayData);

	double createDayAccPowerByPSIdAndDate(String date, int id);

	String getMaxDateByPsId(int id);

	void clearData(int id);

	String getMaxMonthTimeByPSId(int id);

	String getMinMonthTimeByPSId(int id);

	List<Integer> getAllHistoryMonth();
}
