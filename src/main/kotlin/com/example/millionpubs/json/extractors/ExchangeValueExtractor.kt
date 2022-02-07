package com.example.millionpubs.json.extractors

import com.example.millionpubs.json.model.ExchangeRate
import com.google.gson.Gson
import org.springframework.stereotype.Component

@Component
class ExchangeValueExtractor {
    companion object {
        private const val RATE_KEY = "ask"
        private const val NUMBER_FORMAT_EXCEPTION_MESSAGE =
            "Error while extracting value from json: exchange rate is not a number"
    }

    fun extract(json: String): Double {
        val gson = Gson();
        val exchangeRate = gson.fromJson(json, ExchangeRate::class.java)
        return when (val rate = exchangeRate.rates[0][RATE_KEY]) {
            is Number -> rate.toDouble()
            else -> throw NumberFormatException(NUMBER_FORMAT_EXCEPTION_MESSAGE)
        }
    }
}