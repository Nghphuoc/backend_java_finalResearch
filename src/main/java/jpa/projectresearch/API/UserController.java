package jpa.projectresearch.API;

import jpa.projectresearch.Dto.UpdateUser;
import jpa.projectresearch.Dto.UserDto;
import jpa.projectresearch.Dto.UserUpdate;
import jpa.projectresearch.RequestUser.LoginRequest;
import jpa.projectresearch.Service.CartService;
import jpa.projectresearch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
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

    @GetMapping("/personal/{Id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("Id") Long id){
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        userService.CreateUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/personal/{Id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("Id") Long Id
                                              ,@RequestBody UpdateUser user){
        UserDto userDto = userService.UpdateUser(Id,user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteUser(@PathVariable("Id") Long Id){
         userService.DeleteUser(Id);
         return new ResponseEntity<>("User deleted successfully !", HttpStatus.OK);
    }

    @PostMapping("/info")
    public ResponseEntity<UserDto> getAllUsersInfo(@RequestBody LoginRequest loginRequest){
        UserDto user = userService.getUserByEmail(loginRequest.getEmail());
        return  ResponseEntity.ok(user);
    }

    @PutMapping("/update/info-user")
    public ResponseEntity<?> updateUserInfo(@RequestBody UserUpdate userUpdate){
        userService.updateUser(userUpdate.getEmail(),userUpdate.getPhone(),(userUpdate.getPassword()));
        return new ResponseEntity<>("User updated successfully !", HttpStatus.OK);
    }
}
