package com.example.grupo11_vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.databinding.MusicianDetailItemBinding
import com.example.grupo11_vinilos.models.Album
import com.example.grupo11_vinilos.models.MusicianDetail


class MusicianDetailAdapter :
    RecyclerView.Adapter<MusicianDetailAdapter.MusicianDetailViewHolder>() {
    var albumsList: MutableList<Album> = mutableListOf<Album>()
    var musicianDetail: MusicianDetail =
        MusicianDetail(100, "", "", "", "", albumsList)
        set(value) {

            field = value
            notifyDataSetChanged()
        }


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
    }
}