package mymyeong.batch.batchtest.domain.internal

import mymyeong.batch.batchtest.domain.Trade
import org.springframework.batch.item.ItemReader
import java.math.BigDecimal

class GeneratingTradeItemReader(
    private val limit: Int = 1,
    private var counter: Int = 1,
): ItemReader<Trade> {
    override fun read(): Trade? {
        return if (counter < limit) {
            counter++
            Trade(
                isin = "isin$counter",
                quantity = counter.toLong(),
                price = BigDecimal(counter),
                customer = "customer$counter"
            )
        } else {
            null
        }
    }
}