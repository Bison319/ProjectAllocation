package com.assess.employee_allocation.controller;


import com.assess.employee_allocation.model.Employee;
import com.assess.employee_allocation.service.EmployeeProjAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-project-allocation")
public class EmployeeProjAllocationController {

    @Autowired
    private EmployeeProjAllocationService employeeService;


   /* @GetMapping("employee-by-skills")
    public List<Employee> getEmployeeBySkills(@RequestParam String primarySkill, @RequestParam String secondarySkill){
        return  employeeProjAllocationService.getEmployeeBySkills(primarySkill,secondarySkill);
    }*/

    @PostMapping("/allocate")
    public String allocateProject(@RequestParam Long employeeId, @RequestParam Long projectId) {
        return employeeService.allocateProject(employeeId, projectId);
    }

    @PutMapping("/modify-allocation")
    public String modifyProjectAllocation(@RequestParam Long employeeId, @RequestParam Long projectId) {
        return employeeService.modifyProjectAllocation(employeeId, projectId);
    }

    @GetMapping("/second-most-experienced")
    public Employee getSecondMostExperienced(@RequestParam Long projectId) {
        return employeeService.getSecondMostExperiencedEmployee(projectId);
    }

    @GetMapping("/by-skills")
    public List<Employee> getEmployeesBySkills(@RequestParam String primarySkill, @RequestParam String secondarySkill) {
        return employeeService.getEmployeesBySkills(primarySkill, secondarySkill);
    }

    @GetMapping("/unallocated-by-skill")
    public List<Employee> getUnallocatedEmployeesBySkill(@RequestParam String primarySkill) {
        return employeeService.getUnallocatedEmployeesBySkill(primarySkill);
    }



}
