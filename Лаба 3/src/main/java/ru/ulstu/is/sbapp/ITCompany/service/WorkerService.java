package ru.ulstu.is.sbapp.ITCompany.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.ITCompany.model.Worker;
import ru.ulstu.is.sbapp.ITCompany.model.Project;
import ru.ulstu.is.sbapp.ITCompany.model.Position;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class WorkerService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Worker addWorker(String workerName, String workerSurname) {
        if (!StringUtils.hasText(workerName) || !StringUtils.hasText(workerSurname)) {
            throw new IllegalArgumentException("Worker name is null or empty");
        }
        final Worker worker = new Worker(workerName, workerSurname);
        em.persist(worker);
        return worker;
    }

    @Transactional(readOnly = true)
    public Worker findWorker(Long id) {
        final Worker worker = em.find(Worker.class, id);
        if (worker == null) {
            throw new EntityNotFoundException(String.format("Worker with id [%s] is not found", id));
        }
        return worker;
    }

    @Transactional(readOnly = true)
    public List<Worker> findAllWorkers() {
        return em.createQuery("select s from Worker s", Worker.class).getResultList();
    }

    @Transactional
    public Worker updateWorker(Long id, String workerName, String workerSurname) {
        if (!StringUtils.hasText(workerName) || !StringUtils.hasText(workerSurname)) {
            throw new IllegalArgumentException("Worker name is null or empty");
        }
        final Worker currentWorker = findWorker(id);
        currentWorker.setWorkerName(workerName);
        currentWorker.setWorkerSurname(workerSurname);
        return em.merge(currentWorker);
    }

    @Transactional
    public Worker deleteWorker(Long id) {
        final Worker currentWorker = findWorker(id);
        em.remove(currentWorker);
        return currentWorker;
    }

    @Transactional
    public void deleteAllWorkers() {
        em.createQuery("delete from Worker").executeUpdate();
    }

    @Transactional
    public void addProjectToWorkers(Worker worker, Project project) {
        if (project.getId().toString().isEmpty()) {
            throw new IllegalArgumentException("Project is null or empty");
        }
        worker.addProject(project);
        em.merge(worker);
    }

    @Transactional
    public Worker addPosition(Worker worker, Position position) {
        if (position.getId().toString().isEmpty()) {
            throw new IllegalArgumentException("Position's id is null or empty");
        }
        worker.setPosition(position);
        return em.merge(worker);
    }
}
