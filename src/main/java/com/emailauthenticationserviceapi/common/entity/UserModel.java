package com.emailauthenticationserviceapi.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotBlank(message = "User email cannot be blank")
    @NotNull(message = "User email is required")
    @Schema(description = "Receives the email of the user")
    @Email
    private String email;

    @NotBlank(message = "User email cannot be blank")
    @NotNull(message = "User email is required")
    @Schema(description = "Receives the email of the user")
    private String password;

    @NotBlank(message = "User email cannot be blank")
    @NotNull(message = "User email is required")
    @Schema(description = "Receives the email of the user")
    private String firstName;

    @NotBlank(message = "User email cannot be blank")
    @NotNull(message = "User email is required")
    @Schema(description = "Receives the email of the user")
    private String lastName;

    @NotBlank(message = "User email cannot be blank")
    @NotNull(message = "User email is required")
    @Schema(description = "Receives the email of the user")
    private String role;

}
