package ru.ulstu.is.sbapp.ITCompany.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.sbapp.ITCompany.model.Position;
import ru.ulstu.is.sbapp.ITCompany.service.PositionService;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/{id}")
    public Position getPosition(@PathVariable Long id) {
        return positionService.findPosition(id);
    }

    @GetMapping("/")
    public List<Position> getPositions() {
        return positionService.findAllPositions();
    }

    @PostMapping("/")
    public Position createPosition(@RequestParam("firstName") String firstName,
                                  @RequestParam("lastName") String lastName) {
        return positionService.addPosition(firstName, lastName);
    }

    @PatchMapping("/{id}")
    public Position updatePosition(@PathVariable Long id,
                                  @RequestParam("firstName") String firstName,
                                  @RequestParam("lastName") String lastName) {
        return positionService.updatePosition(id, firstName, lastName);
    }

    @DeleteMapping("/{id}")
    public Position deletePosition(@PathVariable Long id) {
        return positionService.deletePosition(id);
    }
}