package com.maxvpire.appointments.roomtype;

import com.maxvpire.appointments.roomtype.dto.RoomtypeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment/room/type")
@RequiredArgsConstructor
public class RoomtypeController {
    private final RoomtypeService roomtypeService;

    @PostMapping
    public ResponseEntity<String> createRoomtype(@RequestBody RoomtypeRequest request){
        return ResponseEntity.ok(roomtypeService.createRoomtype(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Roomtype>> getAllRoomtypes(){
        return ResponseEntity.ok(roomtypeService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Roomtype> getRoomtype(@PathVariable String id){
        return ResponseEntity.ok(roomtypeService.findById(id));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Roomtype> getRoomtypes(@PathVariable String type){
        return ResponseEntity.ok(roomtypeService.findByType(type));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateRoomtype(
            @PathVariable String id,
            @RequestBody RoomtypeRequest request
    ){
        this.roomtypeService.update(id, request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteRoomtype(@PathVariable String id){
        this.roomtypeService.deleteRoomType(id);
        return ResponseEntity.accepted().build();
    }
}
