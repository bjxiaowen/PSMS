package com.PSMS.Action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Service.impl.BiIndexServiceImpl;
import com.PSMS.pojo.PowerStationBase;
import com.PSMS.util.GetTime;

import net.sf.json.JSONObject;

/**
 * 图表逆变器
 * @author Andy
 *
 */
public class BiInverterAction {

	public String toBiInverter(){
		return "success";
	}
}
