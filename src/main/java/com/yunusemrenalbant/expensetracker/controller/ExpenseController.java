package com.yunusemrenalbant.expensetracker.controller;

import com.yunusemrenalbant.expensetracker.dto.ExpenseDto;
import com.yunusemrenalbant.expensetracker.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(
            @RequestBody ExpenseDto expenseDto
    ) {
        ExpenseDto savedExpense = expenseService.createExpense(expenseDto);

        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ExpenseDto> getExpenseById(
            @PathVariable("id") Long expenseId
    ) {

        ExpenseDto expense = expenseService.getExpenseById(expenseId);

        return ResponseEntity.ok(expense);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses() {
        List<ExpenseDto> expenses = expenseService.getAllExpenses();

        return ResponseEntity.ok(expenses);
    }

    @PutMapping("{id}")
    public ResponseEntity<ExpenseDto> updateExpense(
            @PathVariable("id") Long expenseId,
            @RequestBody ExpenseDto expenseDto
    ) {
        ExpenseDto updatedExpense = expenseService.updateExpense(expenseId, expenseDto);

        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteExpense(
            @PathVariable("id") Long expenseId
    ) {
        expenseService.deleteExpense(expenseId);

        return ResponseEntity.ok("Expense deleted successfully");
    }
}
