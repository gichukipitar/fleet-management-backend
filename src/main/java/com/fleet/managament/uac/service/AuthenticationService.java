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
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fleet.managament.utils.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

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

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)

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
        List<ErrorMessage> errorMessageList = new ArrayList<>();

        Optional<User> existingUser = userRepository.findByUserName(request.getUserName());
        if (existingUser.isPresent())
            errorMessageList.add(
                    ErrorMessage.builder().field("userName").message("Duplicate username found").build());
        Optional<User> existingEmail = userRepository.findByEmail(request.getUserName());
        if (existingEmail.isPresent())
            errorMessageList.add(
                    ErrorMessage.builder().field("email").message("Duplicate email found").build());
        Optional<User> existingPhoneNumber = userRepository.findByPhoneNumber(request.getUserName());
        if (existingPhoneNumber.isPresent())
            errorMessageList.add(
                    ErrorMessage.builder().field("phoneNumber").message("Duplicate phone number found").build());
        return errorMessageList;

    }

    @Override
    public JwtAuthenticationResponse signIn(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        var user =
                userRepository
                        .findByUserName(request.getUserName())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public RestResponse fetchUser(SearchDto searchDto) {
        List<ErrorMessage> validateObj = validateNotNull(searchDto);
        if (ObjectUtils.isNotEmpty(validateObj)) {
            return new RestResponse(
                    RestResponseObject.builder().message("Invalid fields!!").errors(validateObj).build(),
                    HttpStatus.BAD_REQUEST);
        }
        try {
            List<User> configs =
                    userCustomAppRepository.findByFieldAndValue(
                            User.class, searchDto.getFieldName(), searchDto.getSearchValue());

            return new RestResponse(
                    RestResponseObject.builder().message("User fetched successfully").build(), HttpStatus.OK);

        } catch (Exception e) {
            log.error("Fetch entity exception:{}", e.getMessage());
            return new RestResponse(
                    RestResponseObject.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public RestResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        List<ErrorMessage> validateObj = validateNotNull(changePasswordRequest);
        if (ObjectUtils.isNotEmpty(validateObj)) {
            return new RestResponse(
                    RestResponseObject.builder().message("Invalid fields!").errors(validateObj).build(),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = userRepository.findByUserName(changePasswordRequest.getUserName());
        if (user.isEmpty()) {
            return new RestResponse(
                    RestResponseObject.builder().message("User does not exist").build(), HttpStatus.BAD_REQUEST);
        }
        User existingUser =
                user.get().toBuilder()
                        .password(passwordEncoder.encode(changePasswordRequest.getPassword()))
                        .build();
        userRepository.save(existingUser);
        return new RestResponse(
                RestResponseObject.builder().message("Password updated successfully successfully").build(), HttpStatus.OK
        );
    }

    @Override

    public RestResponse fetchPaginatedUserList(SearchDto searchDto, Pageable pageable) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> user = cq.from(User.class);

            List<Predicate> predicates = new ArrayList<>();

            if (ObjectUtils.isNotEmpty(searchDto.getFieldName()) && ObjectUtils.isNotEmpty(searchDto.getFieldName())) {
                predicates.add(cb.like(user.get(searchDto.getFieldName()), "%" + searchDto.getSearchValue() + "%"));
            }

            cq.where(predicates.toArray(new Predicate[0]));

            // Apply pagination
            List<User> users = entityManager.createQuery(cq)
                    .setFirstResult((int) pageable.getOffset())
                    .setMaxResults(pageable.getPageSize())
                    .getResultList();

            // Get total count for pagination
            CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
            Root<User> userCount = countQuery.from(User.class);
            countQuery.select(cb.count(userCount)).where(predicates.toArray(new Predicate[0]));
            Long count = entityManager.createQuery(countQuery).getSingleResult();

            Page<User> page = new PageImpl<>(users, pageable, count);

            return new RestResponse(
                    RestResponseObject.builder().message("Fetched Successfully").payload(page).build(),
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error("fetchPaginatedUserList Exception:-" + e.getMessage());
            return new RestResponse(
                    RestResponseObject.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public RestResponse changeUserStatus(ChangeStatusRequest changeStatusRequest) {
        List<ErrorMessage> validateObj = validateNotNull(changeStatusRequest);
        if (ObjectUtils.isNotEmpty(validateObj)) {
            return new RestResponse(
                    RestResponseObject.builder().message("Invalid fields!").errors(validateObj).build(),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<User> user =
                userRepository.findByUserName(changeStatusRequest.getUserName());
        if (user.isEmpty()) {
            return new RestResponse(
                    RestResponseObject.builder().message("User does not exist").build(), HttpStatus.BAD_REQUEST);
        }
        User existingUser = user.get().toBuilder().active(changeStatusRequest.isStatus()).build();
        userRepository.save(existingUser);
        return new RestResponse(
                RestResponseObject.builder().message("User status updated successfully").build(), HttpStatus.OK);
    }

    @Override
    public RestResponse changeUserRole(ChangeRoleRequest changeRoleRequest) {
        List<ErrorMessage> validateObj = validateNotNull(changeRoleRequest);
        if (ObjectUtils.isNotEmpty(validateObj)) {
            return new RestResponse(
                    RestResponseObject.builder().message("Invalid fields!!").errors(validateObj).build(),
                    HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = userRepository.findByUserName(changeRoleRequest.getUserName());
        if (user.isEmpty()) {
            return new RestResponse(
                    RestResponseObject.builder().message("User does not exist").build(), HttpStatus.BAD_REQUEST);
        }
        User existingUser = user.get();
        Role previousRole = existingUser.getRole();
        Role updateRole = Role.valueOf(changeRoleRequest.getRole());
        boolean isValid = validateRole(previousRole, updateRole);
        if (isValid) {
            userRepository.save(existingUser.toBuilder().role(updateRole).build());
            return new RestResponse(
                    RestResponseObject.builder().message("User role updated successfully").build(), HttpStatus.OK);
        }
        return new RestResponse(
                RestResponseObject.builder().message("Invalid Role provided").build(), HttpStatus.BAD_REQUEST);
    }

    private boolean validateRole(Role previousRole, Role newRole) {
        if (previousRole.toString().startsWith("SUPER")) {
            return List.of(Role.SUPER_ADMIN, Role.SUPER_USER).contains(newRole);
        }
        if (previousRole.toString().startsWith("NORMAL")) {
            return List.of(Role.NORMAL_ADMIN, Role.NORMAL_USER).contains(newRole);
        }
        return false;
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
