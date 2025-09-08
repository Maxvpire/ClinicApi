package com.maxvpire.appointments.room;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
    Optional<Room> findRoomByRoomNumber(Integer roomNumber);
    List<Room> findRoomsByAvailable(boolean available);

}
