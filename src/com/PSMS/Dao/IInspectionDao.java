package com.PSMS.Dao;

import java.util.List;

import com.PSMS.pojo.Inspection;
import com.PSMS.pojo.JointInspection;

public interface IInspectionDao {
	/**
	 * 添加巡检信息
	 * @param inspection
	 * @return
	 * @throws Exception
	 */
	boolean addInspection(Inspection inspection) throws Exception;
	
	/**
	 * 更新巡检信息
	 * @param inspection
	 * @return
	 * @throws Exception
	 */
	public boolean UpdateInspection(Inspection inspection) throws Exception;

	/**
	 * 删除巡检信息
	 * @param inspection
	 * @return
	 * @throws Exception
	 */
	public boolean deleteInspection(Inspection inspection) throws Exception;
	
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
