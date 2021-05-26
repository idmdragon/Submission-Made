package com.idm.onepiecelist.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.idm.onepiecelist.core.domain.model.OnePiece
import com.idm.onepiecelist.core.ui.ListItemAdapter
import com.idm.onepiecelist.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: ListItemAdapter
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.favoriteItems.observe(viewLifecycleOwner, ::setView)
    }

    private fun setView(item: List<OnePiece>) {
        if (item.isEmpty()) {
            binding.movieNotfound.isVisible = true
            adapter = ListItemAdapter(item)
        } else {
            binding.movieNotfound.isVisible = false
            adapter = ListItemAdapter(item)
        }
        binding.rvFavItem.adapter = adapter
        adapter.notifyDataSetChanged()
        binding.rvFavItem.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}