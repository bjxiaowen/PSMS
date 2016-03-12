package com.PSMS.Service.impl;

import com.PSMS.Dao.IAreaDao;
import com.PSMS.Dao.IReAreaPowerStationDao;
import com.PSMS.Dao.PS_informationDAO;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.Service.IReAreaPowerStationService;
import com.PSMS.pojo.ReAreaPowerStation;

public class ReAreaPowerStationServiceImpl implements IReAreaPowerStationService {

	IReAreaPowerStationDao dao=DAOFactory.getReAreaPowerStationDaolInstance();
	
	IAreaDao areaDao=DAOFactory.getAreaDaoInstance();
	
	PS_informationDAO psDao= DAOFactory.getPS_informationDaoInstance();
	
	@Override
	public boolean addReAreaPowerStationn(ReAreaPowerStation reAreaPowerStation) throws Exception {
		return dao.addReAreaPowerStationn(reAreaPowerStation);
	}

	@Override
	public boolean UpdateReAreaPowerStation(ReAreaPowerStation reAreaPowerStation) throws Exception {
		return dao.UpdateReAreaPowerStation(reAreaPowerStation);
	}

	@Override
	public boolean deleteReAreaPowerStation(ReAreaPowerStation reAreaPowerStation) throws Exception {
		return dao.deleteReAreaPowerStation(reAreaPowerStation);
	}


	/*@Override
	public AreaPowerStationAgg getById(int id) throws Exception {
		AreaPowerStationAgg agg=new AreaPowerStationAgg();
		ReAreaPowerStation children=dao.getById(id);
		agg.setChildren(children);
		Area area=areaDao.getById(children.getAreaId());
		agg.setArea(area);
		PS_information powerStation=psDao.getPSByID(children.getPsId());
		agg.setPowerStation(powerStation);
		return agg;
	}

	*//**
	 * 查询所有区域与电厂
	 *//*
	public List<AreaPowerStationAgg> getAll() throws Exception {
		List<ReAreaPowerStation>  qlist=dao.getAll();
		List<AreaPowerStationAgg> reList=new ArrayList<AreaPowerStationAgg>();
		for(ReAreaPowerStation re:qlist){
			AreaPowerStationAgg agg=new AreaPowerStationAgg();
			agg.setChildren(re);
			Area area=areaDao.getById(re.getAreaId());
			agg.setArea(area);
			PS_information powerStation=psDao.getPSByID(re.getPsId());
			agg.setPowerStation(powerStation);
			reList.add(agg);
		}
		return reList;
	}*/
	
}
