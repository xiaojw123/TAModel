package com.example.xiaojw.tamodel

import android.util.Log

class APPLog {

    companion object {
        @JvmStatic
          fun  printDebug(message:String){

            Log.d("xjw",message)
        }

    }


}