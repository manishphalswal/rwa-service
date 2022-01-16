package com.rwa.common.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StateDto {
    private Long id;
    private String stateName;
    private String country;
}
