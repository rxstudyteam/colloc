package com.eastandroid.lib.permission

import java.util.*

object PermissionObserver : Observable() {
    override fun notifyObservers(arg: Any) {
        setChanged()
        super.notifyObservers(arg)
    }
}
