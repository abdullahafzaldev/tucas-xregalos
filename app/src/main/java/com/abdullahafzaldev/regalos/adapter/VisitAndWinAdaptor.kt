package com.abdullahafzaldev.regalos.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdullahafzaldev.regalos.R
import com.abdullahafzaldev.regalos.db.TinyDB
import com.abdullahafzaldev.regalos.model.Links
import com.abdullahafzaldev.regalos.util.Utils
import kotlinx.android.synthetic.main.item_web_link.view.*



class VisitAndWinAdaptor : RecyclerView.Adapter<VisitAndWinAdaptor.Views>() {


    var winPointsList = ArrayList<Links>()
    lateinit var context: Context
    lateinit var onItemClick: OnItemClickListener
    lateinit var selectedKey: String

    lateinit var utils: Utils
    lateinit var tinyDB: TinyDB
    var keyList = ArrayList<String>()


    fun submitList(winPointExtraList: ArrayList<Links>) {
        winPointsList = winPointExtraList

    }


    class Views(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Views {
        context = parent.context
        utils = Utils(context)
        tinyDB = TinyDB(context)
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_web_link, parent, false)
        return Views(view)
    }

    override fun onBindViewHolder(holder: Views, @SuppressLint("RecyclerView") position: Int) {

        if (winPointsList[position].hide) {
            holder.itemView.visibility = View.GONE
        }
        Log.d("TAGadasd", "onBindViewHolder:pos  - ${position} == total size - ${winPointsList.size} == key ${winPointsList[position].key} ")
        if (winPointsList[position].open) {
            holder.itemView.extendedLayout.visibility = View.VISIBLE
        } else {
            holder.itemView.extendedLayout.visibility = View.GONE
        }
        holder.itemView.visit_web_btn.setOnClickListener {

            if (!winPointsList[position].open) {
                openCard(position)
            } else {
                closeCard()
            }

        }
        holder.itemView.status.setImageResource(R.drawable.ic_cross)
//        holder.itemView.ll.background =
//            context.resources.getDrawable(R.drawable.wrong_box_r)
        holder.itemView.visit_web_btn.visibility = View.VISIBLE
        holder.itemView.key_et.isEnabled = true


        holder.itemView.visit_web.setOnClickListener {
            //   onItemClick.itemClicked(winPointsList[position].linkList[(0..9).random()] ,winPointsList[position].key )
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(winPointsList[position].linkList[(0..9).random()])
            )
            context.startActivity(intent)
        }




        holder.itemView.key_et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0!!.toString().toLowerCase().equals(selectedKey.toLowerCase())) {

                    keyList = tinyDB.getListString("key_list")
                    keyList.add(selectedKey)
                    tinyDB.putListString("key_list", keyList)
                    utils.setCoin(utils.getCoins() + 100)
                    utils.setDate(utils.getTodayDate().toInt())

                    holder.itemView.key_et.text.clear()
                    holder.itemView.status.setImageResource(R.drawable.ic_tick)
                    holder.itemView.ll.background =
                        context.resources.getDrawable(R.drawable.correct_box_r)
                    utils.setCardVisit(utils.getCardVisit() + 1)
                    holder.itemView.key_et.isEnabled = false
                    onItemClick.itemClicked(openCardPosition)
                //Toast.makeText(context, "100 Coins Added !", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }


    fun setUpOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClick = onItemClickListener
    }

    interface OnItemClickListener {
        fun itemClicked(id: Int)
    }

    var openCardPosition = 0
    private fun openCard(position: Int) {
        openCardPosition = position
        selectedKey = winPointsList[position].key
      //  Log.d("TAGCardKey", "openCard: $selectedKey ")
        Log.d("TAGCardKey", "onBindViewHolder:pos  - ${position} == total size - ${winPointsList.size} == key ${winPointsList[position].key} ")

        for (x in winPointsList.indices) {
            val item = winPointsList[x]
            item.open = x == position
            winPointsList[x] = item
        }
        notifyDataSetChanged()
    }

    private fun closeCard() {
        for (x in winPointsList.indices) {
            val item = winPointsList[x]
            item.open = false
            winPointsList[x] = item
        }
        notifyDataSetChanged()
    }

    private fun hideCard(position: Int) {
        selectedKey = winPointsList[position].key
        for (x in winPointsList.indices) {
            val item = winPointsList[x]
            item.hide = x == position
            winPointsList[x] = item
        }
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return winPointsList.size
    }
}