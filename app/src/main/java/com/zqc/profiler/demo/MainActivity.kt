package com.zqc.profiler.demo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


/**
 *
 * @author sean.zhang
 *
 * Created on 4/12/18.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setTitle(R.string.app_name)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val optionList = arrayOf(
            Option(
                getString(R.string.activity_name_high_memory_usage),
                HighMemoryUsageActivity::class.java
            ),
            Option(
                getString(R.string.activity_name_extensive_gc),
                ExtensiveGCActivity::class.java
            )
        )
        recyclerView.adapter = OptionRecyclerViewAdapter(optionList)
    }

}

data class Option(val name: String, val clazz: Class<out Activity>)

class OptionRecyclerViewAdapter(private val options: Array<Option>) : RecyclerView.Adapter<OptionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): OptionViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return OptionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(viewHolder: OptionViewHolder, position: Int) {
        viewHolder.textView.text = options[position].name
        viewHolder.textView.setOnClickListener {
            val context = viewHolder.textView.context
            context.startActivity(Intent(context, options[position].clazz))
        }
    }

}

class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView = itemView as TextView
}