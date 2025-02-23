package com.example.expensetracker.dto;

import com.example.expensetracker.model.Expense;

public class ExpenseMapper {
    public static ExpenseDto toDto(Expense expense) {
        if (expense == null) {
            return null;
        }

        ExpenseDto dto = new ExpenseDto();
        dto.setId(expense.getId());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        dto.setCategory(expense.getCategory());

        if (expense.getUser() != null) {
            dto.setUserId(expense.getUser().getId());
        }

        return dto;
    }

    public static Expense toEntity(ExpenseDto dto) {
        if (dto == null) {
            return null;
        }

        Expense expense = new Expense();

        expense.setId(dto.getId());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());
        expense.setCategory(dto.getCategory());

        return expense;
    }
}
