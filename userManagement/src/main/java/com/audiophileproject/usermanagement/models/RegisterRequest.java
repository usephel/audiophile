package com.audiophileproject.usermanagement.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NonNull
    private  String firstname;
    @NonNull
    private  String lastname;
    @NonNull
    private  String username;
    @NonNull
    private  String email;
    @NonNull
    private  String password;

}
