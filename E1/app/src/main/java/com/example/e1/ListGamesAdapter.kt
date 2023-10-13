package com.example.e1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ListGamesAdapter (val function : (Game) -> Unit) : RecyclerView.Adapter<ListGamesAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val constraintLayout: ConstraintLayout
        val tvId : TextView
        val tvName: TextView
        val tvPrice: TextView
        val tvQuantity: TextView
        val tvConsole : TextView
        val tvPc : TextView
        init {
            // Define click listener for the ViewHolder's View.
            tvId = view.findViewById(R.id.tvId)
            tvName = view.findViewById(R.id.tvName)
            tvPrice = view.findViewById(R.id.tvPrice)
            tvQuantity = view.findViewById(R.id.tvQuantity)
            tvConsole = view.findViewById(R.id.tvConsole)
            tvPc = view.findViewById(R.id.tvPc)
            constraintLayout = view.findViewById(R.id.Constraint)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_items_view, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvId.text = Singleton.list[position].id.toString()
        viewHolder.tvName.text = Singleton.list[position].name
        viewHolder.tvPrice.text = Singleton.list[position].price.toString()
        viewHolder.tvQuantity.text = Singleton.list[position].quantity.toString()
        viewHolder.tvConsole.text = Singleton.list[position].console

        if(Singleton.list[position].pc){
            viewHolder.tvPc.text = "Available on PC"
        }else{
            viewHolder.tvPc.text = "Not available on PC"
        }

        if(Singleton.list[position].quantity <= 5){
            viewHolder.tvId.setTextColor((Color.RED))
            viewHolder.tvName.setTextColor(Color.RED)
        }

        viewHolder.itemView.setOnClickListener {
            function(Singleton.list[position])
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = Singleton.list.size
}