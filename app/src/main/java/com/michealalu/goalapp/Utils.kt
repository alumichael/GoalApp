package com.michealalu.goalapp

import android.app.Activity
import android.app.Dialog
import android.content.Context
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


