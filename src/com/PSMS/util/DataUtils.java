package com.PSMS.util;

import java.math.BigDecimal;
import java.util.Date;

import com.PSMS.pojo.InspectionManager;
import com.PSMS.pojo.JointInspection;

public class DataUtils {

	public static InspectionManager toInspectionManager(JointInspection joint,String currDate,String nextDate){
		InspectionManager insm=new InspectionManager();
		insm.setId(joint.getManageId());
		insm.setAreaId(joint.getAreaId());
		insm.setEquipmentId(joint.getEquipmentId());
		insm.setPsId(joint.getPsId());
		insm.setUserId(joint.getUserId());
		insm.setInspectionPeriod(joint.getInspectionPeriod());
		insm.setCurrDate(currDate);
		insm.setNextDate(nextDate);
		return insm;
	}
	
	public static BigDecimal getDecimal(Object obj ){
		return obj==null?new BigDecimal(0):new BigDecimal(obj + "").setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public static Integer getInteger(Object obj){
		return obj==null?Integer.parseInt("0"):Integer.parseInt(obj+"");
	}
	
	public static Date getDate(Object obj){
		return obj==null?new Date():new Date(obj + "");
	}
	
	public static String getString(Object obj){
		return obj==null?null:obj.toString();
	}
	
}
