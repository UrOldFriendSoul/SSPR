package ru.ulstu.is.sbapp.ITCompany.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String positionName;
    private String positionDescription;


    public Position() {
    }

    public Position(String positionName, String positionDescription) {
        this.positionName = positionName;
        this.positionDescription = positionDescription;
    }

    public Long getId() {
        return id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(id, position.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", positionName='" + positionName + '\'' +
                ", positionDescription='" + positionDescription + '\'' +
                '}';
    }
}
