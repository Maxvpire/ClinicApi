package com.maxvpire.appointments.room;

import com.maxvpire.appointments.room.dto.RoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody RoomRequest request) {
        return ResponseEntity.ok(roomService.create(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> findAll() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Room> findById(@PathVariable String id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<Room> findByNumber(@PathVariable Integer number) {
        return ResponseEntity.ok(roomService.findByRoomNumber(number));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Room>> findByAvailable() {
        return ResponseEntity.ok(roomService.getActiveRoomsOnly());
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<Room>> findByInactive() {
        return ResponseEntity.ok(roomService.getInActiveRoomsOnly());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody RoomRequest request) {
        this.roomService.update(id, request);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<String> updateActive(@PathVariable String id) {
        this.roomService.activate(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/inactive/{id}")
    public ResponseEntity<String> updateInactive(@PathVariable String id) {
        this.roomService.inActivate(id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        this.roomService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
