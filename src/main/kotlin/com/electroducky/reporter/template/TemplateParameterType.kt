package com.electroducky.reporter.template

enum class TemplateParameterType(val validate: (String) -> Boolean) {
    string({ true; }),
    int({ validateNoException { it.toInt() } }),
    boolean({ validateNoException { it.toBoolean() } }),
    float({ validateNoException { it.toDouble() } });

    companion object {
        private fun validateNoException(validator: () -> Unit) = try {
            validator()
            true
        } catch (e: Exception) {
            false
        }
    }
}