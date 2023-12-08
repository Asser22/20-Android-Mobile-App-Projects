package com.asser.learningapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

object LiveDataTestUtil {
    fun <T> getValue(liveData: LiveData<T>): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = Observer<T> { o ->
            data = o
            latch.countDown()
        }
        liveData.observeForever(observer)
        try {
            if (!latch.await(2, TimeUnit.SECONDS)) {
                throw TimeoutException("LiveData value was never set.")
            }
        } finally {
            liveData.removeObserver(observer)
        }
        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}
