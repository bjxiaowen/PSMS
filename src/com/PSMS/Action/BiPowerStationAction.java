package com.PSMS.Action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.PSMS.Adapter.stationMonitorAdapter;
import com.PSMS.Hibernate.PS_information;
import com.PSMS.Service.HistoryOfDayService;
import com.PSMS.Service.HistoryOfDayServiceImpl;
import com.PSMS.Service.IBiPowerStationService;
import com.PSMS.Service.InverterService;
import com.PSMS.Service.InverterServiceImpl;
import com.PSMS.Service.Inverter_parameterService;
import com.PSMS.Service.Inverter_parameterServiceImpl;
import com.PSMS.Service.PS_informationService;
import com.PSMS.Service.PS_informationServiceImpl;
import com.PSMS.Service.WS_parameterService;
import com.PSMS.Service.WS_parameterServiceImpl;
import com.PSMS.Service.WeatherStationService;
import com.PSMS.Service.WeatherStationServiceImpl;
import com.PSMS.Service.impl.BiPowerStationServiceImpl;
import com.PSMS.pojo.PSTotal;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.GetTime;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class BiPowerStationAction {
	
	private IBiPowerStationService biPowerStationService;
	private PS_informationService ps_informationService ;
	private Inverter_parameterService inverterParameterService;
	private InverterService inverterService;
	private WeatherStationService weatherStationService;
	private WS_parameterService wsParameterService;
	private HistoryOfDayService historyOfDayService;
	
	/**
	 * 电站地图数据
	 * @return
	 */
	public String toPSMap(){
		try {
			HttpServletRequest request =ServletActionContext.getRequest();
			request.setCharacterEncoding("utf-8");
			ps_informationService = new PS_informationServiceImpl();
			biPowerStationService=new BiPowerStationServiceImpl();
			List<PS_information> ps_list=ps_informationService.getAllStation();//查询电站信息
			String dateTime=GetTime.getCurrentTime3();
			BigDecimal sumCapacity=new BigDecimal("0");
			for(PS_information psInfo:ps_list){//通过电站查询电站状态
				int pId=psInfo.getId();
				PowerStationBase power=biPowerStationService.getPowerStationStatus(null, pId);
				if(power.getCapacity()!=null){
					sumCapacity=sumCapacity.add(power.getCapacity());
				}
				
				psInfo.setMachineState(power.getMachineState());
			}
			PSTotal psTotal=biPowerStationService.getPSTotalData();
			psTotal.setTotalHistoryQ(sumCapacity);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("list", ps_list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
			request.setAttribute("list", object.toString());
			request.setAttribute("psTotal", psTotal);
			System.out.println(object.toString());
		}catch(IOException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	
	/**
	 * 组件
	 * @return
	 */
	public String toModule(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			int pId=Integer.parseInt(psId);
			PowerStationBase total=biPowerStationService.getPowerStationDayByDate(dateTime,pId);
			List<PowerStationBase> list=biPowerStationService.getPowerStationHourByDate(dateTime,pId);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", total);
			object.put("list", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
			request.setAttribute("total", total);
			request.setAttribute("list", list);
			request.setAttribute("psId", psId);//电站id
			System.out.println(object.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "success";
	}
	
	
	public String charts(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			int pId=Integer.parseInt(psId);
			PowerStationBase total=biPowerStationService.getPowerStationDayByDate(dateTime,pId);
			List<PowerStationBase> list=biPowerStationService.getPowerStationHourByDate(dateTime,pId);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", total);
			object.put("list", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
			request.setAttribute("total", total);
			request.setAttribute("list", list);
			request.setAttribute("psId", psId);//电站id
			System.out.println(object.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "success";
	}
	
	/**
	 * 组件
	 * @return
	 */
	public String getModule(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			int pId=Integer.parseInt(psId);
			PowerStationBase total=biPowerStationService.getPowerStationDayByDate(dateTime,pId);
			List<PowerStationBase> list=biPowerStationService.getPowerStationHourByDate(dateTime,pId);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", total);
			object.put("list", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	
	/**
	 * 蓄电池
	 * @return
	 */
	public String toBatteryVoltage(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			int pId=Integer.parseInt(psId);
			PowerStationBase total=biPowerStationService.getBatteryVoltageDayByDate(dateTime,pId);
			List<PowerStationBase> list=biPowerStationService.getBatteryVoltageHourByDate(dateTime,pId);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", total);
			object.put("list", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
			request.setAttribute("total", total);
			request.setAttribute("list", list);
			request.setAttribute("psId", psId);//电站id
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "success";
	}
	
	
	/**
	 * 蓄电池
	 * @return
	 */
	public String getBatteryVoltage(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			int pId=Integer.parseInt(psId);
			PowerStationBase total=biPowerStationService.getBatteryVoltageDayByDate(dateTime,pId);
			List<PowerStationBase> list=biPowerStationService.getBatteryVoltageHourByDate(dateTime,pId);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", total);
			object.put("list", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
			request.setAttribute("total", total);
			request.setAttribute("list", list);
			request.setAttribute("psId", psId);//电站id
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	/**
	 * 控制器输出
	 * @return
	 */
	public String toControlOutShow(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			int pId=Integer.parseInt(psId);
			PowerStationBase outTotal=biPowerStationService.getControlOutShowDayByDate(dateTime,pId);
			List<PowerStationBase> outList=biPowerStationService.getControlOutShowHourByDate(dateTime,pId);
			PowerStationBase inTotal=biPowerStationService.getControlInShowDayByDate(dateTime,pId);
			List<PowerStationBase> inList=biPowerStationService.getControlInShowHourByDate(dateTime,pId);//蓄电池
			PowerStationBase voltageTotal=biPowerStationService.getBatteryVoltageDayByDate(dateTime,pId);
			List<PowerStationBase> voltageList=biPowerStationService.getBatteryVoltageHourByDate(dateTime,pId);
			PowerStationBase modelTotal=biPowerStationService.getPowerStationDayByDate(dateTime,pId);
			List<PowerStationBase> modelList=biPowerStationService.getPowerStationHourByDate(dateTime,pId);
			PowerStationBase outStatus=biPowerStationService.getOutputStatus(dateTime, pId);//输出
			JSONObject object = JSONObject.fromObject("{}");
			object.put("outTotal", outTotal);//输出
			object.put("outList", outList);
			object.put("inTotal", inTotal);//输入
			object.put("inList", inList);
			object.put("voltageTotal", voltageTotal);
			object.put("voltageList", voltageList);
			object.put("modelTotal", modelTotal);
			object.put("modelList", modelList);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
//			ServletActionContext.getResponse().getWriter().write(object.toString());
			System.err.println(object.toString());
			request.setAttribute("outList", outList);
			request.setAttribute("outTotal", outTotal);
			request.setAttribute("inTotal", inTotal);
			request.setAttribute("inList", inList);
			request.setAttribute("voltageTotal", voltageTotal);
			request.setAttribute("voltageList", voltageList);
			request.setAttribute("modelTotal", modelTotal);
			request.setAttribute("modelList", modelList);
			request.setAttribute("outStatus", outStatus);//输出状态
			request.setAttribute("psId", psId);//电站id
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "success";
	}
	
	/**
	 * 控制器输出
	 * @return
	 */
	public String getControlOutShow(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			int pId=Integer.parseInt(psId);
			PowerStationBase outTotal=biPowerStationService.getControlOutShowDayByDate(dateTime,pId);
			List<PowerStationBase> outList=biPowerStationService.getControlOutShowHourByDate(dateTime,pId);
			PowerStationBase inTotal=biPowerStationService.getControlInShowDayByDate(dateTime,pId);
			List<PowerStationBase> inList=biPowerStationService.getControlInShowHourByDate(dateTime,pId);//蓄电池
			PowerStationBase voltageTotal=biPowerStationService.getBatteryVoltageDayByDate(dateTime,pId);
			List<PowerStationBase> voltageList=biPowerStationService.getBatteryVoltageHourByDate(dateTime,pId);
			PowerStationBase modelTotal=biPowerStationService.getPowerStationDayByDate(dateTime,pId);
			List<PowerStationBase> modelList=biPowerStationService.getPowerStationHourByDate(dateTime,pId);
			PowerStationBase outStatus=biPowerStationService.getOutputStatus(dateTime, pId);//输出
			JSONObject object = JSONObject.fromObject("{}");
			object.put("outTotal", outTotal);//输出
			object.put("outList", outList);
			object.put("outTotal", outTotal);//输入
			object.put("outList", outList);
			object.put("outStatus", outStatus);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
			request.setAttribute("outList", outList);
			request.setAttribute("outTotal", outTotal);
			request.setAttribute("inTotal", inTotal);
			request.setAttribute("inList", inList);
			request.setAttribute("voltageTotal", voltageTotal);
			request.setAttribute("voltageList", voltageList);
			request.setAttribute("modelTotal", modelTotal);
			request.setAttribute("modelList", modelList);
			request.setAttribute("outStatus", outStatus);//输出状态
			request.setAttribute("psId", psId);//电站id
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	
	/**
	 * 控制器输入
	 * @return
	 */
	public String toControlInShow(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			int pId=Integer.parseInt(psId);
			PowerStationBase total=biPowerStationService.getControlInShowDayByDate(dateTime,pId);
			List<PowerStationBase> list=biPowerStationService.getControlInShowHourByDate(dateTime,pId);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", total);
			object.put("list", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "success";
	}

	/**
	 * 控制器输入
	 * @return
	 */
	public String getControlInShow(){
		HttpServletRequest request =ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			String dateTime=GetTime.getCurrentTime3();
			String psId = request.getParameter("psId");
			psId = java.net.URLDecoder.decode(psId, "UTF-8");
			biPowerStationService=new BiPowerStationServiceImpl();
			int pId=Integer.parseInt(psId);
			PowerStationBase total=biPowerStationService.getControlInShowDayByDate(dateTime,pId);
			List<PowerStationBase> list=biPowerStationService.getControlInShowHourByDate(dateTime,pId);
			JSONObject object = JSONObject.fromObject("{}");
			object.put("total", total);
			object.put("list", list);
			ServletActionContext.getResponse().setContentType("application/json;charset=UTF-8");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	public String getPowerStationAllInfo(){
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
			ps_informationService = new PS_informationServiceImpl();		
			inverterParameterService = new Inverter_parameterServiceImpl();
			inverterService = new InverterServiceImpl();
			weatherStationService = new WeatherStationServiceImpl();
			wsParameterService = new WS_parameterServiceImpl();
			historyOfDayService  = new HistoryOfDayServiceImpl();
			biPowerStationService=new BiPowerStationServiceImpl();
			List<PS_information> list_ps_information = ps_informationService.getAllStation();//获取所有电站信息
			List<stationMonitorAdapter> list_top = new ArrayList<stationMonitorAdapter>();//存储各个电站实时数据
			String dateTime=GetTime.getCurrentTime3();
			for(int i=0;i<list_ps_information.size();i++){
				stationMonitorAdapter s = new stationMonitorAdapter();//初始化一个实例
				int ps_id = list_ps_information.get(i).getId();
				List<Integer> list_inverter_parameter_id = inverterParameterService.getParameterIDByPs_id(ps_id);
				if(list_inverter_parameter_id.size()==0){//如果电站查不到逆变器编号
					s.setInverterPower("逆变器无");
					s.setState("无逆变器状态");				
				}else {
					String inverterPower ="";
					for(int j=0;j<list_inverter_parameter_id.size();j++){
						int parameter_id  = list_inverter_parameter_id.get(j);
						String power = inverterService.searchTopPowerByParameter_id(parameter_id);//得到每个逆变器的实时功率
						String inverter_name = inverterParameterService.getParameterNameByParameterID(parameter_id);//得到每个逆变器的名称
						inverterPower = inverterPower + "逆变器" + inverter_name + "功率:" + power + "W   ";
					}
					s.setInverterPower(inverterPower);				
				}
				//获取逆变器交流功率实时数据			
				List<Integer> list_ws_parameter_id = wsParameterService.getParameterIDByPs_id(ps_id);
				if(list_ws_parameter_id.size()==0){
					s.setIrradiationValue("辐照度未知");
					s.setTemperature("当前气温未知");//用天气预报代替
				}
				else{
					String irradiationValue = "";
					String tempValue = "";
					for(int j=0;j<list_ws_parameter_id.size();j++){
						int ws_id = list_ws_parameter_id.get(j);
						String iValue = weatherStationService.searchTopFZLByParameter_id(ws_id);
						String temp = weatherStationService.searchTopTempByParameter_id(ws_id);
						String ws_name = wsParameterService.getNameById(ws_id);
						irradiationValue = irradiationValue +"气象站" + ws_name + "辐照度:" + iValue + " W/m²   ";
						tempValue = tempValue  +"气象站" + ws_name + "温度:" + temp + "℃   ";
					}
					s.setIrradiationValue(irradiationValue);
					s.setTemperature(tempValue);
				}//获取气象站实时数据
				
				double dayAccPower = historyOfDayService.getTodayAccPowerByPsID(ps_id);//获取实时当日发电量
				String onLineHour = historyOfDayService.getTodayOnLineHourByPsID(ps_id);//获取实时当日上网小时数
				
				if(dayAccPower==0){
					s.setAccPower("当日发电量0Kwh");
				}
				else{
					BigDecimal   b   =   new   BigDecimal(dayAccPower);  
					double  D_power   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue(); //转换成两位小数 
					
					s.setAccPower("当日发电量："+D_power+"Kwh ");
				}			
				
				s.setActivePower("并网电压: 未知");
				if(list_ps_information.get(i).getId()==80 || list_ps_information.get(i).getId()==92)s.setState("电站状态：在线");
				else s.setState("电站状态：离线");
				list_top.add(s);
			}
			for(PS_information psInf:list_ps_information){//获取每一个电站的信息
				PowerStationBase outStatus=biPowerStationService.getOutputStatus(dateTime, psInf.getId());//输出
				psInf.setMachineState(outStatus.getMachineState());
			}
			ArrayList list = new ArrayList();
			list.add(list_top);
			list.add(list_ps_information);
			JSONArray obj = JSONArray.fromObject(list);
			response.setHeader("Cache-Control","no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
			request.setAttribute("monitorInfo", list_top);
			request.setAttribute("psAllInfo", list_ps_information);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
