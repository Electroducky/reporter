package com.electroducky.reporter.template

fun String.findNextSeparator(separator: String, startIndex: Int): Int {
    var nextSeparator = -1
    var currentStartIndex = startIndex

    while (nextSeparator == -1) {
        nextSeparator = this.indexOf(separator, currentStartIndex) // TODO check +1
        if (nextSeparator == -1) {
            return -1
        }

        if (nextSeparator > 0) {
            val escapeSymbolPresent = this[nextSeparator - 1] == '\\'
            if (escapeSymbolPresent) {
                currentStartIndex = nextSeparator + 1 // TODO check +1
                nextSeparator = -1
            }
        }
    }

    return nextSeparator
}

fun String.findNextPlaceholder(openingSeparator: String, closingSeparator: String, startIndex: Int): Pair<Int, Int> {
    val nextOpeningSeparator = this.findNextSeparator(openingSeparator, startIndex)
    if (nextOpeningSeparator == -1) {
        return -1 to -1
    }

    val nextClosingSeparator = this.findNextSeparator(closingSeparator, nextOpeningSeparator + 1)
    if (nextClosingSeparator == -1) {
        throw IllegalStateException("Placeholder parse error: placeholder on $nextOpeningSeparator opened but never closed")
    }

    return nextOpeningSeparator to nextClosingSeparator
}

fun String.unescape(escaped: String): String = this.replace("\\$escaped", escaped)
