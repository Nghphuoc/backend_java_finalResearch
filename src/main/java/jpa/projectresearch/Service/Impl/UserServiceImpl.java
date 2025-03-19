package jpa.projectresearch.Service.Impl;

import jpa.projectresearch.Dto.UpdateUser;
import jpa.projectresearch.Dto.UserDto;
import jpa.projectresearch.Entity.Cart;
import jpa.projectresearch.Entity.User;
import jpa.projectresearch.Mapper.UserMapper;
import jpa.projectresearch.Responsesitory.CartRepository;
import jpa.projectresearch.Responsesitory.UserRepository;
import jpa.projectresearch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        user.setRole(userDto.getRole());
        user.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
        user.setAccountExpiryDate(LocalDate.now().plusYears(1));
        user.setSignUpMethod("email");
        user.setTwoFactorSecret(userDto.getRole().getRoleName().toString());
        Cart cart = new Cart(user.getFullName(),0D);
        user.setCart(cart);
        User savedUser = userRepository.save(user);
        return UserMapper.mapUser(savedUser);
    }

    @Override
    @Transactional
    public UserDto UpdateUser(Long id, UpdateUser userDto){
        User userUpdate = userRepository.findById(id).orElseThrow(()->new RuntimeException("cannot find by id: "+id));
        userUpdate.setFullName(userDto.getFullName());
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

    @Override
    public User loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException ("User Not Found with username: " + username));
        return user;
    }

    @Override
    public void updateUser(String email, String phone, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("cannot find by email: "+email));
        if(user.getPhone().equals(phone)){
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        }

    }
}
