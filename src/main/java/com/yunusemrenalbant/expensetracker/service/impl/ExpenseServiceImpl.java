package com.yunusemrenalbant.expensetracker.service.impl;

import com.yunusemrenalbant.expensetracker.dto.ExpenseDto;
import com.yunusemrenalbant.expensetracker.entity.Category;
import com.yunusemrenalbant.expensetracker.entity.Expense;
import com.yunusemrenalbant.expensetracker.exceptions.ResourceNotFoundException;
import com.yunusemrenalbant.expensetracker.mapper.ExpenseMapper;
import com.yunusemrenalbant.expensetracker.repository.CategoryRepository;
import com.yunusemrenalbant.expensetracker.repository.ExpenseRepository;
import com.yunusemrenalbant.expensetracker.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {

        Expense expense = ExpenseMapper.mapToExpense(expenseDto);

        Expense savedExpense = expenseRepository.save(expense);

        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }

    @Override
    public ExpenseDto getExpenseById(Long expenseId) {

        Expense expense = expenseRepository
                .findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));

        return ExpenseMapper.mapToExpenseDto(expense);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();

       return expenses.stream()
                .map(expense -> ExpenseMapper.mapToExpenseDto(expense))
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {

        Expense expense = expenseRepository
                   .findById(expenseId)
                   .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));

        expense.setAmount(expenseDto.amount());
        expense.setExpenseDate(expenseDto.expenseDate());

        if (expenseDto.categoryDto() != null) {
            Category category = categoryRepository
                    .findById(expenseDto.categoryDto().id())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + expenseDto.categoryDto().id()));

            expense.setCategory(category);
        }

        Expense updatedExpense = expenseRepository.save(expense);

        return ExpenseMapper.mapToExpenseDto(updatedExpense);
    }

    @Override
    public void deleteExpense(Long expenseId) {
        Expense expense = expenseRepository
                .findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));

        expenseRepository.delete(expense)   ;
    }
}
