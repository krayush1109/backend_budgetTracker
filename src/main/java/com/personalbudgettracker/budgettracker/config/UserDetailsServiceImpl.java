package com.personalbudgettracker.budgettracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.personalbudgettracker.budgettracker.model.User;
import com.personalbudgettracker.budgettracker.repository.UserRepo;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

     @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       
        User user = userRepo.findByEmail(email).orElse(null);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username");
        }

        return new UserPrinciple(user);
    }
}
