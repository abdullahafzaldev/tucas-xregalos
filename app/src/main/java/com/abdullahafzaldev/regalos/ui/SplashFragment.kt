package com.abdullahafzaldev.regalos.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abdullahafzaldev.regalos.R
import com.abdullahafzaldev.regalos.util.Utils
import kotlinx.android.synthetic.main.fragment_splash.*


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        val utils = Utils(requireContext())
        if(utils.isAppRunForFirstTime()){
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }

        tv_continue.setOnClickListener {
            utils.setAppRunForFirstTime(true)
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
        tv_link.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.link)))
            startActivity(browserIntent)
        }
    }
}