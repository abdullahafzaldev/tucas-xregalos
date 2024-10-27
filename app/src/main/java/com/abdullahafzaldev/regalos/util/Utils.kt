package com.abdullahafzaldev.regalos.util

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

class Utils(val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("pref",Context.MODE_PRIVATE)
    fun getCoins():Int{
        return sharedPreferences.getInt("coin",0)
    }
    fun setCoin(coin : Int){
        sharedPreferences.edit().putInt("coin",coin).apply()
    }

    //Lives
    fun getLive():Int{
        return sharedPreferences.getInt("live",3)
    }
    fun setLive(coin : Int){
        sharedPreferences.edit().putInt("live",coin).apply()
    }

    //Wait
    fun waitFor24Hour():Boolean{
        return sharedPreferences.getBoolean("waitFor24Hour",false)
    }
    fun waitFor24Hour(wait : Boolean){
        sharedPreferences.edit().putBoolean("waitFor24Hour",wait).apply()
    }

    //Rate us
    fun showRateUs():Boolean{
        return sharedPreferences.getBoolean("rate_us",true)
    }
    fun setRateUs(wait : Boolean){
        sharedPreferences.edit().putBoolean("rate_us",wait).apply()
    }


    //DATE
    fun getDate():Int{

        return sharedPreferences.getInt("date",0)
    }
    fun setDate(wait : Int){
        sharedPreferences.edit().putInt("date",wait).apply()
    }

    fun getTodayDate () : String = SimpleDateFormat("dd", Locale.getDefault()).format(Date())

    //Card Visit
    fun getCardVisit():Int{

        return sharedPreferences.getInt("card_visit",0)
    }
    fun setCardVisit(wait : Int){
        sharedPreferences.edit().putInt("card_visit",wait).apply()
    }

    //Web Visit
    fun getWebVisit():Int{

        return sharedPreferences.getInt("web_visit",0)
    }
    fun setWebVisit(wait : Int){
        sharedPreferences.edit().putInt("web_visit",wait).apply()
    }


    //Radeem
    fun isRedeemShown():Boolean{
        return sharedPreferences.getBoolean("redeem",false)
    }
    fun showRedeem(wait : Boolean){
        sharedPreferences.edit().putBoolean("redeem",wait).apply()
    }

    //CodeGenerated
    fun isCodeGenerated():Boolean{
        return sharedPreferences.getBoolean("code_gen",false)
    }
    fun codeGenerated(wait : Boolean){
        sharedPreferences.edit().putBoolean("code_gen",wait).apply()
    }


    //Code
    fun getCode():String?{

        return sharedPreferences.getString("code","")
    }
    fun setCode(wait : String){
        sharedPreferences.edit().putString("code",wait).apply()
    }

    //AppRunForFirstTime
    fun isAppRunForFirstTime():Boolean{
        return sharedPreferences.getBoolean("first_run",false)
    }
    fun setAppRunForFirstTime(wait : Boolean){
        sharedPreferences.edit().putBoolean("first_run",wait).apply()
    }


    //DATE
    fun getFreeCoinDate():Int{

        return sharedPreferences.getInt("free_coin_date",0)
    }
    fun setFreeCoinDate(wait : Int){
        sharedPreferences.edit().putInt("free_coin_date",wait).apply()
    }


    //AppRunForFirstTime
    fun isFreeCoinReceived():Boolean{
        return sharedPreferences.getBoolean("free_coin_receive",false)
    }
    fun setFreeCoinReceived(wait : Boolean){
        sharedPreferences.edit().putBoolean("free_coin_receive",wait).apply()
    }


}