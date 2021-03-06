package com.idm.onepiecelist.favorite.favorite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.idm.onepiecelist.core.domain.model.OnePiece
import com.idm.onepiecelist.core.ui.ListItemAdapter
import com.idm.onepiecelist.favorite.databinding.FragmentFavoriteBinding
import com.idm.onepiecelist.favorite.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: ListItemAdapter
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(favoriteModule)
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.favoriteItems.observe(viewLifecycleOwner, ::setView)
    }

    private fun setView(item: List<OnePiece>) {
        if (item.isEmpty()) {
            binding.movieNotfound.visibility = View.VISIBLE
            adapter = ListItemAdapter(item)
        } else {
            binding.movieNotfound.visibility = View.GONE
            adapter = ListItemAdapter(item)
            adapter.onItemClick = { selectedData ->
                val toDetailFragment =
                    FavoriteFragmentDirections.actionFavoriteMenuToDetailFragment(selectedData)
                view?.findNavController()?.navigate(toDetailFragment)
            }
        }
        binding.rvFavItem.adapter = adapter
        adapter.notifyDataSetChanged()
        binding.rvFavItem.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
    }




}