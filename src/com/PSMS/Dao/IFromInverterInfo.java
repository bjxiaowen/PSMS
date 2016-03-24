package com.PSMS.Dao;

import java.util.List;

import com.PSMS.pojo.FromData;

public interface IFromInverterInfo {

	boolean add(FromData fromData);
	
	List<FromData> getById(int id);
}
