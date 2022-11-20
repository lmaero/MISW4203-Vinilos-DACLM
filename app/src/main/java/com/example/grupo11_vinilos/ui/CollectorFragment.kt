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
import com.example.grupo11_vinilos.databinding.CollectorFragmentBinding
import com.example.grupo11_vinilos.ui.adapters.CollectorsAdapter
import com.example.grupo11_vinilos.viewmodels.CollectorViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CollectorFragment : Fragment() {
    private var _binding: CollectorFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CollectorViewModel
    private var viewModelAdapter: CollectorsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CollectorFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        view.findViewById<Button>(R.id.navButtonAlbums).setOnClickListener {
            findNavController().navigate(R.id.action_collectorFragment_to_albumFragment)
        }

        view.findViewById<Button>(R.id.navButtonMusicians).setOnClickListener {
            findNavController().navigate(R.id.action_collectorFragment_to_musicianFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.collectorsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.titleCollectors)
        viewModel = ViewModelProvider(
            this,
            CollectorViewModel.Factory(activity.application)
        )[CollectorViewModel::class.java]
        viewModel.collectors.observe(viewLifecycleOwner) {
            it.apply {
                viewModelAdapter = CollectorsAdapter(this)
                recyclerView.adapter = viewModelAdapter
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