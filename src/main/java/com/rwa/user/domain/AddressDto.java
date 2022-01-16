package com.rwa.user.domain;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddressDto implements Serializable {

    private Long id;
    private String address1;

    private Long villageId;

    private String otherVillage;
    private String district;
    private String city;

    private Long stateId;

    private String pincode;

}
