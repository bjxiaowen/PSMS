package com.PSMS.Dao;

import com.PSMS.pojo.ToData;

public interface IToDataDao {

	public boolean addToData(ToData toData)throws Exception;
	
	public int getMax()throws Exception;
}
