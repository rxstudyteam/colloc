package android.log

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.PendingIntent
import android.app.PendingIntent.CanceledException
import android.content.Intent
import android.os.Build
import android.os.Bundle

class LActivity : android.support.v7.app.AppCompatActivity() {
    override fun sendBroadcast(intent: Intent) {
        Log.sendBroadcast(javaClass, intent)
        super.sendBroadcast(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.onCreate(javaClass)
        super.onCreate(savedInstanceState)
    }

    override fun onNewIntent(intent: Intent) {
        Log.onNewIntent(javaClass)
        super.onNewIntent(intent)
    }

    override fun onDestroy() {
        Log.onDestroy(javaClass)
        super.onDestroy()
    }

    //    @Override
    //    protected void onStart() {
    //        Log.onStart(getClass());
    //        super.onStart();
    //    }
    //
    //    @Override
    //    protected void onStop() {
    //        Log.onStop(getClass());
    //        super.onStop();
    //    }
    //
    //    @Override
    //    protected void onRestart() {
    //        Log.onRestart(getClass());
    //        super.onRestart();
    //    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun startActivities(intents: Array<Intent>) {
        Log.startActivities(javaClass, intents)
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            try {
                intents[1].flags = Intent.FLAG_ACTIVITY_NEW_TASK
                PendingIntent.getActivities(this, 0, intents, PendingIntent.FLAG_ONE_SHOT).send()
            } catch (e: CanceledException) {
                e.printStackTrace()
            }

        } else
            super.startActivities(intents)
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
            Log.startActivityForResult(javaClass, intent, requestCode)
        super.startActivityForResult(intent, requestCode)
    }

    @SuppressLint("RestrictedApi")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun startActivityForResult(intent: Intent, requestCode: Int, options: Bundle?) {
        Log.startActivityForResult(javaClass, intent, requestCode, options)
        super.startActivityForResult(intent, requestCode, options)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode shr 16 == 0)
            Log.onActivityResult(javaClass, requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)
    }
}
