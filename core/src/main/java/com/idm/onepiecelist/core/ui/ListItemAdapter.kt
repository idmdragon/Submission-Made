package com.idm.onepiecelist.core.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.idm.onepiecelist.core.databinding.ItemListBinding
import com.idm.onepiecelist.core.domain.model.OnePiece

class ListItemAdapter(private val listItem: List<OnePiece>) :
    RecyclerView.Adapter<ListItemAdapter.ListViewHolder>() {

    var onItemClick: ((OnePiece) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemBinding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemBinding)
    }

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listItem[position])
    }


    inner class ListViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OnePiece) {
            with(binding) {
                tvTittle.text = item.title
                val score: Double = item.score / 2
                ratingBar.rating = score.toFloat()
                tvRated.text = item.rated

                Glide.with(itemView.context)
                    .load(item.image_url)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .placeholder(ColorDrawable(Color.GRAY))
                    .apply(RequestOptions())
                    .into(ivPoster)
            }

        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listItem[adapterPosition])
            }
        }
    }
    }