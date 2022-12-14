package com.example.grupo11_vinilos.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.databinding.NewAlbumFragmentBinding
import com.example.grupo11_vinilos.viewmodels.NewAlbumViewModel

class NewAlbumFragment : Fragment() {

    private lateinit var name: String
    private lateinit var cover: String
    private lateinit var releaseDate: String
    private lateinit var genre: String
    private lateinit var recordLabel: String
    private lateinit var description: String

    private lateinit var viewModel: NewAlbumViewModel
    private lateinit var binding: NewAlbumFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewAlbumFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        val saveButton = view.findViewById<Button>(R.id.buttonSaveAlbumInformation)
        saveButton.setOnClickListener {
            name = view.findViewById<EditText>(R.id.newAlbumName).text.toString()
            viewModel.setNewAlbumName(name)

            cover = view.findViewById<EditText>(R.id.newAlbumCover).text.toString()
            viewModel.setNewAlbumCover(cover)

            releaseDate = view.findViewById<EditText>(R.id.newAlbumReleaseDate).text.toString()
            viewModel.setNewAlbumReleaseDate(releaseDate)

            genre = view.findViewById<EditText>(R.id.newAlbumGenre).text.toString()
            viewModel.setNewAlbumGenre(genre)

            recordLabel = view.findViewById<EditText>(R.id.newAlbumRecordLabel).text.toString()
            viewModel.setNewAlbumRecordLabel(recordLabel)

            description = view.findViewById<EditText>(R.id.newAlbumDescription).text.toString()
            viewModel.setNewAlbumDescription(description)

            viewModel.setInfo(
                viewModel.getNewAlbumName().value.toString(),
                viewModel.getNewAlbumCover().value.toString(),
                viewModel.getNewAlbumReleaseDate().value.toString(),
                viewModel.getNewAlbumGenre().value.toString(),
                viewModel.getNewAlbumRecordLabel().value.toString(),
                viewModel.getNewAlbumDescription().value.toString()
            )
        }
        return view
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
            NewAlbumViewModel.Factory(activity.application)
        )[NewAlbumViewModel::class.java]
        viewModel.getNewAlbumName().observe(viewLifecycleOwner) {
            it.apply {
                Log.d("Test", viewModel.getNewAlbumName().value.toString())
            }
        }

        viewModel.getNewAlbumCover().observe(viewLifecycleOwner) {
            it.apply {
                Log.d("Test", viewModel.getNewAlbumCover().value.toString())
            }
        }

        viewModel.getNewAlbumReleaseDate().observe(viewLifecycleOwner) {
            it.apply {
                Log.d("Test", viewModel.getNewAlbumReleaseDate().value.toString())
            }
        }

        viewModel.getNewAlbumGenre().observe(viewLifecycleOwner) {
            it.apply {
                Log.d("Test", viewModel.getNewAlbumGenre().value.toString())
            }
        }

        viewModel.getNewAlbumRecordLabel().observe(viewLifecycleOwner) {
            it.apply {
                Log.d("Test", viewModel.getNewAlbumRecordLabel().value.toString())
            }
        }

        viewModel.getNewAlbumDescription().observe(viewLifecycleOwner) {
            it.apply {
                Log.d("Test", viewModel.getNewAlbumDescription().value.toString())
            }
        }
    }
}