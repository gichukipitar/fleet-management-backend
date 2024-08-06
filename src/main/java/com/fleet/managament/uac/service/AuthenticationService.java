package com.fleet.managament.uac.service;

import com.fleet.managament.security.entity.User;
import com.fleet.managament.security.repository.UserRepository;
import com.fleet.managament.security.service.JwtService;
import com.fleet.managament.uac.dtos.*;
import com.fleet.managament.uac.mapper.UacMapper;
import com.fleet.managament.utils.CustomAppRepository;
import com.fleet.managament.utils.ErrorMessage;
import com.fleet.managament.utils.RestResponse;
import com.fleet.managament.utils.SearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fleet.managament.utils.*;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.fleet.managament.utils.UtilFunctions.validateNotNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService implements UacInterface {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomAppRepository<User> userCustomAppRepository;

    @Override
    public RestResponse createUser(SignUpRequest request) {
        User user = UacMapper.INSTANCE.signUpToUserMapper(request);
        List<ErrorMessage> validateObj = validateNotNull(request);
        if (ObjectUtils.isNotEmpty(validateObj)) {
            return new RestResponse(
                    RestResponseObject.builder().message("Invalid fields").errors(validateObj).build(),
                    HttpStatus.BAD_REQUEST);
        }
        // Validate specific user details
        List<ErrorMessage> errorMessages = validateUserDetails(request);
        if (ObjectUtils.isNotEmpty(validateObj)) {
            return new RestResponse(
                    RestResponseObject.builder()
                            .message("Invalid User Details!!")
                            .errors(errorMessages)
                            .build(),
                    HttpStatus.BAD_REQUEST);
        }

        //create or save user
        try {

            User saveUser =
                    user.toBuilder()
                            .createDate(Date.from(Instant.now()))
                            .modifiedDate(Date.from(Instant.now()))
                            .createBy(request.getUserName())
                            .modifiedBy(request.getUserName())
                            .password(passwordEncoder.encode(request.getPassword()))
                            .active(true)
                            .build();

            // Save the user to the repository
            userRepository.save(saveUser);

            return new RestResponse(
                    RestResponseObject.builder().message("User created successfully").build(), HttpStatus.OK);

        } catch (Exception e) {
            return new RestResponse(
                    RestResponseObject.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }

    private List<ErrorMessage> validateUserDetails(SignUpRequest request) {
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
        User user = UacMapper.INSTANCE.signUpDateToUserMapper(request);
        List<ErrorMessage> validateObj = validateNotNull(request);
        if (ObjectUtils.isNotEmpty(validateObj)) {
            return new RestResponse(
                    RestResponseObject.builder().message("Invalid fields!!").errors(validateObj).build(),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<User> existingUser = userRepository.findById(user.getUserId());
        if (existingUser.isEmpty())
            return new RestResponse(
                    RestResponseObject.builder()
                            .message("User ID does not Exist")
                            .errors(
                                    Collections.singletonList(
                                            ErrorMessage.builder()
                                                    .field("userId")
                                                    .message("User Id does not exist")
                                                    .build()))
                            .build(),
                    HttpStatus.BAD_REQUEST);

        try {
            User savedUser =
                    user.toBuilder()
                            .createDate(Date.from(Instant.now()))
                            .modifiedDate(Date.from(Instant.now()))
                            .createBy(request.getUserName())
                            .modifiedBy(request.getUserName())
                            .password(passwordEncoder.encode(request.getPassword()))
                            .active(true)
                            .build();
            userRepository.save(savedUser);

            return new RestResponse(
                    RestResponseObject.builder().message("User updated successfully").build(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("signup:- {}", e.getMessage());
            return new RestResponse(
                    RestResponseObject.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

    }
}
