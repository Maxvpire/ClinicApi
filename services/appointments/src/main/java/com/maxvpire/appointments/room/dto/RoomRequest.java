package com.maxvpire.appointments.room.dto;

import jakarta.validation.constraints.NotNull;

public record RoomRequest(
        @NotNull
        Integer roomNumber,
        @NotNull
        String roomtypeId
) {
}
