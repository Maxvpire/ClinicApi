package com.maxvpire.appointments.roomtype;

import com.maxvpire.appointments.exception.RoomtypeNotFoundException;
import com.maxvpire.appointments.roomtype.dto.RoomtypeRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomtypeService {
    private final RoomtypeRepository roomtypeRepository;

    public String createRoomtype(RoomtypeRequest request){
        Roomtype roomtype = Roomtype.builder()
                .type(request.type())
                .description(request.description())
                .build();
        return roomtypeRepository.save(roomtype).getId();
    }

    public List<Roomtype> findAll() {
        return roomtypeRepository.findAll();
    }

    public Roomtype findById(String id) {
        return roomtypeRepository.findById(id)
                .orElseThrow(() -> new RoomtypeNotFoundException("Room type not found!"));
    }


    public Roomtype findByType(String type) {
        return roomtypeRepository.findRoomtypeByType(type)
                .orElseThrow(() -> new RoomtypeNotFoundException("Room type not found!"));
    }

    public void update(String id, RoomtypeRequest request) {
        Roomtype roomtype =  roomtypeRepository.findById(id)
                .orElseThrow(() -> new RoomtypeNotFoundException("Room type not found!"));
        mergeRoomType(roomtype, request);
        roomtypeRepository.save(roomtype);
    }

    private void mergeRoomType(Roomtype roomtype, RoomtypeRequest roomtypeRequest) {
        if(StringUtils.isNotBlank((roomtypeRequest.type()))){
            roomtype.setType(roomtypeRequest.type());
        }
        if(StringUtils.isNotBlank((roomtypeRequest.description()))){
            roomtype.setDescription(roomtypeRequest.description());
        }
    }

    public void deleteRoomType(String id) {
        Roomtype roomtype = roomtypeRepository.findById(id)
                        .orElseThrow(() -> new RoomtypeNotFoundException("Room type not found!"));
        roomtypeRepository.delete(roomtype);
    }
}
