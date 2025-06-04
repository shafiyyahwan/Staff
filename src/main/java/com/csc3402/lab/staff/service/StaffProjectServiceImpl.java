package com.csc3402.lab.staff.service;

import com.csc3402.lab.staff.model.StaffProject;
import com.csc3402.lab.staff.repository.StaffProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffProjectServiceImpl implements StaffProjectService {
    private final StaffProjectRepository staffProjectRepository;

    public StaffProjectServiceImpl(StaffProjectRepository projectRepository, StaffProjectRepository staffProjectRepository) {
        this.staffProjectRepository = staffProjectRepository;
    }

    @Override
    public void saveProject(StaffProject staffProject){
        staffProjectRepository.save(staffProject);
    }

    @Override
    public List<StaffProject> findStaffProjectByStaffId(int Id){
        return staffProjectRepository.findStaffProjectByStaffId(Id);
    }


}
