package com.example.income_expence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.income_expence.Model.IncomeExpenseModel
import com.example.income_expence.model.CategoryModel
import com.example.income_expence.helper.DbHelper

class IncomeExpenseActivity : AppCompatActivity() {
    private var id: String? = null
    lateinit var db: DbHelper
    lateinit var btnExpense: Button
    lateinit var btnDelete: Button
    lateinit var btnIncome: Button
    lateinit var edtTitle: EditText
    lateinit var edtAmount: EditText
    lateinit var edtDate: EditText
    lateinit var edtNotes: EditText
    lateinit var edtTime: EditText
    lateinit var spinner: Spinner
    var categoryList = arrayListOf<CategoryModel>()
    var dataList = arrayListOf<IncomeExpenseModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_expense)
        db = DbHelper(this)
        categoryList = db.getCategory()
        initBinding()
        getIntentData()

    }

    private fun getIntentData() {

        var notes = intent.getStringExtra("notes")
        var title = intent.getStringExtra("title")
        var amount = intent.getStringExtra("amount")
        var date = intent.getStringExtra("date")
        id = intent.getStringExtra("id")
        var status = intent.getIntExtra("status", 0)
        var category = intent.getStringExtra("category")
        var time = intent.getStringExtra("time")

        if (amount != null) {
            btnDelete.visibility = View.VISIBLE
            edtAmount.setText("$amount")
            edtDate.setText("$date")
            edtTime.setText("$time")
            edtNotes.setText("$notes")
            edtTitle.setText("$title")
            var i = 0
            while (i < categoryList.size) {
                if (categoryList[i].name.equals(category)) {
                    spinner.setSelection(i)
                }
                i++
            }

        }
    }

    private fun initBinding() {

        spinner = findViewById<Spinner>(R.id.spinner)
        edtTitle = findViewById<EditText>(R.id.edtTitle)
        edtAmount = findViewById<EditText>(R.id.edtAmount)
        edtNotes = findViewById<EditText>(R.id.edtNotes)
        edtDate = findViewById<EditText>(R.id.edtDate)
        edtTime = findViewById<EditText>(R.id.edtTime)
        btnIncome = findViewById<Button>(R.id.btnIncome)
        btnExpense = findViewById<Button>(R.id.btnExpense)
        btnDelete = findViewById<Button>(R.id.btnDelete)

        //Spinner Category
        var nameList = arrayListOf<String>()
        categoryList.forEach {
            nameList.add(it.name)
        }
        var arrayAdapter = ArrayAdapter(
            this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nameList
        )
        spinner.adapter = arrayAdapter

        // Button Click

        btnIncome.setOnClickListener {
            var index = spinner.selectedItemPosition

            var finalId="0"

            if(id!=null)
            {
                finalId=id!!
            }

            var model = IncomeExpenseModel(
                finalId,
                edtTitle.text.toString(),
                edtAmount.text.toString(),
                edtNotes.text.toString(),
                edtDate.text.toString(),
                edtTime.text.toString(),
                0,
                nameList[index]
            )

            if (id != null) {
                db.updateIncomeExpense(model)
            } else {
                db.addIncomeExpense(model)
            }
            finish()

        }

        btnExpense.setOnClickListener {
            var index = spinner.selectedItemPosition

            var finalId="0"

            if(id!=null)
            {
                finalId=id!!
            }

            var model = IncomeExpenseModel(
                finalId,
                edtTitle.text.toString(),
                edtAmount.text.toString(),
                edtNotes.text.toString(),
                edtDate.text.toString(),
                edtTime.text.toString(),
                1,
                nameList[index]
            )

            if (id != null) {
                db.updateIncomeExpense(model)
            } else {
                db.addIncomeExpense(model)
            }
            finish()


        }

        btnDelete.setOnClickListener {
            db.deleteIncomeExpense(id!!)
            finish()
        }
    }
}