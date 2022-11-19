package com.example.grupo11_vinilos.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.grupo11_vinilos.models.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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
                { response ->
                    val resp = JSONArray(response)
                    val list = mutableListOf<Album>()
                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        val performer =
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
                {
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
                { response ->
                    val resp = JSONObject(response)
                    val id = resp.getInt("id")
                    val albumName = resp.getString("name")
                    val cover = resp.getString("cover")
                    val releaseDate = resp.getString("releaseDate").substring(0, 4)
                    val description = resp.getString("description")
                    val genre = resp.getString("genre")
                    val recordLabel = resp.getString("recordLabel")
                    val tracksJSON = resp.getJSONArray("tracks")
                    val commentsJSON = resp.getJSONArray("comments")
                    val performersJSON = resp.getJSONArray("performers")

                    val trackList: MutableList<Track> = mutableListOf()
                    for (track in 0 until tracksJSON.length()) {
                        val trackId = (tracksJSON.get(track) as JSONObject).getInt("id")
                        val trackName = (tracksJSON.get(track) as JSONObject).getString("name")
                        val duration = (tracksJSON.get(track) as JSONObject).getString("duration")
                        val localTrack = Track(trackId, trackName, duration)
                        trackList.add(localTrack)
                    }
                    val commentList: MutableList<Comment> = mutableListOf()
                    for (comment in 0 until commentsJSON.length()) {
                        val commentId = (commentsJSON.get(comment) as JSONObject).getInt("id")
                        val commentName =
                            (commentsJSON.get(comment) as JSONObject).getString("description")
                        val rating = (commentsJSON.get(comment) as JSONObject).getInt("rating")
                        val localComment = Comment(commentId, commentName, rating)
                        commentList.add(localComment)
                    }
                    var performerName = ""
                    for (performer in 0 until performersJSON.length()) {
                        performerName =
                            (performersJSON.get(performer) as JSONObject).getString("name")
                        if (performer == 0) {
                            break
                        }
                    }
                    onComplete(
                        AlbumDetail(
                            id,
                            albumName,
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
                {
                    onError(it)
                })
        )
    }

    fun getMusicians(
        onComplete: (resp: List<Musician>) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        requestQueue.add(
            getRequest(
                "musicians",
                { response ->
                    val resp = JSONArray(response)
                    val list = mutableListOf<Musician>()
                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        list.add(
                            i,
                            Musician(
                                musicianId = item.getInt("id"),
                                name = item.getString("name"),
                                image = item.getString("image"),
                                description = item.getString("description"),
                                birthDate = item.getString("birthDate")
                            )
                        )
                    }
                    onComplete(list)
                },
                {
                    onError(it)
                })
        )
    }

    fun getMusicianDetail(
        musicianId: Int,
        onComplete: (resp: MusicianDetail) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        requestQueue.add(
            getRequest("musicians/$musicianId",
                { response ->
                    val resp = JSONObject(response)
                    val id = resp.getInt("id")
                    val musicianName = resp.getString("name")
                    val image = resp.getString("image")
                    val description = resp.getString("description")
                    val birthDate = resp.getString("birthDate").substring(0, 4)
                    val albumsJSON = resp.getJSONArray("albums")
                    val albumList: MutableList<Album> = mutableListOf()
                    for (albums in 0 until albumsJSON.length()) {
                        val albumId = (albumsJSON.get(albums) as JSONObject).getInt("id")
                        val albumName = (albumsJSON.get(albums) as JSONObject).getString("name")
                        val albumCover = (albumsJSON.get(albums) as JSONObject).getString("cover")
                        val albumDescription =
                            (albumsJSON.get(albums) as JSONObject).getString("description")
                        val albumGenre = (albumsJSON.get(albums) as JSONObject).getString("genre")
                        val albumRecordLabel =
                            (albumsJSON.get(albums) as JSONObject).getString("recordLabel")


                        val localAlbum = Album(
                            albumId,
                            albumName,
                            albumCover,
                            albumDescription,
                            albumGenre,
                            albumRecordLabel,
                            "",
                            ""
                        )
                        albumList.add(localAlbum)
                    }

                    onComplete(
                        MusicianDetail(
                            id,
                            musicianName,
                            image,
                            description,
                            birthDate,
                            albumList

                        )
                    )
                },
                {
                    onError(it)
                })
        )
    }

    suspend fun getCollectors() = suspendCoroutine<List<Collector>> { cont ->
        requestQueue.add(
            getRequest("collectors",
                { response ->
                    val resp = JSONArray(response)
                    val list = mutableListOf<Collector>()

                    for (i in 0 until resp.length()) {
                        val item = resp.getJSONObject(i)
                        val collector = Collector(
                            collectorId = item.getInt("id"),
                            name = item.getString("name"),
                            telephone = item.getString("telephone"),
                            email = item.getString("email"),
                        )

                        list.add(i, collector)
                    }
                    cont.resume(list)
                },
                { cont.resumeWithException(it) })
        )
    }

    fun getCollectorDetail(
        id: Int,
        onComplete: (resp: CollectorDetail) -> Unit,
        onError: (error: VolleyError) -> Unit
    ) {
        requestQueue.add(
            getRequest(
                "collectors/$id",
                { response ->
                    val resp = JSONObject(response)
                    val collectorId = resp.getInt("id")
                    val name = resp.getString("name")
                    val telephone = resp.getString("telephone")
                    val email = resp.getString("email")

                    onComplete(CollectorDetail(collectorId, name, telephone, email))
                },
                {
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