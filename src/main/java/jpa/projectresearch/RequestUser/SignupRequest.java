package jpa.projectresearch.RequestUser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;


public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 50)
    private String fullName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private String phone;

    private String address;

    public @NotBlank @Size(min = 3, max = 50) String getUsername() {
        return fullName;
    }

    public void setUsername(@NotBlank @Size(min = 3, max = 50) String username) {
        this.fullName = username;
    }

    public @NotBlank @Size(max = 50) @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Size(max = 50) @Email String email) {
        this.email = email;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public @NotBlank @Size(min = 6, max = 40) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(min = 6, max = 40) String password) {
        this.password = password;
    }

    public SignupRequest(String fullName, String email, Set<String> role, String password, String phone, String address) {
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public @NotBlank @Size(min = 3, max = 50) String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank @Size(min = 3, max = 50) String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}