package com.PSMS.Service;

import java.util.List;

import com.PSMS.pojo.PowerStationBase;
/**
 * 组件数据
 * @author Andy
 *
 */
public interface IBiModuleService {

	/**
	 * 组件获取一天的总数据 
	 * @param dateTime  当前时间
	 * @param psId  电站id
	 * @return
	 * @throws Exception
	 */
	public PowerStationBase getPowerStationDayByDate(String dateTime,int psId,String type)throws Exception;
	

	/**
	 * 组件通过日期查询获取组件信息
	 * @param dateTime 
	 * @return
	 * @throws Exception
	 */
	public List<PowerStationBase> getPowerStationHourByDate(String dateTime,int psId)throws Exception;
	

}
