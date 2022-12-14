package com.example.grupo11_vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.databinding.AlbumDetailItemBinding
import com.example.grupo11_vinilos.models.AlbumDetail

class AlbumDetailAdapter(private var albumDetail: AlbumDetail) :
    RecyclerView.Adapter<AlbumDetailAdapter.AlbumDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailViewHolder {
        val withDataBinding: AlbumDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), AlbumDetailViewHolder.LAYOUT, parent, false
        )
        return AlbumDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.albumDetail = albumDetail
        }
        holder.bind(albumDetail)
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

        fun bind(album: AlbumDetail) {
            Glide.with(itemView).load(album.cover.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions().placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.ic_broken_image)
                ).into(viewDataBinding.albumDetailCover)
        }
    }
}