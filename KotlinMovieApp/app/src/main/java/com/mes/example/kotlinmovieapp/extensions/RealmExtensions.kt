package com.mes.example.kotlinmovieapp.extensions

import com.vicpin.krealmextensions.query
import com.vicpin.krealmextensions.save
import io.realm.RealmModel
import io.realm.RealmQuery

typealias Query<T> = RealmQuery<T>.() -> Unit

inline fun <reified T : RealmModel> T.queryAndUpdateAll(noinline query: Query<T>, noinline modify: (T) -> Unit) {
    query(query).let {
        for (item in it) {
            modify(item)
            item.save()
        }
    }
}