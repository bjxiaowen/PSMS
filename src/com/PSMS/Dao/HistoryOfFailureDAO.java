package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Hibernate.HistoryOfFailure;

public interface HistoryOfFailureDAO {

	List<HistoryOfFailure> searchFailureHistoryData(String ps_name,
			String device_type, String fromRangeDate, String toRangeDate);

	boolean getAllFailureInformation();

	boolean deleteAllData();
    boolean getAllFailureInformationJB();
}
