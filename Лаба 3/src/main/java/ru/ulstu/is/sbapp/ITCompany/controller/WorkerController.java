package ru.ulstu.is.sbapp.ITCompany.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.ITCompany.model.Worker;
import ru.ulstu.is.sbapp.ITCompany.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/Worker")
public class WorkerController {
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/{id}")
    public Worker getWorker(@PathVariable Long id) {
        return workerService.findWorker(id);
    }

    @GetMapping("/")
    public List<Worker> getWorkers() {
        return workerService.findAllWorkers();
    }

    @PostMapping("/")
    public Worker createWorker(@RequestParam("workerName") String workerName,
                                   @RequestParam("workerDescription") String workerDescription) {
        return workerService.addWorker(workerName, workerDescription);
    }

    @PatchMapping("/{id}")
    public Worker updateWorker(@PathVariable Long id,
                                   @RequestParam("workerName") String workerName,
                                   @RequestParam("workerDescription") String workerDescription) {
        return workerService.updateWorker(id, workerName, workerDescription);
    }

    @DeleteMapping("/{id}")
    public Worker deleteWorker(@PathVariable Long id) {
        return workerService.deleteWorker(id);
    }
}