package jpa.projectresearch.Service;

import jpa.projectresearch.Dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    List<UserDto> getAllUsers();

    UserDto CreateUser(UserDto userDto);

    UserDto UpdateUser(Long id,UserDto userDto);

    void DeleteUser(Long theId);

    UserDto getUserById(Long theId);

    UserDto getUserByEmail(String theEmail);
}
