package com.example.millionpubs.controllers

import com.example.millionpubs.services.UserAccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/accounts")
class UserAccountController @Autowired constructor(
    private val userAccountService: UserAccountService
) {


    @GetMapping("/{id}/balance")
    fun getAccountBalanceByUserId(@PathVariable("id") id: String): ResponseEntity<Double> {
        val balance = userAccountService.getUserAccountBalanceInUSD(id)
        return if (balance != null) ResponseEntity<Double>(
            balance,
            HttpStatus.OK
        ) else ResponseEntity<Double>(HttpStatus.NOT_FOUND)
    }
}