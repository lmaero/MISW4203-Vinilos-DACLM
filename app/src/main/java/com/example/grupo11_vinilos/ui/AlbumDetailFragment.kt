package com.example.grupo11_vinilos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.databinding.AlbumDetailFragmentBinding
import com.example.grupo11_vinilos.ui.adapters.AlbumDetailAdapter
import com.example.grupo11_vinilos.ui.adapters.CommentsAdapter
import com.example.grupo11_vinilos.ui.adapters.TracksAdapter
import com.example.grupo11_vinilos.viewmodels.AlbumDetailViewModel
import com.google.android.material.card.MaterialCardView

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AlbumDetailFragment : Fragment() {
    private var _binding: AlbumDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AlbumDetailViewModel
    private var viewModelAdapter: AlbumDetailAdapter? = null
    private var tracksViewModelAdapter: TracksAdapter? = null
    private var commentsViewModelAdapter: CommentsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumDetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = AlbumDetailAdapter()
        tracksViewModelAdapter = TracksAdapter()
        commentsViewModelAdapter = CommentsAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.albumDetailRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.titleAlbums)
        val args: AlbumDetailFragmentArgs by this.navArgs()

        viewModel = ViewModelProvider(
            this,
            AlbumDetailViewModel.Factory(activity.application, args.albumId)
        ).get(AlbumDetailViewModel::class.java)
        viewModel.albumDetail.observe(viewLifecycleOwner) {
            it.apply {
                viewModelAdapter!!.albumDetail = this
                if (this.tracks.size > 0) {
                    tracksViewModelAdapter!!.tracks = this.tracks
                    binding.tracks.layoutManager = LinearLayoutManager(context)
                    binding.tracks.adapter = tracksViewModelAdapter
                    view?.findViewById<MaterialCardView>(R.id.tracksCardView)?.visibility =
                        View.VISIBLE
                    view?.findViewById<TextView>(R.id.tracksTitleCardView)?.visibility =
                        View.VISIBLE
                }
                if (this.comments.size > 0) {
                    commentsViewModelAdapter!!.comments = this.comments
                    binding.comments.layoutManager = LinearLayoutManager(context)
                    binding.comments.adapter = commentsViewModelAdapter
                    view?.findViewById<MaterialCardView>(R.id.commentsCardView)?.visibility =
                        View.VISIBLE
                    view?.findViewById<TextView>(R.id.commentsTitleCardView)?.visibility =
                        View.VISIBLE
                }
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