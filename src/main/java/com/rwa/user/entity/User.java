package com.rwa.user.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "t_user_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name="username", updatable = false, nullable = false)
    private String username;

    private String firstName;
    private String lastName;
    private Date dob;

    private String aadharDigits;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private String occupation;

    private String mobileNo;
    private String emailId;

    @Column(name="created_date", insertable = false, updatable = false)
    private Timestamp createdDate;
    private String createdBy;

}
