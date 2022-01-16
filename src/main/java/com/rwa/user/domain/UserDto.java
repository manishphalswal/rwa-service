package com.rwa.user.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto implements Serializable {

    private Long id;

    private String username;

    private String firstName;
    private String lastName;
    private Date dob;

    private String aadharDigits;

    private AddressDto address;

    private String occupation;

    private String mobileNo;
    private String emailId;

}
