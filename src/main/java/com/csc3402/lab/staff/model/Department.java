package com.csc3402.lab.staff.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dept_Id")
    private Integer deptId;

    @Column(name="dept_name")
    private String deptName;
    @Column(name = "address")
    private String address;
    @Column(name="phone")
    private String phone;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Staff> staffs;

    public Department() {
    }

    public Department(String phone, String address, String deptName) {
        this.phone = phone;
        this.address = address;
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<Staff> staffs) {
        this.staffs = staffs;
    }
}
