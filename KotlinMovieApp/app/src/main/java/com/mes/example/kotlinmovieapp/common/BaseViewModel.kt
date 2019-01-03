package com.mes.example.kotlinmovieapp.common

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.databinding.BaseObservable
import java.io.Serializable

open class BaseViewModel: BaseObservable(), LifecycleObserver, Serializable {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {}
}