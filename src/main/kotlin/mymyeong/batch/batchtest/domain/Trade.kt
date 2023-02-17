package mymyeong.batch.batchtest.domain

import java.math.BigDecimal

data class Trade(
    val isin: String = "",

    val quantity: Long = 0,

    val price: BigDecimal = BigDecimal.ZERO,

    val customer: String = "",

    val id: Long = 0,

    val version: Long = 0
)