package com.PSMS.util;

import java.math.BigDecimal;
import java.util.Date;

import com.PSMS.pojo.FromData;
import com.PSMS.pojo.ToData;

/**
 * 数据转化工具
 * @author Andy
 *
 */
public class DataTransformTools {
	
	
	/**
	 * 来源数据转化为目的数据
	 * @param fromData
	 * @return
	 */
	public static ToData transform(FromData fromData){
		
		if(fromData==null){
			return null;
		}
		ToData toData=new ToData();
		//转化数据
		toData.setInverterDataID(fromData.getInverterDataID());//主键
		toData.setInverterID(fromData.getInverterID());//设备id
		String str16=fromData.getReceiveData();//获取16进制的数据
		toData.setReceiveData(str16);//接收的数据
		toData.setOperateDate(new Date());//操作时间
		toData.setType(fromData.getType());//类型
		String[] datas=str16.split(" ");
		if(datas.length>0&&datas.length>18){
			//十六进制转成十进制
			String one=datas[1];//输入电压
			if(one!=null&&!one.equals("")){
				BigDecimal input=new BigDecimal(Integer.valueOf(one,16));
				BigDecimal factor=new BigDecimal(1.57);
				toData.setInputVoltage(input.multiply(factor).setScale(2,   BigDecimal.ROUND_HALF_UP));
			}
			
			String two=datas[2];//电池电压
			if(two!=null&&!two.equals("")){
				BigDecimal battery=new BigDecimal(Integer.valueOf(two,16));
				BigDecimal batfactor=new BigDecimal(0.18);
				toData.setBatteryVoltage(battery.multiply(batfactor).setScale(2,   BigDecimal.ROUND_HALF_UP));
			}
			
			//8F：输出电压  143*1.57 = 224.51V
			String three=datas[3];
			if(three!=null&&!three.equals("")){
				BigDecimal out=new BigDecimal(Integer.valueOf(three,16));
				BigDecimal factor=new BigDecimal(1.57);
				toData.setOutputVoltage(out.multiply(factor).setScale(2,   BigDecimal.ROUND_HALF_UP));
			}
			
			//输出电流 0/10 = 0A
			String four=datas[4];
			if(four!=null&&!four.equals("")){
				BigDecimal out=new BigDecimal(Integer.valueOf(four,16));
				BigDecimal factor=new BigDecimal(10);
				//进行除法运算
				toData.setOutputCurrent(out.divide(factor));
			}
			
			//机器状态 10000000
			String machineStatus=datas[5];
			if(machineStatus!=null&&!machineStatus.equals("")){
				String dlegth=Integer.toBinaryString(Integer.valueOf(machineStatus,16)).toString();
				if(dlegth!=null&&!dlegth.equals("")&&dlegth.length()==8){
					toData.setUndervoltage(Integer.parseInt(dlegth.substring(0, 1)));	//电池欠压  1：电池欠压  0：电池正常
					toData.setCityPower(Integer.parseInt(dlegth.substring(1, 2)));		//1：市电中断  0：市电正常
					toData.setOvervoltage(Integer.parseInt(dlegth.substring(2, 3)));	//1：电池过压   0：电池正常
					toData.setMachineState(Integer.parseInt(dlegth.substring(3, 4)));   //1：机器失效  0：正常
					toData.setChargeDischarge(Integer.parseInt(dlegth.substring(4, 5)));//0：电池充电 1：电池放电
					toData.setMachineObligate1(Integer.parseInt(dlegth.substring(5, 6)));//预留
					toData.setOutputState(Integer.parseInt(dlegth.substring(6, 7)));	//1：输出过载  0：输出正常
					String hz=dlegth.substring(7, 8)=="0"?"50":"60";	//1:电网频率为60Hz ,0:电网频率为50HZ
					toData.setLineFrequency(hz);
				}
			}
			
			String showStatus=datas[6];
			if(showStatus!=null&&!showStatus.equals("")){
				String legth=Integer.toBinaryString(Integer.valueOf(showStatus,16)).toString();
				if(legth!=null&&!legth.equals("")&&legth.length()==8){
					toData.setCanMake(Integer.parseInt(legth.substring(0, 1)));//1:电池充电使能    0:电池充电禁止
					toData.setInCity(Integer.parseInt(legth.substring(1, 2)));//1:进市电使能   0:进市电禁止
					toData.setShowObligate5(Integer.parseInt(legth.substring(2, 3)));
					toData.setShowObligate4(Integer.parseInt(legth.substring(3, 4)));
					toData.setShowObligate3(Integer.parseInt(legth.substring(4, 5)));
					toData.setShowObligate2(Integer.parseInt(legth.substring(5, 6)));
					toData.setShowObligate1(Integer.parseInt(legth.substring(6, 7)));
					toData.setShowObligate0(Integer.parseInt(legth.substring(7, 8)));
				}
			}
			
			String seven=datas[7];
			String eight=datas[8];
			BigDecimal factor=new BigDecimal(10);
			if(seven!=null&&!seven.equals("")&&eight!=null&&!eight.equals("")){
				String se=seven.trim()+eight.trim();
				BigDecimal inVolt=new BigDecimal(Integer.valueOf(se,16)); //00 00：mppt输入电压 0/10 = 0V
				toData.setMpptInVoltage(inVolt.divide(factor));
			}
			
			String nine=datas[9];
			String ten=datas[10];
			if(nine!=null&&!nine.equals("")&&ten!=null&&!ten.equals("")){
				String nt=nine.trim()+ten.trim();
				BigDecimal outVolt=new BigDecimal(Integer.valueOf(nt,16));//00 00：mppt输出电压 0/10 = 0V
				toData.setMpptOutVoltage(outVolt.divide(factor));
			}
			
			String eleven=datas[11];
			String twelve=datas[12];
			if(eleven!=null&&!eleven.equals("")&&twelve!=null&&!twelve.equals("")){
				String et=eleven.trim()+twelve.trim();
				BigDecimal outCurr=new BigDecimal(Integer.valueOf(et,16));//00 00：mppt输出电流 0/10 = 0A
				toData.setMpptOutCurrent(outCurr.divide(factor));
			}
			
			String thirteen=datas[13];
			String fourteen=datas[14];
			if(thirteen!=null&&!thirteen.equals("")&&fourteen!=null&&!fourteen.equals("")){
				String tf=thirteen.trim()+fourteen.trim();
				BigDecimal outCurr=new BigDecimal(Integer.valueOf(tf,16));//00 00：mppt模块温度 0/10 = 0度
				toData.setMpptTemp(outCurr.divide(factor));
			}
			
			if(datas.length>26){
				String obligate=datas[15];
				if(obligate!=null&&!obligate.equals("")){
					Integer showObligate=Integer.valueOf(obligate,16);//预留字段
					toData.setShowObligate(showObligate);
				}
				
				//直流侧历史发电量
				String historyQ=datas[16];
				if(historyQ!=null&&!historyQ.equals("")){
					BigDecimal historyQNum=new BigDecimal(Integer.valueOf(historyQ,16));
					toData.setCurrHistoryQ(historyQNum);
				}
				
				//直流侧日发电量
				String currDayQ=datas[17];
				if(currDayQ!=null&&!currDayQ.equals("")){
					BigDecimal currDayQNum=new BigDecimal(Integer.valueOf(currDayQ,16));
					toData.setCurrDayQ(currDayQNum);
				}
				
				//直流侧功率
				String currPower=datas[18];
				if(currPower!=null&&!currPower.equals("")){
					BigDecimal currPowerNum=new BigDecimal(Integer.valueOf(currPower,16));
					toData.setCurrPower(currPowerNum);
				}
				
				//负载历史用电量
				String loadHistoryQ=datas[19];
				if(loadHistoryQ!=null&&!loadHistoryQ.equals("")){
					BigDecimal loadHistoryQNum=new BigDecimal(Integer.valueOf(loadHistoryQ,16));
					toData.setLoadHistoryQ(loadHistoryQNum);
				}
				
				
				//负载日用电量
				String loadDayQ=datas[20];
				if(loadDayQ!=null&&!loadDayQ.equals("")){
					BigDecimal loadDayQNum=new BigDecimal(Integer.valueOf(loadDayQ,16));
					toData.setLoadDayQ(loadDayQNum);
				}
				
				//交流输出功率
				String exchangeOutPower=datas[21];
				if(exchangeOutPower!=null&&!exchangeOutPower.equals("")){
					BigDecimal exchange=new BigDecimal(Integer.valueOf(exchangeOutPower,16));
					toData.setExchangeOutPower(exchange);
				}
				
				//二氧化碳
				String carbon=datas[22];
				if(carbon!=null&&!carbon.equals("")){
					BigDecimal carbonNum=new BigDecimal(Integer.valueOf(carbon,16));
					toData.setCarbon(carbonNum);
				}
			}
		}
		return toData;
	}
}
