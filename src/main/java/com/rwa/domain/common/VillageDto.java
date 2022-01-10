package com.rwa.domain.common;

import com.rwa.entity.reference.State;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
