package com.rwa.referencedata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "m_village")
public class Village implements Serializable {
    @Id
    private Long id;

    @Column(name="village_name", insertable = false, updatable = false)
    private String villageName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id")
    private State state;

    @Column(name="created_date", insertable = false, updatable = false)
    private Timestamp createdDate;
}
