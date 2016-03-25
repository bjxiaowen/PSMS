package com.test;

import java.util.Date;
import java.util.List;

import com.PSMS.Dao.IFromInverterInfo;
import com.PSMS.Dao.IToDataDao;
import com.PSMS.Factory.BaseDaoFactory;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.FromData;
import com.PSMS.pojo.ToData;
import com.PSMS.util.DataTransformTools;

public class FromDataTest {

	public static void main(String[] args) {
		IFromInverterInfo dao=BaseDaoFactory.getFromInverterInfoDaoInstance();
		IToDataDao tdao=DAOFactory.getToDataDaoInstance();
		List<FromData> list=dao.getById(15629);
		if(list!=null&&list.size()>0){
			for(FromData fromData:list){
				ToData toData=DataTransformTools.transform(fromData);
				System.out.println(toData.toString());
				try {
					tdao.addToData(toData);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void addBase(IFromInverterInfo dao){
		FromData fromData=new FromData();
		fromData.setInverterDataID(15629);
		fromData.setInverterID("000007");
		fromData.setReceiveData("3B 00 95 91 00 80 40 03 1E 01 0C 00 83 00 00 00 00 69 04 0D");
		fromData.setOperateDate(new Date());
		fromData.setType("dtu-sz");
		dao.add(fromData);
	}
	
	public static void getBase(IFromInverterInfo dao){
		List<FromData> list=dao.getById(15629);
		for(FromData fromData:list){
			System.out.println(fromData.toString());
		}
	}

}
