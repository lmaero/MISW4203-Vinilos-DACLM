package com.example.grupo11_vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.databinding.CollectorDetailItemBinding
import com.example.grupo11_vinilos.models.CollectorDetail

class CollectorDetailAdapter :
    RecyclerView.Adapter<CollectorDetailAdapter.CollectorDetailViewHolder>() {
    var collectorDetail: CollectorDetail = CollectorDetail(100, "", "", "")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorDetailViewHolder {
        val withDataBinding: CollectorDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorDetailViewHolder.LAYOUT,
            parent,
            false
        )
        return CollectorDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collectorDetail = collectorDetail
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    class CollectorDetailViewHolder(val viewDataBinding: CollectorDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collector_detail_item
        }
    }
}