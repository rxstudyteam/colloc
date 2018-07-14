package com.eastandroid.lib.permission

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.WindowManager
import java.util.*
import kotlin.collections.ArrayList

class PermissionChecker : android.support.v7.app.AppCompatActivity() {

    private var mContext: Context? = null
    private var mActivity: Activity? = null

    private var mRequestedPermissions: ArrayList<String>? = null//최초 물어본 권한
    private var mDeniedPermissions = ArrayList<String>()//권한이 없는것들
    private var mRequestPermissions = ArrayList<String>()//권한을 물어볼대상

    private var mRequestMessage: CharSequence? = null
    private var mDenyMessage: CharSequence? = null

    private var step_request: Boolean = false
    private var mDlg: AlertDialog? = null

    interface EXTRA {
        companion object {
            val PERMISSIONS = "PERMISSIONS"
            val REQUEST_MESSAGE = "REQUEST_MESSAGE"
            val DENY_MESSAGE = "DENY_MESSAGE"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mActivity = this
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        parseExtra()
        load()

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        parseExtra()
        load()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mDlg != null && mDlg!!.isShowing)
            mDlg!!.cancel()
    }

    private fun parseExtra() {
        val intent = intent
        mRequestedPermissions = intent.getStringArrayListExtra(EXTRA.PERMISSIONS)
        mRequestMessage = intent.getCharSequenceExtra(EXTRA.REQUEST_MESSAGE)
        mDenyMessage = intent.getCharSequenceExtra(EXTRA.DENY_MESSAGE)
    }

    private fun load() {
        val requestedPermissions = mRequestedPermissions

        val deniedPermissions = ArrayList<String>()
        for (permission in requestedPermissions!!) {
            if (PackageManager.PERMISSION_DENIED == ContextCompat.checkSelfPermission(mContext!!, permission))
                deniedPermissions.add(permission)
        }
        if (deniedPermissions.size <= 0) {
            //android.util.Log..("PERMISSIONS", "이미 다 승인됨");
            fireGranted()
            return
        }
        mDeniedPermissions = deniedPermissions
        mRequestPermissions = deniedPermissions
        //android.util.Log..("PERMISSION", "" + step_request);
        if (!step_request) {
            //android.util.Log..("PERMISSIONS", "묻어봄");
            requestPermissions()
            return
        }
        //android.util.Log..("PERMISSIONS", "거부됨");
        denyPermissions()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQ_REQUEST) {
            load()
        }
    }

    private fun requestPermissions() {
        val message = mRequestMessage
        if (message == null || message.length <= 0) {
            ActivityCompat.requestPermissions(mActivity!!, mRequestPermissions.toTypedArray<String>(), REQ_REQUEST)
            //android.util.Log..("PERMISSION", "" + step_request);
            step_request = true
            return
        }

        mDlg = AlertDialog.Builder(mContext!!)//
                .setMessage(message)//
                .setOnCancelListener { fireDenied() }//
                .setNegativeButton("거부") { _, _ -> fireDenied() }//
                .setPositiveButton("설정") { _, _ ->
                    ActivityCompat.requestPermissions(mActivity!!, mRequestPermissions.toTypedArray(), REQ_REQUEST)
                    step_request = true
                }//
                .show()
        mDlg!!.setCanceledOnTouchOutside(false)
    }

    fun denyPermissions() {
        //		Log.l();
        val context = mContext
        val message = mDenyMessage

        if (message == null || message.length <= 0) {
            fireDenied()
            return
        }

        mDlg = AlertDialog.Builder(context!!)//
                .setMessage(message)//
                .setOnCancelListener { dialog -> fireDenied() }//
                .setPositiveButton("거부") { dialogInterface, i -> fireDenied() }//
                .setNegativeButton("설정") { dialogInterface, i ->
                    try {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.parse("package:" + context.packageName))
                        startActivityForResult(intent, REQ_SETTING)
                    } catch (e: ActivityNotFoundException) {
                        val intent = Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS)
                        startActivityForResult(intent, REQ_SETTING)
                    }
                }//
                .show()
        mDlg!!.setCanceledOnTouchOutside(false)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_SETTING) {
            load()
            return
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun fireGranted() {
        PermissionObserver.instance.notifyObservers(ArrayList<String>())
        finish()
        overridePendingTransition(0, 0)
    }

    private fun fireDenied() {
        PermissionObserver.instance.notifyObservers(mDeniedPermissions)
        finish()
        overridePendingTransition(0, 0)
    }

    companion object {

        private val REQ_REQUEST = 10
        private val REQ_SETTING = 20
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //권한 있는지
        /** instead IS_PERMISSIONS  */
        @Deprecated("")
        fun CHECK_PERMISSIONS(context: Context, vararg permissions: String): Boolean {
            return IS_PERMISSIONS(context, *permissions)
        }

        /** instead IS_PERMISSIONS  */
        @Deprecated("")
        fun CHECK_PERMISSIONS(context: Context, permissions: List<String>): Boolean {
            return IS_PERMISSIONS(context, permissions)
        }

        /** instead IS_PERMISSIONS  */
        @Deprecated("")
        fun CHECK_PERMISSIONS(context: Context, permissions: ArrayList<String>): Boolean {
            return IS_PERMISSIONS(context, permissions)
        }

        fun IS_PERMISSIONS(context: Context, vararg permissions: String): Boolean {
            return IS_PERMISSIONS(context, Arrays.asList(*permissions))
        }

        fun IS_PERMISSIONS(context: Context, permissions: List<String>?): Boolean {
            if (permissions == null || permissions.size <= 0)
                return true

            for (permission in permissions) {
                if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context, permission))
                    return false
            }
            return true
        }
    }
}
