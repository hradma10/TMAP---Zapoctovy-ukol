package cz.tmap.converterApp.fragmentsAndActivities

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cz.tmap.converter.R
import cz.tmap.converterApp.database.AppDb
import cz.tmap.converterApp.database.ConversionDB
import java.sql.Timestamp

class ListOfHistoryAdapter(private val conversions: MutableList<ConversionDB>) :
    RecyclerView.Adapter<ListOfHistoryAdapter.ConversionViewHolder>() {

    class ConversionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textType: TextView = itemView.findViewById(R.id.textTypeHistory)
        val textFromLabel: TextView = itemView.findViewById(R.id.textFromLabelHistory)
        val textFromNumber: TextView = itemView.findViewById(R.id.textFromNumberHistory)
        val textToLabel: TextView = itemView.findViewById(R.id.textToLabelHistory)
        val textToNumber: TextView = itemView.findViewById(R.id.textToNumberHistory)
        val timestamp: TextView = itemView.findViewById(R.id.textTimestampHistory)
        val deleteButton: Button = itemView.findViewById(R.id.button_delete_history_item)
        var id: Long = 0
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.history_item_layout,
            parent,
            false
        )
        return ConversionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ConversionViewHolder, position: Int) {
        val conversion = conversions[position]
        holder.textType.text =
            conversion.type.substring(0, 1).uppercase() + conversion.type.substring(1)
        holder.textFromLabel.text = conversion.fromLabel
        holder.textFromNumber.text = conversion.fromNumber + " " + conversion.fromUnit
        holder.textToLabel.text = conversion.toLabel
        holder.textToNumber.text = conversion.toNumber + " " + conversion.toUnit
        holder.timestamp.text = changeFormat(conversion.timestamp)
        holder.id = conversion.id
        holder.deleteButton.setOnClickListener {
            deleteItem(position, holder.itemView)
        }
    }


    private fun changeFormat(timestamp: Timestamp): String {
        timestamp.toString().split(".")[0].replace("-", ".")
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        return dateFormat.format(timestamp).split(".")[0].replace("-", ".")
    }

    override fun getItemCount(): Int {
        return conversions.size
    }

    fun updateData(newConversions: List<ConversionDB>) {
        conversions.clear()
        conversions.addAll(newConversions)
        notifyDataSetChanged()
    }


    private fun deleteItem(position: Int, view: View) {
        val deletedItemId = conversions[position].id
        conversions.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)

        val db = AppDb.getDatabase(view.context)
        val dao = db.conversionDao()
        dao.deleteById(deletedItemId)
    }

}
