package com.maxvpire.appointments.room.dto;

import jakarta.validation.constraints.NotNull;

public record RoomRequest(
        @NotNull(message = "Room number is mandatory!")
        Integer roomNumber,
        @NotNull(message = "Room type is mandatory!")
        String roomtypeId
) {
}
