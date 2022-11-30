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
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.android.volley.Response
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.databinding.CreateAlbumFragmentBinding
import com.example.grupo11_vinilos.models.NewAlbum
import org.json.JSONObject

class CreateAlbumFragment : Fragment() {

    private lateinit var name: String
    private lateinit var cover: String
    private lateinit var releaseDate: String
    private lateinit var genre: String
    private lateinit var recordLabel: String
    private lateinit var description: String
    private lateinit var newAlbum: NewAlbum
    private lateinit var jsonObject: JSONObject
    private lateinit var volleyBroker: VolleyBroker


    companion object {
        fun newInstance() = CreateAlbumFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.create_album_fragment, container, false)

        volleyBroker = VolleyBroker(this.requireContext())


        val saveButton = view.findViewById<Button>(R.id.buttonSaveAlbumInformation)

        saveButton.setOnClickListener {

            name = view.findViewById<EditText>(R.id.createAlbumName).text.toString()
            cover = view.findViewById<EditText>(R.id.createAlbumCover).text.toString()
            releaseDate = view.findViewById<EditText>(R.id.createAlbumReleaseDate).text.toString()
            genre = view.findViewById<EditText>(R.id.createAlbumGenre).text.toString()
            recordLabel = view.findViewById<EditText>(R.id.createAlbumRecordLabel).text.toString()
            description = view.findViewById<EditText>(R.id.createAlbumDescription).text.toString()

            Log.d("name", name)
            Log.d("cover", cover)
            Log.d("releaseDate", releaseDate)
            Log.d("genre", genre)
            Log.d("recordLabel", recordLabel)
            Log.d("description", description)

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
                    // Display the first 500 characters of the response string.
                    Log.d("Album", "posted")
                },
                Response.ErrorListener {
                    Log.d("Album", "No posted")
                }
            ))

        }
        return view
    }


        // TODO: Pasar la informacion al ViewModel
        // TODO: Crear el JSON con la informacion ingresada del album
        // TODO: Mandar el JSON al Repository para hacer el POST








}