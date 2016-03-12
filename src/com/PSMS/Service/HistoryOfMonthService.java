/** * * 
* 文件名称: HistoryOfMonthService.java *
* 
* 月发电量数据管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-12-10 下午11:09:32 *
* * @author  min.li & jiaojiao.wang
*/
package com.PSMS.Service;
import java.util.List;

import com.PSMS.Hibernate.HistoryOfMonth;

public interface HistoryOfMonthService {
	/** 
	*月发电量数据管理需要的操作函数*
	* @author  min.li & jiaojiao.wang
	* @date 2014-12-10 
	*/ 
	List<HistoryOfMonth> getDataByPs_idAndYear(int ps_id, int year);
	List<Integer> getAllHistoryYear();
	boolean insertMonthData(HistoryOfMonth monthData);
	boolean isEmptyByPsId(int id);
	void clearDataByPsID(int id);
	void createDataByMonthAndPsId(String str, int id);

	

}
