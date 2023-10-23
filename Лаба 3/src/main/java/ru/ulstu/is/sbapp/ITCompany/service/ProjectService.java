package ru.ulstu.is.sbapp.ITCompany.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.ITCompany.model.Project;
import ru.ulstu.is.sbapp.ITCompany.model.Worker;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProjectService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Project addProject(String projectName, String projectDescription, int projectImportancy) {
        if (!StringUtils.hasText(projectName) || !StringUtils.hasText(projectDescription) || 0 >= projectImportancy || projectImportancy >= 21 ) {
            throw new IllegalArgumentException("Project name is null or empty");
        }
        final Project project = new Project(projectName, projectDescription, projectImportancy);
        em.persist(project);
        return project;
    }

    @Transactional(readOnly = true)
    public Project findProject(Long id) {
        final Project project = em.find(Project.class, id);
        if (project == null) {
            throw new EntityNotFoundException(String.format("Project with id [%s] is not found", id));
        }
        return project;
    }

    @Transactional(readOnly = true)
    public List<Project> findAllProjects() {
        return em.createQuery("select s from Project s", Project.class).getResultList();
    }

    @Transactional
    public Project updateProject(Long id, String projectName, String projectDescription, int projectImportancy) {
        if (!StringUtils.hasText(projectName) || !StringUtils.hasText(projectDescription) || 0 >= projectImportancy || projectImportancy >= 21) {
            throw new IllegalArgumentException("Project name is null or empty");
        }
        final Project currentProject = findProject(id);
        currentProject.setProjectName(projectName);
        currentProject.setProjectDescription(projectDescription);
        currentProject.setProjectImportancy(projectImportancy);
        return em.merge(currentProject);
    }

    @Transactional
    public Project deleteProject(Long id) {
        final Project currentProject = findProject(id);
        em.remove(currentProject);
        return currentProject;
    }

    @Transactional
    public void deleteAllProjects() {
        em.createQuery("delete from Project").executeUpdate();
    }

    @Transactional
    public void addWorkerToProject(Project project, Worker worker) {
        if (worker.getId().toString().isEmpty()) {
            throw new IllegalArgumentException("Worker List is null or empty");
        }
        project.addWorker(worker);
        em.merge(project);
    }
}
