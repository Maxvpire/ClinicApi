package com.maxvpire.appointments.roomtype.dto;

import jakarta.validation.constraints.NotNull;

public record RoomtypeRequest(
        @NotNull(message = "Room type is mandatory!")
        String type,
        String description
) {
}
