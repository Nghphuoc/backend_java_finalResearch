package jpa.projectresearch.Mapper;

import jpa.projectresearch.Dto.UserDto;
import jpa.projectresearch.Entity.User;

public class UserMapper {

    public static UserDto mapUser(User user){
        return new UserDto(
                user.getUserId(),
                user.getFullName(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getCart(),
                user.getOrders()
        );
    }

    public static User mapUserDto(UserDto userDto){
        return new User(
                userDto.getUserId(),
                userDto.getFullName(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getPhone(),
                userDto.getAddress(),
                userDto.getCart(),
                userDto.getOrders()
        );
    }
}
