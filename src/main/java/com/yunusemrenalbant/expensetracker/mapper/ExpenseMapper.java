package com.yunusemrenalbant.expensetracker.mapper;

import com.yunusemrenalbant.expensetracker.dto.CategoryDto;
import com.yunusemrenalbant.expensetracker.dto.ExpenseDto;
import com.yunusemrenalbant.expensetracker.entity.Category;
import com.yunusemrenalbant.expensetracker.entity.Expense;

public class ExpenseMapper {

    public static Expense mapToExpense(ExpenseDto expenseDto) {

        return new Expense(
                expenseDto.id(),
                expenseDto.amount(),
                expenseDto.expenseDate(),
                new Category(
                        expenseDto.categoryDto().id(),
                        expenseDto.categoryDto().name()
                )
        );
    }


    public static ExpenseDto mapToExpenseDto(Expense expense) {
        return new ExpenseDto(
                expense.getId(),
                expense.getAmount(),
                expense.getExpenseDate(),
                new CategoryDto(
                        expense.getCategory().getId(),
                        expense.getCategory().getName()
                )
        );
    }
}
