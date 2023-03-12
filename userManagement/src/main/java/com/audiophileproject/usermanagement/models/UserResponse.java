package com.audiophileproject.usermanagement.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private  String firstname;
    private  String lastname;
    private  String email;

}
