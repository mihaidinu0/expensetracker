package com.example.expensetracker.dto;

import com.example.expensetracker.model.UserType;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private UserType userType;
}
