package com.example.income_expence.Model

data class IncomeExpenseModel(
    val id :String,
    val title: String,
    val amount: String,
    val notes: String,
    val date: String,
    val time: String,
    val status: Int,
    val category: String)