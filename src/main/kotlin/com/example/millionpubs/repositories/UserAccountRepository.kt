package com.example.millionpubs.repositories

import com.example.millionpubs.domain.UserAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserAccountRepository : JpaRepository<UserAccount, Long> {

    @Query("SELECT balance FROM USER_ACCOUNT WHERE id = :id", nativeQuery = true)
    fun getUserAccountBalanceByUserName(@Param("id") id: String): Double?
}