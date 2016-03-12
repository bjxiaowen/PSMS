package com.PSMS.Dao;

import java.util.List;

import com.PSMS.pojo.JointEngAreaPS;
import com.PSMS.pojo.ReEngAreaPowerStation;

public interface IReEngAreaPS {
	
	/**
	 * 添加关系信息
	 * @param reEngAreaPS
	 * @return
	 * @throws Exception
	 */
	boolean addReEngAreaPS(ReEngAreaPowerStation reEngAreaPS) throws Exception;

	/**
	 * 更新关系信息
	 * @param reEngAreaPS
	 * @return
	 * @throws Exception
	 */
	public boolean UpdateReEngAreaPS(ReEngAreaPowerStation reEngAreaPS) throws Exception;
	

	/**
	 * 删除关系信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteReEngAreaPS(String id) throws Exception;
	

	/**
	 * 通过id关系信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public JointEngAreaPS getById(String id) throws Exception;
	
	/**
	 * 获取所有数据
	 * @return
	 * @throws Exception
	 */
	public List<JointEngAreaPS> getAll() throws Exception;
	
	/**
	 * 根据区域名搜索
	 * @param areaName 区域名称
	 * @return
	 * @throws Exception
	 */
	public List<JointEngAreaPS> searchByAreaName(String areaName) throws Exception;
	
	/**
	 * 根据用户名搜索
	 * @param UserName 用户名
	 * @return
	 * @throws Exception
	 */
	public List<JointEngAreaPS> searchByUserName(String UserName) throws Exception;
	
	
	/**
	 * 根据电站名搜索
	 * @param psName
	 * @return
	 * @throws Exception
	 */
	public List<JointEngAreaPS> searchByPSName(String psName) throws Exception;
	
	
	/**
	 * 校验
	 * @param areaId 区域id
	 * @param userId 用户id
	 * @param psId  电站id
	 * @return
	 * @throws Exception
	 */
	public List<JointEngAreaPS> checkById(String areaId,int userId,int psId) throws Exception;
}
