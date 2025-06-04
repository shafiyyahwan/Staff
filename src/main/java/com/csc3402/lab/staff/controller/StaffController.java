package com.csc3402.lab.staff.controller;

import com.csc3402.lab.staff.model.Project;
import com.csc3402.lab.staff.model.Staff;
import com.csc3402.lab.staff.model.StaffProject;
import com.csc3402.lab.staff.service.DepartmentService;
import com.csc3402.lab.staff.service.ProjectService;
import com.csc3402.lab.staff.service.StaffProjectService;
import com.csc3402.lab.staff.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/staffs")
public class StaffController {

    // Constructor Injection StaffService
    private final StaffService staffService;
    private final DepartmentService departmentService;
    private final ProjectService projectService;
    private final StaffProjectService staffProjectService;

    public StaffController(StaffService staffService, DepartmentService departmentService,
                           ProjectService projectService, StaffProjectService staffProjectService) {

        this.staffService = staffService;
        this.departmentService = departmentService;
        this.projectService = projectService;
        this.staffProjectService = staffProjectService;
    }


    @GetMapping("list")
    public String showAllStaffForm(Model model) {
        model.addAttribute("staffs", staffService.listAllStaffs());
        return "list-staff";
    }

    @GetMapping("signup")
    public String showAddNewStaffForm(Staff staff, Model model){
        model.addAttribute("departments", departmentService.listAllDepartments());
        return "add-staff";
    }

    @PostMapping("add")
    public String addStaff(@Valid Staff staff, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-staff";
        }

        staffService.addNewStaff(staff);
        return "redirect:list";
    }

    // UPDATE STAFF
    @GetMapping("update")
    public String showUpdateMainForm(Model model) {
        model.addAttribute("staffs", staffService.listAllStaffs());
        return "choose-staff-to-update";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Staff staff = staffService.findStaffById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        model.addAttribute("staff", staff);
        model.addAttribute("departments", departmentService.listAllDepartments());
        return "update-staff";
    }

    @PostMapping("update/{id}")
    public String updateStaff(@PathVariable("id") long id, @Valid Staff staff, BindingResult result, Model model) {
        if (result.hasErrors()) {
            staff.setStaffId((int) id);
            return "index";
        }

        model.addAttribute("staffs", staffService.listAllStaffs());
        staffService.updateStaff(staff);
        return "list-staff";
    }

    // DELETE STAFF
    @GetMapping("delete")
    public String showDeleteMainForm(Model model) {
        model.addAttribute("staffs", staffService.listAllStaffs());
        return "choose-staff-to-delete";
    }

    @GetMapping("delete/{id}")
    public String deleteStaff(@PathVariable("id") long id, Model model) {
        Staff staff = staffService.findStaffById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        staffService.deleteStaff(staff);
        model.addAttribute("staffs", staffService.listAllStaffs());
        return "list-staff";
    }

    //// ASSIGN & DISPLAYING STAFF PROJECTS//
    @GetMapping("assign")
    public String assignStaffProject(Model model) {
        model.addAttribute("staffs", staffService.listAllStaffs());
        return "choose-project-to-assign";
    }

    @GetMapping("assign/{id}")
    public String showAssignProjectForm(@PathVariable("id") long id, @Valid StaffProject staffProject, Model model) {
        Staff staff = staffService.findStaffById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        model.addAttribute("staff", staff);
        model.addAttribute("projects", projectService.listAllProjects());

        return "assign-project";
    }

    @PostMapping("staffproject/{staffid}")
    public String updateStaffProject(@PathVariable("staffid") long id1, @Valid Project proj, @RequestParam("startDate") String
                                             startDate,
                                     @RequestParam("endDate") String endDate, @RequestParam("role") String role,
                                     @Valid StaffProject staffProject, BindingResult result) {
        if (result.hasErrors()) {
            staffProject.setId();
            return "index";
        }

        Staff staff = staffService.findStaffById((int) id1)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id1));


        Project project = projectService.findProjectById(proj.getProjectId());
        StaffProject staffProjectTemp = new StaffProject(staff,project,startDate,endDate,role);
        staffProjectService.saveProject(staffProjectTemp);

        return "index";
    }

    @GetMapping("display")
    public String displayStaffProject(Model model) {
        model.addAttribute("staffs", staffService.listAllStaffs());

        return "choose-project-to-display";
    }

    @GetMapping("display/{staffid}")
    public String showDisplayProjectForm(@PathVariable("staffid") long id, Model model) {

        List<StaffProject> staffProject = (List<StaffProject>) staffProjectService.findStaffProjectByStaffId((int)id);

        model.addAttribute("staff", staffService.QueryStaffById((int) id));
        model.addAttribute("staffProjects", staffProject);

        return "display-project";
    }
}

