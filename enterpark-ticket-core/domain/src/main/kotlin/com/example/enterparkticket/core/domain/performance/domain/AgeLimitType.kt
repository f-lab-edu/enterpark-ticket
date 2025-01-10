package com.example.enterparkticket.core.domain.performance.domain

enum class AgeLimitType(val value: String, val age: Int) {
    ALL("전체 관람가", 0),
    AGE_12("12세 이상", 12),
    AGE_15("15세 이상", 15),
    AGE_19("19세 이상", 19),
}
