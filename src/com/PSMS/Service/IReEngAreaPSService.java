package com.PSMS.Service;

import java.util.List;

import com.PSMS.pojo.JointEngAreaPS;
import com.PSMS.pojo.ReEngAreaPowerStation;

public interface IReEngAreaPSService {

	
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
	 * @param areaId 区域id
	 * @return
	 * @throws Exception
	 */
	public List<JointEngAreaPS> searchByAreaId(String areaId) throws Exception;
	
	/**
	 * 根据用户名搜索
	 * @param userId 用户id
	 * @return
	 * @throws Exception
	 */
	public List<JointEngAreaPS> searchByUserId(int userId) throws Exception;
	
	
	/**
	 * 根据电站id搜索
	 * @param psId
	 * @return
	 * @throws Exception
	 */
	public List<JointEngAreaPS> searchByPSId(int psId) throws Exception;
	
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
