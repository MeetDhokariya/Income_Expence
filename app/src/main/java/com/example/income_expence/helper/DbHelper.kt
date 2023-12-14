package com.example.income_expence.helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.income_expence.Model.IncomeExpenseModel
import com.example.income_expence.model.CategoryModel

class DbHelper(context: Context?) : SQLiteOpenHelper(context, "RNW", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE IncomeExpense (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,amount TEXT,notes TEXT,date TEXT,time TEXT,status INTEGER,category TEXT)"
        val queryCategory = "CREATE TABLE Category (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT)"
        db!!.apply {
            execSQL(query)
            execSQL(queryCategory)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}


    fun addCategory(name: String) {
        var db = writableDatabase
        var cn = ContentValues()
        cn.put("name", name)
        db.insert("Category", null, cn)
    }

    fun addIncomeExpense(model: IncomeExpenseModel) {
        val db =writableDatabase
        val cn = ContentValues()
        cn.put("title",model.title)
        cn.put("notes",model.notes)
        cn.put("amount",model.amount)
        cn.put("date",model.date)
        cn.put("time",model.time)
        cn.put("category",model.category)
        cn.put("status",model.status)

        db.insert("IncomeExpense",null,cn)

    }

    fun updateIncomeExpense(model:IncomeExpenseModel) {
        val db =writableDatabase
        val cn = ContentValues()
        cn.put("title",model.title)
        cn.put("notes",model.notes)
        cn.put("amount",model.amount)
        cn.put("date",model.date)
        cn.put("time",model.time)
        cn.put("category",model.category)
        cn.put("status",model.status)

        db.update("IncomeExpense",cn,"id=?", arrayOf(model.id))

    }
    @SuppressLint("Range")
    fun getCategory(): ArrayList<CategoryModel> {
        var list = arrayListOf<CategoryModel>()
        val db = readableDatabase
        val query="SELECT * FROM Category"
        var cusor = db.rawQuery(query,null)

        if(cusor.moveToFirst())
        {
            do {
                var id = cusor.getString(cusor.getColumnIndex("id"))
                var name = cusor.getString(cusor.getColumnIndex("name"))
                var model = CategoryModel(id,name)
                list.add(model)
            }while(cusor.moveToNext())
        }

        return list
    }

    @SuppressLint("Range")
    fun getIncomeExpense(): ArrayList<IncomeExpenseModel> {
        var db =readableDatabase
        var query ="SELECT * FROM IncomeExpense"
        var cursor = db.rawQuery(query,null)
        var dataList = arrayListOf<IncomeExpenseModel>()

        if(cursor.moveToFirst())
        {
            do {
                var id =  cursor.getString(cursor.getColumnIndex("id"))
                var title =  cursor.getString(cursor.getColumnIndex("title"))
                var amount =  cursor.getString(cursor.getColumnIndex("amount"))
                var notes =  cursor.getString(cursor.getColumnIndex("notes"))
                var date =  cursor.getString(cursor.getColumnIndex("date"))
                var time =  cursor.getString(cursor.getColumnIndex("time"))
                var category =  cursor.getString(cursor.getColumnIndex("category"))
                var status =  cursor.getString(cursor.getColumnIndex("status"))

                var model =IncomeExpenseModel(id,title,amount,notes,date,time,status.toInt(),category)
                dataList.add(model)
            }while (cursor.moveToNext())
        }

        return dataList

    }

    fun deleteIncomeExpense( id:String)
    {
        var db = writableDatabase
        db.delete("IncomeExpense","id=?", arrayOf(id))
    }







}