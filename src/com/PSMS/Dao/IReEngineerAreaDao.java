package com.PSMS.Dao;

import java.util.List;

import com.PSMS.pojo.ReEngineerArea;
/**
 * 工程师区域关系
 * @author Andy
 *
 */
public interface IReEngineerAreaDao {
	
	boolean addReEngineerArea(ReEngineerArea reEngineerArea) throws Exception;

	public boolean UpdateReEngineerArea(ReEngineerArea reEngineerArea) throws Exception;

	public boolean deleteReEngineerArea(String id) throws Exception;

	public ReEngineerArea getById(String id) throws Exception;

	public List<ReEngineerArea> getAll() throws Exception;
	
	public List<ReEngineerArea> searchByAreaName(String areaName) throws Exception;
	
	public List<ReEngineerArea> searchByUserName(String UserName) throws Exception;
	
	public List<ReEngineerArea> checkByAreaIdAndUserId(String areaId,String userId) throws Exception;
}
