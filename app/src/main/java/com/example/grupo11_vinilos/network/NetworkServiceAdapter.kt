package com.example.grupo11_vinilos.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.grupo11_vinilos.models.Album
import com.example.grupo11_vinilos.models.AlbumDetail
import com.example.grupo11_vinilos.models.Comment
import com.example.grupo11_vinilos.models.Track
import org.json.JSONArray
import org.json.JSONObject

class NetworkServiceAdapter constructor(context: Context) {
    companion object {
        const val BASE_URL = "https://grupo11-vynils-back.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun getAlbums(onComplete: (resp: List<Album>) -> Unit, onError: (error: VolleyError) -> Unit) {
        requestQueue.add(
            getRequest("albums",
                Response.Listener<String> { response ->
                    val resp = JSONArray(response)
                    val list = mutableListOf<Album>()
                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        var performer =
                            item.getString("performers").substring(19, 100).substringBefore(",")
                                .substringBefore("\"")
                        list.add(
                            i,
                            Album(
                                albumId = item.getInt("id"),
                                name = item.getString("name"),
                                cover = item.getString("cover"),
                                recordLabel = item.getString("recordLabel"),
                                releaseDate = item.getString("releaseDate"),
                                genre = item.getString("genre"),
                                description = item.getString("description"),
                                performers = performer
                            )
                        )
                    }
                    onComplete(list)
                },
                Response.ErrorListener {
                    onError(it)
                })
        )
    }

    fun getAlbumDetail(
        albumId: Int,
        onComplete: (resp: AlbumDetail) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        requestQueue.add(
            getRequest("albums/$albumId",
                Response.Listener<String> { response ->
                    val resp = JSONObject(response)
                    val id = resp.getInt("id")
                    val name = resp.getString("name")
                    val cover = resp.getString("cover")
                    val releaseDate = resp.getString("releaseDate").substring(0, 4)
                    val description = resp.getString("description")
                    val genre = resp.getString("genre")
                    val recordLabel = resp.getString("recordLabel")
                    val tracksJSON = resp.getJSONArray("tracks")
                    val commentsJSON = resp.getJSONArray("comments")
                    val performersJSON = resp.getJSONArray("performers")

                    val trackList: MutableList<Track> = mutableListOf<Track>()
                    for (track in 0..tracksJSON.length() - 1) {
                        var id = (tracksJSON.get(track) as JSONObject).getInt("id")
                        var name = (tracksJSON.get(track) as JSONObject).getString("name")
                        var duration = (tracksJSON.get(track) as JSONObject).getString("duration")
                        var localTrack = Track(id, name, duration)
                        trackList.add(localTrack)
                    }
                    val commentList: MutableList<Comment> = mutableListOf<Comment>()
                    for (comment in 0..commentsJSON.length() - 1) {
                        var id = (commentsJSON.get(comment) as JSONObject).getInt("id")
                        var name =
                            (commentsJSON.get(comment) as JSONObject).getString("description")
                        var rating = (commentsJSON.get(comment) as JSONObject).getInt("rating")
                        var localComment = Comment(id, name, rating)
                        commentList.add(localComment)
                    }
                    var performerName: String = ""
                    for (perfomer in 0..performersJSON.length() - 1) {
                        performerName =
                            (performersJSON.get(perfomer) as JSONObject).getString("name")
                        if (perfomer.equals(0)) {
                            break
                        }
                    }
                    onComplete(
                        AlbumDetail(
                            id,
                            name,
                            cover,
                            releaseDate,
                            description,
                            genre,
                            recordLabel,
                            trackList,
                            commentList,
                            performerName
                        )
                    )
                },
                Response.ErrorListener {
                    onError(it)
                })
        )
    }

    private fun getRequest(
        path: String,
        responseListener: Response.Listener<String>,
        errorListener: Response.ErrorListener
    ): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL + path, responseListener, errorListener)
    }
}