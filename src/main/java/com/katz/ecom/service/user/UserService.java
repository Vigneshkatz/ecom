package com.katz.ecom.service.user;

import com.katz.ecom.contants.Constants;
import com.katz.ecom.dto.ErrorDTO;
import com.katz.ecom.dto.UserDTO;
import com.katz.ecom.enums.UserType;
import com.katz.ecom.model.Otp;
import com.katz.ecom.model.User;
import com.katz.ecom.repository.OtpRepository;
import com.katz.ecom.repository.UserRepository;
import com.katz.ecom.response.Response;
import com.katz.ecom.service.email.EmailSenderService;
import com.katz.ecom.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OtpRepository otpRepository;
    public Response createUser(String phone) {
        Response response = new Response();
        ErrorDTO errorDTO = new ErrorDTO();
        UserDTO userDTO = new UserDTO();
        if(phone.isBlank()) {
            errorDTO.setErrorCode(1);
            errorDTO.setErrorMessage("number should not be null");
        }

        if(phone.length() != 10) {
            errorDTO.setErrorCode(1);
            errorDTO.setErrorMessage("Enter a valid number");
        }

        Optional<User> userOptional = Optional.empty();
        try{
            userOptional = userRepository.findByPhone(phone);
        }catch (Exception e )
        {
            errorDTO.setErrorCode(1);
            errorDTO.setErrorMessage("User not found");
        }
        if(userOptional !=null && userOptional.isPresent()) {
            response.setContent(userOptional.get());
            errorDTO.setErrorCode(1);
            errorDTO.setErrorMessage("user already exists");
        }else {
            User user = new User();
            user.setPhone(phone);
            user.setPrimaryEmail("vignesh000129@gmail.com");
            user.setIs_verified(false);
            setUserType(user,UserType.NEW_USER);
            try{
                userRepository.save(user);
                String otp = UserUtil.createOTP();
                try {
                    createOtp(user,otp);
                    emailSenderService.initiateEmail(user.getPrimaryEmail(), otp);
                }catch (Exception e) {
                    errorDTO.setErrorCode(1);
                    errorDTO.setErrorMessage(e.getMessage());
                }
            }catch (Exception e)
            {
                errorDTO.setErrorCode(2);
                errorDTO.setErrorMessage("User not saved \n internal error");
            }
        }
        response.setResponse(errorDTO);
        response.setContent(userDTO);
        return response;
    }

    public Response verifyOtp(String phone,String otp){
        Response response = new Response();
        ErrorDTO errorDTO = new ErrorDTO();
        UserDTO userDTO = new UserDTO();
        if(phone!=null && !phone.isBlank() && phone.length() < 10) {
            errorDTO.setErrorCode(1);
            errorDTO.setErrorMessage("Enter valid number");
        }
        if(otp!=null && otp.length() <4) {
            errorDTO.setErrorCode(1);
            errorDTO.setErrorMessage("Enter valid otp");
        }
        Optional<User> optionalUser = Optional.empty();
        try{
            optionalUser = this.userRepository.findByPhone(phone);
        }catch (Exception e)
        {
            errorDTO.setErrorCode(1);
            errorDTO.setErrorMessage("Otp not generated");
        }
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            Long userId = user.getUserId();
            Integer noOfAttempt = this.otpRepository.findNoOfOtpAttemptByUserId(phone);
            if(Constants.OTP_MAX_ATTEMPT <= noOfAttempt) {
                errorDTO.setErrorCode(1);
                errorDTO.setErrorMessage("Max attempt reached");
            }else {
               String recentOtp = this.otpRepository.findLatestOtpByUserId(userId);
               if(recentOtp.equals(otp)) {
                   Date currentTime = Calendar.getInstance().getTime();
                   Date otpCreatedAt = this.otpRepository.findOtpCreatedAtByUserId(userId);
                   if((currentTime.getTime() - otpCreatedAt.getTime()) <= Constants.OTP_VALID_DURATION_MILLIS)
                   {
                       if(user.getIs_verified())
                       {
                           response.setContent(user);
                           errorDTO.setErrorCode(0);
                           errorDTO.setErrorMessage("login successful");
                       }else {
                           response.setContent(user);
                           errorDTO.setErrorCode(0);
                           updateUser(user);
                           errorDTO.setErrorMessage("account created successfully");
                       }
                       updateOtpCreated(user.getUserId());
                   }else {
                       errorDTO.setErrorCode(1);
                       errorDTO.setErrorMessage("Otp expired");
                   }
               }
               else {
                   errorDTO.setErrorCode(1);
                   errorDTO.setErrorMessage("Otp not matched");
               }
            }
        }
        else {
            errorDTO.setErrorCode(1);
            errorDTO.setErrorMessage("User not found please register");
        }
        response.setContent(userDTO);
        response.setResponse(errorDTO);
        return response;
    }

    private void updateUser(User user) {
        user.setIs_verified(true);
        this.userRepository.save(user);
    }
    private void updateOtpCreated(Long userId) {
        Otp otp = this.otpRepository.findByUserId(userId);
        otp.setOtp_attempts(otp.getOtp_attempts()+1);
        otp.setOtp_created_at(Calendar.getInstance().getTime());
        this.otpRepository.save(otp);
    }

    private void createOtp(User user,String otpString) {
        Otp otp = new Otp();
        otp.setUser(user);
        otp.setOtp_attempts(0);
        otp.setOtp_generated(otpString);
        otp.setEmail(user.getPrimaryEmail());
        otp.setPhone(user.getPhone());
        otp.setOtp_created_at(Calendar.getInstance().getTime());
        this.otpRepository.save(otp);
    }

    private void setUserType(User user,UserType userType) {
        switch (userType){
            case PRIME:
                user.setUser_type("prime");
                break;
            case NEW_USER:
                user.setUser_type("new_user");
                break;
            case TRANSACTING_USER:
                user.setUser_type("transacting_user");
                break;
            case NON_TRANSACTING_USER:
                user.setUser_type("non_transacting_user");
                break;
            default:
                user.setUser_type("new_user");
                break;
        }
    }

}
