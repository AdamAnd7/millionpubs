package com.example.millionpubs.services

interface UserAccountService {
    fun getUserAccountBalanceInUSD(userId: String): Double?
}