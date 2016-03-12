/** * * 
* 文件名称: PS_informationDAOImpl.java *
* 
* 电站信息管理，增删改查电站信息 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-10-11 下午3:22:33 *
* * @author jie.yang 
*/
package com.PSMS.Dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.PSMS.Hibernate.HibernateSessionFactory;
import com.PSMS.Hibernate.PM_parameter;
import com.PSMS.Hibernate.PS_information;

public class PS_informationDAOImpl implements PS_informationDAO {
	/**
	 * 电站信息管理需要的操作函数*
	 * 
	 * @author jie.yang
	 * @date 2014-10-11
	 */
	@Override
	public PS_information getInformation() {
		// TODO Auto-generated method stub
		// 获取电站信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();

		String hql = "from PS_information c where c.id=1";
		Query query = session.createQuery(hql);
		PS_information p = (PS_information) query.uniqueResult();

		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();

		return p;
	}

	@Override
	public List<PS_information> getAllInformation() {
		// TODO Auto-generated method stub
		// 获取所有电站信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();

		String hql = "from PS_information";
		Query query = session.createQuery(hql);
		List<PS_information> p = query.list();

		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public boolean findPsByName(String psName) {
		// TODO Auto-generated method stub

		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from PS_information p where p.name= ? ";
		Query query = session.createQuery(hql);
		query.setString(0, psName);
		PS_information p = (PS_information) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		if (p != null)
			return true;
		return false;
	}

	@Override
	public List<String> getAllStationLongitude() {
		// TODO Auto-generated method stub
		// 获取所有电站的经度
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "select longitude from PS_information ";
		query = session.createQuery(hql);
		List<String> u = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return u;
	}

	@Override
	public List<String> getAllStationLatitude() {
		// TODO Auto-generated method stub
		// 获取所有电站的纬度
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "select latitude from PS_information ";
		query = session.createQuery(hql);
		List<String> u = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return u;

	}

	@Override
	public List<String> getAllStationName() {
		// TODO Auto-generated method stub
		// 获取所有电站的名称
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "select name from PS_information where Delete_flag = 0";
		query = session.createQuery(hql);
		List<String> u = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return u;
	}

	@Override
	public String getStationNameById(int ps_id) {
		// TODO Auto-generated method stub
		// 通过电站id获取电站名称
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "select name from PS_information c where c.id = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		String p = (String) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public int getPS_idByName(String ps_name) {
		// TODO Auto-generated method stub
		// 通过电站名称获取id
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "select id from PS_information c where c.name = ?";
		Query query = session.createQuery(hql);
		query.setString(0, ps_name);
		Integer p = (Integer) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public List<PS_information> getAllStation() {
		// 获取所有有效电站
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from PS_information c where c.Delete_flag = 0";
		Query query = session.createQuery(hql);
		List<PS_information> p = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public boolean addPs(PS_information ps) {
		// 增加电站
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.save(ps);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean deleteUserById(int id) {
		// 通过电站id删除电站
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		Query query;
		String hql = "delete from PS_information where id = ?";
		query = session.createQuery(hql);
		query.setInteger(0, id);
		query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean updatePS(int ps_id, String ps_name, String ps_capacity, String ps_area, Integer ps_part_num,
			String ps_owner, String ps_investor, String ps_province, String ps_longitude, String ps_latitude,
			String ps_build_time) {
		// TODO Auto-generated method stub
		// 根据电站id更新电站信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();

		String hql = "update PS_information c set c.name=? , c.capacity=? , c.area=? ,c.Part_num=?,"
				+ "c.owner=? ,c.investor=?, c.province=?,c.longitude=?,c.latitude=?,c.Build_time=? where  c.id=?";
		Query query = session.createQuery(hql);
		query.setString(0, ps_name);
		query.setString(1, ps_capacity);
		query.setString(2, ps_area);
		query.setInteger(3, ps_part_num);
		query.setString(4, ps_owner);
		query.setString(5, ps_investor);
		query.setString(6, ps_province);
		query.setString(7, ps_longitude);
		query.setString(8, ps_latitude);
		query.setString(9, ps_build_time);
		query.setInteger(10, ps_id);

		int ret = query.executeUpdate();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public List<PS_information> getStationByName(String ps_name) {
		// TODO Auto-generated method stub
		// 根据电站名称获得电站列表
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();

		String hql = "from PS_information p where p.name=? and p.Delete_flag = 0 ";
		Query query = session.createQuery(hql);
		query.setString(0, ps_name);
		List<PS_information> p = query.list();

		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public List<PS_information> getStationByProvince(String ps_province) {
		// TODO Auto-generated method stub
		// 根据电站省份获得电站信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();

		String hql = "from PS_information p where p.province=? and p.Delete_flag = 0";
		Query query = session.createQuery(hql);
		query.setString(0, ps_province);
		List<PS_information> p = query.list();

		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public List<PS_information> getStationByTime(String date1, String date2) {
		// TODO Auto-generated method stub
		// 根据电站建站时间获得电站信息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = new Date();
		Date d2 = new Date();
		try {
			d1 = sdf.parse(date1);
			d2 = sdf.parse(date2);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();

		String hql = "from PS_information p where  p.Build_time between ? and ?  and p.Delete_flag = 0";
		Query query = session.createQuery(hql);
		query.setDate(0, d1);
		query.setDate(1, d2);
		List<PS_information> p = query.list();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public boolean updatePs(PS_information ps) {
		// TODO Auto-generated method stub
		// 编辑电站后保存信息
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		session.update(ps);
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return true;
	}

	@Override
	public boolean checkPsNameIsExist(String name) {
		// TODO Auto-generated method stub
		// 判断电站是否已存在
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from  PS_information  ps where ps.name= ?";
		Query query = session.createQuery(hql);
		query.setString(0, name);
		PS_information ps = (PS_information) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		if (ps != null)
			return true;
		return false;
	}

	@Override
	public int getPSNum() {
		// TODO Auto-generated method stub
		// 获取电站数量
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "select count(*) from  PS_information p where p.Delete_flag = 0";
		Query query = session.createQuery(hql);
		if (query.uniqueResult() == null) {
			HibernateSessionFactory.commitHibernateTransaction();
			HibernateSessionFactory.closeHibernateSession();
			return 0;
		} else {
			int ps_num = Integer.parseInt(query.uniqueResult().toString());
			HibernateSessionFactory.commitHibernateTransaction();
			HibernateSessionFactory.closeHibernateSession();
			return ps_num;

		}
	}

	@Override
	public String getCapacityById(int ps_id) {
		// TODO Auto-generated method stub
		// 根据ID获得电站的装机容量，即理论发电量
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "select capacity from PS_information c where c.id = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		String p = (String) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public List<PS_information> getStationByPsId(int ps_id) {
		// TODO Auto-generated method stub
		// 根据电站id获得电站
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();

		String hql = "from PS_information p where p.id=? ";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		List<PS_information> p = query.list();

		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public int getUser_numByPsId(int ps_id) {
		// TODO Auto-generated method stub
		// 根据电站id获得用户数目
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "select User_num from PS_information c where c.id = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		Integer p = (Integer) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public PS_information getPSByID(int id) {
		// TODO Auto-generated method stub
		// 根据电站id获得电站
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from PS_information c where c.id = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		PS_information ps = (PS_information) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return ps;
	}

	@Override
	public int getDeleteFlagByPSName(String name) {
		// TODO Auto-generated method stub
		// 查询电站是否删除
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "select Delete_flag from PS_information c where c.name = ?";
		Query query = session.createQuery(hql);
		query.setString(0, name);
		Integer p = (Integer) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}

	@Override
	public PS_information getById(int ps_id) {
		Session session = HibernateSessionFactory.getHibernateSession();
		HibernateSessionFactory.begainHibernateTransaction();
		String hql = "from PS_information c where id = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, ps_id);
		PS_information p = (PS_information) query.uniqueResult();
		HibernateSessionFactory.commitHibernateTransaction();
		HibernateSessionFactory.closeHibernateSession();
		return p;
	}
}
