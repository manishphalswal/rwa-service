package com.rwa.common.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class VillageDto {
    private Long id;
    private String villageName;
    private StateDto state;
}
