package com.katz.ecom.controller;

import com.katz.ecom.model.Address;
import com.katz.ecom.request.AddressUpdateRequest;
import com.katz.ecom.response.Response;
import com.katz.ecom.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/katz_user")
public class User {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Response createUser(@RequestBody String phone){
        System.out.println(phone);
        return userService.createUser(phone);
    }

    @PostMapping("/verify-otp/{phone}/{otp}")
    public Response verifyUser(@RequestParam("phone") String phone, @RequestParam("otp") String otp){
      return this.userService.verifyOtp(phone,otp);
    }

    @PostMapping("/test")
    public ResponseEntity<String> something(@RequestBody HttpRequest request) {
        // Your logic here
        // You can access request details such as method, headers, body, etc.

        String requestBody = request.toString();
        // Process the request body or perform other actions

        return ResponseEntity.ok("Request processed successfully");
    }

    @PostMapping("/address/update")
    public Response addAddress(@RequestBody AddressUpdateRequest request) {
        Address address = request.getAddress();
        Long userId = request.getUserId();
        return this.userService.addAddress(address, userId);
    }

}
