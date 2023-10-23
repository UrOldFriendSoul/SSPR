package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.ITCompany.model.Worker;
import ru.ulstu.is.sbapp.ITCompany.model.Project;
import ru.ulstu.is.sbapp.ITCompany.model.Position;
import ru.ulstu.is.sbapp.ITCompany.service.WorkerService;
import ru.ulstu.is.sbapp.ITCompany.service.ProjectService;
import ru.ulstu.is.sbapp.ITCompany.service.PositionService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
public class JpaWorkerTests {
    private static final Logger log = LoggerFactory.getLogger(JpaWorkerTests.class);

    @Autowired
    private WorkerService workerService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private ProjectService projectService;

    @Test
    void testWorkerCreate() {
        workerService.deleteAllWorkers();
        final Worker worker = workerService.addWorker("Тимофей", "Тимофеевич");
        Position position = positionService.addPosition("Менеджер", "менеджер проектов...");
        workerService.addPosition(worker, position);
        log.info(worker.toString());
        Assertions.assertNotNull(worker.getId());
    }

    @Test
    void testWorkerRead() {
        workerService.deleteAllWorkers();
        final Worker worker = workerService.addWorker("Иван", "Петров");
        Position position = positionService.addPosition("Менеджер", "менеджер проектов...");
        workerService.addPosition(worker, position);
        log.info(worker.toString());
        final Worker findWorker = workerService.findWorker(worker.getId());
        log.info(findWorker.toString());
        Assertions.assertEquals(worker, findWorker);
    }

    @Test
    void testWorkerReadNotFound() {
        workerService.deleteAllWorkers();
        Assertions.assertThrows(EntityNotFoundException.class, () -> workerService.findWorker(-1L));
    }

    @Test
    void testWorkerReadAll() {
        workerService.deleteAllWorkers();
        workerService.addWorker("Тимофей", "Тимофеевич");
        workerService.addWorker("Петр", "Петров");
        final List<Worker> workers = workerService.findAllWorkers();
        log.info(workers.toString());
        Assertions.assertEquals(workers.size(), 2);
    }

    @Test
    void testWorkerReadAllEmpty() {
        workerService.deleteAllWorkers();
        final List<Worker> workers = workerService.findAllWorkers();
        log.info(workers.toString());
        Assertions.assertEquals(workers.size(), 0);
    }

    @Test
    void testWorkerAddProjects() {
        workerService.deleteAllWorkers();
        projectService.deleteAllProjects();
        final Project project = projectService.addProject("Юридическая фирма","описание юр.фирмы", 3);
        final Worker worker = workerService.addWorker("Тимофей", "Тимофеевич");
        projectService.addWorkerToProject(project, worker);
        Assertions.assertNotEquals(project.getWorkers(), worker.getProjects());
    }

    @Test
    void testWorkerAddPosition() {
        Position position = positionService.addPosition("Менеджер", "менеджер проектов...");
        workerService.deleteAllWorkers();
        final Worker worker = workerService.addWorker("Тимофей", "Тимофеевич");
        workerService.addPosition(worker, position);
        log.info(worker.toString());
        Assertions.assertNotEquals(worker.getWorkerPosition(), worker.getProjects());
    }

}
