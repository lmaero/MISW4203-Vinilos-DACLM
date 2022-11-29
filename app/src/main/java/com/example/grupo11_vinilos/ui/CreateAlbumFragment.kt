package com.example.grupo11_vinilos.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.viewmodels.CreateAlbumViewModel

class CreateAlbumFragment : Fragment() {

    companion object {
        fun newInstance() = CreateAlbumFragment()
    }

    private lateinit var viewModel: CreateAlbumViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.create_album_fragment, container, false)
        val saveButton = view.findViewById<Button>(R.id.buttonSaveAlbumInformation)
        saveButton.setOnClickListener{
            Log.d("SaveButton", "Save button was pressed")
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateAlbumViewModel::class.java)
        // TODO: Use the ViewModel
    }

}