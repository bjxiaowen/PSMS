package com.PSMS.Action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Service.IReAreaPowerStationService;

public class ReAreaPowerStationAction {
	
	private IReAreaPowerStationService reAreaPowerStationService;
	
	
	
	/** 
	* 人员分配
	* @author jie.yang
	* @date 2014-1-12  
	*/
	public String toAssignment(){
		HttpServletRequest request =ServletActionContext.getRequest();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		return "success";
	}
	
	

	public IReAreaPowerStationService getReAreaPowerStationService() {
		return reAreaPowerStationService;
	}

	public void setReAreaPowerStationService(IReAreaPowerStationService reAreaPowerStationService) {
		this.reAreaPowerStationService = reAreaPowerStationService;
	}

}
