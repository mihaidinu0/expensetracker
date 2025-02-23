package com.example.expensetracker.service;

import com.example.expensetracker.dto.ExpenseDto;
import com.example.expensetracker.dto.ExpenseMapper;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.ExpenseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService {

    private final UserService userService;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(UserService userService, ExpenseRepository expenseRepository) {
        this.userService = userService;
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(ExpenseDto expenseDto) {
        Expense expense = ExpenseMapper.toEntity(expenseDto);
        User currentUser = userService.getCurrentUser();
        expense.setUser(currentUser);
        return expenseRepository.save(expense);
    }

    public Page<ExpenseDto> getExpenses(Pageable pageable) {
        Page<Expense> expensePage = expenseRepository.findAll(pageable);
        return expensePage.map(ExpenseMapper::toDto);
    }

    public ExpenseDto getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        return expense.map(ExpenseMapper::toDto).orElse(null);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Transactional
    public ExpenseDto updateExpense(Long id, ExpenseDto expenseDto) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id " + id));

        if (expenseDto.getAmount() != null) {
            expense.setAmount(expenseDto.getAmount());
        }
        if (expenseDto.getDate() != null) {
            expense.setDate(expenseDto.getDate());
        }
        if (expenseDto.getCategory() != null) {
            expense.setCategory(expenseDto.getCategory());
        }

        Expense updatedExpense = expenseRepository.save(expense);
        return ExpenseMapper.toDto(updatedExpense);
    }
}
