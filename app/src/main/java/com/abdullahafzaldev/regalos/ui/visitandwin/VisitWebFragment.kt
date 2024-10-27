package com.abdullahafzaldev.regalos.ui.visitandwin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abdullahafzaldev.regalos.R
import com.abdullahafzaldev.regalos.db.TinyDB
import com.abdullahafzaldev.regalos.util.Utils
import kotlinx.android.synthetic.main.fragment_visit_web.*
import kotlinx.android.synthetic.main.fragment_visit_web.tv_coin
import java.util.*
import kotlin.collections.ArrayList


class VisitWebFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_visit_web, container, false)
    }


    lateinit var utils : Utils
    lateinit var tinyDB: TinyDB
    val args : VisitWebFragmentArgs by navArgs()
     var keyList = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        tv_coin.text = utils.getCoins().toString()
        checkIfUsed()
        Toast.makeText(requireContext(), "coin - ${args.key.toString()}", Toast.LENGTH_SHORT).show()
        back.setOnClickListener {
            findNavController().navigateUp()
        }

        visit_web.setOnClickListener {
            val intent  = Intent(Intent.ACTION_VIEW , Uri.parse(args.link))
            startActivity(intent)
        }
        key_et.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.toString().lowercase(Locale.getDefault()) == args.key.toString()
                        .lowercase(Locale.getDefault())){
                     keyList = tinyDB.getListString("key_list")
                    keyList.add(args.key.toString())
                    tinyDB.putListString("key_list",keyList)
                    status.setImageResource(R.drawable.ic_right_icon)
                    utils.setCoin(utils.getCoins() + 100)
                    utils.setDate(utils.getTodayDate().toInt())
                    key_et.text.clear()
                    tv_coin.text = utils.getCoins().toString()
                   visit_web.visibility = GONE
                    utils.setCardVisit(utils.getCardVisit() + 1 )
                    key_et.isEnabled = false
                    Toast.makeText(requireContext(), "100 Coins Added !", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

    }

    private fun checkIfUsed() {
        keyList = tinyDB.getListString("key_list")
        for(v in keyList){
            if(v == args.key.toString()){
                if(utils.getTodayDate().toInt()==utils.getDate()){
                    visit_web.visibility = GONE
                    key_et.isEnabled = false
                }else{
                    visit_web.visibility = VISIBLE
                }

            }
        }
    }

    private fun init() {
        utils = Utils(requireContext())
        tinyDB = TinyDB(requireContext())
    }
}