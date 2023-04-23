package com.codefusion.service.impl;

import com.codefusion.jwt.service.JwtService;
import com.codefusion.model.dto.request.UserCredentialRequestDto;
import com.codefusion.model.dto.response.UserAuthenticationResponse;
import com.codefusion.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserAuthenticationResponse login(UserCredentialRequestDto userCredentialRequestDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentialRequestDto.getUsername(), userCredentialRequestDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }
        if(!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("invalid user request !");
        }
        String token = jwtService.generateToken(userCredentialRequestDto.getUsername());
        return new UserAuthenticationResponse(token);
    }
}
