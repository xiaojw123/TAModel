package com.example.xiaojw.tamodel

interface IUsb {

    fun  charge(){
        APPLog.printDebug("ready charging....")
    }
    fun  debug(){
        APPLog.printDebug("debug enable....")
    }

}