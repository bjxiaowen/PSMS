package com.PSMS.util;

import com.PSMS.pojo.FromData;
import com.PSMS.pojo.ToData;

/**
 * 数据转化工具
 * @author Andy
 *
 */
public class DataTransformTools {
	
	/**
	 * 来源数据转化为目的数据
	 * @param fromData
	 * @return
	 */
	public ToData transform(FromData fromData){
		if(fromData==null){
			return null;
		}
		ToData toData=new ToData();
		//转化数据
		toData.setInverterDataID(fromData.getInverterDataID());//主键
		toData.setInverterID(fromData.getInverterID());//设备id
		String str16=fromData.getReceiveData();//获取16进制的数据
		String[] datas=str16.split(" ");
		if(datas.length>0){
//			String inputVoltage=datas[1];
		}
		return toData;
	}
}
