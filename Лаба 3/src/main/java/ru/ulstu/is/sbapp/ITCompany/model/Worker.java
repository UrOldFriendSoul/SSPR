package ru.ulstu.is.sbapp.ITCompany.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String workerName;
    private String workerSurname;

    @ManyToMany(mappedBy = "workers")
    private List<Project> projects = new ArrayList<>();

    @OneToOne
    private Position position;

    public Worker(){}

    public Worker(String workerName, String workerSurname) {
        this.workerName = workerName;
        this.workerSurname = workerSurname;
    }

    public Long getId() {
        return id;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerSurname() {
        return workerSurname;
    }

    public void setWorkerSurname(String workerSurname) {
        this.workerSurname = workerSurname;
    }

    public void setProjects(List <Project> projects)
    {
        this.projects = projects;
    }

    public void addProject(Project project) {
        projects.add(project);
        if(!project.getWorkers().contains(this)){
            project.addWorker(this);
        }
    }

    public List<Project> getProjects(){return this.projects;}
    public void setPosition(Position position){
        this.position = position;
    }
    public String getWorkerPosition() {
        return position.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return Objects.equals(id, worker.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", workerName='" + workerName + '\'' +
                ", workerSurname='" + workerSurname + '\'' +
                '}';
    }

}
