package com.PSMS.Service;

import java.util.List;

import com.PSMS.Dao.Inverter_parameterDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.pojo.HistoryData;

/**
 * 设备信息管理需要的操作函数*
 * 
 * @author Andy
 * @date 2016-05-01
 */
public class Inverter_parameterServiceImpl implements Inverter_parameterService {
	

	Inverter_parameterDAO dao = DAOFactory.getInverter_parameterDAOInstance();

	/**
	 * 获取所有逆变器的信息*
	 * 
	 * @author Andy
	 * @date 2016-05-01
	 */
	public List<Inverter_parameter> getAllInverter() {
		return dao.getAllInverter();
	}

	/**
	 * 校验 逆变器是否已存在于电站中*
	 * 
	 * @author Andy
	 * @date 2016-05-01
	 */
	@Override
	public boolean checkInverterNameExistById(String name, int ps_id) {
		return dao.checkInverterNameExistById(name, ps_id);
	}

	/**
	 * 新建逆变器*
	 * 
	 * @author Andy
	 * @date 2016-05-01
	 */
	@Override
	public boolean addInverter(Inverter_parameter i_parameter) {
		return dao.addInverter(i_parameter);
	}

	/**
	 * 根据汇流箱id 删除选中逆变器信息*
	 * 
	 * @author Andy
	 * @date 2016-05-01
	 */
	@Override
	public boolean deleteInverterById(int id) {
		return dao.deleteInverterById(id);
	}

	/**
	 * 根据逆变器名查询设备*
	 * 
	 * @author Andy
	 * @date 2016-05-01
	 */
	@Override
	public List<Inverter_parameter> getInverterByIts_name(String inverter_name) {
		return dao.getInverterByIts_name(inverter_name);
	}

	/**
	 * 根据电站id查询逆变器信息*
	 * 
	 * @author Andy
	 * @date 2016-05-01
	 */
	@Override
	public List<Inverter_parameter> getInverterByPs_name(int ps_id) {
		return dao.getInverterByPs_name(ps_id);
	}

	/**
	 * 根据电站id查询逆变器id*
	 * 
	 * @author Andy
	 * @date 2016-05-01
	 */
	@Override
	public List<Integer> getParameterIDByPs_id(int ps_id) {
		return dao.getParameterIDByPs_id(ps_id);
	}

	/**
	 * 根据设备id获得设备名称*
	 * 
	 * @author Andy
	 * @date 2016-05-01
	 */
	@Override
	public String getParameterNameByParameterID(Integer id) {
		return dao.getParameterNameByParameterID(id);
	}

	/**
	 * 根据电站名称和设备名称查询逆变器信息*
	 * 
	 * @author Andy
	 * @date 2016-05-01
	 */
	@Override
	public List<Inverter_parameter> getInverterByPsandname(int ps_id, String inverter_name) {
		return dao.getInverterByPsandname(ps_id, inverter_name);
	}

	/**
	 * 根据电站id获得逆变器信息*
	 * 
	 * @author Andy
	 * @date 2016-05-01
	 */
	@Override
	public List<Inverter_parameter> getInverterByPsId(int ps_id) {
		return dao.getInverterByPsId(ps_id);
	}

	/**
	 * 根据电站id分期*
	 * 
	 * @author Andy
	 * @date 2016-05-01
	 */
	@Override
	public List<Integer> getPeriodIndexByPsId(int ps_id) {
		return dao.getPeriodIndexByPsId(ps_id);
	}

	/**
	 * 根据电站id和电站期数查询所有逆变器名称*
	 * 
	 * @author Andy
	 * @date 2016-05-01
	 */
	@Override
	public List<String> getInverterNamesByPsId(int ps_id, int period_num) {
		return dao.getInverterNamesByPsId(ps_id, period_num);
	}

	/**
	 * 根据电站id和逆变器名称查询对应的parameter_id*
	 * 
	 * @author Andy
	 * @date 2016-05-01
	 */
	@Override
	public Integer getParameterIdByPsId(int ps_id, int period_num, String inverter_name) {
		return dao.getParameterIdByPsId(ps_id, period_num, inverter_name);
	}

	/**
	 * 通过电站和设备类型查询设备
	 */
	@Override
	public List<Inverter_parameter> getParameter(int ps_id, String type) {
		return dao.getParameter(ps_id, type);
	}

	@Override
	public List<HistoryData> getHistoryData() {
		return dao.getHistoryData();
	}

	@Override
	public List<HistoryData> getPSData() {
		return dao.getPSData();
	}

	@Override
	public List<HistoryData> getByPsId(Integer psId,String startTime,String endTime) {
		return dao.getByPsId(psId,startTime,endTime);
	}
}
