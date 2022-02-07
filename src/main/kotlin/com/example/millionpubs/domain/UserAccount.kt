package com.example.millionpubs.domain

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "USER_ACCOUNT")
data class UserAccount(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(name = "user_name")
    val userName: String,
    @Column(name = "balance")
    val balance: Long
)