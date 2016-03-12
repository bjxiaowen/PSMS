package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Hibernate.JunctionBox;

public interface JunctionBoxDAO {

	List<JunctionBox> searchHistoryDataByDateAndParameterID(Integer parameter_id,
			String fromRangeDate, String toRangeDate);

}
