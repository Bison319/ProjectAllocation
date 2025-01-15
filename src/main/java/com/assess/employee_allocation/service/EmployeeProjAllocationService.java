package com.assess.employee_allocation.service;


import com.assess.employee_allocation.model.Employee;
import com.assess.employee_allocation.model.Project;
import com.assess.employee_allocation.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeProjAllocationService {

    @Autowired
    private EmployeeRepository employeeRepository;

 /*   public List<Employee> getEmployeeBySkills(String primarySkill, String secondarySkill){
        return employeeRepository.findByPrimarySkillsAndSecondarySkill(primarySkill,secondarySkill);
    }

    public List<Employee> getUnallocatedEmployeeBySkill(String primarySkill){
        return employeeRepository.findByPrimarySkillsAndAllocatedFalse(primarySkill);
    }*/

    @Autowired
    private JavaMailSender mailSender;

    public String allocateProject(Long employeeId, Long projectId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isEmpty()) return "Employee not found";

        Employee employee = employeeOpt.get();
        if (employee.getProjects().size() >= 3) return "Employee already allocated to 3 projects";

        Project project = new Project();
        project.setId(projectId);
        employee.getProjects().add(project);
        employeeRepository.save(employee);

        sendEmailNotification(employee, project);
        return "Project allocated successfully";
    }

    public String modifyProjectAllocation(Long employeeId, Long projectId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isEmpty()) return "Employee not found";

        Employee employee = employeeOpt.get();
        employee.getProjects().removeIf(p -> p.getId().equals(projectId));
        Project project = new Project();
        project.setId(projectId);
        employee.getProjects().add(project);
        employeeRepository.save(employee);

        sendEmailNotification(employee, project);
        return "Project allocation modified successfully";
    }

    public Employee getSecondMostExperiencedEmployee(Long projectId) {
        return employeeRepository.findAll().stream()
                .filter(e -> e.getProjects().stream().anyMatch(p -> p.getId().equals(projectId)))
                .sorted(Comparator.comparingInt(Employee::getOverall_experience).reversed())
                .skip(1)
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getEmployeesBySkills(String primarySkill, String secondarySkill) {
        return employeeRepository.findByPrimarySkillsAndSecondarySkill(primarySkill, secondarySkill);
    }

    public List<Employee> getUnallocatedEmployeesBySkill(String primarySkill) {
        return employeeRepository.findByPrimarySkillAndProjectsIsEmpty(primarySkill);
    }
    private void sendEmailNotification(Employee employee, Project project) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(employee.getEmail());
        message.setSubject("Project Allocation Notification");
        message.setText("You have been allocated to project: " + project.getProjectName());
        mailSender.send(message);
    }


}
