package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Hibernate.HistoryOfYear;

public interface HistoryOfYearDAO {

	List<HistoryOfYear> getDataByYear(int year);//根据年份获取发电量示例数据年份表中的数据

	List<Integer> getAllHistoryYear();////获取年份历史数据表中所有年份  -------------20141117

	double getSumPowerOfYear();//统计一年所有电站的发电量---------------20141124

	double getPowerByPsIDAndYear(int ps_id,int year);//根据电站ID与年份获得年发电量 ---YJ---20141208 

	List<HistoryOfYear> getDataByYearAndPs_id(int year, int ps_id);

	boolean insertYearData(HistoryOfYear yearData);

	boolean isEmptyByPsId(int id);

	void clearDataByPsID(int id);

	void createDataByYearAndPsId(int year, int id);
}
