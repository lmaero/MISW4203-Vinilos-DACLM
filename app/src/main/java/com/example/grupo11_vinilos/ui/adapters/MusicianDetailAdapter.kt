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
import com.example.grupo11_vinilos.databinding.MusicianDetailItemBinding
import com.example.grupo11_vinilos.models.MusicianDetail


class MusicianDetailAdapter(private var musicianDetail: MusicianDetail) :
    RecyclerView.Adapter<MusicianDetailAdapter.MusicianDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicianDetailViewHolder {
        val withDataBinding: MusicianDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            MusicianDetailViewHolder.LAYOUT,
            parent,
            false
        )
        return MusicianDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: MusicianDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.musicianDetail = musicianDetail
        }
        holder.bind(musicianDetail)
    }

    override fun getItemCount(): Int {
        return 1
    }

    class MusicianDetailViewHolder(val viewDataBinding: MusicianDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.musician_detail_item
        }

        fun bind(musician: MusicianDetail) {
            Glide.with(itemView)
                .load(musician.image.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image)
                )
                .into(viewDataBinding.musicianDetailImage)
        }
    }
}