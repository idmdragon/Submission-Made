package com.idm.onepiecelist.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.snackbar.Snackbar
import com.idm.onepiecelist.R
import com.idm.onepiecelist.core.domain.model.OnePiece
import com.idm.onepiecelist.databinding.FragmentDetailBinding
import com.idm.onepiecelist.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if (bundle == null) {
            Log.e("DetailFragment", "Detail Fragment Cant get Bundle")
            return
        }
        binding.btnBack.setOnClickListener {
            this.findNavController().popBackStack()
        }

        val item = DetailFragmentArgs.fromBundle(bundle).onePiece
        bindView(item)

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
                        if (statusFavorite) {
                            Snackbar.make(view, "Item Saved to Favorite", Snackbar.LENGTH_SHORT)
                                .show()
                        } else {
                            Snackbar.make(view, "Item Deleted from Favorite", Snackbar.LENGTH_SHORT)
                                .show()

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
                    Glide.with(requireActivity())
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
            binding.btnSave.setImageResource(R.drawable.ic_favorite_active)
        } else if (state == false) {
            binding.btnSave.setImageResource(R.drawable.ic_favorite_inactive)

        }

    }

}