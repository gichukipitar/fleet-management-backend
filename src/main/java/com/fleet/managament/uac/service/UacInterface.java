package com.fleet.managament.uac.service;

import com.fleet.managament.uac.dtos.*;
import com.fleet.managament.utils.RestResponse;
import com.fleet.managament.utils.SearchDto;
import org.springframework.data.domain.Pageable;

public interface UacInterface {
    RestResponse createUser(SignUpRequest request);
    JwtAuthenticationResponse signIn(SigninRequest request);

    RestResponse fetchUser (SearchDto searchDto);

    RestResponse changePassword(ChangePasswordRequest changePasswordRequest);

    RestResponse fetchPaginatedUserList(SearchDto searchDto, Pageable pageable);

    RestResponse changeUserStatus(ChangeStatusRequest changeStatusRequest);

    RestResponse changeUserRole (ChangeRoleRequest changeRoleRequest);

    RestResponse updateUser (SignUpUpdateRequest request);

}
