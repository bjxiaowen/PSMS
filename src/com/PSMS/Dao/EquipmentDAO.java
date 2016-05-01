/** * * 
* 文件名称: EquipmentDAO.java *
* 
* 设备信息管理，增删改查设备信息*
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-6 下午6:55:40 *
* @author liu.yang 
*/ 
package com.PSMS.Dao;

import java.util.List;

import com.PSMS.Hibernate.Equipment;

public interface EquipmentDAO {
	/** 
	*设备信息管理需要的操作函数*
	* @author liu.yang 
	* @date 2014-11-6
	*/ 
	
	public List<String> getAllBrandName(String type);//获取所有设备的信息

	public List<String> getAllModelName(String type,String brand);//根据设备品牌和类型显示设备型号

	public List<String> getAllTypeName();//获取所有设备的类型信息

	public List<Equipment> getAllEquipment();//获取所有设备的信息

	 boolean deleteEquipmentById(int id);//删除设备信息

	 boolean addEquipment(Equipment equipment);//新建设备

	public List<Equipment> getEquipmentByIts_name(String brand);//根据设备品牌查询设备信息

	public List<Equipment> getEquipmentByM_name(String model);//根据设备型号查询设备信息

	public List<Equipment> getEquipmentByType(String type);//根据设备类型查询设备信息

	 boolean checkEquipmentExist(String brand, String model, String type);//判断设备是否已存在

	public List<String> getAllModelNameJ(String type, String brand);//根据设备品牌和类型显示设备型号

	public List<Equipment> getEquipmentByTBM(String type, String brand,
			String model);//	根据设备类型,型号和品牌查询设备信息

	public List<Equipment> getEquipmentByTB(String type, String brand);//根据设备类型和品牌查询设备信息

	public List<Equipment> getEquipmentByTM(String type, String model);//根据设备类型型号查询设备信息

	public List<Equipment> getEquipmentByBM(String brand, String model);//根据设备型号和品牌查询设备信息

	public List<String> getAllBrand();//获取所有的品牌
	
	public List<String> getTypeByBrand(String brand);//通过品牌查询设备类型

	public List<String> getModelByBrandAndType(String brand,String type);//通过品牌和类型查询设备型号
	
	
	
}
