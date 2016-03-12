package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Hibernate.WeatherStation;

public interface WeatherStationDAO {

	List<WeatherStation> searchHistoryDataByDateAndParameterID(
			Integer parameter_id, String fromRangeDate, String toRangeDate);

	String searchTopFZLByParameter_id(int ws_id);//--------------LM

	String searchTopTempByParameter_id(int ws_id);

	WeatherStation getRealTimeWSDataByParameterID(int p_id);

	Double getZhengDianAccByParaIdAndHour(int ws_parameter_id, int j);

	

}
