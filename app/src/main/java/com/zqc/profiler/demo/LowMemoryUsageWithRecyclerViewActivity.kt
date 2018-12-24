package com.zqc.profiler.demo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_low_memory_usage_recyclerview.*
import java.util.*

/**
 * @author sean.zhang
 * Created on 24/12/18.
 */
class LowMemoryUsageWithRecyclerViewActivity : AppCompatActivity() {

    val NO_OF_TEXTVIEWS_ADDED = 100000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_low_memory_usage_recyclerview)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.activity_name_low_memory_usage_recyclerview)
        btn_start.setOnClickListener {
            setupRecyclerView()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupRecyclerView() {
        val numbers = arrayOfNulls<Int>(NO_OF_TEXTVIEWS_ADDED).mapIndexed { index, _ -> index }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerViewAdapter(numbers)
    }

}

class MyRecyclerViewAdapter(private val numbers: List<Int>): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = TextView(parent.context)
        val textViewParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = textViewParams
        return MyViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return numbers.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.textView.text = position.toString()
        viewHolder.textView.setBackgroundColor(getRandomColor())
    }

    /**
     * Creates a random color for background color of the text view.
     */
    private fun getRandomColor(): Int {
        val r = Random()
        val red = r.nextInt(255)
        val green = r.nextInt(255)
        val blue = r.nextInt(255)

        return Color.rgb(red, green, blue)
    }
}


class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView = itemView as TextView
}

