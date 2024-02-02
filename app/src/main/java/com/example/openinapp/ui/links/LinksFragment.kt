package com.example.openinapp.ui.links

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openinapp.ApiInterface
import com.example.openinapp.Data
import com.example.openinapp.MyAdapter
import com.example.openinapp.MyData
import com.example.openinapp.RecentLink
import com.example.openinapp.databinding.FragmentLinksBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LinksFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    private var _binding: FragmentLinksBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val linksViewModel =
            ViewModelProvider(this).get(LinksViewModel::class.java)

        _binding = FragmentLinksBinding.inflate(inflater, container, false)
        val root: View = binding.root





        recyclerView=binding.recycler

        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://api.inopenapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitdata=retrofitBuilder.getProductData()
        val arrayLi = ArrayList<RecentLink>()

        retrofitdata.enqueue(object : Callback<RecentLink?> {
            override fun onResponse(call: Call<RecentLink?>, response: Response<RecentLink?>) {
                //if api  call is sucessful

            //    val responseBody=response.body()
               // val productlist=responseBody?.arrayList
                myAdapter= MyAdapter(this@LinksFragment, arrayLi )
                recyclerView.adapter=myAdapter
                recyclerView.layoutManager= LinearLayoutManager(context)



            }

            override fun onFailure(call: Call<RecentLink?>, t: Throwable) {
                //if api call is failed
                Log.d("Links Fragment","onFailure: "+t.message)
            }
        })


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}