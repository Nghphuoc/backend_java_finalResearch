package jpa.projectresearch.API;

import jakarta.validation.Valid;
import jpa.projectresearch.Config.JwtUtils;
import jpa.projectresearch.Dto.UserDto;
import jpa.projectresearch.Entity.Role;
import jpa.projectresearch.RequestUser.LoginRequest;
import jpa.projectresearch.RequestUser.LoginResponse;
import jpa.projectresearch.RequestUser.SignupRequest;
import jpa.projectresearch.Responsesitory.RoleRepository;
import jpa.projectresearch.Responsesitory.UserRepository;
import jpa.projectresearch.Service.UserService;
import jpa.projectresearch.Variable.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private  JwtUtils jwtUtils;

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  RoleRepository roleRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private  UserService userService;


    @PostMapping("/public/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Bad credentials");
            errorResponse.put("status", false);
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
        // Đặt authentication vào SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kiểm tra authentication principal có phải là UserDetails không
        if (!(authentication.getPrincipal() instanceof UserDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Tạo JWT token
        String jwtToken = jwtUtils.generateTokenFromUsername(loginRequest);

        // Lấy danh sách quyền (roles)
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Tạo response
        LoginResponse response = new LoginResponse(loginRequest.getEmail(), roles, jwtToken);

        return ResponseEntity.ok(response);
    }

    // custom user because so many field null
    @PostMapping("/public/signup")
    public ResponseEntity<?> registerUsers(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByFullName(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // Create new user's account
        UserDto user = new UserDto(signUpRequest.getUsername(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail(),
                signUpRequest.getPhone(),
                signUpRequest.getAddress());

        Set<String> strRoles = signUpRequest.getRole();
        Role roles = null;

        if (strRoles == null || strRoles.isEmpty()) {
            roles = (roleRepository.findByRoleName(Variable.AppRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        } else {
            for (String roleStr : strRoles) {
                if (roleStr.equalsIgnoreCase("admin")) {
                    roles = (roleRepository.findByRoleName(Variable.AppRole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
                } else {
                    roles = (roleRepository.findByRoleName(Variable.AppRole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
                }
            }
        }
        user.setRole(roles);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setTwoFactorEnabled(false);
        user.setSignUpMethod("email");
        userService.CreateUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}
