package com.example.millionpubs.services

import com.example.millionpubs.errors.NBPExchangeRateException
import com.example.millionpubs.json.extractors.ExchangeValueExtractor
import com.example.millionpubs.repositories.UserAccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class UserAccountServiceImpl @Autowired constructor(
    private val userAccountRepository: UserAccountRepository,
    private val exchangeValueExtractor: ExchangeValueExtractor
) : UserAccountService {
    companion object {
        private const val GET_EXCHANGE_RATE_URL = "https://api.nbp.pl/api/exchangerates/rates/c/usd/%s/?format=json"
        private const val NBP_EXCEPTION_MESSAGE = "Get exchange rate from NBP failed"
        private const val DATE_FORMAT = "yyyy-MM-dd"
        private const val DECIMAL_FORMAT = "#.##"
    }

    override fun getUserAccountBalanceInUSD(userId: String): Double? {
        val userAccountBalance = userAccountRepository.getUserAccountBalanceByUserName(userId) ?: return null

        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        val currentDate = LocalDate.now().minusDays(4).format(formatter);

        val json: String? =
            WebClient.create(String.format(GET_EXCHANGE_RATE_URL, currentDate)).get()
                .retrieve().bodyToMono(String::class.java).block()

        if (json.isNullOrBlank()) {
            throw NBPExchangeRateException(NBP_EXCEPTION_MESSAGE);
        }

        return roundToTwoDecimals(userAccountBalance * exchangeValueExtractor.extract(json))
    }

    private fun roundToTwoDecimals(balance: Double): Double {
        val df = DecimalFormat(DECIMAL_FORMAT)
        df.roundingMode = RoundingMode.FLOOR
        return df.format(balance).toDouble()
    }
}