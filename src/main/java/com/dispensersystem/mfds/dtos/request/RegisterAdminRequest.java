package com.dispensersystem.mfds.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterAdminRequest {
    private String name;
    private String username;
    private String password;

}
