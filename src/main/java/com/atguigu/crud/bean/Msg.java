/**   
* @Title: Msg.java 
* @Package com.atguigu.crud.bean 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2017年9月8日 下午3:10:30 
* @version V1.0   
*/
package com.atguigu.crud.bean;

import java.util.HashMap;
import java.util.Map;

/** 
* @ClassName: Msg 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author A18ccms a18ccms_gmail_com 
* @date 2017年9月8日 下午3:10:30 
*  
*/
public class Msg {
	
	//状态码
	private int code;
	
	//提示信息
	private String msg;
	
	//用户要返回给浏览器的数据
	private Map<String,Object> extend=new HashMap<String,Object>();
	
	public static Msg success(){
		Msg result=new Msg();
		result.setCode(100);
		result.setMsg("处理成功！");
		return result;
	}
	
	public static Msg fail(){
		Msg result=new Msg();
		result.setCode(200);
		result.setMsg("处理失败！");
		return result;
	}
	
	public Msg add(String key,Object value){
		this.extend.put(key, value);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	} 
	


}
