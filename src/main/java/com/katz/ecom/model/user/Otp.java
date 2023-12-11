package com.katz.ecom.model.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "user_otp")
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long otp_id;
    private String otp_generated;
    @Temporal(TemporalType.TIMESTAMP)
    private Date otp_created_at;
    private int otp_attempts;
    private String phone;
    private String email;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
}
