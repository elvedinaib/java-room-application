package com.example.demo.controller;

import com.example.demo.Model.RoomUser;
import com.example.demo.Service.RoomUserService;
import com.example.demo.dto.RoomUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/room-user")
public class RoomUserController {
    @Autowired
    RoomUserService roomUserService;

    @GetMapping()
    public List<RoomUser> getAllRoomUsers(){
        return roomUserService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) throws NoSuchElementException{
        try {
            return new ResponseEntity<>(roomUserService.findById(id), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Room user with requested id=" + id + " does not exist in our database.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-new")
    public ResponseEntity<?> addNewRoomUser(@RequestBody RoomUserDTO dto) throws NoSuchElementException{
        try {
            return new ResponseEntity<>(roomUserService.addNewRoomUser(dto), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Room with id=" + dto.getRoomId() + " does not exist in our database.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatedById(@PathVariable("id") Long id, @RequestBody RoomUserDTO dto) throws NoSuchElementException{
        try {
            return new ResponseEntity<>(roomUserService.updateById(id, dto), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Room user or room with requested id does not exist in our database so room user cannot be updated.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws NoSuchElementException{
        try {
            return new ResponseEntity<>(roomUserService.deleteById(id), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Room user with requested id=" + id + " does not exist in our database so it cannot be deleted.", HttpStatus.NOT_FOUND);
        }
    }
}
