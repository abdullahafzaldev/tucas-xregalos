package com.abdullahafzaldev.regalos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdullahafzaldev.regalos.R

import com.abdullahafzaldev.regalos.model.Reward
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_get_reward.view.*


class RewardsAdaptor  : RecyclerView.Adapter<RewardsAdaptor.Views>() {


     var rewardsList = ArrayList<Reward>()
    lateinit var context: Context
    lateinit var onItemClick :  OnItemClickListener


    fun submitList(winPointExtraList : ArrayList<Reward> ){
        rewardsList = winPointExtraList

    }



    class  Views(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Views {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_get_reward , parent , false)
        return Views(view)
    }

    override fun onBindViewHolder(holder: Views, position: Int) {
        val reward = rewardsList[position]
        holder.itemView.setOnClickListener {
            onItemClick.itemClicked()
        }
        holder.itemView.title.text = reward.title
        Glide
            .with(context)
            .load(reward.img)
            .into(holder.itemView.img);
    }

    fun setUpOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClick=onItemClickListener
    }
    interface OnItemClickListener {
        fun itemClicked()
    }



    override fun getItemCount(): Int {
       return rewardsList.size
    }
}