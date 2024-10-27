package com.abdullahafzaldev.regalos.ui.getrewards

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.abdullahafzaldev.regalos.R
import com.abdullahafzaldev.regalos.util.Loading
import com.abdullahafzaldev.regalos.util.Utils
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.coins_count.*
import kotlinx.android.synthetic.main.fragment_get_the_rewards.*
import kotlinx.android.synthetic.main.layout_negative_dialog.*
import kotlinx.android.synthetic.main.layout_radeem_dialog.*


class GetTheRewardsFragment : Fragment() {


    lateinit var db : FirebaseFirestore
    lateinit var loading: Loading
    lateinit var utils: Utils
    //val rewardsList = ArrayList<Reward>()
    //lateinit var rewardsAdaptor: RewardsAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_the_rewards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            init()
        tv_coin.text = utils.getCoins().toString()
            //    getAllRewards()

        one.setOnClickListener {
          //  if(true){
            if(utils.getCoins()>15000){
                utils.setCoin(utils.getCoins() - 15000)
                tv_coin.text = utils.getCoins().toString()
                showPositiveDialog()
            }else{
                showNegativeDialog()
            }
        }

        two.setOnClickListener {
            if(utils.getCoins()>15000){
                utils.setCoin(utils.getCoins() - 15000)
                tv_coin.text = utils.getCoins().toString()
                showPositiveDialog()
            }else{
                showNegativeDialog()
            }
        }

        three.setOnClickListener {
            if(utils.getCoins()>15000){
                utils.setCoin(utils.getCoins() - 15000)
                tv_coin.text = utils.getCoins().toString()
                showPositiveDialog()
            }else{
                showNegativeDialog()
            }
        }
        four.setOnClickListener {
            if(utils.getCoins()>15000){
                utils.setCoin(utils.getCoins() - 15000)
                tv_coin.text = utils.getCoins().toString()
                showPositiveDialog()
            }else{
                showNegativeDialog()
            }
        }
        five.setOnClickListener {
            if(utils.getCoins()>15000){
                utils.setCoin(utils.getCoins() - 15000)
                tv_coin.text = utils.getCoins().toString()
                showPositiveDialog()
            }else{
                showNegativeDialog()
            }
        }
        six.setOnClickListener {
            if(utils.getCoins()>15000){
                utils.setCoin(utils.getCoins() - 15000)
                tv_coin.text = utils.getCoins().toString()
                showPositiveDialog()
            }else{
                showNegativeDialog()
            }
        }
        seven.setOnClickListener {
            if(utils.getCoins()>15000){
                utils.setCoin(utils.getCoins() - 15000)
                tv_coin.text = utils.getCoins().toString()
                showPositiveDialog()
            }else{
                showNegativeDialog()
            }
        }
        eight.setOnClickListener {
            if(utils.getCoins()>15000){
                utils.setCoin(utils.getCoins() - 15000)
                tv_coin.text = utils.getCoins().toString()
                showPositiveDialog()
            }else{
                showNegativeDialog()
            }
        }
        nine.setOnClickListener {
            if(utils.getCoins()>15000){
                utils.setCoin(utils.getCoins() - 15000)
                tv_coin.text = utils.getCoins().toString()
                showPositiveDialog()
            }else{
                showNegativeDialog()
            }
        }
        ten.setOnClickListener {
            if(utils.getCoins()>15000){
                utils.setCoin(utils.getCoins() - 15000)
                tv_coin.text = utils.getCoins().toString()
                showPositiveDialog()
            }else{
                showNegativeDialog()
            }
        }

        back.setOnClickListener {
            findNavController().navigateUp()
        }

    }

   /* private fun getAllRewards() {
        loading.showLoading()
        db.collection("Rewards").get().addOnCompleteListener {
            if(it.isSuccessful){
                for(e in it.result){
                    rewardsList.add(Reward(e.data["img"].toString() ,
                    e.data["title"].toString() , e.data["desc"].toString() , e.data["price"].toString()))
                }
            }
            if(rewardsList.size>0){
                setAdapter()
            }
        }
    }*/

    /*private fun setAdapter() {
        rewardsAdaptor.submitList(rewardsList)
        rewardsAdaptor.notifyDataSetChanged()
        loading.hideLoading()
    }*/

    private fun init() {
        db = FirebaseFirestore.getInstance()
        loading = Loading(requireContext())
        utils = Utils(requireContext())
        /*rewardsAdaptor = RewardsAdaptor()
        rewardsAdaptor.setUpOnItemClickListener(object : RewardsAdaptor.OnItemClickListener{
            override fun itemClicked() {
                if(utils.getCoins()>20){
                    utils.setCoin(utils.getCoins() - 20)
                    coin.text = utils.getCoins().toString()
                    showPositiveDialog()
                }else{
                    showNegativeDialog()
                }
            }

        })
        recyclerViewReward.adapter = rewardsAdaptor*/
    }

    private fun showPositiveDialog() {
        val dialog = Dialog(requireContext(),R.style.AppThemeDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.window!!.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.layout_positive_dialog)
        dialog.endBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showNegativeDialog() {
        val dialog = Dialog(requireContext(),R.style.AppThemeDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.window!!.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.layout_negative_dialog)
        dialog.endBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }


fun cardClick(v : View){
    Toast.makeText(requireContext(), "cleciclc", Toast.LENGTH_SHORT).show()
}


}