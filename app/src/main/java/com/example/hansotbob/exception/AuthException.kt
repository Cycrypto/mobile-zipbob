package com.example.hansotbob.exception

sealed class AuthException(message: String) : Exception(message) {
    class InvalidEmailFormatException : AuthException("유효하지 않은 이메일 형식입니다.")
    class WrongPasswordException : AuthException("잘못된 비밀번호입니다.")
    class UserNotFoundException : AuthException("이 이메일로 등록된 계정을 찾을 수 없습니다.")
    class UserDisabledException : AuthException("이 계정은 비활성화되었습니다. 관리자에게 문의하세요.")
    class NetworkErrorException : AuthException("네트워크 오류가 발생했습니다. 인터넷 연결을 확인하고 다시 시도하세요.")
    class UnknownErrorException(message: String) : AuthException(message)
    class EmptyFieldsException : AuthException("모든 필드를 입력하세요.")
    class PasswordMismatchException : AuthException("비밀번호가 일치하지 않습니다.")
}
