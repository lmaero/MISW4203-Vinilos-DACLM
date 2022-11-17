package com.example.grupo11_vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.databinding.MusicianFragmentBinding
import com.example.grupo11_vinilos.ui.adapters.MusiciansAdapter
import com.example.grupo11_vinilos.viewmodels.MusicianViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MusicianFragment : Fragment() {
    private var _binding: MusicianFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MusicianViewModel
    private var viewModelAdapter: MusiciansAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MusicianFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        view.findViewById<Button>(R.id.navButtonAlbums).setOnClickListener {
            findNavController().navigate(R.id.action_musicianFragment_to_albumFragment)
        }

        view.findViewById<Button>(R.id.navButtonCollectors).setOnClickListener {
            findNavController().navigate(R.id.action_musicianFragment_to_collectorFragment)
        }

        viewModelAdapter = MusiciansAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.musiciansRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.titleMusicians)
        viewModel = ViewModelProvider(
            this,
            MusicianViewModel.Factory(activity.application)
        ).get(MusicianViewModel::class.java)
        viewModel.musicians.observe(viewLifecycleOwner) {
            it.apply {
                viewModelAdapter!!.musicians = this
            }
        }
        viewModel.eventNetworkError.observe(
            viewLifecycleOwner
        ) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}