package com.katz.ecom.model;


import com.katz.ecom.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String user_name;
    private String user_type;
    private Date dob;
    private String invite_code;
    private String phone;
    private Boolean is_verified;
    private String primaryEmail;
}
