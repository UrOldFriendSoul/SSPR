package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.ITCompany.model.Position;
import ru.ulstu.is.sbapp.ITCompany.service.PositionService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
public class JpaPositionTests {
    private static final Logger log = LoggerFactory.getLogger(JpaPositionTests.class);

    @Autowired
    private PositionService positionService;

    @Test
    void testPositionCreate() {
        positionService.deleteAllPositions();
        final Position position = positionService.addPosition("Менеджер", "менеджер проектов...");
        log.info(position.toString());
        Assertions.assertNotNull(position.getId());
    }

    @Test
    void testPositionRead() {
        positionService.deleteAllPositions();
        final Position position = positionService.addPosition("Менеджер", "менеджер проектов...");
        log.info(position.toString());
        final Position findPosition = positionService.findPosition(position.getId());
        log.info(findPosition.toString());
        Assertions.assertEquals(position, findPosition);
    }

    @Test
    void testPositionReadNotFound() {
        positionService.deleteAllPositions();
        Assertions.assertThrows(EntityNotFoundException.class, () -> positionService.findPosition(-1L));
    }

    @Test
    void testPositionReadAll() {
        positionService.deleteAllPositions();
        positionService.addPosition("Менеджер", "менеджер проектов...");
        positionService.addPosition("Второй менеджер", "второй менеджер проектов...");
        final List<Position> positions = positionService.findAllPositions();
        log.info(positions.toString());
        Assertions.assertEquals(positions.size(), 2);
    }

    @Test
    void testPositionReadAllEmpty() {
        positionService.deleteAllPositions();
        final List<Position> positions = positionService.findAllPositions();
        log.info(positions.toString());
        Assertions.assertEquals(positions.size(), 0);
    }
}
