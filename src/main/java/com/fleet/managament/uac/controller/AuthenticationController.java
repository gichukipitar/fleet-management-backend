package com.fleet.managament.uac.controller;

import com.fleet.managament.uac.dtos.*;
import com.fleet.managament.uac.service.AuthenticationService;
import com.fleet.managament.utils.RestResponse;
import com.fleet.managament.utils.SearchDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/uac")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/create/user")
    public RestResponse createUser(@RequestBody SignUpRequest signUpRequest) {
        return authenticationService.createUser(signUpRequest);
    }

    @PostMapping("/update/user")
    public RestResponse updateUser(@RequestBody SignUpUpdateRequest signUpUpdateRequest) {
        return authenticationService.updateUser(signUpUpdateRequest);
    }

    @PostMapping("/sign/in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(
            @Valid @RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signinRequest));
    }

    @PostMapping("/change/password")
    public RestResponse changeUserPassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest){
        return authenticationService.changePassword(changePasswordRequest);
    }
    @PostMapping("/change/status")
    public RestResponse changeUserStatus(@Valid @RequestBody ChangeStatusRequest changeStatusRequest){
        return authenticationService.changeUserStatus(changeStatusRequest);
    }
    @PostMapping("/change/role")
    public RestResponse changeUserRole(@Valid @RequestBody ChangeRoleRequest changeRoleRequest){
        return authenticationService.changeUserRole(changeRoleRequest);
    }

    @GetMapping(path = "/search/user", produces = "application/json")
    RestResponse fetchUser(SearchDto searchDto){
        return authenticationService.fetchUser(searchDto);
    }

    @GetMapping(path = "/list/users", produces = "application/json")
    RestResponse fetchPaginatedUserList(SearchDto searchDto, Pageable pageable){
        return authenticationService.fetchPaginatedUserList(searchDto, pageable);
    }
}
