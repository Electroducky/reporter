package com.electroducky.reporter.template

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class TextUtilsKtTest {

    @Test
    fun findNextSeparatorTest() {
        assertEquals(-1, "adsfjhadskgjkfhdgkjlnsdjkgjlkfjdlsghjsfdklg".findNextSeparator("$", 0))
        assertEquals(0, "\$adsfjhadskgjkfhdgkjlnsdjkgjlkfjdlsghjsfdklg".findNextSeparator("$", 0))
        assertEquals(10, "adsfjhadsk\$gjkfhdgkjlnsdjkgjlkfjdlsghjsfdklg".findNextSeparator("$", 0))
        assertEquals(-1, "\\\$adsfjhadskgjkfhdgkjlnsdjkgjlkfjdlsghjsfdklg".findNextSeparator("$", 0))
        assertEquals(-1, "adsfjhadskgjkfhdgkjlnsd\\\$jkgjlkfjdlsghjsfdklg".findNextSeparator("$", 0))
        assertEquals(36, "adsfjhads\\\$kgjkfhdgkjlnsdjkgjlkfjdls\$ghjsfdklg".findNextSeparator("$", 0))
        assertEquals(45, "adsfjhadskgjkfhdgkjlnsd\\\$jkgjlkfjdlsghjsfdklg\$".findNextSeparator("$", 0))
        assertEquals(23, "adsfjhadskgjkfhdgkjlnsd\$\$\$jkgjlkfjdlsghjsfdklg".findNextSeparator("$", 0))
    }

    @Test
    fun findNextPlaceholderTest() {
        assertEquals(0 to 5, "\$test\$".findNextPlaceholder("$", "$", 0))
        assertEquals(0 to 7, "$ test $".findNextPlaceholder("$", "$", 0))
        assertEquals(11 to 18, "dsafsadfgj $ test $ fhjkghfdjkg".findNextPlaceholder("$", "$", 0))
        assertEquals(0 to 7, "$ test $\$ test \$".findNextPlaceholder("$", "$", 0))
        assertEquals(8 to 15, "$ test $\$ test \$\$ test \$".findNextPlaceholder("$", "$", 8))
        assertEquals(26 to 33, "dsafsadfgj $ test $ fhjkgh\$ test \$fdjkg".findNextPlaceholder("$", "$", 23))
        assertThrows<IllegalStateException> {
            "dsafsadfgj $ test $ fhjkgh\$ test:int fdjkg".findNextPlaceholder(
                "$",
                "$",
                23
            )
        }
    }

    @Test
    fun unescapeTest() {
        assertEquals("dafhkasdgfhj\$", "dafhkasdgfhj\\$".unescape("$"))
        assertEquals("$", "\\$".unescape("$"))
        assertEquals("$$$", "\\$\\\$\\\$".unescape("$"))
    }
}