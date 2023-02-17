package mymyeong.batch.batchtest.support

import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter

class RetrySampleItemWriter<T>(
    private var counter: Int = 1
) : ItemWriter<T> {
    override fun write(chunk: Chunk<out T>) {
        val current: Int = counter
        counter += chunk.size()
        check(!(current < 3 && (counter >= 2 || counter >= 3))) { "Temporary error" }
    }
}