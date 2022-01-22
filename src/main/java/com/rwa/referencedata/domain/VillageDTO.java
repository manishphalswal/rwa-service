package com.rwa.referencedata.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class VillageDTO {
    private Long id;
    private String villageName;
    private StateDTO state;
}
