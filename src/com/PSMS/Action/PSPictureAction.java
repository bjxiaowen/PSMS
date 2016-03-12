/** * * 
* 文件名称: PSPictureAction.java *
* 
* 电站图片管理 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-11 下午7:42:32 *
* * @author jie.yang 
*/
package com.PSMS.Action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import com.PSMS.Hibernate.PS_picture;
import com.PSMS.Service.PS_pictureService;
import com.PSMS.Service.PS_pictureServiceImpl;

public class PSPictureAction {
	/** 
	* 电站图片管理*
	* @author jie.yang 
	* @date 2014-11-11
	* @param psPictureService  		
	*/
	PS_pictureService psPictureService;
	
	public String uploadFile(){
		/** 
		* 上传电站图片*
		* @author jie.yang 
		* @date 2014-11-11
		* @param name
		* @param ps_id
		* @param id
		* @param psID
		* @param psP
		* @param result  		
		*/
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		psPictureService =new PS_pictureServiceImpl();	
		String  name=request.getParameter("name");		
		String  ps_id = request.getParameter("ps_id");
		String  id=request.getParameter("id");
		try {
			name = java.net.URLDecoder.decode(name, "UTF-8");      	//以上为获取前台数据，转码
			} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int psID=Integer.parseInt(ps_id);		
		PS_picture psP=new PS_picture();
		if(id!="-1"){
			psP.setId(Integer.parseInt(id));
		}
		psP.setPS_id(psID);
		psP.setPicture_url("");
		psP.setName(name);
		psPictureService.addpsP(psP);		
		ArrayList list = new ArrayList();
		String result = "图片上传成功！";//用result存放提示信息，并将其传回前台
		list.add(result);//通过json将校验结果传回到前台显示
		JSONArray obj = JSONArray.fromObject(list);
		try {
			response.setHeader("Cache-Control","no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getPSName(){
		/** 
		* 获取电站图片名称*
		* @author jie.yang 
		* @date 2014-11-11
		* @param name
		* @param ps_id
		* @param pID
		* @param psID
		*/
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		psPictureService =new PS_pictureServiceImpl();			
		String  ps_id = request.getParameter("ps_id");
		int psID=Integer.parseInt(ps_id);
		String name=psPictureService.getNameByPSid(psID);
		int pID=psPictureService.getIDByPSidAndName(psID, name);	
		ArrayList list = new ArrayList();		
		list.add(name);
		list.add(pID);
		JSONArray obj = JSONArray.fromObject(list);
		try {
			response.setHeader("Cache-Control","no-cache");
			response.setContentType("aplication/json;charset=UTF-8");
			response.getWriter().print(obj);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String deletePic(){
		/** 
		* 删除电站图片*
		* @author jie.yang 
		* @date 2014-11-11
		* @param id
		* @param pid
		*/
		HttpServletRequest request =ServletActionContext.getRequest();
		HttpServletResponse response =ServletActionContext.getResponse();	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pid =  request.getParameter("id");
		int id = Integer.parseInt(pid);
		psPictureService =new PS_pictureServiceImpl();	
		psPictureService.deletePicById(id);//根据id删除该图片
		return null;
	}
}
