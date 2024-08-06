package com.fleet.managament.uac.service;

import com.fleet.managament.security.entity.User;
import com.fleet.managament.security.repository.UserRepository;
import com.fleet.managament.security.service.JwtService;
import com.fleet.managament.uac.dtos.*;
import com.fleet.managament.utils.CustomAppRepository;
import com.fleet.managament.utils.RestResponse;
import com.fleet.managament.utils.SearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService implements UacInterface {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomAppRepository <User> userCustomAppRepository;

    @Override
    public RestResponse createUser(SignUpRequest request) {
        return null;
    }

    @Override
    public JwtAuthenticationResponse signIn(SigninRequest request) {
        return null;
    }

    @Override
    public RestResponse fetchUser(SearchDto searchDto) {
        return null;
    }

    @Override
    public RestResponse changePassword(ChangeStatusRequest changeStatusRequest) {
        return null;
    }

    @Override
    public RestResponse fetchPaginatedUserList(SearchDto searchDto, Pageable pageable) {
        return null;
    }

    @Override
    public RestResponse changeUserStatus(ChangeStatusRequest changeStatusRequest) {
        return null;
    }

    @Override
    public RestResponse changeUserRole(ChangeRoleRequest changeRoleRequest) {
        return null;
    }

    @Override
    public RestResponse updateUser(SignUpUpdateRequest request) {
        return null;
    }
}
