package ru.ulstu.is.sbapp.ITCompany.service;

import  org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.ITCompany.model.Position;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PositionService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Position addPosition(String positionName, String positionDescription) {
        if (!StringUtils.hasText(positionName) || !StringUtils.hasText(positionDescription)) {
            throw new IllegalArgumentException("Position name is null or empty");
        }
        final Position position = new Position(positionName, positionDescription);
        em.persist(position);
        return position;
    }

    @Transactional(readOnly = true)
    public Position findPosition(Long id) {
        final Position position = em.find(Position.class, id);
        if (position == null) {
            throw new EntityNotFoundException(String.format("Position with id [%s] is not found", id));
        }
        return position;
    }

    @Transactional(readOnly = true)
    public List<Position> findAllPositions() {
        return em.createQuery("select s from Position s", Position.class).getResultList();
    }

    @Transactional
    public Position updatePosition(Long id, String positionName, String positionDescription) {
        if (!StringUtils.hasText(positionName) || !StringUtils.hasText(positionDescription)) {
            throw new IllegalArgumentException("Position's name is null or empty");
        }
        final Position currentPosition = findPosition(id);
        currentPosition.setPositionName(positionName);
        currentPosition.setPositionDescription(positionDescription);
        return em.merge(currentPosition);
    }

    @Transactional
    public Position deletePosition(Long id) {
        final Position currentPosition = findPosition(id);
        em.remove(currentPosition);
        return currentPosition;
    }

    @Transactional
    public void deleteAllPositions() {
        em.createQuery("delete from Position").executeUpdate();
    }
}
