package com.eastandroid.lib.permission

import java.util.*

class PermissionObserver private constructor() : Observable() {

    override fun notifyObservers(arg: Any) {
        setChanged()
        super.notifyObservers(arg)
    }

    companion object {
        val instance = PermissionObserver()
    }
}
