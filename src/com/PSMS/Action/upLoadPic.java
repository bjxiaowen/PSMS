/** * * 
* 文件名称: upLoadPic.java *
* 
* 电站图片管理 *
* 
* 版权所有: 版权所有ZTE(C)2015 *
* 
* 完成日期: 2014-11-11 下午7:42:32 *
* * @author jie.yang 
*/
package com.PSMS.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.PSMS.Hibernate.PS_picture;
import com.PSMS.Service.PS_pictureService;
import com.PSMS.Service.PS_pictureServiceImpl;



public class upLoadPic {
	/** 
	* 上传电站图片*
	* @author jie.yang 
	* @date 2014-11-11
	* @param pic
	* @param picFileName
	* @param IDp
	* @param IDps
	* @param psPictureService 		
	*/
	private File pic;
	private String picFileName;
	private int IDp;	
	private int IDps;
	PS_pictureService psPictureService;
	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
	}
	public String getPicFileName() {
		return picFileName;
	}
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}		
	public int getIDp() {
		return IDp;
	}
	public void setIDp(int iDp) {
		IDp = iDp;
	}
	public int getIDps() {
		return IDps;
	}
	public void setIDps(int iDps) {
		IDps = iDps;
	}
	public String getPSPName(){
		/** 
		* 获取电站图片*
		* @author jie.yang 
		* @date 2014-11-11
		* @param ps_id
		* @param psID
		* @param name
		* @param pID 		
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
	

	
	public String importData() throws IOException{
		/** 
		* 上传电站图片*
		* @author jie.yang 
		* @date 2014-11-11
		* @param name
		* @param psID
		* @param id
		* @param picName 
		* @param psP
		* @param saved	
		* @param path	
		* @param ins	
		* @param ous
		*/
		HttpServletRequest request =ServletActionContext.getRequest();	
        HttpServletResponse response = ServletActionContext.getResponse();                
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		psPictureService =new PS_pictureServiceImpl();					
		String name="";
		int psID=-1;
		int id=-1;
		psID=IDps;
		id=IDp;		
		name=picFileName;
		String picName=Integer.toString(psID)+picFileName;
		PS_picture psP=new PS_picture();
		if(id!=-1){
			psP.setId(id);
		}
		psP.setPS_id(psID);
		psP.setPicture_url("");
		psP.setName(picName);
		psPictureService.addpsP(psP);			
		File saved = new File(ServletActionContext.getServletContext().getRealPath("upload"),picName);
		String path = ServletActionContext.getServletContext().getRealPath("upload")+"\\"+picName;
		InputStream ins = null;
		OutputStream ous = null;
		try{
			saved.getParentFile().mkdirs();
			ins = new FileInputStream(pic);
			ous =  new FileOutputStream(saved);
			
			byte[] b = new byte[10485760];
			int len = 0;
			while((len = ins.read(b))!=-1){
				ous.write(b,0,len);
			}
		}catch(Exception e){
			e.printStackTrace();System.out.println("pic fail");
		}finally{
			if(ous!=null) ous.close();
			if(ins!=null) ins.close();
		}	
		return "success";
	}
}
