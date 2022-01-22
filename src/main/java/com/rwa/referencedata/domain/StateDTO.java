package com.rwa.referencedata.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StateDTO {
    private Long id;
    private String stateName;
    private String country;
}
