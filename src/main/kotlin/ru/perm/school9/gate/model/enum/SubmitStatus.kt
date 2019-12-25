package ru.perm.school9.gate.model.enum

enum class SubmitStatus {
    TESTER_INACCESSIBLE,
    AWAITING_TESTING,
    TESTING,
    WRONG_ANSWER,
    TIME_LIMIT,
    MEMORY_LIMIT,
    COMPILATION_ERROR,
}