package com.electroducky.reporter.sender

import com.electroducky.reporter.report.Report
import com.electroducky.reporter.utils.anyNonNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

internal class DispatcherServiceTest {

    @Test
    fun coordinate() {
        val sender = mock(SenderService::class.java)
        `when`(sender.type).thenReturn("Http")
        val dispatcherService = DispatcherService(listOf(sender))

        dispatcherService.dispatch(Report(""), listOf(Recipient("Http", "test.com")))

        verify(sender).send(anyNonNull(), anyNonNull())
    }
}