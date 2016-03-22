package com.PSMS.util;

public class Test {

	public static void main(String[] args) {
		System.out.println(IDGenerate.getId());
		
		try {
			HttpSender.send("http://www.zjysms.com/send/gsend.asp?", "zxnygnjs", "zxnygnjs87", "15810581483", "我在测试短信接口", null, null, "ccdx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
