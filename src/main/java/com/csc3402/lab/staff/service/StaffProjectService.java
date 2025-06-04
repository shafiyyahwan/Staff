package com.csc3402.lab.staff.service;

import java.util.List;
import com.csc3402.lab.staff.model.StaffProject;

public interface StaffProjectService {

    void saveProject(StaffProject staffProject);
    List<StaffProject> findStaffProjectByStaffId(int Id);
}
