package com.example.grupo11_vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.grupo11_vinilos.models.Collector
import com.example.grupo11_vinilos.repositories.NewCommentRepository
import org.json.JSONObject

class NewCommentViewModel(application: Application, albumId: Int): AndroidViewModel(application) {
    private val id: Int = albumId
    private var _description: MutableLiveData<String> = MutableLiveData<String>("")
    private var _rating: MutableLiveData<Int> = MutableLiveData(0)
    private var _collector: MutableLiveData<Collector> = MutableLiveData()

    private val _newCommentRepository = NewCommentRepository(application)


    fun getDescription(): MutableLiveData<String> {
        return _description
    }

    fun getRating(): MutableLiveData<Int> {
        return _rating
    }

    fun getCollector(): MutableLiveData<Collector> {
        return _collector
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    fun setRating(rating: Int) {
        _rating.value = rating
    }

    fun setCollector(collector: Collector) {
        _collector.value = collector
    }

    fun setInfo(description: String, rating: Int, collector: Collector) {
        val collectorBody = mapOf<String, Any>(
            "id" to collector.collectorId,
            "name" to collector.name,
        )

        val postBody = mapOf<String, Any>(
            "description" to description,
            "rating" to rating,
            "collector" to collectorBody
        )

        _newCommentRepository.refreshData(JSONObject(postBody), id)
    }

    class Factory(val application: Application, val albumId: Int): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewCommentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewCommentViewModel(application, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}