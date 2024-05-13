package com.g1.curso.controllers;

import com.g1.curso.dtos.UserDTO;
import com.g1.curso.models.UserModel;
import com.g1.curso.repositories.UserRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("${spring.url}")

public class UserController {
    @Autowired
    UserRepository userRepository;
    @PostMapping("/user/create")
    public ResponseEntity<String> createUser(@RequestBody @NonNull UserDTO userDTO){
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);

        try{
            userRepository.save(userModel);
        }catch (Exception e){

            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok("User Created");
    }
    @PostMapping("/user/find")
    public ResponseEntity<?> findUser(@RequestBody UserDTO userDTO) {

        try {
            Optional<UserModel> userOpitional = userRepository.findByEmailAndPassword(userDTO.email(), userDTO.password());

            if(userOpitional.isPresent()){
                UserModel user = userOpitional.get();
                return ResponseEntity.ok(user);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();

    }

}
