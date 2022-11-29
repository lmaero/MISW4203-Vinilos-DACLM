package com.example.grupo11_vinilos.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.viewmodels.CreateAlbumViewModel

class CreateAlbumFragment : Fragment() {

    companion object {
        fun newInstance() = CreateAlbumFragment()
    }

    private lateinit var viewModel: CreateAlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_album_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateAlbumViewModel::class.java)
        // TODO: Use the ViewModel
    }

}