package com.rwa.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user_session")
public class UserSession implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_session_generator")
    @SequenceGenerator(name = "user_session_generator", sequenceName = "t_user_session_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String username;
    private String userPassword;

    private String role;

    private boolean loggedIn;
    private boolean active;
    private boolean locked;
    private boolean credExpired;

    private Timestamp lastLogin;
    private Timestamp loginTime;

    @Column(name="created_date", insertable = false, updatable = false)
    private Timestamp createdDate;
}
