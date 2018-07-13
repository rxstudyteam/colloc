package com.eastandroid.lib.permission

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat
import java.util.*

class PermissionRequest(context: Context
                        , permissions: List<String>
                        , onPermissionGrantedListener: OnPermissionGrantedListener? = null
                        , onPermissionDeniedListener: OnPermissionDeniedListener? = null
                        , rquestMessage: String? = null
                        , denyMessage: String? = null) : Observer {

    private val mContext: Context = context
    private val requestPermissions: List<String> = permissions
    private val mOnPermissionGrantedListener: OnPermissionGrantedListener? = onPermissionGrantedListener
    private val mOnPermissionDeniedListener: OnPermissionDeniedListener? = onPermissionDeniedListener
    private val mRequestMessage: CharSequence? = rquestMessage
    private val mDenyMessage: CharSequence? = denyMessage

    interface OnPermissionGrantedListener {
        fun onGranted()
    }

    interface OnPermissionDeniedListener {
        fun onDenied(request: PermissionRequest, deniedPermissions: List<String>)
    }

    override fun update(observer: Observable, data: Any) {
        @Suppress("UNCHECKED_CAST")
        val deniedPermissions = data as List<String>
        android.util.Log.e("PERMISSION", "승인상태1:$deniedPermissions")

        if (deniedPermissions.isEmpty())
            mOnPermissionGrantedListener?.onGranted()
        else
            mOnPermissionDeniedListener?.onDenied(this, deniedPermissions)

        PermissionObserver.instance.deleteObserver(this)
    }


    fun run() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            mOnPermissionGrantedListener?.onGranted()

        val deniedPermissions = ArrayList<String>()
        for (permission in requestPermissions) {
            if (PackageManager.PERMISSION_DENIED == ContextCompat.checkSelfPermission(mContext, permission))
                deniedPermissions.add(permission)
        }
        if (deniedPermissions.size <= 0)
            mOnPermissionGrantedListener?.onGranted()

        PermissionObserver.instance.addObserver(this)
        val intent = Intent(mContext, PermissionChecker::class.java)
        intent.putExtra(PermissionChecker.EXTRA.PERMISSIONS, requestPermissions as ArrayList<String>)
        intent.putExtra(PermissionChecker.EXTRA.REQUEST_MESSAGE, mRequestMessage)
        intent.putExtra(PermissionChecker.EXTRA.DENY_MESSAGE, mDenyMessage)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mContext.startActivity(intent)
    }
}
