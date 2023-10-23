package ru.ulstu.is.sbapp.ITCompany.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String projectName;
    private String projectDescription;
    private int projectImportancy;

    @ManyToMany
    @JoinTable(name = "worker_projects",
    joinColumns =  @JoinColumn(name = "worker_id"),
    inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Worker> workers = new ArrayList<>();

    public Project() {
    }

    public Project(String projectName, String projectDescription, int projectImportancy) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectImportancy = projectImportancy;
    }

    public Long getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public int getProjectImportancy() {
        return projectImportancy;
    }

    public void setProjectImportancy(int projectImportancy) {
        this.projectImportancy = projectImportancy;
    }
    public void setPosition (List<Worker> workers)
    {
        this.workers = workers;
    }
    public void addWorker(Worker worker) {
        workers.add(worker);
        if(!worker.getProjects().contains(this)){
            worker.addProject(this);
        }
    }

    public List<Worker> getWorkers(){return this.workers;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectImportancy='" + projectImportancy + '\'' +
                '}';
    }

}
