package com.eastandroid.lib.permission

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import java.util.*

class PermissionRequest(context: Context, permissions: List<String>) : Observer {

    private val mContext: Context = context
    private val requestPermissions: List<String> = permissions
    private var mRequestMessage: CharSequence? = null
    private var mDenyMessage: CharSequence? = null

    private lateinit var onGranted: () -> Unit
    private var onDenied: ((requester: PermissionRequest, deniedPermissions: List<String>) -> Unit)? = null

    fun setGrentedListener(onGranted: () -> Unit): PermissionRequest {
        this.onGranted = onGranted
        return this
    }

    fun setDiniedListener(onDenied: (requester: PermissionRequest, deniedPermissions: List<String>) -> Unit): PermissionRequest {
        this.onDenied = onDenied
        return this
    }

    fun setRequestMessage(requestMessage: String): PermissionRequest {
        mRequestMessage = requestMessage
        return this
    }

    fun setDenyMessage(denyMessage: String): PermissionRequest {
        mDenyMessage = denyMessage
        return this
    }

    override fun update(observer: Observable, data: Any?) {
        @Suppress("UNCHECKED_CAST")
        val deniedPermissions = data as List<String>
        android.util.Log.e("PERMISSION", "승인상태1:$deniedPermissions")

        if (deniedPermissions.isEmpty())
            onGranted()
        else
            onDenied?.invoke(this, deniedPermissions)

        PermissionObserver.deleteObserver(this)
    }


    fun run() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onGranted()
            return
        }

        val deniedPermissions = ArrayList<String>()
        for (permission in requestPermissions) {
            if (PackageManager.PERMISSION_DENIED == ContextCompat.checkSelfPermission(mContext, permission))
                deniedPermissions.add(permission)
        }
        if (deniedPermissions.size <= 0) {
            onGranted()
            return
        }

        PermissionObserver.addObserver(this)
        val intent = Intent(mContext, PermissionChecker::class.java)
        intent.putExtra(PermissionChecker.EXTRA.PERMISSIONS, ArrayList<String>(requestPermissions))
        intent.putExtra(PermissionChecker.EXTRA.REQUEST_MESSAGE, mRequestMessage)
        intent.putExtra(PermissionChecker.EXTRA.DENY_MESSAGE, mDenyMessage)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mContext.startActivity(intent)
    }
}
