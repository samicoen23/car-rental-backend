package com.classboxesproject.services.auth;

import com.classboxesproject.dto.SignupRequest;
import com.classboxesproject.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}
