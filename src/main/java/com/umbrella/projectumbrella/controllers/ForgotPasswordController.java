package com.umbrella.projectumbrella.controllers;

import com.umbrella.projectumbrella.dto.ChangePassword;
import com.umbrella.projectumbrella.dto.MailBody;
import com.umbrella.projectumbrella.entities.ForgotPassword;
import com.umbrella.projectumbrella.entities.User;
import com.umbrella.projectumbrella.repositories.ForgotPasswordRepository;
import com.umbrella.projectumbrella.repositories.UserRepository;
import com.umbrella.projectumbrella.services.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/forgot-password")
public class ForgotPasswordController {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordController(UserRepository userRepository, EmailService emailService, ForgotPasswordRepository forgotPasswordRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/send-otp/{email}")
    public ResponseEntity<String> sendOtp(@PathVariable String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Please provide a valid email"));

        int otp = otpGenerator();

        MailBody mailBody = MailBody.builder()
                .to(email)
                .from("utkarsh.mishra@wobb.ai")
                .text("This is the OTP for your reset password request: "+ otp)
                .subject("OTP For Reset Password")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 70*1000))
                .user(user)
                .build();

        emailService.sendSimpleMessage(mailBody);

        forgotPasswordRepository.save(fp);

        return ResponseEntity.ok("OTP has been successfully sent to "+ email);
    }

    public  Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }

    @GetMapping("/verify-otp/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email) {

        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Please provide a valid email"));

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user).orElseThrow(() -> new RuntimeException("Invalid OTP for email "+email+ " " + otp));

        if(fp.getExpirationTime().before(Date.from(Instant.now()))){
            forgotPasswordRepository.deleteById(fp.getFpid());
            return new ResponseEntity<>("OTP has expired!", HttpStatus.EXPECTATION_FAILED);
        }

        forgotPasswordRepository.deleteById(fp.getFpid());
        return ResponseEntity.ok("OTP Verified successfully, Reset your password!");

    }

    @PostMapping("/change-password/{email}")
    public ResponseEntity<String> changePassword(@RequestBody ChangePassword changePassword, @PathVariable String email){

        if(!Objects.equals(changePassword.password(), changePassword.confirmPassword() )){
            return new ResponseEntity<>("Password and confirm password dont match", HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(changePassword.password());

        userRepository.updatePassword(email, encodedPassword);

        return new ResponseEntity<>("Password updated successfully", HttpStatus.CREATED);
    }


}
