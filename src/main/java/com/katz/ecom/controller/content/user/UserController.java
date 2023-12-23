package com.katz.ecom.controller.content.user;

import com.katz.ecom.request.AddressUpdateRequest;
import com.katz.ecom.response.Response;
import com.katz.ecom.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/katz_user")
public class UserController {
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

    @PostMapping("/address/create")
    public Response addAddress(@RequestBody AddressUpdateRequest request) {
        return this.userService.addAddress(request);
    }
    @PutMapping("/address/update")
    public Response updateAddress(@RequestBody AddressUpdateRequest request) {
        return this.userService.updateAddress(request);
    }

}
