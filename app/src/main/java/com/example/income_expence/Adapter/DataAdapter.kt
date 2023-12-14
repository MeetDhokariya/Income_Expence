package com.example.income_expence.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.income_expence.IncomeExpenseActivity
import com.example.income_expence.Model.IncomeExpenseModel
import com.example.income_expence.R


class DataAdapter(val context: Context, var list :ArrayList<IncomeExpenseModel>) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : ViewHolder(itemView)
    {
        var txtDataTitle =itemView.findViewById<TextView>(R.id.txtDataTitle)
        var txtDataId =itemView.findViewById<TextView>(R.id.txtDataId)
        var txtDataAmount =itemView.findViewById<TextView>(R.id.txtDataAmount)
        var txtDataNotes =itemView.findViewById<TextView>(R.id.txtDataNotes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.data_item,parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        if(list.get(position).status==1)
        {
            holder.txtDataAmount.setTextColor(Color.RED)
        }
        else
        {
            holder.txtDataAmount.setTextColor(Color.GREEN)

        }

        holder.txtDataId.text = list.get(position).id
        holder.txtDataTitle.text = list.get(position).title
        holder.txtDataAmount.text = list.get(position).amount.toString()
        holder.txtDataNotes.text = list.get(position).notes


        holder.txtDataAmount.setOnClickListener{
            var intent = Intent(context, IncomeExpenseActivity::class.java)
            intent.putExtra("title",list.get(position).title)
            intent.putExtra("time",list.get(position).time)
            intent.putExtra("notes",list.get(position).notes)
            intent.putExtra("id",list.get(position).id)
            intent.putExtra("amount",list.get(position).amount)
            intent.putExtra("date",list.get(position).date)
            intent.putExtra("category",list.get(position).category)
            intent.putExtra("status",list.get(position).status)
            context.startActivity(intent)
        }
    }
}