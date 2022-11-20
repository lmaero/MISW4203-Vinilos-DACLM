package com.example.grupo11_vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.databinding.TrackItemBinding
import com.example.grupo11_vinilos.models.Track

class TracksAdapter(private var tracks: MutableList<Track>) : RecyclerView.Adapter<TracksAdapter.TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val withDataBinding: TrackItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            TrackViewHolder.LAYOUT,
            parent,
            false
        )
        return TrackViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.viewDataBinging.track = tracks[position]
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    class TrackViewHolder(val viewDataBinging: TrackItemBinding) :
        RecyclerView.ViewHolder(viewDataBinging.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.track_item
        }
    }
}