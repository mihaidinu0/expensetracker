package com.example.expensetracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDto {
    private Long id;
    private Double amount;
    private LocalDate date;
    private String category;
    private Long userId;
}
