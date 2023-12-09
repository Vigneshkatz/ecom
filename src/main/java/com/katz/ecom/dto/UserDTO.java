package com.katz.ecom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
    

    @JsonProperty("newUser")
    private Boolean newUser;

    @JsonProperty("profileVerified")
    private Boolean profileVerified;

    @JsonProperty("guestUser")
    private Boolean guestUser;
    @JsonProperty("pendingPhone")
    private Boolean pendingPhone;
    @JsonProperty("otp")
    private Boolean otp;

    @JsonProperty("imgUrl1")
    private String imgUrl1;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("is_guest_user")
    private Boolean isGuestUser;

    @JsonProperty("is_new_user")
    private Boolean isNewUser;

    @JsonProperty("is_profile_verified")
    private Boolean isProfileVerified;

    @JsonProperty("is_blog")
    private Boolean isBlog;


}
