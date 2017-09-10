/**   
* @Title: EmployeeService.java 
* @Package com.atguigu.crud.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2017年9月7日 下午4:37:25 
* @version V1.0   
*/
package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.EmployeeMapper;

/** 
* @ClassName: EmployeeService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author A18ccms a18ccms_gmail_com 
* @date 2017年9月7日 下午4:37:25 
*  
*/

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;
	
	/** 
	* @Title: getAll 
	* @Description:  
	* @param @return    设定文件 
	* @return List<Employee>    返回类型 
	* @throws 
	*/
	public List<Employee> getAll() {		
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	
	/** 
	* 员工保存
	*/
	public void saveEmp(Employee employee){
		employeeMapper.insertSelective(employee);
	}

}
