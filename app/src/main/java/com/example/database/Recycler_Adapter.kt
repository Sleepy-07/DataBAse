package com.example.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Update
import com.example.database.DATABASE.NoteData

class Recycler_Adapter(val list: ArrayList<NoteData>, val OnclickLis : OnClick ): RecyclerView.Adapter<Recycler_Adapter.ViewHolder>() {
    class ViewHolder(var view: View):RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.Name)
        val Desc = view.findViewById<TextView>(R.id.Subject)
        val btn1 = view.findViewById<Button>(R.id.Update)
        val btn2 = view.findViewById<Button>(R.id.Delete)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.input,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
            return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.apply {

           title.setText(list[position].Title)
           Desc.setText(list[position].Desc)

           btn1.setOnClickListener {
               OnclickLis.Update(position)
           }
           btn2.setOnClickListener {
               OnclickLis.Delete(position)
           }

       }
    }
}

interface OnClick {
    fun Update(position: Int)
    fun Delete(position: Int)

}
