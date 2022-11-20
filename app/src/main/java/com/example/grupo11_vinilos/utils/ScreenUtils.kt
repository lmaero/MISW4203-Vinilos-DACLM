package com.example.grupo11_vinilos.utils

import android.app.Instrumentation
import android.os.SystemClock
import android.view.MotionEvent
import androidx.test.platform.app.InstrumentationRegistry

class ScreenUtils {
    companion object {
        fun swiper(start: Int, end: Int, delay: Int) {
            val downTime = SystemClock.uptimeMillis()
            var eventTime = SystemClock.uptimeMillis()
            val inst: Instrumentation = InstrumentationRegistry.getInstrumentation()
            var event = MotionEvent.obtain(
                downTime,
                eventTime,
                MotionEvent.ACTION_DOWN,
                500f,
                start.toFloat(),
                0
            )
            inst.sendPointerSync(event)
            eventTime = SystemClock.uptimeMillis() + delay
            event =
                MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_MOVE, 500f, end.toFloat(), 0)
            inst.sendPointerSync(event)
            event =
                MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 500f, end.toFloat(), 0)
            inst.sendPointerSync(event)
            SystemClock.sleep(2000) //The wait is important to scroll
        }
    }
}