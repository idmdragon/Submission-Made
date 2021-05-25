package com.idm.onepiecelist.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.idm.onepiecelist.core.data.source.Resource
import com.idm.onepiecelist.core.ui.ListItemAdapter
import com.idm.onepiecelist.core.vo.Status
import com.idm.onepiecelist.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var adapter: ListItemAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activity.apply {
            viewModel.getList().observe(viewLifecycleOwner) {
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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}