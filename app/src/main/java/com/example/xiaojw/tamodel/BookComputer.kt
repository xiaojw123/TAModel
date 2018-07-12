package com.example.xiaojw.tamodel

import kotlin.math.log

class BookComputer(name:String):Computer(name),IUsb {
    companion object {

        fun use(){

        }
        var  a=10;
    }


    override fun charge() {
    }

    override fun debug() {
        super<Computer>.debug()
        super<IUsb>.debug()
    }



    override fun startSystem() {
        super.startSystem()
        APPLog.printDebug("fasdf")
    }


    fun  shutDown(){


    }



}