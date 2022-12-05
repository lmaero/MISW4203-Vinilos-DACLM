package com.example.grupo11_vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.grupo11_vinilos.models.MusicianDetail
import com.example.grupo11_vinilos.repositories.MusicianDetailRepository

class MusicianDetailViewModel(application: Application, musicianId: Int) :
    AndroidViewModel(application) {

    private val musicianDetailRepository = MusicianDetailRepository(application)

    private val _musicianDetail = MutableLiveData<MusicianDetail>()

    val musicianDetail: LiveData<MusicianDetail>
        get() = _musicianDetail

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id: Int = musicianId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        musicianDetailRepository.refreshData(id, {
            _musicianDetail.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        }, {
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, private val musicianId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MusicianDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST") return MusicianDetailViewModel(app, musicianId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
