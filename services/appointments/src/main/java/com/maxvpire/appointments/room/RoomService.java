package com.maxvpire.appointments.room;

import com.maxvpire.appointments.exception.RoomNotFoundException;
import com.maxvpire.appointments.room.dto.RoomRequest;
import com.maxvpire.appointments.roomtype.Roomtype;
import com.maxvpire.appointments.roomtype.RoomtypeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomtypeRepository roomtypeRepository;

    public String create(RoomRequest request) {
        Roomtype roomtype = roomtypeRepository.findById(request.roomtypeId())
                .orElseThrow(() -> new RoomNotFoundException("Room Type Not Found!"));
        Room room = Room.builder()
                .roomNumber(request.roomNumber())
                .roomtypeId(roomtype.getId())
                .build();
        return roomRepository.save(room).getId();
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findById(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found!"));
    }

    public Room findByRoomNumber(Integer roomNumber) {
        return roomRepository.findRoomByRoomNumber(roomNumber)
                .orElseThrow(() -> new RoomNotFoundException("Room not found!"));
    }

    public List<Room> getActiveRoomsOnly() {
        return roomRepository.findRoomsByAvailable(true);
    }

    public List<Room> getInActiveRoomsOnly() {
        return roomRepository.findRoomsByAvailable(false);
    }


    public void update(String id, RoomRequest request) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found!"));
        mergeRoom(room, request);
        roomRepository.save(room);
    }

    private void mergeRoom(Room room, RoomRequest roomRequest) {
        if(roomRequest.roomNumber() != null){
            room.setRoomNumber(roomRequest.roomNumber());
        }
        if(StringUtils.isNotBlank((roomRequest.roomtypeId()))){
            room.setRoomtypeId(roomRequest.roomtypeId());
        }
    }

    public void inActivate(String id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found!"));
        room.setAvailable(false);
        roomRepository.save(room);
    }

    public void activate(String id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found!"));
        room.setAvailable(true);
        roomRepository.save(room);
    }

    public void deleteById(String id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found!"));
        roomRepository.delete(room);
    }
}
