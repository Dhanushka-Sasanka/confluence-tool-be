package com.example.confluencetoolbe.modal;


import com.example.confluencetoolbe.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String name;
    private String username;
    private String email;
    private String password;
    private String trackID;
    private Set<Role> roles;
}
