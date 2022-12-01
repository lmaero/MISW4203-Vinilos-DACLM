package com.example.grupo11_vinilos.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Response
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.databinding.AlbumFragmentBinding
import com.example.grupo11_vinilos.databinding.NewAlbumFragmentBinding
import com.example.grupo11_vinilos.repositories.CollectorDetailRepository
import com.example.grupo11_vinilos.ui.adapters.AlbumsAdapter
import com.example.grupo11_vinilos.viewmodels.AlbumViewModel
import com.example.grupo11_vinilos.viewmodels.NewAlbumViewModel
import org.json.JSONObject

class NewAlbumFragment : Fragment() {

    private lateinit var name: String
    private lateinit var cover: String
    private lateinit var releaseDate: String
    private lateinit var genre: String
    private lateinit var recordLabel: String
    private lateinit var description: String
    private lateinit var volleyBroker: VolleyBroker

    private lateinit var viewModel: NewAlbumViewModel
    private lateinit var binding: NewAlbumFragmentBinding


    companion object {
        fun newInstance() = NewAlbumFragment()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NewAlbumFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        //val view = inflater.inflate(R.layout.new_album_fragment, container, false)
        val saveButton = view.findViewById<Button>(R.id.buttonSaveAlbumInformation)
        saveButton.setOnClickListener{
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

    /*
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.new_album_fragment, container, false)

        volleyBroker = VolleyBroker(this.requireContext())

        val saveButton = view.findViewById<Button>(R.id.buttonSaveAlbumInformation)

        saveButton.setOnClickListener {

            name = view.findViewById<EditText>(R.id.newAlbumName).text.toString()
            cover = view.findViewById<EditText>(R.id.newAlbumCover).text.toString()
            releaseDate = view.findViewById<EditText>(R.id.newAlbumReleaseDate).text.toString()
            genre = view.findViewById<EditText>(R.id.newAlbumGenre).text.toString()
            recordLabel = view.findViewById<EditText>(R.id.newAlbumRecordLabel).text.toString()
            description = view.findViewById<EditText>(R.id.newAlbumDescription).text.toString()

            val postParams = mapOf<String, Any>(
                "name" to name,
                "cover" to cover,
                "releaseDate" to releaseDate,
                "genre" to genre,
                "recordLabel" to recordLabel,
                "description" to description,
            )
            volleyBroker.instance.add(VolleyBroker.postRequest("albums", JSONObject(postParams),
                Response.Listener<JSONObject> { response ->
                    Log.d("Album", "posted")
                },
                Response.ErrorListener {
                    Log.d("Album", "No posted")
                }
            ))

        }
        return view
    }
    */
}