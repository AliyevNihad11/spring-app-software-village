package az.softwarevillage.book.controller;

import az.softwarevillage.book.dto.request.UserRequest;
import az.softwarevillage.book.dto.response.BasaResponse;
import az.softwarevillage.book.dto.response.UserResponse;
import az.softwarevillage.book.service.UserService;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Serializers;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "users")
@Getter
@Setter

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public BasaResponse registerUser(@RequestBody UserRequest userRequest){
       return  userService.registerUser(userRequest);
    }

    @GetMapping()
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable ("id") Long id){
        return userService.getUserById(id);
    }
    @PutMapping("{id}")
    public BasaResponse updateUser(@PathVariable("id") Long id,@RequestBody UserRequest userRequest){
        return userService.updateUser(id,userRequest);
    }
}
