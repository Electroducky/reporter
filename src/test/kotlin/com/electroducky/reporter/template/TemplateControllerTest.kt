package com.electroducky.reporter.template

import com.electroducky.reporter.sender.Recipient
import com.electroducky.reporter.utils.anyNonNull
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doNothing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*


@WebMvcTest(TemplateController::class)
internal class TemplateControllerTest(
    @Autowired
    private val mapper: ObjectMapper,
    @Autowired
    private val mockMvc: MockMvc
) {
    @MockBean
    private lateinit var templateService: TemplateService

    private val templates = mutableListOf<Template>()

    @BeforeEach
    fun setUp() {
        templates.add(
            Template(
                "test1", "some text 1",
                listOf(Recipient("http", "test1.com"))
            )
        )
        templates.add(
            Template(
                "test2", "some text 2",
                listOf(Recipient("http", "test2.com"))
            )
        )
    }

    @Test
    fun get() {
        `when`(templateService.findById(anyNonNull())).thenReturn(templates[0])

        mockMvc.get("/api/v1/template/{id}", templates[0].templateId) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json("{}") }
        }
    }

    @Test
    fun getAll() {
        `when`(templateService.findAll()).thenReturn(templates)

        mockMvc.get("/api/v1/template") {
            contentType = MediaType.APPLICATION_JSON
            content = (mapper.writeValueAsString(templates))
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json((mapper.writeValueAsString(templates))) }
        }
    }


    @Test
    fun create() {
        `when`(templateService.create(anyNonNull())).thenReturn(templates[0])

        mockMvc.post("/api/v1/template") {
            contentType = MediaType.APPLICATION_JSON
            content = (mapper.writeValueAsString(templates[0]))
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json((mapper.writeValueAsString(templates[0]))) }
        }
    }

    @Test
    fun update() {
        `when`(templateService.update(anyNonNull())).thenReturn(templates[0])

        mockMvc.put("/api/v1/template") {
            contentType = MediaType.APPLICATION_JSON
            content = (mapper.writeValueAsString(templates[0]))
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json((mapper.writeValueAsString(templates[0]))) }
        }
    }

    @Test
    fun delete() {
        doNothing().`when`(templateService).delete(anyNonNull())

        mockMvc.delete("/api/v1/template/{id}", templates[0].templateId) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }
    }
}