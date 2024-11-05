package com.emailauthenticationserviceapi.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "users")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotBlank(message = "User email cannot be blank")
    @NotNull(message = "User email is required")
    @Schema(description = "Receives the email of the user")
    @Email
    private String email;

    @NotBlank(message = "User password cannot be blank")
    @NotNull(message = "User password is required")
    @Schema(description = "Receives the password of the user")
    private String password;

    @NotBlank(message = "User first name cannot be blank")
    @NotNull(message = "User first name is required")
    @Schema(description = "Receives the email of the user")
    private String firstName;

    @NotBlank(message = "User last name cannot be blank")
    @NotNull(message = "User last name is required")
    @Schema(description = "Receives the last name of the user")
    private String lastName;

    @NotBlank(message = "User role cannot be blank")
    @NotNull(message = "User role is required")
    @Schema(description = "Receives the role of the user")
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {

        return email;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
