package com.katz.ecom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long address_id;
    private String name;
    private String first_name;
    private String last_name;
    private String phone;
    private String address_line_1;
    private String address_line_2;
    private String landmark;
    private String city;
    private String pinCode;
    private String state;
    private String address_type;
    private Boolean is_default;
    private Boolean is_serviceable;
    private Boolean is_cod_enabled;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
}
