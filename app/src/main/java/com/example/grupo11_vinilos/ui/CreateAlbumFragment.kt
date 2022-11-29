package com.example.grupo11_vinilos.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.viewmodels.CreateAlbumViewModel

class CreateAlbumFragment : Fragment() {

    companion object {
        fun newInstance() = CreateAlbumFragment()
    }

    private lateinit var viewModel: CreateAlbumViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.create_album_fragment, container, false)

        val name = view.findViewById<EditText>(R.id.createAlbumName)
        val cover = view.findViewById<EditText>(R.id.createAlbumCover)
        val releaseDate = view.findViewById<EditText>(R.id.createAlbumReleaseDate)
        val genre = view.findViewById<EditText>(R.id.createAlbumGenre)
        val recordLabel = view.findViewById<EditText>(R.id.createAlbumRecordLabel)
        val description = view.findViewById<EditText>(R.id.createAlbumDescription)
        val saveButton = view.findViewById<Button>(R.id.buttonSaveAlbumInformation)

        saveButton.setOnClickListener{
            Log.d("name", name.text.toString())
            Log.d("cover", cover.text.toString())
            Log.d("releaseDate", releaseDate.text.toString())
            Log.d("genre", genre.text.toString())
            Log.d("recordLabel", recordLabel.text.toString())
            Log.d("description", description.text.toString())
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateAlbumViewModel::class.java)
        // TODO: Use the ViewModel
    }

}