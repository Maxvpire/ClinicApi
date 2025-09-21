package com.maxvpire.administration.administration.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdministrationCreatedEvent implements BaseEvent{
    private String id;
    private String phone;

    public String getEventType() {
        return "ADMINISTRATION_CREATED";
    }
}
