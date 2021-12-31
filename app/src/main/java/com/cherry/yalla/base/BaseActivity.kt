package com.application.base

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cherry.yalla.R
import com.cherry.yalla.utility.ConnectivityReceiver
import com.cherry.yalla.utility.Util
import com.google.android.material.snackbar.Snackbar

public abstract class BaseActivity : AppCompatActivity(),
    ConnectivityReceiver.ConnectivityReceiverListener {

    interface OnRetryButtonClick {
        public fun onRetryClick();
    }

    protected abstract fun getContentView(): View
    protected abstract fun getRetryListener(): OnRetryButtonClick

    private var doubleBackToExitPressedOnce = false
    var activity: Activity? = null
    var mContext: Context? = null
    var connectivityReceiver: ConnectivityReceiver? = null
    var mProgressDialog: Dialog? = null
    var onRetryListener: OnRetryButtonClick? = null

    var PERMISSIONS = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    var PERMISSIONSWITHCAMERA = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

//    @Inject
//    lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(getContentView())
            onRetryListener = getRetryListener()
//            (this.applicationContext as BaseApplication).getAppComponent()!!.inject(this)
            mProgressDialog = Util.showProgressBar(this, getString(R.string.loading))
            activity = this
            mContext = this
            if (connectivityReceiver == null) {
                connectivityReceiver = ConnectivityReceiver()
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    open fun hideKeyBoard(activity: Activity) {
        try {
            Util.hideKeyboard(activity)
        } catch (e: java.lang.Exception) {
        }
    }

    protected open fun stopAnim() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog!!.isShowing()) {
                    mProgressDialog!!.dismiss()
                }
            }
        } catch (e: IllegalArgumentException) {
            Log.e("error: ", e.localizedMessage)
        }
    }

    protected open fun startAnim() {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = Util.showProgressBar(this!!, getString(R.string.loading))
            } else {
                if (!mProgressDialog!!.isShowing()) {
                    mProgressDialog!!.show()
                }
            }
        } catch (e: WindowManager.BadTokenException) {
            e.printStackTrace()
        }
    }

    open fun exitOnDoubleClick() {
        try {
            if (doubleBackToExitPressedOnce) {
                finish()
            }
            doubleBackToExitPressedOnce = true
            Toast.makeText(
                this,
                resources.getString(R.string.backPress_msg),
                Toast.LENGTH_SHORT
            ).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun visibleFragmentTag(): String {
        var tag = ""
        try {
            tag = supportFragmentManager.getBackStackEntryAt(
                supportFragmentManager
                    .backStackEntryCount - 1
            )
                .name!!
            Log.v("fragment_tag", tag)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return tag
    }

    interface OnFragmentSwitchListener {
        fun onFragmentSwitch(
            fragment: Fragment,
            addToBackStack: Boolean,
            backStackTag: String,
            replace: Boolean,
            screenName: String
        )
    }

    fun <T : View> Activity.bind(@IdRes res: Int): T {
        @Suppress("UNCHECKED_CAST")
        return findViewById(res) as T
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected) {
            try {
                showNoInternetConnection()
            } catch (e: java.lang.Exception) {
            }
        } else {
            hideNoInternetConnection()
        }
    }

    open fun showNoInternetConnection() {
        try {
            Snackbar.make(
                findViewById(android.R.id.content),
                "Sorry, you're offline",
                Snackbar.LENGTH_SHORT
            )
                .setAction("Retry", View.OnClickListener {
                    if (Util.isInternet(this)) {
                        if (onRetryListener != null) {
                            onRetryListener!!.onRetryClick()
                        }
                    } else {
                        showNoInternetConnection()
                    }
                }).show()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    open fun hideNoInternetConnection() {
    }

    open fun showServerError() {
        try {
            Snackbar.make(
                findViewById(android.R.id.content),
                "Something went wrong!",
                Snackbar.LENGTH_SHORT
            )
                .setAction("Retry", View.OnClickListener {
                    if (Util.isInternet(this)) {
                        if (onRetryListener != null) {
                            onRetryListener!!.onRetryClick()
                        }
                    } else {
                        showNoInternetConnection()
                    }
                }).show()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            if (connectivityReceiver != null) {
                BaseApplication().setConnectivityListener(this)
                registerReceiver(
                    connectivityReceiver,
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            } else {
                connectivityReceiver = ConnectivityReceiver()
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        try {
            if (connectivityReceiver != null) {
                unregisterReceiver(connectivityReceiver)
            }
        } catch (e: java.lang.Exception) {
        }
        super.onDestroy()
    }

    open fun moveToNext(intent: Intent, isFinish: Boolean) {
        if (isFinish) {
            startActivity(intent)
            finish()
        } else {
            startActivity(intent)
        }
    }
}