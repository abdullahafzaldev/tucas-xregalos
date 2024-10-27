package com.abdullahafzaldev.regalos.ui.visitandwin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abdullahafzaldev.regalos.R
import com.abdullahafzaldev.regalos.adapter.VisitAndWinAdaptor
import com.abdullahafzaldev.regalos.constants.Constants
import com.abdullahafzaldev.regalos.db.TinyDB
import com.abdullahafzaldev.regalos.model.Links
import com.abdullahafzaldev.regalos.util.Loading
import com.abdullahafzaldev.regalos.util.Utils
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.coins_count.*
import kotlinx.android.synthetic.main.fragment_visit_and_win.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class VisitAndWinFragment : Fragment() {
    lateinit var db: FirebaseFirestore

    //   lateinit var loading: Loading
    var winPointsExtra = arrayListOf<Links>()
    lateinit var utils: Utils
    lateinit var winExtraPointAdaptor: VisitAndWinAdaptor
    lateinit var tinyDB: TinyDB
    var keyList = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visit_and_win, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        addData()
        tv_coin.text = utils.getCoins().toString()
        if (utils.getDate() != utils.getTodayDate().toInt()) {
            keyList.clear()
            tinyDB.putListString("key_list", keyList)
            getAllLinks()
        } else {
            val winPointsExtra = tinyDB.getListObject(
                "list",
                Links::class.java
            ) as ArrayList<Links> /* = java.util.ArrayList<com.abdullahafzaldev.premios.model.Links> */
            if (winPointsExtra.isEmpty()) {

                emptyLayout.visibility = View.VISIBLE
                recyclerView.visibility = GONE

            } else {
                setUpAdapter(winPointsExtra)

            }
        }

        back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun getAllLinks() {
        //loading.showLoading()
        pb.show()
        winPointsExtra.clear()
        val list = ArrayList<Links>()
        db.collection(Constants.GROUP_ID).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (e in task.result) {
                    if (!keyList.any { it == e.data["key"].toString() }) {
                        list.add(
                            Links(
                                e.data["key"].toString(), e.data["link"].toString(), false, false,
                                e.data["randomLinks"] as List<String>
                            )
                        )
                    }

                }
                if (list.size > 0) {
                    Log.d("TAGGER", "getAllLinks: ${utils.getCardVisit()}")
                    emptyLayout.visibility = GONE
                    recyclerView.visibility = View.VISIBLE
                    winPointsExtra.shuffle()
                    list.shuffle()
                    for (i in 0..5) {
                        winPointsExtra.add(list[i])
                    }
                    tinyDB.putListObject("list", winPointsExtra as java.util.ArrayList<Any>)
                    setUpAdapter(winPointsExtra)
                    /* if(utils.getCardVisit()>= winPointsExtra.size){
                         emptyLayout.visibility = View.VISIBLE
                         recyclerView.visibility = GONE
                         loading.hideLoading()
                     }else{
                         emptyLayout.visibility = GONE
                         recyclerView.visibility = View.VISIBLE
                         setUpAdapter(winPointsExtra)
                     }*/

                } else {
                    emptyLayout.visibility = View.VISIBLE
                    recyclerView.visibility = GONE
                    // loading.hideLoading()
                    pb.hide()
                }

            } else {
                // loading.hideLoading()
                pb.hide()
                Toast.makeText(
                    requireContext(),
                    "Error ${task.exception!!.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    private fun setUpAdapter(winPointsExtra: ArrayList<Links>) {
        winExtraPointAdaptor.submitList(winPointsExtra)
        winExtraPointAdaptor.notifyDataSetChanged()
        //loading.hideLoading()
        pb.hide()
    }

    private fun init() {

        db = FirebaseFirestore.getInstance()
        //loading = Loading(requireContext())
        tinyDB = TinyDB(requireContext())
        keyList = tinyDB.getListString("key_list")
        utils = Utils(requireContext())
        winExtraPointAdaptor = VisitAndWinAdaptor()
        winExtraPointAdaptor.setUpOnItemClickListener(object :
            VisitAndWinAdaptor.OnItemClickListener {
            override fun itemClicked(pos: Int) {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(500)
                    Log.d("TAGCardKey", "openCard: $pos ")
                    winExtraPointAdaptor.winPointsList.remove(winExtraPointAdaptor.winPointsList[pos])
                    tinyDB.putListObject(
                        "list",
                        winExtraPointAdaptor.winPointsList as java.util.ArrayList<Any>
                    )
                    winExtraPointAdaptor.notifyItemRemoved(pos)
                    winExtraPointAdaptor.notifyDataSetChanged()
                    tv_coin.text = utils.getCoins().toString()
                    if (winExtraPointAdaptor.winPointsList.size == 0) {
                        emptyLayout.visibility = View.VISIBLE
                        recyclerView.visibility = GONE
                    }
                }
                /* val directions = VisitAndWinFragmentDirections.actionVisitAndWinFragmentToVisitWebFragment(link , pos)
                 findNavController().navigate(directions)*/
            }
        })
        recyclerView.adapter = winExtraPointAdaptor


    }

    data class LL(
        var key: String = "123",
        var randomLinks: ArrayList<String> = arrayListOf<String>(
            "https://www.google.com/search?q=fitspl+.com",
            "https://www.google.com/search?q=fitspl+.com",
            "https://www.google.com/search?q=fitspl+.com",
            "https://www.google.com/search?q=fitspl+.com",
            "https://www.google.com/search?q=fitspl+.com",
            "https://www.google.com/search?q=fitspl+.com"
        )
    )

    fun addData() {
        for (i in 2..7) {
            db.collection("Visit").document(i.toString()).set(LL())
        }
        var hashMap = hashMapOf(
            "url" to "https://www.google.com/search?q=fitspl+.com"
        )
        db.collection("RateUsLink").document("link").set(hashMap)

    }
}