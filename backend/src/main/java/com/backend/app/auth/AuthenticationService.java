package com.backend.app.auth;

import com.backend.app.config.JwtService;
import com.backend.app.exception.ResourceNotFoundException;
import com.backend.app.model.Role;
import com.backend.app.model.User;
import com.backend.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private  final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .userName(request.getUserName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .role(request.getRole())
                .password(passwordEncoder.encode(request.getPassword()))

                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user) ;
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
       try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );}catch (AuthenticationException authenticationException) {
           return null;
       };
        var user = userRepository.findById(request.getUserName()).orElseThrow(()-> new ResourceNotFoundException("User","username",request.getUserName()));
        var jwtToken = jwtService.generateToken(user) ;
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
}
