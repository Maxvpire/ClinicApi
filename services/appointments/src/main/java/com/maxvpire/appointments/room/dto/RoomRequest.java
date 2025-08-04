package com.maxvpire.appointments.room.dto;

import com.maxvpire.appointments.roomtype.Roomtype;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Valid
public record RoomRequest(
        @NotNull
        Integer roomNumber,
        @NotNull
        String roomtypeId
) {
}
