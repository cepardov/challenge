package com.cepardov.challenge.controller;

import com.cepardov.challenge.dto.UserDTO;
import com.cepardov.challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cepardov on 01-08-20
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> list(){
        List<UserDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Long id){
        UserDTO user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody UserDTO userDTO){
        UserDTO userSaved = userService.save(userDTO);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody UserDTO userDTO){
        UserDTO userUpdated = userService.update(userDTO);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody UserDTO userDTO){
        userService.delete(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
