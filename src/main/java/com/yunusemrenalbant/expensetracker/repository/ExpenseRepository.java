package com.yunusemrenalbant.expensetracker.repository;

import com.yunusemrenalbant.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
