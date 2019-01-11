package com.mes.example.kotlinmovieapp.extensions

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent
import rx.Observable
import rx.Subscriber

class RecyclerViewPagingScrollOperator(val startPageWith: Int = 0):
        Observable.Operator<RecyclerViewScrollEvent, RecyclerViewScrollEvent> {
    override fun call(t: Subscriber<in RecyclerViewScrollEvent>?): Subscriber<in RecyclerViewScrollEvent> {
        return object : Subscriber<RecyclerViewScrollEvent>(t) {
            override fun onNext(scrollEvent: RecyclerViewScrollEvent?) {
                val recyclerView = scrollEvent?.view()
                recyclerView?.layoutManager?.let {
                    val visibleCount = it.childCount
                    val totalCount = it.itemCount
                    val firstVisibleItem = (it as? GridLayoutManager ?: it as? LinearLayoutManager)
                        ?.findFirstVisibleItemPosition() ?: -1
                    if (firstVisibleItem == -1) {
                        t?.onError(IllegalStateException("LayoutManager must be LinearLayoutManager or GridLayoutManager."))
                        return
                    }
                    if (visibleCount + firstVisibleItem > totalCount - 4) {
                        request(1)
                        return
                    }
                    t?.onNext(scrollEvent)
                    return
                }
                t?.onError(IllegalStateException("LayoutManager was null."))
            }

            override fun onCompleted() {
                t?.onCompleted()
            }

            override fun onError(e: Throwable?) {
                t?.onError(e)
            }
        }
    }
}

fun Observable<RecyclerViewScrollEvent>.mapToPagingEvents(startPagingWith: Int = 0) =
    lift(RecyclerViewPagingScrollOperator(startPagingWith))