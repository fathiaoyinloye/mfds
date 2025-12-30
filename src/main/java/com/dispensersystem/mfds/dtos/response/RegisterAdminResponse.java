package com.dispensersystem.mfds.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterAdminResponse {
    private String name;
    private String username;
    private String password;

}
