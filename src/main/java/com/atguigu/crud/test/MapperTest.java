/**   
* @Title: MapperTest.java 
* @Package com.atguigu.crud.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2017年9月7日 下午2:44:36 
* @version V1.0   
*/
package com.atguigu.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;

/**
 * @ClassName: MapperTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2017年9月7日 下午2:44:36
 * 
 *       1、导入SpringTest模块 2、@ContextConfiguration指定Spring配置文件的位置
 *       3、直接autowired要使用的组件即可
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MapperTest {

	@Autowired
	DepartmentMapper departmentMapper;

	@Autowired
	SqlSession sqlSession;

	@Test
	public void testCRUD() {
		// 1、插入几个部门
		// departmentMapper.insertSelective(new Department(null,"kaifabumen"));
		// departmentMapper.insertSelective(new Department(null,"ceshibumen"));
		// System.out.println(departmentMapper);

		// 2、批量生产员工数据
		EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
		for (int i = 0; i < 1000; i++) {
			String uid=UUID.randomUUID().toString().substring(0, 5)+i;
			mapper.insertSelective(new Employee(null,uid,"M",uid+"@126.com",1,null));			
		}
		
		System.out.println("批量插入完成");

	}

}
