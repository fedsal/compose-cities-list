package com.example.composecitylist.ui.utils

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val main: CoroutineDispatcher

    val io: CoroutineDispatcher

    val default: CoroutineDispatcher
}

class DefaultDispatcherProvider : DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = kotlinx.coroutines.Dispatchers.Main

    override val io: CoroutineDispatcher
        get() = kotlinx.coroutines.Dispatchers.IO

    override val default: CoroutineDispatcher
        get() = kotlinx.coroutines.Dispatchers.Default
}