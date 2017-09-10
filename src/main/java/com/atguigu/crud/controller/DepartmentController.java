package com.atguigu.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.DepartmentService;

/** 
* @ClassName: DepartmentController 
* @Description: 处理和部门有关的请求 
* @author A18ccms a18ccms_gmail_com 
* @date 2017年9月10日 下午4:34:08 
*/
@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	
	/**
	 * 返回所有的部门信息	
	*/
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDepts(){
		List<Department> list=departmentService.getDepts();
		return Msg.success().add("depts", list);
	}

}
 