package com.app.auth.service;

import com.app.auth.beans.EOUser;
import com.app.auth.exceptions.GlbException;
import com.app.auth.repository.UserRepository;
import com.app.auth.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;


     public long validateOtp(EOUser user,long otp){
         Long otpVal = user.getOtp();
         if(otpVal != null && otpVal == otp){
             return validateOtp(user, AppUtil.generateOtp());
         }
         return otp;
     }

    public Long generateOtp(String emailId){
        EOUser eoUser = this.userRepository.findByEmail(emailId).get(0);
        if(eoUser == null){
            throw new GlbException("Employee not found with id "+emailId);
        }
        Long otp = this.validateOtp(eoUser,AppUtil.generateOtp());
        eoUser.setOtp(otp);
        this.userRepository.saveAndFlush(eoUser);
        return otp;
    }

    public EOUser verifyOtp(Map<String,Object> objectMap){
        EOUser eoUser = this.userRepository.findByEmail((String) objectMap.get("email")).get(0);
        if(eoUser == null){
            throw new GlbException("Employee not found with id "+eoUser.getEmail());
        }
        eoUser.setEmailVerified(eoUser.getOtp() == ((Number)objectMap.get("otp")).longValue());
        userRepository.saveAndFlush(eoUser);
        return eoUser;
    }


}
