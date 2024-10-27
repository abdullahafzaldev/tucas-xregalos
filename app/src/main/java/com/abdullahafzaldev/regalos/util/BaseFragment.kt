package com.abdullahafzaldev.regalos.util

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    private val loading = Loading(requireActivity().applicationContext)
    fun showLoading(){
        loading.showLoading()
    }
fun hideLoading(){
    loading.hideLoading()
}

}