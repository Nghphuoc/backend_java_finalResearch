package jpa.projectresearch.Service.Impl;

import jpa.projectresearch.Dto.UserDto;
import jpa.projectresearch.Entity.Cart;
import jpa.projectresearch.Entity.Product;
import jpa.projectresearch.Entity.User;
import jpa.projectresearch.Mapper.UserMapper;
import jpa.projectresearch.Responsesitory.CartRepository;
import jpa.projectresearch.Responsesitory.UserRepository;
import jpa.projectresearch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapUser).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto CreateUser(UserDto userDto) {
        User user = UserMapper.mapUserDto(userDto);
        Cart cart = new Cart(user.getFullName(),0D);
        user.setCart(cart);
        User savedUser = userRepository.save(user);
        return UserMapper.mapUser(savedUser);
    }

    @Override
    @Transactional
    public UserDto UpdateUser(Long id,UserDto userDto) {
        User userUpdate = userRepository.findById(id).orElseThrow(()->new RuntimeException("cannot find by id: "+id));
        userUpdate.setFullName(userDto.getFullName());
        userUpdate.setEmail(userDto.getEmail());
        userUpdate.setPassword(userDto.getPassword());
        userUpdate.setPhone(userDto.getPhone());
        userUpdate.setAddress(userDto.getAddress());
        User savedUser = userRepository.save(userUpdate);
        return UserMapper.mapUser(savedUser);
    }

    @Override
    @Transactional
    public void DeleteUser(Long theId) {
        User user = userRepository.findById(theId).orElseThrow(()->new RuntimeException("cannot find by id: "+theId));
        userRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public UserDto getUserById(Long theId) {
        User user = userRepository.findById(theId).orElseThrow(()->new RuntimeException("cannot find by id: "+theId));
        return UserMapper.mapUser(user);
    }

    @Override
    @Transactional
    public UserDto getUserByEmail(String theEmail) {
        User user = userRepository.findByEmail(theEmail).orElseThrow(()->new RuntimeException("cannot find by email: "+theEmail));
        return UserMapper.mapUser(user);
    }
}
