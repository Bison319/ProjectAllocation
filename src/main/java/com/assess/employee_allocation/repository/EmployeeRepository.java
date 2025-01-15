package com.assess.employee_allocation.repository;

import com.assess.employee_allocation.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByPrimarySkillsAndSecondarySkill(String primarySkill, String secondarySkill);
    List<Employee> findByPrimarySkillsAndAllocatedFalse(String primarySkill);

    List<Employee> findByPrimarySkillAndProjectsIsEmpty(String primarySkill);
}
