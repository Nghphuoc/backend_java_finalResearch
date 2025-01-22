package jpa.projectresearch.API;

import jpa.projectresearch.Dto.CartDto;
import jpa.projectresearch.Dto.UserDto;
import jpa.projectresearch.Mapper.CartMapper;

import jpa.projectresearch.Service.CartService;
import jpa.projectresearch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("Id") Long id){
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        userService.CreateUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("Id") Long Id
                                              ,@RequestBody UserDto user){
        UserDto userDto = userService.UpdateUser(Id,user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteUser(@PathVariable("Id") Long Id){
         userService.DeleteUser(Id);
         return new ResponseEntity<>("User deleted successfully !", HttpStatus.OK);
    }
}
