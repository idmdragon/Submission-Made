package com.idm.onepiecelist.core.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.idm.onepiecelist.core.data.source.local.entity.OnePieceEntity
import com.idm.onepiecelist.core.domain.model.OnePiece
import com.idm.onepiecelist.databinding.ItemListBinding
import com.idm.onepiecelist.detail.DetailActivity


class ListViewHolder(private val binding: ItemListBinding) :
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

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.ID, item)
                itemView.context.startActivity(intent)
            }
        }
    }
}
