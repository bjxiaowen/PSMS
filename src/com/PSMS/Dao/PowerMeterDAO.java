package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Hibernate.PowerMeter;

public interface PowerMeterDAO {

	List<PowerMeter> searchHistoryDataByDateAndParameterID(
			Integer parameter_id, String fromRangeDate, String toRangeDate);

	String getMaxDateByPsId(int id);

	String getMinDateByPsId(int id);

}
