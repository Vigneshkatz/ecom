package com.katz.ecom.service.email;


import com.katz.ecom.exception.user.EmailNotSentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.*;
import org.springframework.mail.javamail.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender, JavaMailSender sender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) throws EmailNotSentException {
        try {
            javaMailSender.send(email);
        }catch (Exception e)
        {
            throw new EmailNotSentException(e.getMessage());
        }

    }

    public void initiateEmail(String toEmail,String otp) throws EmailNotSentException{
        System.out.println("user verify");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("sanvignesh7890@gmail.com");
        mailMessage.setText("To confirm your account, use this otp : "+ otp);
        System.out.println(mailMessage.getText());
        this.sendEmail(mailMessage);
    }
}