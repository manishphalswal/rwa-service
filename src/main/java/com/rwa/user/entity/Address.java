package com.rwa.user.entity;

import com.rwa.referencedata.entity.State;
import com.rwa.referencedata.entity.Village;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_user_address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_address_generator")
    @SequenceGenerator(name = "user_address_generator", sequenceName = "t_user_address_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String address1;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "village_id")
    private Village village;

    private String otherVillage;
    private String district;
    private String city;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "state_id")
    private State state;

    private String pincode;

    @Column(name="created_date", insertable = false, updatable = false)
    private Timestamp createdDate;
    private String createdBy;
}
