package com.idm.onepiecelist.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.snackbar.Snackbar
import com.idm.onepiecelist.R
import com.idm.onepiecelist.core.data.source.local.entity.OnePieceEntity
import com.idm.onepiecelist.core.domain.model.OnePiece
import com.idm.onepiecelist.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        const val ID = "ID"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentItem = intent.getParcelableExtra<OnePiece>(ID)

        binding.btnBack.setOnClickListener {
            finish()
        }

        bindView(intentItem)

    }

    private fun bindView(intentItem: OnePiece?) {
        with(binding) {
            intentItem.let {
                if (it != null) {
                    var statusFavorite = it.isFavorite
                    stateFavoriteIcon(statusFavorite)
                    binding.btnSave.setOnClickListener { view ->
                        statusFavorite = !statusFavorite
                        viewModel.setFavoriteItem(it, statusFavorite)
                        stateFavoriteIcon(statusFavorite)
                        if (statusFavorite){
                            Snackbar.make(view, "Item Saved to Favorite", Snackbar.LENGTH_SHORT).show()
                        } else {
                            Snackbar.make(view, "Item Deleted from Favorite", Snackbar.LENGTH_SHORT).show()

                        }

                    }
                    progressBar.isVisible = false
                    tvTittle.text = it.title
                    tvStoryline.text = it.synopsis
                    tvRated.text = it.rated
                    tvStar.text = it.score.toString()
                    if (it.airing) {
                        val isAiring = "Is Airing"
                        tvStatus.text = isAiring
                    } else {
                        val isNotAiring = "Is Not Airing"
                        tvStatus.text = isNotAiring
                    }
                    Glide.with(this@DetailActivity)
                        .load(it.image_url)
                        .transform(CenterCrop(), RoundedCorners(16))
                        .placeholder(ColorDrawable(Color.CYAN))
                        .into(ivPoster)
                }
            }


        }
    }

    private fun stateFavoriteIcon(state: Boolean) {
        if (state == true) {
            binding.btnSave.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_favorite_active
                )
            )


        } else if (state == false) {
            binding.btnSave.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_favorite_inactive
                )
            )
        }

    }
}