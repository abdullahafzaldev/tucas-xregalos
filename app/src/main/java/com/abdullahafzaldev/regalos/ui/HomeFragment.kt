package com.abdullahafzaldev.regalos.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abdullahafzaldev.regalos.MainActivity
import com.abdullahafzaldev.regalos.R
import com.abdullahafzaldev.regalos.constants.Constants
import com.abdullahafzaldev.regalos.util.Loading
import com.abdullahafzaldev.regalos.util.Utils
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.coins_count.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_rate_us.*

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    lateinit var util: Utils
    lateinit var rateUsLink: String

    // lateinit var loading: Loading
    lateinit var db: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        if (util.getFreeCoinDate() != util.getTodayDate().toInt()) {
            util.setFreeCoinReceived(false)
            enableCoin()

            //  free_coin.setImageResource(R.drawable.points_on_drawable)
        } else {
            disableCoin()
            // free_coin.setImageResource(R.drawable.points_off_drawable)
        }
        tv_coin.text = util.getCoins().toString()
        win_point.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_visitAndWinFragment)
        }
//        win_point_extra.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_winPointExtraFragment)
//        }
//        win_five_point.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_winCodePointsFragment)
//        }
        go_to_shop.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_getTheRewardsFragment)
        }
        getRateUsLnk()
        free_coin.setOnClickListener {
            if (!util.isFreeCoinReceived()) {
                util.setFreeCoinReceived(true)
                util.setFreeCoinDate(util.getTodayDate().toInt())
                util.setCoin(util.getCoins() + 200)
                tv_coin.text = util.getCoins().toString()
                disableCoin()
                //free_coin.setImageResource(R.drawable.points_off_drawable)
            }
        }

    }

    private fun disableCoin() {
//        free_coin.background = requireActivity().getDrawable(R.drawable.light_green_drawable)
//        free_coin.alpha = 0.1f
        colored_icon.visibility = View.GONE
        colored_background.visibility = View.GONE
    }

    private fun enableCoin() {
//        free_coin.background = requireActivity().getDrawable(R.drawable.light_green_drawable)
//        free_coin.alpha = 0.1f
        colored_icon.visibility = View.VISIBLE
        colored_background.visibility = View.VISIBLE
    }

    private fun getRateUsLnk() {
        pb.show()
        db.collection("Apps").document(Constants.APP_NAME).get().addOnCompleteListener {
            Constants.GROUP_ID = it.result.get("group").toString()
        }
        if (util.showRateUs() && !MainActivity.rateDialogShow) {
            MainActivity.rateDialogShow = true
            db.collection("RateUsGroup").document(Constants.APP_NAME).get().addOnCompleteListener {
                if (it.isSuccessful) {
//                    loading.hideLoading()
                    pb.hide()
                    showRateUsDialog(it.result.get(Constants.APP_NAME).toString())
                } else {
                    // loading.hideLoading()
                    pb.hide()
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            //loading.hideLoading()
            pb.hide()
        }

    }


    private fun showRateUsDialog(link: String) {
        Log.d("TAGDialo", "showRateUsDialog: ${link}")
        val dialog = Dialog(requireContext(), R.style.AppThemeDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.layout_rate_us)
        dialog.rateUs.setOnClickListener {
            dialog.dismiss()
            util.setRateUs(false)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intent)

        }
        dialog.no.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun init() {
        util = Utils(requireContext())
        //  loading = Loading(requireContext())
        db = FirebaseFirestore.getInstance()

    }
}