package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Adapter.inverter_parameter;
import com.PSMS.Hibernate.Inverter;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.pojo.HistoryData;


public interface InverterDAO {

	List<Inverter> searchHistoryDataByDateAndParameterID(Integer parameter_id,
			String fromRangeDate, String toRangeDate);

	String searchTopPowerByParameter_id(int parameter_id);//--------------------20141120根据parameter_id查询最新的逆变器数据

	String searchTopStateByParameter_id(int parameter_id);

	List<Inverter> getTopRealTimeData(Integer parameter_id);

	Double getZhengDianPowerByParaIdAndHour(Integer parameter_id, int h);
	
	/*public List<HistoryData> getHistoryData();
	
	public List<HistoryData> getPSData();
	
	public List<HistoryData> getByPsId(Integer psId);
	*/

}
