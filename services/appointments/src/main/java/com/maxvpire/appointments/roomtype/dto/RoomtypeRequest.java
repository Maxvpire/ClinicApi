package com.maxvpire.appointments.roomtype.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Valid
public record RoomtypeRequest(
        @NotNull
        String type,
        String description
) {
}
