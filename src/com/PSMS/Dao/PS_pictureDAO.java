/** * * 
* 文件名称: PS_pictureDAO.java *
* 
* 电站图片管理 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-11 下午7:42:32 *
* * @author jie.yang 
*/
package com.PSMS.Dao;

import com.PSMS.Hibernate.PS_picture;

public interface PS_pictureDAO {
	/** 
	* 电站图片管理需要的操作函数*
	* @author jie.yang 
	* @date 2014-11-11 
	*/ 

	boolean addpsP(PS_picture psP);//上传电站图片

	String getNameByPSid(int pSid);//根据电站id获取图片

	int getIDByPSidAndName(int psID, String name);//根据电站id和名称获取图片id

	boolean deletePicById(int id);//删除图片

}
