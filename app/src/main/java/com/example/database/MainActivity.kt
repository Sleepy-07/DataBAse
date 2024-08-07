package com.example.database

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.database.DATABASE.Data
import com.example.database.DATABASE.NoteData
import com.example.database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),OnClick {
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerAdapter: Recycler_Adapter
    lateinit var data: Data
    var list = arrayListOf<NoteData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data=Data.getInstance(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerAdapter= Recycler_Adapter(list,this)
        binding.RecycleViewHost.layoutManager=LinearLayoutManager(
            this,LinearLayoutManager.VERTICAL,false
        )
        binding.RecycleViewHost.adapter=recyclerAdapter
        getlist()


        binding.AddData.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.input_data)
            val ttitle = dialog.findViewById<EditText>(R.id.Input_Title)
            val tdesc = dialog.findViewById<EditText>(R.id.Input_Desc)
            val btn = dialog.findViewById<Button>(R.id.Input_btn)
            dialog.show()

            btn.setOnClickListener {
                val Title = ttitle.text.toString()
                val Desc = tdesc.text.toString()
                data.inter().InsertTODO(NoteData(Title = Title, Desc = Desc))
                getlist()
                recyclerAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }
    }

    private fun getlist() {
        list.clear()
        list.addAll(data.inter().GetList())
    }

    override fun Update(position: Int) {
       val dialog = Dialog(this)
        dialog.setContentView(R.layout.update)
        val ttitle = dialog.findViewById<EditText>(R.id.Update_Title)
        val tdesc = dialog.findViewById<EditText>(R.id.Update_Desc)
        ttitle.setText(list[position].Title)
        tdesc.setText(list[position].Desc)
        val btn = dialog.findViewById<Button>(R.id.Update_btn)
        dialog.show()

        btn.setOnClickListener {
            val Title = ttitle.text.toString()
            val Desc = tdesc.text.toString()
//                data.inter().Update(list[position])
           data.inter().Update(NoteData(Id = list[position].Id, Title = Title, Desc = Desc))
//            data.inter().Update(list[position])

            getlist()
            recyclerAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
    }

    override fun Delete(position: Int) {
        data.inter().Delete(list[position])
        getlist()
        recyclerAdapter.notifyDataSetChanged()
    }
}