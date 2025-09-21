package com.maxvpire.administration.administration.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdministrationDeletedEvent implements BaseEvent{
    private String id;

    public String getEventType() {
        return "ADMINISTRATION_DELETED";
    }
}
