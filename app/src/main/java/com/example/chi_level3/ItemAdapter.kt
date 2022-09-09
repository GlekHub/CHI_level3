package com.example.chi_level3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ItemAdapter(
    private val context: Context, private var list: ArrayList<Item>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var itemName: TextView = itemView.findViewById(R.id.item_name)
        private var itemType: TextView = itemView.findViewById(R.id.item_type)
        private var itemPhoto: ImageView = itemView.findViewById(R.id.item_image)


        fun bind(n: String, p: String, u: String) {
            itemName.text = n
            itemType.text = p
            u.replace(" ", "%20")
            Picasso.get().load(u).into(itemPhoto)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v: View = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(
            list[position].name,
            list[position].animal_type,
            list[position].image_link,
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

