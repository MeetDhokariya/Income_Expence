package com.example.income_expence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.income_expence.Adapter.CategoryAdapter

import com.example.income_expence.helper.DbHelper
import com.example.income_expence.model.CategoryModel

class CategoryActivity : AppCompatActivity() {

    lateinit var  rvCategory: RecyclerView
    lateinit var btnaddCategory: Button
    lateinit var edtCategory: EditText
    var list = arrayListOf<CategoryModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        var db = DbHelper(this)
        list = db.getCategory()
        intBinding()
    }

    private fun intBinding() {
        btnaddCategory = findViewById<Button>(R.id.btnAddCategory)
        edtCategory = findViewById<EditText>(R.id.edtCategory)
        rvCategory = findViewById<RecyclerView>(R.id.rvCategory)


        var adapter = CategoryAdapter(this@CategoryActivity,list)
        var layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        rvCategory.layoutManager=layoutManager
        rvCategory.adapter = adapter

        btnaddCategory.setOnClickListener {

            var db = DbHelper(this@CategoryActivity)
            if (edtCategory.text.isEmpty())
            {
                edtCategory.setError("Plz Enter Category")
            }
            else
            {
                db.addCategory(edtCategory.text.toString())
                finish()

            }

        }


    }
}