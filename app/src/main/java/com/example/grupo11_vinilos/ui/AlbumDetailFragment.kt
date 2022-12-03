package com.example.grupo11_vinilos.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.databinding.AlbumDetailFragmentBinding
import com.example.grupo11_vinilos.models.Collector
import com.example.grupo11_vinilos.ui.adapters.AlbumDetailAdapter
import com.example.grupo11_vinilos.ui.adapters.CommentsAdapter
import com.example.grupo11_vinilos.ui.adapters.TracksAdapter
import com.example.grupo11_vinilos.viewmodels.AlbumDetailViewModel
import com.example.grupo11_vinilos.viewmodels.CollectorViewModel
import com.example.grupo11_vinilos.viewmodels.NewCommentViewModel
import com.google.android.material.card.MaterialCardView


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AlbumDetailFragment : Fragment() {
    private var _binding: AlbumDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AlbumDetailViewModel
    private lateinit var newCommentViewModel: NewCommentViewModel
    private lateinit var collectorsViewModel: CollectorViewModel

    private var viewModelAdapter: AlbumDetailAdapter? = null
    private var tracksViewModelAdapter: TracksAdapter? = null
    private var commentsViewModelAdapter: CommentsAdapter? = null

    private lateinit var description: String
    private var rating: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumDetailFragmentBinding.inflate(inflater, container, false)
        val createNewCommentButton = binding.root.findViewById<Button>(R.id.createNewCommentButton)
        createNewCommentButton.setOnClickListener {
            description = binding.root.findViewById<EditText>(R.id.commentEditText).text.toString()
            newCommentViewModel.setDescription(description)

            val ratingSelectedId =
                binding.root.findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId
            rating =
                binding.root.findViewById<RadioButton>(ratingSelectedId).text.toString().toInt()
            newCommentViewModel.setRating(rating)

            val commenterSelected =
                binding.root.findViewById<Spinner>(R.id.commenterSpinner).selectedItem.toString()
            val commenterSelectedSplited = commenterSelected.split(" - ")
            val commenterId = commenterSelectedSplited[0].toInt()
            val commenterName = commenterSelectedSplited[1]

            val commenter = Collector(commenterId, commenterName, "", "")
            newCommentViewModel.setCollector(commenter)

            try {
                newCommentViewModel.setInfo(
                    newCommentViewModel.getDescription().value.toString(),
                    newCommentViewModel.getRating().value.toString().toInt(),
                    newCommentViewModel.getCollector().value!!
                )
                // To reload the all fragment
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    parentFragmentManager
                        .beginTransaction()
                        .detach(this)
                        .commit()
                    parentFragmentManager
                        .beginTransaction()
                        .attach(this)
                        .commit()
                } else {
                    parentFragmentManager
                        .beginTransaction()
                        .detach(this)
                        .attach(this)
                        .commit()
                }
                viewModel.recreateData()
                showToast("Comentario creado exitosamente")
            } catch (e: Exception) {
                Log.e("Error New Comment", e.toString())
                showToast("Error al crear un nuevo comentario")
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.albumDetailRv
        recyclerView.layoutManager = LinearLayoutManager(context)
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
        )[AlbumDetailViewModel::class.java]

        newCommentViewModel = ViewModelProvider(
            this,
            NewCommentViewModel.Factory(activity.application, args.albumId)
        )[NewCommentViewModel::class.java]

        collectorsViewModel = ViewModelProvider(
            this,
            CollectorViewModel.Factory(activity.application)
        )[CollectorViewModel::class.java]

        viewModel.albumDetail.observe(viewLifecycleOwner) {
            it.apply {
                viewModelAdapter = AlbumDetailAdapter(this)
                recyclerView.adapter = viewModelAdapter
                view?.findViewById<LinearLayout>(R.id.newCommentLayout)?.visibility =
                    View.VISIBLE
                if (this.tracks.size > 0) {
                    tracksViewModelAdapter = TracksAdapter(this.tracks)
                    binding.tracks.layoutManager = LinearLayoutManager(context)
                    binding.tracks.adapter = tracksViewModelAdapter
                    view?.findViewById<MaterialCardView>(R.id.tracksCardView)?.visibility =
                        View.VISIBLE
                    view?.findViewById<TextView>(R.id.tracksTitleCardView)?.visibility =
                        View.VISIBLE
                }
                if (this.comments.size > 0) {
                    commentsViewModelAdapter = CommentsAdapter(this.comments)
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

        val collectorsList: MutableList<String> = mutableListOf()
        var collectorsArr: Array<String>

        collectorsViewModel.collectors.observe(viewLifecycleOwner) { it ->
            it.forEach {
                collectorsList.add("${it.collectorId} - ${it.name}")
            }
            collectorsArr = collectorsList.toTypedArray()
            val aa = ArrayAdapter(
                activity.baseContext,
                android.R.layout.simple_spinner_item,
                collectorsArr
            )
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            view?.findViewById<Spinner>(R.id.commenterSpinner)?.adapter = aa
        }
        collectorsViewModel.eventNetworkError.observe(
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

    private fun showToast(message: String) {
        Toast.makeText(
            this.requireActivity().applicationContext,
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}