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
            rendererService.render(mapOf("hgjk" to "10"), "fdghjk \\\$fd ghjkdfs $ hgjk:int $ gjksd")
        )

        assertEquals(
            Report("fdghjk \$fd ghjkdfs10gjksd"),
            rendererService.render(mapOf("hgjk" to "10"), "fdghjk \\\$fd ghjkdfs$ hgjk:int \$gjksd")
        )

        assertThrows<IllegalArgumentException> {
            rendererService.render(mapOf("hgjk" to "10"), "fdghjk \\\$fd ghjkdfs$ hgjk:tryu \$gjksd")
        }

        assertThrows<IllegalStateException> {
            rendererService.render(mapOf("hgjk" to "dgfhdgfh"), "fdghjk \\\$fd ghjkdfs$ hgjk:int \$gjksd")
        }

    }
}