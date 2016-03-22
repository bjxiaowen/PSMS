package com.test;



import com.PSMS.Dao.IReAreaPowerStationDao;
import com.PSMS.Factory.DAOFactory;
import com.PSMS.pojo.ReAreaPowerStation;
import com.PSMS.util.HttpSender;
import com.PSMS.util.IDGenerate;

public class MyTest {

	public static void main(String[] args) throws Exception{
		//addReAreaPowerStationn();
		//getAll();
		HttpSender.send("http://www.zjysms.com/send/gsend.asp?", "zxnygnjs", "zxnygnjs87", "13520029132", "我在测试短信接口", "", "", "ccdx");
		
		 // 构造HttpClient的实例
//        HttpClient httpClient = new HttpClient();
//        GetMethod getMethod = null;
//        try {
//            getMethod = new GetMethod(
//                    "http://www.zjysms.com/send/gsend.asp?name=zxnygnjs&pwd=zxnygnjs87&dst=13520029132&sender=&time=&txt=ccdx&msg="
//                            + URLEncoder.encode("我在测试短信接口", "GB2312"));
//            // 使用系统提供的默认的恢复策略
//            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
//            int statusCode = httpClient.executeMethod(getMethod);
//            System.out.println("短信发送状态" + statusCode);
//            if (statusCode != HttpStatus.SC_OK) {
//                System.err.println("Method failed: " + getMethod.getStatusLine());
//            }
//            // 读取内容
//            byte[] responseBody = getMethod.getResponseBody();
//            // 处理内容
//            System.out.println(new String(responseBody, "GB2312"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 释放连接
//            getMethod.releaseConnection();
//        }
	}
	
	public static void addReAreaPowerStationn() throws Exception{
		IReAreaPowerStationDao rAPS=DAOFactory.getReAreaPowerStationDaolInstance();
		ReAreaPowerStation reAreaPowerStation=new ReAreaPowerStation();
		reAreaPowerStation.setAreaId("14571890456820001");
		reAreaPowerStation.setId("Re"+IDGenerate.getId());
		reAreaPowerStation.setPsId(80);
		rAPS.addReAreaPowerStationn(reAreaPowerStation);
	}
	
	public static void getAll() throws Exception{/*
		IReAreaPowerStationDao rAPS=DAOFactory.getReAreaPowerStationDaolInstance();
		AreaPowerStationAgg agg=new AreaPowerStationAgg();
		List<ReAreaPowerStation> list=rAPS.getAll();
		for(ReAreaPowerStation re:list){
			agg.setId(re.getId());
			agg.setPsId(re.getPsId());
			agg.setAreaId(re.getAreaId());
			PS_informationDAO psDao=DAOFactory.getPS_informationDaoInstance();
			PS_information powerStation=psDao.getById(re.getPsId());
			System.out.println(powerStation);
			agg.setPowerStation(powerStation);
			IAreaDao aDao=DAOFactory.getAreaDaolInstance();
			Area area=aDao.getById(re.getAreaId());
			agg.setArea(area);
		}
		
		System.out.println(agg.toString());
	*/}
}
