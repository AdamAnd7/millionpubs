package com.example.millionpubs.json.model

class ExchangeRate(
    val table: String,
    val currency: String,
    val code: String,
    val rates: List<Map<String, Any>>
)