package org.helper.course.model;

public enum Goal {
    GOAL3_LAB,
    GOAL3_NO_LAB,
    GOAL5,
    GOAL6_FINE_ARTS,
    GOAL6_HUMANITIES,
    GOAL9,
    GOAL10;

    @Override
    public String toString() {
        return name().replace("_", " ");
    }
}
