package com.zqc.profiler.demo

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_numerous_gc.*

/**
 * @author sean.zhang
 * Created on 8/12/18.
 */
class NumerousGCActivity: AppCompatActivity() {

    val NO_OF_TEXTVIEWS_ADDED = 100000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numerous_gc)
        btn_start.setOnClickListener {
            setupRecyclerView()
        }
    }

    private fun setupRecyclerView() {
        val numbers = arrayOfNulls<Int>(NO_OF_TEXTVIEWS_ADDED).mapIndexed { index, _ -> index }
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = NumerousGCRecyclerViewAdapter(numbers)
        recyclerView.adapter = LessNumerousGCRecyclerViewAdapter(this,numbers)
    }
}

class NumerousGCRecyclerViewAdapter(private val numbers: List<Int>): RecyclerView.Adapter<NumerousGCViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumerousGCViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_numerous_gc, parent, false)
        return NumerousGCViewHolder(view)
    }

    override fun getItemCount(): Int {
        return numbers.size
    }

    override fun onBindViewHolder(vh: NumerousGCViewHolder, position: Int) {
        vh.textView.text = position.toString()

        //Create bitmap from resource
        val bitmap = if(position % 2 == 0)
                BitmapFactory.decodeResource(vh.imageView.context.resources, R.drawable.big_bitmap)
            else
                BitmapFactory.decodeResource(vh.imageView.context.resources, R.drawable.small_bitmap)
        vh.imageView.setImageBitmap(bitmap)
    }
}

class LessNumerousGCRecyclerViewAdapter(private val context: Context,
                                         private val numbers: List<Int>): RecyclerView.Adapter<NumerousGCViewHolder>() {

    val bitBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.big_bitmap)
    val smallBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.small_bitmap)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumerousGCViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_numerous_gc, parent, false)
        return NumerousGCViewHolder(view)
    }

    override fun getItemCount(): Int {
        return numbers.size
    }

    override fun onBindViewHolder(vh: NumerousGCViewHolder, position: Int) {
        vh.textView.text = position.toString()

        //Reuse bitmap
        val bitmap = if(position % 2 == 0) bitBitmap else smallBitmap
        vh.imageView.setImageBitmap(bitmap)
    }
}


class NumerousGCViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView = itemView.findViewById(R.id.text_view)
    var imageView: ImageView = itemView.findViewById(R.id.image_view)
}

