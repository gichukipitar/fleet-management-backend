package com.fleet.managament.uac.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String phone;
    private Role role;
    private Date createDate;
    private String createBy;
    private Date modifiedDate;
    private String modifiedBy;
    private boolean active;

}
