package com.abc.task3fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abc.task3fragments.databinding.RecyclerItemPostBinding
import com.abc.task3fragments.models.Post
import com.bumptech.glide.Glide

class PostsAdapter(private val items: List<Post>) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    var onClickItem: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding: RecyclerItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Post, position: Int) {

            binding.apply {

                tvTitle.text = item.title

                Glide.with(ivPost.context).load(item.url).into(ivPost)

                layoutRoot.setOnClickListener {
                    onClickItem(position)
                }

            }
        }

    }
}