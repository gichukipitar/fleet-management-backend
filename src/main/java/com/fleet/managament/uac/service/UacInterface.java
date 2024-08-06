package com.fleet.managament.uac.service;

import com.fleet.managament.uac.dtos.ChangeRoleRequest;
import com.fleet.managament.uac.dtos.ChangeStatusRequest;
import com.fleet.managament.uac.dtos.JwtAuthenticationResponse;
import com.fleet.managament.uac.dtos.SignUpRequest;
import com.fleet.managament.utils.RestResponse;
import com.fleet.managament.utils.SearchDto;
import org.springframework.data.domain.Pageable;

public interface UacInterface {
    RestResponse createUser(SignUpRequest request);
    JwtAuthenticationResponse signIn(SigninRequest request);

    RestResponse fetchUser (SearchDto searchDto);

    RestResponse changePassword(ChangeStatusRequest changeStatusRequest);

    RestResponse fetchPaginatedUserList(SearchDto searchDto, Pageable pageable);

    RestResponse changeUserStatus(ChangeStatusRequest changeStatusRequest);

    RestResponse changeUserRole (ChangeRoleRequest changeRoleRequest);

    RestResponse updateUser (SignUpUpdateRequest request);

}
