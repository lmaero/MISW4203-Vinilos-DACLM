package com.example.grupo11_vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.databinding.AlbumDetailItemBinding
import com.example.grupo11_vinilos.models.AlbumDetail
import com.example.grupo11_vinilos.models.Comment
import com.example.grupo11_vinilos.models.Track

class AlbumDetailAdapter : RecyclerView.Adapter<AlbumDetailAdapter.AlbumDetailViewHolder>() {
    var tracksList: MutableList<Track> = mutableListOf<Track>()
    var commentsList: MutableList<Comment> = mutableListOf<Comment>()
    var albumDetail: AlbumDetail = AlbumDetail(100, "", "", "", "", "", "", tracksList, commentsList, "")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailViewHolder {
        val withDataBinding: AlbumDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumDetailViewHolder.LAYOUT,
            parent,
            false
        )
        return AlbumDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.albumDetail = albumDetail
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    class AlbumDetailViewHolder(val viewDataBinding: AlbumDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_detail_item
        }
    }
}