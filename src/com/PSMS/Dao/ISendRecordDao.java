package com.PSMS.Dao;

import java.util.List;

import com.PSMS.pojo.SendRecord;

public interface ISendRecordDao {
	
	boolean add(SendRecord sendRecord)throws Exception;
	
	List<SendRecord> getAll()throws Exception;

}
