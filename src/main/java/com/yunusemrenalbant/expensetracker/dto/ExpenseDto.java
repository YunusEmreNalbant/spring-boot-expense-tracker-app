package com.yunusemrenalbant.expensetracker.dto;

import com.yunusemrenalbant.expensetracker.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseDto(
        Long id,
        BigDecimal amount,
        LocalDate expenseDate,
        CategoryDto categoryDto
) { }
