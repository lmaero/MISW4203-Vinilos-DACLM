package com.example.grupo11_vinilos.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.grupo11_vinilos.R
import com.example.grupo11_vinilos.databinding.CommentItemBinding
import com.example.grupo11_vinilos.models.Comment

class CommentsAdapter(private var comments: MutableList<Comment>) :
    RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val withDataBinding: CommentItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CommentsViewHolder.LAYOUT,
            parent,
            false
        )
        return CommentsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.viewDataBinding.comment = comments[position]
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    class CommentsViewHolder(val viewDataBinding: CommentItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.comment_item
        }
    }
}