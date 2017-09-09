/**   
* @Title: EmployeeController.java 
* @Package com.atguigu.crud.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2017年9月7日 下午4:32:51 
* @version V1.0   
*/
package com.atguigu.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName: EmployeeController
 * @Description: 处理员工CRUD请求
 * @author A18ccms a18ccms_gmail_com
 * @date 2017年9月7日 下午4:32:51
 * 
 */

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	
	/**
	 * 查询员工数据（分页查询）返回json	
	*/
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 引入Pagehelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页大小
		PageHelper.startPage(pn, 5);
		
		// startPage后面紧跟的这个查询 就是分页查询
		List<Employee> emps = employeeService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息，包括有我们查询出来的数据,传入连续显示的页数
		PageInfo page = new PageInfo(emps, 5);
		
		return Msg.success().add("pageInfo",page);
	}

	/**
	 * 查询员工数据（分页查询）
	 */
	// @RequestMapping("/emps")
	public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// 引入Pagehelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页大小
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的这个查询 就是分页查询
		List<Employee> emps = employeeService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息，包括有我们查询出来的数据,传入连续显示的页数
		PageInfo page = new PageInfo(emps, 5);
		model.addAttribute("pageInfo", page);
		return "list";
	}

}
