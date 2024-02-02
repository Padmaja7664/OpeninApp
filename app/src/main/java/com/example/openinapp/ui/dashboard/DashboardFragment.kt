package com.example.openinapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.openinapp.R
import com.example.openinapp.databinding.FragmentDashboardBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.util.Calendar

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val greeting : TextView = binding.greetingId

        val currentTime = Calendar.getInstance().time
        val hour = currentTime.hours

        val greet = when (hour) {
            in 0..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            else -> "Good Evening"
        }

        greeting.text = greet

        val lineChart: LineChart = binding.chart

        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 20f))
        entries.add(Entry(2f, 25f))
        entries.add(Entry(3f, 18f))
        entries.add(Entry(4f, 30f))
        entries.add(Entry(5f, 22f))



        val dataSet = LineDataSet(entries, "Label for the DataSet")

        val lineData = LineData(dataSet)
        lineChart.data = lineData
        val description = Description()
        description.text = "Sample Line Chart"
        lineChart.description = description
        lineData.setValueTextSize(12f)
        lineChart.invalidate()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}