package com.PSMS.util;

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
}
