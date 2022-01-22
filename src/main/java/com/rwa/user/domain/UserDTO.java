package com.rwa.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rwa.common.domain.Role;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    @JsonProperty("password")
    private String userPassword;

    private String firstName;
    private String lastName;
    private Date dob;

    private String aadharDigits;

    private AddressDTO address;

    private String occupation;

    private String mobileNo;
    private String emailId;
    private Role role;

}
