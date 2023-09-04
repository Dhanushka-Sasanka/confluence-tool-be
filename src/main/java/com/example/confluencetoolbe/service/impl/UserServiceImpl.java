//package com.example.confluencetoolbe.service.impl;
//
//
//
//import com.example.confluencetoolbe.entity.User;
//import com.example.confluencetoolbe.repository.UserRepo;
//import com.example.confluencetoolbe.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//
//@Slf4j
//public class UserServiceImpl implements UserDetailsService , UserService {
//
//    @Autowired
//    private UserRepo userRepo;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        User user = userRepo.findByUsernameOrEmail(username, username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not exists by Username");
//        }
//        Set<GrantedAuthority> authorities = user.getRoles().stream()
//                .map((role) -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toSet());
//
//        log.info("Authorities2 :{}", authorities);
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//    }
//
//    @Override
//    public ResponseEntity<List<User>> getAllUser() {
//        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<User> createUser(User userDTO) {
//        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        User savedUser = userRepo.save(userDTO);
//        return new ResponseEntity<>(savedUser, HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<User> updateUser(Integer userID, User userDTO) {
//        Optional<User> userRepoById = userRepo.findById(userID);
//
//        if (userRepoById.isPresent()) {
//            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//            userRepoById.get().setEmail(userDTO.getEmail());
//            userRepoById.get().setPassword(userDTO.getPassword());
//            userRepoById.get().setName(userDTO.getName());
//            userRepoById.get().setUsername(userDTO.getUsername());
//
//            User updateUser = userRepo.save(userRepoById.get());
//            return new ResponseEntity<>(updateUser, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }
//
//    @Override
//    public Map<String, Boolean> deleteUser(Integer userID) {
//        Map<String, Boolean> map = new HashMap<>();
//        if (userRepo.findById(userID).isPresent()) {
//            userRepo.deleteById(userID);
//            map.put("isDeleted", Boolean.TRUE);
//            return map;
//        }
//        map.put("isDeleted", Boolean.FALSE);
//        return map;
//    }
//
//    @Override
//    public ResponseEntity<User> searchUser(Integer userID) {
//        Optional<User> userRepoById = userRepo.findById(userID);
//        return userRepoById.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//
//    }
//}
//
