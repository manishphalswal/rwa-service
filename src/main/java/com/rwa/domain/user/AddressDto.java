package com.rwa.domain.user;

import com.rwa.domain.common.StateDto;
import com.rwa.domain.common.VillageDto;
import com.rwa.entity.reference.State;
import com.rwa.entity.reference.Village;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
