package com.example.confluencetoolbe.service.impl;


import com.example.confluencetoolbe.entity.Role;
import com.example.confluencetoolbe.entity.User;
import com.example.confluencetoolbe.modal.LoginRequest;
import com.example.confluencetoolbe.modal.RegisterRequest;
import com.example.confluencetoolbe.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserAuthService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(username,username);

        List<Role> userRoles = user.getRoles().stream().collect(Collectors.toList());

        List<GrantedAuthority> grantedAuthorities = userRoles.stream().map(r -> {
            return new SimpleGrantedAuthority(r.getName());
        }).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(username + "/"+ user.getTrackID()+"/"+ user.getEmail(), user.getPassword(), grantedAuthorities);
    }




    public void saveUser(RegisterRequest request) {
        if (userRepository.findByUsernameOrEmail(request.getUsername() , request.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }
log.info("User : {} ", request);
        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setTrackID(request.getTrackID());

        user.setRoles(request.getRoles().stream().map(r -> {
            Role ur = new Role();
            ur.setId(r.getId());
            ur.setName(r.getName());
            return ur;
        }).collect(Collectors.toSet()));
        log.info("User : {} ", user);
        userRepository.save(user);
    }

}
