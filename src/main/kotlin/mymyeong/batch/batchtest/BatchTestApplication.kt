package mymyeong.batch.batchtest

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableBatchProcessing
@SpringBootApplication
class BatchTestApplication

fun main(args: Array<String>) {
    runApplication<BatchTestApplication>(*args)
}
