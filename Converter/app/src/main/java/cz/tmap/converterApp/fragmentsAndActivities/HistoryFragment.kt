package cz.tmap.converterApp.fragmentsAndActivities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cz.tmap.converter.R
import cz.tmap.converterApp.database.AppDb
import cz.tmap.converterApp.database.ConversionDB


class HistoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListOfHistoryAdapter
    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_of_history, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewHistory)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val db = context?.let { AppDb.getDatabase(it) }
        val conversions: MutableList<ConversionDB>? = db?.conversionDao()?.getAll()?.toMutableList()
        adapter = conversions?.let { ListOfHistoryAdapter(it) }!!
        recyclerView.adapter = adapter

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            val newConversions: MutableList<ConversionDB> =
                db.conversionDao().getAll().toMutableList()

            adapter.updateData(newConversions)

            swipeRefreshLayout.isRefreshing = false
        }
        handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            val newConversions: MutableList<ConversionDB> =
                db.conversionDao().getAll().toMutableList()

            adapter.updateData(newConversions)
        }, 10000)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewHistory)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

    }

}