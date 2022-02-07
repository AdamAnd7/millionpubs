package com.example.millionpubs.json.extractors

import com.google.gson.JsonSyntaxException
import org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ExchangeValueExtractorTest {
    private val exchangeValueExtractor: ExchangeValueExtractor = ExchangeValueExtractor()

    @ParameterizedTest
    @MethodSource("provideData")
    fun exchangeValueShouldReturnProperDataWhenJsonIsCorrect(json: String, expectedResult: Double) {
        //when
        val actualResult: Double = exchangeValueExtractor.extract(json)

        //then
        assertThat(actualResult).isEqualTo(expectedResult)
    }


    @Test
    fun exchangeValueShouldThrowJsonSyntaxExceptionWhenJsonIsIncorrect() {
        assertThrows<JsonSyntaxException> {
            exchangeValueExtractor.extract("wrong JSON")
        }
    }

    @Test
    fun exchangeValueShouldThrowNumberFormatExceptionWhenValueIsNotNumber() {
        assertThrows<NumberFormatException> {
            exchangeValueExtractor.extract("{\"table\":\"C\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"064/C/NBP/2016\",\"effectiveDate\":\"2016-04-04\",\"bid\":3.6929,\"ask\":NotNumberValue}]}")
        }
    }

    companion object {
        @JvmStatic
        fun provideData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(provideJson(3.12), 3.12),
                Arguments.of(provideJson(2.0), 2.0),
                Arguments.of(provideJson(0.5), 0.5)
            )
        }

        private fun provideJson(exchangeValue: Double): String {
            return String.format(
                "{\"table\":\"C\",\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"rates\":[{\"no\":\"064/C/NBP/2016\",\"effectiveDate\":\"2016-04-04\",\"bid\":3.6929,\"ask\":%s}]}",
                exchangeValue
            )
        }
    }


}