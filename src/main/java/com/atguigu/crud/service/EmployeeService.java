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
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.bean.EmployeeExample.Criteria;
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
	
	
	/** 
	* 按照员工Id查询员工信息
	*/
	public Employee getEmp(Integer id){
		Employee employee= employeeMapper.selectByPrimaryKey(id);
		return employee;
	}


	/** 
	* 更新员工信息
	*/
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
		
	}


	/** 
	*删除员工
	*/
	public void deleteEmp(Integer id) {		
		employeeMapper.deleteByPrimaryKey(id);
	}


	/** 
	* 批量删除员工信息
	*/
	public void deleteBatch(List<Integer> del_ids) {
		EmployeeExample example=new EmployeeExample();
		Criteria criteria =example.createCriteria();
		//delete from xxx where emp_id in (1,2,3)
		criteria.andEmpIdIn(del_ids);
		employeeMapper.deleteByExample(example);
		
	}

}
