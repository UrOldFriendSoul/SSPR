package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.ITCompany.model.Project;
import ru.ulstu.is.sbapp.ITCompany.service.ProjectService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
public class JpaProjectTests {
    private static final Logger log = LoggerFactory.getLogger(JpaProjectTests.class);

    @Autowired
    private ProjectService projectService;

    @Test
    void testProjectCreate() {
        projectService.deleteAllProjects();
        final Project project = projectService.addProject("Юридическая фирма", "описание юр.фирмы...", 1);
        log.info(project.toString());
        Assertions.assertNotNull(project.getId());
    }

    @Test
    void testProjectRead() {
        projectService.deleteAllProjects();
        final Project project = projectService.addProject("Юридическая фирма", "описание юр.фирмы...", 2);
        log.info(project.toString());
        final Project findProject = projectService.findProject(project.getId());
        log.info(findProject.toString());
        Assertions.assertEquals(project, findProject);
    }

    @Test
    void testProjectReadNotFound() {
        projectService.deleteAllProjects();
        Assertions.assertThrows(EntityNotFoundException.class, () -> projectService.findProject(-1L));
    }

    @Test
    void testProjectReadAll() {
        projectService.deleteAllProjects();
        projectService.addProject("Юридическая фирма", "описание юр.фирмы...", 1);
        projectService.addProject("Строительная фирма", "описание строительной фирмы...",1);
        final List<Project> projects = projectService.findAllProjects();
        log.info(projects.toString());
        Assertions.assertEquals(projects.size(), 2);
    }

    @Test
    void testProjectReadAllEmpty() {
        projectService.deleteAllProjects();
        final List<Project> projects = projectService.findAllProjects();
        log.info(projects.toString());
        Assertions.assertEquals(projects.size(), 0);
    }
}
