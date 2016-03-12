/** * * 
* 文件名称: JunctionBoxServiceImpl.java *
* 
* 汇流箱设备历史数据管理*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-1-6 上午10:36:44 *
* * @author  min.li 
*/
package com.PSMS.Service;
import java.util.List;

import com.PSMS.Dao.JunctionBoxDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.JunctionBox;

public class JunctionBoxServiceImpl implements JunctionBoxService{
	/** 
	*汇流箱设备历史数据管理需要的操作函数*
	* @author min.li 
	* @date 2014-1-6 
	* @param dao
	*/
	JunctionBoxDAO dao = DAOFactory.getJunctionBoxDAOInstance();
	@Override
	public List<JunctionBox> searchHistoryDataByDateAndParameterID(
			Integer parameter_id, String fromRangeDate, String toRangeDate) {
		/** 
		* 获取汇流箱设备历史数据*
		* @author min.li
		* @date 2014-1-6 
		*/	
		// TODO Auto-generated method stub
		return dao.searchHistoryDataByDateAndParameterID(parameter_id, fromRangeDate, toRangeDate) ;
				 
	}

}
