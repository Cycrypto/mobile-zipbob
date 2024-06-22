package com.example.hansotbob.exception

sealed class MealkitException(message: String) : Exception(message) {
    class EmptyFormException: MealkitException("모든 빈칸을 입력하세요")

    class PriceIntException: MealkitException("가격은 숫자를 입력하세요")
}

