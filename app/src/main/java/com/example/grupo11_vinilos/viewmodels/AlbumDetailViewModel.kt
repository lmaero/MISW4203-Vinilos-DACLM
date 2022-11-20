package com.example.grupo11_vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.grupo11_vinilos.models.AlbumDetail
import com.example.grupo11_vinilos.repositories.AlbumDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumDetailViewModel(application: Application, albumId: Int) : AndroidViewModel(application) {

    private val albumDetailRepository = AlbumDetailRepository(application)

    private val _albumDetail = MutableLiveData<AlbumDetail>()

    val albumDetail: LiveData<AlbumDetail>
        get() = _albumDetail

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id: Int = albumId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.Main) {
                    _albumDetail.value = albumDetailRepository.refreshData(id)
                }
            }
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        } catch (e: Exception) {
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumDetailViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
