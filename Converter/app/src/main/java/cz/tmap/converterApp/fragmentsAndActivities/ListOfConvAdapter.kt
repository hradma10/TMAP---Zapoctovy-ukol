package cz.tmap.converterApp.fragmentsAndActivities

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import cz.tmap.converter.R

class ListOfConvAdapter(private val context: Context) :
    ListAdapter<String, ListOfConvAdapter.ViewHolder>(
        StringDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.conv_list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val materialTextView: MaterialTextView = itemView.findViewById(R.id.conv_item_list)

        init {
            itemView.setOnClickListener {
                val clickedItem = getItem(adapterPosition)
                val intent = Intent(context, ConvActivity::class.java)
                intent.putExtra("convType", clickedItem)
                context.startActivity(intent)
            }
        }

        fun bind(item: String) {
            materialTextView.text = item
        }
    }
}

class StringDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
