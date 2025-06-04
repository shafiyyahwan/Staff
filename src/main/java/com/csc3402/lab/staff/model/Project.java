
package com.csc3402.lab.staff.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_Id")
    private Integer projectId;

    @Column(name = "project_name")
    private String projName;

    @Column(name = "cost")
    private Integer cost;

    @OneToMany(mappedBy = "project")
    private Set<StaffProject> staffProjects = new HashSet<>();

    public Project() {
    }

    public Project(String projName, Integer cost) {
        this.projName = projName;
        this.cost = cost;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projName='" + projName + '\'' +
                ", cost=" + cost +
                '}';
    }
}

