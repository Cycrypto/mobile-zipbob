package com.example.hansotbob.exception

sealed class MealkitException(message: String) : Exception(message) {
    class EmptyFormException: MealkitException("모든 입력칸을 입력하세요")
}
