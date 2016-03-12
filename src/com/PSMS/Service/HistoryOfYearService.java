/** * * 
* 文件名称: HistoryOfYearService.java *
* 
* 年发电量数据管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-10 下午10:11:32 *
* * @author  min.li & jie.yang
*/
package com.PSMS.Service;
import java.util.List;

import com.PSMS.Hibernate.HistoryOfYear;
public interface HistoryOfYearService {
	/** 
	*年发电量数据管理需要的操作函数*
	* @author min.li & jie.yang
	* @date 2014-12-10 
	*/ 
	List<HistoryOfYear> getDataByYear(int year);//根据年份获取发电量示例数据年份表中的数据

	List<Integer> getAllHistoryYear();//获取年份历史数据表中所有年份

	double getSumPowerOfYear();//统计一年所有电站的发电量

	double getPowerByPsIDAndYear(int ps_id,int year);//根据电站ID与年份获得年发电量 

	List<HistoryOfYear> getDataByYearAndPs_id(int year, int ps_id);

	boolean insertYearData(HistoryOfYear yearData);

	boolean isEmptyByPsId(int id);

	void clearDataByPsID(int id);

	void createDataByYearAndPsId(int j, int id);
}
