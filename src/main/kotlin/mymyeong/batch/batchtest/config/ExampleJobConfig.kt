package mymyeong.batch.batchtest.config

import mymyeong.batch.batchtest.domain.Trade
import mymyeong.batch.batchtest.domain.internal.GeneratingTradeItemReader
import mymyeong.batch.batchtest.support.RetrySampleItemWriter
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class ExampleJobConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager
) {


    @Bean
    fun retrySample(): Job {
        return JobBuilder("retrySample", jobRepository)
            .start(step())
            .build()
    }

    private fun step(): Step {
        return StepBuilder("step", jobRepository)
            .chunk<Trade, Any>(1, transactionManager)
            .reader(reader())
            .writer(writer())
            .faultTolerant()
            .retry(Exception::class.java)
            .retryLimit(3)
            .build()
    }

    private fun reader(): ItemReader<Trade> {
       return GeneratingTradeItemReader(
           limit = 10
       )
    }

    private fun writer(): ItemWriter<in Any> {
        return RetrySampleItemWriter()
    }
}
