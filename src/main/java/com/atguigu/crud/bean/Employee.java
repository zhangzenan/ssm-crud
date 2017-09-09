package com.atguigu.crud.bean;

public class Employee {
    private Integer empId;

    private String empName;

    private String gender;

    private String email;
    
    private Integer dId;
    
    private Department department;

    public Integer getdId() {
		return dId;
	}

	/**
	 * @param empId
	 * @param empName
	 * @param gender
	 * @param email
	 * @param dId
	 * @param department
	 */
	public Employee(Integer empId, String empName, String gender, String email, Integer dId, Department department) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.email = email;
		this.dId = dId;
		this.department = department;
	}

	/**
	 * 
	 */
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setdId(Integer dId) {
		this.dId = dId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}