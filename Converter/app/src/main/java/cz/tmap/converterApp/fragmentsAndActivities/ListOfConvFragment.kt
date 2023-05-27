package cz.tmap.converterApp.fragmentsAndActivities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.tmap.converter.R

class ListOfConvFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListOfConvAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_of_conv, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewConv)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        val data = listOf(
            resources.getString(R.string.area_title),
            resources.getString(R.string.data_title),
            resources.getString(R.string.length_title),
            resources.getString(R.string.mass_title),
            resources.getString(R.string.speed_title),
            resources.getString(R.string.time_title),
            resources.getString(R.string.volume_title),
            resources.getString(R.string.energy_title),
            resources.getString(R.string.power_title),
            resources.getString(R.string.pressure_title),
            resources.getString(R.string.frequency_title),
            resources.getString(R.string.angle_title),
            resources.getString(R.string.density_title),
            resources.getString(R.string.data_transfer_title),
            resources.getString(R.string.temperature_title)
        )

        adapter = context?.let { ListOfConvAdapter(it) }!!
        recyclerView.adapter = adapter
        adapter.submitList(data)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewConv)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

    }

}