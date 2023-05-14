package com.pm.lovecalendar_kmm.domain.util

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

actual open class CommonStateFlow<T> actual constructor(
    private val flow: StateFlow<T>
): CommonFlow<T>(flow), StateFlow<T> {

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        flow.collect(collector)
    }

    override val replayCache: List<T>
        get() = flow.replayCache
    override val value: T
        get() = flow.value
}