package com.example.millionpubs.json.extractors

import com.example.millionpubs.json.model.ExchangeRate
import com.example.millionpubs.services.UserAccountServiceImpl
import com.google.gson.Gson
import org.springframework.stereotype.Component

@Component
class ExchangeValueExtractor {

    fun extract(json: String): Double {
        val gson = Gson();
        val exchangeRate = gson.fromJson(json, ExchangeRate::class.java)
        return when (val rate = exchangeRate.rates[0][UserAccountServiceImpl.RATE_KEY]) {
            is Number -> rate.toDouble()
            else -> throw NumberFormatException(UserAccountServiceImpl.NUMBER_FORMAT_EXCEPTION_MESSAGE)
        }
    }
}