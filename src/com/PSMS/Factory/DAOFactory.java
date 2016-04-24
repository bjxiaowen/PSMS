package com.PSMS.Factory;

import com.PSMS.Dao.Connection_bookDAO;
import com.PSMS.Dao.Connection_bookDAOImpl;
import com.PSMS.Dao.Count_userDAO;
import com.PSMS.Dao.Count_userDAOImpl;
import com.PSMS.Dao.EquipmentDAO;
import com.PSMS.Dao.EquipmentDAOImpl;
import com.PSMS.Dao.Failure_alarmDAO;
import com.PSMS.Dao.Failure_alarmDAOImpl;
import com.PSMS.Dao.HistoryOfDayDAO;
import com.PSMS.Dao.HistoryOfDayDAOImpl;
import com.PSMS.Dao.HistoryOfFailureDAO;
import com.PSMS.Dao.HistoryOfFailureDAOImpl;
import com.PSMS.Dao.HistoryOfMonthDAO;
import com.PSMS.Dao.HistoryOfMonthDAOImpl;
import com.PSMS.Dao.HistoryOfYearDAO;
import com.PSMS.Dao.HistoryOfYearDAOImpl;
import com.PSMS.Dao.IAreaDao;
import com.PSMS.Dao.IBiIndexDao;
import com.PSMS.Dao.IBiPowerStationDao;
import com.PSMS.Dao.IFaultMessageDao;
import com.PSMS.Dao.IInspectionDao;
import com.PSMS.Dao.IInspectionManagerDao;
import com.PSMS.Dao.IReAreaPowerStationDao;
import com.PSMS.Dao.IReEngAreaPS;
import com.PSMS.Dao.IReEngineerAreaDao;
import com.PSMS.Dao.IReEngineerPowerStationDao;
import com.PSMS.Dao.ISendRecordDao;
import com.PSMS.Dao.IToDataDao;
import com.PSMS.Dao.InverterDAO;
import com.PSMS.Dao.InverterDAOImpl;
import com.PSMS.Dao.Inverter_parameterDAO;
import com.PSMS.Dao.Inverter_parameterDAOImpl;
import com.PSMS.Dao.JB_parameterDAO;
import com.PSMS.Dao.JB_parameterDAOImpl;
import com.PSMS.Dao.JunctionBoxDAO;
import com.PSMS.Dao.JunctionBoxDAOImpl;
import com.PSMS.Dao.M_userDAO;
import com.PSMS.Dao.M_userDAOImpl;
import com.PSMS.Dao.MonitoringDAO;
import com.PSMS.Dao.MonitoringDAOImpl;
import com.PSMS.Dao.PM_parameterDAO;
import com.PSMS.Dao.PM_parameterDAOImpl;
import com.PSMS.Dao.PS_informationDAO;
import com.PSMS.Dao.PS_informationDAOImpl;
import com.PSMS.Dao.PS_periodDAO;
import com.PSMS.Dao.PS_periodDAOImpl;
import com.PSMS.Dao.PS_pictureDAO;
import com.PSMS.Dao.PS_pictureDAOImpl;
import com.PSMS.Dao.PowerMeterDAO;
import com.PSMS.Dao.PowerMeterDAOImpl;
import com.PSMS.Dao.RoleDAO;
import com.PSMS.Dao.RoleDAOImpl;
import com.PSMS.Dao.WS_parameterDAO;
import com.PSMS.Dao.WS_parameterDAOImpl;
import com.PSMS.Dao.WeatherStationDAO;
import com.PSMS.Dao.WeatherStationDAOImpl;
import com.PSMS.Dao.impl.AreaDaoImpl;
import com.PSMS.Dao.impl.BiIndexDaoImpl;
import com.PSMS.Dao.impl.BiPowerStationDaoImpl;
import com.PSMS.Dao.impl.FaultMessageDaoImpl;
import com.PSMS.Dao.impl.InspectionDaoImpl;
import com.PSMS.Dao.impl.InspectionManagerDaoImpl;
import com.PSMS.Dao.impl.ReAreaPowerStationDaoImpl;
import com.PSMS.Dao.impl.ReEngAreaPSImpl;
import com.PSMS.Dao.impl.ReEngineerAreaDaoImpl;
import com.PSMS.Dao.impl.ReEngineerPowerStationDaoImpl;
import com.PSMS.Dao.impl.SendRecordDaoImpl;
import com.PSMS.Dao.impl.ToDataDaoImpl;
/**
 * Dao工厂类
 * @author Andy
 * @date 2016-03-04
 */
