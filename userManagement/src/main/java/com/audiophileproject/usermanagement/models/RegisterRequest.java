package com.audiophileproject.usermanagement.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private  String firstname;
    private  String lastname;
    private  String email;
    private  String password;

}
