package com.emailauthenticationserviceapi.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank
    @NotNull
    @Schema
    private String name;

    @NotBlank
    @NotNull
    @Schema
    private String email;

    @NotBlank
    @NotNull
    @Schema
    private String password;
    
}
