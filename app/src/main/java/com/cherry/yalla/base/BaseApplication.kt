package com.application.base

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.cherry.yalla.utility.ConnectivityReceiver


class BaseApplication : Application() {
//    var myAppComponent: AppComponent? = null
    lateinit var mInstance: BaseApplication

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        myAppComponent = DaggerAppComponent
//            .builder()
//            .sharedPreferenceModule(SharedPreferenceModule(applicationContext))
//            .build()
//        FirebaseApp.initializeApp(applicationContext)
    }

    @Synchronized
    fun getInstance(): BaseApplication {
        return mInstance
    }

//    fun getAppComponent(): AppComponent? {
//        return myAppComponent
//    }

    fun setConnectivityListener(listener: ConnectivityReceiver.ConnectivityReceiverListener?) {
        ConnectivityReceiver.connectivityReceiverListener = listener
    }
}