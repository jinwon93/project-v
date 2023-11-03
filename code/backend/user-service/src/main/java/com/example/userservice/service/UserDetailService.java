package com.example.userservice.service;

import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.userservice.entity.User userEntity = repository.findByEmail(username);

        if (userEntity == null) {
            throw  new UsernameNotFoundException(username);
        }
        return  new User(userEntity.getEmail() , userEntity.getEncryptedPwd() , true , true , true , true , new ArrayList<>());
    }
}
