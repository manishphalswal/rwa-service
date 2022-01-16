package com.rwa.referencedata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "m_state")
public class State implements Serializable {

    @Id
    private Long id;

    @Column(name="state_name", insertable = false, updatable = false)
    private String stateName;

    @Column(name="country", insertable = false, updatable = false)
    private String country;

    @Column(name="created_date", insertable = false)
    private Timestamp createdDate;

}
