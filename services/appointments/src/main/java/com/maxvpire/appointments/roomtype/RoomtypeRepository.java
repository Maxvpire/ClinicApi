package com.maxvpire.appointments.roomtype;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomtypeRepository extends MongoRepository<Roomtype, String> {
    Optional<Roomtype> findRoomtypeByType(String type);
}
