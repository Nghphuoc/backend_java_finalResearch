package jpa.projectresearch.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,jsr250Enabled = true,securedEnabled = true) // Authorize phan quyền  Authentication xác thực
public class Security {

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable); // Vô hiệu hóa CSRF

        http.authorizeHttpRequests(requests -> requests
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Cho phép OPTIONS
                // Các endpoint chung
                .requestMatchers(HttpMethod.POST, "/api/order/search").permitAll() // Cho phép truy cập công khai
                .requestMatchers(HttpMethod.GET, "/api/product/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/product/search").permitAll()
                .requestMatchers( "/api/product").permitAll()
                .requestMatchers("/api/auth/public/**").permitAll() // Cho phép truy cập công khai

                // Các endpoint cụ thể cho USER
                .requestMatchers(HttpMethod.GET, "/api/cart/**").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/api/cart/**").hasRole("USER") // add product on cart remove or update info
                .requestMatchers(HttpMethod.GET, "/api/category").hasAnyRole("USER","ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/order/**").hasAnyRole("USER","ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/order/**").hasAnyRole("USER","ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/user/personal/**").hasAnyRole("USER","ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/user/personal/**").hasRole("USER")

                // Các endpoint cụ thể cho ADMIN
                .requestMatchers("/api/order/**").hasRole("ADMIN")
                .requestMatchers("/api/cart/**").hasRole("ADMIN")
                .requestMatchers("/api/product/**").hasRole("ADMIN")
                .requestMatchers("/api/category/**").hasRole("ADMIN")
                .requestMatchers("/api/user/**").hasRole("ADMIN")



                // Bất kỳ request nào khác cần xác thực
                .anyRequest().authenticated()
        );

        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.httpBasic(withDefaults());

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() { // private clmm, am so spicy
        return new AuthTokenFilter();  //////////////////fix here new JwtUtils
    }

    @Bean
    public PasswordEncoder passwordEncoder() { // encoder by interface passwordEncoder
        return new BCryptPasswordEncoder();
    }
//
//    @Bean
//    public CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder ) {
//        return args -> {
//            Role userRole = roleRepository.findByRoleName(Variable.AppRole.ROLE_USER)
//                    .orElseGet(() -> roleRepository.save(new Role(Variable.AppRole.ROLE_USER)));
//
//            Role adminRole = roleRepository.findByRoleName(Variable.AppRole.ROLE_ADMIN)
//                    .orElseGet(() -> roleRepository.save(new Role(Variable.AppRole.ROLE_ADMIN)));
//
//            if (!userRepository.existsByEmail("user")) {
//                User user1 = new User("user", passwordEncoder.encode("user"), "user@example.com","0127861285618","abc");
//                user1.setAccountNonLocked(false);
//                user1.setAccountNonExpired(true);
//                user1.setCredentialsNonExpired(true);
//                user1.setEnabled(true);
//                user1.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
//                user1.setAccountExpiryDate(LocalDate.now().plusYears(1));
//                user1.setTwoFactorEnabled(false);
//                user1.setSignUpMethod("email");
//                user1.setRole(userRole);
//                userRepository.save(user1);
//            }
//
//            if (!userRepository.existsByEmail("admin")) {
//                User admin = new User("admin",  passwordEncoder.encode("admin"),"admin@example.com","01278612141","cba" );
//                admin.setAccountNonLocked(true);
//                admin.setAccountNonExpired(true);
//                admin.setCredentialsNonExpired(true);
//                admin.setEnabled(true);
//                admin.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
//                admin.setAccountExpiryDate(LocalDate.now().plusYears(1));
//                admin.setTwoFactorEnabled(false);
//                admin.setSignUpMethod("email");
//                admin.setRole(adminRole);
//                userRepository.save(admin);
//            }
//        };
//    }
}
