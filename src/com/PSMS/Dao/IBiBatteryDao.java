package com.PSMS.Dao;

import com.PSMS.pojo.PowerStationBase;

/**
 * 蓄电池
 * @author Andy
 *
 */
public interface IBiBatteryDao {

	/**
	 * 查询蓄电池最新数据
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public PowerStationBase getBatteryNewestDate(String dateTime,int psId)throws Exception;
	
}
