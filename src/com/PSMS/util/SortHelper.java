package com.PSMS.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.PSMS.Hibernate.Inverter;
import com.PSMS.Hibernate.JunctionBox;
import com.PSMS.Hibernate.PowerMeter;
import com.PSMS.Hibernate.WeatherStation;

public class SortHelper {
	public static List<Integer> sortIntegerList(List<Integer> list) {
		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer arg0, Integer arg1) {
				return arg0.compareTo(arg1);
			}
		});
		return list;
	}

	public static List<Inverter> sortInverterList(List<Inverter> list) {
		Collections.sort(list, new Comparator<Inverter>() {
			public int compare(Inverter arg0, Inverter arg1) {
				return arg0.getTime().compareTo(arg1.getTime());
			}
		});
		return list;
	}

	public static List<PowerMeter> sortPowerMeterList(List<PowerMeter> list) {
		Collections.sort(list, new Comparator<PowerMeter>() {
			public int compare(PowerMeter arg0, PowerMeter arg1) {
				return arg0.getTime().compareTo(arg1.getTime());
			}
		});
		return list;
	}

	public static List<JunctionBox> sortJunctionBoxList(List<JunctionBox> list) {
		Collections.sort(list, new Comparator<JunctionBox>() {
			public int compare(JunctionBox arg0, JunctionBox arg1) {
				return arg0.getTime().compareTo(arg1.getTime());
			}
		});
		return list;
	}

	public static List<WeatherStation> sortWeatherStationList(
			List<WeatherStation> list) {
		Collections.sort(list, new Comparator<WeatherStation>() {
			public int compare(WeatherStation arg0, WeatherStation arg1) {
				return arg0.getTime().compareTo(arg1.getTime());
			}
		});
		return list;
	}
}
