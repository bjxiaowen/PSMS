/** * * 
* 文件名称: PS_pictureServiceImpl.java *
* 
* 电站图片管理 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-11 下午7:42:32 *
* * @author jie.yang 
*/
package com.PSMS.Service;

import com.PSMS.Dao.PS_pictureDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Hibernate.PS_picture;

public class PS_pictureServiceImpl implements PS_pictureService {
	PS_pictureDAO dao =DAOFactory.getPS_pictureDaoInstance();

	@Override
	public boolean addpsP(PS_picture psP) {
		// TODO Auto-generated method stub
		/** 
		* 上传电站图片*
		* @author jie.yang 
		* @date 2014-11-11 
		*/ 
		return dao.addpsP(psP);
	}

	@Override
	public String getNameByPSid(int PSid) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站id获取图片*
		* @author jie.yang 
		* @date 2014-11-11 
		*/ 
		return dao.getNameByPSid(PSid);
	}

	@Override
	public int getIDByPSidAndName(int psID, String name) {
		// TODO Auto-generated method stub
		/** 
		* 根据电站id和名称获取图片id*
		* @author jie.yang 
		* @date 2014-11-11 
		*/ 
		return dao.getIDByPSidAndName(psID,name);
	}

	@Override
	public boolean deletePicById(int id) {
		// TODO Auto-generated method stub
		/** 
		* 删除图片*
		* @author jie.yang 
		* @date 2014-11-11 
		*/ 
		return dao.deletePicById(id);
	}
}
