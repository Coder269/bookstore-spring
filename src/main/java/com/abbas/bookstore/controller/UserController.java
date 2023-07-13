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

    @PutMapping("/admin/user-update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User existUser = userService.findUserByEmail(user.getEmail());
        if (existUser != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
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
}
