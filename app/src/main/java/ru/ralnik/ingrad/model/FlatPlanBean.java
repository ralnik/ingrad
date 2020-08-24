package ru.ralnik.ingrad.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlatPlanBean {
    private String id;
    private int square;
    private Float realSquare;
    private int countRooms;
    private int countFlats;
}
