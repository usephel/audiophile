package com.audiophileproject.usermanagement.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.AliasFor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatoinRequest {
    private String email;
    String password;
}
