package com.audiophileproject.usermanagement.controllers;


import com.audiophileproject.usermanagement.models.AuthenticationResponse;
import com.audiophileproject.usermanagement.models.AuthenticatoinRequest;
import com.audiophileproject.usermanagement.models.RegisterRequest;
import com.audiophileproject.usermanagement.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticatoinRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    // TODO complete this endpoint
    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response
    ){

        return ResponseEntity.ok(authService.refresh(request));
    }
}
