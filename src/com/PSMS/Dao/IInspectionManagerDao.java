package com.PSMS.Dao;

import java.util.List;

import com.PSMS.pojo.InspectionManager;
import com.PSMS.pojo.JointInspection;
public interface IInspectionManagerDao {
	
	/**
	 * 添加巡检管理管理信息
	 * @param inspection
	 * @return
	 * @throws Exception
	 */
	boolean addInspectionManager(InspectionManager inspectionManager) throws Exception;
	
	/**
	 * 更新巡检管理信息
	 * @param inspection
	 * @return
	 * @throws Exception
	 */
	public boolean updateInspectionManager(InspectionManager inspectionManager) throws Exception;

	/**
	 * 删除巡检管理信息
	 * @param inspection
	 * @return
	 * @throws Exception
	 */
	public boolean deleteInspectionManager(InspectionManager inspectionManager) throws Exception;
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public JointInspection getById(String id) throws Exception;
	
	/**
	 * 获取所有
	 * @return
	 * @throws Exception
	 */
	public List<JointInspection> getAll() throws Exception;

}
