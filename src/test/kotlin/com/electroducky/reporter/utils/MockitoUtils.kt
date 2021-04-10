package com.electroducky.reporter.utils

import org.mockito.Mockito

inline fun <reified T> anyNonNull(): T = Mockito.any<T>(T::class.java)
