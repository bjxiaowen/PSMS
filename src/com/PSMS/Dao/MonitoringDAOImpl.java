package com.PSMS.Dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.util.SortHelper;
import com.PSMS.Hibernate.Failure_alarm;
import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.Inverter_parameter;
import com.PSMS.Hibernate.JB_parameter;
import com.PSMS.Hibernate.PM_parameter;
import com.PSMS.Hibernate.PowerMeter;
import com.PSMS.Hibernate.WS_parameter;
import com.PSMS.Hibernate.WeatherStation;
import com.PSMS.util.MonitorData;

public class MonitoringDAOImpl implements MonitoringDAO {

	/*
	 * @brief getPsId
	 */
	public Integer getPsIdByUserName(String userName) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT obj.PS_id FROM M_user obj WHERE obj.User_name =?";
		Query query = session.createQuery(hql);
		query.setString(0, userName);
		Integer psId = (Integer) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return psId;
	}

	/* @brief getParameterIdsByPsId */
	/* @param table */
	/* @param PsId */
	public List<Integer> getParameterIdsByPsIdAndTermId(String table,
			Integer psId, Integer termId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT obj.id FROM " + table
				+ " obj WHERE obj.PS_id = ? AND obj.Period_num = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, psId);
		query.setInteger(1, termId);
		List<Integer> list = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

	/* @brief getNamesByPsId */
	/* @param table */
	/* @param PsId */
	public List<String> getNamesByPsId(String table, Integer psId,
			Integer termId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT obj.name FROM " + table
				+ " obj WHERE obj.PS_id = ? AND obj.Period_num = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, psId);
		query.setInteger(1, termId);
		List<String> list = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		System.out.println(list);
		return list;
	}

	/* @param int start:the index of the first record in current page */
	/* @param int limit:record per a page */
	/* @param String table:select a table */
	/* @return accPowerList */
	public List getResult(int start, int limit, String table, List parameterIds) {

		List<Failure_alarm> failureAlarmList = null;
		if (table.equals("Inverter")) {
			failureAlarmList = this.getFailureAlarmByType("逆变器");
		} else if (table.equals("JunctionBox")) {
			failureAlarmList = this.getFailureAlarmByType("汇流箱");
		}
		List<String> failureCodeList = new ArrayList<String>();
		if (failureAlarmList != null) {
			for (int i = 0; i < failureAlarmList.size(); i++) {
				failureCodeList.add(failureAlarmList.get(i).getFailure_code());
			}
		}
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = null;
		Query query = null;
		if (table.equals("Inverter") || table.equals("JunctionBox")) {
			hql = "FROM "
					+ table
					+ " AS tableObj WHERE tableObj.state IN (:failureCodeList) AND flag <>"
					+ MonitorData.DATA_DELETE
					+ " AND tableObj.parameterId IN (:parameterIds)"
					+ " ORDER BY tableObj.time";
			query = session.createQuery(hql);
			query.setParameterList("failureCodeList", failureCodeList);
		} else {
			hql = "FROM "
					+ table
					+ " AS tableObj WHERE tableObj.parameterId IN (:parameterIds) ORDER BY tableObj.time";
			query = session.createQuery(hql);
		}
		query.setParameterList("parameterIds", parameterIds);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List resultList = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return resultList;
	}

	/* @brief getCountOfTable */
	/* @param String table */
	/* @return count Of Table */
	public long getCount(String table, List<Integer> parameterIds) {

		List<Failure_alarm> failureAlarmList = null;
		if (table.equals("Inverter")) {
			failureAlarmList = this.getFailureAlarmByType("逆变器");
		} else if (table.equals("JunctionBox")) {
			failureAlarmList = this.getFailureAlarmByType("汇流箱");
		}
		List<String> failureCodeList = new ArrayList<String>();
		if (failureAlarmList != null) {
			for (int i = 0; i < failureAlarmList.size(); i++) {
				failureCodeList.add(failureAlarmList.get(i).getFailure_code());
			}
		}

		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = null;
		Query query = null;
		if (table.equals("Inverter") || table.equals("JunctionBox")) {
			// filter data with DATA_DELETE
			hql = "SELECT COUNT(*) FROM "
					+ table
					+ " AS tableObj WHERE tableObj.state IN (:failureCodeList) AND tableObj.flag <>"
					+ MonitorData.DATA_DELETE
					+ " AND tableObj.parameterId IN (:parameterIds)";
			query = session.createQuery(hql);
			query.setParameterList("failureCodeList", failureCodeList);
		} else {
			hql = "SELECT COUNT(*) FROM "
					+ table
					+ " AS tableObj WHERE tableObj.parameterId IN (:parameterIds)";
			query = session.createQuery(hql);
		}
		query.setParameterList("parameterIds", parameterIds);
		long count = (Long) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return count;
	}

	/* @brief getChart by parameterId */
	public List getChartByParameterId(String parameterId, String table) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT obj FROM " + table
				+ " obj WHERE obj.parameterId = '" + parameterId
				+ "' ORDER BY obj.time DESC";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(50);
		List list = query.list();

		if (table.equals("Inverter")) {
			SortHelper.sortInverterList(list);
		} else if (table.equals("PowerMeter")) {
			SortHelper.sortPowerMeterList(list);
		} else if (table.equals("JunctionBox")) {
			SortHelper.sortJunctionBoxList(list);
		} else if (table.equals("WeatherStation")) {
			SortHelper.sortWeatherStationList(list);
		}
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

	public Integer getParameterIdByName(List<Integer> parameterIds,
			String name, String table) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT obj.id FROM " + table
				+ " obj WHERE obj.id IN (:ids) AND obj.name = '" + name + "'";
		System.out.println(hql);
		Query query = session.createQuery(hql);
		query.setParameterList("ids", parameterIds);
		Integer parameterId = (Integer) query.uniqueResult();
		return parameterId;
	}

	/* @brief add BatchDelete Flag */
	public void setBatchDelete(List<Integer> ids, String table) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "UPDATE " + table + " tableObj SET tableObj.flag = '"
				+ MonitorData.DATA_DELETE + "' WHERE tableObj.id IN (:ids)";
		Query query = session.createQuery(hql);
		query.setParameterList("ids", ids);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
	}

	/* @brief add BatchAll Flag */
	public void setAllDeleteOrRecover(String table, Integer option) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = null;
		if (option == MonitorData.DATA_DELETE_OPTION) {
			hql = "UPDATE " + table + " tableObj SET tableObj.flag = "
					+ MonitorData.DATA_DELETE;
		} else if (option == MonitorData.DATA_RECOVER_OPTION) {
			hql = "UPDATE " + table + " tableObj SET tableObj.flag = "
					+ MonitorData.DATA_RECOVER;
		}
		Query query = session.createQuery(hql);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
	}

	/* @brief getFailureAlarm */
	public List<Failure_alarm> getFailureAlarmByType(String type) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = null;
		Query query = null;
		if (type == null) {
			hql = "FROM Failure_alarm";
			query = session.createQuery(hql);
		} else {
			hql = "FROM Failure_alarm obj WHERE obj.type=?";
			query = session.createQuery(hql);
			query.setString(0, type);
		}
		List<Failure_alarm> list = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

	public List<PowerMeter> getPowerMeterSummary() {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT obj FROM PowerMeter obj ORDER BY obj.time DESC";// 反向取
		Query query = session.createQuery(hql);
		List<PowerMeter> list = query.list();
		SortHelper.sortPowerMeterList(list);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

	public List<WeatherStation> getWeatherStationSummary(
			List<Integer> parameterIds) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT obj FROM WeatherStation obj WHERE obj.parameterId IN (:parameterIds) ORDER BY obj.time DESC";// 反向取
		Query query = session.createQuery(hql);
		query.setParameterList("parameterIds", parameterIds);
		query.setMaxResults(5);
		List<WeatherStation> list = query.list();
		SortHelper.sortWeatherStationList(list);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

	/* @brief getSummaryChart by parameterIds */
	public List getSummaryChart(List parameterIds, String table) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT obj FROM Inverter obj WHERE obj.parameterId IN (:parameterIds) ORDER BY obj.time DESC";
		Query query = session.createQuery(hql);
		query.setParameterList("parameterIds", parameterIds);
		List list = query.list();
		if (table.equals("Inverter")) {
			SortHelper.sortInverterList(list);
		} else if (table.equals("PowerMeter")) {
			SortHelper.sortPowerMeterList(list);
		} else if (table.equals("JunctionBox")) {
			SortHelper.sortJunctionBoxList(list);
		} else if (table.equals("WeatherStation")) {
			SortHelper.sortWeatherStationList(list);
		}
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return list;
	}

	/* @brief getDeviceCount */
	public Map getDeviceCount(String table, Integer psId) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT new map(obj.Period_num AS periodNum,COUNT(*) AS deviceCount) FROM "
				+ table + " obj WHERE obj.PS_id = ? GROUP BY obj.Period_num";
		Query query = session.createQuery(hql);
		query.setInteger(0, psId);
		List<Map> list = query.list();

		// periodNum => deviceCount
		Map<Integer, Long> periodMapCount = new HashMap<Integer, Long>();
		for (Map row : list) {
			periodMapCount.put((Integer) row.get("periodNum"), (Long) row
					.get("deviceCount"));
		}

		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return periodMapCount;
	}

	public Map getDeviceList(String table, Integer psId) {

		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "SELECT obj FROM " + table
				+ " obj WHERE obj.PS_id = ? ORDER BY obj.Period_num";
		Query query = session.createQuery(hql);
		query.setInteger(0, psId);
		int termCount;
		Map<Integer, Map<Integer, String>> termDeviceMap = new TreeMap<Integer, Map<Integer, String>>(
				new Comparator<Integer>() {
					public int compare(Integer a, Integer b) {
						// 升序排序
						return a - b;
					}
				});
		Map<Integer, String> bufferDevice = new TreeMap<Integer, String>(
				new Comparator<Integer>() {
					public int compare(Integer a, Integer b) {
						// 升序排序
						return a - b;
					}
				});

		if (table.equals("Inverter_parameter")) {
			List<Inverter_parameter> list = query.list();
			termCount = list.get(0).getPeriod_num();
			for (Inverter_parameter obj : list) {
				if (termCount != obj.getPeriod_num()) {
					termDeviceMap.put(termCount, bufferDevice);
					termCount = obj.getPeriod_num();
					System.out.println(bufferDevice.toString());
					bufferDevice = new TreeMap<Integer, String>(
							new Comparator<Integer>() {
								public int compare(Integer a, Integer b) {
									return a - b;
								}
							});
				}
				bufferDevice.put(obj.getId(), obj.getName());
			}
			termDeviceMap.put(termCount, bufferDevice);
		} else if (table.equals("PM_parameter")) {
			List<PM_parameter> list = query.list();
			termCount = list.get(0).getPeriod_num();
			for (PM_parameter obj : list) {
				if (termCount != obj.getPeriod_num()) {
					termDeviceMap.put(termCount, bufferDevice);
					termCount = obj.getPeriod_num();
					System.out.println(bufferDevice.toString());
					bufferDevice = new TreeMap<Integer, String>(
							new Comparator<Integer>() {
								public int compare(Integer a, Integer b) {
									return a - b;
								}
							});
				}
				bufferDevice.put(obj.getId(), obj.getName());
			}
			termDeviceMap.put(termCount, bufferDevice);
		} else if (table.equals("JB_parameter")) {
			List<JB_parameter> list = query.list();
			termCount = list.get(0).getPeriod_num();
			for (JB_parameter obj : list) {
				if (termCount != obj.getPeriod_num()) {
					termDeviceMap.put(termCount, bufferDevice);
					termCount = obj.getPeriod_num();
					System.out.println(bufferDevice.toString());
					bufferDevice = new TreeMap<Integer, String>(
							new Comparator<Integer>() {
								public int compare(Integer a, Integer b) {
									return a - b;
								}
							});
				}
				bufferDevice.put(obj.getId(), obj.getName());
			}
			termDeviceMap.put(termCount, bufferDevice);
		} else if (table.equals("WS_parameter")) {
			List<WS_parameter> list = query.list();
			termCount = list.get(0).getPeriod_num();
			for (WS_parameter obj : list) {
				if (termCount != obj.getPeriod_num()) {
					termDeviceMap.put(termCount, bufferDevice);
					termCount = obj.getPeriod_num();
					System.out.println(bufferDevice.toString());
					bufferDevice = new TreeMap<Integer, String>(
							new Comparator<Integer>() {
								public int compare(Integer a, Integer b) {
									return a - b;
								}
							});
				}
				bufferDevice.put(obj.getId(), obj.getName());
			}
			termDeviceMap.put(termCount, bufferDevice);
		}
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		System.out.println(termDeviceMap.toString());
		return termDeviceMap;
	}

	

	@Override
	public List<PowerMeter> getPMRealTimeData(int ps_id, int period_num, int id) {
		// TODO Auto-generated method stub
		Session session=HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql1=" from PM_parameter  where PS_id = ? and Period_num = ?";
		String hql2=" select top 10 * from Power_meter  where Parameter_id = ? order by time desc";			
		Query query1 = session.createQuery(hql1);
		Query query2 = session.createSQLQuery(hql2).addEntity(PowerMeter.class);
		query1.setInteger(0,ps_id);
		query1.setInteger(1,period_num);		
		List<PM_parameter> list_pm = query1.list();
		if(list_pm.size()>0){
			
			query2.setInteger(0,id);
			List<PowerMeter> result = query2.list();
			return result;
		}
		else return null;
		
	}
}
