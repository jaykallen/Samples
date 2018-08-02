package com.jaykallen.samples.recycler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jaykallen.samples.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recycler.*
import kotlinx.android.synthetic.main.recycler_journal.view.*
import timber.log.Timber

// 2018-02-23 How to create a recycler view in Kotlin
// Requires the support library: implementation 'com.android.support:design:26.1.0'

class RecyclerActivity : AppCompatActivity() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    val journals : ArrayList<JournalModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        Timber.d(" *** Main Activity ***")
        journals.add(JournalModel(1, "Kylie Jenner", "She is so cool"))
        journals.add(JournalModel(2, "I luv Kylie", "I wont use Twitter if Kylie Doesnt"))
        journals.add(JournalModel(3, "Kylie says", "Sell all Snapchat stock if Kylie doesnt like it"))
        journals.add(JournalModel(4, "Do what Kylie does", "What girls dont like make up??"))
        setupRecycler()

    }

    private fun setupRecycler() {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = RecyclerAdapter(journals)
    }

    private inner class RecyclerAdapter(private val itemList: ArrayList<JournalModel>) : RecyclerView.Adapter<RecyclerHolder>() {
        override fun getItemCount(): Int {
            return itemList.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_journal, parent, false)
            return RecyclerHolder(view)
        }

        override fun onBindViewHolder(recyclerholder: RecyclerHolder, position: Int) {
            recyclerholder.id.text = "" + itemList[position].id
            recyclerholder.title.text = itemList[position].title
            recyclerholder.body.text = itemList[position].body
        }
    }

    private inner class RecyclerHolder(holder: View) : RecyclerView.ViewHolder(holder) {
        val id : TextView = holder.idTextView
        val title : TextView = holder.titleTextView
        val body : TextView = holder.bodyTextView
    }
}
