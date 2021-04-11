package com.electroducky.reporter.template

import com.electroducky.reporter.report.Report
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TemplateRendererServiceTest {

    @Test
    fun render() {
        val rendererService = TemplateRendererService("$", "$")

        assertEquals(
            Report("fdghjk \$fd ghjkdfs 10 gjksd"),
            rendererService.render(mapOf("test" to "10"), "fdghjk \\\$fd ghjkdfs $ test:int $ gjksd")
        )

        assertEquals(
            Report("fdghjk \$fd ghjkdfs10gjksd"),
            rendererService.render(mapOf("test" to "10"), "fdghjk \\\$fd ghjkdfs$ test:int \$gjksd")
        )

        assertThrows<IllegalArgumentException> {
            rendererService.render(mapOf("test" to "10"), "fdghjk \\\$fd ghjkdfs$ test:tryu \$gjksd")
        }

        assertThrows<IllegalStateException> {
            rendererService.render(mapOf("test" to "dgfhdgfh"), "fdghjk \\\$fd ghjkdfs$ test:int \$gjksd")
        }

    }
}