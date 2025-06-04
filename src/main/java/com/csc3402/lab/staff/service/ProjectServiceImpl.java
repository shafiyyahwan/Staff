package com.csc3402.lab.staff.service;

import com.csc3402.lab.staff.model.Project;
import com.csc3402.lab.staff.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> listAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project findProjectById(int Id) {
        return projectRepository.findProjectById(Id);
    }
}
