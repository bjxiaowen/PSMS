package com.PSMS.Service;

import java.util.List;

import com.PSMS.pojo.ReEngineerArea;

public interface IReEngineerAreaService {
	/**
	 * 区域与工程师关系
	 * @param reEngineerArea 
	 * @return boolean
	 * @throws Exception
	 * 
	 */
	boolean addReEngineerArea(ReEngineerArea reEngineerArea) throws Exception;
	
	/**
	 * 修改区域与工程师关系
	 * @param reEngineerArea
	 * @return boolean
	 * @throws Exception
	 */
	public boolean UpdateReEngineerArea(ReEngineerArea reEngineerArea) throws Exception;
	
	/**
	 * 删除区域与工程师关系
	 * @param id 主键
	 * @return boolean
	 * @throws Exception
	 */
	public boolean deleteReEngineerArea(String id) throws Exception;
	
	/**
	 * 通过主键查询
	 * @param id 主键
	 * @return ReEngineerArea
	 * @throws Exception
	 */
	public ReEngineerArea getById(String id) throws Exception;

	/**
	 * 查询所有的区域与工程师关系
	 * @return List<ReEngineerArea>
	 * @throws Exception
	 */
	public List<ReEngineerArea> getAll() throws Exception;
	
	/**
	 * 通过区域名称查询
	 * @param areaName 区域名称
	 * @return List<ReEngineerArea>
	 * @throws Exception
	 */
	public List<ReEngineerArea> searchByAreaName(String areaName) throws Exception;
	
	/**
	 * 通过工程师名称查询
	 * @param areaName 区域名称
	 * @return List<ReEngineerArea>
	 * @throws Exception
	 */
	public List<ReEngineerArea> searchByUserName(String UserName) throws Exception;
	
	/**
	 * 通过areaId,userId查询工程师与区域
	 * @param areaId 
	 * @param userId 
	 * @return List<ReEngineerArea>
	 * @throws Exception
	 */
	public List<ReEngineerArea> checkByAreaIdAndUserId(String areaId,String userId) throws Exception;

}
