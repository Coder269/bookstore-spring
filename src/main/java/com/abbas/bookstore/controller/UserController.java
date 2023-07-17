package com.abbas.bookstore.controller;

import com.abbas.bookstore.model.User;
import com.abbas.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/admin/update-email/{id}")
    public ResponseEntity<?> updateEmail(@PathVariable("id") Long id, @RequestBody String email){

        userService.updateUserEmail(id, email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin/update-firstname/{id}")
    public ResponseEntity<?> updateFirstname(@PathVariable("id") Long id, @RequestBody String firstname){

        userService.updateUserFirstname(id, firstname);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin/update-lastname/{id}")
    public ResponseEntity<?> updateLastname(@PathVariable("id") Long id, @RequestBody String lastname){

       userService.updateUserLastname(id, lastname);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/admin/user-delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
       userService.deleteUserById(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin/make-admin/{email}")
    public ResponseEntity<?> makeAdmin(@PathVariable String email){
        userService.makeAdmin(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/all-users")
    public ResponseEntity<?> findAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user-info/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable("id") Long id) { return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK); }
}