public class DAOFactory {
	
	public static IBiIndexDao getBiIndexDaoInstance(){
		return new BiIndexDaoImpl();
	}
	
	
	public static IBiPowerStationDao getBiPowerStationDaoInstance(){
		return new BiPowerStationDaoImpl();
	}
	
	public static IInspectionManagerDao getInspectionManagerDaoInstance(){
		return new InspectionManagerDaoImpl();
	}
	
	public static IToDataDao getToDataDaoInstance(){
		return new ToDataDaoImpl();
	}
	
	public static ISendRecordDao getSendRecordDaoInstance(){
		return new SendRecordDaoImpl();
	}
	
	public static IFaultMessageDao getFaultMessageInstance(){
		return new FaultMessageDaoImpl();
	}
	
	public static IAreaDao getAreaDaoInstance(){
		return new AreaDaoImpl();
	}
	
	public static IReEngAreaPS getReEngAreaPSInstance(){
		return new ReEngAreaPSImpl();
	}
	
	public static IInspectionDao getInspectionDaolInstance(){
		return new InspectionDaoImpl();
	}
	
	public static IReAreaPowerStationDao getReAreaPowerStationDaolInstance(){
		return new ReAreaPowerStationDaoImpl();
	}
	
	public static IReEngineerAreaDao getReEngineerAreaDaoInstance(){
		return new ReEngineerAreaDaoImpl();
	}
	
	public static IReEngineerPowerStationDao getReEngineerPowerStationDaoInstance(){
		return new ReEngineerPowerStationDaoImpl();
	}

	public static MonitoringDAO getMonitoringDaoInstance() {
		return new MonitoringDAOImpl();
	}

	public static PS_informationDAO getPS_informationDaoInstance() {
		return new PS_informationDAOImpl();
	}

	public static PS_pictureDAO getPS_pictureDaoInstance() {
		return new PS_pictureDAOImpl();
	}

	public static M_userDAO getUserDaoInstance() {
		return new M_userDAOImpl();
	}

	public static RoleDAO getRoleDaoInstance() {
		return new RoleDAOImpl();
	}

	public static PM_parameterDAO getPMDAOInstance() {
		return new PM_parameterDAOImpl();
	}

	public static Inverter_parameterDAO getInverter_parameterDAOInstance() {
		return new Inverter_parameterDAOImpl();
	}

	public static WS_parameterDAO getWSDAOInstance() {
		return new WS_parameterDAOImpl();
	}

	public static JB_parameterDAO getJBDAOInstance() {
		return new JB_parameterDAOImpl();
	}

	public static Connection_bookDAO getConnection_bookDaoInstance() {
		return new Connection_bookDAOImpl();
	}

	public static EquipmentDAO getEquipmentDAOInstance() {
		// TODO Auto-generated method stub
		return new EquipmentDAOImpl();
	}

	// ------------------------LM20141114
	public static HistoryOfYearDAO getHistoryOfYearDaoInstance() {
		return new HistoryOfYearDAOImpl();
	}

	public static InverterDAO getInverterDAOInstance() {
		return new InverterDAOImpl();
	}

	// -----------------------WJJ
	public static PowerMeterDAO getPowerMeterDAOInstance() {
		return new PowerMeterDAOImpl();
	}

	public static WeatherStationDAO getWeatherStationDAOInstance() {
		return new WeatherStationDAOImpl();
	}

	public static JunctionBoxDAO getJunctionBoxDAOInstance() {
		return new JunctionBoxDAOImpl();
	}

	public static HistoryOfDayDAO getHistoryOfDayDaoInstance() {
		return new HistoryOfDayDAOImpl();
	}

	public static HistoryOfMonthDAO getHistoryOfMonthDaoInstance() {
		return new HistoryOfMonthDAOImpl();
	}

	public static Failure_alarmDAO getFailure_alarmDAOInstance() {
		return new Failure_alarmDAOImpl();
	}

	public static HistoryOfFailureDAO getHistoryOfFailureDaoInstance() {
		return new HistoryOfFailureDAOImpl();
	}
	public static Count_userDAO getCount_userDAOInstance() {
		return new Count_userDAOImpl();
	}
	public static PS_periodDAO getPS_periodDaoInstance() {
		return new PS_periodDAOImpl();
	}
}
