package com.example.grupo11_vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.grupo11_vinilos.repositories.NewAlbumRepository
import org.json.JSONObject

class NewAlbumViewModel(application: Application) : AndroidViewModel(application) {

    private var _name: MutableLiveData<String> = MutableLiveData<String>("")
    private var _cover: MutableLiveData<String> = MutableLiveData("")
    private var _releaseDate: MutableLiveData<String> = MutableLiveData<String>("")
    private var _genre: MutableLiveData<String> = MutableLiveData<String>("")
    private var _recordLabel: MutableLiveData<String> = MutableLiveData<String>("")
    private var _description: MutableLiveData<String> = MutableLiveData<String>("")

    private val newAlbumRepository = NewAlbumRepository(application)

    fun setNewAlbumName(name: String) {
        _name.value = name
        //newAlbumRepository.refreshData(_name.value.toString())
    }

    fun getNewAlbumName(): LiveData<String> {
        return _name
    }

    fun setNewAlbumCover(cover: String) {
        _cover.value = cover
    }

    fun getNewAlbumCover(): LiveData<String> {
        return _cover
    }

    fun setNewAlbumReleaseDate(releaseDate: String) {
        _releaseDate.value = releaseDate
    }

    fun getNewAlbumReleaseDate(): LiveData<String> {
        return _releaseDate
    }

    fun setNewAlbumGenre(genre: String) {
        _genre.value = genre
    }

    fun getNewAlbumGenre(): LiveData<String> {
        return _genre
    }

    fun setNewAlbumRecordLabel(recordLabel: String) {
        _recordLabel.value = recordLabel
    }

    fun getNewAlbumRecordLabel(): LiveData<String> {
        return _recordLabel
    }

    fun setNewAlbumDescription(description: String) {
        _description.value = description
    }

    fun getNewAlbumDescription(): LiveData<String> {
        return _description
    }

    fun setInfo(
        name: String,
        cover: String,
        releaseDate: String,
        genre: String,
        recordLabel: String,
        description: String
    ) {
        val postParams = mapOf<String, Any>(
            "name" to name,
            "cover" to cover,
            "releaseDate" to releaseDate,
            "genre" to genre,
            "recordLabel" to recordLabel,
            "description" to description,
        )
        newAlbumRepository.refreshData(JSONObject(postParams))
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewAlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewAlbumViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}