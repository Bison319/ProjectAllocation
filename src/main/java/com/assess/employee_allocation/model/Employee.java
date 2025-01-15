package com.assess.employee_allocation.model;

import com.assess.employee_allocation.CapabilityCentre;
import com.assess.employee_allocation.Designation;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public
class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String EmployeeName;
    private String primarySkill;
    private String secondarySkill;
    private int Overall_experience;
    private Date date_of_joining;
    private boolean proj_allocated = false;

    @ManyToMany(mappedBy = "employees")
    private List<Long> projects;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "CapabilityCentre", columnDefinition = "VARCHAR(255)")
    private CapabilityCentre capabilityCentre;

    @Enumerated(EnumType.STRING)
    @Column(name = "Designation", columnDefinition = "VARCHAR(255)")
    private Designation designation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public String getSecondarySkill() {
        return secondarySkill;
    }

    public void setSecondarySkill(String secondarySkill) {
        this.secondarySkill = secondarySkill;
    }

    public int getOverall_experience() {
        return Overall_experience;
    }

    public void setOverall_experience(int overall_experience) {
        Overall_experience = overall_experience;
    }

    public Date getDate_of_joining() {
        return date_of_joining;
    }

    public void setDate_of_joining(Date date_of_joining) {
        this.date_of_joining = date_of_joining;
    }

    public boolean isProj_allocated() {
        return proj_allocated;
    }

    public void setProj_allocated(boolean proj_allocated) {
        this.proj_allocated = proj_allocated;
    }

    public CapabilityCentre getCapabilityCentre() {
        return capabilityCentre;
    }

    public void setCapabilityCentre(CapabilityCentre capabilityCentre) {
        this.capabilityCentre = capabilityCentre;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", EmployeeName='" + EmployeeName + '\'' +
                ", primarySkill='" + primarySkill + '\'' +
                ", secondarySkill='" + secondarySkill + '\'' +
                ", Overall_experience=" + Overall_experience +
                ", date_of_joining=" + date_of_joining +
                ", proj_allocated=" + proj_allocated +
                ", capabilityCentre=" + capabilityCentre +
                ", designation=" + designation +
                '}';
    }

    public boolean canAllocate() {
        return projects.size() < 3;
    }

    public void addProject(String projectId) {
        if (canAllocate()) {
            projects.add(projectId);
        } else {
            throw new IllegalStateException("Employee cannot be allocated to more than 3 projects.");
        }
    }

    public List<Long> getProjects() {
        return projects;
    }

    public void setProjects(List<Long> projects) {
        this.projects = projects;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
