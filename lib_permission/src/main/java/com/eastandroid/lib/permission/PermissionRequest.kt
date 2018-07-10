package com.eastandroid.lib.permission

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat
import java.util.*

class PermissionRequest : Observer {

    protected var mContext: Context
    var requestPermissions: ArrayList<String>
        protected set

    protected var mOnPermissionGrantedListener: OnPermissionGrantedListener? = null
    protected var mOnPermissionDeniedListener: OnPermissionDeniedListener? = null
    protected var mRequestMessage: CharSequence? = null
    protected var mDenyMessage: CharSequence? = null

    interface OnPermissionGrantedListener {
        fun onGranted()
    }

    interface OnPermissionDeniedListener {
        fun onDenied(request: PermissionRequest, deniedPermissions: ArrayList<String>)
    }

    constructor(context: Context, permissions: ArrayList<String>) {
        mContext = context
        requestPermissions = permissions
    }

    constructor(context: Context, permissions: ArrayList<String>, onPermissionGrantedListener: OnPermissionGrantedListener, onPermissionDeniedListener: OnPermissionDeniedListener, rquestMessage: String, denyMessage: String) {
        mContext = context
        requestPermissions = permissions
        mOnPermissionGrantedListener = onPermissionGrantedListener
        mOnPermissionDeniedListener = onPermissionDeniedListener
        mRequestMessage = rquestMessage
        mDenyMessage = denyMessage
    }

    override fun update(observer: Observable, data: Any) {
        val deniedPermissions = data as ArrayList<String>

        //android.util.Log..("PERMISSION", "승인상태1:" + deniedPermissions);
        val grantedListener = mOnPermissionGrantedListener
        val granted = deniedPermissions == null || deniedPermissions.size <= 0
        //android.util.Log..("PERMISSION", "승인상태2:" + granted);
        if (grantedListener != null && granted)
            grantedListener.onGranted()

        val deniedListener = mOnPermissionDeniedListener
        if (deniedListener != null && !granted)
            deniedListener.onDenied(this, deniedPermissions)

        PermissionObserver.instance.deleteObserver(this)
    }

    fun run(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return false

        val deniedPermissions = ArrayList<String>()
        for (permission in requestPermissions) {
            if (PackageManager.PERMISSION_DENIED == ContextCompat.checkSelfPermission(mContext, permission))
                deniedPermissions.add(permission)
        }
        if (deniedPermissions.size <= 0)
            return false

        PermissionObserver.instance.addObserver(this)
        val intent = Intent(mContext, PermissionChecker::class.java)
        intent.putExtra(PermissionChecker.EXTRA.PERMISSIONS, requestPermissions)
        intent.putExtra(PermissionChecker.EXTRA.REQUEST_MESSAGE, mRequestMessage)
        intent.putExtra(PermissionChecker.EXTRA.DENY_MESSAGE, mDenyMessage)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mContext.startActivity(intent)
        return true
    }

    class Builder(context: Context, permissions: ArrayList<String>) {
        internal var p: PermissionRequest

        init {
            p = PermissionRequest(context, permissions)
        }

        constructor(context: Context, vararg permissions: String) : this(context, ArrayList<String>(Arrays.asList<String>(*permissions))) {}

        fun setOnPermissionGrantedListener(onPermissionGrantedListener: OnPermissionGrantedListener): Builder {
            p.mOnPermissionGrantedListener = onPermissionGrantedListener
            return this
        }

        fun setOnPermissionDeniedListener(onPermissionDeniedListener: OnPermissionDeniedListener): Builder {
            p.mOnPermissionDeniedListener = onPermissionDeniedListener
            return this
        }

        fun setRequestMessage(requestMessage: CharSequence): Builder {
            p.mRequestMessage = requestMessage
            return this
        }

        fun setDenyMessage(denyMessage: CharSequence): Builder {
            p.mDenyMessage = denyMessage
            return this
        }

        fun run(): Boolean {
            return p.run()
        }
    }
}
