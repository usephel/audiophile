package com.audiophileproject.usermanagement.services;

import com.audiophileproject.usermanagement.models.*;
import com.audiophileproject.usermanagement.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    // TODO: validate the data before saving it
    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ERole.ROLE_USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .authToken(jwtToken)
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }

    /**
     * authenticate the user by his email and password
     * @param request
     * @return
     * @apiNote we authenticate by email and not username
     */
    public AuthenticationResponse authenticate(AuthenticatoinRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .authToken(jwtToken)
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }

}
