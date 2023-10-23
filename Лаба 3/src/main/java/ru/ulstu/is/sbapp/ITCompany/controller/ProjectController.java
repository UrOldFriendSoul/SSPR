package ru.ulstu.is.sbapp.ITCompany.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.ITCompany.model.Project;
import ru.ulstu.is.sbapp.ITCompany.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/Project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.findProject(id);
    }

    @GetMapping("/")
    public List<Project> getProjects() {
        return projectService.findAllProjects();
    }

    @PostMapping("/")
    public Project createProject(@RequestParam("projectName") String projectName,
                                      @RequestParam("specialtyName") String specialtyName,
                                      @RequestParam("semesterNumber") int semesterNumber) {
        return projectService.addProject(projectName, specialtyName, semesterNumber);
    }

    @PatchMapping("/{id}")
    public Project updateProject(@PathVariable Long id,
                                      @RequestParam("projectName") String projectName,
                                      @RequestParam("specialtyName") String specialtyName,
                                      @RequestParam("semesterNumber") int semesterNumber) {
        return projectService.updateProject(id, projectName, specialtyName, semesterNumber);
    }

    @DeleteMapping("/{id}")
    public Project deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }
}