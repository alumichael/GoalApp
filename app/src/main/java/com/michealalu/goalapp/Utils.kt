package com.michealalu.goalapp

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.Window
import android.widget.Toast
import com.michealalu.goalapp.data.local.ResourceDb
import com.michealalu.goalapp.data.network.Resource

fun Activity.toast(text: String, context: Context) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun Activity.progressBar(dialogTransparent :Dialog,progressView: View ){
    dialogTransparent.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialogTransparent.window?.setBackgroundDrawableResource(
        R.color.colorBlackTransparent)
    dialogTransparent.setContentView(progressView)
}

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    startActivity(Intent(this, activity))
}

fun <A : Activity> Activity.StartNewActivityWithFlag(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun <A : Activity> Activity.startNewActivityByID(activity: Class<A>, content: String) {
    Intent(this, activity).also {
        it.putExtra("CONTENT", content)
        startActivity(it)
    }
}

fun Activity.handleError(
    resource: Resource.Failure?=null, optional_msg: String?=null, context: Context,
    dialogTransparent: Dialog?=null,
    resourcedb: ResourceDb.Failure?=null
) {
    dialogTransparent?.dismiss()
    try {
        if(resource!=null){
            resource.errorBody?.let { msg -> toast(msg,context) }
        }else if(resourcedb!=null){
            resourcedb.errorBody?.let { msg -> toast(msg,context) }
        }else{
            if(optional_msg!=null){
                toast(optional_msg,context)
            }else{
                toast("Sorry, something went wrong",context)
            }

        }

    } catch (e: Exception) {
        e.printStackTrace()
        toast("Sorry, something went wrong", context)
    }
}

fun  Activity.isOnline(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val n = cm.activeNetwork
        if (n != null) {
            val nc = cm.getNetworkCapabilities(n)
            //It will check for both wifi and cellular network
            return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                NetworkCapabilities.TRANSPORT_WIFI)
        }
        return false
    } else {
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}


