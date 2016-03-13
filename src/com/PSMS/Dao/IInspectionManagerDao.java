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
	
	
	/**
	 * 
	 * @param areaId 区域id
	 * @param psId 电站id
	 * @param userId 用户id
	 * @param equipmentId 设备id
	 * @return
	 * @throws Exception
	 */
	public JointInspection checkById(String areaId,int psId,int userId,int equipmentId)throws Exception;
	
	
	/**
	 * 通过电站id查询
	 * @param psId 电站id
	 * @return
	 * @throws Exception
	 */
	public List<JointInspection> getPsId(int psId)throws Exception;

}
