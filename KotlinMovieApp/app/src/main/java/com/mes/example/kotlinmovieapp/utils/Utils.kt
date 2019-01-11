package com.mes.example.kotlinmovieapp.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

fun getFragment(fragmentManager: FragmentManager, tag: String): Fragment? {
    var fragment = fragmentManager.findFragmentByTag(tag)
    return fragment
}