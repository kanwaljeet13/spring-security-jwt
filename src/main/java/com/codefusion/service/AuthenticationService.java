package com.codefusion.service;

import com.codefusion.model.dto.request.UserCredentialRequestDto;
import com.codefusion.model.dto.response.UserAuthenticationResponse;

public interface AuthenticationService {

    UserAuthenticationResponse login(UserCredentialRequestDto userCredentialRequestDto);
}
