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
import com.example.grupo11_vinilos.databinding.AlbumFragmentBinding
import com.example.grupo11_vinilos.ui.adapters.AlbumsAdapter
import com.example.grupo11_vinilos.viewmodels.AlbumViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AlbumFragment : Fragment() {
    private var _binding: AlbumFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AlbumViewModel
    private var viewModelAdapter: AlbumsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        view.findViewById<Button>(R.id.navButtonMusicians).setOnClickListener {
            findNavController().navigate(R.id.action_albumFragment_to_musicianFragment)
        }

        view.findViewById<Button>(R.id.navButtonCollectors).setOnClickListener {
            findNavController().navigate(R.id.action_albumFragment_to_collectorFragment)
        }

        view.findViewById<FloatingActionButton>(R.id.addAlbumButton).setOnClickListener {
            findNavController().navigate(R.id.action_albumFragment_to_createAlbumFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.albumsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.titleAlbums)
        viewModel = ViewModelProvider(
            this,
            AlbumViewModel.Factory(activity.application)
        )[AlbumViewModel::class.java]
        viewModel.albums.observe(viewLifecycleOwner) {
            it.apply {
                viewModelAdapter = AlbumsAdapter(this)
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