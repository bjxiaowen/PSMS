/** * * 
* 文件名称: EquipmentServiceImpl.java *
* 
* 设备信息管理，增删改查设备信息*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-6 下午6:55:40 *
* @author liu.yang 
*/ 
package com.PSMS.Service;

import java.util.List;

import com.PSMS.Dao.EquipmentDAO;
import com.PSMS.Dao.PS_informationDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.Equipment;

public class EquipmentServiceImpl implements EquipmentService{
	EquipmentDAO dao = DAOFactory.getEquipmentDAOInstance();
	@Override
	public List<String> getAllBrandName(String type) {
		// TODO Auto-generated method stub
		/** 
		*获取所有设备的信息*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.getAllBrandName(type);
	}
	@Override
	public List<String> getAllModelName(String type,String brand) {
		// TODO Auto-generated method stub
		/** 
		* 根据设备品牌和类型显示设备型号*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.getAllModelName(type,brand);
	}
	@Override
	public List<String> getAllTypeName() {
		// TODO Auto-generated method stub
		/** 
		* 获取所有设备的类型信息*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.getAllTypeName();
	}
	@Override
	public List<Equipment> getAllEquipment() {
		// TODO Auto-generated method stub
		/** 
		*获取所有设备的信息*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.getAllEquipment();
	}
	@Override
	public boolean deleteEquipmentById(int id) {
		// TODO Auto-generated method stub
		/** 
		* 删除设备信息*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.deleteEquipmentById(id);
	}
	@Override
	public boolean addEquipment(Equipment equipment) {
		// TODO Auto-generated method stub
		/** 
		* 新建设备*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.addEquipment(equipment);
	}
	@Override
	public List<Equipment> getEquipmentByIts_name(String brand) {
		// TODO Auto-generated method stub
		/** 
		* 根据设备品牌查询设备信息*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.getEquipmentByIts_name(brand);
	}
	@Override
	public List<Equipment> getEquipmentByM_name(String model) {
		// TODO Auto-generated method stub
		/** 
		*根据设备型号查询设备信息*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.getEquipmentByM_name(model);
	}
	@Override
	public List<Equipment> getEquipmentByType(String type) {
		// TODO Auto-generated method stub
		/** 
		*根据设备类型查询设备信息*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.getEquipmentByType(type);
	}
	@Override
	public boolean checkEquipmentExist(String brand, String model, String type) {
		// TODO Auto-generated method stub
		/** 
		* 判断设备是否已存在*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.checkEquipmentExist(brand,model,type);
	}
	@Override
	public List<String> getAllModelNameJ(String type, String brand) {
		// TODO Auto-generated method stub
		/** 
		* 根据设备品牌和类型显示设备型号*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.getAllModelNameJ(type, brand);
	}
	@Override
	public List<Equipment> getEquipmentByTBM(String type, String brand,
			String model) {
		// TODO Auto-generated method stub
		/** 
		* 根据设备类型,型号和品牌查询设备信息*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.getEquipmentByTBM(type, brand,
				model);
	}
	@Override
	public List<Equipment> getEquipmentByTB(String type, String brand) {
		// TODO Auto-generated method stub
		/** 
		*根据设备类型和品牌查询设备信息*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao. getEquipmentByTB(type, brand);
	}
	@Override
	public List<Equipment> getEquipmentByTM(String type, String model) {
		// TODO Auto-generated method stub
		/** 
		* 根据设备类型型号查询设备信息*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.getEquipmentByTM(type, model);
	}
	@Override
	public List<Equipment> getEquipmentByBM(String brand, String model) {
		// TODO Auto-generated method stub
		/** 
		* 根据设备型号和品牌查询设备信息*
		* @author liu.yang 
		* @date 2014-11-6
		*/ 
		return dao.getEquipmentByBM(brand, model);
	}	

}
