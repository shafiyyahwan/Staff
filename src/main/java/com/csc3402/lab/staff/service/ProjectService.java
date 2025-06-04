package com.csc3402.lab.staff.service;
import com.csc3402.lab.staff.model.Project;
import java.util.List;

public interface ProjectService {
    List<Project> listAllProjects();
    Project findProjectById(int Id);
}
