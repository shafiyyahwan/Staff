package com.csc3402.lab.staff.repository;

import com.csc3402.lab.staff.model.StaffProject;
import com.csc3402.lab.staff.model.StaffProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StaffProjectRepository extends JpaRepository<StaffProject, StaffProjectId> {
    @Query(value = "SELECT * FROM staff_project WHERE staff_id =:id", nativeQuery = true)
    List<StaffProject> findStaffProjectByStaffId(@Param("id") int id);


}
