/** * * 
* 文件名称: EquipmentService.java *
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

import com.PSMS.Hibernate.Equipment;

public interface EquipmentService {
	/** 
	*设备信息管理需要的操作函数*
	* @author liu.yang 
	* @date 2014-11-6
	*/ 

	List<String> getAllBrandName(String type);//获取所有设备的信息

	List<String> getAllModelName(String type,String brand);//根据设备品牌和类型显示设备型号

	List<String> getAllTypeName();//获取所有设备的类型信息

	List<Equipment> getAllEquipment();//获取所有设备的信息

	boolean deleteEquipmentById(int id);//删除设备信息

	boolean addEquipment(Equipment equipment);//新建设备

	List<Equipment> getEquipmentByIts_name(String brand);//根据设备品牌查询设备信息

	List<Equipment> getEquipmentByM_name(String model);//根据设备型号查询设备信息

	List<Equipment> getEquipmentByType(String type);//根据设备类型查询设备信息

	boolean checkEquipmentExist(String brand, String model, String type);//判断设备是否已存在

	List<String> getAllModelNameJ(String type, String brand);//根据设备品牌和类型显示设备型号

	List<Equipment> getEquipmentByTBM(String type, String brand, String model);//	根据设备类型,型号和品牌查询设备信息	

	List<Equipment> getEquipmentByTB(String type, String brand);//根据设备类型和品牌查询设备信息

	List<Equipment> getEquipmentByTM(String type, String model);//根据设备类型型号查询设备信息

	List<Equipment> getEquipmentByBM(String brand, String model);//根据设备型号和品牌查询设备信息

	

	
}
