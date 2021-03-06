package com.idm.onepiecelist.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.idm.onepiecelist.core.source.Resource
import com.idm.onepiecelist.core.ui.ListItemAdapter
import com.idm.onepiecelist.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: ListItemAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activity.apply {
            viewModel.items.observe(viewLifecycleOwner) {
                when (it) {
                 is Resource.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is Resource.Success -> {
                        binding.progressBar.isVisible = false
                        it.data?.let { item ->
                            adapter = ListItemAdapter(item)
                            adapter.notifyDataSetChanged()
                            binding.rvItems.adapter = adapter

                            adapter.onItemClick = { selectedData ->
                                val toDetailFragment = HomeFragmentDirections.actionHomeMenuToDetailFragment(selectedData)
                                 view.findNavController().navigate(toDetailFragment)
                            }
                        }

                    }
                    is Resource.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(
                            requireContext(),
                            "Error when Load a Data",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            binding.rvItems.layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.VERTICAL,false)
        }
    }




}